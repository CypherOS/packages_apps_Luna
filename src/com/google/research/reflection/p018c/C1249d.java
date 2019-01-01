package com.google.research.reflection.p018c;

import com.google.research.reflection.signal.C0974b;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.research.reflection.c.d */
public class C1249d implements C0952c {
    /* renamed from: fU */
    private final List<C0951a> f549fU = new ArrayList();
    /* renamed from: fV */
    private C0974b f550fV;

    /* JADX WARNING: Missing block: B:34:0x00a6, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final synchronized void mo8544a(ReflectionEvent reflectionEvent) {
        if (this.f550fV == null) {
            byte[] G = reflectionEvent.mo9285G();
            this.f550fV = (G != null ? reflectionEvent.mo9289a(G, G.length) : null).mo9283E();
            return;
        }
        C0974b E = reflectionEvent.mo9283E();
        if (this.f550fV.mo9297J() == null || (E.mo9297J() != null && this.f550fV.mo9297J().getTime() < E.mo9297J().getTime())) {
            this.f550fV.mo9300a(E.mo9297J());
        }
        if (this.f550fV.mo9298K() == null || (E.mo9298K() != null && this.f550fV.mo9298K().getTime() < E.mo9298K().getTime())) {
            this.f550fV.mo9302a(E.mo9298K());
        }
        if (this.f550fV.mo9299L() == null || (E.mo9299L() != null && this.f550fV.mo9299L().getTime() < E.mo9299L().getTime())) {
            this.f550fV.mo9301a(E.mo9299L());
        }
    }

    /* renamed from: au */
    public final C0974b mo14337au() {
        for (C0951a A : this.f549fU) {
            A.mo9212A();
        }
        return this.f550fV;
    }
}
