package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1983n;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1984o;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ac */
final /* synthetic */ class C1705ac implements C0901ag {
    /* renamed from: zw */
    private final C1702Z f2390zw;
    /* renamed from: zx */
    private final C0918e f2391zx;

    C1705ac(C1702Z c1702z, C0918e c0918e) {
        this.f2390zw = c1702z;
        this.f2391zx = c0918e;
    }

    /* renamed from: a */
    public final void mo3341a(int i, int i2, int i3, int i4, int i5, int i6) {
        C1702Z c1702z = this.f2390zw;
        C0918e c0918e = this.f2391zx;
        if (c0918e.f1119uq != null && !c0918e.f1125xr.isEmpty()) {
            int i7;
            int i8;
            int i9 = i2;
            int i10 = i5;
            if (i9 == i10) {
                i7 = i3;
                i8 = i6;
                if (i7 == i8) {
                    return;
                }
            } else {
                i7 = i3;
                i8 = i6;
            }
            C0884M c0884m = c1702z.f2376xp;
            String str = ((C1976c) C0915av.m613t(c1702z.f2375xd.f1119uq)).f3172id;
            C0922j at = C0922j.m631at(c1702z.f2371xF);
            C1985t c1985t = c0918e.f1121uv;
            C1968E c1968e = (C1968E) c0918e.f1125xr.get(0);
            C1983n a = C0922j.m630a(((C1968E) c0918e.f1125xr.get(0)).f3134uH, i, i9, i7, i4, i10, i8);
            C1984o b = at.mo3383b(c1985t);
            b.type = c0918e.mo3372cw() ? 2 : 1;
            b.f3221uK = c1968e;
            b.f3223uP = a;
            b.f3222uN = 3;
            b.f3224vm = c1968e.f3135uI;
            c0884m.mo3324a(str, at.mo3386cz());
        }
    }
}
