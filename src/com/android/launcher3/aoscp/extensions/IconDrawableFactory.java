package com.android.launcher3.extensions.aoscp;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.SettingsActivity;
import com.android.launcher3.Utilities;
import com.android.launcher3.aoscp.LunaDrawableFactory;

public class CustomDrawableFactory extends LunaDrawableFactory {
    private final boolean hasIconPack;

    public CustomDrawableFactory(Context context) {
        super(context);
        hasIconPack = !Utilities.getPrefs(context).getString(SettingsActivity.ICON_PACK_PREF, "").isEmpty();
    }

    @Override
    public FastBitmapDrawable newIcon(Bitmap icon, ItemInfo info) {
        if (hasIconPack) {
            return new FastBitmapDrawable(icon);
        }
        return super.newIcon(icon, info);
    }
}