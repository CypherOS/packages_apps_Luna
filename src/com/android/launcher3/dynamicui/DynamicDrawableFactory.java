package com.android.launcher3.dynamicui;

import android.graphics.Bitmap;
import android.content.Context;

import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.aoscp.ClockStatus;
import com.android.launcher3.aoscp.ClockStatusReceiver;
import com.android.launcher3.graphics.DrawableFactory;

public class DynamicDrawableFactory extends DrawableFactory
{
    ClockStatusReceiver mClockStatusReceiver;

    public DynamicDrawableFactory(final Context context) {
        mClockStatusReceiver = ClockStatusReceiver.getInstance(context);
    }

    public FastBitmapDrawable newIcon(final Bitmap bitmap, final ItemInfo itemInfo) {
        if (itemInfo != null && itemInfo.itemType == 0 && ClockStatusReceiver.mComponentName.equals(itemInfo.getTargetComponent())) {
            final ClockStatus clockStatus = new ClockStatus(bitmap, mClockStatusReceiver);
            clockStatus.setFilterBitmap(true);
            return clockStatus;
        }
        return super.newIcon(bitmap, itemInfo);
    }
}