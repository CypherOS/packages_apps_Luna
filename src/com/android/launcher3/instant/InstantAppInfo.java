package com.android.launcher3.instant;

import android.content.ComponentName;
import android.content.Intent;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ShortcutInfo;

public class InstantAppInfo extends AppInfo {

    public InstantAppInfo(Intent intent, String str) {
        this.intent = intent;
		this.componentName = new ComponentName(str, "@instantapp");
    }

    public ComponentName getTargetComponent() {
        return this.componentName;
    }

    public ShortcutInfo makeShortcut() {
        ShortcutInfo makeShortcut = super.makeShortcut();
        makeShortcut.itemType = 0;
        makeShortcut.status = 26;
        makeShortcut.intent.setPackage(this.componentName.getPackageName());
        return makeShortcut;
    }
}
