package com.android.launcher3.reflection.signal;

import com.android.launcher3.reflection.p015e.C0817b.C0823f;
import com.android.launcher3.reflection.research.signal.C0972d;

/* renamed from: com.android.launcher3.reflection.signal.f */
public class C1219f implements C0972d {
    /* renamed from: dh */
    public C0823f f469dh;

    public C1219f(C0823f c0823f) {
        this.f469dh = c0823f;
    }

    public C1219f() {
        this.f469dh = new C0823f();
    }

    public final long getTimestamp() {
        return this.f469dh.timestamp;
    }

    /* renamed from: e */
    public final C0972d mo14098e(long j) {
        this.f469dh.timestamp = j;
        return this;
    }

    /* renamed from: g */
    public final long mo9289g() {
        return this.f469dh.f185br;
    }

    /* renamed from: f */
    public final C0972d mo14099f(long j) {
        this.f469dh.f185br = j;
        return this;
    }

    /* renamed from: O */
    public final long mo9287O() {
        return this.f469dh.f186bs;
    }

    /* renamed from: g */
    public final C0972d mo14100g(long j) {
        this.f469dh.f186bs = j;
        return this;
    }

    public final String getTimeZone() {
        return this.f469dh.f187bt;
    }

    /* renamed from: i */
    public final C0972d mo14102i(String str) {
        this.f469dh.f187bt = str;
        return this;
    }

    /* renamed from: P */
    public final long mo9288P() {
        return this.f469dh.f188bu;
    }

    /* renamed from: h */
    public final C0972d mo14101h(long j) {
        this.f469dh.f188bu = j;
        return this;
    }
}
