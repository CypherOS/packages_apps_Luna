package com.android.launcher3.predictions;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatProperty;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.allapps.AllAppsContainerView.AdapterHolder;
import com.android.launcher3.allapps.FloatingHeaderView;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertySetter;

public class PredictionsFloatingHeader extends FloatingHeaderView implements Insettable {
    private static final FloatProperty CONTENT_ALPHA = new C0768l("contentAlpha");
    private float mContentAlpha;
    private boolean mIsVerticalLayout;
    /* renamed from: yi */
    private final int f1928yi;
    /* renamed from: yj */
    public PredictionRowView f1929yj;
    /* renamed from: yk */
    private ActionsRowView f1930yk;

    /* renamed from: a */
    static /* synthetic */ void m4671a(PredictionsFloatingHeader predictionsFloatingHeader, float f) {
        predictionsFloatingHeader.mContentAlpha = f;
        predictionsFloatingHeader.mTabLayout.setAlpha(f);
        predictionsFloatingHeader.f1930yk.setAlpha(f);
    }

    public PredictionsFloatingHeader(Context context) {
        this(context, null);
    }

    public PredictionsFloatingHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContentAlpha = 1.0f;
        this.f1928yi = context.getResources().getDimensionPixelSize(2131427392);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f1929yj = (PredictionRowView) findViewById(2131623984);
        this.f1930yk = (ActionsRowView) findViewById(2131623985);
    }

    public final void setup(AdapterHolder[] adapterHolderArr, boolean z) {
        PredictionRowView predictionRowView = this.f1929yj;
        predictionRowView.f1920xX = this;
        predictionRowView.mo4900cP();
        ActionsRowView actionsRowView = this.f1930yk;
        actionsRowView.f1915xX = this;
        actionsRowView.mo4894cP();
        this.mTabsHidden = z;
        actionsRowView = this.f1930yk;
        boolean z2 = this.mIsVerticalLayout && !this.mTabsHidden;
        actionsRowView.mo4898q(z2);
        m4672cS();
        super.setup(adapterHolderArr, z);
    }

    /* renamed from: cS */
    private void m4672cS() {
        PredictionRowView predictionRowView = this.f1929yj;
        int i = 0;
        boolean z = this.mTabsHidden && !this.f1930yk.mo4893cO();
        predictionRowView.f1926yg = z;
        predictionRowView.setPadding(predictionRowView.getPaddingLeft(), predictionRowView.getPaddingTop(), predictionRowView.getPaddingRight(), z ? predictionRowView.getResources().getDimensionPixelSize(2131427523) : 0);
        int expectedHeight = this.f1929yj.getExpectedHeight();
        ActionsRowView actionsRowView = this.f1930yk;
        if (actionsRowView.mo4893cO()) {
            ActionView actionView = (ActionView) actionsRowView.getChildAt(0);
            i = (((actionsRowView.getPaddingTop() + actionsRowView.getPaddingBottom()) + Math.max(actionView.getLineHeight(), actionView.mIconSize)) + actionView.getPaddingTop()) + actionView.getPaddingBottom();
        }
        this.mMaxTranslation = expectedHeight + i;
    }

    public final int getMaxTranslation() {
        if (this.mMaxTranslation == 0 && this.mTabsHidden) {
            return getResources().getDimensionPixelSize(2131427385);
        }
        if (this.mMaxTranslation <= 0 || !this.mTabsHidden) {
            return this.mMaxTranslation;
        }
        return this.mMaxTranslation + getPaddingTop();
    }

    public void setInsets(Rect rect) {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        int i = deviceProfile.desiredWorkspaceLeftRightMarginPx + deviceProfile.cellLayoutPaddingLeftRightPx;
        this.f1929yj.setPadding(i, this.f1929yj.getPaddingTop(), i, this.f1929yj.getPaddingBottom());
        this.mIsVerticalLayout = deviceProfile.isVerticalBarLayout();
        ActionsRowView actionsRowView = this.f1930yk;
        boolean z = this.mIsVerticalLayout && !this.mTabsHidden;
        actionsRowView.mo4898q(z);
    }

    /* renamed from: cT */
    public final void mo4909cT() {
        int i = this.mMaxTranslation;
        m4672cS();
        if (this.mMaxTranslation != i) {
            Launcher.getLauncher(getContext()).mAppsView.setupHeader();
        }
    }

    protected final void applyScroll(int i, int i2) {
        if (i < i2 - this.f1928yi) {
            this.f1929yj.setHidden(true);
            this.f1930yk.setHidden(true);
            return;
        }
        this.f1929yj.setHidden(false);
        this.f1930yk.setHidden(false);
        float f = (float) i;
        this.f1929yj.setTranslationY(f);
        this.f1930yk.setTranslationY(f);
    }

    public final void setContentVisibility(boolean z, boolean z2, PropertySetter propertySetter) {
        int i;
        this.mAllowTouchForwarding = z2;
        float f = 0.0f;
        int i2 = 0;
        if (Float.compare(this.f1929yj.getAlpha(), 0.0f) != 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (!z) {
            i2 = this.f1929yj.f1927yh;
        } else if (z2) {
            i2 = 255;
        }
        if (i == 0) {
            this.f1929yj.setTextAlpha(i2);
        } else {
            propertySetter.setInt(this.f1929yj, PredictionRowView.f1918ya, i2, Interpolators.LINEAR);
        }
        propertySetter.setFloat(this.f1929yj, ALPHA, z ? 1.0f : 0.0f, Interpolators.LINEAR);
        FloatProperty floatProperty = CONTENT_ALPHA;
        if (z2) {
            f = 1.0f;
        }
        propertySetter.setFloat(this, floatProperty, f, Interpolators.LINEAR);
    }

    public final boolean hasVisibleContent() {
        return this.f1929yj.getTranslationY() >= 0.0f && !this.f1929yj.f1923yd.isEmpty();
    }
}
