package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.os.Bundle;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ak */
final /* synthetic */ class C0905ak implements Runnable {
    /* renamed from: xV */
    private final int f1102xV;
    /* renamed from: zH */
    private final C0902ah f1103zH;
    /* renamed from: zI */
    private final int f1104zI;

    C0905ak(C0902ah c0902ah, int i, int i2) {
        this.f1103zH = c0902ah;
        this.f1102xV = i;
        this.f1104zI = i2;
    }

    public final void run() {
        C0902ah c0902ah = this.f1103zH;
        int i = this.f1102xV;
        int i2 = this.f1104zI;
        Bundle bundle = new Bundle();
        bundle.putInt("ACTION_ARGUMENT_SELECTION_START_INT", i);
        bundle.putInt("ACTION_ARGUMENT_SELECTION_END_INT", i2);
        c0902ah.f1098zy.performAccessibilityAction(131072, bundle);
    }
}
