package com.google.android.apps.nexuslauncher.reflection.signal;

import com.google.android.apps.nexuslauncher.reflection.p015e.C0817b.C0819b;
import com.google.research.reflection.signal.C0969a;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.signal.b */
public class C1215b implements C0969a {
    /* renamed from: dg */
    C0819b f465dg;

    public C1215b() {
        this.f465dg = new C0819b();
    }

    public C1215b(C0819b c0819b) {
        this.f465dg = c0819b;
    }

    public final long getTime() {
        return this.f465dg.time;
    }

    public final double getLatitude() {
        return this.f465dg.f178bB;
    }

    public final double getLongitude() {
        return this.f465dg.f179bC;
    }
}
