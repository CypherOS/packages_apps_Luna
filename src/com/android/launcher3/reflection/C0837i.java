package com.google.android.apps.nexuslauncher.reflection;

import android.util.Log;
import com.google.research.reflection.p018c.C0947a;
import com.google.research.reflection.p018c.C0948c;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.List;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.i */
public class C0837i {
    static String TAG = "Reflection.SnsrFactory";
    /* renamed from: aH */
    static boolean f237aH;
    /* renamed from: aI */
    public static C0837i f238aI = new C0837i();

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.i$1 */
    class C08381 implements C0948c {
        C08381() {
        }

        /* renamed from: a */
        public final void mo8526a(ReflectionEvent reflectionEvent) {
            Log.d("Reflection.dbgListener", String.format("event (id: %s, type: %s, time %d, eventSrc: %s, generatedFrom: %s)", new Object[]{reflectionEvent.getId(), reflectionEvent.mo9263C().toString(), Long.valueOf(reflectionEvent.mo9264D().getTimestamp()), reflectionEvent.mo9266F().toString(), reflectionEvent.mo9268H()}));
        }
    }

    /* renamed from: a */
    static void m2669a(C0947a c0947a, List<C0948c> list) {
        for (C0948c a : list) {
            c0947a.mo9195a(a);
        }
    }
}
