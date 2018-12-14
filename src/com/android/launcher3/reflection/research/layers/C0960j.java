package com.android.launcher3.reflection.research.layers;

import java.util.Random;

/* renamed from: com.android.launcher3.reflection.research.layers.j */
public class C0960j {
    /* renamed from: b */
    static double m3056b(double d) {
        d = Math.exp(-d);
        if (Double.isInfinite(d)) {
            return 0.0d;
        }
        return 1.0d / (d + 1.0d);
    }

    /* renamed from: a */
    public static void m3055a(C0951e c0951e, boolean z) {
        int f = c0951e.mo9222f(false);
        for (int i = 0; i < f; i++) {
            int g = c0951e.mo9223g(false);
            if (z) {
                for (int i2 = 0; i2 < g; i2++) {
                    c0951e.mo9220b(false, i, i2, Math.random() * 0.1d);
                }
            } else {
                Random random = new Random();
                for (int i3 = 0; i3 < g; i3++) {
                    c0951e.mo9220b(false, i, i3, random.nextGaussian() * 0.1d);
                }
            }
        }
    }
}
