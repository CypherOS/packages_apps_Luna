package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.ISettingsCallback.Stub;
import com.google.android.apps.miphone.aiai.matchmaker.api.SettingsData;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.K */
class C1990K extends Stub {
    /* renamed from: yy */
    final /* synthetic */ C0875B f3272yy;

    C1990K(C0875B c0875b) {
        this.f3272yy = c0875b;
    }

    public final void onUpdatedSettings(SettingsData settingsData) {
        C0875B c0875b = this.f3272yy;
        C0936x.m650e(String.format("Gleams enabled: %s, Assist Enabled: %s", new Object[]{Boolean.valueOf(settingsData.f1000uw.f3254vN), Boolean.valueOf(settingsData.f1000uw.f3255vO)}));
        c0875b.f1003yk.f1000uw = settingsData.f1000uw;
    }
}
