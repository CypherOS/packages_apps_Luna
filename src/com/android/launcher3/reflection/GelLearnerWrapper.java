package com.google.android.apps.nexuslauncher.reflection;

import com.google.android.apps.nexuslauncher.reflection.C0834g.C0833a;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0792e;
import com.google.android.apps.nexuslauncher.reflection.signal.C1214a;
import com.google.research.reflection.signal.ReflectionEvent;

public final class GelLearnerWrapper implements C0833a {

    public static final GelLearnerWrapper INSTANCE = new GelLearnerWrapper();

    private GelLearnerWrapper() {
    }

    public final boolean shouldLearnOn(C1214a c1214a) {
        return C0792e.m2565b((ReflectionEvent) c1214a);
    }
}
