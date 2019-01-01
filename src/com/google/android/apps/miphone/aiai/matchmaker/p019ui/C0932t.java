package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.t */
class C0932t extends AnimatorListenerAdapter {
    /* renamed from: yf */
    final /* synthetic */ C0931s f1166yf;

    C0932t(C0931s c0931s) {
        this.f1166yf = c0931s;
    }

    public void onAnimationEnd(Animator animator) {
        this.f1166yf.f1164yd.setAlpha(0);
        this.f1166yf.f1165ye.f1163yc.handler.postDelayed(new C0933u(this.f1166yf.f1165ye.f1160xZ), 1000);
    }
}
