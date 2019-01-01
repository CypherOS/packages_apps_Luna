package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassification.Builder;
import android.view.textclassifier.TextSelection;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1977d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ah */
public class C0902ah {
    private final Handler handler;
    /* renamed from: xH */
    private final FrameLayout f1089xH;
    /* renamed from: xd */
    final C0918e f1090xd;
    /* renamed from: zA */
    private final StringBuilder f1091zA = new StringBuilder();
    /* renamed from: zB */
    private final RectF f1092zB;
    /* renamed from: zC */
    private final C0901ag f1093zC;
    /* renamed from: zD */
    private SpannableString f1094zD;
    /* renamed from: zE */
    private int f1095zE = -1;
    /* renamed from: zF */
    private int f1096zF = -1;
    /* renamed from: zG */
    int f1097zG = -1;
    /* renamed from: zy */
    final TextView f1098zy;
    /* renamed from: zz */
    private final List f1099zz = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01d0 A:{SYNTHETIC, Splitter:B:64:0x01d0} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: cN */
    final synchronized C0902ah mo3344cN() {
        synchronized (this) {
            if (this.f1092zB == null) {
                C0936x.m653h("Empty text container rect.");
                return this;
            }
            int i;
            int i2;
            int i3;
            int i4;
            RectF rectF;
            if (C0921i.m629cy()) {
                this.f1098zy.setBackgroundColor(Color.argb(50, 50, 150, 60));
            }
            C1965A[] c1965aArr = this.f1090xd.mo3371cv().f3170uy;
            int i5 = -1;
            int i6 = 0;
            if (!this.f1090xd.f1125xr.isEmpty()) {
                C1968E c1968e = (C1968E) this.f1090xd.f1125xr.get(0);
                if (c1968e.f3138wm != null && c1968e.f3138wm.length > 0) {
                    int i7;
                    i = -1;
                    i2 = i;
                    for (int i42 : c1968e.f3138wm[0].f3149uG) {
                        if (i < 0 || i > i42) {
                            i = i42;
                        }
                        if (i2 < 0 || i2 < i42) {
                            i2 = i42;
                        }
                    }
                    i5 = i;
                    rectF = new RectF();
                    i3 = 1;
                    i = 0;
                    i42 = 1;
                    while (i < c1965aArr.length) {
                        int length;
                        C1965A c1965a;
                        RectF rectF2 = new RectF();
                        C1965A c1965a2 = c1965aArr[i];
                        C1965A c1965a3 = i < c1965aArr.length - i3 ? c1965aArr[i + 1] : null;
                        this.f1090xd.mo3364a(c1965a2, rectF2);
                        RectF ar = this.f1090xd.mo3366ar(c1965a2.f3121we);
                        int i8 = (c1965a3 == null || c1965a3.f3121we == c1965a2.f3121we) ? i6 : i3;
                        if (i8 == 0 && c1965a3 != null) {
                            this.f1090xd.mo3364a(c1965a3, rectF);
                            rectF2.right = rectF.left;
                        }
                        rectF2.top = ar.top;
                        rectF2.bottom = ar.bottom;
                        rectF2.offset(-this.f1092zB.left, -this.f1092zB.top);
                        float f;
                        if (i42 != 0) {
                            length = this.f1091zA.length();
                            this.f1091zA.append(" ");
                            f = 0.0f;
                            c1965a = c1965a3;
                            this.f1099zz.add(C0898a.m604a(" ", new RectF(0.0f, 0.0f, rectF2.left, rectF2.height()), i, false, length, this.f1091zA.length()));
                            i6 = 0;
                        } else {
                            f = 0.0f;
                            c1965a = c1965a3;
                            i6 = i42;
                        }
                        String str = c1965a2.f3119wc;
                        int length2 = this.f1091zA.length();
                        this.f1091zA.append(" ");
                        this.f1091zA.append(str);
                        this.f1091zA.append(" ");
                        length = this.f1091zA.length();
                        C1965A[] c1965aArr2 = c1965aArr;
                        i7 = length2;
                        this.f1099zz.add(C0898a.m604a(str, rectF2, i, false, length2, length));
                        if (i5 == i) {
                            this.f1095zE = i7;
                        }
                        if (i2 == i) {
                            this.f1096zF = length - 1;
                        }
                        if (i8 != 0) {
                            int length3 = this.f1091zA.length();
                            this.f1091zA.append(" \n");
                            i7 = this.f1091zA.length();
                            this.f1099zz.add(C0898a.m604a(" \n", new RectF(0.0f, 0.0f, 1.0f, rectF2.height()), i, false, length3, i7));
                            this.f1091zA.append(" \n");
                            this.f1099zz.add(C0898a.m604a("\n \n", new RectF(0.0f, 0.0f, 1.0f, (this.f1090xd.mo3366ar(((C1965A) C0915av.m613t(c1965a)).f3121we).top - this.f1090xd.mo3366ar(((C1965A) C0915av.m613t(c1965a2)).f3121we).bottom) + 1.0f), i, true, i7, i7 + 2));
                            i42 = 1;
                        } else {
                            i42 = i6;
                        }
                        i++;
                        c1965aArr = c1965aArr2;
                        i6 = 0;
                        i3 = 1;
                    }
                    if (this.f1099zz.isEmpty()) {
                        LayoutParams layoutParams = new LayoutParams(((int) this.f1092zB.width()) + 16, ((int) this.f1092zB.height()) + 16);
                        layoutParams.leftMargin = (int) this.f1092zB.left;
                        layoutParams.topMargin = (int) this.f1092zB.top;
                        this.f1089xH.setLayoutParams(layoutParams);
                        this.f1094zD = new SpannableString(this.f1091zA.toString());
                        for (C0898a c0898a : this.f1099zz) {
                            c0898a.f1081wP.offset(-this.f1092zB.left, -this.f1092zB.top);
                            ((SpannableString) C0915av.m613t(this.f1094zD)).setSpan(c0898a, c0898a.f1083wR, c0898a.f1084wS, c0898a.f1085wT ? 51 : 17);
                        }
                        this.f1098zy.setText(this.f1094zD);
                        this.f1098zy.setVisibility(0);
                        this.f1098zy.setOnLongClickListener(new C0903ai(this));
                        this.f1098zy.setOnGenericMotionListener(new C0904aj(this));
                        this.f1098zy.setOnTouchListener(new C0906al(this));
                        this.f1098zy.setOnClickListener(new C0907am(this));
                        this.f1089xH.setOnLongClickListener(new C0908an(this));
                        this.f1089xH.setOnGenericMotionListener(new C0909ao(this));
                        this.f1089xH.setOnTouchListener(new C0910ap(this));
                        this.f1089xH.setOnClickListener(new C0911aq(this));
                        this.f1098zy.setTextClassifier(new C0913at(this));
                        this.f1098zy.setCustomSelectionActionModeCallback(new C0914au(this));
                        m607av(0);
                        i7 = this.f1095zE;
                        int i9 = this.f1096zF;
                        if (i9 > i7 && !TextUtils.isEmpty(this.f1091zA)) {
                            StringBuilder stringBuilder = new StringBuilder(34);
                            stringBuilder.append("Selecting: ");
                            stringBuilder.append(i7);
                            stringBuilder.append(",");
                            stringBuilder.append(i9);
                            C0936x.m652g(stringBuilder.toString());
                            this.f1089xH.post(new C0905ak(this, i7, i9));
                        }
                        this.f1097zG = this.f1090xd.nextToken();
                        return this;
                    }
                    this.f1089xH.setVisibility(8);
                    return this;
                }
            }
            i2 = -1;
            rectF = new RectF();
            i3 = 1;
            i = 0;
            i42 = 1;
            while (i < c1965aArr.length) {
            }
            if (this.f1099zz.isEmpty()) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: av */
    private synchronized C1968E m607av(int i) {
        C1968E c1968e;
        C0918e c0918e = this.f1090xd;
        if (!c0918e.isEmpty()) {
            int i2;
            int i3 = 0;
            if (c0918e.f1122wg >= 0 && c0918e.mo3371cv().f3171uz != null) {
                if (c0918e.mo3371cv().f3171uz.length != 0) {
                    i2 = 0;
                    if (i2 == 0) {
                        if (i < c0918e.mo3371cv().f3171uz.length) {
                            C1968E[] c1968eArr = ((C1967D) C0915av.m613t(c0918e.f1120ur)).f3129wi;
                            int length = c1968eArr.length;
                            while (i3 < length) {
                                c1968e = c1968eArr[i3];
                                if (c1968e.f3136wg == c0918e.f1122wg && c1968e.f3144ws == i) {
                                    break;
                                }
                                i3++;
                            }
                        }
                    }
                }
            }
            i2 = 1;
            if (i2 == 0) {
            }
        }
        c1968e = null;
        if (c1968e == null) {
            return null;
        }
        String str = "-- Selected entity: ";
        String valueOf = String.valueOf(c1968e.f3139wn);
        C0936x.m650e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return c1968e;
    }

    /* renamed from: a */
    final synchronized TextClassification mo3342a(CharSequence charSequence, int i, int i2) {
        Builder builder = new Builder();
        if (this.f1090xd.mo3367as(this.f1097zG)) {
            StringBuilder stringBuilder = new StringBuilder(38);
            stringBuilder.append("Classify text: ");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            C0936x.m650e(stringBuilder.toString());
            C1968E c = m608c(charSequence, i, i2);
            if (c != null) {
                if (c.f3134uH) {
                    builder.setEntityType("other", 0.8f);
                }
                m609d(c);
            }
            return builder.setText(charSequence.toString()).build();
        }
        return builder.setText(charSequence.toString()).build();
    }

    /* renamed from: d */
    private void m609d(C1968E c1968e) {
        this.handler.post(new C0912ar(this, c1968e));
    }

    /* renamed from: b */
    final synchronized TextSelection mo3343b(CharSequence charSequence, int i, int i2) {
        String valueOf = String.valueOf(charSequence);
        StringBuilder stringBuilder = new StringBuilder(41 + String.valueOf(valueOf).length());
        stringBuilder.append("Text selection: ");
        stringBuilder.append(valueOf);
        stringBuilder.append(": ");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        C0936x.m650e(stringBuilder.toString());
        C1968E av = m607av(0);
        if (av != null) {
            TextSelection.Builder builder = new TextSelection.Builder(i, i2);
            if (av.f3134uH) {
                builder.setEntityType("other", 0.8f);
            }
            return builder.build();
        }
        return new TextSelection.Builder(i, i2).build();
    }

    /* renamed from: c */
    private synchronized C1968E m608c(CharSequence charSequence, int i, int i2) {
        if (!this.f1090xd.mo3367as(this.f1097zG)) {
            return null;
        }
        int indexOf = this.f1091zA.indexOf(charSequence.toString());
        i += indexOf;
        indexOf += i2;
        StringBuilder stringBuilder = new StringBuilder(42);
        stringBuilder.append("Selection changed: ");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(indexOf);
        C0936x.m650e(stringBuilder.toString());
        if (!(i == this.f1095zE && indexOf == this.f1096zF)) {
            int i3 = ((C0898a) this.f1099zz.get(this.f1099zz.size() - 1)).f1082wQ;
            i2 = 0;
            int i4 = i2;
            while (i2 < this.f1099zz.size()) {
                C0898a c0898a = (C0898a) this.f1099zz.get(i2);
                if (c0898a.f1082wQ >= 0) {
                    if (c0898a.f1083wR <= i) {
                        i4 = c0898a.f1082wQ;
                    }
                    if (c0898a.f1084wS - 1 <= indexOf + 1) {
                        i3 = c0898a.f1082wQ;
                    }
                }
                i2++;
            }
            C1968E C = m606C(i4, i3);
            if (C != null) {
                if (!this.f1090xd.f1125xr.isEmpty()) {
                    C0901ag c0901ag = (C0901ag) C0915av.m613t(this.f1093zC);
                    c0901ag.mo3341a(C.f3145wt, i, indexOf, ((C1968E) this.f1090xd.f1125xr.get(0)).f3145wt, this.f1095zE, this.f1096zF);
                }
                this.f1095zE = i;
                this.f1096zF = indexOf;
                m609d(C);
                return C;
            }
        }
        return m607av(0);
    }

    /* JADX WARNING: Missing block: B:31:0x00e7, code skipped:
            return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: C */
    private synchronized C1968E m606C(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(45);
        stringBuilder.append("-- Selection changed: ");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        C0936x.m652g(stringBuilder.toString());
        if (i >= 0 && i2 >= i) {
            if (this.f1090xd != null) {
                int i3;
                C0918e c0918e = (C0918e) C0915av.m613t(this.f1090xd);
                c0918e.mo3371cv().f3171uz = new C1977d[1];
                c0918e.mo3371cv().f3171uz[0] = new C1977d();
                int[] iArr = new int[((i2 - i) + 1)];
                for (i3 = 0; i3 < iArr.length; i3++) {
                    iArr[i3] = i + i3;
                }
                c0918e.mo3371cv().f3171uz[0].f3181uG = iArr;
                ArrayList arrayList = new ArrayList(1);
                this.f1097zG = c0918e.nextToken();
                i3 = this.f1097zG;
                CountDownLatch countDownLatch = new CountDownLatch(1);
                try {
                    c0918e.f1123xp.mo3323a((C1976c) C0915av.m613t(c0918e.f1119uq), new C1707as(arrayList, countDownLatch), false);
                    countDownLatch.await(3000, TimeUnit.MILLISECONDS);
                    if (arrayList.size() == 1 && c0918e.mo3365a(i3, (C1976c) C0915av.m613t(c0918e.f1119uq), (EntitiesData) arrayList.get(0), c0918e.f1122wg)) {
                        return m607av(0);
                    }
                    int size = arrayList.size();
                    StringBuilder stringBuilder2 = new StringBuilder(94);
                    stringBuilder2.append("Unable to query any entities; indices: ");
                    stringBuilder2.append(i);
                    stringBuilder2.append(",");
                    stringBuilder2.append(i2);
                    stringBuilder2.append("; # of new entities: ");
                    stringBuilder2.append(size);
                    C0936x.m653h(stringBuilder2.toString());
                } catch (Throwable e) {
                    C0936x.m647a("Unable to query new entities", e);
                }
            }
        }
        return null;
    }

    C0902ah(FrameLayout frameLayout, C0918e c0918e, C0901ag c0901ag, Handler handler) {
        this.f1089xH = frameLayout;
        this.f1090xd = c0918e;
        this.f1093zC = c0901ag;
        this.handler = handler;
        this.f1092zB = c0918e.f1129xv;
        this.f1098zy = (TextView) frameLayout.findViewById(C0892R.C0890id.text_view);
    }
}
