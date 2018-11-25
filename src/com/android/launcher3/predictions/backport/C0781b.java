package com.android.launcher3.predictions.backport;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.IconCache;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.predictions.backport.C0799i;
import com.android.launcher3.util.InstantAppResolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class C0781b implements Callback {
    /* renamed from: zz */
    private static C0781b f1037zz;
    private final Context mContext;
    private final Handler mHandler = new Handler(this);
    private final InstantAppResolver mInstantAppResolver;
    public final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);
    /* renamed from: zA */
    private final Map f1038zA = new HashMap();
    /* renamed from: zB */
    public C0799i f1039zB;

    public C0781b(Context context) {
        this.mContext = context;
        this.mInstantAppResolver = InstantAppResolver.newInstance(context);
    }

    /* renamed from: f */
    public static C0781b m2482f(Context context) {
        if (f1037zz == null) {
            f1037zz = new C0781b(context.getApplicationContext());
        }
        return f1037zz;
    }

    /* renamed from: h */
    public final C1807a mo3135h(String str) {
        return (C1807a) this.f1038zA.get(str);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            List<String> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (m2484i(str) != null) {
                    arrayList.add(m2484i(str));
                }
            }
            Message.obtain(this.mHandler, 2, arrayList).sendToTarget();
        } else if (message.what == 2) {
            List<C1807a> list2 = (List) message.obj;
            this.f1038zA.clear();
            for (C1807a c1807a : list2) {
                this.f1038zA.put(c1807a.getTargetComponent().getPackageName(), c1807a);
            }
            if (this.f1039zB != null) {
                this.f1039zB.mo3162cG();
            }
        }
        return false;
    }

    /* renamed from: g */
    public static String m2483g(Context context) {
        String str;
        long currentTimeMillis;
        List<ApplicationInfo> instantApps = InstantAppResolver.newInstance(context).getInstantApps();
        if (C0782c.f1040zC == null) {
            C0782c.f1040zC = new C0782c(context.getApplicationContext());
        }
        C0782c c0782c = C0782c.f1040zC;
        String str2 = null;
        if (instantApps == null || instantApps.isEmpty()) {
            str = null;
        } else {
            currentTimeMillis = System.currentTimeMillis();
            Map queryAndAggregateUsageStats = c0782c.f1041zD.queryAndAggregateUsageStats(currentTimeMillis - TimeUnit.HOURS.toMillis(4), currentTimeMillis);
            str = null;
            UsageStats usageStats = null;
            for (ApplicationInfo applicationInfo : instantApps) {
                UsageStats usageStats2 = (UsageStats) queryAndAggregateUsageStats.get(applicationInfo.packageName);
                if (usageStats2 != null) {
                    Object obj = (usageStats != null && usageStats2.getLastTimeUsed() <= usageStats.getLastTimeUsed()) ? null : 1;
                    if (obj != null) {
                        str = applicationInfo.packageName;
                        usageStats = usageStats2;
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (!(instantApps == null || instantApps.isEmpty())) {
            currentTimeMillis = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(4);
            Map queryAndAggregateUsageStats2 = c0782c.f1041zD.queryAndAggregateUsageStats(currentTimeMillis - TimeUnit.DAYS.toMillis(2), currentTimeMillis);
            UsageStats usageStats3 = null;
            for (ApplicationInfo applicationInfo2 : instantApps) {
                UsageStats usageStats4 = (UsageStats) queryAndAggregateUsageStats2.get(applicationInfo2.packageName);
                if (usageStats4 != null) {
                    Object obj2 = (usageStats3 != null && usageStats4.getTotalTimeInForeground() <= usageStats3.getTotalTimeInForeground()) ? null : 1;
                    if (obj2 != null) {
                        str2 = applicationInfo2.packageName;
                        usageStats3 = usageStats4;
                    }
                }
            }
        }
        return str2;
    }

    /* renamed from: i */
    private C1807a m2484i(String str) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            if (!this.mInstantAppResolver.isInstantApp(packageManager.getApplicationInfo(str, 0))) {
                return null;
            }
            String str2 = null;
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setPackage(str), 8388736)) {
                if (resolveInfo.activityInfo.metaData != null && resolveInfo.activityInfo.metaData.containsKey("default-url")) {
                    str2 = resolveInfo.activityInfo.metaData.getString("default-url");
                }
            }
            if (str2 == null) {
                StringBuilder stringBuilder = new StringBuilder("no default-url available for pkg ");
                stringBuilder.append(str);
                Log.w("InstantApps", stringBuilder.toString());
                return null;
            }
            C1807a c1807a = new C1807a(new Intent("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(str2)), str);
            IconCache iconCache = LauncherAppState.getInstance(this.mContext).mIconCache;
            iconCache.getTitleAndIcon(c1807a, false);
            if (c1807a.iconBitmap == null || iconCache.isDefaultIcon(c1807a.iconBitmap, c1807a.user)) {
                return null;
            }
            return c1807a;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
