package com.google.android.apps.nexuslauncher.reflection.sensing;

import android.service.notification.StatusBarNotification;

/* compiled from: lambda */
/* renamed from: com.google.android.apps.nexuslauncher.reflection.sensing.-$$Lambda$NotificationSensor$JV4C1Qy8cFKwxu4HvDhifAUXgn4 */
public final /* synthetic */ class C0842-$$Lambda$NotificationSensor$JV4C1Qy8cFKwxu4HvDhifAUXgn4 implements Runnable {
    private final /* synthetic */ NotificationSensor f$0;
    private final /* synthetic */ StatusBarNotification f$1;

    public /* synthetic */ C0842-$$Lambda$NotificationSensor$JV4C1Qy8cFKwxu4HvDhifAUXgn4(NotificationSensor notificationSensor, StatusBarNotification statusBarNotification) {
        this.f$0 = notificationSensor;
        this.f$1 = statusBarNotification;
    }

    public final void run() {
        this.f$0.m4746b(this.f$1);
    }
}
