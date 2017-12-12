package com.android.launcher3.aoscp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.FastBitmapDrawable;

import java.util.Calendar;

public class ClockStatus extends FastBitmapDrawable implements Runnable
{
    private ClockStatusReceiver mClockStatusReceiver;
    private Calendar mCalendar;

    public ClockStatus(final Bitmap bitmap) {
        super(bitmap);
        mCalendar = Calendar.getInstance();
    }

    public void draw(final Canvas canvas) {
        final long mil = 100;
        if (!(getCallback() instanceof BubbleTextView)) {
            draw(canvas);
            return;
        }
        if (!mClockStatusReceiver.getLayerDrawable()) {
            super.draw(canvas);
            return;
        }
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        final Rect bounds = getBounds();
        final Drawable layers = mClockStatusReceiver.setLayers(mCalendar);
        layers.setBounds(bounds);
        final float scale = mClockStatusReceiver.getScale();
        canvas.scale(scale, scale, bounds.exactCenterX(), bounds.exactCenterY());
        layers.draw(canvas);
        unscheduleSelf(this);
        final long uptimeMillis = SystemClock.uptimeMillis();
        scheduleSelf(this, uptimeMillis - uptimeMillis % mil + mil);
    }

    public void run() {
        invalidateSelf();
    }
}