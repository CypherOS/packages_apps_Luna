package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;

/* renamed from: com.android.launcher3.reflection.research.b.d */
public class C1277d extends C1236a {
    public final String getFeatureName() {
        return "deep_link_history";
    }

    public C1277d(int i, long j, long j2, int i2) {
        super(i, j, j2, i2);
    }

    /* renamed from: W */
    public final C1277d clone() {
        C1277d c1277d = new C1277d(this.f538du, this.f539dv, this.f540dw, this.f541dx);
        c1277d.mo14289a((C1236a) this);
        return c1277d;
    }

    /* renamed from: f */
    public final boolean mo14290f(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9263C() == ReflectionEventType.SHORTCUTS;
    }
}
