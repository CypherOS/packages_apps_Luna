package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;

/* renamed from: com.android.launcher3.reflection.research.predictor.m */
public class C1294m extends C1281d {
    public final String getName() {
        return "shortcut_neural_predictor";
    }

    /* renamed from: k */
    public final boolean mo9256k(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9263C() == ReflectionEventType.SHORTCUTS;
    }
}
