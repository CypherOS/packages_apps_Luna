package com.android.launcher3.predictions.backport;

import android.content.Context;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ComponentKey;
import com.android.quickstep.InstantAppResolverImpl;

/* renamed from: com.google.android.apps.nexuslauncher.e.a */
public class C0783a {
    /* renamed from: Gs */
    protected final ComponentKey f1042Gs;
    private final Context mContext;

    public C0783a(Context context, ComponentKey componentKey) {
        this.mContext = context;
        this.f1042Gs = componentKey;
    }

    public final String getPackage() {
        return this.f1042Gs.componentName.getPackageName();
    }

    /* renamed from: eZ */
    public final String mo3138eZ() {
        return this.f1042Gs.componentName.getClassName();
    }

    public String toString() {
        return this.f1042Gs.toString();
    }

    /* renamed from: a */
    public final ItemInfoWithIcon mo3137a(AllAppsStore allAppsStore) {
        AppInfo app = allAppsStore.getApp(this.f1042Gs);
        if (app != null) {
            return app;
        }
        if (mo3138eZ().equals(InstantAppResolverImpl.COMPONENT_CLASS_MARKER)) {
            return C0781b.m2482f(this.mContext).mo3135h(this.f1042Gs.componentName.getPackageName());
        }
        if (!(this.f1042Gs instanceof ShortcutKey)) {
            return null;
        }
        return (ShortcutInfo) C0755a.m2422a(this.mContext).f971xn.get((ShortcutKey) this.f1042Gs);
    }
}
