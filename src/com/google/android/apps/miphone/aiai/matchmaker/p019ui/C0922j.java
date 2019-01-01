package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.FeedbackData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1978f;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1980j;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1981k;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1982l;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1983n;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1984o;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.j */
public class C0922j {
    /* renamed from: xD */
    private static final C1980j[] f1133xD = new C1980j[0];
    /* renamed from: xE */
    final List f1134xE = new ArrayList();
    /* renamed from: xF */
    final int f1135xF;

    /* renamed from: at */
    static C0922j m631at(int i) {
        return new C0922j(i);
    }

    /* renamed from: b */
    static C1983n m632b(C1968E c1968e) {
        C1983n c1983n = new C1983n();
        boolean z = true;
        boolean z2 = c1968e.f3145wt == 1 && !c1968e.f3134uH;
        c1983n.f3215vg = z2;
        if (c1968e.f3145wt != 1 || !c1968e.f3134uH) {
            z = false;
        }
        c1983n.f3214vf = z;
        c1983n.f3216vh = false;
        if (c1968e.f3134uH) {
            c1983n.f3217vi = c1968e.f3146wu;
            c1983n.f3218vj = c1968e.f3147wv;
        } else {
            c1983n.f3219vk = c1968e.f3146wu;
            c1983n.f3220vl = c1968e.f3147wv;
        }
        return c1983n;
    }

    /* renamed from: a */
    static C1983n m630a(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        C1983n c1983n = new C1983n();
        boolean z2 = false;
        boolean z3 = i4 == 1 && !z;
        c1983n.f3215vg = z3;
        if (i == 1 && z) {
            z2 = true;
        }
        c1983n.f3214vf = z2;
        c1983n.f3216vh = true;
        c1983n.f3217vi = i2;
        c1983n.f3218vj = i3;
        c1983n.f3219vk = i5;
        c1983n.f3220vl = i6;
        return c1983n;
    }

    /* renamed from: au */
    final C0922j mo3382au(int i) {
        C1982l cA = mo3385cA();
        cA.f3210uN = 1;
        cA.f3211vc = i;
        return this;
    }

    /* renamed from: a */
    final C0922j mo3381a(C1985t c1985t, C1968E c1968e, C0918e c0918e, C1983n c1983n) {
        C1984o b = mo3383b(c1985t);
        b.type = c0918e.mo3372cw() ? 2 : 1;
        b.f3221uK = c1968e;
        b.f3223uP = c1983n;
        b.f3222uN = 4;
        b.f3224vm = c1968e.f3135uI;
        return this;
    }

    /* renamed from: cz */
    final FeedbackData mo3386cz() {
        C1981k c1981k = new C1981k();
        c1981k.f3208uA = (long) this.f1135xF;
        c1981k.f3209vb = (C1980j[]) C0915av.m613t((C1980j[]) this.f1134xE.toArray(f1133xD));
        return FeedbackData.m571a(c1981k);
    }

    /* renamed from: cA */
    final C1982l mo3385cA() {
        C1980j cB = m633cB();
        cB.f3201uV = 0;
        C1982l c1982l = new C1982l();
        cB.f3200uT = -1;
        cB.f3200uT = 0;
        cB.f3203uX = c1982l;
        return c1982l;
    }

    /* renamed from: b */
    final C1984o mo3383b(C1985t c1985t) {
        C1980j cB = m633cB();
        if (c1985t != null) {
            cB.f3206uv = c1985t;
        }
        cB.f3201uV = 0;
        C1984o c1984o = new C1984o();
        cB.f3200uT = -1;
        cB.f3200uT = 1;
        cB.f3204uY = c1984o;
        return c1984o;
    }

    /* renamed from: c */
    final C1978f mo3384c(C1985t c1985t) {
        C1980j cB = m633cB();
        if (c1985t != null) {
            cB.f3206uv = c1985t;
        }
        cB.f3201uV = 0;
        C1978f c1978f = new C1978f();
        cB.f3200uT = -1;
        cB.f3200uT = 2;
        cB.f3205uZ = c1978f;
        return c1978f;
    }

    /* renamed from: cB */
    private C1980j m633cB() {
        C1980j c1980j = new C1980j();
        this.f1134xE.add(c1980j);
        return c1980j;
    }

    private C0922j(int i) {
        this.f1135xF = i;
    }
}
