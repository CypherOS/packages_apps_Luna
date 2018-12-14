package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.predictor.C0962e;
import com.android.launcher3.reflection.research.signal.C0971c;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.b.k */
public class C1243k extends C0945f {
    /* renamed from: dE */
    public static final long f548dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9182T() {
        return 125;
    }

    public final String getFeatureName() {
        return "public_place";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1243k();
    }

    /* renamed from: a */
    public final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        boolean z = true;
        C0951e c0951e = new C0951e(1, 125);
        if (reflectionEvent.mo9265E() != null) {
            if (reflectionEvent.mo9265E().mo9280K() != null) {
                C0971c K = reflectionEvent.mo9265E().mo9280K();
                if (K != null) {
                    long timestamp = reflectionEvent.mo9264D().getTimestamp() - K.getTime();
                    if (!K.mo9285N().isEmpty() && 0 <= timestamp && timestamp <= f548dE) {
                        if (z && C0962e.f353fE.containsKey(K.mo9285N())) {
                            c0951e.f322eR[((Integer) C0962e.f353fE.get(K.mo9285N())).intValue()] = 1.0d;
                        }
                        return c0951e;
                    }
                }
                z = false;
                c0951e.f322eR[((Integer) C0962e.f353fE.get(K.mo9285N())).intValue()] = 1.0d;
                return c0951e;
            }
        }
        return c0951e;
    }

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1243k();
    }
}
