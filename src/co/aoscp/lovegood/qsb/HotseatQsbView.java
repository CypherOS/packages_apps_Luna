/*
 * Copyright (C) 2018 CypherOS
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
package co.aoscp.lovegood.qsb;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Process;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;

import co.aoscp.lovegood.LunaLauncher;
import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.qsb.configs.ConfigurationBuilder;
import co.aoscp.lovegood.qsb.configs.QsbConfiguration;

import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.LauncherAppsCompat;

import java.util.List;

public class HotseatQsbView extends BaseQsbView {

    private BroadcastReceiver mWallpaperChanged;
    private BroadcastReceiver mSearchReceiver;

    private QsbConfiguration mQsbConfig;

    public HotseatQsbView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HotseatQsbView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mWallpaperChanged = new BroadcastReceiver() {
			@Override
            public void onReceive(Context context, Intent intent) {
                setColors();
            }
        };
		mSearchReceiver = new BroadcastReceiver() {
			@Override
            public void onReceive(Context context, Intent intent) {
                if (getResultCode() == 0) {
					fallbackSearch("com.google.android.googlequicksearchbox.TEXT_ASSIST");
				} else {
					mLauncher.getQsbController().playQsbAnimation();
				}
            }
        };
        mQsbConfig = QsbConfiguration.getInstance(context);
        setOnClickListener(this);
    }

    public void onAttachedToWindow() {
        setSuperGAlpha();
        super.onAttachedToWindow();
        getContext().registerReceiver(mWallpaperChanged, new IntentFilter("android.intent.action.WALLPAPER_CHANGED"));
        setMicRipple();
        setOnFocusChangeListener(mLauncher.mFocusHandler);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mWallpaperChanged);
    }

    public Drawable getIcon() {
        return getIcon();
    }

    public void setSuperGAlpha() {
        View gIcon = findViewById(R.id.g_icon);
        if (gIcon != null) {
            gIcon.setAlpha(1.0f);
        }
    }

	@Override
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility != 0) {
            setSuperGAlpha();
        }
    }

    public void setColors() {
		removeAllViews();
        mUseTwoBubbles = mQsbConfig.useTwoBubbles();
        View.inflate(new ContextThemeWrapper(getContext(), R.style.HotseatQsbTheme), R.layout.qsb_hotseat_content, this);
        setColor(getResources().getColor(0xCCFFFFFF));
        setColorAlpha(ColorUtils.setAlphaComponent(mColor, mQsbConfig.getMicOpacity()));
    }

	@Override
    public int getWidth(int width) {
        CellLayout view = mLauncher.getHotseat().getLayout();
        return (width - view.getPaddingLeft()) - view.getPaddingRight();
    }

    public void startSearch() {
        ConfigurationBuilder config = new ConfigurationBuilder(this, false);
        if (mLauncher.getLauncherCallbacks().startSearch(config.build(), false, config.getExtras())) {
            SharedPreferences devicePrefs = Utilities.getDevicePrefs(getContext());
            devicePrefs.edit().putInt("key_hotseat_qsb_tap_count", devicePrefs.getInt("key_hotseat_qsb_tap_count", 0) + 1).apply();
            mLauncher.getQsbController().playQsbAnimation();
            return;
        }
        getContext().sendOrderedBroadcast(getSearchIntent(), null, mSearchReceiver, null, 0, null, null);
    }

    public void noGoogleAppSearch() {
        final Intent searchIntent = new Intent("com.google.android.apps.searchlite.WIDGET_ACTION")
		        .setComponent(ComponentName.unflattenFromString("com.google.android.apps.searchlite/.ui.SearchActivity"))
				.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
				.putExtra("showKeyboard", true)
				.putExtra("contentType", 12);
        final Context context = getContext();
        if (context.getPackageManager().queryIntentActivities(searchIntent, 0).isEmpty()) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://google.com")));
                mLauncher.openQsb();
            } catch (ActivityNotFoundException e) {
                try {
                    getContext().getPackageManager().getPackageInfo("com.google.android.googlequicksearchbox", 0);
                    LauncherAppsCompat.getInstance(getContext())
					        .showAppDetailsForProfile(new ComponentName("com.google.android.googlequicksearchbox", ".SearchActivity"), Process.myUserHandle());
                } catch (NameNotFoundException ex) {
                }
            }
            return;
        }
        mLauncher.openQsb().addListener(new AnimatorListenerAdapter(this) {
            public void onAnimationEnd(Animator animation) {
                context.startActivity(searchIntent);
            }
        });
    }

    public boolean hasDoodle() {
        return false;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setTranslationY((float) (-getHotseatPadding(mLauncher)));
    }

    public void setInsets(Rect rect) {
        super.setInsets(rect);
        setVisibility(mLauncher.getDeviceProfile().isVerticalBarLayout() ? View.GONE : View.VISIBLE);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this) {
            startSearch("", f421Di);
        }
    }

    public final Intent createSettingsIntent() {
        Intent addFlags = new Intent("com.google.android.apps.gsa.nowoverlayservice.PIXEL_DOODLE_QSB_SETTINGS")
		        .setPackage(LunaLauncherCallbacks.SEARCH_PACKAGE).addFlags(268435456);
        int i = 0;
        List queryBroadcastReceivers = getContext().getPackageManager().queryBroadcastReceivers(addFlags, 0);
        if (!(queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty())) {
            i = 1;
        }
        if (i != 0) {
            return addFlags;
        }
        return null;
    }

    public final Intent getSearchIntent() {
        int[] array = new int[2];
        getLocationInWindow(array);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        rect.offset(array[0], array[1]);
        rect.inset(getPaddingLeft(), getPaddingTop());
        return ConfigurationBuilder.getSearchIntent(rect, findViewById(R.id.g_icon), mMicIconView);
    }

    public static int getHotseatPadding(Launcher launcher) {
        Resources resources = launcher.getResources();
        DeviceProfile profile = launcher.getDeviceProfile();
        Rect rect = profile.getInsets();
        Rect hotseatLayoutPadding = profile.getHotseatLayoutPadding();
        int barSizePx = profile.hotseatBarSizePx;
        int bottomRect = rect.bottom;
        float rectValue = ((float) bottomRect) * 0.67f;
        return Math.round((((((((float) (barSizePx + bottomRect)) - rectValue) - ((((float) (((barSizePx + bottomRect) - hotseatLayoutPadding.top) - hotseatLayoutPadding.bottom)) + (((float) profile.iconSizePx) * 0.92f)) / 2.0f)) - resources.getDimension(R.dimen.qsb_widget_height)) - ((float) profile.verticalDragHandleSizePx)) / 2.0f) + rectValue);
    }

    public String getClipboardText() {
        return null;
    }
}