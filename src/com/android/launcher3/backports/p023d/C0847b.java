package com.android.launcher3.backports.p023d;

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
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.backports.C0861l;
import com.android.launcher3.util.InstantAppResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.apps.nexuslauncher.d.b */
public class C0847b implements Callback {
    /* renamed from: DB */
    private static boolean f1263DB = true;
    /* renamed from: DC */
    private static boolean f1264DC = false;
    /* renamed from: Dy */
    private static C0847b f1265Dy;
    /* renamed from: DA */
    public C0861l f1266DA;
    /* renamed from: Dz */
    private final Map f1267Dz = new HashMap();
    private final Context mContext;
    private final Handler mHandler = new Handler(this);
    private final InstantAppResolver mInstantAppResolver;
    public final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);

    public C0847b(Context context) {
        this.mContext = context;
        this.mInstantAppResolver = InstantAppResolver.newInstance(context);
    }

    /* renamed from: h */
    public static C0847b m679h(Context context) {
        if (f1265Dy == null) {
            f1265Dy = new C0847b(context.getApplicationContext());
        }
        return f1265Dy;
    }

    /* renamed from: i */
    public final C1909a mo3455i(String str) {
        return (C1909a) this.f1267Dz.get(str);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            List<String> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            List arrayList = new ArrayList();
            for (String str : list) {
                if (m681j(str) != null) {
                    arrayList.add(m681j(str));
                }
            }
            Message.obtain(this.mHandler, 2, arrayList).sendToTarget();
        } else if (message.what == 2) {
            List<C1909a> list2 = (List) message.obj;
            this.f1267Dz.clear();
            for (C1909a c1909a : list2) {
                this.f1267Dz.put(c1909a.getTargetComponent().getPackageName(), c1909a);
            }
            if (this.f1266DA != null) {
                this.f1266DA.onUpdateUI();
            }
        }
        return false;
    }

    /* renamed from: i */
    public static String m680i(Context context) {
        Map queryAndAggregateUsageStats;
        Object obj;
        List<ApplicationInfo> instantApps = InstantAppResolver.newInstance(context).getInstantApps();
        if (C0848c.f1268DD == null) {
            C0848c.f1268DD = new C0848c(context.getApplicationContext());
        }
        C0848c c0848c = C0848c.f1268DD;
        String str = "";
        if (f1263DB) {
            if (instantApps == null || instantApps.isEmpty()) {
                str = null;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long toMillis = currentTimeMillis - TimeUnit.HOURS.toMillis(4);
                queryAndAggregateUsageStats = c0848c.f1269DE.queryAndAggregateUsageStats(toMillis, currentTimeMillis);
                Iterator it = instantApps.iterator();
                UsageStats usageStats = null;
                String str2 = null;
                while (it.hasNext()) {
                    Iterator it2;
                    UsageStats usageStats2;
                    ApplicationInfo applicationInfo = (ApplicationInfo) it.next();
                    UsageStats usageStats3 = (UsageStats) queryAndAggregateUsageStats.get(applicationInfo.packageName);
                    if (usageStats3 != null) {
                        ApplicationInfo applicationInfo2 = applicationInfo;
                        UsageStats usageStats4 = usageStats3;
                        it2 = it;
                        usageStats2 = usageStats;
                        if (c0848c.mo3456a(usageStats3, toMillis, currentTimeMillis)) {
                            obj = (usageStats2 != null && usageStats4.getLastTimeUsed() <= usageStats2.getLastTimeUsed()) ? null : 1;
                            if (obj != null) {
                                str2 = applicationInfo2.packageName;
                                usageStats = usageStats4;
                                it = it2;
                            }
                        }
                    } else {
                        it2 = it;
                        usageStats2 = usageStats;
                    }
                    usageStats = usageStats2;
                    it = it2;
                }
                str = str2;
            }
        }
        if (!TextUtils.isEmpty(str) || !f1264DC) {
            return str;
        }
        if (instantApps == null || instantApps.isEmpty()) {
            return null;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        long toMillis2 = currentTimeMillis2 - TimeUnit.DAYS.toMillis(2);
        queryAndAggregateUsageStats = c0848c.f1269DE.queryAndAggregateUsageStats(toMillis2, currentTimeMillis2);
        UsageStats usageStats5 = null;
        String str3 = null;
        for (ApplicationInfo applicationInfo3 : instantApps) {
            UsageStats usageStats6 = (UsageStats) queryAndAggregateUsageStats.get(applicationInfo3.packageName);
            if (usageStats6 != null && c0848c.mo3456a(usageStats6, toMillis2, currentTimeMillis2)) {
                obj = (usageStats5 != null && usageStats6.getTotalTimeInForeground() <= usageStats5.getTotalTimeInForeground()) ? null : 1;
                if (obj != null) {
                    str3 = applicationInfo3.packageName;
                    usageStats5 = usageStats6;
                }
            }
        }
        return str3;
    }

    private C1909a m681j(String str) {
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
            C1909a c1909a = new C1909a(new Intent("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(str2)), str);
            IconCache iconCache = LauncherAppState.getInstance(this.mContext).mIconCache;
            iconCache.getTitleAndIcon(c1909a, false);
            if (c1909a.iconBitmap == null || iconCache.isDefaultIcon(c1909a.iconBitmap, c1909a.user)) {
                return null;
            }
            return c1909a;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
