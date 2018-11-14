package com.android.launcher3.backports.allapps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.widget.LinearLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.logging.UserEventDispatcher.LogContainerProvider;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.userevent.nano.LauncherLogExtensions.TargetExtension;
import com.android.launcher3.userevent.nano.LauncherLogProto.Target;
import java.util.ArrayList;

public class ActionsRowView extends LinearLayout implements LogContainerProvider, C0836l {
    /* renamed from: Bq */
    private C0829d f2417Bq;
    /* renamed from: Br */
    PredictionsFloatingHeader f2418Br;
    /* renamed from: Bs */
    private boolean f2419Bs;
    /* renamed from: Bt */
    boolean f2420Bt;
    /* renamed from: Bu */
    Layout f2421Bu;
    /* renamed from: Bv */
    private int f2422Bv;
    /* renamed from: Bw */
    public boolean f2423Bw;
    /* renamed from: Bx */
    private LauncherAccessibilityDelegate f2424Bx;
    /* renamed from: ds */
    private boolean f2425ds;
    private boolean mIsDarkTheme;
    public final Launcher mLauncher;

    public ActionsRowView(Context context) {
        this(context, null);
    }

    public ActionsRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2423Bw = false;
        setOrientation(0);
        this.mLauncher = Launcher.getLauncher(getContext());
        this.mIsDarkTheme = WallpaperColorInfo.getInstance(context).mExtractionInfo.supportsDarkTheme;
        this.f2422Bv = context.getResources().getDimensionPixelSize(2131165185);
        this.f2424Bx = new C1811m(this, this.mLauncher);
        LauncherAccessibilityDelegate launcherAccessibilityDelegate = this.f2424Bx;
        launcherAccessibilityDelegate.mActions.put(2131361797, new AccessibilityAction(2131361797, launcherAccessibilityDelegate.mLauncher.getText(2131820627)));
    }

    public final void fillInLogContainerData(View view, ItemInfo itemInfo, Target target, Target target2) {
        target2.containerType = 7;
        target2.extension = new TargetExtension();
        target2.extension.isActionSuggestion = true;
    }

    protected void onMeasure(int i, int i2) {
        int paddingRight;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        i = MeasureSpec.getSize(i);
        if (this.mLauncher.getDeviceProfile().isVerticalBarLayout()) {
            i -= this.mLauncher.mAppsView.getActiveRecyclerView().getPaddingLeft();
            paddingRight = this.mLauncher.mAppsView.getActiveRecyclerView().getPaddingRight();
        } else {
            View view = this.mLauncher.mHotseat.mContent;
            i -= view.getPaddingLeft();
            paddingRight = view.getPaddingRight();
        }
        i -= paddingRight;
        super.onMeasure(MeasureSpec.makeMeasureSpec(((i - ((i / deviceProfile.inv.numHotseatIcons) - Math.round(0.92f * ((float) deviceProfile.iconSizePx)))) + getPaddingLeft()) + getPaddingRight(), 1073741824), i2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2417Bq = C0829d.m656c(getContext());
        this.f2417Bq.f1216Be = this;
        mo3431e(this.f2417Bq.f1211AZ);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2417Bq.f1216Be = null;
    }

    /* renamed from: e */
    public final void mo3431e(ArrayList arrayList) {
        int i;
        int min = Math.min(2, arrayList.size());
        if (getChildCount() != min) {
            while (getChildCount() > min) {
                removeViewAt(0);
            }
            while (getChildCount() < min) {
                ActionView actionView = (ActionView) LayoutInflater.from(getContext()).inflate(2131558402, this, false);
                if (this.mIsDarkTheme) {
                    GradientDrawable gradientDrawable = (GradientDrawable) actionView.getBackground();
                    gradientDrawable.mutate();
                    gradientDrawable.setColor(872415231);
                }
                LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
                layoutParams.weight = 1.0f;
                actionView.setLayoutParams(layoutParams);
                actionView.setAccessibilityDelegate(this.f2424Bx);
                addView(actionView);
            }
            i = 0;
            while (i < min) {
                ((LinearLayout.LayoutParams) getChildAt(i).getLayoutParams()).setMarginEnd(i < min + -1 ? this.f2422Bv : 0);
                i++;
            }
        }
        for (i = 0; i < getChildCount(); i++) {
            ActionView actionView2 = (ActionView) getChildAt(i);
            actionView2.reset();
            if (min > i) {
                actionView2.setVisibility(0);
                C0828a c0828a = (C0828a) arrayList.get(i);
                ShortcutInfo shortcutInfo = c0828a.f1200AL;
                shortcutInfo.contentDescription = getContext().getString(2131820726, new Object[]{c0828a.contentDescription, c0828a.f1198AJ});
                actionView2.applyFromShortcutInfo(shortcutInfo, false);
                actionView2.mo6394a(c0828a, i);
                if (TextUtils.isEmpty(actionView2.getText())) {
                    StringBuilder stringBuilder = new StringBuilder("Empty ActionView text: action=");
                    stringBuilder.append(c0828a);
                    Log.e("ActionsRowView", stringBuilder.toString());
                }
            } else {
                actionView2.setVisibility(4);
                actionView2.mo6394a(null, -1);
            }
        }
        mo5366dd();
        this.f2418Br.mo5385dk();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f2420Bt) {
            int width = (getWidth() / 2) - (this.f2421Bu.getWidth() / 2);
            int height = (getHeight() - getResources().getDimensionPixelSize(2131165199)) - this.f2421Bu.getHeight();
            canvas.translate((float) width, (float) height);
            this.f2421Bu.draw(canvas);
            canvas.translate((float) (-width), (float) (-height));
        }
    }

    public final void setHidden(boolean z) {
        this.f2425ds = z;
        mo5366dd();
    }

    /* renamed from: q */
    public final void mo5371q(boolean z) {
        this.f2419Bs = z;
        mo5366dd();
    }

    /* renamed from: dc */
    public final boolean mo5365dc() {
        return (this.f2419Bs || getChildCount() <= 0 || this.f2423Bw) ? false : true;
    }

    /* renamed from: dd */
    public final void mo5366dd() {
        int i = !mo5365dc() ? 8 : this.f2425ds ? 4 : 0;
        setVisibility(i);
    }

    /* renamed from: a */
    public final C0828a mo5364a(ItemInfo itemInfo) {
        for (int i = 0; i < getChildCount(); i++) {
            if (itemInfo == ((ItemInfo) getChildAt(i).getTag())) {
                return ((ActionView) getChildAt(i)).f3282AP;
            }
        }
        return null;
    }
}
