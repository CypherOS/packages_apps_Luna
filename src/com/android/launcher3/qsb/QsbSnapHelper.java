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
package com.android.launcher3.qsb;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.android.launcher3.BaseRecyclerView;

public class QsbSnapHelper extends SnapHelper {

    private AllAppsQsbLayout mQsbLayout;

    private QsbSnapHelper(AllAppsQsbLayout allAppsQsbLayout) {
        mQsbLayout = allAppsQsbLayout;
    }

    QsbSnapHelper(AllAppsQsbLayout allAppsQsbLayout, byte bytes) {
        this(allAppsQsbLayout);
    }

    public final void onScrolled(RecyclerView recyclerView, int i) {
        mQsbLayout.useAlpha(((BaseRecyclerView) recyclerView).getCurrentScrollY());
    }
}
