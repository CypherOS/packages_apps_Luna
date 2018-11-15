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

import android.content.Context;
import android.os.SystemProperties;

import com.android.launcher3.R;
import com.android.launcher3.Utilities;

public class QuickEvents {

    private static boolean mIsQuickEvent = false;
	
	private Context mContext;
	private QuickSpaceView mQuickSpace;

    public QuickEvents(Context context) {
		mContext = context;
		mQuickSpace = new QuickSpaceView(context, null);
		initQuickEvents();
    }
	
	private void initQuickEvents() {
		deviceIntroEvent();
	}

	private void deviceIntroEvent() {
		final boolean isFirstTime = SystemProperties.getBoolean("ro.aoscp.first_time", false);
		if (!isFirstTime) {
			return;
		}
		mIsQuickEvent = true;
		mQuickSpace.setEventTitle(R.string.quick_event_aoscp_intro_welcome);
		mQuickSpace.setEventSubTitle(R.string.quick_event_aoscp_intro_learn);
	}

	public static boolean isQuickEvent() {
		return mIsQuickEvent;
	}
}