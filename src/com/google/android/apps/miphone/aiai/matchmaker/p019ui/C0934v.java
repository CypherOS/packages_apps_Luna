package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.res.Resources;
import android.os.Handler;
import android.util.SparseArray;
import android.widget.FrameLayout;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.v */
public class C0934v {
    final Handler handler;
    /* renamed from: xH */
    final FrameLayout f1168xH;
    /* renamed from: xK */
    final int f1169xK;
    /* renamed from: xL */
    final int f1170xL;
    /* renamed from: xM */
    final SparseArray f1171xM = new SparseArray();
    /* renamed from: xd */
    final C0918e f1172xd;

    C0934v(FrameLayout frameLayout, C0918e c0918e, Handler handler) {
        this.f1168xH = frameLayout;
        this.f1172xd = c0918e;
        this.handler = handler;
        Resources resources = frameLayout.getResources();
        this.f1169xK = resources.getDimensionPixelSize(C0892R.dimen.selection_padding_x);
        this.f1170xL = resources.getDimensionPixelSize(C0892R.dimen.selection_padding_y);
    }
}
