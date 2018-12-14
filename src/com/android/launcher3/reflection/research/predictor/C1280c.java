package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.predictor.c */
public class C1280c extends C1258l {
    /* renamed from: ft */
    static final long f619ft = TimeUnit.MINUTES.toMillis(90);
    /* renamed from: fu */
    private final C0940c f620fu;

    public final String getName() {
        return "Location_Rule_Predictor";
    }

    public C1280c(C0940c c0940c) {
        this.f620fu = c0940c;
    }

    /* renamed from: j */
    public final C0968k mo9255j(ReflectionEvent reflectionEvent) {
        C0968k c0968k = new C0968k();
        if (!(reflectionEvent.mo9265E() == null || reflectionEvent.mo9265E().mo9280K() == null)) {
            if (reflectionEvent.mo9265E().mo9280K().mo9285N() != null) {
                long timestamp = reflectionEvent.mo9264D().getTimestamp() - reflectionEvent.mo9265E().mo9280K().getTime();
                if (timestamp >= 0) {
                    if (timestamp <= f619ft) {
                        String N = reflectionEvent.mo9265E().mo9280K().mo9285N();
                        String str = null;
                        if ("Place.TYPE_AIRPORT".equals(N)) {
                            str = this.f620fu.mo9174r();
                        } else if ("Place.TYPE_RESTAURANT".equals(N) || "Place.TYPE_CAFE".equals(N)) {
                            str = this.f620fu.mo9175s();
                        }
                        if (str != null) {
                            c0968k.f367fR.add(new C0967a(str, 1.0f, "Location_Rule_Predictor"));
                        }
                        return c0968k;
                    }
                }
                return c0968k;
            }
        }
        return c0968k;
    }
}
