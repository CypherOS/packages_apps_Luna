package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.G */
class C1701G implements C0937y {
    /* renamed from: yy */
    final /* synthetic */ C0875B f2369yy;

    C1701G(C0875B c0875b) {
        this.f2369yy = c0875b;
    }

    /* renamed from: cD */
    public final IScreenMatchmaker mo3403cD() {
        return this.f2369yy.f1004yl;
    }

    /* renamed from: c */
    public final void mo3402c(Runnable runnable) {
        this.f2369yy.mo3304cF();
        this.f2369yy.mo3305e(new C0880H(this, runnable));
    }

    /* renamed from: d */
    public final void mo3404d(Runnable runnable) {
        this.f2369yy.f1002yj.post(runnable);
    }
}
