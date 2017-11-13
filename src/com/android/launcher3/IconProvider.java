package com.android.launcher3;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.graphics.drawable.Drawable;

import java.util.Locale;

import com.android.launcher3.pixel.DynamicIconProvider;

public class IconProvider {

    private static final boolean DBG = false;
    private static final String TAG = "IconProvider";

    protected String mSystemState;
	private Context mContext;

    public IconProvider() {
        updateSystemStateString();
    }
	
	public static IconProvider loadByName(String className, Context context) {
        DynamicIconProvider provider = new DynamicIconProvider(context);
        if (provider != null) {
            return provider;
        }
        if (TextUtils.isEmpty(className)) return new IconProvider();
        if (DBG) Log.d(TAG, "Loading IconProvider: " + className);
        try {
            Class<?> cls = Class.forName(className);
            return (IconProvider) cls.getDeclaredConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | ClassCastException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Bad IconProvider class", e);
            return new IconProvider();
        }
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
