package com.android.launcher3.backports.allapps;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.android.launcher3.Launcher;
import com.android.launcher3.graphics.BitmapRenderer;
import com.android.launcher3.graphics.DragPreviewProvider;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.c */
class C1537c extends DragPreviewProvider {
    /* renamed from: AR */
    final /* synthetic */ ActionView f2457AR;
    protected final Point mPositionShift = new Point();

    public C1537c(ActionView actionView, View view, Point point) {
		super(view, view.getContext());
        f2457AR = actionView;
        mPositionShift.set(point.x, point.y);
    }

    public final float getScaleAndPosition(Bitmap bitmap, int[] iArr) {
        Launcher launcher = Launcher.getLauncher(mView.getContext());
        launcher.mDragLayer.getLocationInDragLayer(mView, iArr);
        float f = ((float) f2457AR.mIconSize) / ((float) launcher.getDeviceProfile().iconSizePx);
        iArr[0] = (iArr[0] + mPositionShift.x) - (bitmap.getWidth() / 2);
        iArr[1] = (iArr[1] + mPositionShift.y) - bitmap.getHeight();
        return f;
    }

    public final Bitmap createDragBitmap() {
        float width;
        Rect drawableBounds = DragPreviewProvider.getDrawableBounds(f2457AR.mIcon);
        if (drawableBounds.width() > 0) {
            width = ((float) Launcher.getLauncher(mView.getContext()).getDeviceProfile().iconSizePx) / ((float) drawableBounds.width());
        } else {
            width = 1.0f;
        }
        return BitmapRenderer.createHardwareBitmap(((int) (((float) drawableBounds.width()) * width)) + blurSizeOutline, ((int) (((float) drawableBounds.height()) * width)) + blurSizeOutline, new BitmapListener(this, width));
    }
}
