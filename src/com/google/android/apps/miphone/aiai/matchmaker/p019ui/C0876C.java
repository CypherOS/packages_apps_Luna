package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.C */
public final /* synthetic */ class C0876C implements Runnable {
    /* renamed from: xQ */
    private final RectF f1010xQ;
    /* renamed from: yr */
    private final C0875B f1011yr;
    /* renamed from: ys */
    private final PointF f1012ys;
    /* renamed from: yt */
    private final long f1013yt;
    /* renamed from: yu */
    private final Bitmap f1014yu;
    /* renamed from: yv */
    private final Bundle f1015yv;
    /* renamed from: yw */
    private final ComponentName f1016yw;
    /* renamed from: yx */
    private final C0883L f1017yx;

    public C0876C(C0875B c0875b, RectF rectF, PointF pointF, long j, Bitmap bitmap, Bundle bundle, ComponentName componentName, C0883L c0883l) {
        this.f1011yr = c0875b;
        this.f1010xQ = rectF;
        this.f1012ys = pointF;
        this.f1013yt = j;
        this.f1014yu = bitmap;
        this.f1015yv = bundle;
        this.f1016yw = componentName;
        this.f1017yx = c0883l;
    }

    public final void run() {
        C0875B c0875b = this.f1011yr;
        RectF rectF = this.f1010xQ;
        PointF pointF = this.f1012ys;
        long j = this.f1013yt;
        Bitmap bitmap = this.f1014yu;
        Bundle bundle = this.f1015yv;
        ComponentName componentName = this.f1016yw;
        C0883L c0883l = this.f1017yx;
        C0937y c0937y = c0875b.f1008yp;
        if (componentName == null) {
            componentName = new ComponentName("", "");
        }
        C1702Z.m2230a(c0875b.context, new C0884M(c0937y, rectF, pointF, j, bitmap, bundle, componentName, c0883l != null ? c0883l : C1708b.m2247ct()), c0875b.f1003yk, c0875b.f1002yj);
    }
}
