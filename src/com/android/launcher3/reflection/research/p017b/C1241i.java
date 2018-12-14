package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.signal.C0969a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.b.i */
public class C1241i extends C0945f {
    /* renamed from: dE */
    public static final long f546dE = TimeUnit.HOURS.toMillis(1);

    /* renamed from: T */
    public final int mo9182T() {
        return 3;
    }

    public final String getFeatureName() {
        return "lat_lng";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1241i();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, 3);
        if (reflectionEvent.mo9265E() == null) {
            return c0951e;
        }
        boolean z;
        C0969a L = reflectionEvent.mo9265E().mo9281L();
        if (L != null) {
            long timestamp = reflectionEvent.mo9264D().getTimestamp() - L.getTime();
            if (L.getLatitude() != 0.0d && L.getLongitude() != 0.0d && 0 <= timestamp && timestamp <= f546dE) {
                z = true;
                if (z) {
                    double latitude = L.getLatitude();
                    double longitude = L.getLongitude();
                    latitude = Math.toRadians(latitude);
                    longitude = Math.toRadians(longitude);
                    double cos = Math.cos(latitude);
                    float[] fArr = new float[]{(float) (Math.cos(longitude) * cos), (float) (Math.sin(longitude) * cos), (float) Math.sin(latitude)};
                    if (c0951e.f322eR.length == 3) {
                        for (int i = 0; i < 3; i++) {
                            c0951e.f322eR[i] = (double) fArr[i];
                        }
                    } else {
                        throw new RuntimeException();
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

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1241i();
    }
}
