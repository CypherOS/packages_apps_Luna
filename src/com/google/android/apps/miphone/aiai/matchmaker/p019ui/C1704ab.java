package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1975b;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1977d;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1984o;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.ab */
final /* synthetic */ class C1704ab implements C0896X {
    /* renamed from: xV */
    private final int f2388xV;
    /* renamed from: zw */
    private final C1702Z f2389zw;

    C1704ab(C1702Z c1702z, int i) {
        this.f2389zw = c1702z;
        this.f2388xV = i;
    }

    /* renamed from: a */
    public final void mo3335a(C1976c c1976c, C1985t c1985t) {
        C1976c c1976c2 = c1976c;
        C1702Z c1702z = this.f2389zw;
        if (c1702z.f2375xd.mo3367as(this.f2388xV)) {
            C1975b[] c1975bArr = c1976c2.f3174uB;
            int length = c1975bArr.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                C1975b[] c1975bArr2;
                C1975b c1975b = c1975bArr[i2];
                FrameLayout frameLayout = (FrameLayout) C1702Z.inflate(c1702z.getContext(), C0892R.layout.gleam_view, null);
                c1702z.f2379zp.addView(frameLayout);
                C0924l c0924l = r2;
                ViewGroup viewGroup = null;
                C0924l c0924l2 = new C0924l(frameLayout, c1975b, c1702z.f2372xG, c1702z.f2376xp, c1702z, i, (int) c1976c2.f3173uA, c1976c2.f3172id, c1702z.handler);
                C1977d[] c1977dArr = c0924l2.f1139xI.f3171uz;
                int length2 = c1977dArr.length;
                int i3 = 0;
                while (i3 < length2) {
                    C1977d[] c1977dArr2;
                    C1977d c1977d = c1977dArr[i3];
                    int i4 = c1977d.f3183uI;
                    c0924l2.f1143xM.clear();
                    if (i4 >= 2) {
                        int[] iArr = c1977d.f3181uG;
                        i = iArr.length;
                        Object obj = viewGroup;
                        int i5 = 0;
                        Object obj2 = null;
                        while (i5 < i) {
                            int i6 = iArr[i5];
                            RectF rectF = new RectF();
                            C1965A c1965a = c0924l2.f1139xI.f3170uy[i6];
                            if (obj == null) {
                                obj = new RectF();
                                C0924l.m643b(c1965a, obj);
                            }
                            C0924l.m643b(c1965a, rectF);
                            c1975bArr2 = c1975bArr;
                            c0924l2.f1146xp.mo3317a(rectF);
                            RectF rectF2 = (RectF) c0924l2.f1143xM.get(c1965a.f3121we);
                            if (rectF2 == null) {
                                c0924l2.f1143xM.put(c1965a.f3121we, new RectF(rectF));
                            } else {
                                rectF2.union(rectF);
                            }
                            i5++;
                            c1975bArr = c1975bArr2;
                            c1976c2 = c1976c;
                            obj2 = 1;
                        }
                        c1975bArr2 = c1975bArr;
                        int i7 = 0;
                        while (i7 < c0924l2.f1143xM.size()) {
                            RectF rectF3 = new RectF((RectF) c0924l2.f1143xM.valueAt(i7));
                            rectF3.inset((float) (-c0924l2.f1141xK), (float) (-c0924l2.f1142xL));
                            LayoutParams layoutParams = new LayoutParams((int) rectF3.width(), (int) rectF3.height());
                            layoutParams.leftMargin = (int) rectF3.left;
                            layoutParams.topMargin = (int) rectF3.top;
                            if (c0924l2.f1137xG != null) {
                                c0924l2.f1137xG.mo3387cC();
                            }
                            FrameLayout frameLayout2 = (FrameLayout) FrameLayout.inflate(c0924l2.f1138xH.getContext(), C0892R.layout.gleam_line_view, null);
                            c0924l2.f1138xH.addView(frameLayout2);
                            frameLayout2.setLayoutParams(layoutParams);
                            if (c0924l2.f1137xG != null) {
                                RectF rectF4 = (RectF) C0915av.m613t(obj);
                                if (c0924l2.f1137xG != null) {
                                    frameLayout2.setLongClickable(true);
                                    frameLayout2.setOnTouchListener(new C0927o(c0924l2, i4, rectF4, frameLayout2));
                                    frameLayout2.setOnLongClickListener(new C0928p(c0924l2, rectF4));
                                }
                            }
                            C0884M c0884m = c0924l2.f1146xp;
                            String str = c0924l2.f1144xN;
                            C0922j at = C0922j.m631at(c0924l2.f1136xF);
                            c1977dArr2 = c1977dArr;
                            C1984o b = at.mo3383b(null);
                            b.type = 1;
                            b.f3222uN = 5;
                            b.f3224vm = i4;
                            c0884m.mo3324a(str, at.mo3386cz());
                            c0924l2.handler.post(new C0925m(c0924l2, rectF3, frameLayout2, i4, c0924l2.f1145xO));
                            i7++;
                            c1977dArr = c1977dArr2;
                        }
                        c1977dArr2 = c1977dArr;
                        if (obj2 != null) {
                            c0924l2.f1145xO++;
                        }
                    } else {
                        c1975bArr2 = c1975bArr;
                        c1977dArr2 = c1977dArr;
                    }
                    i3++;
                    c1975bArr = c1975bArr2;
                    c1977dArr = c1977dArr2;
                    c1976c2 = c1976c;
                    viewGroup = null;
                }
                c1975bArr2 = c1975bArr;
                i = c0924l2.f1145xO;
                i2++;
                c1976c2 = c1976c;
            }
            StringBuilder stringBuilder = new StringBuilder(45);
            stringBuilder.append("Total of ");
            stringBuilder.append(i);
            stringBuilder.append(" gleams selections found.");
            C0936x.m652g(stringBuilder.toString());
        }
    }
}
