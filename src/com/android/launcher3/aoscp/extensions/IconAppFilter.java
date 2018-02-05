package com.android.launcher3.aoscp.extensions;

import android.content.ComponentName;
import android.content.Context;

import com.android.launcher3.SettingsActivity;
import com.android.launcher3.Utilities;
import com.android.launcher3.aoscp.LunaAppFilter;

import co.aoscp.lunalauncher.utils.IconPackUtils;

public class IconAppFilter extends LunaAppFilter {
    private final Context mContext;
    private final boolean hasIconPack;

    public IconAppFilter(Context context) {
        super(context);
        mContext = context;
        hasIconPack = !Utilities.getPrefs(context).getString(SettingsActivity.ICON_PACK_PREF, "").isEmpty();
    }

    @Override
    public boolean shouldShowApp(ComponentName componentName) {
        return super.shouldShowApp(componentName) &&
                (!hasIconPack || !IconPackUtils.isPackProvider(mContext, componentName.getPackageName()));
    }
}