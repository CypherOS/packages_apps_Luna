package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;
import com.google.android.apps.miphone.aiai.matchmaker.api.InteractionContextData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.N */
final /* synthetic */ class C0885N implements Runnable {
    /* renamed from: yO */
    private final C0884M f1054yO;
    /* renamed from: yP */
    private final IScreenMatchmaker f1055yP;
    /* renamed from: yQ */
    private final C1985t f1056yQ;
    /* renamed from: yR */
    private final C0896X f1057yR;

    C0885N(C0884M c0884m, IScreenMatchmaker iScreenMatchmaker, C1985t c1985t, C0896X c0896x) {
        this.f1054yO = c0884m;
        this.f1055yP = iScreenMatchmaker;
        this.f1056yQ = c1985t;
        this.f1057yR = c0896x;
    }

    public final void run() {
        C0884M c0884m = this.f1054yO;
        IScreenMatchmaker iScreenMatchmaker = this.f1055yP;
        C1985t c1985t = this.f1056yQ;
        C0896X c0896x = this.f1057yR;
        synchronized (c0884m) {
            try {
                ((IScreenMatchmaker) C0915av.m613t(iScreenMatchmaker)).extractContentAsync(c0884m.packageName, c0884m.f1042yC, InteractionContextData.m573a(c1985t), c0884m.f1041yB, c0884m.f1046yG, c0884m.f1053yN, new C1991V(c0884m, c1985t, c0896x));
            } catch (Throwable e) {
                C0936x.m647a("Failed to call matchmaker (content extraction) ", e);
            }
        }
    }
}
