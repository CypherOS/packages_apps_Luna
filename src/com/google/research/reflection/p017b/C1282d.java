package com.google.research.reflection.p017b;

import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;

/* renamed from: com.google.research.reflection.b.d */
public class C1282d extends C1240a {
    public final String getFeatureName() {
        return "deep_link_history";
    }

    public C1282d(int i, long j, long j2, int i2) {
        super(i, j, j2, i2);
    }

    /* renamed from: W */
    public final C1282d clone() {
        ? c1282d = new C1282d(this.f537du, this.f538dv, this.f539dw, this.f540dx);
        c1282d.mo14330a((C1240a) this);
        return c1282d;
    }

    /* renamed from: f */
    public final boolean mo14331f(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9281C() == ReflectionEventType.SHORTCUTS;
    }
}
