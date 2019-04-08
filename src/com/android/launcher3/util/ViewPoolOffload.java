/**
 * Copyright (C) 2019 CypherOS
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

package com.android.launcher3.util;

import android.content.Context;
import android.os.Handler;

import com.android.launcher3.util.ViewPool;

public class ViewPoolOffload implements Runnable {

    private ViewPool mViewPool;
    private int mCount;
    private Handler mHandler;

    public ViewPoolOffload(ViewPool viewPool, int count, Handler handler) {
		mViewPool = viewPool;
        mCount = count;
        mHandler = handler;
    }

    @Override
    public void run() {
        mViewPool.startRecycle(mCount, mHandler);
    }
}
