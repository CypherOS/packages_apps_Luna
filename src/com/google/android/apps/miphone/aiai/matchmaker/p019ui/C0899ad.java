package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ad */
final /* synthetic */ class C0899ad implements OnTouchListener {
    /* renamed from: zw */
    private final C1702Z f1087zw;

    C0899ad(C1702Z c1702z) {
        this.f1087zw = c1702z;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        C1702Z c1702z = this.f1087zw;
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    if (!c1702z.f2382zs) {
                        C0936x.m652g("Handling secondary selection.");
                        PointF pointF = new PointF();
                        pointF.set(motionEvent.getX(), motionEvent.getY());
                        c1702z.mo5344a(pointF);
                        c1702z.mo5345b(pointF);
                        z = true;
                    }
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
        z = c1702z.f2382zs;
        StringBuilder stringBuilder = new StringBuilder(49);
        stringBuilder.append("Canceling secondary selection; long press = ");
        stringBuilder.append(z);
        C0936x.m652g(stringBuilder.toString());
        if (c1702z.f2382zs) {
            c1702z.mo5347cI();
            c1702z.mo5350cL();
        }
        z = false;
        c1702z.f2382zs = z;
        return view.onTouchEvent(motionEvent);
    }
}
