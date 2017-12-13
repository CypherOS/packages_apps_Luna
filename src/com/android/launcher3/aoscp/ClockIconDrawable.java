/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3.aoscp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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
	
	public static final String TAG = "ClockIconDrawable";
	
	private int mOffest = new GregorianCalendar().getTimeZone().getRawOffset();
	private Bitmap mBackground = null;
	
	private Drawable mBackgroundShape;
	private LayerDrawable mIcon;
		
	private RotateDrawable mHour;
	private RotateDrawable mMinute;
	private RotateDrawable mSecond;
	
	private Context mContext;
	
	float mScale = 1f;	
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public ClockIconDrawable() {
		mBackgroundShape = (Drawable) mContext.getDrawable(R.drawable.launcher_clock_background);
		mIcon = (LayerDrawable) mContext.getDrawable(R.drawable.launcher_clock);
		mHour = (RotateDrawable) mIcon.getDrawable(1);
		mMinute = (RotateDrawable) mIcon.getDrawable(2);
		mSecond = (RotateDrawable) mIcon.getDrawable(3);
	}
	
	private void updateLayers() {
        long secondOfDay = (((System.currentTimeMillis() + mOffest) % (1000 * 60 * 60 * 24)) / 1000);
		int sOD = (int) secondOfDay;
        int second = sOD % 60;
        int minute = (sOD / 60) % 60;
        int hour = (sOD / 60 / 60) % 12;

        int secondLevel = second * 10000 / 60;
        int minuteLevel = minute * 10000 / 60 + (secondLevel / 60);
        int hourLevel = hour * 10000 / 12 + (minuteLevel / 12);
		
        mHour.setLevel(hourLevel);
        mMinute.setLevel(minuteLevel);
        mSecond.setLevel(secondLevel);
    }
	
	@Override 
	public void draw(Canvas canvas) {
        updateLayers();
        canvas.drawBitmap(mBackground, 0f, 0f, mPaint);
        mIcon.draw(canvas);
        nextFrame();
    }
	
	@Override
    protected void onBoundsChange(Rect bounds) {
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        int inset = convertDpToPixel(-11f);

        mBackground = getBackground(width, height);
        mIcon.setBounds(inset, inset, width - inset, height - inset);
    }
	
	public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / 160f);
        return Math.round(px);
    }
	
	public Bitmap getBackground(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        int inset = convertDpToPixel(2f);

        mBackgroundShape.setBounds(inset, inset, width - inset, height - inset);
        mBackgroundShape.draw(canvas);

        return bitmap;
    }
	
	@Override
    public void setAlpha(int alpha) {
        // No op
    }
	
	@Override
    public void setColorFilter(ColorFilter cf) {
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
	
	public static class Wrapper extends FastBitmapDrawable implements Callback {
		Canvas canvas = new Canvas();
        ClockIconDrawable drawable = new ClockIconDrawable();
        Paint clearPaint = new Paint();
		Bitmap mBitmap;
		
		public Wrapper() {
            drawable.getCallback();
            clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

		@Override 
		public void draw(Canvas canvas) {
			Rect bounds = new Rect();
            if (mBitmap == null) return;
            canvas.drawRect(bounds, clearPaint);
            drawable.draw(canvas);
            super.draw(canvas);
        }
		
		@Override
		protected void onBoundsChange(Rect bounds) {
            int width = bounds.right - bounds.left;
            int height = bounds.bottom - bounds.top;
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            canvas.setBitmap(bitmap);
            drawable.setBounds(0, 0, width, height);
        }
		
		@Override
		public int getIntrinsicWidth() {
			return mBitmap.getWidth();
		}

		@Override
		public int getIntrinsicHeight() {
			return mBitmap.getHeight();
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
