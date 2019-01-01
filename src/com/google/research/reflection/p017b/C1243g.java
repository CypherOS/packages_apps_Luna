package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0943b;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.List;

/* renamed from: com.google.research.reflection.b.g */
public class C1243g extends C0949f {
    /* renamed from: dC */
    private boolean f543dC = true;
    /* renamed from: dD */
    private boolean f544dD = true;

    /* renamed from: T */
    public final int mo9200T() {
        return 4;
    }

    public final String getFeatureName() {
        return "headset";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1243g();
    }

    public C1243g() {
        this.f282dB.clear();
        this.f282dB.put("headset_wired", Boolean.valueOf(true));
        this.f282dB.put("headset_bluetooth", Boolean.valueOf(true));
    }

    /* renamed from: a */
    protected final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, 4);
        List<ReflectionEvent> a = C0943b.m2981a(c0942a, ReflectionEventType.HEADSET);
        this.f543dC = true;
        this.f544dD = true;
        for (ReflectionEvent reflectionEvent2 : a) {
            if (reflectionEvent.mo9282D().getTimestamp() - reflectionEvent2.mo9282D().getTimestamp() < 600000) {
                if (reflectionEvent2.getId().equals("headset_wired_in")) {
                    c0955e.f322eR[0] = 1.0d;
                    this.f543dC = false;
                } else if (reflectionEvent2.getId().equals("headset_wired_out")) {
                    c0955e.f322eR[1] = 1.0d;
                    this.f543dC = false;
                } else if (reflectionEvent2.getId().equals("headset_bluetooth_in")) {
                    c0955e.f322eR[2] = 1.0d;
                    this.f544dD = false;
                } else if (reflectionEvent2.getId().equals("headset_bluetooth_out")) {
                    c0955e.f322eR[3] = 1.0d;
                    this.f544dD = false;
                }
            }
        }
        return c0955e;
    }

    /* renamed from: U */
    public final C0949f mo9201U() {
        return new C1243g();
    }

    /* renamed from: e */
    protected final void mo9208e(boolean z) {
        this.f282dB.put("headset_wired", Boolean.valueOf(this.f543dC));
        this.f282dB.put("headset_bluetooth", Boolean.valueOf(this.f544dD));
    }
}
