package com.android.launcher3.backports.p023d;

import android.content.ComponentName;
import android.content.Intent;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ShortcutInfo;
import com.android.quickstep.InstantAppResolverImpl;

/* renamed from: com.google.android.apps.nexuslauncher.d.a */
public class C1909a extends AppInfo {
    public C1909a(Intent intent, String str) {
        this.intent = intent;
        this.componentName = new ComponentName(str, InstantAppResolverImpl.COMPONENT_CLASS_MARKER);
    }

    public ComponentName getTargetComponent() {
        return this.componentName;
    }

    public final ShortcutInfo makeShortcut() {
        ShortcutInfo makeShortcut = super.makeShortcut();
        makeShortcut.itemType = 0;
        makeShortcut.status = 26;
        makeShortcut.intent.setPackage(this.componentName.getPackageName());
        return makeShortcut;
    }
}
