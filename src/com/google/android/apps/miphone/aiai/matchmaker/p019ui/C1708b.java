package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.PointF;
import android.view.ViewGroup;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.b */
public class C1708b implements C0883L {
    /* renamed from: xj */
    private static final C1708b f2396xj = new C1708b(null);
    /* renamed from: xk */
    final PointF f2397xk = new PointF();
    /* renamed from: xl */
    private final ViewGroup f2398xl;
    /* renamed from: xm */
    long f2399xm = -1;

    public C1708b(ViewGroup viewGroup) {
        this.f2398xl = viewGroup;
    }

    /* renamed from: ct */
    public static C0883L m2247ct() {
        return f2396xj;
    }

    /* renamed from: a */
    public final void mo3316a(C1702Z c1702z) {
        if (this.f2398xl != null) {
            this.f2398xl.removeAllViews();
            this.f2398xl.addView(c1702z);
        }
        c1702z.mo5347cI();
        c1702z.setLongClickable(true);
        c1702z.setOnTouchListener(new C0916c(this, c1702z));
        c1702z.setOnLongClickListener(new C0917d(this, c1702z));
    }
}
