package com.android.launcher3.reflection.research.predictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.android.launcher3.reflection.research.predictor.k */
public class C0968k {
    /* renamed from: fO */
    public double[] f364fO = null;
    /* renamed from: fP */
    float[] f365fP = null;
    /* renamed from: fQ */
    private float f366fQ = -1.0f;
    /* renamed from: fR */
    public ArrayList<C0967a> f367fR = new ArrayList();

    /* renamed from: com.android.launcher3.reflection.research.predictor.k$a */
    public static class C0967a implements Comparable<C0967a> {
        /* renamed from: ca */
        public float f360ca;
        /* renamed from: fS */
        public final Set<String> f361fS;
        /* renamed from: fT */
        int f362fT = 0;
        /* renamed from: id */
        public String f363id;

        public /* synthetic */ int compareTo(Object obj) {
            return Float.compare(this.f360ca, ((C0967a) obj).f360ca);
        }

        public C0967a(String str, float f, String str2) {
            this.f363id = str;
            this.f360ca = f;
            this.f361fS = new HashSet(Arrays.asList(new String[]{str2}));
        }

        public C0967a(String str, float f, Set<String> set) {
            this.f363id = str;
            this.f360ca = f;
            this.f361fS = new HashSet(set);
        }
    }
}
