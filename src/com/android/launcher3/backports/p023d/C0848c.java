package com.android.launcher3.backports.p023d;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;

/* renamed from: com.google.android.apps.nexuslauncher.d.c */
public class C0848c {
    /* renamed from: DD */
    static C0848c f1268DD;
    /* renamed from: DE */
    final UsageStatsManager f1269DE;

    C0848c(Context context) {
        this.f1269DE = (UsageStatsManager) context.getSystemService("usagestats");
    }

    /* renamed from: a */
    final boolean mo3456a(UsageStats usageStats, long j, long j2) {
        if (usageStats.getLastTimeUsed() < j || usageStats.getLastTimeUsed() > j2) {
            return false;
        }
        return true;
    }
}
