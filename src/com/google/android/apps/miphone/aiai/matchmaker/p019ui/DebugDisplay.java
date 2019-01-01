package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1975b;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.DebugDisplay */
class DebugDisplay extends FrameLayout {
    /* renamed from: wU */
    private final Paint f1024wU;
    /* renamed from: wV */
    private final Paint f1025wV;
    /* renamed from: wW */
    private final Paint f1026wW;
    /* renamed from: wX */
    private final Paint f1027wX;
    /* renamed from: wY */
    private final Paint f1028wY = new Paint();
    /* renamed from: wZ */
    private final RectF f1029wZ = new RectF();
    /* renamed from: xa */
    private final float f1030xa;
    /* renamed from: xb */
    final FrameLayout f1031xb;
    /* renamed from: xc */
    private DebugLevel f1032xc = DebugLevel.NONE;
    /* renamed from: xd */
    C0918e f1033xd;

    /* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.DebugDisplay$DebugLevel */
    enum DebugLevel {
        NONE(0),
        RECTS(1),
        RECTS_AND_TEXT(2);
        
        /* renamed from: xh */
        private static final int f1022xh = 0;
        private final int value;

        static {
            f1022xh = RECTS_AND_TEXT.value + 1;
        }

        private DebugLevel(int i) {
            this.value = i;
        }
    }

    public DebugDisplay(Context context, FrameLayout frameLayout) {
        super(context);
        this.f1028wY.setColor(-16711936);
        this.f1028wY.setStrokeWidth(2.0f);
        this.f1028wY.setStyle(Style.STROKE);
        this.f1026wW = m586A(-16776961, 30);
        this.f1027wX = m586A(-65536, 30);
        this.f1025wV = new Paint();
        this.f1025wV.setColor(-65536);
        this.f1025wV.setStyle(Style.FILL);
        this.f1025wV.setAlpha(150);
        this.f1024wU = m586A(-1, 30);
        this.f1030xa = TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        setLayoutParams(new LayoutParams(-1, -1));
        this.f1031xb = frameLayout;
    }

    /* renamed from: cs */
    final void mo3310cs() {
        this.f1031xb.removeAllViews();
        if (this.f1033xd != null) {
            if (this.f1033xd.f1119uq != null) {
                C0918e c0918e = (C0918e) C0915av.m613t(this.f1033xd);
                int length;
                int i;
                if (this.f1032xc == DebugLevel.RECTS) {
                    C1975b[] cu = c0918e.mo3370cu();
                    length = cu.length;
                    int i2 = 0;
                    i = i2;
                    while (i < length) {
                        int i3 = i2;
                        for (C1965A c1965a : cu[i].f3170uy) {
                            c0918e.mo3364a(c1965a, this.f1029wZ);
                            this.f1029wZ.inset(-this.f1030xa, -this.f1030xa);
                            FrameLayout frameLayout = this.f1031xb;
                            RectF rectF = this.f1029wZ;
                            String num = Integer.toString(c1965a.f3121we);
                            i2 = c1965a.f3123wg;
                            StringBuilder stringBuilder = new StringBuilder(15 + String.valueOf(num).length());
                            stringBuilder.append("l:");
                            stringBuilder.append(num);
                            stringBuilder.append("g:");
                            stringBuilder.append(i2);
                            m587a(frameLayout, rectF, stringBuilder.toString(), i3 % 2 == 0 ? this.f1026wW : this.f1027wX, this.f1028wY);
                            i3++;
                        }
                        i++;
                        i2 = i3;
                    }
                    return;
                }
                if (this.f1032xc == DebugLevel.RECTS_AND_TEXT) {
                    for (C1975b c1975b : c0918e.mo3370cu()) {
                        for (C1965A c1965a2 : c1975b.f3170uy) {
                            c0918e.mo3364a(c1965a2, this.f1029wZ);
                            m587a(this.f1031xb, this.f1029wZ, c1965a2.f3119wc, this.f1024wU, this.f1025wV);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m587a(FrameLayout frameLayout, RectF rectF, String str, Paint paint, Paint paint2) {
        FrameLayout frameLayout2 = new FrameLayout(getContext());
        frameLayout.addView(frameLayout2);
        LayoutParams layoutParams = new LayoutParams((int) rectF.width(), (int) rectF.height());
        layoutParams.leftMargin = (int) rectF.left;
        layoutParams.topMargin = (int) rectF.top;
        frameLayout2.setLayoutParams(layoutParams);
        frameLayout2.setAlpha(((float) paint2.getAlpha()) / 255.0f);
        frameLayout2.setBackgroundColor(paint2.getColor());
        TextView textView = new TextView(getContext());
        frameLayout2.addView(textView);
        textView.setText(str);
        textView.setTextSize(7.0f);
        textView.setAlpha(((float) paint.getAlpha()) / 255.0f);
        textView.setTextColor(paint.getColor());
    }

    /* renamed from: A */
    private Paint m586A(int i, int i2) {
        Paint paint = new Paint(65);
        paint.setStyle(Style.FILL);
        paint.setColor(i);
        paint.setTextAlign(Align.LEFT);
        paint.setTextSize((float) i2);
        return paint;
    }
}
