package com.android.launcher3.predictions.backport;

import android.app.usage.UsageStatsManager;
import android.content.Context;

/* renamed from: com.google.android.apps.nexuslauncher.d.c */
public class C0782c {
    /* renamed from: zC */
    static C0782c f1040zC;
    /* renamed from: zD */
    final UsageStatsManager f1041zD;

    C0782c(Context context) {
        this.f1041zD = (UsageStatsManager) context.getSystemService("usagestats");
    }
}
