package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1984o;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.o */
final /* synthetic */ class C0927o implements OnTouchListener {
    /* renamed from: xP */
    private final C0924l f1153xP;
    /* renamed from: xV */
    private final int f1154xV;
    /* renamed from: xW */
    private final RectF f1155xW;
    /* renamed from: xX */
    private final FrameLayout f1156xX;

    C0927o(C0924l c0924l, int i, RectF rectF, FrameLayout frameLayout) {
        this.f1153xP = c0924l;
        this.f1154xV = i;
        this.f1155xW = rectF;
        this.f1156xX = frameLayout;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        C0924l c0924l = this.f1153xP;
        int i = this.f1154xV;
        RectF rectF = this.f1155xW;
        FrameLayout frameLayout = this.f1156xX;
        if (motionEvent.getAction() == 1) {
            if (i != 5) {
                frameLayout.requestDisallowInterceptTouchEvent(true);
                int[] iArr = new int[2];
                frameLayout.getLocationOnScreen(iArr);
                DisplayMetrics displayMetrics = c0924l.f1138xH.getContext().getResources().getDisplayMetrics();
                ((C0923k) C0915av.m613t(c0924l.f1137xG)).mo3388e(frameLayout, (iArr[0] + (view.getWidth() / 2)) - (displayMetrics.widthPixels / 2), (iArr[1] - (displayMetrics.heightPixels / 2)) + c0924l.f1138xH.getContext().getResources().getDimensionPixelSize(C0892R.dimen.gleam_popup_margin_top));
            } else {
                c0924l.f1140xJ.mo5345b(new PointF(rectF.centerX(), rectF.centerY()));
                c0924l.f1140xJ.mo5346cH();
            }
        }
        C0884M c0884m = c0924l.f1146xp;
        String str = c0924l.f1144xN;
        C0922j at = C0922j.m631at(c0924l.f1136xF);
        C1984o b = at.mo3383b(null);
        b.type = 1;
        b.f3222uN = 6;
        b.f3224vm = i;
        c0884m.mo3324a(str, at.mo3386cz());
        return view.onTouchEvent(motionEvent);
    }
}
