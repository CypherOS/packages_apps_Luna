package com.android.launcher3.predictions;

import android.graphics.Canvas;
import com.android.launcher3.graphics.BitmapRenderer.Renderer;

public final class ActionsRenderer implements Renderer {

    private final C1450c f$0;
    private final float f$1;

    public ActionsRenderer(C1450c c1450c, float f) {
        this.f$0 = c1450c;
        this.f$1 = f;
    }

    public final void draw(Canvas canvas) {
        this.f$0.drawDragView(canvas, this.f$1);
    }
}
