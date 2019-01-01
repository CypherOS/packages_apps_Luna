package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.apps.miphone.aiai.matchmaker.api.SettingsData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1969F;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1978f;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1982l;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1983n;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1988y;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1989z;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.Z */
public class C1702Z extends FrameLayout implements C0920h {
    /* renamed from: zm */
    private static final Random f2370zm = new Random();
    final Handler handler;
    /* renamed from: xF */
    final int f2371xF;
    /* renamed from: xG */
    public C0923k f2372xG;
    /* renamed from: xN */
    private final String f2373xN;
    /* renamed from: xb */
    private final FrameLayout f2374xb;
    /* renamed from: xd */
    public final C0918e f2375xd;
    /* renamed from: xp */
    public final C0884M f2376xp;
    /* renamed from: zn */
    private final FrameLayout f2377zn;
    /* renamed from: zo */
    private final FrameLayout f2378zo;
    /* renamed from: zp */
    final FrameLayout f2379zp;
    /* renamed from: zq */
    public final SettingsData f2380zq;
    /* renamed from: zr */
    private final DebugDisplay f2381zr;
    /* renamed from: zs */
    boolean f2382zs = false;
    /* renamed from: zt */
    private boolean f2383zt = false;
    /* renamed from: zu */
    private boolean f2384zu;
    /* renamed from: zv */
    List f2385zv;

    /* renamed from: a */
    public final void mo3378a(C1988y c1988y, C1968E c1968e) {
        if (c1988y != null && c1968e != null && !this.f2375xd.isEmpty() && this.f2375xd.f1119uq != null && c1968e.f3137wl != null) {
            C1988y[] c1988yArr = new C1988y[c1968e.f3137wl.length];
            for (int i = 0; i < c1968e.f3137wl.length; i++) {
                c1988yArr[i] = c1968e.f3137wl[i].f3269vX;
            }
            C0884M c0884m = this.f2376xp;
            String str = ((C1976c) C0915av.m613t(this.f2375xd.f1119uq)).f3172id;
            C0922j at = C0922j.m631at(this.f2371xF);
            C1985t c1985t = this.f2375xd.f1121uv;
            C0918e c0918e = this.f2375xd;
            C1983n b = C0922j.m632b(c1968e);
            C1978f c = at.mo3384c(c1985t);
            c.f3184uJ = c0918e.mo3372cw() ? 2 : 1;
            c.f3185uK = c1968e;
            c.f3186uL = c1988yArr;
            c.f3187uM = c1988y;
            c.f3188uN = 1;
            c.f3189uO = c1968e.f3135uI;
            c.f3190uP = b;
            c0884m.mo3324a(str, at.mo3386cz());
        }
    }

    /* renamed from: b */
    public final void mo3379b(C1988y c1988y, C1968E c1968e) {
        if (!(c1988y == null || c1968e == null || this.f2375xd.isEmpty() || this.f2375xd.f1119uq == null || c1968e.f3137wl == null)) {
            C1988y[] c1988yArr = new C1988y[c1968e.f3137wl.length];
            for (int i = 0; i < c1968e.f3137wl.length; i++) {
                c1988yArr[i] = c1968e.f3137wl[i].f3269vX;
            }
            C0884M c0884m = this.f2376xp;
            String str = ((C1976c) C0915av.m613t(this.f2375xd.f1119uq)).f3172id;
            C0922j at = C0922j.m631at(this.f2371xF);
            C1985t c1985t = this.f2375xd.f1121uv;
            C0918e c0918e = this.f2375xd;
            C1983n b = C0922j.m632b(c1968e);
            C1978f c = at.mo3384c(c1985t);
            c.f3184uJ = c0918e.mo3372cw() ? 2 : 1;
            c.f3185uK = c1968e;
            c.f3186uL = c1988yArr;
            c.f3187uM = c1988y;
            c.f3188uN = 2;
            c.f3189uO = c1968e.f3135uI;
            c.f3190uP = b;
            c0884m.mo3324a(str, at.mo3386cz());
        }
        mo5347cI();
        mo5350cL();
    }

    /* renamed from: c */
    public final void mo3380c(C1988y c1988y, C1968E c1968e) {
        if (c1988y != null && c1968e != null && !this.f2375xd.isEmpty() && this.f2375xd.f1119uq != null && !this.f2375xd.f1125xr.isEmpty() && c1968e.f3137wl != null) {
            C1988y[] c1988yArr = new C1988y[c1968e.f3137wl.length];
            for (int i = 0; i < c1968e.f3137wl.length; i++) {
                c1988yArr[i] = c1968e.f3137wl[i].f3269vX;
            }
            C0884M c0884m = this.f2376xp;
            String str = ((C1976c) C0915av.m613t(this.f2375xd.f1119uq)).f3172id;
            C0922j at = C0922j.m631at(this.f2371xF);
            C1985t c1985t = this.f2375xd.f1121uv;
            C0918e c0918e = this.f2375xd;
            C1983n b = C0922j.m632b((C1968E) this.f2375xd.f1125xr.get(0));
            C1978f c = at.mo3384c(c1985t);
            c.f3184uJ = c0918e.mo3372cw() ? 2 : 1;
            c.f3185uK = c1968e;
            c.f3186uL = c1988yArr;
            c.f3187uM = c1988y;
            c.f3188uN = 3;
            c.f3189uO = c1968e.f3135uI;
            c.f3190uP = b;
            c0884m.mo3324a(str, at.mo3386cz());
        }
    }

    /* renamed from: a */
    public final PointF mo5344a(PointF pointF) {
        C0884M c0884m = this.f2376xp;
        pointF.x /= c0884m.f1045yF.x;
        pointF.y /= c0884m.f1045yF.y;
        pointF.offset(-c0884m.f1044yE.left, -c0884m.f1044yE.top);
        return pointF;
    }

    /* renamed from: b */
    public final void mo5345b(PointF pointF) {
        String valueOf = String.valueOf(pointF);
        StringBuilder stringBuilder = new StringBuilder(28 + String.valueOf(valueOf).length());
        stringBuilder.append("Start suggest interaction @ ");
        stringBuilder.append(valueOf);
        C0936x.m652g(stringBuilder.toString());
        mo5347cI();
        int nextToken = this.f2375xd.nextToken();
        this.f2376xp.mo3327n(false);
        this.f2376xp.mo3318a(pointF, this.f2385zv, new C1703aa(this, nextToken));
    }

    /* renamed from: cH */
    public final void mo5346cH() {
        C0936x.m652g("Complete suggest interaction");
        this.f2376xp.mo3327n(false);
        this.f2384zu = true;
        mo5351cM();
    }

    /* renamed from: cI */
    public final void mo5347cI() {
        C0936x.m652g("Clearing suggestions.");
        this.f2385zv = null;
        this.f2375xd.clear();
        int i = 0;
        this.f2384zu = false;
        this.f2376xp.mo3327n(true);
        this.f2377zn.removeAllViews();
        this.f2379zp.removeAllViews();
        this.f2374xb.removeAllViews();
        C1709f c1709f = this.f2375xd.f1124xq;
        if (c1709f.f2404xz != null) {
            c1709f.f2404xz.hide();
            if (!(c1709f.f2403xy == null || ((C1968E) C0915av.m613t(c1709f.f2403xy)).f3137wl == null)) {
                C1989z[] c1989zArr = ((C1968E) C0915av.m613t(c1709f.f2403xy)).f3137wl;
                int length = c1989zArr.length;
                while (i < length) {
                    C1989z c1989z = c1989zArr[i];
                    if (c1989z != null) {
                        c1709f.f2401xw.mo3380c(c1989z.f3269vX, (C1968E) C0915av.m613t(c1709f.f2403xy));
                    }
                    i++;
                }
            }
        }
        this.f2376xp.mo3326cG();
    }

    /* renamed from: cJ */
    public void mo5348cJ() {
        if (this.f2380zq.f1000uw.f3254vN) {
            this.f2376xp.mo3324a(this.f2373xN, C0922j.m631at(this.f2371xF).mo3382au(2).mo3386cz());
            mo5347cI();
            C0936x.m652g("Showing automatic suggestions");
            int nextToken = this.f2375xd.nextToken();
            this.f2376xp.mo3327n(false);
            this.f2376xp.mo3318a(null, this.f2385zv, new C1704ab(this, nextToken));
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && !this.f2383zt) {
            z = mo5349cK();
            StringBuilder stringBuilder = new StringBuilder(35);
            stringBuilder.append("Layout occurred, is primary = ");
            stringBuilder.append(z);
            C0936x.m652g(stringBuilder.toString());
            this.f2383zt = true;
            if (mo5349cK()) {
                this.f2376xp.mo3324a(this.f2373xN, C0922j.m631at(this.f2371xF).mo3382au(0).mo3386cz());
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        boolean cK = mo5349cK();
        StringBuilder stringBuilder = new StringBuilder(33);
        stringBuilder.append("Detached view, is primary = ");
        stringBuilder.append(cK);
        C0936x.m652g(stringBuilder.toString());
        if (mo5349cK()) {
            C0884M c0884m = this.f2376xp;
            String str = this.f2373xN;
            C0922j at = C0922j.m631at(this.f2371xF);
            C1982l cA = at.mo3385cA();
            cA.f3210uN = 2;
            cA.f3211vc = 0;
            cA.f3213ve = 0;
            c0884m.mo3324a(str, at.mo3386cz());
        }
    }

    /* renamed from: a */
    static C1702Z m2230a(Context context, C0884M c0884m, SettingsData settingsData, Handler handler) {
        return new C1702Z(context, c0884m, settingsData, handler);
    }

    /* renamed from: cK */
    final boolean mo5349cK() {
        Rect rect = new Rect();
        if (getGlobalVisibleRect(rect, new Point()) && C1702Z.m2229B(rect.width(), rect.height()) == C1702Z.m2229B(getMeasuredWidth(), getMeasuredHeight())) {
            return true;
        }
        return false;
    }

    /* renamed from: B */
    private static int m2229B(int i, int i2) {
        return (int) ((((float) i) / ((float) i2)) * 100.0f);
    }

    /* renamed from: cL */
    final void mo5350cL() {
        this.f2378zo.setOnTouchListener(null);
        this.f2378zo.setOnLongClickListener(null);
        this.f2378zo.setVisibility(8);
    }

    /* renamed from: cM */
    final void mo5351cM() {
        if (this.f2384zu && !this.f2375xd.isEmpty()) {
            if (this.f2375xd.isEmpty()) {
                C0936x.m653h("displayEntities: No content present.");
                return;
            }
            int length = this.f2375xd.mo3370cu().length;
            C0936x.m652g("Displaying entities for long-press.");
            int i = 0;
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    break;
                }
                int i3;
                C0918e c0918e = this.f2375xd;
                c0918e = new C0918e(c0918e.f1119uq, c0918e.f1128xu, c0918e.f1123xp, c0918e.f1124xq, i).mo3373cx();
                if (c0918e.f1129xv != null) {
                    ViewGroup viewGroup = null;
                    if (c0918e.mo3372cw()) {
                        FrameLayout frameLayout = (FrameLayout) C1702Z.inflate(getContext(), C0892R.layout.image_view, null);
                        this.f2377zn.addView(frameLayout);
                        i2 += c0918e.f1125xr.size();
                        C0934v c0934v = new C0934v(frameLayout, c0918e, this.handler);
                        for (C1968E c1968e : c0934v.f1172xd.f1125xr) {
                            c0934v.f1171xM.clear();
                            C1969F[] c1969fArr = c1968e.f3138wm;
                            int length2 = c1969fArr.length;
                            ViewGroup viewGroup2 = viewGroup;
                            int i4 = 0;
                            while (i4 < length2) {
                                C1969F c1969f = c1969fArr[i4];
                                RectF rectF = new RectF();
                                C1965A[] c1965aArr = c1969f.f3150wx;
                                int length3 = c1965aArr.length;
                                ViewGroup viewGroup3 = viewGroup2;
                                int i5 = 0;
                                while (i5 < length3) {
                                    C1965A c1965a = c1965aArr[i5];
                                    if (viewGroup3 == null) {
                                        RectF rectF2 = new RectF();
                                        i3 = length;
                                        c0934v.f1172xd.mo3368b(c1965a, rectF2);
                                        viewGroup3 = rectF2;
                                    } else {
                                        i3 = length;
                                    }
                                    c0934v.f1172xd.mo3364a(c1965a, rectF);
                                    RectF rectF3 = (RectF) c0934v.f1171xM.get(c1965a.f3121we);
                                    if (rectF3 == null) {
                                        c0934v.f1171xM.put(c1965a.f3121we, new RectF(rectF));
                                    } else {
                                        rectF3.union(rectF);
                                    }
                                    i5++;
                                    length = i3;
                                }
                                i3 = length;
                                i4++;
                                viewGroup2 = viewGroup3;
                            }
                            i3 = length;
                            for (length = 0; length < c0934v.f1171xM.size(); length++) {
                                RectF rectF4 = new RectF((RectF) c0934v.f1171xM.valueAt(length));
                                rectF4.inset((float) (-c0934v.f1169xK), (float) (-c0934v.f1170xL));
                                LayoutParams layoutParams = new LayoutParams((int) rectF4.width(), (int) rectF4.height());
                                layoutParams.leftMargin = (int) rectF4.left;
                                layoutParams.topMargin = (int) rectF4.top;
                                FrameLayout frameLayout2 = (FrameLayout) FrameLayout.inflate(c0934v.f1168xH.getContext(), C0892R.layout.image_line_view, null);
                                c0934v.f1168xH.addView(frameLayout2);
                                frameLayout2.setLayoutParams(layoutParams);
                                C0874A c0874a = c0934v.f1172xd.f1124xq;
                                RectF rectF5 = (RectF) C0915av.m613t(c0934v.f1172xd.mo3363a(c1968e));
                                C0918e c0918e2 = (C0918e) C0915av.m613t(c0934v.f1172xd);
                                c0874a.f2403xy = c1968e;
                                c0874a.f2400xd = c0918e2;
                                c0874a.f2402xx.set(rectF5);
                                if (c0874a.f2404xz != null) {
                                    c0874a.f2404xz.mo3406a(c0874a);
                                }
                                c0934v.handler.post(new C0935w(c0934v, rectF4, frameLayout2));
                            }
                            length = i3;
                            viewGroup = null;
                        }
                    } else {
                        i3 = length;
                        FrameLayout frameLayout3 = (FrameLayout) C1702Z.inflate(getContext(), C0892R.layout.text_selection_view, null);
                        this.f2377zn.addView(frameLayout3);
                        if (c0918e.isEmpty() || c0918e.f1119uq == null || c0918e.f1125xr.isEmpty()) {
                            z = false;
                        }
                        if (z) {
                            i2 += c0918e.f1125xr.size();
                            this.f2376xp.mo3324a(((C1976c) C0915av.m613t(c0918e.f1119uq)).f3172id, C0922j.m631at(this.f2371xF).mo3381a(c0918e.f1121uv, (C1968E) c0918e.f1125xr.get(0), c0918e, C0922j.m632b((C1968E) c0918e.f1125xr.get(0))).mo3386cz());
                            new C0902ah(frameLayout3, c0918e, new C1705ac(this, c0918e), this.handler).mo3344cN();
                        }
                        i++;
                        length = i3;
                    }
                }
                i3 = length;
                i++;
                length = i3;
            }
            if (i2 > 0) {
                this.f2378zo.setLongClickable(true);
                this.f2378zo.setVisibility(0);
                this.f2382zs = false;
                this.f2378zo.setOnTouchListener(new C0899ad(this));
                this.f2378zo.setOnLongClickListener(new C0900ae(this));
            } else {
                mo5347cI();
            }
            if (this.f2381zr != null) {
                DebugDisplay debugDisplay = this.f2381zr;
                debugDisplay.f1033xd = this.f2375xd;
                debugDisplay.f1031xb.addView(debugDisplay);
                debugDisplay.setLayoutParams(new LayoutParams(-1, -1));
                debugDisplay.mo3310cs();
            }
        }
    }

    private C1702Z(Context context, C0884M c0884m, SettingsData settingsData, Handler handler) {
        super(context);
        boolean z = false;
        C1702Z.inflate(context, C0892R.layout.suggest_view, this);
        this.f2380zq = settingsData;
        this.f2377zn = (FrameLayout) findViewById(C0892R.C0890id.selection_container);
        this.f2378zo = (FrameLayout) findViewById(C0892R.C0890id.selection_override_container);
        this.f2379zp = (FrameLayout) findViewById(C0892R.C0890id.gleam_container);
        this.f2374xb = (FrameLayout) findViewById(C0892R.C0890id.debug_container);
        this.handler = handler;
        this.f2376xp = c0884m;
        this.f2375xd = C0918e.m614a(c0884m, new C1709f(context, this), -1);
        c0884m.mo3326cG().mo3316a(this);
        Resources resources = getResources();
        RectF rectF = new RectF();
        rectF.inset(-resources.getDimension(C0892R.dimen.long_press_detection_size_x), -resources.getDimension(C0892R.dimen.long_press_detection_size_y));
        Locale locale = resources.getConfiguration().getLocales().get(0);
        int dimension = (int) resources.getDimension(C0892R.dimen.focus_rect_expand_size);
        if (TextUtils.getLayoutDirectionFromLocale(locale) == 1) {
            z = true;
        }
        c0884m.mo3319a(rectF, dimension, z);
        if (C0921i.m629cy()) {
            this.f2381zr = new DebugDisplay(getContext(), this.f2374xb);
        } else {
            this.f2381zr = null;
        }
        String str = "session_overview";
        String valueOf = String.valueOf(UUID.randomUUID());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + String.valueOf(valueOf).length());
        stringBuilder.append(str);
        stringBuilder.append(valueOf);
        this.f2373xN = stringBuilder.toString();
        this.f2371xF = f2370zm.nextInt();
    }
}
