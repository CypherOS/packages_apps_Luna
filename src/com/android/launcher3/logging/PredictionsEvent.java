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

package com.android.launcher3.logging;

import android.content.Context;
import android.text.TextUtils;

import com.android.launcher3.predictions.backport.C0784b;
import com.android.launcher3.predictions.states.StateManager;
import com.android.launcher3.util.ComponentKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictionsEvent extends StateManager {

    List mApps = new ArrayList();
    private final Context mContext;
    boolean mIsPredictionsEnabled;

    public PredictionsEvent(Context context) {
        super(context);
        mContext = context;
    }

    public final void mo3143a(boolean predictionsEnabled, String str, String str2, boolean z2) {
        mIsPredictionsEnabled = predictionsEnabled;
        if (!predictionsEnabled) {
            mApps = Collections.emptyList();
        } else if (TextUtils.isEmpty(str2)) {
            mApps = Collections.emptyList();
        } else {
            ArrayList arrayList = new ArrayList();
            for (String b : str2.split(";")) {
                ComponentKey b2 = C0784b.m2492b(b, mContext);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
            mApps = arrayList;
        }
    }
}
