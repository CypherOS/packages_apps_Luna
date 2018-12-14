package com.google.android.apps.nexuslauncher.reflection;

import android.content.SharedPreferences;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0791d;
import com.google.research.reflection.predictor.C0968k.C0967a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.e */
public class C0831e {
    static final String PREDICTION_APP_SEPARATOR = ";";
    /* renamed from: o */
    final SharedPreferences f208o;

    public C0831e(SharedPreferences sharedPreferences) {
        this.f208o = sharedPreferences;
    }

    /* renamed from: c */
    public final Map<String, Integer> mo8507c(String str) {
        String[] split = this.f208o.getString(C0791d.m2561d(str).f107bb, BuildConfig.FLAVOR).split(PREDICTION_APP_SEPARATOR);
        HashMap hashMap = new HashMap();
        int i = 0;
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                int i2 = i + 1;
                hashMap.put(str2, Integer.valueOf(i));
                i = i2;
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    public static String m2643b(List<C0967a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0967a c0967a : list) {
            sb.append(c0967a.f363id);
            sb.append(PREDICTION_APP_SEPARATOR);
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static String m2644c(List<C0967a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0967a c0967a : list) {
            Matcher matcher = C0832f.f209ai.matcher(c0967a.f363id);
            if (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(4);
                if (sb.length() > 0) {
                    sb.append(PREDICTION_APP_SEPARATOR);
                }
                sb.append(group);
                sb.append("/");
                sb.append(group2);
                if (group3 != null) {
                    sb.append('#');
                    sb.append(group3);
                }
            }
        }
        return sb.toString();
    }
}
