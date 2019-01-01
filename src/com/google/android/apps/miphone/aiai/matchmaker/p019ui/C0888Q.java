package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.Q */
final /* synthetic */ class C0888Q implements Runnable {
    /* renamed from: yO */
    private final C0884M f1066yO;
    /* renamed from: yY */
    private final C1976c f1067yY;
    /* renamed from: yZ */
    private final C0896X f1068yZ;
    /* renamed from: za */
    private final C1985t f1069za;

    C0888Q(C0884M c0884m, C1976c c1976c, C0896X c0896x, C1985t c1985t) {
        this.f1066yO = c0884m;
        this.f1067yY = c1976c;
        this.f1068yZ = c0896x;
        this.f1069za = c1985t;
    }

    public final void run() {
        C0884M c0884m = this.f1066yO;
        C1976c c1976c = this.f1067yY;
        C0896X c0896x = this.f1068yZ;
        C1985t c1985t = this.f1069za;
        synchronized (c0884m) {
            c0896x.mo3335a(c1976c, c1985t);
        }
    }
}
