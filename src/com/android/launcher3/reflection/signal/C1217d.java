package com.android.launcher3.reflection.signal;

import com.android.launcher3.reflection.p015e.C0817b.C0821d;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace.Alias;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.signal.d */
public class C1217d implements ReflectionPrivatePlace {
    /* renamed from: cm */
    C0821d f467cm;

    public C1217d(C0821d c0821d) {
        this.f467cm = c0821d;
    }

    public C1217d() {
        this.f467cm = new C0821d();
    }

    public final long getTime() {
        return this.f467cm.time;
    }

    /* renamed from: M */
    public final List<Alias> mo9274M() {
        ArrayList arrayList = new ArrayList(this.f467cm.f183bD.length);
        for (int i : this.f467cm.f183bD) {
            arrayList.add(Alias.values()[i]);
        }
        return arrayList;
    }
}
