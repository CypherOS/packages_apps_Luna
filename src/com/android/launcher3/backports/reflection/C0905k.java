package com.android.launcher3.backports.reflection;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.k */
public class C0905k {
    /* renamed from: Fu */
    public static final Pattern f1369Fu = Pattern.compile("^([^/]+)/([^#/]+)(#(\\d+))?$");
    /* renamed from: Fv */
    public static final List f1370Fv = Collections.unmodifiableList(Arrays.asList(new String[]{"reflection.engine", "reflection.engine.background", "model.properties.xml", "reflection_multi_process.xml", "client_actions"}));
    /* renamed from: Fw */
    public static final long f1371Fw = TimeUnit.SECONDS.toMillis(1);
    /* renamed from: Fx */
    public static final long f1372Fx = TimeUnit.SECONDS.toMillis(3);

    /* renamed from: m */
    public static SharedPreferences m790m(Context context) {
        return context.getSharedPreferences("reflection.private.properties", 0);
    }

    /* renamed from: a */
    public static String m788a(ComponentName componentName) {
        return componentName.flattenToString();
    }

    /* renamed from: a */
    public static String m789a(String str, String str2) {
        return String.format("%s%s/%s", new Object[]{"_", str, str2});
    }
}
