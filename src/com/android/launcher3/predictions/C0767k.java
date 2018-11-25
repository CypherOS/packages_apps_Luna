package com.android.launcher3.predictions;

import android.util.IntProperty;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.k */
class C0767k extends IntProperty {
    C0767k(String str) {
        super(str);
    }

    public /* synthetic */ Object get(Object obj) {
        return Integer.valueOf(((PredictionRowView) obj).f1927yh);
    }

    public /* synthetic */ void setValue(Object obj, int i) {
        ((PredictionRowView) obj).setTextAlpha(i);
    }
}
