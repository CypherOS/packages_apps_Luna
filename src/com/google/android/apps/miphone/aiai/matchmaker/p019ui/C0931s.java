package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.s */
class C0931s extends AnimatorListenerAdapter {
    /* renamed from: yd */
    final /* synthetic */ ImageView f1164yd;
    /* renamed from: ye */
    final /* synthetic */ C0930r f1165ye;

    C0931s(C0930r c0930r, ImageView imageView) {
        this.f1165ye = c0930r;
        this.f1164yd = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.f1164yd.setAlpha(0.7f);
        if (this.f1165ye.f1162yb == 4) {
            C0924l.m642a(this.f1164yd, Float.valueOf(0.0f), 500, new LinearInterpolator(), 250 - this.f1165ye.f1161ya, new C0932t(this));
        }
    }
}
