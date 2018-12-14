package com.android.launcher3.instant;

import android.content.ComponentName;
import android.content.Intent;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ShortcutInfo;

public class InstantAppInfo extends AppInfo {
	
	private ComponentName mComponentName;
	private Intent mIntent;

    public InstantAppInfo(Intent intent, String str) {
        mIntent = intent;
        mComponentName = new ComponentName(str, "@instantapp");
    }

    public ComponentName getTargetComponent() {
        return mComponentName;
    }

    public ShortcutInfo makeShortcut() {
        ShortcutInfo makeShortcut = super.makeShortcut();
        makeShortcut.itemType = 0;
        makeShortcut.status = 26;
        makeShortcut.mIntent.setPackage(mComponentName.getPackageName());
        return makeShortcut;
    }
}
