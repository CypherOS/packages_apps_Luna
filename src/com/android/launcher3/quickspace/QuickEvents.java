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

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.View;

import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;

public class QuickEvents {
	
	private Context mContext;
	
	private int mEventTitleText;
	private int mEventTitleSubText;
	private int mEventSubIcon;

	private boolean mIsQuickEvent = false;
	
	// Event Indentifiers
	private boolean mEventIntro = false;
	private boolean mEventIntroClicked = false;

    public QuickEvents(Context context) {
		mContext = context;
		initQuickEvents();
    }

	public void initQuickEvents() {
		deviceIntroEvent();
	}

	private void deviceIntroEvent() {
		final boolean isFirstTime = SystemProperties.getBoolean("ro.aoscp.first_time", false);
		if (!isFirstTime || mEventIntroClicked) {
			mEventIntro = false;
			return;
		}
		mIsQuickEvent = true;
		mEventIntro = true;
		mEventTitleText = R.string.quick_event_aoscp_intro_welcome;
		mEventTitleSubText = R.string.quick_event_aoscp_intro_learn;
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
			final Intent intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
			try {
                Launcher.getLauncher(mContext).startActivitySafely(view, intent, null);
            } catch (ActivityNotFoundException ex) {
            }
			mIsQuickEvent = false;
			mEventIntroClicked = true;
		}
	}
}