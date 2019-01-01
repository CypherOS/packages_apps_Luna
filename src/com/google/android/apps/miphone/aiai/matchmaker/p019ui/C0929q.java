package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.q */
class C0929q extends AnimatorListenerAdapter {
    final /* synthetic */ View val$view;
    /* renamed from: xY */
    final /* synthetic */ Float f1159xY;

    C0929q(View view, Float f) {
        this.val$view = view;
        this.f1159xY = f;
    }

    public void onAnimationEnd(Animator animator) {
        this.val$view.setAlpha(this.f1159xY.floatValue());
    }
}
