package com.android.launcher3.backports.allapps;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.Property;
import android.view.animation.Interpolator;

import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.allapps.AllAppsContainerView.AdapterHolder;
import com.android.launcher3.allapps.FloatingHeaderView;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.backports.PredicitonsHeaderListener;
import com.android.launcher3.util.Themes;

import com.android.quickstep.AnimatedFloat;

import com.android.launcher3.backports.PredictionUiStateManager;
import com.android.launcher3.backports.allapps.PredictionRowView.DividerType;

import java.util.List;

public class PredictionsFloatingHeader extends FloatingHeaderView implements Insettable, PredicitonsHeaderListener {

    private static final FloatProperty CONTENT_ALPHA = new C0839q("contentAlpha");
    /* renamed from: BZ */
    private final PredictionUiStateManager f2450BZ;
    /* renamed from: Bt */
    private boolean f2451Bt;
    /* renamed from: Bw */
    public boolean f2452Bw;
    /* renamed from: Ca */
    private final int f2453Ca;
    /* renamed from: Cb */
    public PredictionRowView f2454Cb;
    /* renamed from: Cc */
    public ActionsRowView f2455Cc;
    public float mContentAlpha;
    private boolean mIsVerticalLayout;

    public PredictionsFloatingHeader(Context context) {
        this(context, null);
    }

    public PredictionsFloatingHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContentAlpha = 1.0f;
        this.f2453Ca = context.getResources().getDimensionPixelSize(R.dimen.all_apps_header_top_padding);
        this.f2450BZ = PredictionUiStateManager.m2238a(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f2454Cb = (PredictionRowView) findViewById(R.id.prediction_row);
        this.f2455Cc = (ActionsRowView) findViewById(R.id.actions_row);
    }

    public final void setup(AdapterHolder[] adapterHolderArr, boolean z) {
        PredictionRowView predictionRowView = this.f2454Cb;
        boolean z2 = this.f2450BZ.f2416Ar.f1286Av;
        predictionRowView.f2447Br = this;
        predictionRowView.mo5383r(z2);
        ActionsRowView actionsRowView = this.f2455Cc;
        actionsRowView.f2418Br = this;
        actionsRowView.mo5366dd();
        this.mTabsHidden = z;
        actionsRowView = this.f2455Cc;
        z2 = this.mIsVerticalLayout && !this.mTabsHidden;
        actionsRowView.mo5371q(z2);
        m2265dj();
        super.setup(adapterHolderArr, z);
    }

    /* renamed from: dj */
    private void m2265dj() {
        CharSequence text;
        ActionsRowView actionsRowView = this.f2455Cc;
        int i = 0;
        boolean z = this.f2451Bt && this.mTabsHidden && this.f2455Cc.mo5365dc();
        if (actionsRowView.f2420Bt != z) {
            actionsRowView.f2420Bt = z;
            actionsRowView.setWillNotDraw(actionsRowView.f2420Bt ^ true);
            if (actionsRowView.f2420Bt) {
                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
                textPaint.setColor(ContextCompat.getColor(actionsRowView.getContext(), Themes.getAttrBoolean(actionsRowView.getContext(), R.attr.isMainColorDark) ? R.color.all_apps_label_text_dark : R.color.all_apps_label_text));
                textPaint.setTextSize((float) actionsRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_text_size));
                text = actionsRowView.getResources().getText(R.string.all_apps_label);
                actionsRowView.f2421Bu = Builder.obtain(text, 0, text.length(), textPaint, Math.round(textPaint.measureText(text.toString()))).setAlignment(Alignment.ALIGN_CENTER).setMaxLines(1).setIncludePad(true).build();
            } else {
                actionsRowView.f2421Bu = null;
            }
            actionsRowView.setPadding(actionsRowView.getPaddingLeft(), actionsRowView.getPaddingTop(), actionsRowView.getPaddingRight(), actionsRowView.f2420Bt ? (actionsRowView.f2421Bu.getHeight() + actionsRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_top_padding)) + actionsRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_bottom_padding) : 0);
        }
        DividerType dividerType = DividerType.NONE;
        if (this.f2451Bt && this.mTabsHidden && !this.f2455Cc.mo5365dc()) {
            dividerType = DividerType.ALL_APPS_LABEL;
        } else if (this.mTabsHidden && !this.f2455Cc.mo5365dc()) {
            dividerType = DividerType.LINE;
        }
        PredictionRowView predictionRowView = this.f2454Cb;
        if (predictionRowView.f2440BO != dividerType) {
            if (dividerType == DividerType.ALL_APPS_LABEL) {
                predictionRowView.f2435BJ.setAntiAlias(true);
                predictionRowView.f2435BJ.setTypeface(Typeface.create("sans-serif-medium", 0));
                predictionRowView.f2435BJ.setTextSize((float) predictionRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_text_size));
                text = predictionRowView.getResources().getText(R.dimen.all_apps_label);
                predictionRowView.f2448Bu = Builder.obtain(text, 0, text.length(), predictionRowView.f2435BJ, Math.round(predictionRowView.f2435BJ.measureText(text.toString()))).setAlignment(Alignment.ALIGN_CENTER).setMaxLines(1).setIncludePad(true).build();
            } else {
                predictionRowView.f2448Bu = null;
            }
        }
        predictionRowView.f2440BO = dividerType;
        int dimensionPixelSize = predictionRowView.f2440BO == DividerType.LINE ? predictionRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_prediction_row_divider_height) : predictionRowView.f2440BO == DividerType.ALL_APPS_LABEL ? (predictionRowView.f2448Bu.getHeight() + predictionRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_top_padding)) + predictionRowView.getResources().getDimensionPixelSize(R.dimen.all_apps_label_bottom_padding) : 0;
        predictionRowView.setPadding(predictionRowView.getPaddingLeft(), predictionRowView.getPaddingTop(), predictionRowView.getPaddingRight(), dimensionPixelSize);
        dimensionPixelSize = this.f2454Cb.getExpectedHeight();
        ActionsRowView actionsRowView2 = this.f2455Cc;
        if (actionsRowView2.mo5365dc()) {
            ActionView actionView = (ActionView) actionsRowView2.getChildAt(0);
            i = (((actionsRowView2.getPaddingTop() + actionsRowView2.getPaddingBottom()) + Math.max(actionView.getLineHeight(), actionView.mIconSize)) + actionView.getPaddingTop()) + actionView.getPaddingBottom();
        }
        this.mMaxTranslation = dimensionPixelSize + i;
    }

    public final int getMaxTranslation() {
        if (this.mMaxTranslation == 0 && this.mTabsHidden) {
            return getResources().getDimensionPixelSize(R.dimen.all_apps_search_bar_bottom_padding);
        }
        if (this.mMaxTranslation <= 0 || !this.mTabsHidden) {
            return this.mMaxTranslation;
        }
        return this.mMaxTranslation + getPaddingTop();
    }

    public void setInsets(Rect rect) {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        int i = deviceProfile.desiredWorkspaceLeftRightMarginPx + deviceProfile.cellLayoutPaddingLeftRightPx;
        this.f2454Cb.setPadding(i, this.f2454Cb.getPaddingTop(), i, this.f2454Cb.getPaddingBottom());
        this.mIsVerticalLayout = deviceProfile.isVerticalBarLayout();
        ActionsRowView actionsRowView = this.f2455Cc;
        boolean z = this.mIsVerticalLayout && !this.mTabsHidden;
        actionsRowView.mo5371q(z);
    }

    /* renamed from: dk */
    public final void mo5385dk() {
        int i = this.mMaxTranslation;
        m2265dj();
        if (this.mMaxTranslation != i) {
            Launcher.getLauncher(getContext()).mAppsView.setupHeader();
        }
    }

    protected final void applyScroll(int i, int i2) {
        if (i < i2 - this.f2453Ca) {
            this.f2454Cb.mo5384s(true);
            this.f2455Cc.setHidden(true);
            return;
        }
        this.f2455Cc.setHidden(false);
        float f = (float) i;
        this.f2455Cc.setTranslationY(f);
        this.f2454Cb.mo5384s(false);
        PredictionRowView predictionRowView = this.f2454Cb;
        predictionRowView.f2442BQ = f;
        predictionRowView.mo5375di();
    }

    public final void setContentVisibility(boolean z, boolean z2, PropertySetter propertySetter, Interpolator interpolator) {
        if (z && !z2 && this.f2452Bw) {
            Launcher.getLauncher(getContext()).mAppsView.mSearchUiManager.resetSearch();
        }
        this.mAllowTouchForwarding = z2;
        float f = 1.0f;
        propertySetter.setFloat(this, CONTENT_ALPHA, z2 ? 1.0f : 0.0f, interpolator);
        PredictionRowView predictionRowView = this.f2454Cb;
        int i = 0;
        int i2 = predictionRowView.getAlpha() > 0.0f ? 1 : 0;
        if (!z) {
            i = predictionRowView.f2434BI;
        } else if (z2) {
            i = predictionRowView.f2433BH;
        }
        if (i2 == 0) {
            predictionRowView.setAlpha(i);
        } else {
            propertySetter.setInt(predictionRowView, PredictionRowView.f2426BA, i, interpolator);
        }
        AnimatedFloat animatedFloat = predictionRowView.f2444BS;
        Property property = AnimatedFloat.VALUE;
        float f2 = (!z || z2) ? 0.0f : 1.0f;
        propertySetter.setFloat(animatedFloat, property, f2, Interpolators.LINEAR);
        AnimatedFloat animatedFloat2 = predictionRowView.f2443BR;
        Property property2 = AnimatedFloat.VALUE;
        if (!z) {
            f = 0.0f;
        }
        propertySetter.setFloat(animatedFloat2, property2, f, interpolator);
    }

    /* renamed from: t */
    public final void mo5386t(boolean z) {
        if (this.f2451Bt != z) {
            this.f2451Bt = z;
            mo5385dk();
        }
    }

    public final boolean hasVisibleContent() {
        return this.f2450BZ.f2416Ar.f1286Av;
    }

    public final void setAppsViewHeader(boolean enabled, List list) {
        PredictionRowView predictionRowView = this.f2454Cb;
        predictionRowView.mo5383r(enabled);
        predictionRowView.f2429BD.clear();
        predictionRowView.f2429BD.addAll(list);
        predictionRowView.onAppsUpdated();
    }
}
