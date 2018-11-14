package com.android.launcher3.backports.p024e;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;

/* renamed from: com.google.android.apps.nexuslauncher.e.d */
public class C0852d {
    /* renamed from: a */
    public static IntentFilter m689a(String str, String... strArr) {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : strArr) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(str, 0);
        return intentFilter;
    }

    /* renamed from: q */
    public static boolean m690q(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo("com.google.android.googlequicksearchbox", 0).enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
