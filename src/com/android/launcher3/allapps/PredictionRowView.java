package com.android.launcher3.allapps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.anim.PredictionInterpolator;
import com.android.launcher3.allapps.anim.PredictionScaleGroup;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.keyboard.FocusIndicatorHelper;
import com.android.launcher3.keyboard.FocusIndicatorHelper.SimpleFocusIndicatorHelper;
import com.android.launcher3.logging.UserEventDispatcher.LogContainerProvider;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.util.ComponentKeyMapper;
import com.android.launcher3.util.Themes;
import com.android.quickstep.AnimatedFloat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictionRowView extends LinearLayout implements LogContainerProvider, OnUpdateListener, OnDeviceProfileChangeListener {

    public static final Interpolator mAlphaInterpolator = PredictionInterpolator.INSTANCE;
    public static final Property<PredictionRowView, Integer> TEXT_ALPHA = new Property<PredictionRowView, Integer>(Integer.class, "textAlpha") {
        public void set(PredictionRowView predictionRowView, Integer value) {
            predictionRowView.setTextAlpha(value.intValue());
        }

        public Integer get(PredictionRowView predictionRowView) {
            return Integer.valueOf(predictionRowView.mIconCurrentTextAlpha);
        }
    };
    public Layout mAllAppsLabelLayout;
    public final int mAllAppsLabelTextColor;
    public int mAllAppsLabelTextCurrentAlpha;
    public final int mAllAppsLabelTextFullAlpha;
    public final TextPaint mAllAppsLabelTextPaint = new TextPaint();
    public final AnimatedFloat mContentAlphaFactor = new AnimatedFloat(new PredictionScaleGroup(this));
    public DividerType mDividerType;
    public final FocusIndicatorHelper mFocusHelper;
    public int mIconCurrentTextAlpha;
    public final int mIconFullTextAlpha;
    public final int mIconTextColor;
    public boolean mIsCollapsed = false;
    public final Launcher mLauncher;
    public View mLoadingProgress;
    public final int mNumPredictedAppsPerRow;
    public final AnimatedFloat mOverviewScrollFactor = new AnimatedFloat(new PredictionScaleGroup(this));
    public final Paint mPaint;
    public PredictionsFloatingHeader mParent;
    public final List<ComponentKeyMapper> mPredictedAppComponents = new ArrayList();
    public final ArrayList<ItemInfoWithIcon> mPredictedApps = new ArrayList();
    public boolean mPredictionsEnabled = false;
    public float mScrollTranslation = 0.0f;
    public boolean mScrolledOut;
    public final int mStrokeColor;

    public enum DividerType {
        NONE,
        LINE,
        ALL_APPS_LABEL
    }

    public static float setInterpolation(float input) {
        if (input < 0.8f) {
            return 0.0f;
        }
        return (input - 0.8f) / 0.2f;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public PredictionRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        setWillNotDraw(false);
        boolean isMainColorDark = Themes.getAttrBoolean(context, 2130968911);
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, isMainColorDark ? 2131099680 : 2131099679));
        mPaint.setStrokeWidth((float) getResources().getDimensionPixelSize(2131165267));
        mStrokeColor = mPaint.getColor();
        mFocusHelper = new SimpleFocusIndicatorHelper(this);
        mNumPredictedAppsPerRow = LauncherAppState.getIDP(context).numPredictions;
        mLauncher = Launcher.getLauncher(context);
        mLauncher.addOnDeviceProfileChangeListener(this);
        mIconTextColor = Themes.getAttrColor(context, 16842808);
        mIconFullTextAlpha = Color.alpha(mIconTextColor);
        mIconCurrentTextAlpha = mIconFullTextAlpha;
        mAllAppsLabelTextPaint.setColor(ContextCompat.getColor(context, isMainColorDark ? 2131099677 : 2131099676));
        mAllAppsLabelTextColor = mAllAppsLabelTextPaint.getColor();
        mAllAppsLabelTextFullAlpha = Color.alpha(mAllAppsLabelTextColor);
        mAllAppsLabelTextCurrentAlpha = mAllAppsLabelTextFullAlpha;
        updateVisibility();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAppsStore().addUpdateListener(this);
        getAppsStore().registerIconContainer(this);
    }

    public final AllAppsStore getAppsStore() {
        return mLauncher.getAppsView().getAppsStore();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getAppsStore().removeUpdateListener(this);
        getAppsStore().unregisterIconContainer(this);
    }

    public void setup(PredictionsFloatingHeader predictionsFloatingHeader, boolean z) {
        mParent = predictionsFloatingHeader;
        setPredictionsEnabled(z);
    }

    public final void setPredictionsEnabled(boolean z) {
        if (z != mPredictionsEnabled) {
            mPredictionsEnabled = z;
            updateVisibility();
        }
    }

    public final void updateVisibility() {
        int i;
        if (mPredictionsEnabled) {
            if (!mIsCollapsed) {
                i = 0;
                setVisibility(i);
            }
        }
        i = 8;
        setVisibility(i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getExpectedHeight(), 1073741824));
    }

    public void dispatchDraw(Canvas canvas) {
        mFocusHelper.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public int getExpectedHeight() {
        if (getVisibility() == 8) {
            return 0;
        }
        return (Launcher.getLauncher(getContext()).getDeviceProfile().allAppsCellHeightPx + getPaddingBottom()) + getPaddingTop();
    }

    @SuppressLint({"NewApi"})
    public void setDividerType(DividerType dividerType) {
        int i = 0;
        if (mDividerType != dividerType) {
            if (dividerType == DividerType.ALL_APPS_LABEL) {
                mAllAppsLabelTextPaint.setAntiAlias(true);
                mAllAppsLabelTextPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
                mAllAppsLabelTextPaint.setTextSize((float) getResources().getDimensionPixelSize(2131165278));
                CharSequence text = getResources().getText(2131951737);
                int length = text.length();
                TextPaint textPaint = mAllAppsLabelTextPaint;
                mAllAppsLabelLayout = Builder.obtain(text, 0, length, textPaint, Math.round(textPaint.measureText(text.toString()))).setAlignment(Alignment.ALIGN_CENTER).setMaxLines(1).setIncludePad(true).build();
            } else {
                mAllAppsLabelLayout = null;
            }
        }
        mDividerType = dividerType;
        DividerType dividerType2 = mDividerType;
        if (dividerType2 == DividerType.LINE) {
            i = getResources().getDimensionPixelSize(2131165280);
        } else if (dividerType2 == DividerType.ALL_APPS_LABEL) {
            i = getAllAppsLayoutFullHeight();
        }
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), i);
    }

    public List<ItemInfoWithIcon> getPredictedApps() {
        return mPredictedApps;
    }

    public void setPredictedApps(boolean z, List<ComponentKeyMapper> list) {
        setPredictionsEnabled(z);
        mPredictedAppComponents.clear();
        mPredictedAppComponents.addAll(list);
        onAppsUpdated();
    }

    public void onDeviceProfileChanged(DeviceProfile deviceProfile) {
        removeAllViews();
        applyPredictionApps();
    }

    public void onAppsUpdated() {
        mPredictedApps.clear();
        mPredictedApps.addAll(processPredictedAppComponents(mPredictedAppComponents));
        applyPredictionApps();
    }

    public final void applyPredictionApps() {
        View view = mLoadingProgress;
        if (view != null) {
            removeView(view);
        }
        if (getChildCount() != mNumPredictedAppsPerRow) {
            while (getChildCount() > mNumPredictedAppsPerRow) {
                removeViewAt(0);
            }
            while (getChildCount() < mNumPredictedAppsPerRow) {
                BubbleTextView bubbleTextView = (BubbleTextView) mLauncher.getLayoutInflater().inflate(2131623985, this, false);
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
        if (size == 0) {
            if (mLoadingProgress == null) {
                mLoadingProgress = LayoutInflater.from(getContext()).inflate(2131624082, this, false);
            }
            addView(mLoadingProgress);
        } else {
            mLoadingProgress = null;
        }
        mParent.headerChanged();
    }

    public final List<ItemInfoWithIcon> processPredictedAppComponents(List<ComponentKeyMapper> list) {
        if (getAppsStore().getApps().isEmpty()) {
            return Collections.emptyList();
        }
        List<ItemInfoWithIcon> arrayList = new ArrayList();
        for (ComponentKeyMapper app : list) {
            ItemInfoWithIcon app2 = app.getApp(getAppsStore());
            if (app2 != null) {
                arrayList.add(app2);
            }
            if (arrayList.size() == mNumPredictedAppsPerRow) {
                break;
            }
        }
        return arrayList;
    }

    public void onDraw(Canvas canvas) {
        DividerType dividerType = mDividerType;
        if (dividerType == DividerType.LINE) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131165400);
            float height = (float) (getHeight() - (getPaddingBottom() / 2));
            canvas.drawLine((float) (getPaddingLeft() + dimensionPixelSize), height, (float) ((getWidth() - getPaddingRight()) - dimensionPixelSize), height, mPaint);
        } else if (dividerType == DividerType.ALL_APPS_LABEL) {
            drawAllAppsHeader(canvas);
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

    public void setScrolledOut(boolean z) {
        mScrolledOut = z;
        updateTranslationAndAlpha();
    }

    public void setTextAlpha(int i) {
        int i2;
        mIconCurrentTextAlpha = i;
        int alphaComponent = ColorUtils.setAlphaComponent(mIconTextColor, mIconCurrentTextAlpha);
        if (mLoadingProgress == null) {
            for (i2 = 0; i2 < getChildCount(); i2++) {
                ((BubbleTextView) getChildAt(i2)).setTextColor(alphaComponent);
            }
        }
        i2 = mStrokeColor;
        i = ColorUtils.setAlphaComponent(i2, Math.round(((float) (Color.alpha(i2) * i)) / 255.0f));
        if (i != mPaint.getColor()) {
            mPaint.setColor(i);
            mAllAppsLabelTextCurrentAlpha = Math.round((float) ((mAllAppsLabelTextFullAlpha * mIconCurrentTextAlpha) / mIconFullTextAlpha));
            mAllAppsLabelTextPaint.setColor(ColorUtils.setAlphaComponent(mAllAppsLabelTextColor, mAllAppsLabelTextCurrentAlpha));
            if (mDividerType != DividerType.NONE) {
                invalidate();
            }
        }
    }

    public void setScrollTranslation(float f) {
        mScrollTranslation = f;
        updateTranslationAndAlpha();
    }

    public final void updateTranslationAndAlpha() {
        float f = 1.0f;
        setTranslationY((1.0f - mOverviewScrollFactor.value) * mScrollTranslation);
        float interpolation = mAlphaInterpolator.getInterpolation(mOverviewScrollFactor.value);
        float f2 = mContentAlphaFactor.value;
        float f3 = 1.0f - interpolation;
        if (mScrolledOut) {
            f = 0.0f;
        }
        setAlpha(f2 * ((f3 * f) + interpolation));
    }

    public void setContentVisibility(boolean hasHeader, boolean hasContent, PropertySetter propertySetter, Interpolator interpolator) {
        int i = 0;
        float f = 0.0f;
        boolean z = false;
        boolean visible = getAlpha() > 0.0f;
        if (!hasHeader) {
            i = mIconCurrentTextAlpha;
        } else if (hasContent) {
            i = mIconFullTextAlpha;
        }
        if (visible) {
            propertySetter.setInt(this, TEXT_ALPHA, i, interpolator);
        } else {
            setTextAlpha(i);
        }
        if (hasHeader && !hasContent) {
            z = true;
        }
        propertySetter.setFloat(mOverviewScrollFactor, AnimatedFloat.VALUE, z ? 1.0f : 0.0f, Interpolators.LINEAR);
        AnimatedFloat animatedFloat = mContentAlphaFactor;
        Property<AnimatedFloat, Float> property = AnimatedFloat.VALUE;
        if (hasHeader) {
            f = 1.0f;
        }
        propertySetter.setFloat(animatedFloat, property, f, interpolator);
    }

    public final void drawAllAppsHeader(Canvas canvas) {
        drawAllAppsHeader(canvas, this, mAllAppsLabelLayout);
    }

    public static void drawAllAppsHeader(Canvas canvas, View view, Layout allAppsLayout) {
        int width = (view.getWidth() / 2) - (allAppsLayout.getWidth() / 2);
        int height = (view.getHeight() - view.getResources().getDimensionPixelSize(2131165277)) - allAppsLayout.getHeight();
        canvas.translate((float) width, (float) height);
        allAppsLayout.draw(canvas);
        canvas.translate((float) (-width), (float) (-height));
    }

    public final int getAllAppsLayoutFullHeight() {
        return (mAllAppsLabelLayout.getHeight() + getResources().getDimensionPixelSize(2131165279)) + getResources().getDimensionPixelSize(2131165277);
    }

    public void setCollapsed(boolean z) {
        if (z != mIsCollapsed) {
            mIsCollapsed = z;
            updateVisibility();
        }
    }
}
