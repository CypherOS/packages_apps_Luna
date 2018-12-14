package com.android.launcher3.reflection.sensing;

import android.service.notification.StatusBarNotification;

public final class NotificationSensorRunnable implements Runnable {

    private NotificationSensor mNotificationSensor;
    private StatusBarNotification mBarNotification;

    public NotificationSensorRunnable(NotificationSensor notificationSensor, StatusBarNotification statusBarNotification) {
        mNotificationSensor = notificationSensor;
        mBarNotification = statusBarNotification;
    }

    public final void run() {
        mNotificationSensor.m4744a(mBarNotification);
    }
}
