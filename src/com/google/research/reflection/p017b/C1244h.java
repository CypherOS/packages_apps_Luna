package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0946e;
import com.google.research.reflection.signal.ReflectionEvent;

/* renamed from: com.google.research.reflection.b.h */
public class C1244h extends C0949f {
    /* renamed from: T */
    public final int mo9200T() {
        return 24;
    }

    public final String getFeatureName() {
        return "hour_of_day";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1244h();
    }

    /* renamed from: a */
    protected final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, 24);
        int i = C0946e.m2989e(reflectionEvent).get(11);
        c0955e.f322eR[i] = 1.0d;
        int i2 = C0946e.m2989e(reflectionEvent).get(12);
        if (i2 < 30) {
            i2 = i - 1;
            if (i2 < 0) {
                i2 = 23;
            }
            c0955e.f322eR[i2] = 1.0d;
        } else if (i2 > 30) {
            i++;
            if (i > 23) {
                i = 0;
            }
            c0955e.f322eR[i] = 1.0d;
        }
        return c0955e;
    }

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1244h();
    }
}
