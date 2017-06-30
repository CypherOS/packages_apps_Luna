/*
 * Copyright (C) 2017 CypherOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.HashMap;
import java.util.Map;

public class NotificationListener extends NotificationListenerService {
    private final static Map<String, Integer> NOTI_COUNTS = new HashMap<>();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if(sbn.getTag() != null) {
            int num = getNotificationCount(sbn.getPackageName());
            setNotificationCount(sbn.getPackageName(), num + 1);
            LauncherAppState.getInstance().reloadAll();
        }
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
        onNotificationPosted(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if(sbn.getTag() != null){
            int num = getNotificationCount(sbn.getPackageName());
            setNotificationCount(sbn.getPackageName(), num -1);
            LauncherAppState.getInstance().reloadAll();
        }
    }

    public static boolean hasNotifications(String packageName){
        Integer num = NOTI_COUNTS.get(packageName);
        return num != null && num > 0;
    }

    private static int getNotificationCount(String packageName){
        Integer num = NOTI_COUNTS.get(packageName);
        return num == null ? 0 : num;
    }

    private static void setNotificationCount(String packageName, int count){
        if(count < 0) count = 0;
        NOTI_COUNTS.put(packageName, count);
    }
}
