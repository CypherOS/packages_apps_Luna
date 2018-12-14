package com.android.launcher3.reflection;

import com.android.launcher3.reflection.C0834g.C0833a;
import com.android.launcher3.reflection.p010a.C0792e;
import com.android.launcher3.reflection.signal.C1214a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;

public final class GelLearnerWrapper implements C0833a {

    public static final GelLearnerWrapper INSTANCE = new GelLearnerWrapper();

    private GelLearnerWrapper() {
    }

    public final boolean shouldLearnOn(C1214a c1214a) {
        return C0792e.m2565b((ReflectionEvent) c1214a);
    }
}
