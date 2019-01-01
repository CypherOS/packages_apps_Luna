package com.google.research.reflection.layers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* renamed from: com.google.research.reflection.layers.e */
public class C0955e {
    public int columnCount;
    /* renamed from: eR */
    public double[] f322eR;
    public int rowCount;

    public C0955e(int i, int i2) {
        this.rowCount = i;
        this.columnCount = i2;
        this.f322eR = new double[(i * i2)];
    }

    /* renamed from: af */
    public final C0955e clone() {
        C0955e c0955e = new C0955e(this.rowCount, this.columnCount);
        for (int i = 0; i < this.f322eR.length; i++) {
            c0955e.f322eR[i] = this.f322eR[i];
        }
        return c0955e;
    }

    /* renamed from: a */
    public static C0955e m3033a(final C0955e c0955e, final C0955e c0955e2, final C0955e c0955e3, final boolean z) throws InvalidValueException {
        if (c0955e.mo9241g(false) == c0955e2.mo9241g(false) && c0955e.mo9240f(false) == c0955e2.mo9240f(false) && c0955e3.mo9241g(false) == c0955e2.mo9241g(false) && c0955e3.mo9240f(false) == c0955e2.mo9240f(false)) {
            C0963i.m3067ak().mo9254a(c0955e3.f322eR.length, new C0961h() {
                /* renamed from: b */
                public final Boolean mo9243b(int i) {
                    if (z) {
                        double[] dArr = c0955e3.f322eR;
                        dArr[i] = dArr[i] + (c0955e.f322eR[i] + c0955e2.f322eR[i]);
                    } else {
                        c0955e3.f322eR[i] = c0955e.f322eR[i] + c0955e2.f322eR[i];
                    }
                    return Boolean.valueOf(true);
                }
            });
            return c0955e3;
        }
        int f = c0955e.mo9240f(false);
        int g = c0955e.mo9241g(false);
        int f2 = c0955e2.mo9240f(false);
        int g2 = c0955e2.mo9241g(false);
        int f3 = c0955e3.mo9240f(false);
        int g3 = c0955e3.mo9241g(false);
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
    public static C0955e m3034a(C0955e c0955e, C0955e c0955e2, boolean z, C0955e c0955e3, boolean z2) throws InvalidValueException {
        if (c0955e.mo9241g(false) == c0955e2.mo9240f(z) && c0955e3.mo9240f(false) == c0955e.mo9240f(false) && c0955e3.mo9241g(false) == c0955e2.mo9241g(z)) {
            final boolean z3 = z2;
            final C0955e c0955e4 = c0955e3;
            final C0955e c0955e5 = c0955e;
            final C0955e c0955e6 = c0955e2;
            final boolean z4 = z;
            C0963i.m3067ak().mo9254a(c0955e3.f322eR.length, new C0961h() {
                /* renamed from: b */
                public final Boolean mo9243b(int i) {
                    if (z3) {
                        double[] dArr = c0955e4.f322eR;
                        dArr[i] = dArr[i] + m3049a(i / c0955e4.mo9241g(false), i % c0955e4.mo9241g(false));
                    } else {
                        c0955e4.f322eR[i] = m3049a(i / c0955e4.mo9241g(false), i % c0955e4.mo9241g(false));
                    }
                    return Boolean.valueOf(true);
                }

                /* renamed from: a */
                private double m3049a(int i, int i2) {
                    double d = 0.0d;
                    for (int i3 = 0; i3 < c0955e5.mo9241g(false); i3++) {
                        d += c0955e5.mo9235b(false, i, i3) * c0955e6.mo9235b(z4, i3, i2);
                    }
                    return d;
                }
            });
            return c0955e3;
        }
        int f = c0955e.mo9240f(false);
        int g = c0955e.mo9241g(false);
        int f2 = c0955e2.mo9240f(z);
        int g2 = c0955e2.mo9241g(z);
        int f3 = c0955e3.mo9240f(false);
        int g3 = c0955e3.mo9241g(false);
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
        for (int i = 0; i < mo9240f(false); i++) {
            for (int i2 = 0; i2 < mo9241g(false); i2++) {
                double b = mo9235b(false, i, i2);
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
    public final int mo9230a(boolean z, int i, int i2) {
        if (z) {
            return (i2 * this.columnCount) + i;
        }
        return (i * this.columnCount) + i2;
    }

    /* renamed from: b */
    public final double mo9235b(boolean z, int i, int i2) {
        int f;
        StringBuilder sb;
        if (i >= mo9240f(z)) {
            f = mo9240f(z);
            sb = new StringBuilder(41);
            sb.append("requested row: ");
            sb.append(i);
            sb.append(" >= ");
            sb.append(f);
            throw new RuntimeException(sb.toString());
        } else if (i2 < mo9241g(z)) {
            return this.f322eR[mo9230a(z, i, i2)];
        } else {
            f = mo9241g(z);
            sb = new StringBuilder(41);
            sb.append("requested col: ");
            sb.append(i2);
            sb.append(" >= ");
            sb.append(f);
            throw new RuntimeException(sb.toString());
        }
    }

    /* renamed from: a */
    public final void mo9233a(boolean z, int i, int i2, double d) {
        double[] dArr = this.f322eR;
        int a = mo9230a(z, i, i2);
        dArr[a] = dArr[a] + d;
    }

    /* renamed from: b */
    public final void mo9238b(boolean z, int i, int i2, double d) {
        this.f322eR[mo9230a(z, i, i2)] = d;
    }

    /* renamed from: b */
    public final void mo9237b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.rowCount);
        dataOutputStream.writeInt(this.columnCount);
        for (double writeDouble : this.f322eR) {
            dataOutputStream.writeDouble(writeDouble);
        }
    }

    /* renamed from: b */
    public final void mo9236b(DataInputStream dataInputStream) throws IOException {
        this.rowCount = dataInputStream.readInt();
        this.columnCount = dataInputStream.readInt();
        this.f322eR = new double[(this.rowCount * this.columnCount)];
        for (int i = 0; i < this.f322eR.length; i++) {
            this.f322eR[i] = dataInputStream.readDouble();
        }
    }

    /* renamed from: f */
    public final int mo9240f(boolean z) {
        if (z) {
            return this.columnCount;
        }
        return this.rowCount;
    }

    /* renamed from: g */
    public final int mo9241g(boolean z) {
        if (z) {
            return this.rowCount;
        }
        return this.columnCount;
    }

    /* renamed from: a */
    public static C0955e m3032a(C0955e c0955e, C0955e c0955e2) {
        if (c0955e.mo9240f(false) == c0955e2.mo9240f(false)) {
            C0955e c0955e3 = new C0955e(c0955e.mo9240f(false), c0955e.mo9241g(false) + c0955e2.mo9241g(false));
            for (int i = 0; i < c0955e3.mo9240f(false); i++) {
                for (int i2 = 0; i2 < c0955e3.mo9241g(false); i2++) {
                    if (i2 < c0955e.mo9241g(false)) {
                        c0955e3.mo9238b(false, i, i2, c0955e.mo9235b(false, i, i2));
                    } else {
                        c0955e3.mo9238b(false, i, i2, c0955e2.mo9235b(false, i, i2 - c0955e.mo9241g(false)));
                    }
                }
            }
            return c0955e3;
        }
        throw new RuntimeException();
    }

    /* renamed from: a */
    public final C0955e mo9231a(final double d) throws InvalidValueException {
        C0963i.m3067ak().mo9254a(this.f322eR.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) {
                if (C0955e.this.f322eR[i] != 0.0d) {
                    double[] dArr = C0955e.this.f322eR;
                    dArr[i] = dArr[i] * d;
                }
                return Boolean.valueOf(true);
            }
        });
        return this;
    }

    /* renamed from: a */
    public final C0955e mo9232a(final C0955e c0955e) throws InvalidValueException {
        if (mo9241g(false) == c0955e.mo9241g(false) && mo9240f(false) == c0955e.mo9240f(false)) {
            C0963i.m3067ak().mo9254a(this.f322eR.length, new C0961h() {
                /* renamed from: b */
                public final Boolean mo9243b(int i) {
                    if (c0955e.f322eR[i] != 0.0d) {
                        double[] dArr = C0955e.this.f322eR;
                        dArr[i] = dArr[i] + c0955e.f322eR[i];
                    }
                    return Boolean.valueOf(true);
                }
            });
            return this;
        }
        int f = mo9240f(false);
        int g = mo9241g(false);
        int f2 = c0955e.mo9240f(false);
        int g2 = c0955e.mo9241g(false);
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
