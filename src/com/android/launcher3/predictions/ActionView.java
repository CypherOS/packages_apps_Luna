package com.android.launcher3.predictions;

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
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;

public class ActionView extends BubbleTextView implements OnLongClickListener {
    private int mPos;
    /* renamed from: xA */
    private int f2660xA;
    /* renamed from: xx */
    private final Point f2661xx;
    /* renamed from: xy */
    private final boolean f2662xy;
    /* renamed from: xz */
    private C0759a f2663xz;

    public ActionView(Context context) {
        this(context, null);
    }

    public ActionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2661xx = new Point();
        this.f2662xy = Utilities.isRtl(getResources());
        setOnClickListener(ItemClickHandler.INSTANCE);
        setOnLongClickListener(this);
        setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
    }

    /* renamed from: a */
    public final void mo5695a(C0759a c0759a, int i) {
        this.f2663xz = c0759a;
        this.mPos = i;
    }

    public boolean performClick() {
        C0764h c0764h = C0760d.m2434b(getContext()).f993xL;
        String str = this.f2663xz.f977sc;
        int i = this.mPos;
        C0765i c0765i = new C0765i(0);
        c0765i.f1002ts = System.currentTimeMillis();
        c0765i.f1003xS = true;
        if (str == null) {
            str = "";
        }
        c0765i.f1004xT = str;
        c0765i.f1005xU = i + 1;
        Message.obtain(c0764h.f1001xR.mWorker, 0, 0, 0, c0765i).sendToTarget();
        return super.performClick();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(0, i2);
        this.f2660xA = getMeasuredWidth();
        super.onMeasure(i, i2);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        requestLayout();
    }

    public void onDraw(Canvas canvas) {
        float measuredWidth = ((float) (getMeasuredWidth() - this.f2660xA)) / 2.0f;
        if (measuredWidth > 0.0f) {
            if (this.f2662xy) {
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
            this.f2661xx.set((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void getIconBounds(Rect rect) {
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        rect.set(paddingLeft, paddingTop, this.mIconSize + paddingLeft, this.mIconSize + paddingTop);
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
        dragController.addDragListener(new C1449b(this, view, dragController));
        DeviceProfile deviceProfile = launcher.getDeviceProfile();
        DragOptions dragOptions = new DragOptions();
        dragOptions.intrinsicIconScaleFactor = ((float) deviceProfile.allAppsIconSizePx) / ((float) deviceProfile.iconSizePx);
        ItemInfo itemInfo = (ItemInfo) view.getTag();
        Point point = new Point();
        point.x = this.f2661xx.x;
        point.y = this.f2661xx.y;
        launcher.mWorkspace.beginDragShared(view, launcher.mAppsView, itemInfo, new C1450c(this, view, point), dragOptions).animateShift(-point.x, -point.y);
        return false;
    }
}
