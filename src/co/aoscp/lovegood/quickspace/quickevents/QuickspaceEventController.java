/*
 * Copyright (C) 2018-2019 CypherOS
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

package co.aoscp.lovegood.quickspace.quickevents;

import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import co.aoscp.lovegood.Bits;

import com.android.internal.aoscp.AmbientHistoryData;
import com.android.internal.aoscp.AmbientHistoryManager;

import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.MultiHashMap;
import com.android.launcher3.util.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class QuickspaceEventController {

    public static final String TAG = "QuickspaceEventController";
    private int AMBIENT_EVENT_DURATION = 60000; // 1 Minute
    private static final String SETTING_DEVICE_INTRO_COMPLETED = "device_introduction_completed";

    public static final int EVENT_NONE = 0;
    public static final int EVENT_FIRST_TIME = 1;
    public static final int EVENT_AMBIENT_PLAY = 2;

    private static QuickspaceEventController sController;
    private Context mContext;
    private Handler mHandler;
    private IEventInfo mListener;
    private FirstTimeSettingsObserver mSettingsObserver;

    private boolean mIsFirstTimeFinished;

    private BroadcastReceiver mAmbientReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            if (intent.getAction().equals(AmbientHistoryManager.INTENT_SONG_MATCH.getAction())) {
                updateEventInfo();
            }
        }
    };

    public static QuickspaceEventController get(Context context) {
        Preconditions.assertUIThread();
        if (sController == null) {
            sController = new QuickspaceEventController(context.getApplicationContext());
        }
        return sController;
    }

    private QuickspaceEventController(Context context) {
        mContext = context;
        mHandler = new Handler();
        mContext.registerReceiver(mAmbientReceiver, new IntentFilter(AmbientHistoryManager.INTENT_SONG_MATCH.getAction()));
    }

    private void checkForFirstTime() {
        String deviceIntro = mContext.getResources().getString(R.string.quick_event_aoscp_intro_welcome);
        if (!mIsFirstTimeFinished) {
            if (mListener != null) {
                mListener.onNewEvent(deviceIntro, EVENT_FIRST_TIME);
            }
        } else {
            if (mListener != null) {
                mListener.onNewEvent(null, EVENT_NONE);
            }
        }
    }

    private void updateEventInfo() {
        checkForFirstTime();
        List<AmbientHistoryData> info = AmbientHistoryManager.getSongs(mContext);
        if (info.size() < 1) return;
        AmbientHistoryData data = info.get(0);
        String ambientMusicText = String.format(mContext.getResources().getString(
                R.string.quick_event_ambient_song_artist), data.getSongTitle(), data.getArtistTitle());
        if (mListener != null && mIsFirstTimeFinished) {
            mListener.onNewEvent(ambientMusicText, EVENT_AMBIENT_PLAY);
            mHandler.postDelayed(() -> {
                    mListener.onNewEvent(null, EVENT_NONE);
            }, AMBIENT_EVENT_DURATION);
        }
    }

    public void setListener(IEventInfo listener) {
        mListener = listener;
        mSettingsObserver = new FirstTimeSettingsObserver();
        mSettingsObserver.observe();
        mSettingsObserver.update();
    }

    private class FirstTimeSettingsObserver extends ContentObserver {
        FirstTimeSettingsObserver() {
            super(null);
        }

        void observe() {
            mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(SETTING_DEVICE_INTRO_COMPLETED),
                    false, this, UserHandle.USER_ALL);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            if (uri.equals(Settings.System.getUriFor(SETTING_DEVICE_INTRO_COMPLETED))) {
                update();
            }
        }

        public void update() {
            mIsFirstTimeFinished = Settings.System.getIntForUser(mContext.getContentResolver(),
                    SETTING_DEVICE_INTRO_COMPLETED, 0, UserHandle.USER_CURRENT) != 0;
            checkForFirstTime();
        }
    }
}
