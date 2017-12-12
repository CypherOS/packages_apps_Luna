package com.android.launcher3.dynamicui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.graphics.DrawableFactory;
import com.android.launcher3.aoscp.ClockStatusReceiver;

public class DynamicDrawableFactory extends DrawableFactory {
	
    private final ClockStatusReceiver mClockStatusReceiver;

    public DynamicDrawableFactory(Context context) {
        mClockStatusReceiver = new ClockStatusReceiver(context);
    }

    public FastBitmapDrawable newIcon(Bitmap bitmap, ItemInfo itemInfo) {
        if (itemInfo != null && itemInfo.itemType == 0 && ClockStatusReceiver.fk.equals(itemInfo.getTargetComponent()) && itemInfo.user.equals(Process.myUserHandle())) {
            return mClockStatusReceiver.dJ(bitmap);
        }
        return super.newIcon(bitmap, itemInfo);
    }
}
