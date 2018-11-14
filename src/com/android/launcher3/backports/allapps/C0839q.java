package com.android.launcher3.backports.allapps;

import android.util.FloatProperty;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.q */
class C0839q extends FloatProperty {
    C0839q(String str) {
        super(str);
    }

    public /* synthetic */ Object get(Object obj) {
        return Float.valueOf(((PredictionsFloatingHeader) obj).mContentAlpha);
    }
}
