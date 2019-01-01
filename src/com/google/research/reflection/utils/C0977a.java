package com.google.research.reflection.utils;

import com.google.research.reflection.p016a.C0944c;
import com.google.research.reflection.predictor.C0965b;
import com.google.research.reflection.predictor.C0968g;
import com.google.research.reflection.predictor.C0970i;
import java.util.LinkedHashMap;

/* renamed from: com.google.research.reflection.utils.a */
public class C0977a {
    /* renamed from: gs */
    public static final LinkedHashMap<String, Integer> f390gs = new PredictorFactory$1();

    /* renamed from: a */
    public static C0970i m3134a(C0965b c0965b, C0944c c0944c) {
        C0970i c0970i = new C0970i();
        for (String str : f390gs.keySet()) {
            c0970i.mo9276a(C0968g.m3089a(str, c0944c), ((Integer) f390gs.get(str)).intValue());
        }
        c0970i.mo9278c(c0965b);
        return c0970i;
    }
}
