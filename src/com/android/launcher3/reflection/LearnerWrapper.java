package com.google.android.apps.nexuslauncher.reflection;

import com.google.android.apps.nexuslauncher.reflection.C0834g.C0833a;
import com.google.android.apps.nexuslauncher.reflection.signal.C1214a;

public final class LearnerWrapper implements C0833a {

    private C0834g mC0834g;

    public LearnerWrapper(C0834g c0834g) {
        mC0834g = c0834g;
    }

    public final boolean shouldLearnOn(C1214a c1214a) {
        return mC0834g.m2651a(c1214a);
    }
}
