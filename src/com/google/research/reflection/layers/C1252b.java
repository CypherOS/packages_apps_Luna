package com.google.research.reflection.layers;

import com.google.research.reflection.p016a.C0945d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: com.google.research.reflection.layers.b */
public class C1252b extends C0954c {
    /* renamed from: eg */
    C0953a[] f561eg;
    /* renamed from: eh */
    int f562eh;

    /* renamed from: Z */
    public final C0954c mo9216Z() {
        return null;
    }

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final String getName() {
        return "LSTMLayer";
    }

    C1252b() {
    }

    /* renamed from: a */
    public final void mo9219a(C0960f c0960f, int i, C0955e c0955e, C0955e c0955e2, C0955e c0955e3) throws InvalidValueException {
        C0955e.m3033a(c0955e, c0955e2, this.f316es, false);
        final C0955e c0955e4 = (C0955e) this.f314eq.mo9187a(i);
        final ArrayList[] arrayListArr = (ArrayList[]) this.f315er.mo9187a(i);
        final C0955e c0955e5 = (C0955e) this.f313ep.mo9187a(i - 1);
        final int i2 = i;
        C0963i.m3067ak().mo9254a(this.f561eg.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) throws InvalidValueException {
                int i2;
                int i3;
                double b;
                int i4;
                double b2;
                double d;
                C0955e c0955e = new C0955e(C1252b.this.f311dF, C1252b.this.f562eh);
                int i5 = i * C1252b.this.f562eh;
                for (i2 = 0; i2 < C1252b.this.f311dF; i2++) {
                    for (i3 = 0; i3 < C1252b.this.f562eh; i3++) {
                        c0955e.mo9238b(false, i2, i3, C1252b.this.f316es.mo9235b(false, i2, i5 + i3));
                    }
                }
                C0953a c0953a = C1252b.this.f561eg[i];
                int i6 = i2;
                ArrayList[] arrayListArr = arrayListArr;
                C0955e c0955e2 = c0955e4;
                C0955e c0955e3 = c0955e5;
                C0955e c0955e4 = (C0955e) c0953a.f287dJ.mo9187a(i6);
                C0955e c0955e5 = new C0955e(c0953a.f283dF, 1);
                for (i5 = 0; i5 < c0953a.f283dF; i5++) {
                    double d2 = 0.0d;
                    for (i2 = 0; i2 < c0953a.f284dG; i2++) {
                        d2 += c0955e4.mo9235b(false, i5, i2) * c0955e.mo9235b(false, i5, i2);
                    }
                    double b3 = ((C0955e) c0953a.f290dM.mo9187a(i6)).mo9235b(false, i5, 0);
                    c0955e5.mo9238b(false, i5, 0, ((1.0d - b3) * b3) * d2);
                }
                C0955e c0955e6 = new C0955e(c0953a.f283dF, c0953a.f284dG);
                C0955e c0955e7 = (C0955e) c0953a.f291dN.mo9187a(i6 + 1);
                C0955e c0955e8 = (C0955e) c0953a.f288dK.mo9187a(i6);
                int i7 = 0;
                while (i7 < c0953a.f283dF) {
                    b = ((C0955e) c0953a.f290dM.mo9187a(i6)).mo9235b(false, i7, 0);
                    double b4 = c0955e7 != null ? c0955e7.mo9235b(false, i7, 0) : 0.0d;
                    for (i4 = 0; i4 < c0953a.f284dG; i4++) {
                        double b5 = c0955e4.mo9235b(false, i7, i4);
                        b5 = ((1.0d - (b5 * b5)) * b) * c0955e.mo9235b(false, i7, i4);
                        if (c0953a.f293dP[0] != null) {
                            i3 = 2;
                            b5 = ((b5 + (c0953a.f293dP[0].mo9235b(false, i7, i4) * b4)) + (c0953a.f296dS.mo9235b(false, i4, 0) * c0953a.f293dP[1].mo9235b(false, i7, 0))) + (c0953a.f296dS.mo9235b(false, i4, 1) * c0953a.f293dP[2].mo9235b(false, i7, 0));
                        } else {
                            i3 = 2;
                        }
                        c0955e6.mo9238b(false, i7, i4, b5 + (c0953a.f296dS.mo9235b(false, i4, i3) * c0955e5.mo9235b(false, i7, 0)));
                    }
                    i7++;
                }
                c0955e4 = (C0955e) c0953a.f292dO.mo9187a(i6);
                c0955e = (C0955e) c0953a.f289dL.mo9187a(i6);
                c0955e7 = new C0955e(c0953a.f283dF, c0953a.f284dG);
                for (i3 = 0; i3 < c0953a.f283dF; i3++) {
                    b2 = c0955e4.mo9235b(false, i3, 0);
                    for (int i8 = 0; i8 < c0953a.f284dG; i8++) {
                        b = c0955e.mo9235b(false, i3, i8);
                        c0955e7.mo9238b(false, i3, i8, ((1.0d - (b * b)) * b2) * c0955e6.mo9235b(false, i3, i8));
                    }
                }
                C0955e c0955e9 = new C0955e(c0953a.f283dF, 1);
                c0955e4 = (C0955e) c0953a.f291dN.mo9187a(i6);
                C0955e c0955e10 = (C0955e) c0953a.f288dK.mo9187a(i6 - 1);
                for (i3 = 0; i3 < c0953a.f283dF; i3++) {
                    b2 = c0955e4.mo9235b(false, i3, 0);
                    b = (1.0d - b2) * b2;
                    i4 = 0;
                    d = 0.0d;
                    while (c0955e10 != null && i4 < c0953a.f284dG) {
                        d += c0955e10.mo9235b(false, i3, i4) * c0955e6.mo9235b(false, i3, i4);
                        i4++;
                    }
                    c0955e9.mo9238b(false, i3, 0, d * b);
                }
                c0955e4 = new C0955e(c0953a.f283dF, 1);
                C0955e c0955e11 = (C0955e) c0953a.f292dO.mo9187a(i6);
                for (i3 = 0; i3 < c0953a.f283dF; i3++) {
                    b2 = c0955e11.mo9235b(false, i3, 0);
                    b = (1.0d - b2) * b2;
                    d = 0.0d;
                    for (i4 = 0; i4 < c0953a.f284dG; i4++) {
                        d += c0955e.mo9235b(false, i3, i4) * c0955e6.mo9235b(false, i3, i4);
                    }
                    c0955e4.mo9238b(false, i3, 0, d * b);
                }
                c0953a.f293dP = new C0955e[]{c0955e6, c0955e4, c0955e9, c0955e7, c0955e5};
                c0955e9 = C0955e.m3032a(C0955e.m3032a(C0955e.m3032a(c0955e4, c0955e9), c0955e5), c0955e7);
                C0955e.m3034a(c0955e9, C0955e.m3032a(c0953a.f294dQ, c0953a.f299dV), true, c0953a.f308ee, false);
                C0955e.m3034a(c0955e9, C0955e.m3032a(c0953a.f295dR, c0953a.f298dU), true, c0953a.f309ef, false);
                C0953a c0953a2 = c0953a;
                c0955e = c0955e10;
                c0953a2.mo9215a(false, c0953a.f301dX, c0953a.f285dH, c0953a.f283dF, c0953a.f293dP, arrayListArr, c0955e2);
                c0953a2.mo9215a(false, c0953a.f302dY, c0953a.f286dI, c0953a.f283dF, c0953a.f293dP, null, c0955e3);
                C0955e c0955e12 = c0955e;
                C0953a.m3012a(false, c0953a.f303dZ, c0953a.f293dP[1], 0, null, c0955e12, c0953a.f284dG, 0, c0953a.f283dF);
                C0953a.m3012a(false, c0953a.f303dZ, c0953a.f293dP[2], 0, null, c0955e12, c0953a.f284dG, 1, c0953a.f283dF);
                C0953a.m3012a(false, c0953a.f303dZ, c0953a.f293dP[4], 0, null, c0955e8, c0953a.f284dG, 2, c0953a.f283dF);
                c0953a.mo9215a(true, c0953a.f304ea, 1, c0953a.f283dF, c0953a.f293dP, null, null);
                for (int i9 = 0; i9 < c0953a.f284dG; i9++) {
                    i7 = i9;
                    int i10 = i9;
                    C0953a.m3012a(false, c0953a.f306ec, c0953a.f293dP[3], i7, arrayListArr, c0955e2, c0953a.f285dH, i10, c0953a.f283dF);
                    C0953a.m3012a(false, c0953a.f305eb, c0953a.f293dP[3], i7, null, c0955e3, c0953a.f286dI, i10, c0953a.f283dF);
                    C0953a.m3012a(true, c0953a.f307ed, c0953a.f293dP[3], i9, null, null, 1, i9, c0953a.f283dF);
                }
                return Boolean.valueOf(true);
            }
        });
        Arrays.fill(this.f317et.f322eR, 0.0d);
        for (C0953a c0953a : this.f561eg) {
            this.f317et.mo9232a(c0953a.f308ee);
        }
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        for (C0953a c0953a2 : this.f561eg) {
            this.f318eu.mo9232a(c0953a2.f309ef);
        }
    }

    /* renamed from: aa */
    final void mo9220aa() {
        super.mo9220aa();
        for (C0953a c0953a : this.f561eg) {
            c0953a.f293dP = new C0955e[5];
            Arrays.fill(c0953a.f307ed.f322eR, 0.0d);
            Arrays.fill(c0953a.f305eb.f322eR, 0.0d);
            Arrays.fill(c0953a.f306ec.f322eR, 0.0d);
            Arrays.fill(c0953a.f303dZ.f322eR, 0.0d);
            Arrays.fill(c0953a.f302dY.f322eR, 0.0d);
            Arrays.fill(c0953a.f301dX.f322eR, 0.0d);
            Arrays.fill(c0953a.f304ea.f322eR, 0.0d);
        }
    }

    /* renamed from: ab */
    public final void mo9221ab() {
        super.mo9221ab();
        for (C0953a c0953a : this.f561eg) {
            c0953a.f288dK.clear();
            c0953a.f287dJ.clear();
            c0953a.f289dL.clear();
            c0953a.f290dM.clear();
            c0953a.f291dN.clear();
            c0953a.f292dO.clear();
        }
    }

    /* renamed from: a */
    public final C0955e mo9217a(boolean z, C0960f c0960f, ArrayList<C0945d>[] arrayListArr, C0955e c0955e) throws InvalidValueException {
        if (arrayListArr != null) {
            this.f319ev = true;
            this.f315er.mo9188a((Object) arrayListArr);
        } else {
            this.f319ev = false;
            this.f314eq.mo9188a((Object) c0955e);
        }
        final C0955e c0955e2 = this.f313ep.f275dl.length > 1 ? (C0955e) this.f313ep.getLast() : null;
        Object obj = (C0955e) this.f313ep.mo9185Q();
        if (obj == null) {
            obj = new C0955e(this.f311dF, this.f320ew);
        }
        C0955e c0955e3 = (C0955e) this.f313ep.mo9188a(obj);
        final ArrayList<C0945d>[] arrayListArr2 = arrayListArr;
        final C0955e c0955e4 = c0955e;
        final C0955e c0955e5 = c0955e3;
        C0963i.m3067ak().mo9254a(this.f561eg.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) throws InvalidValueException {
                int i2;
                C0955e c0955e;
                int i3;
                C0955e c0955e2 = new C0955e(C1252b.this.f311dF, C1252b.this.f562eh);
                C0953a c0953a = C1252b.this.f561eg[i];
                ArrayList[] arrayListArr = arrayListArr2;
                C0955e c0955e3 = c0955e4;
                C0955e c0955e4 = c0955e2;
                Object obj = (C0955e) c0953a.f290dM.mo9185Q();
                if (obj == null) {
                    obj = new C0955e(c0953a.f283dF, 1);
                }
                C0955e c0955e5 = (C0955e) c0953a.f290dM.mo9188a(obj);
                obj = (C0955e) c0953a.f291dN.mo9185Q();
                if (obj == null) {
                    obj = new C0955e(c0953a.f283dF, 1);
                }
                C0955e c0955e6 = (C0955e) c0953a.f291dN.mo9188a(obj);
                obj = (C0955e) c0953a.f292dO.mo9185Q();
                if (obj == null) {
                    obj = new C0955e(c0953a.f283dF, 1);
                }
                C0955e c0955e7 = (C0955e) c0953a.f292dO.mo9188a(obj);
                C0955e c0955e8 = (C0955e) c0953a.f288dK.getLast();
                Object obj2 = (C0955e) c0953a.f287dJ.mo9185Q();
                if (obj2 == null) {
                    obj2 = new C0955e(c0953a.f283dF, c0953a.f284dG);
                }
                C0955e c0955e9 = (C0955e) c0953a.f287dJ.mo9188a(obj2);
                obj2 = (C0955e) c0953a.f288dK.mo9185Q();
                if (obj2 == null) {
                    obj2 = new C0955e(c0953a.f283dF, c0953a.f284dG);
                }
                C0955e c0955e10 = (C0955e) c0953a.f288dK.mo9188a(obj2);
                obj2 = (C0955e) c0953a.f289dL.mo9185Q();
                if (obj2 == null) {
                    obj2 = new C0955e(c0953a.f283dF, c0953a.f284dG);
                }
                C0955e c0955e11 = (C0955e) c0953a.f289dL.mo9188a(obj2);
                int i4 = 0;
                while (i4 < c0953a.f283dF) {
                    double d;
                    C0955e c0955e12;
                    C0955e c0955e13;
                    ArrayList[] arrayListArr2;
                    double d2;
                    double d3;
                    int i5;
                    C0955e c0955e14;
                    C0953a c0953a2;
                    int i6 = i4;
                    C0955e c0955e15 = c0955e10;
                    double b = C0964j.m3071b(c0953a.mo9214a(i4, arrayListArr, c0955e3, c0955e4, c0955e8, 0) + c0953a.f297dT.f322eR[0]);
                    double d4 = b;
                    c0955e7.mo9238b(false, i6, 0, b);
                    b = C0964j.m3071b(c0953a.mo9214a(i6, arrayListArr, c0955e3, c0955e4, c0955e8, 1) + c0953a.f297dT.f322eR[1]);
                    double d5 = b;
                    c0955e6.mo9238b(false, i6, 0, b);
                    i2 = 0;
                    while (i2 < c0953a.f284dG) {
                        int i7;
                        C0955e c0955e16;
                        boolean z;
                        int i8;
                        double d6;
                        double d7;
                        d = c0953a.f300dW.f322eR[i2];
                        if (arrayListArr != null) {
                            i7 = i6;
                            for (Iterator it = arrayListArr[i7].iterator(); it.hasNext(); it = it) {
                                C0945d c0945d = (C0945d) it.next();
                                d += ((double) c0945d.value) * c0953a.f299dV.mo9235b(false, c0945d.index, i2);
                                c0955e4 = c0955e4;
                            }
                            c0955e16 = c0955e4;
                            z = false;
                        } else {
                            c0955e16 = c0955e4;
                            i7 = i6;
                            z = false;
                            for (i8 = 0; i8 < c0953a.f285dH; i8++) {
                                d += c0955e3.mo9235b(false, i7, i8) * c0953a.f299dV.mo9235b(false, i8, i2);
                            }
                        }
                        i8 = 0;
                        while (c0955e16 != null && i8 < c0953a.f286dI) {
                            d += c0955e16.mo9235b(z, i7, i8) * c0953a.f298dU.mo9235b(z, i8, i2);
                            i8++;
                            z = false;
                        }
                        c0955e4 = c0955e16;
                        double tanh = Math.tanh(d);
                        c0955e11.mo9238b(false, i7, i2, tanh);
                        double d8 = d4;
                        double d9 = d8 * tanh;
                        if (c0955e8 != null) {
                            d6 = d8;
                            d7 = tanh;
                            d = d5;
                            d9 += c0955e8.mo9235b(false, i7, i2) * d;
                        } else {
                            d6 = d8;
                            d7 = tanh;
                            d = d5;
                        }
                        double d10 = d9;
                        c0955e12 = c0955e8;
                        double d11 = d6;
                        c0955e13 = c0955e4;
                        arrayListArr2 = arrayListArr;
                        c0955e = c0955e2;
                        d2 = d;
                        d3 = d7;
                        i5 = i7;
                        c0955e15.mo9238b(false, i7, i2, d10);
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
                        c0955e8 = c0955e12;
                        c0955e4 = c0955e13;
                        c0955e2 = c0955e;
                        arrayListArr = arrayListArr2;
                        i6 = i5;
                    }
                    c0955e12 = c0955e8;
                    c0955e13 = c0955e4;
                    arrayListArr2 = arrayListArr;
                    c0955e = c0955e2;
                    i5 = i6;
                    d3 = C0964j.m3071b(c0953a.mo9214a(i5, arrayListArr2, c0955e3, c0955e13, c0955e15, 2) + c0953a.f297dT.f322eR[2]);
                    c0955e5.mo9238b(false, i5, 0, d3);
                    i3 = 0;
                    while (i3 < c0953a.f284dG) {
                        C0955e c0955e17 = c0955e15;
                        int i9 = i5;
                        double tanh2 = Math.tanh(c0955e17.mo9235b(false, i9, i3));
                        double d12 = tanh2;
                        c0955e9.mo9238b(false, i9, i3, tanh2);
                        d = d12;
                        b = d3 * d;
                        c0955e10 = c0955e12;
                        int i10 = i9;
                        C0955e c0955e18 = c0955e13;
                        c0955e14 = c0955e3;
                        c0953a2 = c0953a;
                        ArrayList[] arrayListArr3 = arrayListArr2;
                        double d13 = d3;
                        c0955e.mo9238b(false, i9, i3, b);
                        if (Double.isNaN(b)) {
                            d2 = c0955e17.mo9235b(false, i10, i3);
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
                        c0955e15 = c0955e17;
                        c0955e13 = c0955e18;
                        c0955e12 = c0955e10;
                        c0955e3 = c0955e14;
                        c0953a = c0953a2;
                        arrayListArr2 = arrayListArr3;
                        i5 = i10;
                        d3 = d13;
                    }
                    c0955e14 = c0955e3;
                    c0953a2 = c0953a;
                    i4 = i5 + 1;
                    c0955e4 = c0955e13;
                    c0955e8 = c0955e12;
                    arrayListArr = arrayListArr2;
                    c0955e2 = c0955e;
                    c0955e10 = c0955e15;
                }
                c0955e = c0955e2;
                i3 = C1252b.this.f562eh * i;
                for (i2 = 0; i2 < C1252b.this.f311dF; i2++) {
                    for (int i11 = 0; i11 < C1252b.this.f562eh; i11++) {
                        c0955e5.mo9238b(false, i2, i3 + i11, c0955e.mo9235b(false, i2, i11));
                    }
                    C0955e c0955e19 = c0955e;
                }
                return Boolean.valueOf(true);
            }
        });
        return c0955e3;
    }

    public final void update() throws InvalidValueException {
        for (C0953a c0953a : this.f561eg) {
            c0953a.f294dQ.mo9232a(c0953a.f301dX.mo9231a(-C0954c.f310eo));
            c0953a.f295dR.mo9232a(c0953a.f302dY.mo9231a(-C0954c.f310eo));
            c0953a.f296dS.mo9232a(c0953a.f303dZ.mo9231a(-C0954c.f310eo));
            c0953a.f297dT.mo9232a(c0953a.f304ea.mo9231a(-C0954c.f310eo));
            c0953a.f298dU.mo9232a(c0953a.f305eb.mo9231a(-C0954c.f310eo));
            c0953a.f299dV.mo9232a(c0953a.f306ec.mo9231a(-C0954c.f310eo));
            c0953a.f300dW.mo9232a(c0953a.f307ed.mo9231a(-C0954c.f310eo));
        }
    }
}
