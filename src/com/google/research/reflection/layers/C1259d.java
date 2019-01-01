package com.google.research.reflection.layers;

import com.google.research.reflection.p016a.C0945d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: com.google.research.reflection.layers.d */
public class C1259d extends C0954c {
    /* renamed from: eA */
    private C0955e f582eA;
    /* renamed from: eB */
    private C0955e f583eB;
    /* renamed from: eC */
    private C0955e f584eC;
    /* renamed from: eD */
    private C0955e f585eD;
    /* renamed from: eE */
    public C0955e f586eE;
    /* renamed from: eF */
    private C0955e f587eF;
    /* renamed from: eG */
    int f588eG;
    /* renamed from: eH */
    private int f589eH;
    /* renamed from: eI */
    private float f590eI;
    /* renamed from: ey */
    int f591ey;
    /* renamed from: ez */
    private boolean f592ez;

    /* renamed from: com.google.research.reflection.layers.d$3 */
    class C12553 implements C0961h {
        C12553() {
        }

        /* renamed from: b */
        public final Boolean mo9243b(int i) {
            int i2;
            for (int i3 = 0; i3 < C1259d.this.f312dH; i3++) {
                i2 = C1259d.this.f320ew * i3;
                C1259d.this.f582eA.mo9233a(C1259d.this.f592ez, i3, i, (-C0954c.m3018ad()) * C1259d.this.f584eC.f322eR[i2 + i]);
            }
            if (C1259d.this.f321ex) {
                for (int i4 = 0; i4 < C1259d.this.f320ew; i4++) {
                    i2 = C1259d.this.f320ew * i4;
                    double[] dArr = C1259d.this.f583eB.f322eR;
                    i2 += i;
                    dArr[i2] = dArr[i2] - (C0954c.m3018ad() * C1259d.this.f585eD.f322eR[i2]);
                }
            }
            if (Double.isNaN(C1259d.this.f587eF.f322eR[i])) {
                throw new RuntimeException("NaN in bias gradients...");
            }
            double[] dArr2 = C1259d.this.f586eE.f322eR;
            dArr2[i] = dArr2[i] - (C0954c.m3018ad() * C1259d.this.f587eF.f322eR[i]);
            return Boolean.valueOf(true);
        }
    }

    public String getName() {
        return "LinearLayer";
    }

    public C1259d(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, boolean z3, float f) {
        int i8 = i4;
        int i9 = i5;
        int i10 = i6;
        boolean z4 = z3;
        super(z, i, i3, i4, i5);
        this.f588eG = i10;
        this.f590eI = f;
        this.f586eE = new C0955e(1, i9);
        this.f591ey = i2;
        this.f584eC = new C0955e(i8, i9);
        this.f585eD = new C0955e(i9, i9);
        this.f587eF = new C0955e(1, i9);
        this.f592ez = z2;
        if (i10 < 0) {
            this.f582eA = new C0955e(i8, i9);
            C0964j.m3070a(this.f582eA, z4);
            Arrays.fill(this.f586eE.f322eR, 0.0d);
        }
        this.f589eH = i7;
        this.f583eB = new C0955e(i9, i9);
        C0964j.m3070a(this.f583eB, z4);
    }

    /* renamed from: aa */
    final void mo9220aa() {
        super.mo9220aa();
        Arrays.fill(this.f584eC.f322eR, 0.0d);
        Arrays.fill(this.f585eD.f322eR, 0.0d);
        Arrays.fill(this.f587eF.f322eR, 0.0d);
    }

    /* renamed from: a */
    public final C0955e mo14338a(C0960f c0960f) {
        if (this.f588eG >= 0) {
            return ((C1259d) c0960f.f338fb.get(this.f588eG)).f582eA;
        }
        return this.f582eA;
    }

    /* renamed from: ae */
    public C1259d clone() {
        C1259d c1259d = new C1259d();
        super.mo9218a(c1259d);
        c1259d.f592ez = this.f592ez;
        c1259d.f591ey = this.f591ey;
        c1259d.f582eA = this.f582eA.clone();
        c1259d.f584eC = this.f584eC.clone();
        c1259d.f583eB = this.f583eB.clone();
        c1259d.f585eD = this.f585eD.clone();
        c1259d.f586eE = this.f586eE.clone();
        c1259d.f587eF = this.f587eF.clone();
        c1259d.f588eG = this.f588eG;
        c1259d.f589eH = this.f589eH;
        return c1259d;
    }

    /* renamed from: a */
    public final void mo14340a(C1259d c1259d) {
        super.mo9218a(c1259d);
        c1259d.f592ez = this.f592ez;
        c1259d.f591ey = this.f591ey;
        c1259d.f582eA = this.f582eA.clone();
        c1259d.f584eC = this.f584eC.clone();
        c1259d.f583eB = this.f583eB.clone();
        c1259d.f585eD = this.f585eD.clone();
        c1259d.f586eE = this.f586eE.clone();
        c1259d.f587eF = this.f587eF.clone();
        c1259d.f588eG = this.f588eG;
        c1259d.f589eH = this.f589eH;
    }

    /* renamed from: a */
    public final void mo9219a(C0960f c0960f, int i, C0955e c0955e, C0955e c0955e2, C0955e c0955e3) throws InvalidValueException {
        C0955e.m3033a(c0955e, c0955e2, this.f316es, false);
        mo14339a(this.f591ey, this.f316es, (C0955e) this.f313ep.mo9187a(i), c0955e3);
        C0955e.m3034a(this.f316es, mo14338a(c0960f), this.f592ez ^ true, this.f317et, false);
        if (this.f321ex) {
            C0955e.m3034a(this.f316es, this.f583eB, true, this.f318eu, false);
        }
        final C0955e c0955e4 = (C0955e) this.f314eq.mo9187a(i);
        final ArrayList[] arrayListArr = (ArrayList[]) this.f315er.mo9187a(i);
        final C0955e c0955e5 = (C0955e) this.f313ep.mo9187a(i - 1);
        C0963i.m3067ak().mo9254a(this.f311dF * this.f320ew, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) {
                int i2 = i / C1259d.this.f320ew;
                i %= C1259d.this.f320ew;
                double d = C1259d.this.f316es.f322eR[(C1259d.this.f320ew * i2) + i];
                if (C1259d.this.f319ev) {
                    Iterator it = arrayListArr[i2].iterator();
                    while (it.hasNext()) {
                        C0945d c0945d = (C0945d) it.next();
                        double[] dArr = C1259d.this.f584eC.f322eR;
                        int i3 = (c0945d.index * C1259d.this.f320ew) + i;
                        dArr[i3] = dArr[i3] + (((double) c0945d.value) * d);
                    }
                } else {
                    for (int i4 = 0; i4 < C1259d.this.f312dH; i4++) {
                        double[] dArr2 = C1259d.this.f584eC.f322eR;
                        int i5 = (C1259d.this.f320ew * i4) + i;
                        dArr2[i5] = dArr2[i5] + (c0955e4.f322eR[(C1259d.this.f312dH * i2) + i4] * d);
                    }
                }
                if (C1259d.this.f321ex && c0955e5 != null) {
                    for (int i6 = 0; i6 < C1259d.this.f320ew; i6++) {
                        double[] dArr3 = C1259d.this.f585eD.f322eR;
                        int i7 = (C1259d.this.f320ew * i6) + i;
                        dArr3[i7] = dArr3[i7] + (c0955e5.f322eR[(C1259d.this.f320ew * i2) + i6] * d);
                    }
                }
                double[] dArr4 = C1259d.this.f587eF.f322eR;
                dArr4[i] = dArr4[i] + d;
                return Boolean.valueOf(true);
            }
        });
    }

    /* renamed from: a */
    void mo14339a(final int i, final C0955e c0955e, final C0955e c0955e2, C0955e c0955e3) throws InvalidValueException {
        C0963i.m3067ak().mo9254a(c0955e.f322eR.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) {
                if (i == 1) {
                    if (c0955e2.f322eR[i] == 0.0d) {
                        c0955e.f322eR[i] = 0.0d;
                    }
                } else if (i == 0) {
                    c0955e.f322eR[i] = (c0955e2.f322eR[i] * (1.0d - c0955e2.f322eR[i])) * c0955e.f322eR[i];
                } else {
                    int i2 = i;
                    StringBuilder sb = new StringBuilder(44);
                    sb.append("Unsupported activation function: ");
                    sb.append(i2);
                    throw new RuntimeException(sb.toString());
                }
                return Boolean.valueOf(true);
            }
        });
    }

    public final void update() throws InvalidValueException {
        C0963i.m3067ak().mo9254a(this.f320ew, new C12553());
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0955e mo9217a(boolean z, C0960f c0960f, ArrayList<C0945d>[] arrayListArr, C0955e c0955e) throws InvalidValueException {
        ArrayList<C0945d>[] arrayListArr2 = arrayListArr;
        C0955e c0955e2 = c0955e;
        int i = 0;
        if (arrayListArr2 != null) {
            this.f319ev = true;
            this.f315er.mo9188a((Object) arrayListArr2);
        } else {
            this.f319ev = false;
            this.f314eq.mo9188a((Object) c0955e2);
            if (!(c0955e2.mo9241g(false) == this.f312dH && c0955e2.mo9240f(false) == this.f311dF)) {
                throw new RuntimeException(String.format("Inconsistent input, input size: %d, expected input size: %d, row size: %d, expected row size: %d", new Object[]{Integer.valueOf(c0955e2.mo9241g(false)), Integer.valueOf(this.f312dH), Integer.valueOf(c0955e2.mo9240f(false)), Integer.valueOf(this.f311dF)}));
            }
        }
        final C0955e c0955e3 = this.f313ep.f275dl.length > 1 ? (C0955e) this.f313ep.getLast() : null;
        final C0955e c0955e4 = new C0955e(this.f311dF, this.f320ew);
        final C0960f c0960f2 = c0960f;
        arrayListArr2 = arrayListArr;
        c0955e2 = c0955e;
        final C0955e c0955e5 = c0955e4;
        C0963i.m3067ak().mo9254a(this.f311dF * this.f320ew, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) throws InvalidValueException {
                int i2 = i / C1259d.this.f320ew;
                i %= C1259d.this.f320ew;
                C0955e a = C1259d.this.mo14338a(c0960f2);
                int i3 = C1259d.this.f320ew * i2;
                double d = C1259d.this.f586eE.f322eR[i];
                if (C1259d.this.f319ev) {
                    Iterator it = arrayListArr2[i2].iterator();
                    while (it.hasNext()) {
                        C0945d c0945d = (C0945d) it.next();
                        d += ((double) c0945d.value) * a.mo9235b(C1259d.this.f592ez, c0945d.index, i);
                    }
                } else {
                    for (int i4 = 0; i4 < C1259d.this.f312dH; i4++) {
                        d += c0955e2.mo9235b(false, i2, i4) * a.mo9235b(C1259d.this.f592ez, i4, i);
                    }
                }
                if (C1259d.this.f321ex && c0955e3 != null) {
                    for (int i5 = 0; i5 < C1259d.this.f320ew; i5++) {
                        d += c0955e3.mo9235b(false, i2, i5) * C1259d.this.f583eB.mo9235b(false, i5, i);
                    }
                }
                c0955e5.f322eR[i3 + i] = d;
                return Boolean.valueOf(true);
            }
        });
        final C0955e c0955e6 = (C0955e) this.f313ep.mo9188a((Object) new C0955e(this.f311dF, this.f320ew));
        final int i2 = this.f591ey;
        if (i2 != 1) {
            if (i2 != 0) {
                if (i2 == 2) {
                    C0963i.m3067ak().mo9254a(c0955e4.mo9240f(false), new C0961h() {
                        /* renamed from: b */
                        public final Boolean mo9243b(int i) {
                            int i2 = i;
                            C0955e c0955e = c0955e4;
                            C0955e c0955e2 = c0955e6;
                            int g = c0955e.mo9241g(false);
                            int a = c0955e.mo9230a(false, i2, 0);
                            i2 = c0955e.mo9230a(false, i2, g);
                            double d = -1.7976931348623157E308d;
                            for (int i3 = a; i3 < i2; i3++) {
                                if (c0955e.f322eR[i3] > d) {
                                    d = c0955e.f322eR[i3];
                                }
                            }
                            double d2 = 0.0d;
                            while (a < i2) {
                                c0955e2.f322eR[a] = Math.exp(c0955e.f322eR[a] - d);
                                d2 += c0955e2.f322eR[a];
                                a++;
                            }
                            if (d2 != 0.0d) {
                                for (int i4 = 0; i4 < g; i4++) {
                                    double[] dArr = c0955e2.f322eR;
                                    dArr[i4] = dArr[i4] / d2;
                                }
                                return Boolean.valueOf(true);
                            }
                            throw new RuntimeException("softmax sum = 0");
                        }
                    });
                    if (this.f590eI > 0.0f) {
                        if (z) {
                            while (i < c0955e6.f322eR.length) {
                                if (Math.random() < ((double) this.f590eI)) {
                                    c0955e6.f322eR[i] = 0.0d;
                                }
                                i++;
                            }
                        } else {
                            while (i < c0955e6.f322eR.length) {
                                double[] dArr = c0955e6.f322eR;
                                dArr[i] = dArr[i] * ((double) (1.0f - this.f590eI));
                                i++;
                            }
                        }
                    }
                    return c0955e6;
                }
                StringBuilder sb = new StringBuilder(44);
                sb.append("Unsupported activation function: ");
                sb.append(i2);
                throw new RuntimeException(sb.toString());
            }
        }
        C0963i.m3067ak().mo9254a(c0955e4.f322eR.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) {
                if (i2 == 1) {
                    double[] dArr = c0955e6.f322eR;
                    double d = c0955e4.f322eR[i];
                    if (d <= 0.0d) {
                        d = 0.0d;
                    }
                    dArr[i] = d;
                } else {
                    c0955e6.f322eR[i] = C0964j.m3071b(c0955e4.f322eR[i]);
                }
                return Boolean.valueOf(true);
            }
        });
        if (this.f590eI > 0.0f) {
        }
        return c0955e6;
    }

    /* renamed from: b */
    public void mo9224b(DataOutputStream dataOutputStream) throws IOException {
        super.mo9224b(dataOutputStream);
        dataOutputStream.writeInt(this.f591ey);
        dataOutputStream.writeBoolean(this.f592ez);
        this.f583eB.mo9237b(dataOutputStream);
        this.f586eE.mo9237b(dataOutputStream);
        dataOutputStream.writeInt(this.f588eG);
        if (this.f588eG < 0) {
            this.f582eA.mo9237b(dataOutputStream);
        }
        dataOutputStream.writeInt(this.f589eH);
        mo9226c(dataOutputStream);
    }

    /* renamed from: b */
    public void mo9223b(DataInputStream dataInputStream) throws IOException {
        super.mo9223b(dataInputStream);
        this.f591ey = dataInputStream.readInt();
        this.f592ez = dataInputStream.readBoolean();
        this.f583eB = new C0955e();
        this.f583eB.mo9236b(dataInputStream);
        this.f586eE = new C0955e();
        this.f586eE.mo9236b(dataInputStream);
        this.f588eG = dataInputStream.readInt();
        if (this.f588eG < 0) {
            this.f582eA = new C0955e();
            this.f582eA.mo9236b(dataInputStream);
        }
        this.f584eC = new C0955e(this.f312dH, this.f320ew);
        this.f585eD = new C0955e(this.f320ew, this.f320ew);
        this.f587eF = new C0955e(1, this.f320ew);
        this.f589eH = dataInputStream.readInt();
        mo9225c(dataInputStream);
    }
}
