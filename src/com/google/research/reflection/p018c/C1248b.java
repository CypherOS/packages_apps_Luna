package com.google.research.reflection.p018c;

import com.google.research.reflection.predictor.C0965b;
import com.google.research.reflection.signal.ReflectionEvent;

/* renamed from: com.google.research.reflection.c.b */
public class C1248b implements C0952c {
    /* renamed from: fG */
    private C0965b f548fG;

    public C1248b(C0965b c0965b) {
        mo14335c(c0965b);
    }

    /* renamed from: a */
    public final synchronized void mo8544a(ReflectionEvent reflectionEvent) {
        mo14336c(reflectionEvent);
    }

    /* renamed from: c */
    public final synchronized void mo14336c(ReflectionEvent reflectionEvent) {
        this.f548fG.mo9257c(reflectionEvent);
    }

    /* renamed from: c */
    public final synchronized void mo14335c(C0965b c0965b) {
        this.f548fG = c0965b;
    }
}
