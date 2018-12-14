package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0942e;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;

/* renamed from: com.android.launcher3.reflection.research.b.c */
public class C1237c extends C0945f {
    /* renamed from: T */
    public final int mo9182T() {
        return 7;
    }

    public final String getFeatureName() {
        return "day_of_week";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1237c();
    }

    /* renamed from: a */
    protected final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, 7);
        c0951e.f322eR[C0942e.m2974e(reflectionEvent).get(7) - 1] = 1.0d;
        return c0951e;
    }

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1237c();
    }
}
