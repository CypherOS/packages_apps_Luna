package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.I */
class C0881I extends BroadcastReceiver {
    /* renamed from: yy */
    final /* synthetic */ C0875B f1038yy;

    C0881I(C0875B c0875b) {
        this.f1038yy = c0875b;
    }

    public void onReceive(Context context, Intent intent) {
        C0936x.m652g("Apk changed, reconnecting to service");
        this.f1038yy.f1002yj.post(new C0882J(this.f1038yy));
    }

    /* renamed from: c */
    static final /* synthetic */ void m589c(C0875B c0875b) {
        c0875b.mo3303cE();
        c0875b.mo3304cF();
    }
}
