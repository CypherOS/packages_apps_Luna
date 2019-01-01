package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import com.google.android.apps.miphone.aiai.matchmaker.api.ContentData;
import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.FeedbackData;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1973K;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1986u;
import java.util.List;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.M */
public class C0884M {
    final String packageName;
    /* renamed from: vu */
    public boolean f1040vu = false;
    /* renamed from: yB */
    final long f1041yB;
    /* renamed from: yC */
    final String f1042yC;
    /* renamed from: yD */
    private final C0937y f1043yD;
    /* renamed from: yE */
    final RectF f1044yE;
    /* renamed from: yF */
    final PointF f1045yF;
    /* renamed from: yG */
    final Bitmap f1046yG;
    /* renamed from: yH */
    private final RectF f1047yH = new RectF();
    /* renamed from: yI */
    private final RectF f1048yI = new RectF(-1.0f, -1.0f, -1.0f, -1.0f);
    /* renamed from: yJ */
    private final C0883L f1049yJ;
    /* renamed from: yK */
    private int f1050yK = -1;
    /* renamed from: yL */
    private boolean f1051yL;
    /* renamed from: yM */
    private boolean f1052yM;
    /* renamed from: yN */
    public Bundle f1053yN;

    /* renamed from: n */
    final synchronized void mo3327n(boolean z) {
        this.f1052yM = z;
        if (z) {
            this.f1048yI.set(-1.0f, -1.0f, -1.0f, -1.0f);
        }
    }

    /* renamed from: a */
    final RectF mo3317a(RectF rectF) {
        float width = rectF.width() * this.f1045yF.x;
        float height = rectF.height() * this.f1045yF.y;
        rectF.left = (this.f1044yE.left + rectF.left) * this.f1045yF.x;
        rectF.top = (this.f1044yE.top + rectF.top) * this.f1045yF.y;
        rectF.right = rectF.left + width;
        rectF.bottom = rectF.top + height;
        return rectF;
    }

    /* renamed from: a */
    final synchronized void mo3318a(PointF pointF, List list, C0896X c0896x) {
        String valueOf = String.valueOf(pointF);
        StringBuilder stringBuilder = new StringBuilder(20 + String.valueOf(valueOf).length());
        stringBuilder.append("Fetching contents @ ");
        stringBuilder.append(valueOf);
        C0936x.m652g(stringBuilder.toString());
        IScreenMatchmaker cD = this.f1043yD.mo3403cD();
        if (cD == null) {
            mo3325a(CanceledException.m583d("No matchmaker service."));
            return;
        }
        RectF rectF = null;
        if (pointF != null) {
            rectF = new RectF(this.f1047yH);
            rectF.offset(pointF.x, pointF.y);
        }
        C1985t c1985t = new C1985t();
        if (rectF != null) {
            c1985t.f3226vn = new C1973K();
            c1985t.f3226vn.left = rectF.left;
            c1985t.f3226vn.top = rectF.top;
            c1985t.f3226vn.width = rectF.width();
            c1985t.f3226vn.height = rectF.height();
            this.f1048yI.set(rectF);
        }
        if (!(list == null || list.isEmpty())) {
            C1986u[] c1986uArr = new C1986u[list.size()];
            int i = 0;
            for (MotionEvent motionEvent : list) {
                int i2 = i + 1;
                C1986u c1986u = new C1986u();
                c1986u.action = motionEvent.getAction();
                c1986u.f3249vx = motionEvent.getActionButton();
                c1986u.f3250vy = motionEvent.getActionIndex();
                c1986u.f3251vz = motionEvent.getActionMasked();
                c1986u.f3236vA = motionEvent.getButtonState();
                c1986u.f3237vB = motionEvent.getDeviceId();
                c1986u.f3238vC = motionEvent.getDownTime();
                c1986u.f3248vM = motionEvent.getEventTime();
                c1986u.edgeFlags = motionEvent.getEdgeFlags();
                c1986u.f3239vD = motionEvent.getFlags();
                c1986u.orientation = motionEvent.getOrientation();
                c1986u.f3240vE = motionEvent.getRawX();
                c1986u.f3241vF = motionEvent.getRawY();
                c1986u.f3242vG = motionEvent.getSource();
                c1986u.toolMajor = motionEvent.getToolMajor();
                c1986u.toolMinor = motionEvent.getToolMinor();
                c1986u.f3252x = motionEvent.getX();
                c1986u.f3253y = motionEvent.getY();
                c1986u.f3243vH = motionEvent.getXPrecision();
                c1986u.f3244vI = motionEvent.getYPrecision();
                c1986u.f3245vJ = new C1973K();
                c1986u.f3245vJ.left = this.f1044yE.left;
                c1986u.f3245vJ.top = this.f1044yE.top;
                c1986u.f3245vJ.width = this.f1044yE.width();
                c1986u.f3245vJ.height = this.f1044yE.height();
                c1986u.f3246vK = this.f1045yF.x;
                c1986u.f3247vL = this.f1045yF.y;
                c1986uArr[i] = c1986u;
                i = i2;
            }
            c1985t.f3234vv = c1986uArr;
        }
        c1985t.f3232vt = this.f1051yL;
        c1985t.f3233vu = this.f1040vu;
        c1985t.versionCode = 1;
        if (this.f1050yK > 0) {
            c1985t.f3227vo = true;
            c1985t.f3228vp = this.f1050yK;
        }
        String valueOf2 = String.valueOf(rectF);
        stringBuilder = new StringBuilder(19 + String.valueOf(valueOf2).length());
        stringBuilder.append("Contents new rect: ");
        stringBuilder.append(valueOf2);
        C0936x.m651f(stringBuilder.toString());
        this.f1043yD.mo3402c(new C0885N(this, cD, c1985t, c0896x));
    }

    /* renamed from: a */
    final synchronized void mo3322a(C1976c c1976c, C0897Y c0897y) {
        mo3323a(c1976c, c0897y, true);
    }

    /* renamed from: a */
    final synchronized void mo3323a(C1976c c1976c, C0897Y c0897y, boolean z) {
        C0936x.m652g("Extracting entities.");
        IScreenMatchmaker cD = this.f1043yD.mo3403cD();
        if (cD == null) {
            mo3325a(CanceledException.m583d("No matchmaker service."));
        } else if (this.f1052yM) {
            mo3325a(CanceledException.m583d("Entity extraction cancelled."));
        } else {
            this.f1043yD.mo3402c(new C0886O(this, cD, c1976c, c0897y, z));
        }
    }

    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    final synchronized void mo3324a(String str, FeedbackData feedbackData) {
        IScreenMatchmaker cD = this.f1043yD.mo3403cD();
        if (cD != null) {
            if (feedbackData.f998uu.f3209vb.length != 0) {
                this.f1043yD.mo3402c(new C0887P(cD, str, feedbackData));
            }
        }
    }

    /* renamed from: cG */
    final synchronized C0883L mo3326cG() {
        return this.f1049yJ;
    }

    /* renamed from: a */
    final synchronized void mo3319a(RectF rectF, int i, boolean z) {
        this.f1047yH.set(rectF);
        this.f1051yL = z;
        this.f1050yK = i;
    }

    /* renamed from: a */
    final synchronized void mo3320a(ContentData contentData, C1985t c1985t, C0896X c0896x) {
        if (this.f1052yM) {
            mo3325a(CanceledException.m583d("Content extraction cancelled."));
            return;
        }
        String str = "Fetched contents\n\n";
        String valueOf = String.valueOf(C0936x.m645a(contentData));
        C0936x.m650e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.f1043yD.mo3404d(new C0888Q(this, contentData.f994uq == null ? new C1976c() : contentData.f994uq, c0896x, c1985t));
    }

    /* renamed from: a */
    final synchronized void mo3321a(C1976c c1976c, EntitiesData entitiesData, C0897Y c0897y, boolean z) {
        if (this.f1052yM) {
            mo3325a(CanceledException.m583d("Entity extraction cancelled"));
            return;
        }
        C0936x.m651f(C0936x.m646a(entitiesData));
        C1967D c1967d = entitiesData.f995ur == null ? new C1967D() : entitiesData.f995ur;
        if (z) {
            this.f1043yD.mo3404d(new C0893S(this, c1967d, c0897y, c1976c, entitiesData));
            return;
        }
        this.f1043yD.mo3404d(new C0894T(this, c1967d));
        c0897y.mo3336a(c1976c, entitiesData);
    }

    /* renamed from: a */
    final void mo3325a(Throwable th) {
        C0936x.m652g("Canceling suggestion interaction.");
        this.f1043yD.mo3404d(new C0895U(this, th));
    }

    C0884M(C0937y c0937y, RectF rectF, PointF pointF, long j, Bitmap bitmap, Bundle bundle, ComponentName componentName, C0883L c0883l) {
        this.f1043yD = c0937y;
        this.f1044yE = new RectF(rectF);
        this.f1045yF = new PointF(pointF.x, pointF.y);
        this.f1041yB = j;
        this.f1046yG = bitmap;
        this.f1053yN = bundle;
        this.packageName = componentName.getPackageName();
        this.f1042yC = componentName.getClassName();
        this.f1049yJ = c0883l;
    }
}
