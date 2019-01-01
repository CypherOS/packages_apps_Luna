package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.H */
final /* synthetic */ class C0880H implements Runnable {
    /* renamed from: yA */
    private final Runnable f1036yA;
    /* renamed from: yz */
    private final C1701G f1037yz;

    C0880H(C1701G c1701g, Runnable runnable) {
        this.f1037yz = c1701g;
        this.f1036yA = runnable;
    }

    public final void run() {
        C1701G c1701g = this.f1037yz;
        c1701g.f2369yy.f1001yi.execute(this.f1036yA);
    }
}
