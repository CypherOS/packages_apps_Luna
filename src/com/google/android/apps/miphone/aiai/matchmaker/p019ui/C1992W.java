package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.IEntitiesCallback.Stub;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.W */
class C1992W extends Stub {
    /* renamed from: zi */
    final /* synthetic */ C0884M f3276zi;
    /* renamed from: zj */
    final /* synthetic */ C1976c f3277zj;
    /* renamed from: zk */
    final /* synthetic */ C0897Y f3278zk;
    /* renamed from: zl */
    final /* synthetic */ boolean f3279zl;

    C1992W(C0884M c0884m, C1976c c1976c, C0897Y c0897y, boolean z) {
        this.f3276zi = c0884m;
        this.f3277zj = c1976c;
        this.f3278zk = c0897y;
        this.f3279zl = z;
    }

    public final void onFinished(EntitiesData entitiesData) {
        this.f3276zi.mo3321a(this.f3277zj, entitiesData, this.f3278zk, this.f3279zl);
    }
}
