package com.android.launcher3.util;

import android.content.Context;

import com.android.launcher3.AppInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.instant.InstantAppController;
import com.android.launcher3.instant.InstantAppInfo;
import com.android.launcher3.predictions.PredictionsController;
import com.android.launcher3.shortcuts.ShortcutKey;

public class ComponentKeyMapper {

    protected ComponentKey mComponentKey;
    private Context mContext;

    public ComponentKeyMapper(Context context, ComponentKey componentKey) {
        mContext = context;
        mComponentKey = componentKey;
    }

    public String getPackage() {
        return mComponentKey.componentName.getPackageName();
    }

    public String getComponentClass() {
        return mComponentKey.componentName.getClassName();
    }

    public ComponentKey getComponentKey() {
        return mComponentKey;
    }

    public String toString() {
        return mComponentKey.toString();
    }

    public ItemInfoWithIcon getApp(AllAppsStore allAppsStore) {
        AppInfo app = allAppsStore.getApp(mComponentKey);
        if (app != null) {
            return app;
        }
        if (getComponentClass().equals("@instantapp")) {
            InstantAppController instantAppController = InstantAppController.getInstance(mContext);
            return (InstantAppInfo) instantAppController.mInstantAppMapper.get(mComponentKey.componentName.getPackageName());
        } else if (!(mComponentKey instanceof ShortcutKey)) {
            return null;
        } else {
            return (ShortcutInfo) PredictionsController.getInstance(mContext).mShortcutMapper.get((ShortcutKey) mComponentKey);
        }
    }
}
