package com.google.android.apps.nexuslauncher.reflection.signal;

import com.google.android.apps.nexuslauncher.reflection.p015e.C0817b.C0820c;
import com.google.research.reflection.signal.C0969a;
import com.google.research.reflection.signal.C0970b;
import com.google.research.reflection.signal.C0971c;
import com.google.research.reflection.signal.ReflectionPrivatePlace;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.signal.c */
public class C1216c implements C0970b {
    /* renamed from: cj */
    C0820c f466cj;

    public C1216c(C0820c c0820c) {
        this.f466cj = c0820c;
    }

    public C1216c() {
        this.f466cj = new C0820c();
    }

    /* renamed from: J */
    public final ReflectionPrivatePlace mo9279J() {
        if (this.f466cj.f180cm == null) {
            return null;
        }
        return new C1217d(this.f466cj.f180cm);
    }

    /* renamed from: K */
    public final C0971c mo9280K() {
        if (this.f466cj.f181cn == null) {
            return null;
        }
        return new C1218e(this.f466cj.f181cn);
    }

    /* renamed from: L */
    public final C0969a mo9281L() {
        if (this.f466cj.f182co == null) {
            return null;
        }
        return new C1215b(this.f466cj.f182co);
    }

    /* renamed from: a */
    public final C0970b mo9283a(C0969a c0969a) {
        if (c0969a == null) {
            this.f466cj.f182co = null;
        } else {
            this.f466cj.f182co = ((C1215b) c0969a).f465dg;
        }
        return this;
    }

    /* renamed from: a */
    public final C0970b mo9282a(ReflectionPrivatePlace reflectionPrivatePlace) {
        if (reflectionPrivatePlace == null) {
            this.f466cj.f180cm = null;
        } else {
            this.f466cj.f180cm = ((C1217d) reflectionPrivatePlace).f467cm;
        }
        return this;
    }

    /* renamed from: a */
    public final C0970b mo9284a(C0971c c0971c) {
        if (c0971c == null) {
            this.f466cj.f181cn = null;
        } else {
            this.f466cj.f181cn = ((C1218e) c0971c).f468cn;
        }
        return this;
    }
}
