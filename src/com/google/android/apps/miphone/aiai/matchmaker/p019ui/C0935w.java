package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.w */
final /* synthetic */ class C0935w implements Runnable {
    /* renamed from: xQ */
    private final RectF f1173xQ;
    /* renamed from: xR */
    private final FrameLayout f1174xR;
    /* renamed from: yg */
    private final C0934v f1175yg;

    C0935w(C0934v c0934v, RectF rectF, FrameLayout frameLayout) {
        this.f1175yg = c0934v;
        this.f1173xQ = rectF;
        this.f1174xR = frameLayout;
    }

    public final void run() {
        RectF rectF = this.f1173xQ;
        FrameLayout frameLayout = this.f1174xR;
        if (frameLayout.isAttachedToWindow()) {
            int width = ((int) rectF.width()) / 2;
            int height = ((int) rectF.height()) / 2;
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(frameLayout, width, height, 0.0f, (float) Math.hypot((double) width, (double) height));
            frameLayout.setVisibility(0);
            createCircularReveal.start();
        }
    }
}
