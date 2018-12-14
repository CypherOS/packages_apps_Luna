package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.predictor.a */
public final class C1279a extends C1258l {
    /* renamed from: ft */
    static final long f617ft = TimeUnit.MINUTES.toMillis(1);
    /* renamed from: fu */
    private final C0940c f618fu;

    public final String getName() {
        return "Bluetooth_Rule_Predictor";
    }

    public C1279a(C0940c c0940c) {
        this.f618fu = c0940c;
    }

    /* JADX WARNING: Missing block: B:19:0x006f, code:
            r4v1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public final C0968k mo9255j(ReflectionEvent reflectionEvent) {
        boolean z;
        C0961b c0961b = this.f355fG;
        int i = c0961b.mo9238ar().f274dk;
        long timestamp = reflectionEvent.mo9264D().getTimestamp();
        i--;
        while (true) {
            z = false;
            if (i < 0) {
                break;
            }
            ReflectionEvent reflectionEvent2 = (ReflectionEvent) c0961b.mo9238ar().mo9169a(i);
            if (reflectionEvent2.mo9263C() == ReflectionEventType.HEADSET) {
                long timestamp2 = timestamp - reflectionEvent2.mo9264D().getTimestamp();
                if (0 > timestamp2 || timestamp2 > f617ft) {
                    break;
                } else if ("headset_wired_in".equals(reflectionEvent2.getId())) {
                    break;
                } else if ("headset_bluetooth_in".equals(reflectionEvent2.getId())) {
                    break;
                } else if ("headset_wired_out".equals(reflectionEvent2.getId())) {
                    break;
                } else if ("headset_bluetooth_out".equals(reflectionEvent2.getId())) {
                    break;
                }
            }
            i--;
        }
        C0968k c0968k = new C0968k();
        if (z) {
            String q = this.f618fu.mo9173q();
            if (q != null) {
                c0968k.f367fR.add(new C0967a(q, 1.0f, "Bluetooth_Rule_Predictor"));
            }
        }
        return c0968k;
    }
}
