package com.android.launcher3.aoscp;

import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;

import com.android.launcher3.AppFilter;
import com.android.launcher3.SettingsActivity;
import com.android.launcher3.Utilities;

import co.aoscp.lunalauncher.utils.IconPackUtils;

import java.util.HashSet;

public class LunaAppFilter extends AppFilter {

    private final HashSet<ComponentName> mHideList = new HashSet<>();

    private final Context mContext;
    private final boolean hasIconPack;

    public LunaAppFilter(Context context) {
        mContext = context;
        hasIconPack = !Utilities.getPrefs(context).getString(SettingsActivity.ICON_PACK_PREF, "").isEmpty();

        //Voice Search
        mHideList.add(ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/.VoiceSearchActivity"));

        //Wallpapers
        mHideList.add(ComponentName.unflattenFromString("com.google.android.apps.wallpaper/.picker.CategoryPickerActivity"));

        //Google Now Launcher
        mHideList.add(ComponentName.unflattenFromString("com.google.android.launcher/com.google.android.launcher.StubApp"));
    }

    @Override
    public boolean shouldShowApp(ComponentName componentName, Context context) {
		Set<String> hiddenApps = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(Utilities.KEY_HIDDEN_APPS_SET, null);
        return super.shouldShowApp(componentName, context) &&
                (!hasIconPack || !IconPackUtils.isPackProvider(mContext, componentName.getPackageName()))
                 && !mHideList.contains(componentName) && hiddenApps == null || !hiddenApps.contains(componentName.getPackageName());
    }
}
