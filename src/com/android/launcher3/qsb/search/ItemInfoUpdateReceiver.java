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
package com.android.launcher3.qsb.search;

import android.content.SharedPreferences;

import com.android.launcher3.AppInfo;
import com.android.launcher3.IconCache;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherCallbacks;
import com.android.launcher3.R;
import com.android.launcher3.allapps.AllAppsRecyclerView;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import com.android.launcher3.util.ComponentKeyMapper;

import java.util.Iterator;

public class ItemInfoUpdateReceiver implements IconCache.ItemInfoUpdateReceiver, SharedPreferences.OnSharedPreferenceChangeListener {

    private int mAllAppsNumCols;
    private final Launcher mLauncher;
    
    public ItemInfoUpdateReceiver(Launcher launcher, LauncherCallbacks callbacks) {
        mLauncher = launcher;
        mAllAppsNumCols = launcher.getDeviceProfile().allAppsNumCols;
    }

    public void updateItemInfo() {
        final AlphabeticalAppsList apps = ((AllAppsRecyclerView)mLauncher.findViewById(R.id.apps_list_view)).getApps();
        final IconCache iconCache = LauncherAppState.getInstance(mLauncher).getIconCache();
        final Iterator<ComponentKeyMapper<AppInfo>> iterator = mLauncher.getPredictedApps().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final AppInfo app = apps.findApp(iterator.next());
            int n2;
            if (app != null) {
                if (app.usingLowResIcon) {
                    iconCache.updateIconInBackground(this, app);
                }
                n2 = n + 1;
                if (n2 >= mAllAppsNumCols) {
                    break;
                }
            }
            else {
                n2 = n;
            }
            n = n2;
        }
    }

    public void onCreate() {
        mLauncher.getSharedPrefs().registerOnSharedPreferenceChangeListener(this);
    }
    
    public void onDestroy() {
        mLauncher.getSharedPrefs().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ("pref_show_predictions".equals(key)) {
            updateItemInfo();
        }
    }
    
    public void reapplyItemInfo(final ItemInfoWithIcon itemInfoWithIcon) {
    }
}
