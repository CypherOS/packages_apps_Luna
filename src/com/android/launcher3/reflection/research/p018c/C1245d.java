package com.android.launcher3.reflection.research.p018c;

import com.android.launcher3.reflection.research.signal.C0970b;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.research.c.d */
public class C1245d implements C0948c {
    /* renamed from: fU */
    private final List<C0947a> f550fU = new ArrayList();
    /* renamed from: fV */
    private C0970b f551fV;

    /* JADX WARNING: Missing block: B:34:0x00a6, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final synchronized void mo8526a(ReflectionEvent reflectionEvent) {
        if (this.f551fV == null) {
            byte[] G = reflectionEvent.mo9267G();
            this.f551fV = (G != null ? reflectionEvent.mo9271a(G, G.length) : null).mo9265E();
            return;
        }
        C0970b E = reflectionEvent.mo9265E();
        if (this.f551fV.mo9279J() == null || (E.mo9279J() != null && this.f551fV.mo9279J().getTime() < E.mo9279J().getTime())) {
            this.f551fV.mo9282a(E.mo9279J());
        }
        if (this.f551fV.mo9280K() == null || (E.mo9280K() != null && this.f551fV.mo9280K().getTime() < E.mo9280K().getTime())) {
            this.f551fV.mo9284a(E.mo9280K());
        }
        if (this.f551fV.mo9281L() == null || (E.mo9281L() != null && this.f551fV.mo9281L().getTime() < E.mo9281L().getTime())) {
            this.f551fV.mo9283a(E.mo9281L());
        }
    }

    /* renamed from: au */
    public final C0970b mo14296au() {
        for (C0947a A : this.f550fU) {
            A.mo9194A();
        }
        return this.f551fV;
    }
}
