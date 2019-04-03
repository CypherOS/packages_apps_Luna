/*
 * Copyright (C) 2018-2019 CypherOS
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
package co.aoscp.lovegood.quickspace;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.graphics.drawable.VectorDrawable;
import android.provider.Settings;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.util.Themes;

import co.aoscp.lovegood.Bits;
import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.quickspace.quickevents.IEventInfo;
import co.aoscp.lovegood.quickspace.quickevents.QuickspaceEventController;
import co.aoscp.lovegood.quickspace.receivers.QuickSpaceActionReceiver;
import co.aoscp.lovegood.views.DateTextView;

import java.util.ArrayList;
import java.util.Locale;

public class QuickSpaceView extends FrameLayout implements AnimatorUpdateListener, IQuickspace, IEventInfo {

    private static final String TAG = "QuickSpaceView";
    private static final String SETTING_WEATHER_LOCKSCREEN_UNIT = "weather_lockscreen_unit";

    private final ColorStateList mColorStateList;
    private BubbleTextView mBubbleTextView;
    private final int mQuickspaceBackgroundRes;

    private DateTextView mClockView;
    private ViewGroup mQuickspaceContent;
    private ViewGroup mWeatherContentSub;
    private ImageView mWeatherIconSub;
    private View mTitleSeparator;
    private ViewGroup mWeatherContent;
    private ImageView mWeatherIcon;
    private TextView mWeatherTemp;

	private View mQuickEventContent;
	private TextView mEventInfo;
	private int mEventIconSize;
	private String mInfo;
	private int mEventType = QuickspaceEventController.EVENT_NONE;

    private boolean mIsQuickEvent;
    private boolean mFinishedInflate;
    private boolean mUseImperialUnit;
    private boolean mWeatherAvailable;

    private QuickSpaceActionReceiver mActionReceiver;
    private QuickspaceController mController;
	private QuickspaceEventController mEventController;
    private QuickspaceCard mCardInfo;
    private ArrayList<QuickspaceCard> mQuickspaceCard;
    private WeatherSettingsObserver mWeatherSettingsObserver;

    private final OnClickListener mEventAction = new OnClickListener() {
        @Override
        public void onClick(View view) {
            openTask(view);
        }
    };

    public QuickSpaceView(Context context, AttributeSet set) {
        super(context, set);
        mActionReceiver = new QuickSpaceActionReceiver(context);
        mController = QuickspaceController.get(context);
		mEventController = QuickspaceEventController.get(context);
        mColorStateList = ColorStateList.valueOf(Themes.getAttrColor(getContext(), R.attr.workspaceTextColor));
        mQuickspaceBackgroundRes = R.drawable.bg_quickspace;
        setClipChildren(false);
        mWeatherSettingsObserver = new WeatherSettingsObserver(context.getContentResolver());
        mWeatherSettingsObserver.register();
        mWeatherSettingsObserver.updateLockscreenUnit();
    }

    @Override
    public void onNewCard(ArrayList<QuickspaceCard> info) {
        mQuickspaceCard = info;
        if (info != null && info.size() >= 1) {
            mCardInfo = (QuickspaceCard) info.get(0);
            boolean isQuickEvent = mCardInfo != null && mCardInfo.getEventType() != 0;
            if (mIsQuickEvent != isQuickEvent) {
                mIsQuickEvent = isQuickEvent;
                prepareLayout();
            }
            mWeatherAvailable = mCardInfo != null && mCardInfo.getStatus() == 0;
            getQuickSpaceView();
            loadSingleLine();
        } else {
            Log.d(TAG, "No card info");
        }
    }

	@Override
    public void onNewEvent(String eventInfo, int eventType) {
		mInfo = eventInfo;
		mEventType = eventType;
		updateEventView();
	}

    private void loadSingleLine() {
        LayoutTransition transition = mQuickspaceContent.getLayoutTransition();
        mQuickspaceContent.setLayoutTransition(transition == null ? new LayoutTransition() : null);
        setBackgroundResource(0);
        bindWeather(mWeatherContent, mWeatherTemp, mWeatherIcon);
        bindClockAndSeparator(false);
    }

	private void updateEventView() {
		mQuickEventContent = findViewById(R.id.quickevent_content);
		mEventInfo = (TextView) findViewById(R.id.quickevent_info);
		mEventIconSize = getResources().getDimensionPixelSize(R.dimen.quickspace_event_icon_size);
		updateEventChip();
	}

	private void updateEventChip() {
		mEventInfo.setText(mIsQuickEvent ? mCardInfo.getEventTitle() : mInfo);
		mEventInfo.setContentDescription(mIsQuickEvent ? mCardInfo.getEventTitle() : mInfo);
		if (getEventIcon() != null) {
            getEventIcon().setBounds(0, 0, mEventIconSize, mEventIconSize);
        }
		boolean rtl = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
        mEventInfo.setCompoundDrawables(rtl ? null : getEventIcon(), null, rtl ? getEventIcon() : null, null);
		if (mInfo == null && !mIsQuickEvent) {
			Log.d(TAG, "No indication");
			mEventInfo.animate().cancel();
            mQuickEventContent.setVisibility(View.INVISIBLE);
		} else {
			mQuickEventContent.setVisibility(View.VISIBLE);
			mEventInfo.setTranslationY((float) (mEventInfo.getHeight() / 2));
            mEventInfo.setAlpha(0.0f);
            mEventInfo.animate()
			        .withLayer()
					.alpha(1.0f)
					.translationY(0.0f)
					.setStartDelay(150)
					.setDuration(100)
					.setListener(
                    .setInterpolator(Interpolators.DEACCEL_2_5)
					.start();
        }
    }

    private void bindClockAndSeparator(boolean forced) {
        boolean hasGoogleCalendar = Bits.hasPackageInstalled(Launcher.getLauncher(mContext), "com.google.android.calendar");
        mClockView.setVisibility(View.VISIBLE);
        mClockView.setOnClickListener(hasGoogleCalendar ? mActionReceiver.getCalendarAction() : null);
        if (forced) {
            mClockView.reloadDateFormat(true);
        }
        mTitleSeparator.setVisibility(mWeatherAvailable ? View.VISIBLE : View.GONE);
    }

    private void bindWeather(View container, TextView title, ImageView icon) {
        boolean hasGoogleApp = Bits.hasPackageInstalled(Launcher.getLauncher(mContext), LunaLauncherCallbacks.SEARCH_PACKAGE);
        if (mWeatherAvailable) {
            container.setVisibility(View.VISIBLE);
            container.setOnClickListener(hasGoogleApp ? mActionReceiver.getWeatherAction() : null);
            title.setText(getWeatherTemp());
            icon.setImageIcon(Icon.createWithResource(mContext, mCardInfo.getWeatherIcon()));
            return;
        }
        container.setVisibility(View.GONE);
    }

    private String getWeatherTemp() {
        int tempMetric = mCardInfo.getTemperature(true);
        int tempImperial = mCardInfo.getTemperature(false);
        String weatherTemp = mUseImperialUnit ?
                Integer.toString(tempImperial) + "°F" :
                Integer.toString(tempMetric) + "°C";
        return weatherTemp;
    }

    public void reloadConfiguration() {
        if (!mIsQuickEvent) {
            bindClockAndSeparator(true);
        }
    }

    private void loadViews() {
        mWeatherIcon = (ImageView) findViewById(R.id.weather_icon);
        mQuickspaceContent = (ViewGroup) findViewById(R.id.quickspace_content);
        mWeatherContent = (ViewGroup) findViewById(R.id.weather_content);
        mWeatherTemp = (TextView) findViewById(R.id.weather_temp);
        mClockView = (DateTextView) findViewById(R.id.clock_view);
        mTitleSeparator = findViewById(R.id.separator);
        setTypeface(mWeatherTemp, mClockView);
    }

    private void setTypeface(TextView... views) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/GoogleSans-Regular.ttf");
        for (TextView view : views) {
            if (view != null) {
                view.setTypeface(tf);
            }
        }
    }

    private void prepareLayout() {
        int indexOfChild = indexOfChild(mQuickspaceContent);
        removeView(mQuickspaceContent);
        addView(LayoutInflater.from(getContext()).inflate(
		        R.layout.quickspace_view, this, false), indexOfChild);
        loadViews();
    }

    private void getQuickSpaceView() {
        if (!(mQuickspaceContent.getVisibility() == View.VISIBLE)) {
            mQuickspaceContent.setVisibility(View.VISIBLE);
            mQuickspaceContent.setAlpha(0.0f);
            mQuickspaceContent.animate().setDuration(200).alpha(1.0f);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        invalidate();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mController != null && mFinishedInflate) {
            mController.setListener(this);
        }
		if (mEventController != null && mFinishedInflate) {
            mEventController.setListener(this);
        }
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        loadViews();
        mFinishedInflate = true;
        mBubbleTextView = findViewById(R.id.dummyBubbleTextView);
        mBubbleTextView.setTag(new ItemInfo() {
            @Override
            public ComponentName getTargetComponent() {
                return new ComponentName(getContext(), "");
            }
        });
        mBubbleTextView.setContentDescription("");
        if (isAttachedToWindow()) {
            if (mController != null) {
                mController.setListener(this);
            }
        }
    }

    private boolean isEventInteractive() {
        if (mCardInfo == null) return false;
        return mCardInfo.getEventType() == 1;
    }

    private void openTask(View view) {
        if (mCardInfo == null) return;
        String action = null;
        if (mCardInfo.getEventType() == 1) {
            action = Settings.ACTION_DEVICE_INTRODUCTION;
        }
        mActionReceiver.openQuickspaceTask(action, view);
        if (mController != null) {
            mController.broadcastInteracted();
        }
    }

	private VectorDrawable getEventIcon() {
		VectorDrawable eventIcon = null;
		if (mEventType == QuickspaceEventController.EVENT_FIRST_TIME) {
			eventIcon = (VectorDrawable) getContext().getDrawable(R.drawable.ic_quick_event_default).getConstantState().newDrawable();
		} else if (mEventType == QuickspaceEventController.EVENT_AMBIENT_PLAY) {
			eventIcon = (VectorDrawable) getContext().getDrawable(R.drawable.ic_quick_event_ambient).getConstantState().newDrawable();
		}
		return eventIcon;
	}

    private class WeatherSettingsObserver extends ContentObserver {

        private ContentResolver mResolver;

        WeatherSettingsObserver(ContentResolver resolver) {
            super(null);
            mResolver = resolver;
        }

        public void register() {
            mResolver.registerContentObserver(Settings.System.getUriFor(
                    SETTING_WEATHER_LOCKSCREEN_UNIT), false, this);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            updateLockscreenUnit();
        }

        public void updateLockscreenUnit() {
            mUseImperialUnit = Settings.System.getInt(mResolver, SETTING_WEATHER_LOCKSCREEN_UNIT, 1) != 0;
            if (mQuickspaceCard != null) {
                onNewCard(mQuickspaceCard);
            }
        }
    }
}
