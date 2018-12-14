package com.android.launcher3.reflection.research.layers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* renamed from: com.android.launcher3.reflection.research.layers.e */
public class C0951e {
    public int columnCount;
    /* renamed from: eR */
    public double[] f322eR;
    public int rowCount;

    public C0951e(int i, int i2) {
        this.rowCount = i;
        this.columnCount = i2;
        this.f322eR = new double[(i * i2)];
    }

    /* renamed from: af */
    public final C0951e clone() {
        C0951e c0951e = new C0951e(this.rowCount, this.columnCount);
        for (int i = 0; i < this.f322eR.length; i++) {
            c0951e.f322eR[i] = this.f322eR[i];
        }
        return c0951e;
    }

    /* renamed from: a */
    public static C0951e m3018a(final C0951e c0951e, final C0951e c0951e2, final C0951e c0951e3, final boolean z) throws InvalidValueException {
        if (c0951e.mo9223g(false) == c0951e2.mo9223g(false) && c0951e.mo9222f(false) == c0951e2.mo9222f(false) && c0951e3.mo9223g(false) == c0951e2.mo9223g(false) && c0951e3.mo9222f(false) == c0951e2.mo9222f(false)) {
            C0959i.m3052ak().mo9236a(c0951e3.f322eR.length, new C0957h() {
                /* renamed from: b */
                public final Boolean mo9225b(int i) {
                    if (z) {
                        double[] dArr = c0951e3.f322eR;
                        dArr[i] = dArr[i] + (c0951e.f322eR[i] + c0951e2.f322eR[i]);
                    } else {
                        c0951e3.f322eR[i] = c0951e.f322eR[i] + c0951e2.f322eR[i];
                    }
                    return Boolean.valueOf(true);
                }
            });
            return c0951e3;
        }
        int f = c0951e.mo9222f(false);
        int g = c0951e.mo9223g(false);
        int f2 = c0951e2.mo9222f(false);
        int g2 = c0951e2.mo9223g(false);
        int f3 = c0951e3.mo9222f(false);
        int g3 = c0951e3.mo9223g(false);
        StringBuilder sb = new StringBuilder(71);
        sb.append(f);
        sb.append("x");
        sb.append(g);
        sb.append(" ");
        sb.append(f2);
        sb.append("x");
        sb.append(g2);
        sb.append(" ");
        sb.append(f3);
        sb.append("x");
        sb.append(g3);
        throw new RuntimeException(sb.toString());
    }

    /* renamed from: a */
    public static C0951e m3019a(C0951e c0951e, C0951e c0951e2, boolean z, C0951e c0951e3, boolean z2) throws InvalidValueException {
        if (c0951e.mo9223g(false) == c0951e2.mo9222f(z) && c0951e3.mo9222f(false) == c0951e.mo9222f(false) && c0951e3.mo9223g(false) == c0951e2.mo9223g(z)) {
            final boolean z3 = z2;
            final C0951e c0951e4 = c0951e3;
            final C0951e c0951e5 = c0951e;
            final C0951e c0951e6 = c0951e2;
            final boolean z4 = z;
            C0959i.m3052ak().mo9236a(c0951e3.f322eR.length, new C0957h() {
                /* renamed from: b */
                public final Boolean mo9225b(int i) {
                    if (z3) {
                        double[] dArr = c0951e4.f322eR;
                        dArr[i] = dArr[i] + m3034a(i / c0951e4.mo9223g(false), i % c0951e4.mo9223g(false));
                    } else {
                        c0951e4.f322eR[i] = m3034a(i / c0951e4.mo9223g(false), i % c0951e4.mo9223g(false));
                    }
                    return Boolean.valueOf(true);
                }

                /* renamed from: a */
                private double m3034a(int i, int i2) {
                    double d = 0.0d;
                    for (int i3 = 0; i3 < c0951e5.mo9223g(false); i3++) {
                        d += c0951e5.mo9217b(false, i, i3) * c0951e6.mo9217b(z4, i3, i2);
                    }
                    return d;
                }
            });
            return c0951e3;
        }
        int f = c0951e.mo9222f(false);
        int g = c0951e.mo9223g(false);
        int f2 = c0951e2.mo9222f(z);
        int g2 = c0951e2.mo9223g(z);
        int f3 = c0951e3.mo9222f(false);
        int g3 = c0951e3.mo9223g(false);
        StringBuilder sb = new StringBuilder(71);
        sb.append(f);
        sb.append("x");
        sb.append(g);
        sb.append(" ");
        sb.append(f2);
        sb.append("x");
        sb.append(g2);
        sb.append(" ");
        sb.append(f3);
        sb.append("x");
        sb.append(g3);
        throw new RuntimeException(sb.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mo9222f(false); i++) {
            for (int i2 = 0; i2 < mo9223g(false); i2++) {
                double b = mo9217b(false, i, i2);
                StringBuilder sb2 = new StringBuilder(25);
                sb2.append(b);
                sb2.append(" ");
                sb.append(sb2.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final int mo9212a(boolean z, int i, int i2) {
        if (z) {
            return (i2 * this.columnCount) + i;
        }
        return (i * this.columnCount) + i2;
    }

    /* renamed from: b */
    public final double mo9217b(boolean z, int i, int i2) {
        int f;
        StringBuilder sb;
        if (i >= mo9222f(z)) {
            f = mo9222f(z);
            sb = new StringBuilder(41);
            sb.append("requested row: ");
            sb.append(i);
            sb.append(" >= ");
            sb.append(f);
            throw new RuntimeException(sb.toString());
        } else if (i2 < mo9223g(z)) {
            return this.f322eR[mo9212a(z, i, i2)];
        } else {
            f = mo9223g(z);
            sb = new StringBuilder(41);
            sb.append("requested col: ");
            sb.append(i2);
            sb.append(" >= ");
            sb.append(f);
            throw new RuntimeException(sb.toString());
        }
    }

    /* renamed from: a */
    public final void mo9215a(boolean z, int i, int i2, double d) {
        double[] dArr = this.f322eR;
        int a = mo9212a(z, i, i2);
        dArr[a] = dArr[a] + d;
    }

    /* renamed from: b */
    public final void mo9220b(boolean z, int i, int i2, double d) {
        this.f322eR[mo9212a(z, i, i2)] = d;
    }

    /* renamed from: b */
    public final void mo9219b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.rowCount);
        dataOutputStream.writeInt(this.columnCount);
        for (double writeDouble : this.f322eR) {
            dataOutputStream.writeDouble(writeDouble);
        }
    }

    /* renamed from: b */
    public final void mo9218b(DataInputStream dataInputStream) throws IOException {
        this.rowCount = dataInputStream.readInt();
        this.columnCount = dataInputStream.readInt();
        this.f322eR = new double[(this.rowCount * this.columnCount)];
        for (int i = 0; i < this.f322eR.length; i++) {
            this.f322eR[i] = dataInputStream.readDouble();
        }
    }

    /* renamed from: f */
    public final int mo9222f(boolean z) {
        if (z) {
            return this.columnCount;
        }
        return this.rowCount;
    }

    /* renamed from: g */
    public final int mo9223g(boolean z) {
        if (z) {
            return this.rowCount;
        }
        return this.columnCount;
    }

    /* renamed from: a */
    public static C0951e m3017a(C0951e c0951e, C0951e c0951e2) {
        if (c0951e.mo9222f(false) == c0951e2.mo9222f(false)) {
            C0951e c0951e3 = new C0951e(c0951e.mo9222f(false), c0951e.mo9223g(false) + c0951e2.mo9223g(false));
            for (int i = 0; i < c0951e3.mo9222f(false); i++) {
                for (int i2 = 0; i2 < c0951e3.mo9223g(false); i2++) {
                    if (i2 < c0951e.mo9223g(false)) {
                        c0951e3.mo9220b(false, i, i2, c0951e.mo9217b(false, i, i2));
                    } else {
                        c0951e3.mo9220b(false, i, i2, c0951e2.mo9217b(false, i, i2 - c0951e.mo9223g(false)));
                    }
                }
            }
            return c0951e3;
        }
        throw new RuntimeException();
    }

    /* renamed from: a */
    public final C0951e mo9213a(final double d) throws InvalidValueException {
        C0959i.m3052ak().mo9236a(this.f322eR.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) {
                if (C0951e.this.f322eR[i] != 0.0d) {
                    double[] dArr = C0951e.this.f322eR;
                    dArr[i] = dArr[i] * d;
                }
                return Boolean.valueOf(true);
            }
        });
        return this;
    }

    /* renamed from: a */
    public final C0951e mo9214a(final C0951e c0951e) throws InvalidValueException {
        if (mo9223g(false) == c0951e.mo9223g(false) && mo9222f(false) == c0951e.mo9222f(false)) {
            C0959i.m3052ak().mo9236a(this.f322eR.length, new C0957h() {
                /* renamed from: b */
                public final Boolean mo9225b(int i) {
                    if (c0951e.f322eR[i] != 0.0d) {
                        double[] dArr = C0951e.this.f322eR;
                        dArr[i] = dArr[i] + c0951e.f322eR[i];
                    }
                    return Boolean.valueOf(true);
                }
            });
            return this;
        }
        int f = mo9222f(false);
        int g = mo9223g(false);
        int f2 = c0951e.mo9222f(false);
        int g2 = c0951e.mo9223g(false);
        StringBuilder sb = new StringBuilder(47);
        sb.append(f);
        sb.append("x");
        sb.append(g);
        sb.append(" ");
        sb.append(f2);
        sb.append("x");
        sb.append(g2);
        throw new RuntimeException(sb.toString());
    }
}
