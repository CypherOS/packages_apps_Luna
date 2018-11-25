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

package com.android.launcher3;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.android.launcher3.IconCache;
import com.android.launcher3.IconCache.ItemInfoUpdateReceiver;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.predictions.PredictionRowView;
import com.android.launcher3.predictions.PredictionsFloatingHeader;
import com.android.launcher3.predictions.backport.C0781b;
import com.android.launcher3.predictions.backport.C0755a;
import com.android.launcher3.predictions.backport.C0783a;
import com.android.launcher3.predictions.backport.C0784b;
import com.android.launcher3.predictions.backport.C0799i;
import com.android.launcher3.predictions.states.StateManager;
import com.android.launcher3.util.ComponentKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictionsUiStateManager extends StateManager implements OnGlobalLayoutListener, ItemInfoUpdateReceiver, C0799i {

    private static boolean f1951xi = true;
    private final AllAppsContainerView mAppsView;
    private final IconCache mIconCache;
    private final Launcher mLauncher;
    /* renamed from: xf */
    private final int f1952xf;
    /* renamed from: xg */
    private final C0781b f1953xg;
    /* renamed from: xh */
    private final C0755a f1954xh;
    /* renamed from: xj */
    private List f1955xj;

    public PredictionsUiStateManager(Launcher launcher) {
        super(launcher);
        this.mLauncher = launcher;
        this.mIconCache = LauncherAppState.getInstance(launcher).mIconCache;
        this.mAppsView = (AllAppsContainerView) launcher.findViewById(2131623979);
        this.f1952xf = launcher.getDeviceProfile().inv.numColumns;
        this.f1953xg = C0781b.m2482f(launcher);
        this.f1954xh = C0755a.m2422a((Context) launcher);
    }

    public final void register() {
        super.register();
        this.f1953xg.f1039zB = this;
        this.f1954xh.f972xo = this;
    }

    public final void unregister() {
        super.unregister();
        this.f1953xg.f1039zB = null;
        this.f1954xh.f972xo = null;
    }

    /* renamed from: cF */
    public final List mo4927cF() {
        boolean predictionsEnabled = mPredicitons.getBoolean("pref_show_predictions", true);
        String str = null;
        if (predictionsEnabled) {
            str = mPredicitonReform.getString("reflection_last_predictions", null);
        }
        return m4712a(predictionsEnabled, str);
    }

    private List m4712a(boolean predictionsEnabled, String str) {
        if (!predictionsEnabled) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            for (String b : str.split(";")) {
                ComponentKey b2 = C0784b.m2492b(b, this.mLauncher);
                if (b2 != null) {
                    arrayList.add(new C0783a(this.mLauncher.getApplicationContext(), b2));
                }
            }
        }
        return arrayList;
    }

    public void onGlobalLayout() {
        if (!(this.f1955xj == null || this.mAppsView.isShown())) {
            m4714f(this.f1955xj);
            this.f1955xj = null;
        }
        if (this.f1955xj == null) {
            this.mAppsView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* renamed from: f */
    private void m4714f(List list) {
        PredictionRowView predictionRowView = ((PredictionsFloatingHeader) this.mAppsView.mHeader).f1929yj;
        predictionRowView.f1922yc.clear();
        predictionRowView.f1922yc.addAll(list);
        predictionRowView.onAppsUpdated();
    }
	
	@Override
    public final void mo3143a(boolean predictionsEnabled, String str, String str2, String str3, boolean z2) {
        List<C0783a> a = m4712a(predictionsEnabled, str);
        m4713a(z2, (List) a);
        int i = 0;
        for (C0783a c0783a : a) {
            if ("@instantapp".equals(c0783a.mo3138eZ())) {
                str2 = c0783a.getPackage();
                if (!(TextUtils.isEmpty(str2))) { // Add || mInvariantDeviceProfile.numColumns (c0781b.f819sv.f821sx.containsKey(str2))
                    Message.obtain(f1954xh.mWorker, 1, str2).sendToTarget();
                }
                i++;
            } else {
                ItemInfoWithIcon a2 = c0783a.mo3137a(this.mAppsView.mAllAppsStore);
                if (a2 != null) {
                    if (a2.usingLowResIcon) {
                        this.mIconCache.updateIconInBackground(this, a2);
                    }
                    i++;
                }
            }
            if (i >= this.f1952xf) {
                return;
            }
        }
    }

    public final void reapplyItemInfo(ItemInfoWithIcon itemInfoWithIcon) {
    }

    /* renamed from: a */
    private void m4713a(boolean z, List list) {
        if (!z || !this.mAppsView.isShown()) {
            m4714f(list);
            this.f1955xj = null;
        } else if (f1951xi) {
            m4714f(list);
        } else {
            Object obj = this.f1955xj == null ? 1 : null;
            this.f1955xj = list;
            if (obj != null) {
                this.mAppsView.getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        }
    }

    /* renamed from: cG */
    public final void mo3162cG() {
        m4713a(true, mo4927cF());
    }
}
