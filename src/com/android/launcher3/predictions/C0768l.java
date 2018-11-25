package com.android.launcher3.predictions;

import android.util.FloatProperty;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.l */
class C0768l extends FloatProperty {
    C0768l(String str) {
        super(str);
    }

    public /* synthetic */ Object get(Object obj) {
        return Float.valueOf(((PredictionsFloatingHeader) obj).mContentAlpha);
    }

    public /* synthetic */ void setValue(Object obj, float f) {
        PredictionsFloatingHeader.m4671a((PredictionsFloatingHeader) obj, f);
    }
}
