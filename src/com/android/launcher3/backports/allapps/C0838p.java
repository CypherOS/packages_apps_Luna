package com.android.launcher3.backports.allapps;

import android.util.IntProperty;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.p */
class C0838p extends IntProperty {
    C0838p(String str) {
        super(str);
    }

    public Object get(Object obj) {
        return Integer.valueOf(((PredictionRowView) obj).f2434BI);
    }

	@Override
	public void setValue(Object object, int value) {
	}
}
