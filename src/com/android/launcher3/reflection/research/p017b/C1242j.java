package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace.Alias;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.b.j */
public class C1242j extends C0945f {
    /* renamed from: dE */
    public static final long f547dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9182T() {
        return 2;
    }

    public final String getFeatureName() {
        return "private_place";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1242j();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, 2);
        if (reflectionEvent.mo9265E() != null) {
            if (reflectionEvent.mo9265E().mo9279J() != null) {
                boolean z;
                ReflectionPrivatePlace J = reflectionEvent.mo9265E().mo9279J();
                if (J != null) {
                    long timestamp = reflectionEvent.mo9264D().getTimestamp() - J.getTime();
                    if (!J.mo9274M().isEmpty() && 0 <= timestamp && timestamp <= f547dE) {
                        z = true;
                        if (z) {
                            if (J.mo9274M().get(0) == Alias.WORK) {
                                c0951e.f322eR[0] = 1.0d;
                            } else if (J.mo9274M().get(0) == Alias.HOME) {
                                c0951e.f322eR[1] = 1.0d;
                            }
                        }
                        return c0951e;
                    }
                }
                z = false;
                if (z) {
                }
                return c0951e;
            }
        }
        return c0951e;
    }

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1242j();
    }
}
