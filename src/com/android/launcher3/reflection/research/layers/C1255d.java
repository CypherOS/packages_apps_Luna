package com.android.launcher3.reflection.research.layers;

import com.android.launcher3.reflection.research.p016a.C0941d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: com.android.launcher3.reflection.research.layers.d */
public class C1255d extends C0950c {
    /* renamed from: eA */
    private C0951e f583eA;
    /* renamed from: eB */
    private C0951e f584eB;
    /* renamed from: eC */
    private C0951e f585eC;
    /* renamed from: eD */
    private C0951e f586eD;
    /* renamed from: eE */
    public C0951e f587eE;
    /* renamed from: eF */
    private C0951e f588eF;
    /* renamed from: eG */
    int f589eG;
    /* renamed from: eH */
    private int f590eH;
    /* renamed from: eI */
    private float f591eI;
    /* renamed from: ey */
    int f592ey;
    /* renamed from: ez */
    private boolean f593ez;

    /* renamed from: com.android.launcher3.reflection.research.layers.d$3 */
    class C12513 implements C0957h {
        C12513() {
        }

        /* renamed from: b */
        public final Boolean mo9225b(int i) {
            int i2;
            for (int i3 = 0; i3 < C1255d.this.f312dH; i3++) {
                i2 = C1255d.this.f320ew * i3;
                C1255d.this.f583eA.mo9215a(C1255d.this.f593ez, i3, i, (-C0950c.m3003ad()) * C1255d.this.f585eC.f322eR[i2 + i]);
            }
            if (C1255d.this.f321ex) {
                for (int i4 = 0; i4 < C1255d.this.f320ew; i4++) {
                    i2 = C1255d.this.f320ew * i4;
                    double[] dArr = C1255d.this.f584eB.f322eR;
                    i2 += i;
                    dArr[i2] = dArr[i2] - (C0950c.m3003ad() * C1255d.this.f586eD.f322eR[i2]);
                }
            }
            if (Double.isNaN(C1255d.this.f588eF.f322eR[i])) {
                throw new RuntimeException("NaN in bias gradients...");
            }
            double[] dArr2 = C1255d.this.f587eE.f322eR;
            dArr2[i] = dArr2[i] - (C0950c.m3003ad() * C1255d.this.f588eF.f322eR[i]);
            return Boolean.valueOf(true);
        }
    }

    public String getName() {
        return "LinearLayer";
    }

    public C1255d(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, boolean z3, float f) {
        int i8 = i4;
        int i9 = i5;
        int i10 = i6;
        boolean z4 = z3;
        super(z, i, i3, i4, i5);
        this.f589eG = i10;
        this.f591eI = f;
        this.f587eE = new C0951e(1, i9);
        this.f592ey = i2;
        this.f585eC = new C0951e(i8, i9);
        this.f586eD = new C0951e(i9, i9);
        this.f588eF = new C0951e(1, i9);
        this.f593ez = z2;
        if (i10 < 0) {
            this.f583eA = new C0951e(i8, i9);
            C0960j.m3055a(this.f583eA, z4);
            Arrays.fill(this.f587eE.f322eR, 0.0d);
        }
        this.f590eH = i7;
        this.f584eB = new C0951e(i9, i9);
        C0960j.m3055a(this.f584eB, z4);
    }

    /* renamed from: aa */
    final void mo9202aa() {
        super.mo9202aa();
        Arrays.fill(this.f585eC.f322eR, 0.0d);
        Arrays.fill(this.f586eD.f322eR, 0.0d);
        Arrays.fill(this.f588eF.f322eR, 0.0d);
    }

    /* renamed from: a */
    public final C0951e mo14297a(C0956f c0956f) {
        if (this.f589eG >= 0) {
            return ((C1255d) c0956f.f338fb.get(this.f589eG)).f583eA;
        }
        return this.f583eA;
    }

    /* renamed from: ae */
    public C1255d clone() {
        C1255d c1255d = new C1255d();
        super.mo9200a(c1255d);
        c1255d.f593ez = this.f593ez;
        c1255d.f592ey = this.f592ey;
        c1255d.f583eA = this.f583eA.clone();
        c1255d.f585eC = this.f585eC.clone();
        c1255d.f584eB = this.f584eB.clone();
        c1255d.f586eD = this.f586eD.clone();
        c1255d.f587eE = this.f587eE.clone();
        c1255d.f588eF = this.f588eF.clone();
        c1255d.f589eG = this.f589eG;
        c1255d.f590eH = this.f590eH;
        return c1255d;
    }

    /* renamed from: a */
    public final void mo14299a(C1255d c1255d) {
        super.mo9200a(c1255d);
        c1255d.f593ez = this.f593ez;
        c1255d.f592ey = this.f592ey;
        c1255d.f583eA = this.f583eA.clone();
        c1255d.f585eC = this.f585eC.clone();
        c1255d.f584eB = this.f584eB.clone();
        c1255d.f586eD = this.f586eD.clone();
        c1255d.f587eE = this.f587eE.clone();
        c1255d.f588eF = this.f588eF.clone();
        c1255d.f589eG = this.f589eG;
        c1255d.f590eH = this.f590eH;
    }

    /* renamed from: a */
    public final void mo9201a(C0956f c0956f, int i, C0951e c0951e, C0951e c0951e2, C0951e c0951e3) throws InvalidValueException {
        C0951e.m3018a(c0951e, c0951e2, this.f316es, false);
        mo14298a(this.f592ey, this.f316es, (C0951e) this.f313ep.mo9169a(i), c0951e3);
        C0951e.m3019a(this.f316es, mo14297a(c0956f), this.f593ez ^ true, this.f317et, false);
        if (this.f321ex) {
            C0951e.m3019a(this.f316es, this.f584eB, true, this.f318eu, false);
        }
        final C0951e c0951e4 = (C0951e) this.f314eq.mo9169a(i);
        final ArrayList[] arrayListArr = (ArrayList[]) this.f315er.mo9169a(i);
        final C0951e c0951e5 = (C0951e) this.f313ep.mo9169a(i - 1);
        C0959i.m3052ak().mo9236a(this.f311dF * this.f320ew, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) {
                int i2 = i / C1255d.this.f320ew;
                i %= C1255d.this.f320ew;
                double d = C1255d.this.f316es.f322eR[(C1255d.this.f320ew * i2) + i];
                if (C1255d.this.f319ev) {
                    Iterator it = arrayListArr[i2].iterator();
                    while (it.hasNext()) {
                        C0941d c0941d = (C0941d) it.next();
                        double[] dArr = C1255d.this.f585eC.f322eR;
                        int i3 = (c0941d.index * C1255d.this.f320ew) + i;
                        dArr[i3] = dArr[i3] + (((double) c0941d.value) * d);
                    }
                } else {
                    for (int i4 = 0; i4 < C1255d.this.f312dH; i4++) {
                        double[] dArr2 = C1255d.this.f585eC.f322eR;
                        int i5 = (C1255d.this.f320ew * i4) + i;
                        dArr2[i5] = dArr2[i5] + (c0951e4.f322eR[(C1255d.this.f312dH * i2) + i4] * d);
                    }
                }
                if (C1255d.this.f321ex && c0951e5 != null) {
                    for (int i6 = 0; i6 < C1255d.this.f320ew; i6++) {
                        double[] dArr3 = C1255d.this.f586eD.f322eR;
                        int i7 = (C1255d.this.f320ew * i6) + i;
                        dArr3[i7] = dArr3[i7] + (c0951e5.f322eR[(C1255d.this.f320ew * i2) + i6] * d);
                    }
                }
                double[] dArr4 = C1255d.this.f588eF.f322eR;
                dArr4[i] = dArr4[i] + d;
                return Boolean.valueOf(true);
            }
        });
    }

    /* renamed from: a */
    void mo14298a(final int i, final C0951e c0951e, final C0951e c0951e2, C0951e c0951e3) throws InvalidValueException {
        C0959i.m3052ak().mo9236a(c0951e.f322eR.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) {
                if (i == 1) {
                    if (c0951e2.f322eR[i] == 0.0d) {
                        c0951e.f322eR[i] = 0.0d;
                    }
                } else if (i == 0) {
                    c0951e.f322eR[i] = (c0951e2.f322eR[i] * (1.0d - c0951e2.f322eR[i])) * c0951e.f322eR[i];
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
        C0959i.m3052ak().mo9236a(this.f320ew, new C12513());
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final C0951e mo9199a(boolean z, C0956f c0956f, ArrayList<C0941d>[] arrayListArr, C0951e c0951e) throws InvalidValueException {
        ArrayList<C0941d>[] arrayListArr2 = arrayListArr;
        C0951e c0951e2 = c0951e;
        int i = 0;
        if (arrayListArr2 != null) {
            this.f319ev = true;
            this.f315er.mo9170a((Object) arrayListArr2);
        } else {
            this.f319ev = false;
            this.f314eq.mo9170a((Object) c0951e2);
            if (!(c0951e2.mo9223g(false) == this.f312dH && c0951e2.mo9222f(false) == this.f311dF)) {
                throw new RuntimeException(String.format("Inconsistent input, input size: %d, expected input size: %d, row size: %d, expected row size: %d", new Object[]{Integer.valueOf(c0951e2.mo9223g(false)), Integer.valueOf(this.f312dH), Integer.valueOf(c0951e2.mo9222f(false)), Integer.valueOf(this.f311dF)}));
            }
        }
        final C0951e c0951e3 = this.f313ep.f275dl.length > 1 ? (C0951e) this.f313ep.getLast() : null;
        final C0951e c0951e4 = new C0951e(this.f311dF, this.f320ew);
        final C0956f c0956f2 = c0956f;
        arrayListArr2 = arrayListArr;
        c0951e2 = c0951e;
        final C0951e c0951e5 = c0951e4;
        C0959i.m3052ak().mo9236a(this.f311dF * this.f320ew, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) throws InvalidValueException {
                int i2 = i / C1255d.this.f320ew;
                i %= C1255d.this.f320ew;
                C0951e a = C1255d.this.mo14297a(c0956f2);
                int i3 = C1255d.this.f320ew * i2;
                double d = C1255d.this.f587eE.f322eR[i];
                if (C1255d.this.f319ev) {
                    Iterator it = arrayListArr2[i2].iterator();
                    while (it.hasNext()) {
                        C0941d c0941d = (C0941d) it.next();
                        d += ((double) c0941d.value) * a.mo9217b(C1255d.this.f593ez, c0941d.index, i);
                    }
                } else {
                    for (int i4 = 0; i4 < C1255d.this.f312dH; i4++) {
                        d += c0951e2.mo9217b(false, i2, i4) * a.mo9217b(C1255d.this.f593ez, i4, i);
                    }
                }
                if (C1255d.this.f321ex && c0951e3 != null) {
                    for (int i5 = 0; i5 < C1255d.this.f320ew; i5++) {
                        d += c0951e3.mo9217b(false, i2, i5) * C1255d.this.f584eB.mo9217b(false, i5, i);
                    }
                }
                c0951e5.f322eR[i3 + i] = d;
                return Boolean.valueOf(true);
            }
        });
        final C0951e c0951e6 = (C0951e) this.f313ep.mo9170a((Object) new C0951e(this.f311dF, this.f320ew));
        final int i2 = this.f592ey;
        if (i2 != 1) {
            if (i2 != 0) {
                if (i2 == 2) {
                    C0959i.m3052ak().mo9236a(c0951e4.mo9222f(false), new C0957h() {
                        /* renamed from: b */
                        public final Boolean mo9225b(int i) {
                            int i2 = i;
                            C0951e c0951e = c0951e4;
                            C0951e c0951e2 = c0951e6;
                            int g = c0951e.mo9223g(false);
                            int a = c0951e.mo9212a(false, i2, 0);
                            i2 = c0951e.mo9212a(false, i2, g);
                            double d = -1.7976931348623157E308d;
                            for (int i3 = a; i3 < i2; i3++) {
                                if (c0951e.f322eR[i3] > d) {
                                    d = c0951e.f322eR[i3];
                                }
                            }
                            double d2 = 0.0d;
                            while (a < i2) {
                                c0951e2.f322eR[a] = Math.exp(c0951e.f322eR[a] - d);
                                d2 += c0951e2.f322eR[a];
                                a++;
                            }
                            if (d2 != 0.0d) {
                                for (int i4 = 0; i4 < g; i4++) {
                                    double[] dArr = c0951e2.f322eR;
                                    dArr[i4] = dArr[i4] / d2;
                                }
                                return Boolean.valueOf(true);
                            }
                            throw new RuntimeException("softmax sum = 0");
                        }
                    });
                    if (this.f591eI > 0.0f) {
                        if (z) {
                            while (i < c0951e6.f322eR.length) {
                                if (Math.random() < ((double) this.f591eI)) {
                                    c0951e6.f322eR[i] = 0.0d;
                                }
                                i++;
                            }
                        } else {
                            while (i < c0951e6.f322eR.length) {
                                double[] dArr = c0951e6.f322eR;
                                dArr[i] = dArr[i] * ((double) (1.0f - this.f591eI));
                                i++;
                            }
                        }
                    }
                    return c0951e6;
                }
                StringBuilder sb = new StringBuilder(44);
                sb.append("Unsupported activation function: ");
                sb.append(i2);
                throw new RuntimeException(sb.toString());
            }
        }
        C0959i.m3052ak().mo9236a(c0951e4.f322eR.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) {
                if (i2 == 1) {
                    double[] dArr = c0951e6.f322eR;
                    double d = c0951e4.f322eR[i];
                    if (d <= 0.0d) {
                        d = 0.0d;
                    }
                    dArr[i] = d;
                } else {
                    c0951e6.f322eR[i] = C0960j.m3056b(c0951e4.f322eR[i]);
                }
                return Boolean.valueOf(true);
            }
        });
        if (this.f591eI > 0.0f) {
        }
        return c0951e6;
    }

    /* renamed from: b */
    public void mo9206b(DataOutputStream dataOutputStream) throws IOException {
        super.mo9206b(dataOutputStream);
        dataOutputStream.writeInt(this.f592ey);
        dataOutputStream.writeBoolean(this.f593ez);
        this.f584eB.mo9219b(dataOutputStream);
        this.f587eE.mo9219b(dataOutputStream);
        dataOutputStream.writeInt(this.f589eG);
        if (this.f589eG < 0) {
            this.f583eA.mo9219b(dataOutputStream);
        }
        dataOutputStream.writeInt(this.f590eH);
        mo9208c(dataOutputStream);
    }

    /* renamed from: b */
    public void mo9205b(DataInputStream dataInputStream) throws IOException {
        super.mo9205b(dataInputStream);
        this.f592ey = dataInputStream.readInt();
        this.f593ez = dataInputStream.readBoolean();
        this.f584eB = new C0951e();
        this.f584eB.mo9218b(dataInputStream);
        this.f587eE = new C0951e();
        this.f587eE.mo9218b(dataInputStream);
        this.f589eG = dataInputStream.readInt();
        if (this.f589eG < 0) {
            this.f583eA = new C0951e();
            this.f583eA.mo9218b(dataInputStream);
        }
        this.f585eC = new C0951e(this.f312dH, this.f320ew);
        this.f586eD = new C0951e(this.f320ew, this.f320ew);
        this.f588eF = new C0951e(1, this.f320ew);
        this.f590eH = dataInputStream.readInt();
        mo9207c(dataInputStream);
    }
}
