package com.android.launcher3.dynamicui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import com.android.launcher3.IconProvider;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.aoscp.ClockStatusReceiver;
import java.util.Calendar;

public class DynamicIconProvider extends IconProvider {
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(this);
    private final Context mContext;
    private final PackageManager mPackageManager;

    public DynamicIconProvider(Context context) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        context.registerReceiver(mBroadcastReceiver, intentFilter, null, new Handler(LauncherModel.getWorkerLooper()));
        mContext = context;
        mPackageManager = context.getPackageManager();
    }

    public Drawable getIcon(LauncherActivityInfo launcherActivityInfo, int iconDpi, boolean icon) {
        Drawable drawable = null;
        String str = launcherActivityInfo.getApplicationInfo().packageName;
        if (isCalendar(str)) {
            try {
                Bundle bundle = this.mPackageManager.getActivityInfo(launcherActivityInfo.getComponentName(), PackageManager.GET_META_DATA | PackageManager.MATCH_UNINSTALLED_PACKAGES).metaData;
                Resources resourcesForApplication = mPackageManager.getResourcesForApplication(str);
                int shape = getCorrectShape(bundle, resourcesForApplication);
                if (shape != 0) {
                    drawable = resourcesForApplication.getDrawableForDensity(shape, iconDpi);
                }
            } catch (NameNotFoundException e) {
            }
        } else if (!icon && ClockStatusReceiver.fk.equals(launcherActivityInfo.getComponentName()) && Process.myUserHandle().equals(launcherActivityInfo.getUser()) && mContext.getResources().getString(R.string.drawable_factory_class).equals(DynamicDrawableFactory.class.getName())) {
            drawable = ClockStatusReceiver.dK(mContext, iconDpi);
        }
        return drawable == null ? super.getIcon(launcherActivityInfo, iconDpi, icon) : drawable;
    }

    public String getIconSystemState(String str) {
        if (isCalendar(str)) {
            return mSystemState + " " + dayOfMonth();
        }
        return mSystemState;
    }

    private boolean isCalendar(String str) {
        return "com.google.android.calendar".equals(str);
    }

    private int getCorrectShape(Bundle bundle, Resources resources) {
        if (bundle == null) {
            return 0;
        }
        int roundIcons = bundle.getInt("com.google.android.calendar.dynamic_icons_nexus_round", 0);
        if (roundIcons == 0) {
            return 0;
        }
        try {
            return resources.obtainTypedArray(roundIcons).getResourceId(dayOfMonth(), 0);
        } catch (NotFoundException e) {
            return 0;
        }
    }

    private int dayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1;
    }
}
