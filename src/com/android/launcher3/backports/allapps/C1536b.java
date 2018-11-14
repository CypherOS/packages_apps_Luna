package com.android.launcher3.backports.allapps;

import android.view.View;
import com.android.launcher3.DropTarget.DragObject;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragController.DragListener;
import com.android.launcher3.dragndrop.DragOptions;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.b */
class C1536b implements DragListener {
    /* renamed from: AR */
    final /* synthetic */ ActionView f2456AR;
    final /* synthetic */ DragController mDragController;
    final /* synthetic */ View mView;

    C1536b(ActionView actionView, View view, DragController dragController) {
        this.f2456AR = actionView;
        this.mView = view;
        this.mDragController = dragController;
    }

    public final void onDragStart(DragObject dragObject, DragOptions dragOptions) {
        this.mView.setVisibility(4);
    }

    public final void onDragEnd() {
        this.mView.setVisibility(0);
        this.mDragController.removeDragListener(this);
    }
}
