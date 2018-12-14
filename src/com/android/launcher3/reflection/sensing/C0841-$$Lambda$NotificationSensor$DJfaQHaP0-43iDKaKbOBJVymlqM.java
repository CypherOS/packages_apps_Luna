package com.google.android.apps.nexuslauncher.reflection.sensing;

import android.service.notification.StatusBarNotification;

/* compiled from: lambda */
/* renamed from: com.google.android.apps.nexuslauncher.reflection.sensing.-$$Lambda$NotificationSensor$DJfaQHaP0-43iDKaKbOBJVymlqM */
public final /* synthetic */ class C0841-$$Lambda$NotificationSensor$DJfaQHaP0-43iDKaKbOBJVymlqM implements Runnable {
    private final /* synthetic */ NotificationSensor f$0;
    private final /* synthetic */ StatusBarNotification f$1;

    public /* synthetic */ C0841-$$Lambda$NotificationSensor$DJfaQHaP0-43iDKaKbOBJVymlqM(NotificationSensor notificationSensor, StatusBarNotification statusBarNotification) {
        this.f$0 = notificationSensor;
        this.f$1 = statusBarNotification;
    }

    public final void run() {
        this.f$0.m4744a(this.f$1);
    }
}
