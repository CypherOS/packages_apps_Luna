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

package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import com.android.launcher3.AppFilter;
import com.android.launcher3.AppInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.logging.UserEventDispatcher;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ComponentKeyMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PredictionsUiManager implements OnSharedPreferenceChangeListener {

    public static final int BOOST_ON_OPEN = 9;
    public static final Set<String> EMPTY_SET = new HashSet();
    public static final int MAX_PREDICTIONS = 10;
    public static final String[] PLACE_HOLDERS = new String[]{
		"com.google.android.apps.photos",
		"com.google.android.apps.maps",
		"com.google.android.gm",
		"com.google.android.deskclock",
		"com.android.settings",
		"com.whatsapp",
		"com.facebook.katana",
		"com.facebook.orca",
		"com.google.android.youtube",
		"com.yodo1.crossyroad",
		"com.spotify.music",
		"com.android.chrome",
		"com.instagram.android",
		"com.skype.raider",
		"com.snapchat.android",
		"com.viber.voip",
		"com.twitter.android",
		"com.android.phone",
		"com.google.android.music",
		"com.google.android.calendar",
		"com.google.android.apps.genie.geniewidget",
		"com.netflix.mediaclient",
		"bbc.iplayer.android",
		"com.google.android.videos",
		"com.amazon.mShop.android.shopping",
		"com.microsoft.office.word",
		"com.google.android.apps.docs",
		"com.google.android.keep",
		"com.google.android.apps.plus",
		"com.google.android.talk"
	};
    public static final String PREDICTION_PREFIX = "pref_prediction_count_";
    public static final String PREDICTION_SET = "pref_prediction_set";
    public AppFilter mAppFilter;
    public Context mContext;
    public PackageManager mPackageManager;
    public SharedPreferences mPrefs;

    public PredictionsUiManager(Context context) {
		mAppFilter = AppFilter.newInstance(context);
        mContext = context;
        mPrefs = Utilities.getPrefs(context);
        mPrefs.registerOnSharedPreferenceChangeListener(this);
        mPackageManager = context.getPackageManager();
    }

    public List<ComponentKeyMapper> getPredictedApps() {
        List<ComponentKeyMapper> list = new ArrayList();
        if (isPredictorEnabled()) {
            clearNonExistingComponents();
            List<String> predictionList = new ArrayList(getStringSetCopy());
			Collections.sort(predictionList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.compare(getLaunchCount(o2), getLaunchCount(o1));
                }
            });
            for (String prediction : predictionList) {
                ComponentKeyMapper keyMapper = getComponentFromString(prediction);
                list.add(keyMapper);
            }
            for (int i = 0; i < PLACE_HOLDERS.length && list.size() < 10; i++) {
                Intent intent = mPackageManager.getLaunchIntentForPackage(PLACE_HOLDERS[i]);
                if (intent != null) {
                    ComponentName componentInfo = intent.getComponent();
                    if (componentInfo != null) {
                        ComponentKey key = new ComponentKey(componentInfo, Process.myUserHandle());
                        if (!predictionList.contains(key.toString())) {
                            list.add(mContext, new ComponentKeyMapper(key));
                        }
                    }
                }
            }
        }
        return list;
    }

    public void logAppLaunch(View v, Intent intent) {
        if (isPredictorEnabled() && recursiveIsDrawer(v)) {
            ComponentName componentInfo = intent.getComponent();
            if (componentInfo != null && mAppFilter.shouldShowApp(componentInfo)) {
                clearNonExistingComponents();
                Set<String> predictionSet = getStringSetCopy();
                Editor edit = mPrefs.edit();
                String prediction = new ComponentKey(componentInfo, Process.myUserHandle()).toString();
                if (predictionSet.contains(prediction)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(PREDICTION_PREFIX);
                    sb.append(prediction);
                    edit.putInt(sb.toString(), getLaunchCount(prediction) + 9);
                } else if (predictionSet.size() < 10 || decayHasSpotFree(predictionSet, edit)) {
                    predictionSet.add(prediction);
                }
                edit.putStringSet(PREDICTION_SET, predictionSet);
                edit.apply();
            }
        }
    }

    private boolean decayHasSpotFree(Set<String> toDecay, Editor edit) {
        boolean spotFree = false;
        Set<String> toRemove = new HashSet();
        for (String prediction : toDecay) {
            int launchCount = getLaunchCount(prediction);
            StringBuilder sb;
            if (launchCount > 0) {
                sb = new StringBuilder();
                sb.append(PREDICTION_PREFIX);
                sb.append(prediction);
                edit.putInt(sb.toString(), launchCount - 1);
            } else if (!spotFree) {
                sb = new StringBuilder();
                sb.append(PREDICTION_PREFIX);
                sb.append(prediction);
                edit.remove(sb.toString());
                toRemove.add(prediction);
                spotFree = true;
            }
        }
        for (String prediction2 : toRemove) {
            toDecay.remove(prediction2);
        }
        return spotFree;
    }

    private int getLaunchCount(String component) {
        SharedPreferences sharedPreferences = mPrefs;
        StringBuilder sb = new StringBuilder();
        sb.append(PREDICTION_PREFIX);
        sb.append(component);
        return sharedPreferences.getInt(sb.toString(), 0);
    }

    private boolean recursiveIsDrawer(View view) {
        if (view != null) {
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof AllAppsContainerView) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPredictorEnabled() {
        return true;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!isPredictorEnabled()) {
            Set<String> predictionSet = getStringSetCopy();
            Editor edit = mPrefs.edit();
            for (String prediction : predictionSet) {
                StringBuilder sb = new StringBuilder();
                sb.append("Clearing ");
                sb.append(prediction);
                sb.append(" at ");
                sb.append(getLaunchCount(prediction));
                Log.i("Predictor", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(PREDICTION_PREFIX);
                sb2.append(prediction);
                edit.remove(sb2.toString());
            }
            edit.putStringSet(PREDICTION_SET, EMPTY_SET);
            edit.apply();
        }
    }

    private ComponentKeyMapper<AppInfo> getComponentFromString(String str) {
        return new ComponentKeyMapper(new ComponentKey(mContext, str));
    }

    private void clearNonExistingComponents() {
        Set<String> originalSet = mPrefs.getStringSet(PREDICTION_SET, EMPTY_SET);
        Set<String> predictionSet = new HashSet(originalSet);
        Editor edit = mPrefs.edit();
        for (String prediction : originalSet) {
            ComponentName cn = new ComponentKey(mContext, prediction).componentName;
            try {
                mPackageManager.getActivityInfo(cn, 0);
            } catch (NameNotFoundException e) {
                predictionSet.remove(prediction);
                StringBuilder sb = new StringBuilder();
                sb.append(PREDICTION_PREFIX);
                sb.append(prediction);
                edit.remove(sb.toString());
                Intent intent = mPackageManager.getLaunchIntentForPackage(cn.getPackageName());
                if (intent != null) {
                    ComponentName componentInfo = intent.getComponent();
                    if (componentInfo != null) {
                        predictionSet.add(new ComponentKey(componentInfo, Process.myUserHandle()).toString());
                    }
                }
            }
        }
        edit.putStringSet(PREDICTION_SET, predictionSet);
        edit.apply();
    }

    private Set<String> getStringSetCopy() {
        return new HashSet(mPrefs.getStringSet(PREDICTION_SET, EMPTY_SET));
    }
}