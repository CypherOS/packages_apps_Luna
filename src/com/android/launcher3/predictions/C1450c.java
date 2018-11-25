package com.android.launcher3.predictions;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.android.launcher3.Launcher;
import com.android.launcher3.graphics.BitmapRenderer;
import com.android.launcher3.graphics.DragPreviewProvider;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.c */
class C1450c extends DragPreviewProvider {
    protected final Point mPositionShift = new Point();
    /* renamed from: xB */
    final /* synthetic */ ActionView f1932xB;

    public C1450c(ActionView actionView, View view, Point point) {
		super(view, view.getContext());
        this.f1932xB = actionView;
        this.mPositionShift.set(point.x, point.y);
    }

    public final float getScaleAndPosition(Bitmap bitmap, int[] iArr) {
        float locationInDragLayer = Launcher.getLauncher(this.mView.getContext()).mDragLayer.getLocationInDragLayer(this.mView, iArr);
        iArr[0] = (iArr[0] + this.mPositionShift.x) - (bitmap.getWidth() / 2);
        iArr[1] = (iArr[1] + this.mPositionShift.y) - (bitmap.getHeight() / 2);
        return locationInDragLayer;
    }

    public final Bitmap createDragBitmap() {
        float width;
        Rect drawableBounds = DragPreviewProvider.getDrawableBounds(this.f1932xB.mIcon);
        if (drawableBounds.width() > 0) {
            width = ((float) Launcher.getLauncher(this.mView.getContext()).getDeviceProfile().iconSizePx) / ((float) drawableBounds.width());
        } else {
            width = 1.0f;
        }
        return BitmapRenderer.createHardwareBitmap(((int) (((float) drawableBounds.width()) * width)) + this.blurSizeOutline, ((int) (((float) drawableBounds.height()) * width)) + this.blurSizeOutline, new ActionsRenderer(this, width));
    }
}
