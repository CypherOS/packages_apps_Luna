package com.android.launcher3.reflection.research.p016a;

import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.research.a.b */
public class C0939b {
    /* renamed from: a */
    public static List<ReflectionEvent> m2966a(C0938a<ReflectionEvent> c0938a, ReflectionEventType reflectionEventType) {
        ArrayList arrayList = new ArrayList();
        for (int i = c0938a.f274dk - 1; i >= 0; i--) {
            ReflectionEvent reflectionEvent = (ReflectionEvent) c0938a.mo9169a(i);
            if (reflectionEvent.mo9263C() == reflectionEventType) {
                arrayList.add(reflectionEvent);
            }
        }
        return arrayList;
    }
}
