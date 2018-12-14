package com.google.android.apps.nexuslauncher.reflection.sensing;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.notification.NotificationListener.StatusBarNotificationsChangedListener;
import com.google.android.apps.nexuslauncher.reflection.C0836h.C0835a;
import com.google.android.apps.nexuslauncher.reflection.ReflectionClient;
import com.google.android.apps.nexuslauncher.reflection.signal.C1214a;
import com.google.android.apps.nexuslauncher.reflection.signal.C1219f;
import com.google.research.reflection.p018c.C0947a;
import com.google.research.reflection.p018c.C0948c;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NotificationSensor implements StatusBarNotificationsChangedListener, C0835a, C0947a {
    /* renamed from: cM */
    protected static final long f440cM = TimeUnit.MINUTES.toMillis(30);
    /* renamed from: cN */
    private static final SBNCompareByPostTime f441cN = new SBNCompareByPostTime();
    /* renamed from: cL */
    private final List<C0948c> f442cL = new ArrayList();
    /* renamed from: cO */
    private final Set<String> f443cO = new HashSet();
    /* renamed from: cP */
    private final Set<String> f444cP = new HashSet();
    /* renamed from: cQ */
    private long f445cQ = 0;
    /* renamed from: cR */
    private NotificationListener f446cR;
    private final Context mContext;
    long mTimeOfLastReload = 0;

    static class SBNCompareByPostTime implements Comparator<StatusBarNotification> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            StatusBarNotification statusBarNotification = (StatusBarNotification) obj;
            StatusBarNotification statusBarNotification2 = (StatusBarNotification) obj2;
            if (statusBarNotification.getPostTime() == statusBarNotification2.getPostTime()) {
                return 0;
            }
            return statusBarNotification2.getPostTime() > statusBarNotification.getPostTime() ? -1 : 1;
        }

        SBNCompareByPostTime() {
        }
    }

    /* renamed from: A */
    public final void mo9194A() {
    }

    public NotificationSensor(Context context) {
        this.mContext = context;
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        ReflectionClient.getInstance(this.mContext).postNotificationEvent(new NotificationSensorRunnable2(this, statusBarNotification));
    }

    /* renamed from: onNotificationPostedHelper */
    synchronized void m4746b(StatusBarNotification statusBarNotification) {
        if (statusBarNotification.getPostTime() < this.mTimeOfLastReload) {
            reloadPostedNotifications();
        }
        if (!this.f443cO.contains(statusBarNotification.getKey())) {
            m4745a(statusBarNotification, true, statusBarNotification.getPostTime());
            this.f443cO.add(statusBarNotification.getKey());
        }
        if (statusBarNotification.getPostTime() - this.mTimeOfLastReload > f440cM || this.f443cO.size() > 100) {
            reloadPostedNotifications();
        }
    }

    synchronized void reloadPostedNotifications() {
        NotificationListener instanceIfConnected;
        if (this.f446cR == null) {
            instanceIfConnected = NotificationListener.getInstanceIfConnected();
        } else {
            instanceIfConnected = this.f446cR;
        }
        if (instanceIfConnected != null) {
            StatusBarNotification[] activeNotifications = instanceIfConnected.getActiveNotifications();
            Arrays.sort(activeNotifications, f441cN);
            this.f444cP.clear();
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                if (!this.f443cO.contains(statusBarNotification.getKey())) {
                    m4745a(statusBarNotification, true, statusBarNotification.getPostTime());
                }
                this.f444cP.add(statusBarNotification.getKey());
            }
            this.f443cO.clear();
            this.f443cO.addAll(this.f444cP);
            this.f444cP.clear();
            this.mTimeOfLastReload = System.currentTimeMillis();
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        ReflectionClient.getInstance(this.mContext).postNotificationEvent(new NotificationSensorRunnable(this, statusBarNotification));
    }

    /* renamed from: onNotificationRemovedHelper */
    synchronized void m4744a(StatusBarNotification statusBarNotification) {
        m4745a(statusBarNotification, false, System.currentTimeMillis());
    }

    /* renamed from: a */
    public final C0947a mo9195a(C0948c c0948c) {
        this.f442cL.add(c0948c);
        return this;
    }

    /* renamed from: a */
    private void m4745a(StatusBarNotification statusBarNotification, boolean z, long j) {
        if (j >= this.f445cQ) {
            String str;
            Object[] objArr;
            C1214a c1214a = new C1214a();
            Object packageName = statusBarNotification.getPackageName();
            if (z) {
                str = "%s%s";
                objArr = new Object[]{packageName, "/posted"};
            } else {
                str = "%s%s";
                objArr = new Object[]{packageName, "/removed"};
            }
            C1214a c1214a2 = (C1214a) c1214a.mo14095g(String.format(str, objArr)).mo9269a(ReflectionEventType.NOTIFICATION).mo9270a(new C1219f().mo14098e(j));
            UsageEventSensor e = UsageEventSensor.m4757e(this.mContext);
            if (e != null) {
                e.mo14084d(j);
            }
            for (C0948c a : this.f442cL) {
                a.mo8526a(c1214a2.mo14091I());
            }
            this.f445cQ = j;
        }
    }

    /* renamed from: h */
    public final void mo8519h() {
        NotificationListener.removeStatusBarNotificationsChangedListener();
    }

    protected NotificationSensor(NotificationListener notificationListener) {
        this.f446cR = notificationListener;
        this.mContext = null;
    }
}
