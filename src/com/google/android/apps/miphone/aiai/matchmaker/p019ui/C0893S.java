package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.S */
final /* synthetic */ class C0893S implements Runnable {
    /* renamed from: yO */
    private final C0884M f1072yO;
    /* renamed from: zb */
    private final C1967D f1073zb;
    /* renamed from: zc */
    private final C0897Y f1074zc;
    /* renamed from: zd */
    private final C1976c f1075zd;
    /* renamed from: ze */
    private final EntitiesData f1076ze;

    C0893S(C0884M c0884m, C1967D c1967d, C0897Y c0897y, C1976c c1976c, EntitiesData entitiesData) {
        this.f1072yO = c0884m;
        this.f1073zb = c1967d;
        this.f1074zc = c0897y;
        this.f1075zd = c1976c;
        this.f1076ze = entitiesData;
    }

    public final void run() {
        C0884M c0884m = this.f1072yO;
        C0897Y c0897y = this.f1074zc;
        C1976c c1976c = this.f1075zd;
        EntitiesData entitiesData = this.f1076ze;
        synchronized (c0884m) {
            c0897y.mo3336a(c1976c, entitiesData);
        }
    }
}
