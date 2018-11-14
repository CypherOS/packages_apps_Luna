package com.android.launcher3.backports.allapps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.IntProperty;
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
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.allapps.AllAppsStore.OnUpdateListener;
import com.android.launcher3.backports.p024e.C0849a;
import com.android.launcher3.keyboard.FocusIndicatorHelper;
import com.android.launcher3.keyboard.FocusIndicatorHelper.SimpleFocusIndicatorHelper;
import com.android.launcher3.logging.UserEventDispatcher.LogContainerProvider;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.userevent.nano.LauncherLogProto.Target;
import com.android.launcher3.util.Themes;

import com.android.quickstep.AnimatedFloat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PredictionRowView extends LinearLayout implements OnDeviceProfileChangeListener, OnUpdateListener, LogContainerProvider {
    /* renamed from: BA */
    static final IntProperty f2426BA = new C0838p("textAlpha");
    /* renamed from: BB */
    private static final Interpolator f2427BB = PredictionRowViewInterpolator.INSTANCE;
    /* renamed from: BC */
    private final int f2428BC;
    /* renamed from: BD */
    final List f2429BD;
    /* renamed from: BE */
    public final ArrayList f2430BE;
    /* renamed from: BF */
    private final FocusIndicatorHelper f2431BF;
    /* renamed from: BG */
    private final int f2432BG;
    /* renamed from: BH */
    final int f2433BH;
    /* renamed from: BI */
    int f2434BI;
    /* renamed from: BJ */
    final TextPaint f2435BJ;
    /* renamed from: BK */
    private final int f2436BK;
    /* renamed from: BL */
    private final int f2437BL;
    /* renamed from: BM */
    private int f2438BM;
    /* renamed from: BN */
    private final int f2439BN;
    /* renamed from: BO */
    DividerType f2440BO;
    /* renamed from: BP */
    private boolean f2441BP;
    /* renamed from: BQ */
    float f2442BQ;
    /* renamed from: BR */
    final AnimatedFloat f2443BR;
    /* renamed from: BS */
    final AnimatedFloat f2444BS;
    /* renamed from: BT */
    private View f2445BT;
    /* renamed from: BU */
    private boolean f2446BU;
    /* renamed from: Br */
    PredictionsFloatingHeader f2447Br;
    /* renamed from: Bu */
    Layout f2448Bu;
    /* renamed from: Bw */
    public boolean f2449Bw;
    private final Launcher mLauncher;
    private final Paint mPaint;

    public enum DividerType {
        NONE,
        LINE,
        ALL_APPS_LABEL
    }

    static /* synthetic */ float lambda$static$0(float f) {
        return f < 0.8f ? 0.0f : (f - 0.8f) / 0.2f;
    }

    public PredictionRowView(Context context) {
        this(context, null);
    }

    public PredictionRowView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
        int i;
        this.f2429BD = new ArrayList();
        this.f2430BE = new ArrayList();
        this.f2435BJ = new TextPaint();
        this.f2442BQ = 0.0f;
        this.f2443BR = new AnimatedFloat(new AnimatedFloatListener(this));
        this.f2444BS = new AnimatedFloat(new AnimatedFloatListener(this));
        this.f2449Bw = false;
        this.f2446BU = false;
        setOrientation(0);
        setWillNotDraw(false);
        boolean attrBoolean = Themes.getAttrBoolean(context, 2130968625);
        this.mPaint = new Paint();
        Paint paint = this.mPaint;
        if (attrBoolean) {
            i = 2131099653;
        } else {
            i = 2131099652;
        }
        paint.setColor(ContextCompat.getColor(context, i));
        this.mPaint.setStrokeWidth((float) getResources().getDimensionPixelSize(2131165189));
        this.f2439BN = this.mPaint.getColor();
        this.f2431BF = new SimpleFocusIndicatorHelper(this);
        this.f2428BC = LauncherAppState.getInstance(context).mInvariantDeviceProfile.numColumns;
        this.mLauncher = Launcher.getLauncher(context);
        this.mLauncher.addOnDeviceProfileChangeListener(this);
        this.f2432BG = Themes.getAttrColor(context, 16842808);
        this.f2433BH = Color.alpha(this.f2432BG);
        this.f2434BI = this.f2433BH;
        this.f2435BJ.setColor(ContextCompat.getColor(context, attrBoolean ? 2131099651 : 2131099650));
        this.f2436BK = this.f2435BJ.getColor();
        this.f2437BL = Color.alpha(this.f2436BK);
        this.f2438BM = this.f2437BL;
        mo5374dd();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m2256dg().addUpdateListener(this);
        m2256dg().registerIconContainer(this);
    }

    /* renamed from: dg */
    private AllAppsStore m2256dg() {
        return this.mLauncher.mAppsView.mAllAppsStore;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m2256dg().removeUpdateListener(this);
        m2256dg().unregisterIconContainer(this);
    }

    /* renamed from: r */
    final void mo5383r(boolean z) {
        if (z != this.f2446BU) {
            this.f2446BU = z;
            mo5374dd();
        }
    }

    /* renamed from: dd */
    public final void mo5374dd() {
        int i = (!this.f2446BU || this.f2449Bw) ? 8 : 0;
        setVisibility(i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getExpectedHeight(), 1073741824));
    }

    protected void dispatchDraw(Canvas canvas) {
        this.f2431BF.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public final int getExpectedHeight() {
        if (getVisibility() == 8) {
            return 0;
        }
        return (Launcher.getLauncher(getContext()).getDeviceProfile().allAppsCellHeightPx + getPaddingTop()) + getPaddingBottom();
    }

    public void onDeviceProfileChanged(DeviceProfile deviceProfile) {
        removeAllViews();
        m2257dh();
    }

    public void onAppsUpdated() {
        Collection emptyList;
        this.f2430BE.clear();
        ArrayList arrayList = this.f2430BE;
        List<C0849a> list = this.f2429BD;
        if (m2256dg().mComponentToAppMap.values().isEmpty()) {
            emptyList = Collections.emptyList();
        } else {
            List arrayList2 = new ArrayList();
            for (C0849a a : list) {
                ItemInfoWithIcon a2 = a.mo3457a(m2256dg());
                if (a2 != null) {
                    arrayList2.add(a2);
                }
                if (arrayList2.size() == this.f2428BC) {
                    break;
                }
            }
            emptyList = arrayList2;
        }
        arrayList.addAll(emptyList);
        m2257dh();
    }

    /* renamed from: dh */
    private void m2257dh() {
        if (this.f2445BT != null) {
            removeView(this.f2445BT);
        }
        if (getChildCount() != this.f2428BC) {
            while (getChildCount() > this.f2428BC) {
                removeViewAt(0);
            }
            while (getChildCount() < this.f2428BC) {
                BubbleTextView bubbleTextView = (BubbleTextView) this.mLauncher.getLayoutInflater().inflate(2131558409, this, false);
                bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
                bubbleTextView.setOnLongClickListener(ItemLongClickListener.INSTANCE_ALL_APPS);
                bubbleTextView.setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
                bubbleTextView.setOnFocusChangeListener(this.f2431BF);
                LayoutParams layoutParams = (LayoutParams) bubbleTextView.getLayoutParams();
                layoutParams.height = this.mLauncher.getDeviceProfile().allAppsCellHeightPx;
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                addView(bubbleTextView);
            }
        }
        int size = this.f2430BE.size();
        int f = ColorUtils.setAlphaComponent(this.f2432BG, this.f2434BI);
        for (int i = 0; i < getChildCount(); i++) {
            BubbleTextView bubbleTextView2 = (BubbleTextView) getChildAt(i);
            bubbleTextView2.reset();
            if (size > i) {
                bubbleTextView2.setVisibility(0);
                if (this.f2430BE.get(i) instanceof AppInfo) {
                    bubbleTextView2.applyFromApplicationInfo((AppInfo) this.f2430BE.get(i));
                } else if (this.f2430BE.get(i) instanceof ShortcutInfo) {
                    bubbleTextView2.applyFromShortcutInfo((ShortcutInfo) this.f2430BE.get(i), false);
                }
                bubbleTextView2.setTextColor(f);
            } else {
                bubbleTextView2.setVisibility(size == 0 ? 8 : 4);
            }
        }
        if (size == 0) {
            if (this.f2445BT == null) {
                this.f2445BT = LayoutInflater.from(getContext()).inflate(2131558462, this, false);
            }
            addView(this.f2445BT);
        } else {
            this.f2445BT = null;
        }
        this.f2447Br.mo5385dk();
    }

    protected void onDraw(Canvas canvas) {
        int dimensionPixelSize;
        if (this.f2440BO == DividerType.LINE) {
            dimensionPixelSize = getResources().getDimensionPixelSize(2131165251);
            float height = (float) (getHeight() - (getPaddingBottom() / 2));
            Canvas canvas2 = canvas;
            float f = height;
            canvas2.drawLine((float) (getPaddingLeft() + dimensionPixelSize), f, (float) ((getWidth() - getPaddingRight()) - dimensionPixelSize), height, this.mPaint);
            return;
        }
        if (this.f2440BO == DividerType.ALL_APPS_LABEL) {
            dimensionPixelSize = (getWidth() / 2) - (this.f2448Bu.getWidth() / 2);
            int height2 = (getHeight() - getResources().getDimensionPixelSize(2131165199)) - this.f2448Bu.getHeight();
            canvas.translate((float) dimensionPixelSize, (float) height2);
            this.f2448Bu.draw(canvas);
            canvas.translate((float) (-dimensionPixelSize), (float) (-height2));
        }
    }

    public final void fillInLogContainerData(View view, ItemInfo itemInfo, Target target, Target target2) {
        for (int i = 0; i < this.f2430BE.size(); i++) {
            if (((ItemInfoWithIcon) this.f2430BE.get(i)) == itemInfo) {
                target2.containerType = 7;
                target.predictedRank = i;
                return;
            }
        }
    }

    /* renamed from: s */
    public final void mo5384s(boolean z) {
        this.f2441BP = z;
        mo5375di();
    }

    /* renamed from: ax */
    public final void setValue(int i) {
        this.f2434BI = i;
        int f = ColorUtils.setAlphaComponent(this.f2432BG, this.f2434BI);
        if (this.f2445BT == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                ((BubbleTextView) getChildAt(i2)).setTextColor(f);
            }
        }
        i = ColorUtils.setAlphaComponent(this.f2439BN, Math.round(((float) (Color.alpha(this.f2439BN) * i)) / 255.0f));
        if (i != this.mPaint.getColor()) {
            this.mPaint.setColor(i);
            this.f2438BM = Math.round((float) ((this.f2437BL * this.f2434BI) / this.f2433BH));
            this.f2435BJ.setColor(ColorUtils.setAlphaComponent(this.f2436BK, this.f2438BM));
            if (this.f2440BO != DividerType.NONE) {
                invalidate();
            }
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    /* renamed from: di */
    void mo5375di() {
        setTranslationY((1.0f - this.f2444BS.value) * this.f2442BQ);
        float interpolation = f2427BB.getInterpolation(this.f2444BS.value);
        setAlpha(this.f2443BR.value * (interpolation + ((1.0f - interpolation) * ((float) (this.f2441BP)))));
    }
}
