package com.google.android.apps.nexuslauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.graphics.ColorUtils;
import android.view.Menu;
import android.view.View;

import com.android.launcher3.AppInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherCallbacks;
import com.android.launcher3.LauncherExterns;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.graphics.DrawableFactory;
import com.android.launcher3.util.ComponentKeyMapper;
import com.android.launcher3.util.Themes;

import com.google.android.apps.nexuslauncher.search.ItemInfoUpdateReceiver;
import com.google.android.apps.nexuslauncher.smartspace.SmartspaceView;
import com.google.android.apps.nexuslauncher.smartspace.SmartspaceController;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class NexusLauncher {

    private final Launcher mLauncher;
    public final LauncherCallbacks mCallbacks;
    private final LauncherExterns mLauncherExterns;
    private boolean mStarted;
    private final Bundle mUiInformation = new Bundle();
    private ItemInfoUpdateReceiver mItemInfoUpdateReceiver;

    public NexusLauncher(NexusLauncherActivity activity) {
        mLauncher = activity;
        mLauncherExterns = activity;
        mCallbacks = new NexusLauncherCallbacks();
        mLauncherExterns.setLauncherCallbacks(mCallbacks);
    }

    class NexusLauncherCallbacks implements LauncherCallbacks, SharedPreferences.OnSharedPreferenceChangeListener, WallpaperColorInfo.OnChangeListener {
        private SmartspaceView mSmartspace;

        private ItemInfoUpdateReceiver getUpdateReceiver() {
            if (mItemInfoUpdateReceiver == null) {
                mItemInfoUpdateReceiver = new ItemInfoUpdateReceiver(mLauncher, mCallbacks);
            }
            return mItemInfoUpdateReceiver;
        }

        public void bindAllApplications(final ArrayList<AppInfo> list) {
            getUpdateReceiver().di();
        }

        public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            SmartspaceController.get(mLauncher).cX(s, printWriter);
        }

        public void finishBindingItems(final boolean b) {
        }

        public List<ComponentKeyMapper<AppInfo>> getPredictedApps() {
            return ((CustomAppPredictor) mLauncher.getUserEventDispatcher()).getPredictions();
        }

        public boolean handleBackPressed() {
            return false;
        }

        public boolean hasCustomContentToLeft() {
            return false;
        }

        public boolean hasSettings() {
            return true;
        }
		
		public void onHomeIntent(boolean z) {
		}

        public void onActivityResult(final int n, final int n2, final Intent intent) {
        }

        public void onCreate(final Bundle bundle) {
            SharedPreferences prefs = Utilities.getPrefs(mLauncher);

            prefs.registerOnSharedPreferenceChangeListener(this);

            SmartspaceController.get(mLauncher).cW();
            mSmartspace = mLauncher.findViewById(R.id.search_container_workspace);

            mUiInformation.putInt("system_ui_visibility", mLauncher.getWindow().getDecorView().getSystemUiVisibility());
            WallpaperColorInfo instance = WallpaperColorInfo.getInstance(mLauncher);
            instance.addOnChangeListener(this);
            onExtractedColorsChanged(instance);

            getUpdateReceiver().onCreate();
        }

        public void onDestroy() {
            Utilities.getPrefs(mLauncher).unregisterOnSharedPreferenceChangeListener(this);
            getUpdateReceiver().onDestroy();
        }
		
		public void onDetachedFromWindow() {
        }

        public void onInteractionBegin() {
        }

        public void onInteractionEnd() {
        }

        public void onLauncherProviderChange() {
        }

        public void onNewIntent(final Intent intent) {
        }

        public void onPause() {
            if (mSmartspace != null) {
                mSmartspace.onPause();
            }
        }

        public void onPostCreate(final Bundle bundle) {
        }

        public boolean onPrepareOptionsMenu(final Menu menu) {
            return false;
        }

        public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        }

        public void onResume() {
            if (mSmartspace != null) {
                mSmartspace.onResume();
            }
        }

        public void onSaveInstanceState(final Bundle bundle) {
        }

        public void onStart() {
            mStarted = true;
        }

        public void onStop() {
            mStarted = false;
        }

        public void onTrimMemory(int n) {
        }

        public void onWindowFocusChanged(boolean hasFocus) {
        }

        public void onWorkspaceLockedChanged() {
        }

        public void populateCustomContentContainer() {
        }

        public void preOnCreate() {
            DrawableFactory.get(mLauncher);
        }

        public void preOnResume() {
        }

        public boolean shouldMoveToDefaultScreenOnHomeIntent() {
            return true;
        }

        public boolean startSearch(String s, boolean b, Bundle bundle) {
            View gIcon = mLauncher.findViewById(R.id.g_icon);
            while (gIcon != null && !gIcon.isClickable()) {
                if (gIcon.getParent() instanceof View) {
                    gIcon = (View)gIcon.getParent();
                } else {
                    gIcon = null;
                }
            }
            if (gIcon != null && gIcon.performClick()) {
                return true;
            }
            return false;
        }

        @Override
        public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
            int alpha = mLauncher.getResources().getInteger(R.integer.extracted_color_gradient_alpha);

            mUiInformation.putInt("background_color_hint", primaryColor(wallpaperColorInfo, mLauncher, alpha));
            mUiInformation.putInt("background_secondary_color_hint", secondaryColor(wallpaperColorInfo, mLauncher, alpha));
            mUiInformation.putBoolean("is_background_dark", Themes.getAttrBoolean(mLauncher, R.attr.isMainColorDark));
        }
    }

    public static int primaryColor(WallpaperColorInfo wallpaperColorInfo, Context context, int alpha) {
        return compositeAllApps(ColorUtils.setAlphaComponent(wallpaperColorInfo.getMainColor(), alpha), context);
    }

    public static int secondaryColor(WallpaperColorInfo wallpaperColorInfo, Context context, int alpha) {
        return compositeAllApps(ColorUtils.setAlphaComponent(wallpaperColorInfo.getSecondaryColor(), alpha), context);
    }

    private static int compositeAllApps(int color, Context context) {
        return ColorUtils.compositeColors(Themes.getAttrColor(context, R.attr.allAppsScrimColor), color);
    }
}