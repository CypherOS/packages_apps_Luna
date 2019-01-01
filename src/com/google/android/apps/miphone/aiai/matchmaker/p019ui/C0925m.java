package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.m */
final /* synthetic */ class C0925m implements Runnable {
    /* renamed from: xP */
    private final C0924l f1147xP;
    /* renamed from: xQ */
    private final RectF f1148xQ;
    /* renamed from: xR */
    private final FrameLayout f1149xR;
    /* renamed from: xS */
    private final int f1150xS;
    /* renamed from: xT */
    private final int f1151xT;

    C0925m(C0924l c0924l, RectF rectF, FrameLayout frameLayout, int i, int i2) {
        this.f1147xP = c0924l;
        this.f1148xQ = rectF;
        this.f1149xR = frameLayout;
        this.f1150xS = i;
        this.f1151xT = i2;
    }

    public final void run() {
        C0924l c0924l = this.f1147xP;
        RectF rectF = this.f1148xQ;
        FrameLayout frameLayout = this.f1149xR;
        int i = this.f1150xS;
        int i2 = this.f1151xT;
        if (frameLayout.isAttachedToWindow()) {
            int width = ((int) rectF.width()) / 2;
            int height = ((int) rectF.height()) / 2;
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(frameLayout, width, height, 0.0f, (float) Math.hypot((double) width, (double) height));
            i2 = Math.min(250, i2 * 50);
            createCircularReveal.addListener(new C0930r(c0924l, frameLayout, i2, i));
            long j = (long) i2;
            c0924l.handler.postDelayed(new C0926n(frameLayout), j);
            createCircularReveal.setStartDelay(j);
            createCircularReveal.setDuration(200);
            createCircularReveal.start();
        }
    }
}
