package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0946e;
import com.google.research.reflection.signal.ReflectionEvent;

/* renamed from: com.google.research.reflection.b.c */
public class C1241c extends C0949f {
    /* renamed from: T */
    public final int mo9200T() {
        return 7;
    }

    public final String getFeatureName() {
        return "day_of_week";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1241c();
    }

    /* renamed from: a */
    protected final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, 7);
        c0955e.f322eR[C0946e.m2989e(reflectionEvent).get(7) - 1] = 1.0d;
        return c0955e;
    }

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1241c();
    }
}
