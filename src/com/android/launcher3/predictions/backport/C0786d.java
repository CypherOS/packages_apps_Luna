package com.android.launcher3.predictions.backport;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;

public class C0786d {
    /* renamed from: a */
    public static IntentFilter m2495a(String str, String... strArr) {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : strArr) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(str, 0);
        return intentFilter;
    }

    /* renamed from: q */
    public static boolean m2496q(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo("com.google.android.googlequicksearchbox", 0).enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
