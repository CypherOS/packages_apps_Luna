package com.android.launcher3.predictions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.p010v4.p012b.C0149a;
import android.util.AttributeSet;
import android.util.IntProperty;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.allapps.AllAppsStore.OnUpdateListener;
import com.android.launcher3.keyboard.FocusIndicatorHelper;
import com.android.launcher3.keyboard.FocusIndicatorHelper.SimpleFocusIndicatorHelper;
import com.android.launcher3.logging.UserEventDispatcher.LogContainerProvider;
import com.android.launcher3.predictions.backport.C0783a;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.userevent.nano.LauncherLogProto.Target;
import com.android.launcher3.util.Themes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PredictionRowView extends LinearLayout implements OnDeviceProfileChangeListener, OnUpdateListener, LogContainerProvider {
    /* renamed from: ya */
    public static final IntProperty f1918ya = new C0767k("textAlpha");
    /* renamed from: dx */
    private boolean f1919dx;
    private final Launcher mLauncher;
    private final Paint mPaint;
    /* renamed from: xX */
    PredictionsFloatingHeader f1920xX;
    /* renamed from: yb */
    private final int f1921yb;
    /* renamed from: yc */
    public final List f1922yc;
    /* renamed from: yd */
    public final ArrayList f1923yd;
    /* renamed from: ye */
    private final FocusIndicatorHelper f1924ye;
    /* renamed from: yf */
    private final int f1925yf;
    /* renamed from: yg */
    boolean f1926yg;
    /* renamed from: yh */
    int f1927yh;

    public PredictionRowView(Context context) {
        this(context, null);
    }

    public PredictionRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1922yc = new ArrayList();
        this.f1923yd = new ArrayList();
        this.f1927yh = 255;
        setOrientation(0);
        setWillNotDraw(false);
        this.mPaint = new Paint();
        this.mPaint.setColor(Themes.getAttrColor(context, 16843820));
        this.mPaint.setStrokeWidth((float) getResources().getDimensionPixelSize(2131427396));
        this.f1925yf = this.mPaint.getColor();
        this.f1924ye = new SimpleFocusIndicatorHelper(this);
        this.f1921yb = LauncherAppState.getInstance(context).mInvariantDeviceProfile.numColumns;
        this.mLauncher = Launcher.getLauncher(context);
        this.mLauncher.addOnDeviceProfileChangeListener(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m4664cQ().addUpdateListener(this);
        m4664cQ().registerIconContainer(this);
    }

    /* renamed from: cQ */
    private AllAppsStore m4664cQ() {
        return this.mLauncher.mAppsView.mAllAppsStore;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m4664cQ().removeUpdateListener(this);
        m4664cQ().unregisterIconContainer(this);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getExpectedHeight(), 1073741824));
    }

    protected void dispatchDraw(Canvas canvas) {
        this.f1924ye.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public final int getExpectedHeight() {
        if (this.f1923yd.isEmpty()) {
            return 0;
        }
        return (0 + Launcher.getLauncher(getContext()).getDeviceProfile().allAppsCellHeightPx) + (getPaddingTop() + getPaddingBottom());
    }

    public void onDeviceProfileChanged(DeviceProfile deviceProfile) {
        removeAllViews();
        m4665cR();
    }

    public void onAppsUpdated() {
        Collection emptyList;
        this.f1923yd.clear();
        ArrayList arrayList = this.f1923yd;
        List<C0783a> list = this.f1922yc;
        if (m4664cQ().mComponentToAppMap.values().isEmpty()) {
            emptyList = Collections.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (C0783a a : list) {
                ItemInfoWithIcon a2 = a.mo3137a(m4664cQ());
                if (a2 != null) {
                    arrayList2.add(a2);
                }
                if (arrayList2.size() == this.f1921yb) {
                    break;
                }
            }
            emptyList = arrayList2;
        }
        arrayList.addAll(emptyList);
        m4665cR();
    }

    /* renamed from: cR */
    private void m4665cR() {
        if (getChildCount() != this.f1921yb) {
            while (getChildCount() > this.f1921yb) {
                removeViewAt(0);
            }
            while (getChildCount() < this.f1921yb) {
                BubbleTextView bubbleTextView = (BubbleTextView) this.mLauncher.getLayoutInflater().inflate(2130968585, this, false);
                bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
                bubbleTextView.setOnLongClickListener(ItemLongClickListener.INSTANCE_ALL_APPS);
                bubbleTextView.setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
                bubbleTextView.setOnFocusChangeListener(this.f1924ye);
                LayoutParams layoutParams = (LayoutParams) bubbleTextView.getLayoutParams();
                layoutParams.height = this.mLauncher.getDeviceProfile().allAppsCellHeightPx;
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                bubbleTextView.setTextAlpha(this.f1927yh);
                addView(bubbleTextView);
            }
        }
        for (int i = 0; i < getChildCount(); i++) {
            BubbleTextView bubbleTextView2 = (BubbleTextView) getChildAt(i);
            bubbleTextView2.reset();
            if (this.f1923yd.size() > i) {
                bubbleTextView2.setVisibility(0);
                if (this.f1923yd.get(i) instanceof AppInfo) {
                    bubbleTextView2.applyFromApplicationInfo((AppInfo) this.f1923yd.get(i));
                } else if (this.f1923yd.get(i) instanceof ShortcutInfo) {
                    bubbleTextView2.applyFromShortcutInfo((ShortcutInfo) this.f1923yd.get(i), false);
                }
            } else {
                bubbleTextView2.setVisibility(4);
            }
        }
        mo4900cP();
        this.f1920xX.mo4909cT();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f1926yg) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131427350);
            float height = (float) (getHeight() - (getPaddingBottom() / 2));
            Canvas canvas2 = canvas;
            float f = height;
            canvas2.drawLine((float) (getPaddingLeft() + dimensionPixelSize), f, (float) ((getWidth() - getPaddingRight()) - dimensionPixelSize), height, this.mPaint);
        }
    }

    public final void fillInLogContainerData(View view, ItemInfo itemInfo, Target target, Target target2) {
        for (int i = 0; i < this.f1923yd.size(); i++) {
            if (((ItemInfoWithIcon) this.f1923yd.get(i)) == itemInfo) {
                target2.containerType = 7;
                target.predictedRank = i;
                return;
            }
        }
    }

    public final void setHidden(boolean z) {
        this.f1919dx = z;
        mo4900cP();
    }

    /* renamed from: cP */
    final void mo4900cP() {
        int i = this.f1923yd.isEmpty() ? 8 : this.f1919dx ? 4 : 0;
        setVisibility(i);
    }

    public final void setTextAlpha(int i) {
        this.f1927yh = i;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((BubbleTextView) getChildAt(i2)).setTextAlpha(i);
        }
        i = C0149a.m707f(this.f1925yf, Math.round(((float) (Color.alpha(this.f1925yf) * i)) / 255.0f));
        if (i != this.mPaint.getColor()) {
            this.mPaint.setColor(i);
            if (this.f1926yg) {
                invalidate();
            }
        }
    }
}
