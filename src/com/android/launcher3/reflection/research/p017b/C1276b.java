package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0939b;
import com.android.launcher3.reflection.research.p016a.C0941d;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.android.launcher3.reflection.research.b.b */
public class C1276b extends C1236a {

    /* renamed from: com.android.launcher3.reflection.research.b.b$1 */
    class C09441 implements Comparator<ReflectionEvent> {
        C09441() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return Long.compare(((ReflectionEvent) obj2).mo9264D().getTimestamp(), ((ReflectionEvent) obj).mo9264D().getTimestamp());
        }
    }

    public final String getFeatureName() {
        return "local_app_usage_history";
    }

    public C1276b() {
        this.f541dx = 5;
    }

    public C1276b(int i) {
        super(i);
        this.f541dx = 5;
    }

    /* renamed from: V */
    public final C1276b clone() {
        C1276b c1276b = new C1276b(this.f538du);
        for (Entry entry : this.f535dr.entrySet()) {
            c1276b.f535dr.put((String) entry.getKey(), (Integer) entry.getValue());
        }
        for (Entry entry2 : this.f536ds.entrySet()) {
            c1276b.f536ds.put((Integer) entry2.getKey(), (Long) entry2.getValue());
        }
        c1276b.f537dt = Arrays.copyOf(this.f537dt, this.f537dt.length);
        c1276b.f281dA = this.f281dA;
        return c1276b;
    }

    /* renamed from: f */
    public final boolean mo14290f(ReflectionEvent reflectionEvent) {
        return reflectionEvent.mo9263C() == ReflectionEventType.APP_USAGE;
    }

    /* renamed from: a */
    protected final ArrayList<C0941d> mo14288a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent, long j, long j2, int i) {
        ArrayList<C0941d> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        List<ReflectionEvent> a = C0939b.m2966a(c0938a, ReflectionEventType.APP_USAGE);
        Collections.sort(a, new C09441());
        for (ReflectionEvent reflectionEvent2 : a) {
            if (reflectionEvent.mo9264D().getTimestamp() - reflectionEvent2.mo9264D().getTimestamp() <= j) {
                int a2 = mo14287a(reflectionEvent2.getId(), reflectionEvent.mo9264D().getTimestamp());
                C0941d c0941d = (C0941d) hashMap.get(Integer.valueOf(a2));
                if (c0941d == null) {
                    if (hashMap.size() >= i) {
                        break;
                    }
                    c0941d = new C0941d(a2);
                    hashMap.put(Integer.valueOf(a2), c0941d);
                }
                r1v11.value += 1.0f;
            }
        }
        arrayList.addAll(hashMap.values());
        return arrayList;
    }
}
