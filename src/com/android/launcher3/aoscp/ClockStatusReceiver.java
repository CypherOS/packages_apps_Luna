package com.android.launcher3.aoscp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.graphics.IconNormalizer;

import java.util.Calendar;

public class ClockStatusReceiver extends BroadcastReceiver {
    private static final Object LOCK;
    public static final ComponentName mComponentName;
    private static ClockStatusReceiver mClockStatusReceiver;
    private LayerDrawable mLayerDrawable;
    private int mHour;
    private int mMinute;
    private int mSecond;
    private final Context mContext;
    private float mScale;

    static {
        LOCK = new Object();
        mComponentName = new ComponentName("com.android.deskclock", "com.android.deskclock.DeskClock");
    }

    private ClockStatusReceiver(final Context mContext) {
        mContext = mContext;
        final Handler handler = new Handler(LauncherModel.getWorkerLooper());
        mContext.registerReceiver(this, clockState("com.android.deskclock", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED"), null, handler);
        handler.post(new SomeRunnable(this));
    }

    public static IntentFilter clockState(final String s, final String... array) {
        final IntentFilter intentFilter = new IntentFilter();
        for (int length = array.length, i = 0; i < length; ++i) {
            intentFilter.addAction(array[i]);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(s, 0);
        return intentFilter;
    }

    private static Drawable cL(final Context context, final Resources resources, final int dpi) {
        if (dpi != 0) {
            return resources.getDrawableForDensity(dpi, LauncherAppState.getInstance(context).getInvariantDeviceProfile().fillResIconDpi);
        }
        return null;
    }

    private void initAnimatedIcon() {
        try {
            final Bundle metaData = mContext.getPackageManager().getApplicationInfo("com.android.deskclock", PackageManager.GET_META_DATA | PackageManager.MATCH_UNINSTALLED_PACKAGES).metaData;
            if (metaData != null) {
                final Resources resourcesForApplication = mContext.getPackageManager().getResourcesForApplication("com.android.deskclock");
                final Drawable cl = cL(mContext, resourcesForApplication, metaData.getInt("com.android.launcher3.LEVEL_PER_TICK_ICON", 0));
                mLayerDrawable = (LayerDrawable)cl;
                mHour = metaData.getInt("com.android.launcher3.HOUR_LAYER_INDEX", -1);
                mMinute = metaData.getInt("com.android.launcher3.MINUTE_LAYER_INDEX", -1);
                mSecond = metaData.getInt("com.android.launcher3.SECOND_LAYER_INDEX", -1);
                if (mLayerDrawable != null) {
                    mScale = IconNormalizer.getInstance(mContext).getScale(mLayerDrawable, null, null, null);
                }
            }
        }
        catch (Exception ex) {}
    }

    public static ClockStatusReceiver getInstance(final Context context) {
        synchronized (ClockStatusReceiver.LOCK) {
            if (mClockStatusReceiver == null) {
                mClockStatusReceiver = new ClockStatusReceiver(context.getApplicationContext());
            }
            return mClockStatusReceiver;
        }
    }

    public Drawable setLayers(final Calendar calendar) {
        final int def = -1;
        if (mHour != def) {
            mLayerDrawable.getDrawable(mHour).setLevel(calendar.get(Calendar.HOUR) * 60 + calendar.get(Calendar.MINUTE));
        }
        if (mMinute != def) {
            mLayerDrawable.getDrawable(mMinute).setLevel(calendar.get(Calendar.HOUR) * 60 + calendar.get(Calendar.MINUTE));
        }
        if (mSecond != def) {
            mLayerDrawable.getDrawable(mSecond).setLevel(calendar.get(Calendar.SECOND) * 10 + calendar.get(Calendar.MILLISECOND) / 100);
        }
        return mLayerDrawable;
    }

    public float getScale() {
        return mScale;
    }

    public boolean getLayerDrawable() {
        return mLayerDrawable != null;
    }

    public void onReceive(final Context context, final Intent intent) {
        initAnimatedIcon();
    }

    final class SomeRunnable implements Runnable
    {
        final ClockStatusReceiver mClockStatusReceiver;

        SomeRunnable(final ClockStatusReceiver mClockStatusReceiver) {
            mClockStatusReceiver = mClockStatusReceiver;
        }

        public void run() {
            mClockStatusReceiver.initAnimatedIcon();
        }
    }
}