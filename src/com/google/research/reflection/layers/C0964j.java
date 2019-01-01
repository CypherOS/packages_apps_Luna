package com.google.research.reflection.layers;

import java.util.Random;

/* renamed from: com.google.research.reflection.layers.j */
public class C0964j {
    /* renamed from: b */
    static double m3071b(double d) {
        d = Math.exp(-d);
        if (Double.isInfinite(d)) {
            return 0.0d;
        }
        return 1.0d / (d + 1.0d);
    }

    /* renamed from: a */
    public static void m3070a(C0955e c0955e, boolean z) {
        int f = c0955e.mo9240f(false);
        for (int i = 0; i < f; i++) {
            int g = c0955e.mo9241g(false);
            if (z) {
                for (int i2 = 0; i2 < g; i2++) {
                    c0955e.mo9238b(false, i, i2, Math.random() * 0.1d);
                }
            } else {
                Random random = new Random();
                for (int i3 = 0; i3 < g; i3++) {
                    c0955e.mo9238b(false, i, i3, random.nextGaussian() * 0.1d);
                }
            }
        }
    }
}
