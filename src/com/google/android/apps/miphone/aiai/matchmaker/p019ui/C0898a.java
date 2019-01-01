package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.a */
class C0898a extends ReplacementSpan {
    /* renamed from: wP */
    final RectF f1081wP;
    /* renamed from: wQ */
    final int f1082wQ;
    /* renamed from: wR */
    final int f1083wR;
    /* renamed from: wS */
    final int f1084wS;
    /* renamed from: wT */
    final boolean f1085wT;
    /* renamed from: wc */
    final String f1086wc;

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = (int) (-this.f1081wP.height());
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.leading = 0;
            fontMetricsInt.descent = 0;
            fontMetricsInt.bottom = 0;
        }
        return (int) this.f1081wP.width();
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        if (C0921i.m629cy()) {
            paint.setColor(Color.argb(50, 100, 100, this.f1082wQ * 10));
            int i6 = (int) f;
            canvas.drawRect((float) i6, (float) i3, (float) (i6 + ((int) this.f1081wP.width())), (float) i5, paint);
        }
    }

    /* renamed from: a */
    static C0898a m604a(String str, RectF rectF, int i, boolean z, int i2, int i3) {
        return new C0898a(str, rectF, i, z, i2, i3);
    }

    private C0898a(String str, RectF rectF, int i, boolean z, int i2, int i3) {
        this.f1086wc = str;
        this.f1081wP = rectF;
        this.f1082wQ = i;
        this.f1085wT = z;
        this.f1083wR = i2;
        this.f1084wS = i3;
    }
}
