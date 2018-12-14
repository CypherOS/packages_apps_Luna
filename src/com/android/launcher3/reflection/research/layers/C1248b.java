package com.android.launcher3.reflection.research.layers;

import com.android.launcher3.reflection.research.p016a.C0941d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: com.android.launcher3.reflection.research.layers.b */
public class C1248b extends C0950c {
    /* renamed from: eg */
    C0949a[] f562eg;
    /* renamed from: eh */
    int f563eh;

    /* renamed from: Z */
    public final C0950c mo9198Z() {
        return null;
    }

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final String getName() {
        return "LSTMLayer";
    }

    C1248b() {
    }

    /* renamed from: a */
    public final void mo9201a(C0956f c0956f, int i, C0951e c0951e, C0951e c0951e2, C0951e c0951e3) throws InvalidValueException {
        C0951e.m3018a(c0951e, c0951e2, this.f316es, false);
        final C0951e c0951e4 = (C0951e) this.f314eq.mo9169a(i);
        final ArrayList[] arrayListArr = (ArrayList[]) this.f315er.mo9169a(i);
        final C0951e c0951e5 = (C0951e) this.f313ep.mo9169a(i - 1);
        final int i2 = i;
        C0959i.m3052ak().mo9236a(this.f562eg.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) throws InvalidValueException {
                int i2;
                int i3;
                double b;
                int i4;
                double b2;
                double d;
                C0951e c0951e = new C0951e(C1248b.this.f311dF, C1248b.this.f563eh);
                int i5 = i * C1248b.this.f563eh;
                for (i2 = 0; i2 < C1248b.this.f311dF; i2++) {
                    for (i3 = 0; i3 < C1248b.this.f563eh; i3++) {
                        c0951e.mo9220b(false, i2, i3, C1248b.this.f316es.mo9217b(false, i2, i5 + i3));
                    }
                }
                C0949a c0949a = C1248b.this.f562eg[i];
                int i6 = i2;
                ArrayList[] arrayListArr = arrayListArr;
                C0951e c0951e2 = c0951e4;
                C0951e c0951e3 = c0951e5;
                C0951e c0951e4 = (C0951e) c0949a.f287dJ.mo9169a(i6);
                C0951e c0951e5 = new C0951e(c0949a.f283dF, 1);
                for (i5 = 0; i5 < c0949a.f283dF; i5++) {
                    double d2 = 0.0d;
                    for (i2 = 0; i2 < c0949a.f284dG; i2++) {
                        d2 += c0951e4.mo9217b(false, i5, i2) * c0951e.mo9217b(false, i5, i2);
                    }
                    double b3 = ((C0951e) c0949a.f290dM.mo9169a(i6)).mo9217b(false, i5, 0);
                    c0951e5.mo9220b(false, i5, 0, ((1.0d - b3) * b3) * d2);
                }
                C0951e c0951e6 = new C0951e(c0949a.f283dF, c0949a.f284dG);
                C0951e c0951e7 = (C0951e) c0949a.f291dN.mo9169a(i6 + 1);
                C0951e c0951e8 = (C0951e) c0949a.f288dK.mo9169a(i6);
                int i7 = 0;
                while (i7 < c0949a.f283dF) {
                    b = ((C0951e) c0949a.f290dM.mo9169a(i6)).mo9217b(false, i7, 0);
                    double b4 = c0951e7 != null ? c0951e7.mo9217b(false, i7, 0) : 0.0d;
                    for (i4 = 0; i4 < c0949a.f284dG; i4++) {
                        double b5 = c0951e4.mo9217b(false, i7, i4);
                        b5 = ((1.0d - (b5 * b5)) * b) * c0951e.mo9217b(false, i7, i4);
                        if (c0949a.f293dP[0] != null) {
                            i3 = 2;
                            b5 = ((b5 + (c0949a.f293dP[0].mo9217b(false, i7, i4) * b4)) + (c0949a.f296dS.mo9217b(false, i4, 0) * c0949a.f293dP[1].mo9217b(false, i7, 0))) + (c0949a.f296dS.mo9217b(false, i4, 1) * c0949a.f293dP[2].mo9217b(false, i7, 0));
                        } else {
                            i3 = 2;
                        }
                        c0951e6.mo9220b(false, i7, i4, b5 + (c0949a.f296dS.mo9217b(false, i4, i3) * c0951e5.mo9217b(false, i7, 0)));
                    }
                    i7++;
                }
                c0951e4 = (C0951e) c0949a.f292dO.mo9169a(i6);
                c0951e = (C0951e) c0949a.f289dL.mo9169a(i6);
                c0951e7 = new C0951e(c0949a.f283dF, c0949a.f284dG);
                for (i3 = 0; i3 < c0949a.f283dF; i3++) {
                    b2 = c0951e4.mo9217b(false, i3, 0);
                    for (int i8 = 0; i8 < c0949a.f284dG; i8++) {
                        b = c0951e.mo9217b(false, i3, i8);
                        c0951e7.mo9220b(false, i3, i8, ((1.0d - (b * b)) * b2) * c0951e6.mo9217b(false, i3, i8));
                    }
                }
                C0951e c0951e9 = new C0951e(c0949a.f283dF, 1);
                c0951e4 = (C0951e) c0949a.f291dN.mo9169a(i6);
                C0951e c0951e10 = (C0951e) c0949a.f288dK.mo9169a(i6 - 1);
                for (i3 = 0; i3 < c0949a.f283dF; i3++) {
                    b2 = c0951e4.mo9217b(false, i3, 0);
                    b = (1.0d - b2) * b2;
                    i4 = 0;
                    d = 0.0d;
                    while (c0951e10 != null && i4 < c0949a.f284dG) {
                        d += c0951e10.mo9217b(false, i3, i4) * c0951e6.mo9217b(false, i3, i4);
                        i4++;
                    }
                    c0951e9.mo9220b(false, i3, 0, d * b);
                }
                c0951e4 = new C0951e(c0949a.f283dF, 1);
                C0951e c0951e11 = (C0951e) c0949a.f292dO.mo9169a(i6);
                for (i3 = 0; i3 < c0949a.f283dF; i3++) {
                    b2 = c0951e11.mo9217b(false, i3, 0);
                    b = (1.0d - b2) * b2;
                    d = 0.0d;
                    for (i4 = 0; i4 < c0949a.f284dG; i4++) {
                        d += c0951e.mo9217b(false, i3, i4) * c0951e6.mo9217b(false, i3, i4);
                    }
                    c0951e4.mo9220b(false, i3, 0, d * b);
                }
                c0949a.f293dP = new C0951e[]{c0951e6, c0951e4, c0951e9, c0951e7, c0951e5};
                c0951e9 = C0951e.m3017a(C0951e.m3017a(C0951e.m3017a(c0951e4, c0951e9), c0951e5), c0951e7);
                C0951e.m3019a(c0951e9, C0951e.m3017a(c0949a.f294dQ, c0949a.f299dV), true, c0949a.f308ee, false);
                C0951e.m3019a(c0951e9, C0951e.m3017a(c0949a.f295dR, c0949a.f298dU), true, c0949a.f309ef, false);
                C0949a c0949a2 = c0949a;
                c0951e = c0951e10;
                c0949a2.mo9197a(false, c0949a.f301dX, c0949a.f285dH, c0949a.f283dF, c0949a.f293dP, arrayListArr, c0951e2);
                c0949a2.mo9197a(false, c0949a.f302dY, c0949a.f286dI, c0949a.f283dF, c0949a.f293dP, null, c0951e3);
                C0951e c0951e12 = c0951e;
                C0949a.m2997a(false, c0949a.f303dZ, c0949a.f293dP[1], 0, null, c0951e12, c0949a.f284dG, 0, c0949a.f283dF);
                C0949a.m2997a(false, c0949a.f303dZ, c0949a.f293dP[2], 0, null, c0951e12, c0949a.f284dG, 1, c0949a.f283dF);
                C0949a.m2997a(false, c0949a.f303dZ, c0949a.f293dP[4], 0, null, c0951e8, c0949a.f284dG, 2, c0949a.f283dF);
                c0949a.mo9197a(true, c0949a.f304ea, 1, c0949a.f283dF, c0949a.f293dP, null, null);
                for (int i9 = 0; i9 < c0949a.f284dG; i9++) {
                    i7 = i9;
                    int i10 = i9;
                    C0949a.m2997a(false, c0949a.f306ec, c0949a.f293dP[3], i7, arrayListArr, c0951e2, c0949a.f285dH, i10, c0949a.f283dF);
                    C0949a.m2997a(false, c0949a.f305eb, c0949a.f293dP[3], i7, null, c0951e3, c0949a.f286dI, i10, c0949a.f283dF);
                    C0949a.m2997a(true, c0949a.f307ed, c0949a.f293dP[3], i9, null, null, 1, i9, c0949a.f283dF);
                }
                return Boolean.valueOf(true);
            }
        });
        Arrays.fill(this.f317et.f322eR, 0.0d);
        for (C0949a c0949a : this.f562eg) {
            this.f317et.mo9214a(c0949a.f308ee);
        }
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        for (C0949a c0949a2 : this.f562eg) {
            this.f318eu.mo9214a(c0949a2.f309ef);
        }
    }

    /* renamed from: aa */
    final void mo9202aa() {
        super.mo9202aa();
        for (C0949a c0949a : this.f562eg) {
            c0949a.f293dP = new C0951e[5];
            Arrays.fill(c0949a.f307ed.f322eR, 0.0d);
            Arrays.fill(c0949a.f305eb.f322eR, 0.0d);
            Arrays.fill(c0949a.f306ec.f322eR, 0.0d);
            Arrays.fill(c0949a.f303dZ.f322eR, 0.0d);
            Arrays.fill(c0949a.f302dY.f322eR, 0.0d);
            Arrays.fill(c0949a.f301dX.f322eR, 0.0d);
            Arrays.fill(c0949a.f304ea.f322eR, 0.0d);
        }
    }

    /* renamed from: ab */
    public final void mo9203ab() {
        super.mo9203ab();
        for (C0949a c0949a : this.f562eg) {
            c0949a.f288dK.clear();
            c0949a.f287dJ.clear();
            c0949a.f289dL.clear();
            c0949a.f290dM.clear();
            c0949a.f291dN.clear();
            c0949a.f292dO.clear();
        }
    }

    /* renamed from: a */
    public final C0951e mo9199a(boolean z, C0956f c0956f, ArrayList<C0941d>[] arrayListArr, C0951e c0951e) throws InvalidValueException {
        if (arrayListArr != null) {
            this.f319ev = true;
            this.f315er.mo9170a((Object) arrayListArr);
        } else {
            this.f319ev = false;
            this.f314eq.mo9170a((Object) c0951e);
        }
        final C0951e c0951e2 = this.f313ep.f275dl.length > 1 ? (C0951e) this.f313ep.getLast() : null;
        Object obj = (C0951e) this.f313ep.mo9167Q();
        if (obj == null) {
            obj = new C0951e(this.f311dF, this.f320ew);
        }
        C0951e c0951e3 = (C0951e) this.f313ep.mo9170a(obj);
        final ArrayList<C0941d>[] arrayListArr2 = arrayListArr;
        final C0951e c0951e4 = c0951e;
        final C0951e c0951e5 = c0951e3;
        C0959i.m3052ak().mo9236a(this.f562eg.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) throws InvalidValueException {
                int i2;
                C0951e c0951e;
                int i3;
                C0951e c0951e2 = new C0951e(C1248b.this.f311dF, C1248b.this.f563eh);
                C0949a c0949a = C1248b.this.f562eg[i];
                ArrayList[] arrayListArr = arrayListArr2;
                C0951e c0951e3 = c0951e4;
                C0951e c0951e4 = c0951e2;
                Object obj = (C0951e) c0949a.f290dM.mo9167Q();
                if (obj == null) {
                    obj = new C0951e(c0949a.f283dF, 1);
                }
                C0951e c0951e5 = (C0951e) c0949a.f290dM.mo9170a(obj);
                obj = (C0951e) c0949a.f291dN.mo9167Q();
                if (obj == null) {
                    obj = new C0951e(c0949a.f283dF, 1);
                }
                C0951e c0951e6 = (C0951e) c0949a.f291dN.mo9170a(obj);
                obj = (C0951e) c0949a.f292dO.mo9167Q();
                if (obj == null) {
                    obj = new C0951e(c0949a.f283dF, 1);
                }
                C0951e c0951e7 = (C0951e) c0949a.f292dO.mo9170a(obj);
                C0951e c0951e8 = (C0951e) c0949a.f288dK.getLast();
                Object obj2 = (C0951e) c0949a.f287dJ.mo9167Q();
                if (obj2 == null) {
                    obj2 = new C0951e(c0949a.f283dF, c0949a.f284dG);
                }
                C0951e c0951e9 = (C0951e) c0949a.f287dJ.mo9170a(obj2);
                obj2 = (C0951e) c0949a.f288dK.mo9167Q();
                if (obj2 == null) {
                    obj2 = new C0951e(c0949a.f283dF, c0949a.f284dG);
                }
                C0951e c0951e10 = (C0951e) c0949a.f288dK.mo9170a(obj2);
                obj2 = (C0951e) c0949a.f289dL.mo9167Q();
                if (obj2 == null) {
                    obj2 = new C0951e(c0949a.f283dF, c0949a.f284dG);
                }
                C0951e c0951e11 = (C0951e) c0949a.f289dL.mo9170a(obj2);
                int i4 = 0;
                while (i4 < c0949a.f283dF) {
                    double d;
                    C0951e c0951e12;
                    C0951e c0951e13;
                    ArrayList[] arrayListArr2;
                    double d2;
                    double d3;
                    int i5;
                    C0951e c0951e14;
                    C0949a c0949a2;
                    int i6 = i4;
                    C0951e c0951e15 = c0951e10;
                    double b = C0960j.m3056b(c0949a.mo9196a(i4, arrayListArr, c0951e3, c0951e4, c0951e8, 0) + c0949a.f297dT.f322eR[0]);
                    double d4 = b;
                    c0951e7.mo9220b(false, i6, 0, b);
                    b = C0960j.m3056b(c0949a.mo9196a(i6, arrayListArr, c0951e3, c0951e4, c0951e8, 1) + c0949a.f297dT.f322eR[1]);
                    double d5 = b;
                    c0951e6.mo9220b(false, i6, 0, b);
                    i2 = 0;
                    while (i2 < c0949a.f284dG) {
                        int i7;
                        C0951e c0951e16;
                        boolean z;
                        int i8;
                        double d6;
                        double d7;
                        d = c0949a.f300dW.f322eR[i2];
                        if (arrayListArr != null) {
                            i7 = i6;
                            for (Iterator it = arrayListArr[i7].iterator(); it.hasNext(); it = it) {
                                C0941d c0941d = (C0941d) it.next();
                                d += ((double) c0941d.value) * c0949a.f299dV.mo9217b(false, c0941d.index, i2);
                                c0951e4 = c0951e4;
                            }
                            c0951e16 = c0951e4;
                            z = false;
                        } else {
                            c0951e16 = c0951e4;
                            i7 = i6;
                            z = false;
                            for (i8 = 0; i8 < c0949a.f285dH; i8++) {
                                d += c0951e3.mo9217b(false, i7, i8) * c0949a.f299dV.mo9217b(false, i8, i2);
                            }
                        }
                        i8 = 0;
                        while (c0951e16 != null && i8 < c0949a.f286dI) {
                            d += c0951e16.mo9217b(z, i7, i8) * c0949a.f298dU.mo9217b(z, i8, i2);
                            i8++;
                            z = false;
                        }
                        c0951e4 = c0951e16;
                        double tanh = Math.tanh(d);
                        c0951e11.mo9220b(false, i7, i2, tanh);
                        double d8 = d4;
                        double d9 = d8 * tanh;
                        if (c0951e8 != null) {
                            d6 = d8;
                            d7 = tanh;
                            d = d5;
                            d9 += c0951e8.mo9217b(false, i7, i2) * d;
                        } else {
                            d6 = d8;
                            d7 = tanh;
                            d = d5;
                        }
                        double d10 = d9;
                        c0951e12 = c0951e8;
                        double d11 = d6;
                        c0951e13 = c0951e4;
                        arrayListArr2 = arrayListArr;
                        c0951e = c0951e2;
                        d2 = d;
                        d3 = d7;
                        i5 = i7;
                        c0951e15.mo9220b(false, i7, i2, d10);
                        if (Double.isNaN(d10)) {
                            StringBuilder sb = new StringBuilder(78);
                            sb.append(d11);
                            sb.append(" x ");
                            sb.append(d3);
                            sb.append(" + ");
                            sb.append(d2);
                            throw new InvalidValueException(sb.toString());
                        }
                        i2++;
                        d4 = d11;
                        d5 = d2;
                        c0951e8 = c0951e12;
                        c0951e4 = c0951e13;
                        c0951e2 = c0951e;
                        arrayListArr = arrayListArr2;
                        i6 = i5;
                    }
                    c0951e12 = c0951e8;
                    c0951e13 = c0951e4;
                    arrayListArr2 = arrayListArr;
                    c0951e = c0951e2;
                    i5 = i6;
                    d3 = C0960j.m3056b(c0949a.mo9196a(i5, arrayListArr2, c0951e3, c0951e13, c0951e15, 2) + c0949a.f297dT.f322eR[2]);
                    c0951e5.mo9220b(false, i5, 0, d3);
                    i3 = 0;
                    while (i3 < c0949a.f284dG) {
                        C0951e c0951e17 = c0951e15;
                        int i9 = i5;
                        double tanh2 = Math.tanh(c0951e17.mo9217b(false, i9, i3));
                        double d12 = tanh2;
                        c0951e9.mo9220b(false, i9, i3, tanh2);
                        d = d12;
                        b = d3 * d;
                        c0951e10 = c0951e12;
                        int i10 = i9;
                        C0951e c0951e18 = c0951e13;
                        c0951e14 = c0951e3;
                        c0949a2 = c0949a;
                        ArrayList[] arrayListArr3 = arrayListArr2;
                        double d13 = d3;
                        c0951e.mo9220b(false, i9, i3, b);
                        if (Double.isNaN(b)) {
                            d2 = c0951e17.mo9217b(false, i10, i3);
                            StringBuilder sb2 = new StringBuilder(82);
                            sb2.append(d13);
                            sb2.append(" x ");
                            sb2.append(d);
                            sb2.append("=tanh(");
                            sb2.append(d2);
                            sb2.append(")");
                            throw new InvalidValueException(sb2.toString());
                        }
                        i3++;
                        c0951e15 = c0951e17;
                        c0951e13 = c0951e18;
                        c0951e12 = c0951e10;
                        c0951e3 = c0951e14;
                        c0949a = c0949a2;
                        arrayListArr2 = arrayListArr3;
                        i5 = i10;
                        d3 = d13;
                    }
                    c0951e14 = c0951e3;
                    c0949a2 = c0949a;
                    i4 = i5 + 1;
                    c0951e4 = c0951e13;
                    c0951e8 = c0951e12;
                    arrayListArr = arrayListArr2;
                    c0951e2 = c0951e;
                    c0951e10 = c0951e15;
                }
                c0951e = c0951e2;
                i3 = C1248b.this.f563eh * i;
                for (i2 = 0; i2 < C1248b.this.f311dF; i2++) {
                    for (int i11 = 0; i11 < C1248b.this.f563eh; i11++) {
                        c0951e5.mo9220b(false, i2, i3 + i11, c0951e.mo9217b(false, i2, i11));
                    }
                    C0951e c0951e19 = c0951e;
                }
                return Boolean.valueOf(true);
            }
        });
        return c0951e3;
    }

    public final void update() throws InvalidValueException {
        for (C0949a c0949a : this.f562eg) {
            c0949a.f294dQ.mo9214a(c0949a.f301dX.mo9213a(-C0950c.f310eo));
            c0949a.f295dR.mo9214a(c0949a.f302dY.mo9213a(-C0950c.f310eo));
            c0949a.f296dS.mo9214a(c0949a.f303dZ.mo9213a(-C0950c.f310eo));
            c0949a.f297dT.mo9214a(c0949a.f304ea.mo9213a(-C0950c.f310eo));
            c0949a.f298dU.mo9214a(c0949a.f305eb.mo9213a(-C0950c.f310eo));
            c0949a.f299dV.mo9214a(c0949a.f306ec.mo9213a(-C0950c.f310eo));
            c0949a.f300dW.mo9214a(c0949a.f307ed.mo9213a(-C0950c.f310eo));
        }
    }
}
