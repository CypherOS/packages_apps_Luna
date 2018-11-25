package com.android.launcher3.predictions;

import android.view.View;
import com.android.launcher3.DropTarget.DragObject;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragController.DragListener;
import com.android.launcher3.dragndrop.DragOptions;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.b */
class C1449b implements DragListener {
    final /* synthetic */ DragController val$dragController;
    final /* synthetic */ View val$v;
    /* renamed from: xB */
    final /* synthetic */ ActionView f1931xB;

    C1449b(ActionView actionView, View view, DragController dragController) {
        this.f1931xB = actionView;
        this.val$v = view;
        this.val$dragController = dragController;
    }

    public final void onDragStart(DragObject dragObject, DragOptions dragOptions) {
        this.val$v.setVisibility(4);
    }

    public final void onDragEnd() {
        this.val$v.setVisibility(0);
        this.val$dragController.removeDragListener(this);
    }
}
