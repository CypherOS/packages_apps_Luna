package com.android.launcher3.backports.allapps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.DragView;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;

public class ActionView extends BubbleTextView implements OnLongClickListener {
    /* renamed from: AN */
    private final Point f3280AN;
    /* renamed from: AO */
    private final boolean f3281AO;
    /* renamed from: AP */
    C0828a f3282AP;
    /* renamed from: AQ */
    private int f3283AQ;
    private int mPos;

    public ActionView(Context context) {
        this(context, null);
    }

    public ActionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3280AN = new Point();
        this.f3281AO = Utilities.isRtl(getResources());
        setOnClickListener(ItemClickHandler.INSTANCE);
        setOnLongClickListener(this);
        setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
        setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.action_view_compound_drawable_padding));
    }

    /* renamed from: a */
    public final void mo6394a(C0828a c0828a, int i) {
        this.f3282AP = c0828a;
        this.mPos = i;
    }

    public boolean performClick() {
        C0833h c0833h = C0829d.m656c(getContext()).f1214Bc;
        String str = this.f3282AP.f1203id;
        int i = this.mPos;
        C0834i c0834i = new C0834i();
        c0834i.f1230ts = System.currentTimeMillis();
        c0834i.f1226Bl = 1;
        if (str == null) {
            str = "";
        }
        c0834i.f1227Bm = str;
        c0834i.f1228Bn = i + 1;
        Message.obtain(c0833h.f1225Bk.mWorker, 0, 0, 0, c0834i).sendToTarget();
        return super.performClick();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(0, i2);
        this.f3283AQ = getMeasuredWidth();
        super.onMeasure(i, i2);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        requestLayout();
    }

    public void onDraw(Canvas canvas) {
        float measuredWidth = ((float) (getMeasuredWidth() - this.f3283AQ)) / 2.0f;
        if (measuredWidth > 0.0f) {
            if (this.f3281AO) {
                measuredWidth *= -1.0f;
            }
            canvas.translate(measuredWidth, 0.0f);
            super.onDraw(canvas);
            canvas.translate(-measuredWidth, 0.0f);
            return;
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || action == 2) {
            this.f3280AN.set((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void getIconBounds(Rect rect) {
        int measuredWidth = (getMeasuredWidth() - this.f3283AQ) / 2;
        int paddingLeft = getPaddingLeft() + measuredWidth;
        int i = this.mIconSize + paddingLeft;
        if (this.f3281AO) {
            i = (getMeasuredWidth() - getPaddingRight()) - measuredWidth;
            paddingLeft = i - this.mIconSize;
        }
        measuredWidth = getPaddingTop();
        rect.set(paddingLeft, measuredWidth, i, this.mIconSize + measuredWidth);
    }

    public boolean onLongClick(View view) {
        Launcher launcher = Launcher.getLauncher(view.getContext());
        if (!ItemLongClickListener.canStartDrag(launcher)) {
            return false;
        }
        if ((!launcher.isInState(LauncherState.ALL_APPS) && !launcher.isInState(LauncherState.OVERVIEW)) || launcher.mWorkspace.mIsSwitchingState) {
            return false;
        }
        DragController dragController = launcher.mDragController;
        dragController.addDragListener(new C1536b(this, view, dragController));
        DeviceProfile deviceProfile = launcher.getDeviceProfile();
        DragOptions dragOptions = new DragOptions();
        dragOptions.intrinsicIconScaleFactor = ((float) deviceProfile.allAppsIconSizePx) / ((float) deviceProfile.iconSizePx);
        ItemInfo itemInfo = (ItemInfo) view.getTag();
        Point point = new Point();
        point.x = this.f3280AN.x;
        point.y = this.f3280AN.y;
        DragView beginDragShared = launcher.mWorkspace.beginDragShared(view, launcher.mAppsView, itemInfo, new C1537c(this, view, point), dragOptions);
        Rect rect = new Rect();
        getIconBounds(rect);
        beginDragShared.animateShift(((-point.x) + rect.left) + (rect.width() / 2), ((-point.y) + rect.top) + rect.height());
        return false;
    }
}
