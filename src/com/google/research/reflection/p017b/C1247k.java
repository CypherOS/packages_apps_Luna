package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.predictor.C0966e;
import com.google.research.reflection.signal.C0975c;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.b.k */
public class C1247k extends C0949f {
    /* renamed from: dE */
    public static final long f547dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9200T() {
        return 125;
    }

    public final String getFeatureName() {
        return "public_place";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1247k();
    }

    /* renamed from: a */
    public final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        boolean z = true;
        C0955e c0955e = new C0955e(1, 125);
        if (reflectionEvent.mo9283E() != null) {
            if (reflectionEvent.mo9283E().mo9298K() != null) {
                C0975c K = reflectionEvent.mo9283E().mo9298K();
                if (K != null) {
                    long timestamp = reflectionEvent.mo9282D().getTimestamp() - K.getTime();
                    if (!K.mo9303N().isEmpty() && 0 <= timestamp && timestamp <= f547dE) {
                        if (z && C0966e.f353fE.containsKey(K.mo9303N())) {
                            c0955e.f322eR[((Integer) C0966e.f353fE.get(K.mo9303N())).intValue()] = 1.0d;
                        }
                        return c0955e;
                    }
                }
                z = false;
                c0955e.f322eR[((Integer) C0966e.f353fE.get(K.mo9303N())).intValue()] = 1.0d;
                return c0955e;
            }
        }
        return c0955e;
    }

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1247k();
    }
}
