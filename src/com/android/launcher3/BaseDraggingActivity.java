/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3;

import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Process;
import android.os.StrictMode;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.ActionMode;
import android.view.Surface;
import android.view.View;
import android.widget.Toast;
import android.hardware.biometrics.BiometricPrompt;

import co.aoscp.lovegood.Bits;
import co.aoscp.lovegood.SettingsFragment;
import co.aoscp.lovegood.micode.MiBits;
import co.aoscp.lovegood.micode.biometrics.AuthenticationCallback;
import co.aoscp.lovegood.micode.biometrics.BiometricsManager;

import com.android.launcher3.LauncherSettings.Favorites;
import com.android.launcher3.badge.BadgeInfo;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.uioverrides.DisplayRotationListener;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.views.BaseDragLayer;

import java.util.concurrent.Executors;

/**
 * Extension of BaseActivity allowing support for drag-n-drop
 */
public abstract class BaseDraggingActivity extends BaseActivity
        implements WallpaperColorInfo.OnChangeListener {

    private static final String TAG = "BaseDraggingActivity";

    // The Intent extra that defines whether to ignore the launch animation
    public static final String INTENT_EXTRA_IGNORE_LAUNCH_ANIMATION =
            "com.android.launcher3.intent.extra.shortcut.INGORE_LAUNCH_ANIMATION";

    /** Whether to force dark theme if Configuration.UI_MODE_NIGHT_YES. */
    private static final boolean DARK_THEME_IN_NIGHT_MODE = true;

    // When starting an action mode, setting this tag will cause the action mode to be cancelled
    // automatically when user interacts with the launcher.
    public static final Object AUTO_CANCEL_ACTION_MODE = new Object();

    /** The system setting for Color Manager **/
    private static final String COLOR_MANAGER_THEME = "system_theme";

    private ActionMode mCurrentActionMode;
    protected boolean mIsSafeModeEnabled;

    private OnStartCallback mOnStartCallback;

    private int mThemeRes = R.style.AppTheme;

    private DisplayRotationListener mRotationListener;

    private View mAuthView;
    private Intent mAuthIntent;
    private ItemInfo mAuthItem;
    private boolean mIsAuthorized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsSafeModeEnabled = getPackageManager().isSafeMode();
        mRotationListener = new DisplayRotationListener(this, this::onDeviceRotationChanged);

        // Update theme
        WallpaperColorInfo wallpaperColorInfo = WallpaperColorInfo.getInstance(this);
        wallpaperColorInfo.addOnChangeListener(this);
        int themeRes = getThemeRes(wallpaperColorInfo);
        if (themeRes != mThemeRes) {
            mThemeRes = themeRes;
        }
        updateTheme(wallpaperColorInfo);
    }

    @Override
    public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
        if (mThemeRes != getThemeRes(wallpaperColorInfo)) {
            recreate();
        }
    }

    protected int getThemeRes(WallpaperColorInfo wallpaperColorInfo) {
        if (wallpaperColorInfo.isDark()) {
            return wallpaperColorInfo.supportsDarkText() ?
                    R.style.AppTheme_Dark_DarkText : R.style.AppTheme_Dark;
        } else {
            return wallpaperColorInfo.supportsDarkText() ?
                    R.style.AppTheme_DarkText : R.style.AppTheme;
        }
    }

    protected void updateTheme(WallpaperColorInfo wallpaperColorInfo) {
        final Configuration config = this.getResources().getConfiguration();
        ContentResolver resolver = this.getContentResolver();
        final boolean supportsDarkText = wallpaperColorInfo.supportsDarkText();
        final int systemTheme = Settings.Secure.getInt(resolver, COLOR_MANAGER_THEME, 0);
        final boolean nightModeWantsDarkTheme = DARK_THEME_IN_NIGHT_MODE
                && (config.uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        switch (systemTheme) {
            case 1:
                setTheme(supportsDarkText ? R.style.LauncherTheme_DarkText : R.style.LauncherTheme);
                break;
            case 2:
                setTheme(supportsDarkText ? R.style.LauncherTheme_Dark_DarkText : R.style.LauncherTheme_Dark);
                break;
            case 3:
                setTheme(supportsDarkText ? R.style.LauncherTheme_Black_DarkText : R.style.LauncherTheme_Black);
                break;
            default:
                setTheme(nightModeWantsDarkTheme ? R.style.LauncherTheme_Dark : mThemeRes);
                break;
        }
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        mCurrentActionMode = mode;
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        mCurrentActionMode = null;
    }

    public boolean finishAutoCancelActionMode() {
        if (mCurrentActionMode != null && AUTO_CANCEL_ACTION_MODE == mCurrentActionMode.getTag()) {
            mCurrentActionMode.finish();
            return true;
        }
        return false;
    }

    public abstract BaseDragLayer getDragLayer();

    public abstract <T extends View> T getOverviewPanel();

    public abstract View getRootView();

    public abstract BadgeInfo getBadgeInfoForItem(ItemInfo info);

    public abstract void invalidateParent(ItemInfo info);

    public static BaseDraggingActivity fromContext(Context context) {
        if (context instanceof BaseDraggingActivity) {
            return (BaseDraggingActivity) context;
        }
        return ((BaseDraggingActivity) ((ContextWrapper) context).getBaseContext());
    }

    public Rect getViewBounds(View v) {
        int[] pos = new int[2];
        v.getLocationOnScreen(pos);
        return new Rect(pos[0], pos[1], pos[0] + v.getWidth(), pos[1] + v.getHeight());
    }

    public final Bundle getActivityLaunchOptionsAsBundle(View v) {
        ActivityOptions activityOptions = getActivityLaunchOptions(v);
        return activityOptions == null ? null : activityOptions.toBundle();
    }

    public abstract ActivityOptions getActivityLaunchOptions(View v);

    public boolean startActivitySafely(View v, Intent intent, ItemInfo item, boolean skipAuth) {
        if (mIsSafeModeEnabled && !Utilities.isSystemApp(this, intent)) {
            Toast.makeText(this, R.string.safemode_shortcut_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean isAppLock = Utilities.getPrefs(getBaseContext()).getBoolean(SettingsFragment.KEY_APP_LOCK, false)
                && MiBits.hasBiometricsSupport(getBaseContext()) && Bits.hasEnrolledFingerprints(getBaseContext());
        if (!isAppLock || mIsAuthorized || skipAuth) {
            mIsAuthorized = false;
            // Only launch using the new animation if the shortcut has not opted out (this is a
            // private contract between launcher and may be ignored in the future).
            boolean useLaunchAnimation = (v != null) &&
                    !intent.hasExtra(INTENT_EXTRA_IGNORE_LAUNCH_ANIMATION);
            Bundle optsBundle = useLaunchAnimation
                    ? getActivityLaunchOptionsAsBundle(v)
                    : null;

            UserHandle user = item == null ? null : item.user;

            // Prepare intent
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (v != null) {
                intent.setSourceBounds(getViewBounds(v));
            }
            try {
                boolean isShortcut = Utilities.ATLEAST_MARSHMALLOW
                        && (item instanceof ShortcutInfo)
                        && (item.itemType == Favorites.ITEM_TYPE_SHORTCUT
                        || item.itemType == Favorites.ITEM_TYPE_DEEP_SHORTCUT)
                        && !((ShortcutInfo) item).isPromise();
                if (isShortcut) {
                    // Shortcuts need some special checks due to legacy reasons.
                    startShortcutIntentSafely(intent, optsBundle, item);
                } else if (user == null || user.equals(Process.myUserHandle())) {
                    // Could be launching some bookkeeping activity
                    startActivity(intent, optsBundle);
                } else {
                    LauncherAppsCompat.getInstance(this).startActivityForProfile(
                            intent.getComponent(), user, intent.getSourceBounds(), optsBundle);
                }
                getUserEventDispatcher().logAppLaunch(v, intent);
                return true;
            } catch (ActivityNotFoundException|SecurityException e) {
                Toast.makeText(this, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Unable to launch. tag=" + item + " intent=" + intent, e);
            }
            return false;
        } else {
            startActivityWithAuth(v, intent, item);
        }
        return false;
    }

    private void startShortcutIntentSafely(Intent intent, Bundle optsBundle, ItemInfo info) {
        try {
            StrictMode.VmPolicy oldPolicy = StrictMode.getVmPolicy();
            try {
                // Temporarily disable deathPenalty on all default checks. For eg, shortcuts
                // containing file Uri's would cause a crash as penaltyDeathOnFileUriExposure
                // is enabled by default on NYC.
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                        .penaltyLog().build());

                if (info.itemType == LauncherSettings.Favorites.ITEM_TYPE_DEEP_SHORTCUT) {
                    String id = ((ShortcutInfo) info).getDeepShortcutId();
                    String packageName = intent.getPackage();
                    DeepShortcutManager.getInstance(this).startShortcut(
                            packageName, id, intent.getSourceBounds(), optsBundle, info.user);
                } else {
                    // Could be launching some bookkeeping activity
                    startActivity(intent, optsBundle);
                }
            } finally {
                StrictMode.setVmPolicy(oldPolicy);
            }
        } catch (SecurityException e) {
            if (!onErrorStartingShortcut(intent, info)) {
                throw e;
            }
        }
    }

    private void startActivityWithAuth(View v, Intent intent, ItemInfo item) {
        mAuthView = v;
        mAuthIntent = intent;
        mAuthItem = item;
        Drawable appIcon = null;
        CharSequence appName;

        try {
            PackageManager pm = getBaseContext().getPackageManager();
            ApplicationInfo app = pm.getApplicationInfo(mAuthIntent.getComponent().getPackageName(), 0);
            appName = pm.getApplicationLabel(app);
            appIcon = pm.getApplicationIcon(app);
        } catch (PackageManager.NameNotFoundException e) {
            appName = "App";
        }

        BiometricsManager manager = new BiometricsManager(this).setTitle(appName.toString()).setIcon(appIcon);
        final AuthenticationCallback callback = new AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                manager.updatePrompt(R.string.app_lock_authentication_status_retry);
            }

            @Override
            public void onAuthenticationSucceeded() {
                mIsAuthorized = true;
                manager.closePrompt();
                startActivitySafely(mAuthView, mAuthIntent, mAuthItem, false);
            }

            @Override
            public void onAuthenticationFailed() {
                manager.updatePrompt(R.string.app_lock_authentication_status_unrecognized);
            }
        };
        manager.show(callback);
    }

    private CancellationSignal getCancellationSignal() {
        CancellationSignal signal = new CancellationSignal();
        signal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
            }
        });
        return signal;
    }

	public void setAuthorized(boolean isAuthorized) {
		mIsAuthorized = isAuthorized;
	}

    protected boolean onErrorStartingShortcut(Intent intent, ItemInfo info) {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mOnStartCallback != null) {
            mOnStartCallback.onActivityStart(this);
            mOnStartCallback = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WallpaperColorInfo.getInstance(this).removeOnChangeListener(this);
        mRotationListener.disable();
    }

    public <T extends BaseDraggingActivity> void setOnStartCallback(OnStartCallback<T> callback) {
        mOnStartCallback = callback;
    }

    protected void onDeviceProfileInitiated() {
        if (mDeviceProfile.isVerticalBarLayout()) {
            mRotationListener.enable();
            mDeviceProfile.updateIsSeascape(getWindowManager());
        } else {
            mRotationListener.disable();
        }
    }

    private void onDeviceRotationChanged() {
        if (mDeviceProfile.updateIsSeascape(getWindowManager())) {
            reapplyUi();
        }
    }

    protected abstract void reapplyUi();

    /**
     * Callback for listening for onStart
     */
    public interface OnStartCallback<T extends BaseDraggingActivity> {

        void onActivityStart(T activity);
    }
}
