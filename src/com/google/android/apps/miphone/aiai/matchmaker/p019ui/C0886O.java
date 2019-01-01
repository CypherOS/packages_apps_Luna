package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.ContentData;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.O */
final /* synthetic */ class C0886O implements Runnable {
    /* renamed from: yO */
    private final C0884M f1058yO;
    /* renamed from: yP */
    private final IScreenMatchmaker f1059yP;
    /* renamed from: yS */
    private final C1976c f1060yS;
    /* renamed from: yT */
    private final C0897Y f1061yT;
    /* renamed from: yU */
    private final boolean f1062yU;

    C0886O(C0884M c0884m, IScreenMatchmaker iScreenMatchmaker, C1976c c1976c, C0897Y c0897y, boolean z) {
        this.f1058yO = c0884m;
        this.f1059yP = iScreenMatchmaker;
        this.f1060yS = c1976c;
        this.f1061yT = c0897y;
        this.f1062yU = z;
    }

    public final void run() {
        C0884M c0884m = this.f1058yO;
        IScreenMatchmaker iScreenMatchmaker = this.f1059yP;
        C1976c c1976c = this.f1060yS;
        C0897Y c0897y = this.f1061yT;
        boolean z = this.f1062yU;
        synchronized (c0884m) {
            try {
                ((IScreenMatchmaker) C0915av.m613t(iScreenMatchmaker)).getEntitiesAsync(c0884m.packageName, c0884m.f1042yC, ContentData.m568a(c1976c), c0884m.f1041yB, c0884m.f1046yG, c0884m.f1053yN, new C1992W(c0884m, c1976c, c0897y, z));
            } catch (Throwable e) {
                C0936x.m647a("Failed to call service - extract entities.", e);
                c0884m.mo3325a(e);
            }
        }
    }
}
