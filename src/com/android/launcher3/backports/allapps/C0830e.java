package com.android.launcher3.backports.allapps;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.e */
class C0830e extends ContentObserver {
    /* renamed from: Bg */
    final /* synthetic */ C0829d f1218Bg;

    C0830e(C0829d c0829d, Handler handler) {
		super(handler);
        this.f1218Bg = c0829d;
    }

    public void onChange(boolean z) {
        this.f1218Bg.m661cZ();
    }
}
