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

package co.aoscp.lovegood.allapps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import co.aoscp.lovegood.allapps.ShortcutsController.UpdateListener;

import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.logging.UserEventDispatcher.LogContainerProvider;
import com.android.launcher3.userevent.nano.LauncherLogProto.Target;
import com.android.launcher3.util.Themes;

import java.util.ArrayList;
import java.util.List;

public class ShortcutsRowView extends LinearLayout implements UpdateListener, LogContainerProvider {

	public static final String TAG = "ShortcutsRowView";

    public LauncherAccessibilityDelegate mActionAccessibilityDelegate;
	public ShortcutsController mShortcutsController;
	public Layout mAllAppsLabelLayout;
    public boolean mDisabled;
    public boolean mHidden;
    public boolean mIsCollapsed = false;
    public boolean mIsDarkTheme;
    public final Launcher mLauncher;
    public PredictionsFloatingHeader mParent;
	public boolean mShowAllAppsLabel;
    public int mSpacing;
	public int mPredictionsMinSize = 0;
	public boolean mEnabled;

    public ShortcutsRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        mLauncher = Launcher.getLauncher(getContext());
        mIsDarkTheme = Themes.getAttrBoolean(mLauncher, R.attr.isMainColorDark);
		mShortcutsController = ShortcutsController.get(getContext());
        mSpacing = context.getResources().getDimensionPixelSize(R.dimen.all_apps_action_spacing);
        mActionAccessibilityDelegate = mLauncher.getAccessibilityDelegate();
        mActionAccessibilityDelegate.addAccessibilityAction(R.id.action_dismiss_suggestion, R.string.dismiss_drop_target_label);
    }

    public void setup(PredictionsFloatingHeader predictionsHeader, boolean isEnabled) {
        mParent = predictionsHeader;
		setPredictionsEnabled(isEnabled);
    }

	public void setPredictionsEnabled(boolean enabled) {
        if (enabled != mEnabled) {
            mEnabled = enabled;
            updateVisibility();
        }
    }

    public int getExpectedHeight() {
        if (!shouldDraw()) {
            return 0;
        }
        ShortcutView shortcutView = (ShortcutView) getChildAt(0);
        return (((getPaddingTop() + getPaddingBottom()) + Math.max(shortcutView.getLineHeight(), shortcutView.getIconSize())) + shortcutView.getPaddingTop()) + shortcutView.getPaddingBottom();
    }

    @Override
    public void fillInLogContainerData(View view, ItemInfo itemInfo, Target target, Target target2) {
        target2.containerType = 7; //cardinality
    }

    @Override
    public void onMeasure(int width, int height) {
        DeviceProfile deviceProfile = mLauncher.getDeviceProfile();
        width = getRowWidth(MeasureSpec.getSize(width));
        super.onMeasure(MeasureSpec.makeMeasureSpec(((width - (DeviceProfile.calculateCellWidth(width, deviceProfile.inv.numHotseatIcons) - Math.round(((float) deviceProfile.iconSizePx) * 0.92f))) + getPaddingLeft()) + getPaddingRight(), 1073741824), height);
    }

    public int getRowWidth(int width) {
        if (mLauncher.getDeviceProfile().isVerticalBarLayout()) {
            return (width - mLauncher.getAppsView().getActiveRecyclerView().getPaddingLeft()) - mLauncher.getAppsView().getActiveRecyclerView().getPaddingRight();
        }
        View layout = mLauncher.getHotseat().getLayout();
        return (width - layout.getPaddingLeft()) - layout.getPaddingRight();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mShortcutsController.setListener(this);
    }

	@Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mShortcutsController.setListener(null);
    }

	public void setPredictedShortcuts(boolean isPredictions, ArrayList<Shortcut> list) {
        setPredictionsEnabled(isPredictions);
        onShortcutsUpdated(list);
    }

	@Override
    public void onShortcutsUpdated(ArrayList<Shortcut> predictions) {
		Log.d(TAG, "Updating shortcut predictions view");
        int i;
        mPredictionsMinSize = Math.min(2, predictions.size());
        if (getChildCount() != mPredictionsMinSize) {
            while (getChildCount() > mPredictionsMinSize) {
                removeViewAt(0);
            }
            while (getChildCount() < mPredictionsMinSize) {
                ShortcutView actionView = (ShortcutView) LayoutInflater.from(getContext()).inflate(R.layout.all_apps_actions_view, this, false);
                if (mIsDarkTheme) {
                    GradientDrawable gradientDrawable = (GradientDrawable) actionView.getBackground();
                    gradientDrawable.mutate();
                    gradientDrawable.setColor(872415231);
                }
                LayoutParams layoutParams = new LayoutParams(0, -1);
                layoutParams.weight = 1.0f;
                actionView.setLayoutParams(layoutParams);
                actionView.setAccessibilityDelegate(mActionAccessibilityDelegate);
                addView(actionView);
            }
            i = 0;
            while (i < mPredictionsMinSize) {
                ((LayoutParams) getChildAt(i).getLayoutParams()).setMarginEnd(i < mPredictionsMinSize + -1 ? mSpacing : 0);
                i++;
            }
        }
        for (i = 0; i < getChildCount(); i++) {
            ShortcutView view = (ShortcutView) getChildAt(i);
            view.reset();
            if (mPredictionsMinSize > i) {
				Log.d(TAG, "Predictions found making visible");
                view.setVisibility(View.VISIBLE);
                Shortcut shortcut = (Shortcut) predictions.get(i);
                ShortcutInfo shortcutInfo = shortcut.shortcutInfo;
                shortcutInfo.contentDescription = getContext().getString(R.string.suggested_action_content_description);
                view.applyFromShortcutInfo(shortcutInfo);
				StringBuilder details = new StringBuilder("Shortcut Predictions:");
				details.append(shortcutInfo);
				Log.d(TAG, details.toString());
                if (TextUtils.isEmpty(view.getText())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Empty ActionView text: action=");
                    sb.append(shortcut);
                    Log.e("ActionsRowView", sb.toString());
                }
            } else {
				Log.d(TAG, "No predictions found, making invisible");
                view.setVisibility(View.INVISIBLE);
            }
        }
        updateVisibility();
        mParent.headerChanged();
    }

	@Override
	public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShowAllAppsLabel) {
            drawAllAppsHeader(canvas);
        }
    }

    public void setHidden(boolean hidden) {
        mHidden = hidden;
        updateVisibility();
    }

    public void setDisabled(boolean disabled) {
        mDisabled = disabled;
        updateVisibility();
    }

	public void setShowAllAppsLabel(boolean show) {
        if (mShowAllAppsLabel != show) {
            mShowAllAppsLabel = show;
            setWillNotDraw(mShowAllAppsLabel);
            int height = 0;
            if (mShowAllAppsLabel) {
                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
                textPaint.setTextSize((float) getResources().getDimensionPixelSize(R.dimen.all_apps_label_text_size));
                CharSequence text = getResources().getText(R.string.all_apps_label);
                mAllAppsLabelLayout = Builder.obtain(text, 0, text.length(), textPaint, Math.round(textPaint.measureText(text.toString()))).setAlignment(Alignment.ALIGN_CENTER).setMaxLines(1).setIncludePad(true).build();
            } else {
                mAllAppsLabelLayout = null;
            }
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            if (mShowAllAppsLabel) {
                height = getAllAppsLayoutFullHeight();
            }
            setPadding(getPaddingLeft(), paddingTop, paddingRight, height);
        }
    }

    public boolean shouldDraw() {
        return !mDisabled && getChildCount() > 0 && !mIsCollapsed;
    }

    public void updateVisibility() {
		int visibility = !shouldDraw() ? View.GONE : mHidden ? View.INVISIBLE : View.VISIBLE;
        setVisibility(mEnabled && visibility);
    }

	public void drawAllAppsHeader(Canvas canvas) {
        PredictionRowView.drawAllAppsHeader(canvas, this, mAllAppsLabelLayout);
    }

    public int getAllAppsLayoutFullHeight() {
        return (mAllAppsLabelLayout.getHeight() + getResources().getDimensionPixelSize(R.dimen.all_apps_label_top_padding)) 
		        + getResources().getDimensionPixelSize(R.dimen.all_apps_label_bottom_padding);
    }

    public void setCollapsed(boolean collapsed) {
        if (collapsed != mIsCollapsed) {
            mIsCollapsed = collapsed;
            updateVisibility();
        }
    }
}
