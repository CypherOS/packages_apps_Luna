package com.android.launcher3.reflection;

import android.content.ComponentName;
import android.content.Context;
import com.android.launcher3.userevent.nano.LauncherLogProto$LauncherEvent;
import com.android.launcher3.util.Preconditions;
import com.google.android.apps.nexuslauncher.p009b.C0771b;
import com.android.launcher3.reflection.p010a.C0791d;
import com.android.launcher3.reflection.p010a.C0792e;
import com.android.launcher3.reflection.p011b.C0793a;
import com.android.launcher3.reflection.p011b.C0794b;
import com.android.launcher3.reflection.p011b.C0796c;
import com.android.launcher3.reflection.p011b.C0796c.C0795a;
import com.android.launcher3.reflection.p011b.C0797d;
import com.android.launcher3.reflection.p011b.C0798e;
import com.android.launcher3.reflection.p011b.C0799f;
import com.android.launcher3.reflection.p014d.C0809d;
import com.android.launcher3.reflection.p014d.C0810e;
import com.android.launcher3.reflection.p015e.C0811a.C0812a;
import com.android.launcher3.reflection.p015e.C0811a.C0813b;
import com.android.launcher3.reflection.p015e.C0811a.C0814c;
import com.android.launcher3.reflection.p015e.C0811a.C0815d;
import com.android.launcher3.reflection.p015e.C0811a.C0816e;
import com.android.launcher3.reflection.sensing.UsageEventSensor;
import com.android.launcher3.reflection.signal.C1214a;
import com.android.launcher3.reflection.research.p018c.C1245d;
import com.android.launcher3.reflection.research.predictor.C0968k;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.android.launcher3.reflection.h */
public class C0836h {
    /* renamed from: aA */
    private final C0799f f222aA;
    /* renamed from: aB */
    private final C0831e f223aB;
    /* renamed from: aC */
    private final C0810e f224aC;
    /* renamed from: aD */
    final C0800b f225aD;
    /* renamed from: aE */
    final FirstPageComponentFilter f226aE;
    /* renamed from: aF */
    private final UsageEventSensor f227aF;
    /* renamed from: aG */
    private final C0840j f228aG;
    /* renamed from: aa */
    private final C0809d f229aa;
    /* renamed from: ae */
    private final C0794b f230ae;
    /* renamed from: au */
    final ArrayList<C0835a> f231au = new ArrayList();
    /* renamed from: av */
    final C1245d f232av;
    /* renamed from: aw */
    private final C0797d f233aw;
    /* renamed from: ax */
    private final C0793a f234ax;
    /* renamed from: ay */
    final C0796c f235ay;
    /* renamed from: az */
    private final C0798e f236az;
    final Context mContext;
    final C0834g mEngine;

    /* renamed from: com.android.launcher3.reflection.h$a */
    public interface C0835a {
        /* renamed from: h */
        void mo8519h();
    }

    public C0836h(Context context, C0834g c0834g, C0840j c0840j, C1245d c1245d, C0794b c0794b, C0797d c0797d, C0796c c0796c, C0831e c0831e, C0810e c0810e, C0809d c0809d, C0800b c0800b, FirstPageComponentFilter firstPageComponentFilter) {
        this.mContext = context;
        this.mEngine = c0834g;
        this.f228aG = c0840j;
        this.f232av = c1245d;
        this.f230ae = c0794b;
        this.f233aw = c0797d;
        this.f235ay = c0796c;
        this.f222aA = new C0799f(context);
        this.f234ax = new C0793a(context);
        this.f223aB = c0831e;
        this.f224aC = c0810e;
        this.f229aa = c0809d;
        this.f225aD = c0800b;
        this.f236az = new C0798e(0);
        this.f227aF = UsageEventSensor.m4757e(context);
        this.f226aE = firstPageComponentFilter;
    }

    void updateNewEventTesting(C1214a c1214a) {
        Preconditions.assertNonUiThread();
        this.mEngine.mo8512b(c1214a);
    }

    /* renamed from: a */
    final void mo8520a(C1214a c1214a, LauncherLogProto$LauncherEvent launcherLogProto$LauncherEvent) {
        this.mEngine.mo8512b(c1214a);
        this.mEngine.mo8517j();
        C0796c c0796c = this.f235ay;
        Iterator it = c0796c.f120bj.iterator();
        while (it.hasNext()) {
            C0795a c0795a = (C0795a) it.next();
            c0795a.f115bm++;
        }
        c0796c.mo8490o();
        if (this.f229aa != null) {
            Calendar instance = Calendar.getInstance();
            C0813b c0813b = new C0813b();
            c0813b.f157bA = c1214a.mo9263C().toString();
            c0813b.timestamp = instance.getTimeInMillis();
            c0813b.packageName = c1214a.f464df.f177id;
            if (launcherLogProto$LauncherEvent != null) {
                C0812a c0812a = new C0812a();
                if (launcherLogProto$LauncherEvent.srcTarget.length >= 2 && launcherLogProto$LauncherEvent.srcTarget[1].containerType != 0) {
                    c0812a.f153bS = Integer.toString(launcherLogProto$LauncherEvent.srcTarget[1].containerType);
                    c0812a.f154bT = Integer.toString(launcherLogProto$LauncherEvent.srcTarget[0].pageIndex);
                }
                c0813b.f158bW = c0812a;
            }
            this.f229aa.mo8501a(c0813b);
        }
    }

    /* renamed from: a */
    final void mo8521a(C1214a c1214a, boolean z) {
        if (this.f227aF != null) {
            if (z) {
                this.f227aF.mo14085e(c1214a);
            }
            this.f227aF.mo14084d(c1214a.mo9264D().getTimestamp());
        }
    }

    /* renamed from: a */
    public final void mo8522a(String str, int i) {
        Preconditions.assertNonUiThread();
        C1214a a = C0792e.m2562a(ReflectionEventType.APP_USAGE, "predictionEvent", BuildConfig.FLAVOR, this.f225aD.mo8495g(), this.f232av.mo14296au());
        mo8521a(a, false);
        predict(str, i, a);
    }

    void predict(String str, int i, C1214a c1214a) {
        C0813b c0813b;
        C0816e c0816e;
        List arrayList;
        List arrayList2;
        List arrayList3;
        List arrayList4;
        List subList;
        List subList2;
        if (this.f229aa != null) {
            c0813b = new C0813b();
            c0813b.f157bA = "prediction_update";
            c0813b.timestamp = c1214a.mo9264D().getTimestamp();
            c0816e = new C0816e();
            c0813b.f159bX = c0816e;
        } else {
            c0813b = null;
            c0816e = null;
        }
        this.f234ax.f109bd = i;
        C0968k a = this.mEngine.mo8509a(str, c1214a);
        double[] dArr = a.f364fO;
        List arrayList5 = a.f367fR;
        if (c0816e != null) {
            ArrayList arrayList6 = new ArrayList();
            arrayList = new ArrayList();
            arrayList2 = new ArrayList(arrayList5);
            arrayList3 = new ArrayList();
            arrayList4 = new ArrayList();
        } else {
            arrayList = null;
            arrayList2 = null;
            arrayList3 = null;
            arrayList4 = null;
        }
        this.f235ay.mo8489a(arrayList5, arrayList);
        this.f233aw.mo8492a(arrayList5, arrayList3);
        this.f230ae.mo8485a((List) arrayList5, arrayList4);
        this.f222aA.mo8494a((List) arrayList5, null);
        this.f236az.mo8493a(arrayList5, null);
        C0793a c0793a = this.f234ax;
        if (!c0793a.mo8482e(arrayList5)) {
            String c = C0771b.m2511c(c0793a.mContext);
            if (c != null) {
                C0967a c0967a;
                int a2 = c0793a.mo8481a(arrayList5, c);
                float f = 0.0f;
                if (a2 >= 0) {
                    c0967a = (C0967a) arrayList5.remove(a2);
                    c0967a.f361fS.add("instant_app_filter");
                } else {
                    c0967a = new C0967a(new ComponentName(c, "@instantapp").flattenToString(), 0.0f, "instant_app_filter");
                }
                int min = Math.min(arrayList5.size(), c0793a.f109bd > 0 ? c0793a.f109bd - 1 : 4);
                if (min < arrayList5.size()) {
                    f = ((C0967a) arrayList5.get(min)).f360ca;
                }
                c0967a.f360ca = f;
                arrayList5.add(min, c0967a);
            }
        }
        if (c0816e != null) {
            if (dArr != null) {
                c0816e.f170ch = new C0815d();
                c0816e.f170ch.f163bG = dArr;
            }
            c0816e.f165cc = m2662d(arrayList5);
            c0816e.f166cd = m2662d(arrayList2);
            c0816e.f169cg = m2662d(arrayList3);
            c0816e.f167ce = m2662d(arrayList4);
        }
        if (arrayList5.size() > 12) {
            arrayList5 = new ArrayList(arrayList5.subList(0, 12));
        }
        Map c2 = this.f223aB.mo8507c(str);
        if (arrayList5.size() > i) {
            subList = arrayList5.subList(0, i);
            subList2 = arrayList5.subList(i, arrayList5.size());
        } else {
            subList2 = Collections.emptyList();
            subList = arrayList5;
        }
        if (!c2.isEmpty()) {
            subList = C0836h.stabilizePredictedAppOrder(subList, c2);
        }
        C0831e c0831e = this.f223aB;
        ArrayList arrayList7 = new ArrayList(subList.size() + subList2.size());
        arrayList7.addAll(subList);
        arrayList7.addAll(subList2);
        String c3 = C0831e.m2644c((List) arrayList7);
        long currentTimeMillis = System.currentTimeMillis();
        String b = C0831e.m2643b(subList);
        String c4 = C0831e.m2644c((List) arrayList5);
        C0791d d = C0791d.m2561d(str);
        if (!c4.equals(c0831e.f208o.getString(d.f108bc, BuildConfig.FLAVOR))) {
            c0831e.f208o.edit().putString(d.f105aZ, c3).putLong(d.f106ba, currentTimeMillis).putString(d.f107bb, b).putString(d.f108bc, c4).apply();
        }
        if (c0813b != null) {
            this.f229aa.mo8501a(c0813b);
        }
    }

    /* renamed from: d */
    private C0814c[] m2662d(List<C0967a> list) {
        C0814c[] c0814cArr = new C0814c[list.size()];
        for (int i = 0; i < list.size(); i++) {
            C0814c c0814c = new C0814c();
            c0814c.f161bZ = ((C0967a) list.get(i)).f363id;
            c0814c.f162ca = ((C0967a) list.get(i)).f360ca;
            c0814cArr[i] = c0814c;
        }
        return c0814cArr;
    }

    /* renamed from: a */
    public final synchronized void mo8523a(boolean z) {
        Preconditions.assertNonUiThread();
        if (z) {
            this.f224aC.mo8504u();
            this.mContext.deleteDatabase("reflection.events");
            this.mEngine.reset();
        }
        Iterator it = this.f231au.iterator();
        while (it.hasNext()) {
            ((C0835a) it.next()).mo8519h();
        }
    }

    static List<C0967a> stabilizePredictedAppOrder(List<C0967a> list, Map<String, Integer> map) {
        C0967a[] c0967aArr = new C0967a[list.size()];
        ArrayList arrayList = new ArrayList();
        for (C0967a c0967a : list) {
            if (map.containsKey(c0967a.f363id)) {
                int intValue = ((Integer) map.get(c0967a.f363id)).intValue();
                if (intValue < c0967aArr.length) {
                    c0967aArr[intValue] = c0967a;
                } else {
                    arrayList.add(c0967a);
                }
            } else {
                arrayList.add(c0967a);
            }
        }
        map.clear();
        int i = 0;
        for (int i2 = 0; i2 < c0967aArr.length; i2++) {
            if (c0967aArr[i2] == null) {
                int i3 = i + 1;
                c0967aArr[i2] = (C0967a) arrayList.get(i);
                i = i3;
            }
        }
        return Arrays.asList(c0967aArr);
    }
}
