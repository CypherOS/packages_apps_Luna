package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionPrivatePlace;
import com.google.research.reflection.signal.ReflectionPrivatePlace.Alias;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.b.j */
public class C1246j extends C0949f {
    /* renamed from: dE */
    public static final long f546dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9200T() {
        return 2;
    }

    public final String getFeatureName() {
        return "private_place";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1246j();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, 2);
        if (reflectionEvent.mo9283E() != null) {
            if (reflectionEvent.mo9283E().mo9297J() != null) {
                boolean z;
                ReflectionPrivatePlace J = reflectionEvent.mo9283E().mo9297J();
                if (J != null) {
                    long timestamp = reflectionEvent.mo9282D().getTimestamp() - J.getTime();
                    if (!J.mo9292M().isEmpty() && 0 <= timestamp && timestamp <= f546dE) {
                        z = true;
                        if (z) {
                            if (J.mo9292M().get(0) == Alias.WORK) {
                                c0955e.f322eR[0] = 1.0d;
                            } else if (J.mo9292M().get(0) == Alias.HOME) {
                                c0955e.f322eR[1] = 1.0d;
                            }
                        }
                        return c0955e;
                    }
                }
                z = false;
                if (z) {
                }
                return c0955e;
            }
        }
        return c0955e;
    }

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1246j();
    }
}
