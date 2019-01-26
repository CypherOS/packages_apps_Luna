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
package co.aoscp.lovegood.micode.biometrics;

import static com.android.launcher3.logging.LoggerUtils.newContainerTarget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget.DragObject;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.graphics.ColorScrim;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.userevent.nano.LauncherLogProto.ContainerType;
import com.android.launcher3.userevent.nano.LauncherLogProto.Target;
import com.android.launcher3.util.SystemUiController;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.AbstractSlideInView;

abstract class BaseBiometricsPrompt extends AbstractSlideInView
        implements OnClickListener, OnLongClickListener, DragSource {

    protected final ColorScrim mColorScrim;

    public BaseBiometricsPrompt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mColorScrim = ColorScrim.createExtractedColorScrim(this);
    }

    @Override
    public final void onClick(View v) {
    }

    @Override
    public final boolean onLongClick(View v) {
        return false;
    }

    protected void setTranslationShift(float translationShift) {
        super.setTranslationShift(translationShift);
        mColorScrim.setProgress(1 - mTranslationShift);
    }


    protected void onCloseComplete() {
        super.onCloseComplete();
        clearNavBarColor();
    }

    protected void clearNavBarColor() {
        mLauncher.getSystemUiController().updateUiState(
                SystemUiController.UI_STATE_WIDGET_BOTTOM_SHEET, 0);
    }

    protected void setupNavBarColor() {
        boolean isSheetDark = Themes.getAttrBoolean(mLauncher, R.attr.isMainColorDark);
        mLauncher.getSystemUiController().updateUiState(
                SystemUiController.UI_STATE_WIDGET_BOTTOM_SHEET,
                isSheetDark ? SystemUiController.FLAG_DARK_NAV : SystemUiController.FLAG_LIGHT_NAV);
    }

    @Override
    public void onDropCompleted(View target, DragObject d, boolean success) { }

    @Override
    public void fillInLogContainerData(View v, ItemInfo info, Target target, Target targetParent) {
        targetParent.containerType = ContainerType.WIDGETS;
        targetParent.cardinality = getElementsRowCount();
    }

    @Override
    public final void logActionCommand(int command) {
        Target target = newContainerTarget(ContainerType.WIDGETS);
        target.cardinality = getElementsRowCount();
        mLauncher.getUserEventDispatcher().logActionCommand(command, target);
    }

    protected abstract int getElementsRowCount();

}