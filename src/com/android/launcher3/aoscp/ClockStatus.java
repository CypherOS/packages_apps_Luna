package com.android.launcher3.aoscp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import com.android.launcher3.FastBitmapDrawable;
import java.util.TimeZone;

public class ClockStatus extends FastBitmapDrawable implements Runnable {
    private b fa;

    public ClockStatus(Bitmap bitmap, b bVar) {
        super(bitmap);
        this.fa = bVar;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.fa != null) {
            this.fa.fb.setBounds(rect);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.fa != null) {
            this.fa.dG();
            Rect bounds = getBounds();
            canvas.scale(this.fa.scale, this.fa.scale, bounds.exactCenterX(), bounds.exactCenterY());
            this.fa.fb.draw(canvas);
            dD();
        }
    }

    public void run() {
        if (this.fa.dG()) {
            invalidateSelf();
        } else {
            dD();
        }
    }

    private void dD() {
        unscheduleSelf(this);
        long uptimeMillis = SystemClock.uptimeMillis();
        scheduleSelf(this, (uptimeMillis - (uptimeMillis % 1000)) + 1000);
    }

    public void dE(b bVar) {
        this.fa = bVar;
        if (this.fa != null) {
            this.fa.fb.setBounds(getBounds());
        }
        invalidateSelf();
    }

    public void dF(TimeZone timeZone) {
        if (this.fa != null) {
            this.fa.dH(timeZone);
            invalidateSelf();
        }
    }
}
