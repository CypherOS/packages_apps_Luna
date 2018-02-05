package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;

import com.android.launcher3.aoscp.LunaAppFilter;

public class AppFilter {

    public static AppFilter newInstance(Context context) {
        return Utilities.getOverrideObject(AppFilter.class, context, LunaAppFilter.class);
    }

    public boolean shouldShowApp(ComponentName app) {
        return true;
    }
}
