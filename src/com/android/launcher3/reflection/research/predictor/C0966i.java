package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.launcher3.reflection.research.predictor.i */
public class C0966i {
    /* renamed from: fK */
    public int f357fK = 0;
    /* renamed from: fL */
    public final List<C0964g> f358fL = new ArrayList();
    /* renamed from: fM */
    public final HashMap<C0964g, Integer> f359fM = new HashMap();

    /* renamed from: a */
    public final void mo9258a(C0964g c0964g, int i) {
        this.f358fL.add(c0964g);
        this.f359fM.put(c0964g, Integer.valueOf(i));
    }

    /* renamed from: l */
    public final C0968k mo9261l(ReflectionEvent reflectionEvent) {
        C0968k c0968k = new C0968k();
        HashMap hashMap = new HashMap();
        for (C0964g c0964g : this.f358fL) {
            ArrayList<C0967a> arrayList = c0964g.mo9255j(reflectionEvent).f367fR;
            for (C0967a c0967a : arrayList.subList(0, Math.min(arrayList.size(), ((Integer) this.f359fM.get(c0964g)).intValue()))) {
                if (hashMap.containsKey(c0967a.f363id)) {
                    ((C0967a) hashMap.get(c0967a.f363id)).f361fS.addAll(c0967a.f361fS);
                } else {
                    C0967a c0967a2 = new C0967a(c0967a.f363id, c0967a.f360ca, (Set) c0967a.f361fS);
                    c0968k.f367fR.add(c0967a2);
                    hashMap.put(c0967a.f363id, c0967a2);
                }
            }
        }
        C0963f a = new C0963f().mo9244a(this);
        Iterator it = c0968k.f367fR.iterator();
        while (it.hasNext()) {
            C0967a c0967a3 = (C0967a) it.next();
            c0967a3.f362fT = a.mo9245a(c0967a3).mo9246as();
        }
        return c0968k;
    }

    /* renamed from: a */
    public final void mo9259a(String str, String str2, Map<String, String> map) {
        for (C0964g a : this.f358fL) {
            a.mo9250a(str, str2, (Map) map);
        }
    }

    /* renamed from: c */
    public final void mo9260c(C0961b c0961b) {
        for (C0964g c : this.f358fL) {
            c.mo9252c(c0961b);
        }
    }
}
