package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0944c;
import com.google.research.reflection.predictor.C0972k.C0971a;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.predictor.a */
public final class C1284a extends C1262l {
    /* renamed from: ft */
    static final long f616ft = TimeUnit.MINUTES.toMillis(1);
    /* renamed from: fu */
    private final C0944c f617fu;

    public final String getName() {
        return "Bluetooth_Rule_Predictor";
    }

    public C1284a(C0944c c0944c) {
        this.f617fu = c0944c;
    }

    /* JADX WARNING: Missing block: B:19:0x006f, code:
            r4v1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public final C0972k mo9273j(ReflectionEvent reflectionEvent) {
        boolean z;
        C0965b c0965b = this.f355fG;
        int i = c0965b.mo9256ar().f274dk;
        long timestamp = reflectionEvent.mo9282D().getTimestamp();
        i--;
        while (true) {
            z = false;
            if (i < 0) {
                break;
            }
            ReflectionEvent reflectionEvent2 = (ReflectionEvent) c0965b.mo9256ar().mo9187a(i);
            if (reflectionEvent2.mo9281C() == ReflectionEventType.HEADSET) {
                long timestamp2 = timestamp - reflectionEvent2.mo9282D().getTimestamp();
                if (0 > timestamp2 || timestamp2 > f616ft) {
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
        C0972k c0972k = new C0972k();
        if (z) {
            String q = this.f617fu.mo9191q();
            if (q != null) {
                c0972k.f367fR.add(new C0971a(q, 1.0f, "Bluetooth_Rule_Predictor"));
            }
        }
        return c0972k;
    }
}
