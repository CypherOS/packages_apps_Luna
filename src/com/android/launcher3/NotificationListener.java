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

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.HashMap;
import java.util.Map;

public class NotificationListener extends NotificationListenerService {
    private final static Map<String, Integer> Boolean> HAS_NOTIFICATION = new HashMap<>();

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        update(false);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        update(true);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        update(true);
    }

    public static boolean hasNotifications(String packageName){
        Boolean tmp = HAS_NOTIFICATION.get(packageName);
        return tmp != null && tmp;
    }

    private void update(boolean reload) {
        for(String key : HAS_NOTIFICATION.keySet()){
            HAS_NOTIFICATION.put(key, false);
        }
        for(StatusBarNotification sbnn : getActiveNotifications()){
            String key = sbnn.getPackageName();
            boolean target = sbnn.isClearable() && sbnn.getNotification().priority > Notification.PRIORITY_LOW;
            HAS_NOTIFICATION.put(key, target || hasNotifications(key));
        }
        if(reload){
            LauncherAppState.getInstance().reloadLuna(false);
        }
    }
}
