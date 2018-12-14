package com.android.launcher3.instant;

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
import com.android.launcher3.PredictionUiCallback;
import com.android.launcher3.util.InstantAppResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InstantAppController implements Callback {

    private static InstantAppController mController = null;
    private static boolean mAdvTimeCalculation = true;
    public final Map<String, InstantAppInfo> mInstantAppMapper = new HashMap();
    public PredictionUiCallback mUiCallback;
    public final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Context mContext;
    private final Handler mHandler = new Handler(this);
    private final InstantAppResolver mInstantAppResolver;

    public InstantAppController(Context context) {
        mContext = context;
        mInstantAppResolver = InstantAppResolver.newInstance(context);
    }

    public static InstantAppController getInstance(Context context) {
        if (mController == null) {
            mController = new InstantAppController(context.getApplicationContext());
        }
        return mController;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            List<String> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (getInfo(str) != null) {
                    arrayList.add(getInfo(str));
                }
            }
            Message.obtain(mHandler, 2, arrayList).sendToTarget();
        } else if (message.what == 2) {
            List<InstantAppInfo> list2 = (List) message.obj;
            mInstantAppMapper.clear();
            for (InstantAppInfo info : list2) {
                mInstantAppMapper.put(info.getTargetComponent().getPackageName(), info);
            }
            if (mUiCallback != null) {
                mUiCallback.onUpdateUI();
            }
        }
        return false;
    }

    public static String m2511c(Context context) {
        Map queryAndAggregateUsageStats;
        boolean z;
        List<ApplicationInfo> instantApps = InstantAppResolver.newInstance(context).getInstantApps();
        if (InstantAppStats.mStats == null) {
            InstantAppStats.mStats = new InstantAppStats(context.getApplicationContext());
        }
        InstantAppStats instantStats = InstantAppStats.mStats;
        String str = "";
        if (mAdvTimeCalculation) {
            if (instantApps != null) {
                if (!instantApps.isEmpty()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long millis = currentTimeMillis - TimeUnit.HOURS.toMillis(4);
                    queryAndAggregateUsageStats = instantStats.mUsageStats.queryAndAggregateUsageStats(millis, currentTimeMillis);
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
                            if (instantStats.isNewStats(usageStats3, millis, currentTimeMillis)) {
                                if (usageStats2 != null) {
                                    if (usageStats4.getLastTimeUsed() <= usageStats2.getLastTimeUsed()) {
                                        z = false;
                                        if (z) {
                                            str2 = applicationInfo2.packageName;
                                            usageStats = usageStats4;
                                            it = it2;
                                        }
                                    }
                                }
                                z = true;
                                if (z) {
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
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (instantApps != null) {
            if (!instantApps.isEmpty()) {
                long currentTimeMillis2 = System.currentTimeMillis();
                long millis2 = currentTimeMillis2 - TimeUnit.DAYS.toMillis(2);
                queryAndAggregateUsageStats = instantStats.mUsageStats.queryAndAggregateUsageStats(millis2, currentTimeMillis2);
                UsageStats usageStats5 = null;
                String str3 = null;
                for (ApplicationInfo applicationInfo3 : instantApps) {
                    UsageStats usageStats6 = (UsageStats) queryAndAggregateUsageStats.get(applicationInfo3.packageName);
                    if (usageStats6 != null && instantStats.isNewStats(usageStats6, millis2, currentTimeMillis2)) {
                        if (usageStats5 != null) {
                            if (usageStats6.getTotalTimeInForeground() <= usageStats5.getTotalTimeInForeground()) {
                                z = false;
                                if (!z) {
                                    str3 = applicationInfo3.packageName;
                                    usageStats5 = usageStats6;
                                }
                            }
                        }
                        z = true;
                        if (!z) {
                        }
                    }
                }
                return str3;
            }
        }
        return null;
    }

    private InstantAppInfo getInfo(String str) {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            if (!mInstantAppResolver.isInstantApp(packageManager.getApplicationInfo(str, 0))) {
                return null;
            }
            String str2 = null;
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setPackage(str), 8388736)) {
                if (resolveInfo.activityInfo.metaData != null && resolveInfo.activityInfo.metaData.containsKey("default-url")) {
                    str2 = resolveInfo.activityInfo.metaData.getString("default-url");
                }
            }
            if (str2 == null) {
                StringBuilder sb = new StringBuilder("no default-url available for pkg ");
                sb.append(str);
                Log.w("InstantApps", sb.toString());
                return null;
            }
            InstantAppInfo info = new InstantAppInfo(new Intent("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(str2)), str);
            IconCache iconCache = LauncherAppState.getInstance(mContext).getIconCache();
            iconCache.getTitleAndIcon(info, false);
            if (info.iconBitmap != null) {
                if (!iconCache.isDefaultIcon(info.iconBitmap, info.user)) {
                    return info;
                }
            }
            return null;
        } catch (NameNotFoundException unused) {
            return null;
        }
    }
}
