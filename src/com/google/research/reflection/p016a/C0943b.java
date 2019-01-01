package com.google.research.reflection.p016a;

import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.research.reflection.a.b */
public class C0943b {
    /* renamed from: a */
    public static List<ReflectionEvent> m2981a(C0942a<ReflectionEvent> c0942a, ReflectionEventType reflectionEventType) {
        ArrayList arrayList = new ArrayList();
        for (int i = c0942a.f274dk - 1; i >= 0; i--) {
            ReflectionEvent reflectionEvent = (ReflectionEvent) c0942a.mo9187a(i);
            if (reflectionEvent.mo9281C() == reflectionEventType) {
                arrayList.add(reflectionEvent);
            }
        }
        return arrayList;
    }
}
