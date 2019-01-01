package com.google.research.reflection.p017b;

import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0943b;
import com.google.research.reflection.p016a.C0945d;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.google.research.reflection.b.b */
public class C1281b extends C1240a {

    /* renamed from: com.google.research.reflection.b.b$1 */
    class C09481 implements Comparator<ReflectionEvent> {
        C09481() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return Long.compare(((ReflectionEvent) obj2).mo9282D().getTimestamp(), ((ReflectionEvent) obj).mo9282D().getTimestamp());
        }
    }

    public final String getFeatureName() {
        return "local_app_usage_history";
    }

    public C1281b() {
        this.f540dx = 5;
    }

    public C1281b(int i) {
        super(i);
        this.f540dx = 5;
    }

    /* renamed from: V */
    public final C1281b clone() {
        C1281b c1281b = new C1281b(this.f537du);
        for (Entry entry : this.f534dr.entrySet()) {
            c1281b.f534dr.put((String) entry.getKey(), (Integer) entry.getValue());
        }
        for (Entry entry2 : this.f535ds.entrySet()) {
            c1281b.f535ds.put((Integer) entry2.getKey(), (Long) entry2.getValue());
        }
        c1281b.f536dt = Arrays.copyOf(this.f536dt, this.f536dt.length);
        c1281b.f281dA = this.f281dA;
        return c1281b;
    }

    /* renamed from: f */
    public final boolean mo14331f(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9281C() == ReflectionEventType.APP_USAGE;
    }

    /* renamed from: a */
    protected final ArrayList<C0945d> mo14329a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent, long j, long j2, int i) {
        ArrayList<C0945d> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        List<ReflectionEvent> a = C0943b.m2981a(c0942a, ReflectionEventType.APP_USAGE);
        Collections.sort(a, new C09481());
        for (ReflectionEvent reflectionEvent2 : a) {
            if (reflectionEvent.mo9282D().getTimestamp() - reflectionEvent2.mo9282D().getTimestamp() <= j) {
                int a2 = mo14328a(reflectionEvent2.getId(), reflectionEvent.mo9282D().getTimestamp());
                C0945d c0945d = (C0945d) hashMap.get(Integer.valueOf(a2));
                if (c0945d == null) {
                    if (hashMap.size() >= i) {
                        break;
                    }
                    c0945d = new C0945d(a2);
                    hashMap.put(Integer.valueOf(a2), c0945d);
                }
                r1v11.value += 1.0f;
            }
        }
        arrayList.addAll(hashMap.values());
        return arrayList;
    }
}
