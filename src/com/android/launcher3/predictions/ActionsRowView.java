package com.android.launcher3.predictions;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Launcher;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import java.util.ArrayList;

public class ActionsRowView extends LinearLayout implements C0766j {
    /* renamed from: dx */
    private boolean f1913dx;
    private boolean mIsDarkTheme;
    private final Launcher mLauncher;
    /* renamed from: xW */
    private C0760d f1914xW;
    /* renamed from: xX */
    PredictionsFloatingHeader f1915xX;
    /* renamed from: xY */
    private boolean f1916xY;
    /* renamed from: xZ */
    private int f1917xZ;

    public ActionsRowView(Context context) {
        this(context, null);
    }

    public ActionsRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        this.mLauncher = Launcher.getLauncher(getContext());
        this.mIsDarkTheme = WallpaperColorInfo.getInstance(context).mIsDark;
        this.f1917xZ = context.getResources().getDimensionPixelSize(2131427524);
    }

    protected void onMeasure(int i, int i2) {
        int paddingRight;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        i = MeasureSpec.getSize(i);
        if (this.mLauncher.getDeviceProfile().isVerticalBarLayout()) {
            i -= this.mLauncher.mAppsView.getActiveRecyclerView().getPaddingLeft();
            paddingRight = this.mLauncher.mAppsView.getActiveRecyclerView().getPaddingRight();
        } else {
            CellLayout cellLayout = this.mLauncher.mHotseat.mContent;
            i -= cellLayout.getPaddingLeft();
            paddingRight = cellLayout.getPaddingRight();
        }
        i -= paddingRight;
        super.onMeasure(MeasureSpec.makeMeasureSpec(((i - ((i / deviceProfile.inv.numHotseatIcons) - Math.round(0.92f * ((float) deviceProfile.iconSizePx)))) + getPaddingLeft()) + getPaddingRight(), 1073741824), i2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1914xW = C0760d.m2434b(getContext());
        this.f1914xW.f994xM = this;
        mo3114d(this.f1914xW.f988gL);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1914xW.f994xM = null;
    }

    /* renamed from: d */
    public final void mo3114d(ArrayList arrayList) {
        int i;
        int min = Math.min(2, arrayList.size());
        if (getChildCount() != min) {
            while (getChildCount() > min) {
                removeViewAt(0);
            }
            while (getChildCount() < min) {
                ActionView actionView = (ActionView) LayoutInflater.from(getContext()).inflate(2130968578, this, false);
                if (this.mIsDarkTheme) {
                    GradientDrawable gradientDrawable = (GradientDrawable) actionView.getBackground();
                    gradientDrawable.mutate();
                    gradientDrawable.setColor(872415231);
                }
                LayoutParams layoutParams = new LayoutParams(0, -1);
                layoutParams.weight = 1.0f;
                actionView.setLayoutParams(layoutParams);
                addView(actionView);
            }
            i = 0;
            while (i < min) {
                ((LayoutParams) getChildAt(i).getLayoutParams()).setMarginEnd(i < min + -1 ? this.f1917xZ : 0);
                i++;
            }
        }
        for (i = 0; i < getChildCount(); i++) {
            ActionView actionView2 = (ActionView) getChildAt(i);
            actionView2.reset();
            if (min > i) {
                actionView2.setVisibility(0);
                C0759a c0759a = (C0759a) arrayList.get(i);
                actionView2.applyFromShortcutInfo(c0759a.f982xw, false);
                actionView2.mo5695a(c0759a, i);
            } else {
                actionView2.setVisibility(4);
                actionView2.mo5695a(null, -1);
            }
        }
        mo4894cP();
        this.f1915xX.mo4909cT();
    }

    public final void setHidden(boolean z) {
        this.f1913dx = z;
        mo4894cP();
    }

    /* renamed from: q */
    public final void mo4898q(boolean z) {
        this.f1916xY = z;
        mo4894cP();
    }

    /* renamed from: cO */
    public final boolean mo4893cO() {
        return !this.f1916xY && getChildCount() > 0;
    }

    /* renamed from: cP */
    final void mo4894cP() {
        int i = !mo4893cO() ? 8 : this.f1913dx ? 4 : 0;
        setVisibility(i);
    }
}
