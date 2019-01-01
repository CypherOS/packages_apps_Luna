package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.view.ActionMode;
import android.view.ActionMode.Callback2;
import android.view.Menu;
import android.view.MenuItem;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.au */
class C0914au extends Callback2 {
    /* renamed from: zM */
    final /* synthetic */ C0902ah f1114zM;

    C0914au(C0902ah c0902ah) {
        this.f1114zM = c0902ah;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        synchronized (this.f1114zM) {
            if (this.f1114zM.f1090xd.mo3367as(this.f1114zM.f1097zG)) {
                this.f1114zM.f1090xd.f1124xq.mo5355a(actionMode, menu, this.f1114zM.f1090xd);
                return true;
            }
            return false;
        }
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        synchronized (this.f1114zM) {
            if (this.f1114zM.f1090xd.mo3367as(this.f1114zM.f1097zG)) {
                this.f1114zM.f1090xd.f1124xq.mo5355a(actionMode, menu, this.f1114zM.f1090xd);
                return true;
            }
            return false;
        }
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        boolean a;
        synchronized (this.f1114zM) {
            a = this.f1114zM.f1090xd.f1124xq.mo3301a(menuItem);
        }
        return a;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }
}
