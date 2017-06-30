package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;

import java.util.Map;

import com.android.launcher3.compat.LauncherActivityInfoCompat;

public class IconPack {

    private Map<String, String> icons = new ArrayMap<>();
    private String packageName;
    private Context mContext;


    public IconPack(Map<String, String> icons, Context context, String packageName) {
        this.icons = icons;
        this.packageName = packageName;
        mContext = context;
    }


    public Drawable getIcon(LauncherActivityInfoCompat info) {
        return getIcon(info.getComponentName());
    }

    public Drawable getIcon(ActivityInfo info) {
        return getIcon(new ComponentName(info.packageName, info.name));
    }

    public static Drawable getIcon(ComponentName name) {
        return getDrawable(icons.get(name.toString()));
    }

    private Drawable getDrawable(String name) {
        Resources res;
        try {
            res = mContext.getPackageManager().getResourcesForApplication(packageName);
            int resourceId = res.getIdentifier(name, "drawable", packageName);
            if (0 != resourceId) {
                Bitmap b = BitmapFactory.decodeResource(res, resourceId);
                return new FastBitmapDrawable(b);
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}