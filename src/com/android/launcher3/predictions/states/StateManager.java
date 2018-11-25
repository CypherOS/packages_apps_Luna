/*
 * Copyright (C) 2018 Google Inc.
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

package com.android.launcher3.predictions.states;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.android.launcher3.Utilities;
import com.android.launcher3.reflection.C0854i;

public abstract class StateManager implements OnSharedPreferenceChangeListener {

    public final SharedPreferences mPredicitons;
    public final SharedPreferences mPredicitonReform;

    public abstract void mo3143a(boolean predictionsEnabled, String str, String str2, boolean z2);

    public StateManager(Context context) {
        mPredicitons = Utilities.getPrefs(context);
        mPredicitonReform = C0854i.m2673l(context);
    }

    public void register() {
        mPredicitons.registerOnSharedPreferenceChangeListener(this);
        mPredicitonReform.registerOnSharedPreferenceChangeListener(this);
    }

    public void unregister() {
        mPredicitons.unregisterOnSharedPreferenceChangeListener(this);
        mPredicitonReform.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("reflection_last_predictions".equals(str) || "pref_show_predictions".equals(str) || "prediction_order_by_rank".equals(str)) {
            mo3142B(true);
        }
    }

    public final void mo3142B(boolean predictionsEnabled) {
        String string;
        boolean z2 = mPredicitons.getBoolean("pref_show_predictions", true);
        String str = null;
        if (z2) {
            string = mPredicitonReform.getString("reflection_last_predictions", null);
        } else {
            string = null;
        }
        if (z2) {
            str = mPredicitonReform.getString("prediction_order_by_rank", null);
        }
        mo3143a(z2, string, str, predictionsEnabled);
    }
}
