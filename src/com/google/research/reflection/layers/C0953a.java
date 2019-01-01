package com.google.research.reflection.layers;

import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0945d;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.google.research.reflection.layers.a */
public class C0953a {
    /* renamed from: dF */
    int f283dF;
    /* renamed from: dG */
    int f284dG;
    /* renamed from: dH */
    int f285dH;
    /* renamed from: dI */
    int f286dI;
    /* renamed from: dJ */
    C0942a<C0955e> f287dJ;
    /* renamed from: dK */
    C0942a<C0955e> f288dK;
    /* renamed from: dL */
    C0942a<C0955e> f289dL;
    /* renamed from: dM */
    C0942a<C0955e> f290dM;
    /* renamed from: dN */
    C0942a<C0955e> f291dN;
    /* renamed from: dO */
    C0942a<C0955e> f292dO;
    /* renamed from: dP */
    C0955e[] f293dP;
    /* renamed from: dQ */
    C0955e f294dQ;
    /* renamed from: dR */
    C0955e f295dR;
    /* renamed from: dS */
    C0955e f296dS;
    /* renamed from: dT */
    C0955e f297dT;
    /* renamed from: dU */
    C0955e f298dU;
    /* renamed from: dV */
    C0955e f299dV;
    /* renamed from: dW */
    C0955e f300dW;
    /* renamed from: dX */
    C0955e f301dX;
    /* renamed from: dY */
    C0955e f302dY;
    /* renamed from: dZ */
    C0955e f303dZ;
    /* renamed from: ea */
    C0955e f304ea;
    /* renamed from: eb */
    C0955e f305eb;
    /* renamed from: ec */
    C0955e f306ec;
    /* renamed from: ed */
    C0955e f307ed;
    /* renamed from: ee */
    C0955e f308ee;
    /* renamed from: ef */
    C0955e f309ef;

    /* renamed from: a */
    final void mo9215a(boolean z, C0955e c0955e, int i, int i2, C0955e[] c0955eArr, ArrayList<C0945d>[] arrayListArr, C0955e c0955e2) {
        boolean z2 = z;
        C0955e c0955e3 = c0955e;
        ArrayList<C0945d>[] arrayListArr2 = arrayListArr;
        C0955e c0955e4 = c0955e2;
        int i3 = i;
        int i4 = i2;
        C0953a.m3012a(z2, c0955e3, c0955eArr[1], 0, arrayListArr2, c0955e4, i3, 0, i4);
        C0953a.m3012a(z2, c0955e3, c0955eArr[2], 0, arrayListArr2, c0955e4, i3, 1, i4);
        C0953a.m3012a(z2, c0955e3, c0955eArr[4], 0, arrayListArr2, c0955e4, i3, 2, i4);
    }

    /* renamed from: a */
    static void m3012a(boolean z, C0955e c0955e, C0955e c0955e2, int i, ArrayList<C0945d>[] arrayListArr, C0955e c0955e3, int i2, int i3, int i4) {
        C0955e c0955e4 = c0955e3;
        boolean z2 = false;
        int i5 = i4;
        int i6 = 0;
        while (i6 < i5) {
            int i7;
            double b = c0955e2.mo9235b(z2, i6, i);
            if (z) {
                c0955e.mo9233a(false, 0, i3, b);
            } else if (arrayListArr != null) {
                Iterator it = arrayListArr[i6].iterator();
                while (it.hasNext()) {
                    C0945d c0945d = (C0945d) it.next();
                    c0955e.mo9233a(false, c0945d.index, i3, ((double) c0945d.value) * b);
                    i5 = i4;
                }
            } else if (c0955e4 != null) {
                i7 = i2;
                for (i5 = 0; i5 < i7; i5++) {
                    c0955e.mo9233a(false, i5, i3, c0955e4.mo9235b(false, i6, i5) * b);
                }
                i6++;
                z2 = false;
                i5 = i4;
            }
            i7 = i2;
            i6++;
            z2 = false;
            i5 = i4;
        }
    }

    /* renamed from: a */
    final double mo9214a(int i, ArrayList<C0945d>[] arrayListArr, C0955e c0955e, C0955e c0955e2, C0955e c0955e3, int i2) {
        int i3;
        double d = 0.0d;
        if (arrayListArr != null) {
            Iterator it = arrayListArr[i].iterator();
            while (it.hasNext()) {
                C0945d c0945d = (C0945d) it.next();
                d += ((double) c0945d.value) * this.f294dQ.mo9235b(false, c0945d.index, i2);
            }
        } else {
            for (i3 = 0; i3 < this.f285dH; i3++) {
                d += c0955e.mo9235b(false, i, i3) * this.f294dQ.mo9235b(false, i3, i2);
            }
        }
        i3 = 0;
        while (c0955e2 != null && i3 < this.f286dI) {
            d += c0955e2.mo9235b(false, i, i3) * this.f295dR.mo9235b(false, i3, i2);
            i3++;
        }
        i3 = 0;
        while (c0955e3 != null && i3 < this.f284dG) {
            d += c0955e3.mo9235b(false, i, i3) * this.f296dS.mo9235b(false, i3, i2);
            i3++;
        }
        return d;
    }
}
