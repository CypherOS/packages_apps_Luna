package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0944c;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.research.reflection.predictor.j */
public final class C1287j extends C1262l {
    /* renamed from: fN */
    private final List<C0968g> f624fN = new ArrayList();

    public final String getName() {
        return "Rule_Based_Predictor";
    }

    public C1287j(C0944c c0944c) {
        this.f624fN.add(new C1285c(c0944c));
        this.f624fN.add(new C1284a(c0944c));
    }

    /* renamed from: j */
    public final C0972k mo9273j(ReflectionEvent reflectionEvent) {
        for (C0968g j : this.f624fN) {
            C0972k j2 = j.mo9273j(reflectionEvent);
            if (j2.f367fR != null && !j2.f367fR.isEmpty()) {
                return j2;
            }
        }
        return new C0972k();
    }

    /* renamed from: c */
    public final void mo9270c(C0965b c0965b) {
        super.mo9270c(c0965b);
        for (C0968g c : this.f624fN) {
            c.mo9270c(c0965b);
        }
    }
}
