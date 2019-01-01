package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0944c;
import com.google.research.reflection.predictor.C0972k.C0971a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.predictor.c */
public class C1285c extends C1262l {
    /* renamed from: ft */
    static final long f618ft = TimeUnit.MINUTES.toMillis(90);
    /* renamed from: fu */
    private final C0944c f619fu;

    public final String getName() {
        return "Location_Rule_Predictor";
    }

    public C1285c(C0944c c0944c) {
        this.f619fu = c0944c;
    }

    /* renamed from: j */
    public final C0972k mo9273j(ReflectionEvent reflectionEvent) {
        C0972k c0972k = new C0972k();
        if (!(reflectionEvent.mo9283E() == null || reflectionEvent.mo9283E().mo9298K() == null)) {
            if (reflectionEvent.mo9283E().mo9298K().mo9303N() != null) {
                long timestamp = reflectionEvent.mo9282D().getTimestamp() - reflectionEvent.mo9283E().mo9298K().getTime();
                if (timestamp >= 0) {
                    if (timestamp <= f618ft) {
                        String N = reflectionEvent.mo9283E().mo9298K().mo9303N();
                        String str = null;
                        if ("Place.TYPE_AIRPORT".equals(N)) {
                            str = this.f619fu.mo9192r();
                        } else if ("Place.TYPE_RESTAURANT".equals(N) || "Place.TYPE_CAFE".equals(N)) {
                            str = this.f619fu.mo9193s();
                        }
                        if (str != null) {
                            c0972k.f367fR.add(new C0971a(str, 1.0f, "Location_Rule_Predictor"));
                        }
                        return c0972k;
                    }
                }
                return c0972k;
            }
        }
        return c0972k;
    }
}
