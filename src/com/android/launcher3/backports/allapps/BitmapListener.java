package com.android.launcher3.backports.allapps;

import android.graphics.Canvas;
import com.android.launcher3.graphics.BitmapRenderer.Renderer;

public final class BitmapListener implements Renderer {
    private final C1537c f$0;
    private final float f$1;

    public BitmapListener(C1537c c1537c, float f) {
        this.f$0 = c1537c;
        this.f$1 = f;
    }

    public final void draw(Canvas canvas) {
        this.f$0.createDragBitmap(canvas, this.f$1);
    }
}
