package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.util.ArrayList;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.c */
final /* synthetic */ class C0916c implements OnTouchListener {
    /* renamed from: xn */
    private final C1708b f1115xn;
    /* renamed from: xo */
    private final C1702Z f1116xo;

    C0916c(C1708b c1708b, C1702Z c1702z) {
        this.f1115xn = c1708b;
        this.f1116xo = c1702z;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        C1708b c1708b = this.f1115xn;
        C1702Z c1702z = this.f1116xo;
        switch (motionEvent.getAction()) {
            case 0:
                if (c1708b.f2399xm < 0) {
                    c1708b.f2399xm = System.currentTimeMillis();
                }
                c1708b.f2397xk.set(motionEvent.getX(), motionEvent.getY());
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(motionEvent);
                c1702z.f2385zv = arrayList;
                c1702z.mo5345b(c1702z.mo5344a(c1708b.f2397xk));
                break;
            case 1:
                if (c1708b.f2399xm > 0) {
                    c1702z.mo5347cI();
                }
                c1708b.f2399xm = -1;
                break;
            default:
                break;
        }
        return view.onTouchEvent(motionEvent);
    }
}
