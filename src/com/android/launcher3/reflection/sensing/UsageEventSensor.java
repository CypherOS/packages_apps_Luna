package com.google.android.apps.nexuslauncher.reflection.sensing;

import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.p003v7.widget.RecyclerView;
import com.android.launcher3.util.InstantAppResolver;
import com.google.android.apps.nexuslauncher.reflection.C0832f;
import com.google.android.apps.nexuslauncher.reflection.ReflectionClient;
import com.google.android.apps.nexuslauncher.reflection.signal.C1214a;
import com.google.android.apps.nexuslauncher.reflection.signal.C1219f;
import com.google.research.reflection.p018c.C0947a;
import com.google.research.reflection.p018c.C0948c;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UsageEventSensor implements C0947a {
    /* renamed from: cS */
    public static final long f447cS = TimeUnit.MINUTES.toMillis(20);
    /* renamed from: cT */
    public static final long f448cT = TimeUnit.MINUTES.toMillis(10);
    /* renamed from: cU */
    private static final long f449cU = TimeUnit.SECONDS.toMillis(5);
    /* renamed from: cV */
    private static final Set<Integer> f450cV;
    /* renamed from: cW */
    private static UsageEventSensor f451cW;
    /* renamed from: cL */
    private final List<C0948c> f452cL;
    /* renamed from: cX */
    private final UsageStatsManagerWrapper f453cX;
    /* renamed from: cY */
    private final List<C1214a> f454cY;
    /* renamed from: cZ */
    private final InstantAppResolverWrapper f455cZ;
    /* renamed from: da */
    private long f456da;
    /* renamed from: db */
    private C1214a f457db = new C1214a();
    /* renamed from: dc */
    private C1214a f458dc = new C1214a();
    private final Context mContext;
    long mEarliestTrainableTime = RecyclerView.FOREVER_NS;
    private final SharedPreferences mPrivatePrefs;

    protected class InstantAppResolverWrapper {
        /* renamed from: dd */
        Set<String> f250dd = new HashSet();
        InstantAppResolver mInstantAppResolver;

        public InstantAppResolverWrapper(Context context) {
            this.mInstantAppResolver = InstantAppResolver.newInstance(context);
        }
    }

    protected class UsageStatsManagerWrapper {
        /* renamed from: O */
        UsageStatsManager f252O;

        public UsageStatsManagerWrapper(UsageStatsManager usageStatsManager) {
            this.f252O = usageStatsManager;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(1));
        if (VERSION.SDK_INT >= 25) {
            hashSet.add(Integer.valueOf(8));
        }
        f450cV = hashSet;
    }

    public static synchronized UsageEventSensor m4757e(Context context) {
        UsageEventSensor usageEventSensor;
        synchronized (UsageEventSensor.class) {
            if (f451cW == null) {
                UsageEventSensor usageEventSensor2 = null;
                int checkOpNoThrow = ((AppOpsManager) context.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName());
                boolean z = false;
                if (checkOpNoThrow != 3) {
                    if (checkOpNoThrow == 0) {
                    }
                    if (z) {
                        usageEventSensor2 = new UsageEventSensor((UsageStatsManager) context.getSystemService("usagestats"), context);
                    }
                    f451cW = usageEventSensor2;
                    if (usageEventSensor2 != null) {
                        long j = f451cW.mPrivatePrefs.getLong("reflection_most_recent_usage", 0);
                        if (j != 0 && j < System.currentTimeMillis()) {
                            f451cW.f456da = j;
                        }
                    }
                }
                z = true;
                if (z) {
                }
                f451cW = usageEventSensor2;
                if (usageEventSensor2 != null) {
                }
            }
            usageEventSensor = f451cW;
        }
        return usageEventSensor;
    }

    public UsageEventSensor(UsageStatsManager usageStatsManager, Context context) {
        this.f453cX = new UsageStatsManagerWrapper(usageStatsManager);
        this.f452cL = new ArrayList();
        this.f454cY = new ArrayList();
        this.f455cZ = new InstantAppResolverWrapper(context);
        this.mContext = context;
        this.mPrivatePrefs = C0832f.m2649d(this.mContext);
    }

    /* renamed from: e */
    public final synchronized void mo14085e(C1214a c1214a) {
        this.f458dc = this.f457db;
        this.f457db = c1214a.mo14091I();
    }

    /* renamed from: f */
    private synchronized boolean m4758f(C1214a c1214a) {
        if (!m4755a(c1214a, this.f457db)) {
            if (!m4755a(c1214a, this.f458dc)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private synchronized boolean m4755a(C1214a c1214a, C1214a c1214a2) {
        return c1214a.f464df.f177id.equals(c1214a2.f464df.f177id) && Math.abs(c1214a2.mo9264D().getTimestamp() - c1214a.mo9264D().getTimestamp()) <= f449cU;
    }

    /* renamed from: a */
    public final C0947a mo9195a(C0948c c0948c) {
        this.f452cL.add(c0948c);
        return this;
    }

    /* renamed from: A */
    public final void mo9194A() {
        mo14084d(System.currentTimeMillis());
    }

    boolean trainOnUsageEvent(C1214a c1214a) {
        if (this.mContext == null || c1214a.mo9264D().getTimestamp() < this.mEarliestTrainableTime || (c1214a.mo9263C() != ReflectionEventType.SHORTCUTS && c1214a.mo9263C() != ReflectionEventType.INSTANT_APP_USAGE)) {
            return false;
        }
        m4756c(c1214a.mo9264D().getTimestamp());
        ReflectionClient.getInstance(this.mContext).onUsageEventTarget(c1214a);
        return true;
    }

    /* renamed from: c */
    private void m4756c(long j) {
        if (this.mPrivatePrefs != null) {
            this.mPrivatePrefs.edit().putLong("reflection_most_recent_usage_buffer", j).apply();
        }
    }

    void updateEarliestTrainableTime(long j) {
        if (j - f447cS > this.f456da || this.mEarliestTrainableTime == RecyclerView.FOREVER_NS) {
            this.mEarliestTrainableTime = j - f448cT;
        }
    }

    /* renamed from: d */
    public final synchronized void mo14084d(long j) {
        InstantAppResolverWrapper instantAppResolverWrapper = this.f455cZ;
        List<ApplicationInfo> instantApps = instantAppResolverWrapper.mInstantAppResolver.getInstantApps();
        instantAppResolverWrapper.f250dd.clear();
        for (ApplicationInfo applicationInfo : instantApps) {
            instantAppResolverWrapper.f250dd.add(applicationInfo.packageName);
        }
        updateEarliestTrainableTime(j);
        this.f454cY.clear();
        if (this.f456da < j) {
            long max = Math.max(this.f456da + 1, j - f447cS);
            UsageStatsManagerWrapper usageStatsManagerWrapper = this.f453cX;
            ArrayList<Event> arrayList = new ArrayList();
            UsageEvents queryEvents = usageStatsManagerWrapper.f252O.queryEvents(max, j);
            while (queryEvents.hasNextEvent()) {
                Event event = new Event();
                queryEvents.getNextEvent(event);
                if (f450cV.contains(Integer.valueOf(event.getEventType()))) {
                    arrayList.add(event);
                }
            }
            if (!arrayList.isEmpty()) {
                this.f456da = ((Event) arrayList.get(arrayList.size() - 1)).getTimeStamp();
            }
            for (Event event2 : arrayList) {
                String a;
                C1214a c1214a = new C1214a();
                c1214a.mo9270a(new C1219f().mo14098e(event2.getTimeStamp()));
                c1214a.mo14096h("UsageEventSensor");
                boolean z = false;
                boolean z2 = event2.getEventType() == 1 && !m4754a(event2);
                if (z2) {
                    c1214a.mo9269a(ReflectionEventType.APP_USAGE);
                    a = C0832f.m2647a(new ComponentName(event2.getPackageName(), event2.getClassName()));
                } else {
                    if (VERSION.SDK_INT >= 25 && event2.getEventType() == 8) {
                        z = true;
                    }
                    if (z) {
                        c1214a.mo9269a(ReflectionEventType.SHORTCUTS);
                        a = C0832f.m2648a(event2.getPackageName(), event2.getShortcutId());
                    } else if (m4754a(event2)) {
                        c1214a.mo9269a(ReflectionEventType.INSTANT_APP_USAGE);
                        a = C0832f.m2647a(new ComponentName(event2.getPackageName(), "@instantapp"));
                    }
                }
                c1214a.mo14095g(a);
                this.f454cY.add(c1214a);
            }
        }
        for (C1214a c1214a2 : this.f454cY) {
            if (!(m4758f(c1214a2) || trainOnUsageEvent(c1214a2))) {
                for (C0948c a2 : this.f452cL) {
                    a2.mo8526a(c1214a2.mo14091I());
                }
            }
        }
        m4756c(this.f456da);
    }

    /* renamed from: a */
    private boolean m4754a(Event event) {
        return this.f455cZ.f250dd.contains(event.getPackageName());
    }

    protected UsageEventSensor(UsageStatsManagerWrapper usageStatsManagerWrapper, InstantAppResolverWrapper instantAppResolverWrapper) {
        this.f453cX = usageStatsManagerWrapper;
        this.f452cL = new ArrayList();
        this.f454cY = new ArrayList();
        this.f455cZ = instantAppResolverWrapper;
        this.mContext = null;
        this.mPrivatePrefs = null;
    }
}
