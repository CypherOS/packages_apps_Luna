package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.View.OnLongClickListener;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.p */
final /* synthetic */ class C0928p implements OnLongClickListener {
    /* renamed from: xP */
    private final C0924l f1157xP;
    /* renamed from: xQ */
    private final RectF f1158xQ;

    C0928p(C0924l c0924l, RectF rectF) {
        this.f1157xP = c0924l;
        this.f1158xQ = rectF;
    }

    public final boolean onLongClick(View view) {
        C0924l c0924l = this.f1157xP;
        RectF rectF = this.f1158xQ;
        c0924l.f1140xJ.mo5345b(new PointF(rectF.centerX(), rectF.centerY()));
        c0924l.f1140xJ.mo5346cH();
        return true;
    }
}
