/*
 * Copyright (C) 2018 CypherOS
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

package co.aoscp.lovegood.allapps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DropTarget.DragObject;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragController.DragListener;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.DragView;
import com.android.launcher3.graphics.BitmapRenderer;
import com.android.launcher3.graphics.DragPreviewProvider;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;

public class ActionView extends BubbleTextView implements OnLongClickListener {

    public Action mAction;
    public final boolean mIsRTL = Utilities.isRtl(getResources());
    public final Point mLastTouchPos = new Point();
    public int mMeasuredUnspecifiedWidth;
    public int mPos;

    public class MyDragPreviewProvider extends DragPreviewProvider {
        public final Point mPositionShift = new Point();

        public MyDragPreviewProvider(View view, Point point) {
            super(view, view.getContext());
            mPositionShift.set(point.x, point.y);
        }

        public float getScaleAndPosition(Bitmap bitmap, int[] iArr) {
            Launcher launcher = Launcher.getLauncher(mView.getContext());
            launcher.getDragLayer().getLocationInDragLayer(mView, iArr);
            float iconSize = ((float) ActionView.getIconSize()) / ((float) launcher.getDeviceProfile().iconSizePx);
            iArr[0] = (iArr[0] + mPositionShift.x) - (bitmap.getWidth() / 2);
            iArr[1] = (iArr[1] + mPositionShift.y) - bitmap.getHeight();
            return iconSize;
        }

        public Bitmap createDragBitmap() {
            Rect drawableBounds = DragPreviewProvider.getDrawableBounds(ActionView.getIcon());
            float width = drawableBounds.width() > 0 ? ((float) Launcher.getLauncher(mView.getContext()).getDeviceProfile().iconSizePx) / ((float) drawableBounds.width()) : 1.0f;
            return BitmapRenderer.createHardwareBitmap(((int) (((float) drawableBounds.width()) * width)) + blurSizeOutline, ((int) (((float) drawableBounds.height()) * width)) + blurSizeOutline, new ActionDragView(this, width));
        }
    }

    public ActionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(ItemClickHandler.INSTANCE);
        setOnLongClickListener(this);
        setLongPressTimeout(ViewConfiguration.getLongPressTimeout());
        setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.action_view_compound_drawable_padding));
        setTextSize(0, Launcher.getLauncher(mView.getContext()).getDeviceProfile().allAppsIconTextSizePx);
    }

    public void setAction(Action action, int position) {
        mAction = action;
        mPos = position;
    }

    public Action getAction() {
        return mAction;
    }

    public boolean performClick() {
        logClickEvent();
        return super.performClick();
    }

    public final void logClickEvent() {
        ActionsController.get(getContext()).getLogger().logClick(mAction.f42id, mPos);
    }

    public void onMeasure(int width, int height) {
        super.onMeasure(0, height);
        mMeasuredUnspecifiedWidth = getMeasuredWidth();
        super.onMeasure(width, height);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        requestLayout();
    }

    public void onDraw(Canvas canvas) {
        float measuredWidth = ((float) (getMeasuredWidth() - mMeasuredUnspecifiedWidth)) / 2.0f;
        if (measuredWidth > 0.0f) {
            if (mIsRTL) {
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
            mLastTouchPos.set((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    public void getIconBounds(Rect rect) {
        int measuredWidth = (getMeasuredWidth() - mMeasuredUnspecifiedWidth) / 2;
        int paddingLeft = getPaddingLeft() + measuredWidth;
        int iconSize = getIconSize() + paddingLeft;
        if (mIsRTL) {
            iconSize = (getMeasuredWidth() - getPaddingRight()) - measuredWidth;
            paddingLeft = iconSize - getIconSize();
        }
        measuredWidth = getPaddingTop();
        rect.set(paddingLeft, measuredWidth, iconSize, getIconSize() + measuredWidth);
    }

    public boolean onLongClick(final View view) {
        Launcher launcher = Launcher.getLauncher(view.getContext());
        if (!ItemLongClickListener.canStartDrag(launcher)) {
            return false;
        }
        if ((!launcher.isInState(LauncherState.ALL_APPS) && !launcher.isInState(LauncherState.OVERVIEW)) || launcher.getWorkspace().isSwitchingState()) {
            return false;
        }
        final DragController dragController = launcher.getDragController();
        dragController.addDragListener(new DragListener(this) {
            public void onDragStart(DragObject dragObject, DragOptions dragOptions) {
                view.setVisibility(4);
            }

            public void onDragEnd() {
                view.setVisibility(0);
                dragController.removeDragListener(this);
            }
        });
        DeviceProfile deviceProfile = launcher.getDeviceProfile();
        DragOptions dragOptions = new DragOptions();
        dragOptions.intrinsicIconScaleFactor = ((float) deviceProfile.allAppsIconSizePx) / ((float) deviceProfile.iconSizePx);
        ItemInfo itemInfo = (ItemInfo) view.getTag();
        Point point = new Point();
        Point point2 = mLastTouchPos;
        point.x = point2.x;
        point.y = point2.y;
        DragView dragView = launcher.getWorkspace().beginDragShared(view, launcher.getAppsView(), itemInfo, new MyDragPreviewProvider(view, point), dragOptions);
        Rect rect = new Rect();
        getIconBounds(rect);
        dragView.animateShift(((-point.x) + rect.left) + (rect.width() / 2), ((-point.y) + rect.top) + rect.height());
        return false;
    }
}
