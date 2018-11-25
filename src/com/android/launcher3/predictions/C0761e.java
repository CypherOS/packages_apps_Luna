package com.android.launcher3.predictions;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.e */
class C0761e extends ContentObserver {
    /* renamed from: xN */
    final /* synthetic */ C0760d f995xN;

    C0761e(C0760d c0760d, Handler handler) {
		super(handler);
        this.f995xN = c0760d;
    }

    public void onChange(boolean z) {
        this.f995xN.m2441cL();
    }
}
