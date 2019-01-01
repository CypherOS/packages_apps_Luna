package com.google.research.reflection.predictor;

import com.google.research.reflection.predictor.C0972k.C0971a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.research.reflection.predictor.i */
public class C0970i {
    /* renamed from: fK */
    public int f357fK = 0;
    /* renamed from: fL */
    public final List<C0968g> f358fL = new ArrayList();
    /* renamed from: fM */
    public final HashMap<C0968g, Integer> f359fM = new HashMap();

    /* renamed from: a */
    public final void mo9276a(C0968g c0968g, int i) {
        this.f358fL.add(c0968g);
        this.f359fM.put(c0968g, Integer.valueOf(i));
    }

    /* renamed from: l */
    public final C0972k mo9279l(ReflectionEvent reflectionEvent) {
        C0972k c0972k = new C0972k();
        HashMap hashMap = new HashMap();
        for (C0968g c0968g : this.f358fL) {
            ArrayList<C0971a> arrayList = c0968g.mo9273j(reflectionEvent).f367fR;
            for (C0971a c0971a : arrayList.subList(0, Math.min(arrayList.size(), ((Integer) this.f359fM.get(c0968g)).intValue()))) {
                if (hashMap.containsKey(c0971a.f363id)) {
                    ((C0971a) hashMap.get(c0971a.f363id)).f361fS.addAll(c0971a.f361fS);
                } else {
                    C0971a c0971a2 = new C0971a(c0971a.f363id, c0971a.f360ca, (Set) c0971a.f361fS);
                    c0972k.f367fR.add(c0971a2);
                    hashMap.put(c0971a.f363id, c0971a2);
                }
            }
        }
        C0967f a = new C0967f().mo9262a(this);
        Iterator it = c0972k.f367fR.iterator();
        while (it.hasNext()) {
            C0971a c0971a3 = (C0971a) it.next();
            c0971a3.f362fT = a.mo9263a(c0971a3).mo9264as();
        }
        return c0972k;
    }

    /* renamed from: a */
    public final void mo9277a(String str, String str2, Map<String, String> map) {
        for (C0968g a : this.f358fL) {
            a.mo9268a(str, str2, (Map) map);
        }
    }

    /* renamed from: c */
    public final void mo9278c(C0965b c0965b) {
        for (C0968g c : this.f358fL) {
            c.mo9270c(c0965b);
        }
    }
}
