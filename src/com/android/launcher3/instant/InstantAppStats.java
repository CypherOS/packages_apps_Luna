package com.android.launcher3.instant;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;

public class InstantAppStats {

    static InstantAppStats mStats;
    UsageStatsManager mUsageStats;

    InstantAppStats(Context context) {
        mUsageStats = (UsageStatsManager) context.getSystemService("usagestats");
    }

    public boolean isNewStats(UsageStats usageStats, long interval, long current) {
        return usageStats.getLastTimeUsed() >= interval && usageStats.getLastTimeUsed() <= current;
    }
}
