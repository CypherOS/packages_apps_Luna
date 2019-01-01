package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1989z;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.g */
class C0919g implements OnActionExpandListener {
    /* renamed from: xA */
    final /* synthetic */ C1989z f1130xA;
    /* renamed from: xB */
    final /* synthetic */ C1709f f1131xB;

    C0919g(C1709f c1709f, C1989z c1989z) {
        this.f1131xB = c1709f;
        this.f1130xA = c1989z;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        this.f1131xB.f2401xw.mo3378a(((C1989z) C0915av.m613t(this.f1130xA)).f3269vX, (C1968E) C0915av.m613t(this.f1131xB.f2403xy));
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return true;
    }
}
