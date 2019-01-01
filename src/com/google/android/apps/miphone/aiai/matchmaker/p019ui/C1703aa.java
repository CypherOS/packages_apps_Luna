package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1984o;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.aa */
final /* synthetic */ class C1703aa implements C0896X {
    /* renamed from: xV */
    private final int f2386xV;
    /* renamed from: zw */
    private final C1702Z f2387zw;

    C1703aa(C1702Z c1702z, int i) {
        this.f2387zw = c1702z;
        this.f2386xV = i;
    }

    /* renamed from: a */
    public final void mo3335a(C1976c c1976c, C1985t c1985t) {
        C1702Z c1702z = this.f2387zw;
        int i = this.f2386xV;
        if (c1702z.f2375xd.mo3367as(i)) {
            c1702z.f2375xd.f1121uv = c1985t;
            c1702z.f2376xp.mo3322a(c1976c, new C1706af(c1702z, i));
            if (c1976c != null && c1985t != null) {
                C0884M c0884m = c1702z.f2376xp;
                String str = ((C1976c) C0915av.m613t(c1976c)).f3172id;
                C0922j at = C0922j.m631at(c1702z.f2371xF);
                C0918e c0918e = c1702z.f2375xd;
                C1984o b = at.mo3383b(c1985t);
                b.type = c0918e.mo3372cw() ? 2 : 1;
                b.f3222uN = 1;
                c0884m.mo3324a(str, at.mo3386cz());
            }
        }
    }
}
