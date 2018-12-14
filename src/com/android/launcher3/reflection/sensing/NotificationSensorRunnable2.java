package com.android.launcher3.reflection.sensing;

import android.service.notification.StatusBarNotification;

public final class NotificationSensorRunnable2 implements Runnable {

    private NotificationSensor mSensor;
    private StatusBarNotification mBarNotification;

    public NotificationSensorRunnable2(NotificationSensor notificationSensor, StatusBarNotification statusBarNotification) {
        mSensor = notificationSensor;
        mBarNotification = statusBarNotification;
    }

    public final void run() {
        mSensor.m4746b(mBarNotification);
    }
}
