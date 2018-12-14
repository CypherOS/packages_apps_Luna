package com.android.launcher3.reflection;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.p010a.C0790c;
import com.android.launcher3.reflection.p011b.C0794b;
import com.android.launcher3.reflection.p014d.C1210b;
import com.android.launcher3.reflection.p014d.C1211c;
import com.android.launcher3.reflection.p015e.C0824c.C0826b;
import com.android.launcher3.reflection.p015e.C0824c.C0827c;
import com.android.launcher3.reflection.p015e.C0824c.C0829e;
import com.android.launcher3.reflection.signal.C1214a;
import com.google.protobuf.nano.MessageNano;
import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.p018c.C1244b;
import com.android.launcher3.reflection.research.predictor.C0961b;
import com.android.launcher3.reflection.research.predictor.C0964g;
import com.android.launcher3.reflection.research.predictor.C0966i;
import com.android.launcher3.reflection.research.predictor.C0968k;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import com.android.launcher3.reflection.research.utils.C0973a;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.android.launcher3.reflection.g */
public class C0834g {
    /* renamed from: ae */
    private final C0794b f212ae;
    /* renamed from: al */
    final C1244b f213al;
    /* renamed from: am */
    final C1210b f214am;
    /* renamed from: an */
    private final C0940c f215an;
    /* renamed from: ao */
    private final Map<String, C0833a> f216ao = new HashMap();
    /* renamed from: ap */
    private final C1211c f217ap;
    /* renamed from: aq */
    private final Runnable f218aq;
    /* renamed from: ar */
    private final SharedPreferences f219ar;
    /* renamed from: as */
    private final String f220as;
    /* renamed from: at */
    private File f221at = null;
    private final Context mContext;
    final C0961b mEventBuffer;
    Map<String, C0966i> mRankers = new HashMap();

    /* renamed from: com.android.launcher3.reflection.g$a */
    public interface C0833a {
        boolean shouldLearnOn(C1214a c1214a);
    }

    public C0834g(Context context, C1211c c1211c, C0940c c0940c, SharedPreferences sharedPreferences, String str, Runnable runnable, C0794b c0794b) {
        Preconditions.assertNotNull(c0940c);
        this.mContext = context;
        this.f217ap = c1211c;
        this.f215an = c0940c;
        this.f219ar = sharedPreferences;
        this.f220as = str;
        this.f218aq = runnable;
        this.mEventBuffer = new C0961b();
        this.f213al = new C1244b(this.mEventBuffer);
        this.f214am = new C1210b(this.f217ap);
        this.f212ae = c0794b;
        this.f216ao.put("OVERVIEW_GEL", new LearnerWrapper(this));
        this.f216ao.put("GEL", GelLearnerWrapper.INSTANCE);
    }

    /* renamed from: a */
    private boolean m2651a(C1214a c1214a) {
        boolean z = c1214a != null && ((c1214a.mo9266F() == null || c1214a.mo9266F().isEmpty()) && c1214a.mo9263C() == ReflectionEventType.INSTANT_APP_USAGE);
        if (z) {
            return true;
        }
        C0794b c0794b = this.f212ae;
        Set emptySet = Collections.emptySet();
        if (c1214a.mo9263C().equals(ReflectionEventType.INSTANT_APP_USAGE)) {
            emptySet = c0794b.mo8488n();
        }
        if (!(!c0794b.mo8487a(c1214a.f464df.f177id, emptySet) || c1214a == null || c1214a.mo9266F() == null)) {
            if (c1214a.mo9266F().size() >= 2) {
                return "GEL".equals((String) c1214a.mo9266F().get(0));
            }
        }
        return false;
    }

    /* renamed from: a */
    public final synchronized void mo8511a(File file) {
        this.f221at = file;
    }

    /* renamed from: a */
    public final synchronized void mo8510a(C0834g c0834g) {
        synchronized (c0834g) {
            this.mRankers.clear();
            this.mRankers.putAll(c0834g.mRankers);
            for (C0966i c : this.mRankers.values()) {
                c.mo9260c(this.mEventBuffer);
            }
        }
    }

    /* renamed from: j */
    public final synchronized boolean mo8517j() {
        Throwable th;
        Throwable th2;
        Preconditions.assertNonUiThread();
        if (!(this.mEventBuffer == null || this.f219ar == null)) {
            String b = C0961b.m3063b(this.mEventBuffer);
            this.f219ar.edit().putString(this.f220as, b).putLong("reflection_most_recent_usage", this.f219ar.getLong("reflection_most_recent_usage_buffer", 0)).apply();
        }
        if (this.f221at == null) {
            return false;
        }
        DataOutputStream dataOutputStream = null;
        try {
            Map<String, C0966i> map = this.mRankers;
            C0827c c0827c = new C0827c();
            c0827c.timestamp = Calendar.getInstance().getTimeInMillis();
            c0827c.version = -1;
            c0827c.f193cu = BuildConfig.FLAVOR;
            c0827c.f194cv = new C0829e[map.size()];
            int i = 0;
            for (Entry entry : map.entrySet()) {
                C0829e c0829e = new C0829e();
                c0829e.f204bp = (String) entry.getKey();
                C0966i c0966i = (C0966i) entry.getValue();
                c0829e.f205cF = new C0826b[c0966i.f358fL.size()];
                int i2 = 0;
                for (C0964g c0964g : c0966i.f358fL) {
                    c0829e.f205cF[i2] = new C0826b();
                    c0829e.f205cF[i2].f191cs = C0790c.m2558a(c0964g);
                    C0790c.m2558a(c0964g);
                    c0829e.f205cF[i2].f192ct = ((Integer) c0966i.f359fM.get(c0964g)).intValue();
                    i2++;
                }
                int i3 = i + 1;
                c0827c.f194cv[i] = c0829e;
                i = i3;
            }
            c0827c.timestamp = Calendar.getInstance().getTimeInMillis();
            c0827c.version = 42;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.format("LatLong=%b ", new Object[]{Boolean.valueOf(true)}));
            stringBuffer.append(String.format("Privateplace=%b ", new Object[]{Boolean.valueOf(false)}));
            stringBuffer.append(String.format("Publicplace=%b ", new Object[]{Boolean.valueOf(true)}));
            stringBuffer.append(String.format("Install=%b ", new Object[]{Boolean.valueOf(true)}));
            stringBuffer.append(String.format("Headset=%b ", new Object[]{Boolean.valueOf(true)}));
            c0827c.f193cu = stringBuffer.toString();
            DataOutputStream dataOutputStream2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.f221at)));
            try {
                dataOutputStream2.write(MessageNano.toByteArray(c0827c));
                if (this.f218aq != null) {
                    this.f218aq.run();
                }
                Utilities.closeSilently(dataOutputStream2);
                return true;
            } catch (Throwable e) {
                DataOutputStream dataOutputStream3 = dataOutputStream2;
                th = e;
                dataOutputStream = dataOutputStream3;
                try {
                    Log.e("Reflection.Engine", "Failed to save models", th);
                    Utilities.closeSilently(dataOutputStream);
                    return false;
                } catch (Throwable th3) {
                    th2 = th3;
                    Utilities.closeSilently(dataOutputStream);
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                dataOutputStream = dataOutputStream2;
                Utilities.closeSilently(dataOutputStream);
                throw th2;
            }
        } catch (IOException e2) {
            th = e2;
            Log.e("Reflection.Engine", "Failed to save models", th);
            Utilities.closeSilently(dataOutputStream);
            return false;
        }
    }

    final synchronized void reset() {
        Preconditions.assertNonUiThread();
        this.mRankers.clear();
    }

    /* renamed from: b */
    public final synchronized void mo8512b(C1214a c1214a) {
        mo8514c(c1214a);
        if (!(c1214a.f464df.f177id.startsWith("/deleted_app/") || this.mEventBuffer == null)) {
            this.f213al.mo14295c((ReflectionEvent) c1214a);
            this.f214am.mo14072c(c1214a);
        }
        this.f217ap.mo14074a(c1214a.mo9264D().getTimestamp());
    }

    /* renamed from: c */
    final synchronized void mo8514c(C1214a c1214a) {
        if (!c1214a.f464df.f177id.startsWith("/deleted_app/")) {
            for (String str : this.f216ao.keySet()) {
                if (((C0833a) this.f216ao.get(str)).shouldLearnOn(c1214a)) {
                    C0966i c0966i = (C0966i) this.mRankers.get(str);
                    if (c0966i == null) {
                        c0966i = C0973a.m3119a(this.mEventBuffer, this.f215an);
                        this.mRankers.put(str, c0966i);
                    }
                    for (C0964g c0964g : r2v9.f358fL) {
                        if (c0964g.mo9256k(c1214a)) {
                            c0964g.mo9254h(c1214a);
                        } else {
                            C0968k c0968k = new C0968k();
                        }
                    }
                    r2v9.f357fK++;
                    C0968k c0968k2 = new C0968k();
                }
            }
        }
    }

    /* renamed from: a */
    public final synchronized C0968k mo8509a(String str, C1214a c1214a) {
        C0966i c0966i = (C0966i) this.mRankers.get(str);
        if (c0966i == null) {
            C0968k c0968k = new C0968k();
            c0968k.f367fR = new ArrayList();
            return c0968k;
        }
        return c0966i.mo9261l(c1214a);
    }

    /* renamed from: b */
    public final synchronized void mo8513b(String str, String str2) {
        StringBuilder sb = new StringBuilder("/deleted_app/");
        sb.append(System.currentTimeMillis());
        sb.append("/");
        String sb2 = sb.toString();
        HashMap hashMap = new HashMap();
        C0966i c0966i = (C0966i) this.mRankers.get(str);
        if (c0966i != null) {
            c0966i.mo9259a(str2, sb2, hashMap);
            mo8517j();
        }
        this.f217ap.mo14076a(str2, sb2, (Map) hashMap);
    }

    Map<String, C0966i> getPredictors() {
        return this.mRankers;
    }
}
