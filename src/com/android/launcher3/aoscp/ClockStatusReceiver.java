package com.android.launcher3.aoscp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.MainThreadExecutor;
import com.android.launcher3.graphics.IconNormalizer;
import com.android.launcher3.util.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;
import java.util.WeakHashMap;

public class ClockStatusReceiver extends BroadcastReceiver {
    public static final ComponentName fk = new ComponentName("com.android.deskclock", "com.android.deskclock.DeskClock");
    private final Set fl = Collections.newSetFromMap(new WeakHashMap());
    private b fm = new b();
    private final Context mContext;

    public ClockStatusReceiver(Context context) {
        this.mContext = context;
        Handler handler = new Handler(LauncherModel.getWorkerLooper());
        context.registerReceiver(this, clockPackage("com.android.deskclock", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED"), null, handler);
        handler.post(new SomeRunnable(this));
        context.registerReceiver(new ClockZone(this), new IntentFilter("android.intent.action.TIMEZONE_CHANGED"), null, new Handler(Looper.getMainLooper()));
    }
	
	public static IntentFilter clockPackage(String string, String... stringArray) {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : stringArray) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(string, 0);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        dN();
    }

    public void dM(String str) {
        TimeZone timeZone = str == null ? TimeZone.getDefault() : TimeZone.getTimeZone(str);
        for (Object dF : this.fl) {
			ClockStatus cs = (ClockStatus)dF;
            cs.dF(timeZone);
        }
    }

    public void dN() {
        new MainThreadExecutor().execute(new DensityRunnable(this, dL(this.mContext, LauncherAppState.getIDP(this.mContext).fillResIconDpi, true)));
    }

    public void dO(b bVar) {
        this.fm = bVar;
        for (Object dE : this.fl) {
			ClockStatus cs = (ClockStatus)dE; 
            cs.dE(this.fm.clone());
        }
    }

    public ClockStatus dJ(Bitmap bitmap) {
        ClockStatus aVar = new ClockStatus(bitmap, this.fm.clone());
        this.fl.add(aVar);
        return aVar;
    }

    public static Drawable dK(Context context, int i) {
        b clone = dL(context, i, false).clone();
        if (clone == null) {
            return null;
        }
        clone.dG();
        return clone.fb;
    }

    private static b dL(Context context, int i, boolean z) {
        Preconditions.assertWorkerThread();
        b bVar = new b();
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.android.deskclock", PackageManager.GET_META_DATA | PackageManager.MATCH_UNINSTALLED_PACKAGES);
            Bundle bundle = applicationInfo.metaData;
            if (bundle == null) {
                return bVar;
            }
            int i2 = bundle.getInt("com.android.launcher3.LEVEL_PER_TICK_ICON_ROUND", 0);
            if (i2 == 0) {
                return bVar;
            }
            bVar.fb = packageManager.getResourcesForApplication(applicationInfo).getDrawableForDensity(i2, i).mutate();
            bVar.fg = bundle.getInt("com.android.launcher3.HOUR_LAYER_INDEX", -1);
            bVar.fi = bundle.getInt("com.android.launcher3.MINUTE_LAYER_INDEX", -1);
            bVar.fj = bundle.getInt("com.android.launcher3.SECOND_LAYER_INDEX", -1);
            bVar.fc = bundle.getInt("com.android.launcher3.DEFAULT_HOUR", 0);
            bVar.fd = bundle.getInt("com.android.launcher3.DEFAULT_MINUTE", 0);
            bVar.fe = bundle.getInt("com.android.launcher3.DEFAULT_SECOND", 0);
            if (z) {
                bVar.scale = IconNormalizer.getInstance(context).getScale(bVar.fb, null, null, null);
            }
            LayerDrawable dI = bVar.dI();
            int numberOfLayers = dI.getNumberOfLayers();
            if (bVar.fg < 0 || bVar.fg >= numberOfLayers) {
                bVar.fg = -1;
            }
            if (bVar.fi < 0 || bVar.fi >= numberOfLayers) {
                bVar.fi = -1;
            }
            if (bVar.fj < 0 || bVar.fj >= numberOfLayers) {
                bVar.fj = -1;
                return bVar;
            }
            dI.setDrawable(bVar.fj, null);
            bVar.fj = -1;
            return bVar;
        } catch (Exception e) {
            bVar.fb = null;
        }
		return bVar;
    }
}
