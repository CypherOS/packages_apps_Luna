package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.support.p009v4.view.p016b.C1598a;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.quickstep.QuickScrubController;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.r */
class C0930r implements AnimatorListener {
    /* renamed from: xZ */
    final /* synthetic */ FrameLayout f1160xZ;
    /* renamed from: ya */
    final /* synthetic */ int f1161ya;
    /* renamed from: yb */
    final /* synthetic */ int f1162yb;
    /* renamed from: yc */
    final /* synthetic */ C0924l f1163yc;

    C0930r(C0924l c0924l, FrameLayout frameLayout, int i, int i2) {
        this.f1163yc = c0924l;
        this.f1160xZ = frameLayout;
        this.f1161ya = i;
        this.f1162yb = i2;
    }

    public void onAnimationStart(Animator animator) {
        View view = (ImageView) this.f1160xZ.findViewById(C0892R.C0890id.highlight_color);
        C0924l.m642a(view, Float.valueOf(0.75f), QuickScrubController.QUICK_SCRUB_FROM_HOME_START_DURATION, new C1598a(), this.f1161ya, new C0931s(this, view));
        if (this.f1162yb != 4) {
            View view2 = (ImageView) this.f1160xZ.findViewById(C0892R.C0890id.stroke_color);
            C0924l.m642a(view, Float.valueOf(0.0f), 500, new DecelerateInterpolator(), 250 - this.f1161ya, null);
            C0924l.m642a(view2, Float.valueOf(0.7f), 500, new DecelerateInterpolator(), 250 - this.f1161ya, null);
        }
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }
}
