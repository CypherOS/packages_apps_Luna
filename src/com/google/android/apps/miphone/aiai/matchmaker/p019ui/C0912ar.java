package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ar */
final /* synthetic */ class C0912ar implements Runnable {
    /* renamed from: zH */
    private final C0902ah f1111zH;
    /* renamed from: zJ */
    private final C1968E f1112zJ;

    C0912ar(C0902ah c0902ah, C1968E c1968e) {
        this.f1111zH = c0902ah;
        this.f1112zJ = c1968e;
    }

    public final void run() {
        C0902ah c0902ah = this.f1111zH;
        C1968E c1968e = this.f1112zJ;
        synchronized (c0902ah) {
            if (c0902ah.f1090xd.mo3367as(c0902ah.f1097zG)) {
                if (c1968e.f3139wn != null) {
                    c0902ah.f1090xd.f1123xp.mo3326cG();
                }
                C1709f c1709f = c0902ah.f1090xd.f1124xq;
                C0918e c0918e = (C0918e) C0915av.m613t(c0902ah.f1090xd);
                c1709f.f2403xy = c1968e;
                c1709f.f2400xd = c0918e;
                return;
            }
        }
    }
}
