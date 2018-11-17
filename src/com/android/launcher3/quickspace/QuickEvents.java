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
package com.android.launcher3.quickspace;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.View;

import com.android.internal.ambient.play.AmbientIndicationManagerCallback;
import com.android.internal.ambient.play.AmbientManager;
import com.android.internal.ambient.play.DataObserver;

import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;

public class QuickEvents {

	private static final int AMBIENT_INFO_MAX_DURATION = 120000 // 2 minutes
	private static final String SETTING_AMBIENT_RECOGNITION = "ambient_recognition";

    private AmbientManager mAmbientManager;
    private Context mContext;
	private Handler mHandler;

    private int mEventTitleText;
    private int mEventTitleSubText;
    private int mEventSubIcon;

    private boolean mIsQuickEvent = false;

    // Event Indentifiers
    private boolean mEventIntro = false;
    private boolean mEventIntroClicked = false;
	// Event: Ambient Play
	private boolean mEventAmbientPlay = false;
	private boolean mEventAmbientPlayCallback = false;
	private long mLastAmbientInfo;

    public QuickEvents(Context context) {
        mContext = context;
		mHandler = new Handler();
        initQuickEvents();
    }

	public void initAmbientObserver() {
		boolean ambientPlayEnabled = Settings.System.getInt(
		        mContext.getContentResolver(), SETTING_AMBIENT_RECOGNITION, 0) != 0;
		if (!ambientPlayEnabled) {
			if (mEventAmbientPlayCallback) {
				mAmbientManager.unRegisterCallback(mAmbientCallback);
				mEventAmbientPlayCallback = false;
			}
			return;
		}
		mAmbientManager = new AmbientManager(mContext);
        mAmbientManager.registerCallback(mAmbientCallback);
		mEventAmbientPlayCallback = true;
	}

    public void initQuickEvents() {
		mIsFirstTime = SystemProperties.getBoolean("persist.aoscp.first_time", false);
        deviceIntroEvent();
    }

    private void deviceIntroEvent() {
        if (!mIsFirstTime || mEventIntroClicked) {
            mEventIntro = false;
            return;
        }
        mIsQuickEvent = true;
        mEventIntro = true;
        mEventTitleText = R.string.quick_event_aoscp_intro_welcome;
        mEventTitleSubText = R.string.quick_event_aoscp_intro_learn;
    }

	private void ambientPlayEvent() {
		initAmbientObserver();
		if (mEventIntro || !mEventAmbientPlay) {
			return;
		}
		mEventSubIcon = R.drawable.ic_super_g_shadow;
		boolean ambientInfoExpired = System.currentTimeMillis() - mLastAmbientInfo > AMBIENT_INFO_MAX_DURATION;
		if (ambientInfoExpired) {
			mIsQuickEvent = false;
			mEventAmbientPlay = false;
		}
	}

    public boolean isQuickEvent() {
        return mIsQuickEvent;
    }

    public int getEventTitle() {
        return mEventTitleText;
    }

    public int getEventSubTitle() {
        return mEventTitleSubText;
    }

    public int getEventSubIcon() {
        return mEventSubIcon;
    }

    public void setOnClickTask(View view) {
        if (mEventIntro) {
            final Intent intro = new Intent(Settings.ACTION_DEVICE_INTRODUCTION)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            try {
                Launcher.getLauncher(mContext).startActivitySafely(view, intro, null);
            } catch (ActivityNotFoundException ex) {
            }
            mIsQuickEvent = false;
            mEventIntroClicked = true;
        } else if (mEventAmbientPlay) {
			String query = String.format(mContext.getResources().getString(
					R.string.quick_event_ambient_information_small), mAmbientPlaySong, mAmbientPlayArtist)
			final Intent ambient = new Intent(Intent.ACTION_WEB_SEARCH)
			        .putExtra(SearchManager.QUERY, query);
			try {
                Launcher.getLauncher(mContext).startActivitySafely(view, ambient, null);
            } catch (ActivityNotFoundException ex) {
            }
		}
    }

	private AmbientIndicationManagerCallback mAmbientCallback = new AmbientIndicationManagerCallback() {
        @Override
        public void onRecognitionResult(DataObserver observed) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
					if (observed.Song != null && observed.Artist != null) {
						mEventTitleText = String.format(mContext.getResources().getString(
						        R.string.quick_event_ambient_information), observed.Song, observed.Artist)
						mEventTitleSubText = R.string.quick_event_ambient_information_search;
						mIsQuickEvent = true;
						mEventAmbientPlay = true;
						mLastAmbientInfo = System.currentTimeMillis();
						mAmbientPlaySong = observed.Song;
						mAmbientPlayArtist = observed.Artist;
					}
                }
            });
        }

        @Override
        public void onRecognitionNoResult() {
            mEventAmbientPlay = false;
        }

        @Override
        public void onRecognitionError() {
            mEventAmbientPlay = false;
        }

        @Override
        public void onSettingsChanged(String key, boolean newValue) {
        }
    };
}