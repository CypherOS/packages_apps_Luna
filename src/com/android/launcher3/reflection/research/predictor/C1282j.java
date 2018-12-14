package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.research.predictor.j */
public final class C1282j extends C1258l {
    /* renamed from: fN */
    private final List<C0964g> f625fN = new ArrayList();

    public final String getName() {
        return "Rule_Based_Predictor";
    }

    public C1282j(C0940c c0940c) {
        this.f625fN.add(new C1280c(c0940c));
        this.f625fN.add(new C1279a(c0940c));
    }

    /* renamed from: j */
    public final C0968k mo9255j(ReflectionEvent reflectionEvent) {
        for (C0964g j : this.f625fN) {
            C0968k j2 = j.mo9255j(reflectionEvent);
            if (j2.f367fR != null && !j2.f367fR.isEmpty()) {
                return j2;
            }
        }
        return new C0968k();
    }

    /* renamed from: c */
    public final void mo9252c(C0961b c0961b) {
        super.mo9252c(c0961b);
        for (C0964g c : this.f625fN) {
            c.mo9252c(c0961b);
        }
    }
}
