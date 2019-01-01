package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.RectF;
import android.util.SparseArray;
import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1975b;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.e */
public class C0918e {
    /* renamed from: uq */
    C1976c f1119uq;
    /* renamed from: ur */
    C1967D f1120ur;
    /* renamed from: uv */
    C1985t f1121uv;
    /* renamed from: wg */
    int f1122wg;
    /* renamed from: xp */
    final C0884M f1123xp;
    /* renamed from: xq */
    public final C1709f f1124xq;
    /* renamed from: xr */
    final List f1125xr = new ArrayList();
    /* renamed from: xs */
    private final SparseArray f1126xs = new SparseArray();
    /* renamed from: xt */
    private int f1127xt;
    /* renamed from: xu */
    EntitiesData f1128xu;
    /* renamed from: xv */
    RectF f1129xv = null;

    /* renamed from: a */
    static C0918e m614a(C0884M c0884m, C1709f c1709f, int i) {
        C0918e c0918e = new C0918e(null, null, c0884m, c1709f, i);
        if (i >= 0) {
            c0918e.mo3373cx();
        }
        return c0918e;
    }

    final void clear() {
        this.f1119uq = null;
        this.f1120ur = null;
        this.f1129xv = null;
        this.f1125xr.clear();
        this.f1126xs.clear();
    }

    /* renamed from: a */
    final RectF mo3363a(C1968E c1968e) {
        if (c1968e.f3136wg != this.f1122wg) {
            return null;
        }
        for (int i = 0; i < c1968e.f3138wm.length; i++) {
            if (c1968e.f3138wm[i].f3150wx.length > 0) {
                return new RectF((RectF) C0915av.m613t(mo3366ar(c1968e.f3138wm[i].f3150wx[0].f3121we)));
            }
        }
        return null;
    }

    /* renamed from: ar */
    final RectF mo3366ar(int i) {
        return (RectF) C0915av.m613t((RectF) this.f1126xs.get(i));
    }

    /* renamed from: a */
    final boolean mo3365a(int i, C1976c c1976c, EntitiesData entitiesData, int i2) {
        if (mo3367as(i)) {
            clear();
            this.f1119uq = c1976c;
            this.f1128xu = entitiesData;
            this.f1120ur = entitiesData.f995ur;
            this.f1122wg = i2;
            mo3373cx();
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder(47);
        stringBuilder.append("--Error: token expired; new token = ");
        stringBuilder.append(i);
        C0936x.m653h(stringBuilder.toString());
        return false;
    }

    /* renamed from: cu */
    final C1975b[] mo3370cu() {
        return (C1975b[]) C0915av.m613t(((C1976c) C0915av.m613t(this.f1119uq)).f3174uB);
    }

    /* renamed from: cv */
    final C1975b mo3371cv() {
        return mo3370cu()[this.f1122wg];
    }

    final boolean isEmpty() {
        if (!(this.f1119uq == null || this.f1120ur == null || this.f1119uq.f3174uB == null)) {
            if (this.f1119uq.f3174uB.length != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    final void mo3364a(C1965A c1965a, RectF rectF) {
        mo3368b(c1965a, rectF);
        this.f1123xp.mo3317a(rectF);
    }

    /* renamed from: b */
    final void mo3368b(C1965A c1965a, RectF rectF) {
        rectF.set(c1965a.f3118wb.left, c1965a.f3118wb.top, c1965a.f3118wb.width + c1965a.f3118wb.left, c1965a.f3118wb.height + c1965a.f3118wb.top);
    }

    /* renamed from: cw */
    final boolean mo3372cw() {
        if (this.f1122wg >= 0) {
            if (mo3371cv().f3170uy != null) {
                for (C1965A c1965a : mo3371cv().f3170uy) {
                    if (c1965a.f3120wd == 2) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    final int nextToken() {
        int i = this.f1127xt + 1;
        this.f1127xt = i;
        return i;
    }

    /* renamed from: as */
    final boolean mo3367as(int i) {
        return this.f1127xt == i;
    }

    /* renamed from: a */
    private void m615a(C1967D c1967d) {
        for (C1968E c1968e : c1967d.f3129wi) {
            if (c1968e.f3136wg == this.f1122wg) {
                this.f1125xr.add(c1968e);
            }
        }
    }

    /* renamed from: cx */
    final C0918e mo3373cx() {
        if (this.f1122wg >= 0 && this.f1120ur != null) {
            if (this.f1119uq != null) {
                C1965A[] c1965aArr = mo3371cv().f3170uy;
                RectF rectF = new RectF();
                for (C1965A c1965a : c1965aArr) {
                    mo3364a(c1965a, rectF);
                    RectF rectF2 = (RectF) this.f1126xs.get(c1965a.f3121we);
                    if (rectF2 == null) {
                        this.f1126xs.put(c1965a.f3121we, new RectF(rectF));
                    } else {
                        rectF2.union(rectF);
                    }
                    if (this.f1129xv == null) {
                        this.f1129xv = new RectF(rectF);
                    } else {
                        this.f1129xv.union(rectF);
                    }
                }
                m615a((C1967D) C0915av.m613t(this.f1120ur));
                return this;
            }
        }
        return this;
    }

    C0918e(C1976c c1976c, EntitiesData entitiesData, C0884M c0884m, C1709f c1709f, int i) {
        this.f1119uq = c1976c;
        this.f1128xu = entitiesData;
        if (entitiesData != null) {
            this.f1120ur = entitiesData.f995ur;
        }
        this.f1123xp = c0884m;
        this.f1124xq = c1709f;
        this.f1122wg = i;
    }
}
