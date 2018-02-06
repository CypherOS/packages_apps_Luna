package com.google.android.apps.nexuslauncher;

import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;

import com.android.launcher3.SettingsActivity;
import com.android.launcher3.Utilities;

import java.util.Set;

public class CustomAppFilter extends NexusAppFilter {
	
    private final Context mContext;
    private final boolean hasIconPack;

    public CustomAppFilter(Context context) {
        super(context);
        mContext = context;
        hasIconPack = !Utilities.getPrefs(context).getString(SettingsActivity.ICON_PACK_PREF, "").isEmpty();
    }

    @Override
    public boolean shouldShowApp(ComponentName componentName) {
		Set<String> hiddenApps = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(Utilities.KEY_HIDDEN_APPS_SET, null);
        return super.shouldShowApp(componentName) &&
		        hiddenApps == null || !hiddenApps.contains(componentName.getPackageName()) &&
                (!hasIconPack || !CustomIconUtils.isPackProvider(mContext, componentName.getPackageName()))
				&& !mHideList.contains(componentName.getPackageName());
    }
}
