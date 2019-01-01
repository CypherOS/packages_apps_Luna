package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.view.View;
import android.view.View.OnLongClickListener;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ae */
final /* synthetic */ class C0900ae implements OnLongClickListener {
    /* renamed from: zw */
    private final C1702Z f1088zw;

    C0900ae(C1702Z c1702z) {
        this.f1088zw = c1702z;
    }

    public final boolean onLongClick(View view) {
        C1702Z c1702z = this.f1088zw;
        if (c1702z.f2382zs) {
            c1702z.mo5346cH();
            C0936x.m652g("Handled secondary selection.");
            return true;
        }
        c1702z.mo5350cL();
        return false;
    }
}
