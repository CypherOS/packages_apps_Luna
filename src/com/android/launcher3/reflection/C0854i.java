package com.android.launcher3.reflection;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.i */
public class C0854i {
    /* renamed from: Bn */
    public static final Pattern f1130Bn = Pattern.compile("^([^/]+)/([^#/]+)(#(\\d+))?$");
    /* renamed from: Bo */
    public static final List f1131Bo = Collections.unmodifiableList(Arrays.asList(new String[]{"reflection.engine", "reflection.engine.background", "reflection.events", "model.properties.xml", "reflection_multi_process.xml", "client_actions"}));

    /* renamed from: l */
    public static SharedPreferences m2673l(Context context) {
        return context.getSharedPreferences("reflection.private.properties", 0);
    }

    /* renamed from: a */
    public static String m2671a(ComponentName componentName) {
        return componentName.flattenToString();
    }

    /* renamed from: a */
    public static String m2672a(String str, String str2) {
        return String.format("%s%s/%s", new Object[]{"_", str, str2});
    }
}
