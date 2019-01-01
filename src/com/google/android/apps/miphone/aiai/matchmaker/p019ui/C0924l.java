package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1975b;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.l */
public class C0924l {
    final Handler handler;
    /* renamed from: xF */
    final int f1136xF;
    /* renamed from: xG */
    final C0923k f1137xG;
    /* renamed from: xH */
    final FrameLayout f1138xH;
    /* renamed from: xI */
    final C1975b f1139xI;
    /* renamed from: xJ */
    final C1702Z f1140xJ;
    /* renamed from: xK */
    final int f1141xK;
    /* renamed from: xL */
    final int f1142xL;
    /* renamed from: xM */
    final SparseArray f1143xM = new SparseArray();
    /* renamed from: xN */
    final String f1144xN;
    /* renamed from: xO */
    int f1145xO;
    /* renamed from: xp */
    final C0884M f1146xp;

    /* renamed from: a */
    static /* synthetic */ void m642a(View view, Float f, int i, Interpolator interpolator, int i2, AnimatorListenerAdapter animatorListenerAdapter) {
        AnimatorListener animatorListenerAdapter2;
        ViewPropertyAnimator startDelay = view.animate().alpha(f.floatValue()).setDuration((long) i).setInterpolator(interpolator).setStartDelay((long) i2);
        if (animatorListenerAdapter2 == null) {
            animatorListenerAdapter2 = new C0929q(view, f);
        }
        startDelay.setListener(animatorListenerAdapter2);
    }

    /* renamed from: b */
    static void m643b(C1965A c1965a, RectF rectF) {
        rectF.set(c1965a.f3118wb.left, c1965a.f3118wb.top, c1965a.f3118wb.width + c1965a.f3118wb.left, c1965a.f3118wb.height + c1965a.f3118wb.top);
    }

    C0924l(FrameLayout frameLayout, C1975b c1975b, C0923k c0923k, C0884M c0884m, C1702Z c1702z, int i, int i2, String str, Handler handler) {
        this.f1138xH = frameLayout;
        this.f1137xG = c0923k;
        this.f1139xI = c1975b;
        this.f1140xJ = c1702z;
        this.f1146xp = c0884m;
        this.f1145xO = i;
        this.f1136xF = i2;
        this.f1144xN = str;
        this.handler = handler;
        Resources resources = frameLayout.getResources();
        this.f1141xK = resources.getDimensionPixelSize(C0892R.dimen.selection_padding_x);
        this.f1142xL = resources.getDimensionPixelSize(C0892R.dimen.selection_padding_y);
    }
}
