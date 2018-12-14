package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0939b;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.research.b.g */
public class C1239g extends C0945f {
    /* renamed from: dC */
    private boolean f544dC = true;
    /* renamed from: dD */
    private boolean f545dD = true;

    /* renamed from: T */
    public final int mo9182T() {
        return 4;
    }

    public final String getFeatureName() {
        return "headset";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1239g();
    }

    public C1239g() {
        this.f282dB.clear();
        this.f282dB.put("headset_wired", Boolean.valueOf(true));
        this.f282dB.put("headset_bluetooth", Boolean.valueOf(true));
    }

    /* renamed from: a */
    protected final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, 4);
        List<ReflectionEvent> a = C0939b.m2966a(c0938a, ReflectionEventType.HEADSET);
        this.f544dC = true;
        this.f545dD = true;
        for (ReflectionEvent reflectionEvent2 : a) {
            if (reflectionEvent.mo9264D().getTimestamp() - reflectionEvent2.mo9264D().getTimestamp() < 600000) {
                if (reflectionEvent2.getId().equals("headset_wired_in")) {
                    c0951e.f322eR[0] = 1.0d;
                    this.f544dC = false;
                } else if (reflectionEvent2.getId().equals("headset_wired_out")) {
                    c0951e.f322eR[1] = 1.0d;
                    this.f544dC = false;
                } else if (reflectionEvent2.getId().equals("headset_bluetooth_in")) {
                    c0951e.f322eR[2] = 1.0d;
                    this.f545dD = false;
                } else if (reflectionEvent2.getId().equals("headset_bluetooth_out")) {
                    c0951e.f322eR[3] = 1.0d;
                    this.f545dD = false;
                }
            }
        }
        return c0951e;
    }

    /* renamed from: U */
    public final C0945f mo9183U() {
        return new C1239g();
    }

    /* renamed from: e */
    protected final void mo9190e(boolean z) {
        this.f282dB.put("headset_wired", Boolean.valueOf(this.f544dC));
        this.f282dB.put("headset_bluetooth", Boolean.valueOf(this.f545dD));
    }
}
