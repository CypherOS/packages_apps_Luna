/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.launcher3.allapps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.logging.UserEventDispatcher;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ComponentKeyMapper;
import com.android.launcher3.util.Themes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PredictionRowView extends LinearLayout implements AllAppsStore.OnUpdateListener, OnDeviceProfileChangeListener, 
        UserEventDispatcher.LogContainerProvider {

    private static final String TAG = "PredictionRowView";

    private int mNumPredictedAppsPerRow;
    // The set of predicted app component names
    private final List<ComponentKeyMapper<AppInfo>> mPredictedAppComponents = new ArrayList<>();
    // The set of predicted apps resolved from the component names and the current set of apps
    private final ArrayList<AppInfo> mPredictedApps = new ArrayList<>();
    private final Paint mPaint;
    // This adapter is only used to create an identical item w/ same behavior as in the all apps RV
    private AllAppsGridAdapter mAdapter;
    private boolean mShowDivider;

	public Launcher mLauncher;
	public int mIconCurrentTextAlpha;
    public int mIconFullTextAlpha;
    public int mIconTextColor;
	public boolean mIsCollapsed = false;
	public boolean mPredictionsEnabled = false;

    public PredictionRowView(@NonNull Context context) {
        this(context, null);
    }

    public PredictionRowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setColor(Themes.getAttrColor(context, android.R.attr.colorControlHighlight));
        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.all_apps_divider_height));
		mLauncher = Launcher.getLauncher(context);
		mIconTextColor = Themes.getAttrColor(context, android.R.attr.textColorSecondary);
        mIconFullTextAlpha = Color.alpha(mIconTextColor);
        mIconCurrentTextAlpha = mIconFullTextAlpha;
		updateVisibility();
    }

    public void setup(AllAppsGridAdapter adapter, int numPredictedAppsPerRow) {
        mAdapter = adapter;
        mNumPredictedAppsPerRow = numPredictedAppsPerRow;
		setPredictionsEnabled(true);
    }

	@Override
	public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAppsStore().addUpdateListener(this);
    }
	
	public final AllAppsStore getAppsStore() {
        return mLauncher.getAppsView().getAppsStore();
    }

	@Override
	public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getAppsStore().removeUpdateListener(this);
    }
	
	public void setPredictionsEnabled(boolean enabled) {
        if (enabled != mPredictionsEnabled) {
            mPredictionsEnabled = enabled;
            updateVisibility();
        }
    }
	
	public void updateVisibility() {
        int visibility;
        if (mPredictionsEnabled) {
            if (!mIsCollapsed) {
                visibility = View.GONE;
                setVisibility(visibility);
				return;
            }
        }
        visibility = View.VISIBLE;
        setVisibility(visibility);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(getExpectedHeight(),
                MeasureSpec.EXACTLY));
    }

    public int getExpectedHeight() {
        int height = 0;
        if (!mPredictedAppComponents.isEmpty()) {
            height += Launcher.getLauncher(getContext())
                    .getDeviceProfile().allAppsCellHeightPx;
            height += getPaddingTop() + getPaddingBottom();
        }
        return height;
    }

    public void setShowDivider(boolean showDivider) {
        mShowDivider = showDivider;
        int paddingBottom = showDivider ? getResources()
                .getDimensionPixelSize(R.dimen.all_apps_prediction_row_divider_height) : 0;
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), paddingBottom);
    }

    /**
     * Sets the number of apps per row.
     */
    public void setNumAppsPerRow(int numPredictedAppsPerRow) {
        if (mNumPredictedAppsPerRow != numPredictedAppsPerRow) {
            mNumPredictedAppsPerRow = numPredictedAppsPerRow;
            onPredictionsUpdated();
        }
    }

    /**
     * Returns the predicted apps.
     */
    public List<AppInfo> getPredictedApps() {
        return mPredictedApps;
    }

    /**
     * Sets the current set of predicted apps.
     *
     * This can be called before we get the full set of applications, we should merge the results
     * only in onPredictionsUpdated() which is idempotent.
     *
     * If the number of predicted apps is the same as the previous list of predicted apps,
     * we can optimize by swapping them in place.
     */
    public void setPredictedApps(List<ComponentKeyMapper<AppInfo>> apps) {
        mPredictedAppComponents.clear();
        mPredictedAppComponents.addAll(apps);
		onAppsUpdated();
    }

    private void onPredictionsUpdated() {
        int childCountBefore = getChildCount();
        if (getChildCount() != mNumPredictedAppsPerRow) {
            while (getChildCount() > mNumPredictedAppsPerRow) {
                removeViewAt(0);
            }
            while (getChildCount() < mNumPredictedAppsPerRow) {
				BubbleTextView bubbleTextView = (BubbleTextView) mLauncher.getLayoutInflater().inflate(R.layout.all_apps_icon, this, false);
                bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
                bubbleTextView.setOnLongClickListener(ItemLongClickListener.INSTANCE_ALL_APPS);
                bubbleTextView.setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
                bubbleTextView.setOnFocusChangeListener(mFocusHelper);
                LayoutParams layoutParams = (LayoutParams) bubbleTextView.getLayoutParams();
                layoutParams.height = getExpectedHeight();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                addView(bubbleTextView);
            }
        }

		int size = mPredictedApps.size();
        int alphaComponent = ColorUtils.setAlphaComponent(mIconTextColor, mIconCurrentTextAlpha);
        for (int i = 0; i < getChildCount(); i++) {
            BubbleTextView bubbleTextView2 = (BubbleTextView) getChildAt(i);
            bubbleTextView2.reset();
            if (size > i) {
                bubbleTextView2.setVisibility(0);
                if (mPredictedApps.get(i) instanceof AppInfo) {
                    bubbleTextView2.applyFromApplicationInfo((AppInfo) mPredictedApps.get(i));
                } else if (mPredictedApps.get(i) instanceof ShortcutInfo) {
                    bubbleTextView2.applyFromShortcutInfo((ShortcutInfo) mPredictedApps.get(i));
                }
                bubbleTextView2.setTextColor(alphaComponent);
            } else {
                bubbleTextView2.setVisibility(size == 0 ? 8 : 0);
            }
        }

        if (getChildCount() > 0 && childCountBefore == 0
                || getChildCount() == 0 && childCountBefore > 0) {
            // setting up header to adjust the height
            // only necessary if childcount switches from/to 0
            Launcher.getLauncher(getContext()).getAppsView().setupHeader();
        }
    }
	
	public void onDeviceProfileChanged(DeviceProfile deviceProfile) {
        removeAllViews();
        onPredictionsUpdated();
    }

    /**
     * Refreshes the app icons in the row view, while preserving the same set of predictions.
     */
	@Override
    public void onAppsUpdated() {
		mPredictedApps.clear();
        mPredictedApps.addAll(processPredictedAppComponents(mPredictedAppComponents));
        onPredictionsUpdated();
    }

    private List<AppInfo> processPredictedAppComponents(List<ComponentKeyMapper<AppInfo>> components) {
		if (getAppsStore().getApps().isEmpty()) {
            return Collections.emptyList();
        }
        List<ItemInfoWithIcon> predictedApps = new ArrayList();
        for (ComponentKeyMapper app : components) {
            ItemInfoWithIcon app2 = app.getApp(getAppsStore());
            if (app2 != null) {
                predictedApps.add(app2);
            }
            if (predictedApps.size() == mNumPredictedAppsPerRow) {
                break;
            }
        }
        return predictedApps;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mShowDivider) {
            int side = getResources().getDimensionPixelSize(R.dimen.dynamic_grid_edge_margin);
            int y = getHeight() - (getPaddingBottom() / 2);
            int x1 = getPaddingLeft() + side;
            int x2 = getWidth() - getPaddingRight() - side;
            canvas.drawLine(x1, y, x2, y, mPaint);
        }
    }

    @Override
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target,
            LauncherLogProto.Target targetParent) {
        for (int i = 0; i < mPredictedApps.size(); i++) {
            AppInfo appInfo = mPredictedApps.get(i);
            if (appInfo == info) {
                targetParent.containerType = LauncherLogProto.ContainerType.PREDICTION;
                target.predictedRank = i;
                break;
            }
        }
    }

	public void setCollapsed(boolean collapsed) {
        if (collapsed != mIsCollapsed) {
            mIsCollapsed = collapsed;
            updateVisibility();
        }
    }
}