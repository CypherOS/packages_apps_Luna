package com.android.launcher3.dynamicui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.SystemClock;
import android.util.DisplayMetrics;

import com.android.launcher3.R;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.Utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockIconDrawable extends Drawable {
	
	private final String TAG = "ClockIconDrawable";
	
	private Drawable mBackgroundShape;
	private LayerDrawable mIcon;
	private RotateDrawable mHour;
	private RotateDrawable mMinute;
	private RotateDrawable mSecond;
	
	private final GregorianCalendar mOffset = new GregorianCalendar().getTimeZone().getRawOffset();
	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Bitmap mBackground = null;
	private Bitmap mBitmap;
	
	private float mScale = 1f;
	
	private Context mContext;
	
	public ClockIconDrawable() {
		mBackgroundShape = mContext.getDrawable(R.drawable.launcher_clock_background);
		mIcon = (LayerDrawable) mContext.getDrawable(R.drawable.launcher_clock);
		mHour = (RotateDrawable) mIcon.getDrawable(1);
		mMinute = (RotateDrawable) mIcon.getDrawable(1);
		mSecond = (RotateDrawable) mIcon.getDrawable(1);
	}

    private void updateLayers() {
        String secondOfDay = (((System.currentTimeMillis() + mOffset) % (1000 * 60 * 60 * 24)) / 1000).toInt();
        float second = secondOfDay % 60;
        float minute = (secondOfDay / 60) % 60;
        float hour = (secondOfDay / 60 / 60) % 12;

        int secondLevel = second * 10000 / 60;
        int minuteLevel = minute * 10000 / 60 + (secondLevel / 60);
        int hourLevel = hour * 10000 / 12 + (minuteLevel / 12);
        mHour = hourLevel;
        mMinute = minuteLevel;
        mSecond = secondLevel;
    }

	@Override
    public void draw(Canvas canvas) {
        updateLayers();
        canvas.drawBitmap(mBackground, 0f, 0f, mPaint);
        mIcon.draw(canvas);
        nextFrame();
    }

    @Override 
	public void onBoundsChange(Rect bounds) {
		bounds = bounds;
        updateBounds();
    }

    public void updateBounds() {
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        int inset = convertDpToPixel(-11f);

        mBackground = getBackground(width, height);
        mIcon.setBounds(inset, inset, width - inset, height - inset);
    }

    public int convertDpToPixel(float dp) {
		DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
		float px = (int)(dp * (displayMetrics.densityDpi / 160f));
        return Math.round(px);
    }

    private Bitmap getBackground(int width, int height) {
        mBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        final canvas = Canvas(mBitmap);

        int inset = convertDpToPixel(2f);

        mBackgroundShape.setBounds(inset, inset, width - inset, height - inset);
        mBackgroundShape.draw(canvas);

        return Utilities.addShadowToIcon(mBitmap, width);
    }

    @Override 
	public void setAlpha(int i) {
		// No op
    }

    @Override 
	public void setColorFilter(ColorFilter colorFilter) {
		// No op
    }

    @Override 
	public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override 
	public boolean setVisible(boolean visible, boolean restart) {
        boolean changed = super.setVisible(visible, restart);
        if (visible) {
            nextFrame();
        } else {
            unscheduleSelf(mNextFrame);
        }
        return changed;
    }

    private void nextFrame() {
        unscheduleSelf(mNextFrame);
        scheduleSelf(mNextFrame, SystemClock.uptimeMillis() + 1000);
    }

	final Runnable mNextFrame = new Runnable() {
		public void run() {
			invalidateSelf();
		}
	};

    public class Wrapper extends Drawable implements Callback {
		final Rect bounds;
        final Canvas canvas;
        final ClockIconDrawable drawable;

		Wrapper(Callback callback, Paint clearPaint) {
			callback = this;
			clearPaint = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
		}

        @Override 
		public void draw(Canvas canvas) {
            if (mBitmap == null) return;
            canvas.drawRect(bounds, clearPaint);
            drawable.draw(this.canvas);
            super.draw(canvas);
        }

        @Override 
		public void onBoundsChange(Rect bounds) {
            int width = bounds.right - bounds.left;
            int height = bounds.bottom - bounds.top;
            mBitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            canvas.setBitmap(mBitmap);
            drawable.setBounds(0, 0, width, height);
        }

        @Override 
		public int getIntrinsicWidth() {
			return mBackground.getWidth, 0;
        }

        @Override  
		public int getIntrinsicHeight() {
			return mBackground.getHeight(), 0;
        }

        @Override 
		public void unscheduleDrawable(Drawable p0, Runnable p1) {
            unscheduleSelf(p1);
        }

        @Override 
		public void invalidateDrawable(Drawable p0) {
            invalidateSelf();
        }

        @Override 
		public void scheduleDrawable(Drawable p0, Runnable p1, long p2) {
            scheduleSelf(p1, p2);
        }
    }
}