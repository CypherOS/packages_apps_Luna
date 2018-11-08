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
package com.android.launcher3.qsb.hotseat.search;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.launcher3.BaseActivity;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageManagerHelper;

public class AppLaunchActivity extends BaseActivity {
    private void dk(Uri uri) {
        try {
            ComponentKey dl = AppSearchProvider.dl(uri, this);
            ItemInfo dVar = new AppItemInfoWithIcon(dl);
            if (!getPackageManager().isSafeMode() || Utilities.isSystemApp(this, dVar.getIntent())) {
                if (dl.user.equals(android.os.Process.myUserHandle())) {
                    startActivity(dVar.getIntent());
                } else {
                    LauncherAppsCompat.getInstance(this).startActivityForProfile(dl.componentName, dl.user, getIntent().getSourceBounds(), null);
                }
                View view = new View(this);
                view.setTag(dVar);
                /*int i = 2;
                LauncherModel.Callbacks callback = LauncherAppState.getInstance(this).getModel().getCallback();
                if (callback instanceof Launcher) {
                    i = ((Launcher) callback).getWorkspace().getState().containerType;
                }*/
                String queryParameter = uri.getQueryParameter("predictionRank");
                new LogContainerProvider(this, TextUtils.isEmpty(queryParameter) ? -1 : Integer.parseInt(queryParameter)).addView(view);
                return;
            }
            Toast.makeText(this, R.string.safemode_shortcut_error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
        }

    }

    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mDeviceProfile = LauncherAppState.getIDP(this).getDeviceProfile(this);
        final Uri data = this.getIntent().getData();
        if (data != null) {
            this.dk(data);
        } else {
            final String stringExtra = this.getIntent().getStringExtra("query");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.startActivity(PackageManagerHelper.getMarketSearchIntent(this, stringExtra));
            }
        }
        this.finish();
    }
}
