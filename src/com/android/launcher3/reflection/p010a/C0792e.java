package com.android.launcher3.reflection.p010a;

import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.reflection.C0832f;
import com.android.launcher3.reflection.p015e.C0824c.C0825a;
import com.android.launcher3.reflection.p015e.C0824c.C0830f;
import com.android.launcher3.reflection.signal.C1214a;
import com.android.launcher3.reflection.signal.C1219f;
import com.android.launcher3.reflection.research.signal.C0970b;
import com.android.launcher3.reflection.research.signal.C0972d;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/* renamed from: com.android.launcher3.reflection.a.e */
public class C0792e {
    /* renamed from: b */
    public static boolean m2565b(ReflectionEvent reflectionEvent) {
        if (!(reflectionEvent == null || reflectionEvent.mo9266F() == null)) {
            if (reflectionEvent.mo9266F().size() >= 2) {
                if (!"GEL".equals((String) reflectionEvent.mo9266F().get(0))) {
                    return false;
                }
                String str = (String) reflectionEvent.mo9266F().get(1);
                if (!(Objects.equals(str, Integer.toString(4)) || Objects.equals(str, Integer.toString(7)) || Objects.equals(str, Integer.toString(8)))) {
                    if (!Objects.equals(str, Integer.toString(9))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static C0830f[] m2564a(Map<String, Integer> map) {
        C0830f[] c0830fArr = new C0830f[map.size()];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            C0830f c0830f = new C0830f();
            c0830f.f207id = ((Integer) entry.getValue()).intValue();
            c0830f.name = (String) entry.getKey();
            int i2 = i + 1;
            c0830fArr[i] = c0830f;
            i = i2;
        }
        return c0830fArr;
    }

    /* renamed from: b */
    public static <T extends Number> C0825a[] m2566b(Map<Integer, T> map) {
        C0825a[] c0825aArr = new C0825a[map.size()];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            C0825a c0825a = new C0825a();
            c0825a.key = ((Integer) entry.getKey()).intValue();
            c0825a.value = ((Number) entry.getValue()).longValue();
            int i2 = i + 1;
            c0825aArr[i] = c0825a;
            i = i2;
        }
        return c0825aArr;
    }

    /* renamed from: a */
    public static C1214a m2562a(ReflectionEventType reflectionEventType, String str, String str2, long j, C0970b c0970b) {
        Calendar instance = Calendar.getInstance();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        C1214a c1214a = new C1214a();
        C1219f c1219f = new C1219f();
        c1219f.mo14098e(instance.getTimeInMillis());
        c1219f.mo14102i(instance.getTimeZone().getID());
        c1219f.mo14101h((long) instance.getTimeZone().getOffset(c1219f.f469dh.timestamp));
        c1219f.mo14099f(j);
        c1219f.mo14100g(elapsedRealtime);
        c1214a.mo9270a((C0972d) c1219f);
        c1214a.mo14095g(str);
        c1214a.mo14094f(Arrays.asList(new String[]{"GEL", str2}));
        c1214a.mo14096h("GEL");
        if (reflectionEventType == null) {
            c1214a.mo9269a(ReflectionEventType.APP_USAGE);
        } else {
            c1214a.mo9269a(reflectionEventType);
        }
        if (c0970b != null) {
            c1214a.mo14092a(c0970b);
        }
        return c1214a;
    }

    /* renamed from: a */
    public static String m2563a(ComponentName componentName, long j, Context context) {
        String a = C0832f.m2647a(componentName);
        if (j == UserManagerCompat.getInstance(context).getSerialNumberForUser(Process.myUserHandle())) {
            return a;
        }
        return String.format("%s#%d", new Object[]{a, Long.valueOf(j)});
    }
}
