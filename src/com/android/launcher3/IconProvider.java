package com.android.launcher3;

import android.content.pm.LauncherActivityInfo;
import android.graphics.drawable.Drawable;

import java.util.Locale;

public class IconProvider {

    private static final boolean DBG = false;
    private static final String TAG = "IconProvider";

    protected String mSystemState;
	private Context mContext;

    public IconProvider() {
		CustomIconProvider provider = new CustomIconProvider(context);
        if (provider != null) {
            return provider;
        }
        updateSystemStateString();
    }

    public void updateSystemStateString() {
        mSystemState = Locale.getDefault().toString();
    }

    public String getIconSystemState(String packageName) {
        return mSystemState;
    }


    public Drawable getIcon(LauncherActivityInfo info, int iconDpi) {
        return info.getIcon(iconDpi);
    }
}
