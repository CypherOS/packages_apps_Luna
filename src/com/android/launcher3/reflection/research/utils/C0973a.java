package com.android.launcher3.reflection.research.utils;

import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.predictor.C0961b;
import com.android.launcher3.reflection.research.predictor.C0964g;
import com.android.launcher3.reflection.research.predictor.C0966i;
import java.util.LinkedHashMap;

/* renamed from: com.android.launcher3.reflection.research.utils.a */
public class C0973a {
    /* renamed from: gs */
    public static final LinkedHashMap<String, Integer> f390gs = new PredictorFactory$1();

    /* renamed from: a */
    public static C0966i m3119a(C0961b c0961b, C0940c c0940c) {
        C0966i c0966i = new C0966i();
        for (String str : f390gs.keySet()) {
            c0966i.mo9258a(C0964g.m3074a(str, c0940c), ((Integer) f390gs.get(str)).intValue());
        }
        c0966i.mo9260c(c0961b);
        return c0966i;
    }
}
