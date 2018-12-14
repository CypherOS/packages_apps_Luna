package com.android.launcher3.reflection.p011b;

import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.b.e */
public class C0798e {
    /* renamed from: bo */
    private final int f124bo;

    public C0798e(int i) {
        this.f124bo = i;
    }

    /* renamed from: a */
    public final void mo8493a(List<C0967a> list, List<C0967a> list2) {
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            C0967a c0967a = (C0967a) it.next();
            if (c0967a.f363id.startsWith("_")) {
                i++;
                if (i > this.f124bo) {
                    it.remove();
                    if (list2 != null) {
                        list2.add(c0967a);
                    }
                }
            }
        }
    }
}
