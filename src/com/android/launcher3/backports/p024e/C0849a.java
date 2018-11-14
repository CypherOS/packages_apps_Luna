package com.android.launcher3.backports.p024e;

import android.content.Context;

import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ComponentKey;

import com.android.quickstep.InstantAppResolverImpl;

import com.google.android.apps.nexuslauncher.p020a.C0825a;
import com.google.android.apps.nexuslauncher.p023d.C0847b;

/* renamed from: com.google.android.apps.nexuslauncher.e.a */
public class C0849a {
    /* renamed from: KT */
    protected final ComponentKey f1270KT;
    private final Context mContext;

    public C0849a(Context context, ComponentKey componentKey) {
        this.mContext = context;
        this.f1270KT = componentKey;
    }

    public final String getPackage() {
        return this.f1270KT.componentName.getPackageName();
    }

    /* renamed from: fk */
    public final String mo3458fk() {
        return this.f1270KT.componentName.getClassName();
    }

    public String toString() {
        return this.f1270KT.toString();
    }

    /* renamed from: a */
    public final ItemInfoWithIcon mo3457a(AllAppsStore allAppsStore) {
        ItemInfoWithIcon app = allAppsStore.getApp(this.f1270KT);
        if (app != null) {
            return app;
        }
        if (mo3458fk().equals(InstantAppResolverImpl.COMPONENT_CLASS_MARKER)) {
            return C0847b.m679h(this.mContext).mo3455i(this.f1270KT.componentName.getPackageName());
        }
        if (!(this.f1270KT instanceof ShortcutKey)) {
            return null;
        }
        return (ShortcutInfo) C0825a.m648b(this.mContext).f1185AB.get((ShortcutKey) this.f1270KT);
    }
}
