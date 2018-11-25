package com.android.launcher3.predictions.backport;

import android.content.ComponentName;
import android.content.Intent;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ShortcutInfo;
import com.android.quickstep.InstantAppResolverImpl;

/* renamed from: com.google.android.apps.nexuslauncher.d.a */
public class C1807a extends AppInfo {
    public C1807a(Intent intent, String str) {
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
