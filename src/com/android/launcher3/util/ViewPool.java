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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPool {

    public int mCurrentSize = 0;
    public final LayoutInflater mInflater;
    public final int mLayoutId;
    public final ViewGroup mParent;
    public final Object[] mPool;

    public interface Reusable {
        void onRecycle();
    }

    public ViewPool(Context context, ViewGroup viewGroup, int layoutId, int size, int count) {
        mLayoutId = layoutId;
        mParent = viewGroup;
        mInflater = LayoutInflater.from(context);
        mPool = new Object[size];
        if (count > 0) {
            initPool(count);
        }
    }

    public View getView() {
        if (mCurrentSize <= 0) {
            return mInflater.inflate(mLayoutId, mParent, false);
        }
        mCurrentSize = mCurrentSize - 1;
        return (View) mPool[mCurrentSize];
    }

    private void initPool(int count) {
        new Thread(new ViewPoolOffload(this, count, new Handler())).start();
    }

    public void recycle(View view) {
        ((Reusable) view).onRecycle();
        if (mCurrentSize < mPool.length) {
            mPool[mCurrentSize] = view;
            mCurrentSize = mCurrentSize + 1;
        }
    }

	public void postRecycle() {
        if (mCurrentSize < mPool.length) {
            mPool[mCurrentSize] = getView();
            mCurrentSize = mCurrentSize + 1;
        }
    }

    public void startRecycle(int count, Handler handler) {
        for (int i = 0; i < count; i++) {
			handler.post(new Runnable() {
				@Override
				public void run() {
                    postRecycle();
				}
			});
        }
    }
}
