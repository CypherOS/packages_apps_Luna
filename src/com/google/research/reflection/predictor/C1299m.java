package com.google.research.reflection.predictor;

import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;

/* renamed from: com.google.research.reflection.predictor.m */
public class C1299m extends C1286d {
    public final String getName() {
        return "shortcut_neural_predictor";
    }

    /* renamed from: k */
    public final boolean mo9274k(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9281C() == ReflectionEventType.SHORTCUTS;
    }
}
