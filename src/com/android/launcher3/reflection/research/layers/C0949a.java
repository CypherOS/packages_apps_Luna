package com.android.launcher3.reflection.research.layers;

import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0941d;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.launcher3.reflection.research.layers.a */
public class C0949a {
    /* renamed from: dF */
    int f283dF;
    /* renamed from: dG */
    int f284dG;
    /* renamed from: dH */
    int f285dH;
    /* renamed from: dI */
    int f286dI;
    /* renamed from: dJ */
    C0938a<C0951e> f287dJ;
    /* renamed from: dK */
    C0938a<C0951e> f288dK;
    /* renamed from: dL */
    C0938a<C0951e> f289dL;
    /* renamed from: dM */
    C0938a<C0951e> f290dM;
    /* renamed from: dN */
    C0938a<C0951e> f291dN;
    /* renamed from: dO */
    C0938a<C0951e> f292dO;
    /* renamed from: dP */
    C0951e[] f293dP;
    /* renamed from: dQ */
    C0951e f294dQ;
    /* renamed from: dR */
    C0951e f295dR;
    /* renamed from: dS */
    C0951e f296dS;
    /* renamed from: dT */
    C0951e f297dT;
    /* renamed from: dU */
    C0951e f298dU;
    /* renamed from: dV */
    C0951e f299dV;
    /* renamed from: dW */
    C0951e f300dW;
    /* renamed from: dX */
    C0951e f301dX;
    /* renamed from: dY */
    C0951e f302dY;
    /* renamed from: dZ */
    C0951e f303dZ;
    /* renamed from: ea */
    C0951e f304ea;
    /* renamed from: eb */
    C0951e f305eb;
    /* renamed from: ec */
    C0951e f306ec;
    /* renamed from: ed */
    C0951e f307ed;
    /* renamed from: ee */
    C0951e f308ee;
    /* renamed from: ef */
    C0951e f309ef;

    /* renamed from: a */
    final void mo9197a(boolean z, C0951e c0951e, int i, int i2, C0951e[] c0951eArr, ArrayList<C0941d>[] arrayListArr, C0951e c0951e2) {
        boolean z2 = z;
        C0951e c0951e3 = c0951e;
        ArrayList<C0941d>[] arrayListArr2 = arrayListArr;
        C0951e c0951e4 = c0951e2;
        int i3 = i;
        int i4 = i2;
        C0949a.m2997a(z2, c0951e3, c0951eArr[1], 0, arrayListArr2, c0951e4, i3, 0, i4);
        C0949a.m2997a(z2, c0951e3, c0951eArr[2], 0, arrayListArr2, c0951e4, i3, 1, i4);
        C0949a.m2997a(z2, c0951e3, c0951eArr[4], 0, arrayListArr2, c0951e4, i3, 2, i4);
    }

    /* renamed from: a */
    static void m2997a(boolean z, C0951e c0951e, C0951e c0951e2, int i, ArrayList<C0941d>[] arrayListArr, C0951e c0951e3, int i2, int i3, int i4) {
        C0951e c0951e4 = c0951e3;
        boolean z2 = false;
        int i5 = i4;
        int i6 = 0;
        while (i6 < i5) {
            int i7;
            double b = c0951e2.mo9217b(z2, i6, i);
            if (z) {
                c0951e.mo9215a(false, 0, i3, b);
            } else if (arrayListArr != null) {
                Iterator it = arrayListArr[i6].iterator();
                while (it.hasNext()) {
                    C0941d c0941d = (C0941d) it.next();
                    c0951e.mo9215a(false, c0941d.index, i3, ((double) c0941d.value) * b);
                    i5 = i4;
                }
            } else if (c0951e4 != null) {
                i7 = i2;
                for (i5 = 0; i5 < i7; i5++) {
                    c0951e.mo9215a(false, i5, i3, c0951e4.mo9217b(false, i6, i5) * b);
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
    final double mo9196a(int i, ArrayList<C0941d>[] arrayListArr, C0951e c0951e, C0951e c0951e2, C0951e c0951e3, int i2) {
        int i3;
        double d = 0.0d;
        if (arrayListArr != null) {
            Iterator it = arrayListArr[i].iterator();
            while (it.hasNext()) {
                C0941d c0941d = (C0941d) it.next();
                d += ((double) c0941d.value) * this.f294dQ.mo9217b(false, c0941d.index, i2);
            }
        } else {
            for (i3 = 0; i3 < this.f285dH; i3++) {
                d += c0951e.mo9217b(false, i, i3) * this.f294dQ.mo9217b(false, i3, i2);
            }
        }
        i3 = 0;
        while (c0951e2 != null && i3 < this.f286dI) {
            d += c0951e2.mo9217b(false, i, i3) * this.f295dR.mo9217b(false, i3, i2);
            i3++;
        }
        i3 = 0;
        while (c0951e3 != null && i3 < this.f284dG) {
            d += c0951e3.mo9217b(false, i, i3) * this.f296dS.mo9217b(false, i3, i2);
            i3++;
        }
        return d;
    }
}
