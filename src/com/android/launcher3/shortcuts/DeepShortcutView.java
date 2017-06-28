/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.android.launcher3.shortcuts;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.Utilities;

public class DeepShortcutView extends FrameLayout {
    private static final Point sTempPoint = new Point();
    private BubbleTextView mBubbleText;
    private ShortcutInfoCompat mDetail;
    private View mIconView;
    private ShortcutInfo mInfo;
    private final Rect mPillRect;

    public DeepShortcutView(Context context) {
        this(context, null, 0);
    }

    public DeepShortcutView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DeepShortcutView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPillRect = new Rect();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBubbleText = (BubbleTextView) findViewById(R.id.bubble_text);
        this.mIconView = findViewById(R.id.icon);
    }

    public BubbleTextView getBubbleText() {
        return this.mBubbleText;
    }

    public void setWillDrawIcon(boolean z) {
        this.mIconView.setVisibility(z ? VISIBLE : INVISIBLE);
    }

    public Point getIconCenter() {
        Point point = sTempPoint;
        int measuredHeight = getMeasuredHeight() / 2;
        sTempPoint.x = measuredHeight;
        point.y = measuredHeight;
        if (Utilities.isRtl(getResources())) {
            sTempPoint.x = getMeasuredWidth() - sTempPoint.x;
        }
        return sTempPoint;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPillRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void applyShortcutInfo(ShortcutInfo shortcutInfo, ShortcutInfoCompat shortcutInfoCompat, ShortcutsItemView shortcutsItemView) {
        Object obj = null;
        this.mInfo = shortcutInfo;
        this.mDetail = shortcutInfoCompat;
        this.mBubbleText.applyFromShortcutInfo(shortcutInfo);
        this.mIconView.setBackground(this.mBubbleText.getIcon());
        CharSequence longLabel = this.mDetail.getLongLabel();
        int width = (this.mBubbleText.getWidth() - this.mBubbleText.getTotalPaddingLeft()) - this.mBubbleText.getTotalPaddingRight();
        if (!TextUtils.isEmpty(longLabel) && this.mBubbleText.getPaint().measureText(longLabel.toString()) <= ((float) width)) {
            obj = 1;
        }
        this.mBubbleText.setText(obj != null ? longLabel : this.mDetail.getShortLabel());
        this.mBubbleText.setOnClickListener(Launcher.getLauncher(getContext()));
        this.mBubbleText.setOnLongClickListener(shortcutsItemView);
        this.mBubbleText.setOnTouchListener(shortcutsItemView);
    }

    public ShortcutInfo getFinalInfo() {
        ShortcutInfo shortcutInfo = new ShortcutInfo(this.mInfo);
        Launcher.getLauncher(getContext()).getModel().updateAndBindShortcutInfo(shortcutInfo, this.mDetail);
        return shortcutInfo;
    }

    public View getIconView() {
        return this.mIconView;
    }
}
