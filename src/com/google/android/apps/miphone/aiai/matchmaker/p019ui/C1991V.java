package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.ContentData;
import com.google.android.apps.miphone.aiai.matchmaker.api.IContentCallback.Stub;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.V */
class C1991V extends Stub {
    /* renamed from: zg */
    final /* synthetic */ C1985t f3273zg;
    /* renamed from: zh */
    final /* synthetic */ C0896X f3274zh;
    /* renamed from: zi */
    final /* synthetic */ C0884M f3275zi;

    C1991V(C0884M c0884m, C1985t c1985t, C0896X c0896x) {
        this.f3275zi = c0884m;
        this.f3273zg = c1985t;
        this.f3274zh = c0896x;
    }

    public final void onFinished(ContentData contentData) {
        this.f3275zi.mo3320a(contentData, this.f3273zg, this.f3274zh);
    }
}
