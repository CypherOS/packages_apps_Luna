package com.android.launcher3.reflection;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.android.launcher3.reflection.f */
public class C0832f {
    public static final List<String> ALL_FILES = Collections.unmodifiableList(Arrays.asList(new String[]{"reflection.engine", "reflection.engine.background", "model.properties.xml", "reflection_multi_process.xml", "client_actions"}));
    /* renamed from: ai */
    public static final Pattern f209ai = Pattern.compile("^([^/]+)/([^#/]+)(#(\\d+))?$");
    /* renamed from: aj */
    public static final long f210aj = TimeUnit.SECONDS.toMillis(1);
    /* renamed from: ak */
    public static final long f211ak = TimeUnit.SECONDS.toMillis(3);

    /* renamed from: d */
    public static SharedPreferences m2649d(Context context) {
        return context.getSharedPreferences("reflection.private.properties", 0);
    }

    /* renamed from: a */
    public static String m2647a(ComponentName componentName) {
        return componentName.flattenToString();
    }

    /* renamed from: a */
    public static String m2648a(String str, String str2) {
        return String.format("%s%s/%s", new Object[]{"_", str, str2});
    }
}
