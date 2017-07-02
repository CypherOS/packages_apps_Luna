/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.launcher3.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import com.android.launcher3.compat.UserHandleCompat;

public abstract class LauncherActivityInfoCompat {
	private LauncherActivityInfo mLauncherActivityInfo;
	

    LauncherActivityInfoCompat() {
    }

    /**
     * Creates a LauncherActivityInfoCompat for the primary user.
     */
    public static LauncherActivityInfoCompat fromResolveInfo(ResolveInfo info, Context context) {
        return new LauncherActivityInfoCompatV16(context, info);
    }
	
	public LauncherActivityInfoCompat(LauncherActivityInfo info) {
        mLauncherActivityInfo = info;
    }
	
	public static LauncherActivityInfoCompat create(Context context, UserHandleCompat user, Intent intent) {
		UserHandle user2 = new UserHandle.myUserHandle();
        LauncherApps launcherApps = (LauncherApps) context.getSystemService("launcherapps");
        LauncherActivityInfo info = launcherApps.resolveActivity(intent, user2);
        return;
    }
	
	public ComponentName getComponentName() {
        return mLauncherActivityInfo.getComponentName();
    }

	public abstract UserHandleCompat getUser();

    public CharSequence getLabel() {
        return mLauncherActivityInfo.getLabel();
    }

    public Drawable getIcon(int density) {
        return mLauncherActivityInfo.getIcon(density);
    }

    public ApplicationInfo getApplicationInfo() {
        return mLauncherActivityInfo.getApplicationInfo();
    }

    public long getFirstInstallTime() {
        return mLauncherActivityInfo.getFirstInstallTime();
    }

    public String getName() {
        return mLauncherActivityInfo.getName();
    }
}
