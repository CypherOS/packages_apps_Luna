package com.google.research.reflection.p016a;

import java.util.LinkedList;

/* renamed from: com.google.research.reflection.a.a */
public class C0942a<T> {
    /* renamed from: dj */
    public int f273dj = -1;
    /* renamed from: dk */
    public int f274dk = 0;
    /* renamed from: dl */
    public Object[] f275dl;
    /* renamed from: dm */
    public LinkedList<T> f276dm;
    /* renamed from: dn */
    public int f277dn = -1;

    public C0942a(int i, boolean z) {
        if (i > 0) {
            this.f275dl = new Object[i];
            if (z) {
                this.f276dm = new LinkedList();
                return;
            }
            return;
        }
        throw new RuntimeException();
    }

    /* renamed from: Q */
    public final T mo9185Q() {
        if (this.f276dm != null) {
            if (!this.f276dm.isEmpty()) {
                return this.f276dm.removeLast();
            }
        }
        return null;
    }

    /* renamed from: R */
    public final boolean mo9186R() {
        return this.f276dm != null && this.f276dm.size() < this.f275dl.length;
    }

    /* renamed from: a */
    public final T mo9188a(T t) {
        this.f273dj++;
        if (this.f273dj == this.f275dl.length) {
            this.f273dj = 0;
        }
        if (this.f275dl[this.f273dj] != null && mo9186R()) {
            this.f276dm.add(this.f275dl[this.f273dj]);
        }
        this.f275dl[this.f273dj] = t;
        if (this.f274dk < this.f275dl.length) {
            this.f274dk++;
        }
        this.f277dn++;
        return t;
    }

    /* renamed from: a */
    public final T mo9187a(int i) {
        if (i >= 0) {
            if (i < this.f274dk) {
                i = this.f273dj - ((this.f274dk - i) - 1);
                if (i < 0) {
                    i += this.f275dl.length;
                }
                return this.f275dl[i];
            }
        }
        return null;
    }

    public final T getLast() {
        if (this.f274dk == 0) {
            return null;
        }
        return this.f275dl[this.f273dj];
    }

    public final void clear() {
        this.f273dj = -1;
        this.f277dn = -1;
        this.f274dk = 0;
    }
}
