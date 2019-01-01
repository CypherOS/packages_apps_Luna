package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.signal.C0973a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.b.i */
public class C1245i extends C0949f {
    /* renamed from: dE */
    public static final long f545dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9200T() {
        return 3;
    }

    public final String getFeatureName() {
        return "lat_lng";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1245i();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, 3);
        if (reflectionEvent.mo9283E() == null) {
            return c0955e;
        }
        boolean z;
        C0973a L = reflectionEvent.mo9283E().mo9299L();
        if (L != null) {
            long timestamp = reflectionEvent.mo9282D().getTimestamp() - L.getTime();
            if (L.getLatitude() != 0.0d && L.getLongitude() != 0.0d && 0 <= timestamp && timestamp <= f545dE) {
                z = true;
                if (z) {
                    double latitude = L.getLatitude();
                    double longitude = L.getLongitude();
                    latitude = Math.toRadians(latitude);
                    longitude = Math.toRadians(longitude);
                    double cos = Math.cos(latitude);
                    float[] fArr = new float[]{(float) (Math.cos(longitude) * cos), (float) (Math.sin(longitude) * cos), (float) Math.sin(latitude)};
                    if (c0955e.f322eR.length == 3) {
                        for (int i = 0; i < 3; i++) {
                            c0955e.f322eR[i] = (double) fArr[i];
                        }
                    } else {
                        throw new RuntimeException();
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

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1245i();
    }
}
