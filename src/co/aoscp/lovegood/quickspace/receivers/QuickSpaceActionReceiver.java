/*
 * Copyright (C) 2018 CypherOS
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
package co.aoscp.lovegood.quickspace.receivers;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.os.UserHandle;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;

import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.quickspace.quickevents.QuickspaceEventController;

import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.compat.LauncherAppsCompat;

public class QuickSpaceActionReceiver {

    private static Context mContext;

    public OnClickListener mCalendarClickListener;
    public OnClickListener mEventClickListener;
    public OnClickListener mWeatherClickListener;

    private int mEventType = QuickspaceEventController.EVENT_NONE;
    private String mNowPlayingInfo;

    public QuickSpaceActionReceiver(Context context) {
        mContext = context;

        mCalendarClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleCalendar(view);
            }
        };

        mWeatherClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleWeather(view);
            }
        };

        mEventClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEventType == QuickspaceEventController.EVENT_FIRST_TIME) {
                    openEventFirstTime(view);
                } else if (mEventType == QuickspaceEventController.EVENT_AMBIENT_PLAY) {
                    openEventAmbientResult(view);
                }
            }
        };
    }

    private void openGoogleCalendar(View view) {
        final Uri content_URI = CalendarContract.CONTENT_URI;
        final Uri.Builder appendPath = content_URI.buildUpon().appendPath("time");
        ContentUris.appendId(appendPath, System.currentTimeMillis());
        final Intent addFlags = new Intent(Intent.ACTION_VIEW)
                .setData(appendPath.build())
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            Launcher.getLauncher(mContext).startActivitySafely(view, addFlags, null);
        } catch (ActivityNotFoundException ex) {
            LauncherAppsCompat.getInstance(mContext).showAppDetailsForProfile(new ComponentName(LunaLauncherCallbacks.SEARCH_PACKAGE, ""), Process.myUserHandle());
        }
    }

    private void openGoogleWeather(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("dynact://velour/weather/ProxyActivity"));
        intent.setComponent(new ComponentName(LunaLauncherCallbacks.SEARCH_PACKAGE, "com.google.android.apps.gsa.velour.DynamicActivityTrampoline"));
        try {
            Launcher.getLauncher(mContext).startActivitySafely(view, intent, null);
        } catch (ActivityNotFoundException ex) {
            LauncherAppsCompat.getInstance(mContext).showAppDetailsForProfile(new ComponentName(LunaLauncherCallbacks.SEARCH_PACKAGE, 
                    "com.google.android.apps.gsa.velour.DynamicActivityTrampoline"), Process.myUserHandle());
        }
    }

    private void openEventAmbientResult(View view) {
        final Intent query = new Intent(Intent.ACTION_WEB_SEARCH)
                .putExtra(SearchManager.QUERY, mNowPlayingInfo);
        try {
            Launcher.getLauncher(mContext).startActivitySafely(view, query, null);
        } catch (ActivityNotFoundException ex) {
        }
    }

    public void openEventFirstTime(View view) {
        final Intent intent = new Intent(Settings.ACTION_DEVICE_INTRODUCTION)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            Launcher.getLauncher(mContext).startActivitySafely(view, intent, null);
        } catch (ActivityNotFoundException ex) {
        }
    }

    public OnClickListener getCalendarAction() {
        return mCalendarClickListener;
    }

    public OnClickListener getWeatherAction() {
        return mWeatherClickListener;
    }

    public OnClickListener getEventAction(String nowPlayingInfo, int eventType) {
        mEventType = eventType;
        mNowPlayingInfo = nowPlayingInfo;
        return mEventClickListener;
    }
}
