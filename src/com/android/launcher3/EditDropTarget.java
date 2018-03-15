/*
 * Copyright (C) 2017 Paranoid Android
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
import android.content.res.Resources;
import android.util.AttributeSet;

import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;

public class EditDropTarget extends ButtonDropTarget {

    private static Context mContext;
    private Launcher mLauncher;
	private static ShortcutInfoCompat mShortcutInfo;

    public EditDropTarget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditDropTarget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLauncher = Launcher.getLauncher(context);
		mContext = context;
		mShortcutInfo = new ShortcutInfoCompat(ShortcutInfoCompat.getShortcutInfo());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setDrawable(R.drawable.ic_edit_launcher);
    }

    @Override
    protected boolean supportsDrop(DragSource source, ItemInfo info) {
        return info instanceof AppInfo || info instanceof ShortcutInfo;
    }

    @Override
    public void completeDrop(DragObject d) {
        startEditApplicationDialog(d.dragInfo, mLauncher);
        
    }
	
	public static boolean startEditApplicationDialog(ItemInfo info, Launcher launcher) {
        ComponentName componentName = null;
        if (info instanceof AppInfo) {
            componentName = ((AppInfo) info).componentName;
        } else if (info instanceof ShortcutInfo) {
            componentName = info.getTargetComponent();
        }

        if (componentName != null) {
            launcher.startEdit(info, componentName);
        }
		return true;
    }
}
