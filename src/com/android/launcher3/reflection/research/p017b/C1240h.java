package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0942e;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;

/* renamed from: com.android.launcher3.reflection.research.b.h */
public class C1240h extends C0945f {
    /* renamed from: T */
    public final int mo9182T() {
        return 24;
    }

    public final String getFeatureName() {
        return "hour_of_day";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1240h();
    }

    /* renamed from: a */
    protected final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, 24);
        int i = C0942e.m2974e(reflectionEvent).get(11);
        c0951e.f322eR[i] = 1.0d;
        int i2 = C0942e.m2974e(reflectionEvent).get(12);
        if (i2 < 30) {
            i2 = i - 1;
            if (i2 < 0) {
                i2 = 23;
            }
            c0951e.f322eR[i2] = 1.0d;
        } else if (i2 > 30) {
            i++;
            if (i > 23) {
                i = 0;
            }
            c0951e.f322eR[i] = 1.0d;
        }
        return c0951e;
    }

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1240h();
    }
}
