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
package co.aoscp.lovegood.quickspace;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Process;
import android.provider.CalendarContract;
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
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.util.Themes;

import co.aoscp.lovegood.Bits;
import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.quickspace.receivers.QuickSpaceActionReceiver;
import co.aoscp.lovegood.views.DateTextView;

import java.util.ArrayList;

public class QuickSpaceView extends FrameLayout implements AnimatorUpdateListener, Runnable, IQuickspace {

	private static final String TAG = "QuickSpaceView";
	private static final String SETTING_WEATHER_LOCKSCREEN_UNIT = "weather_lockscreen_unit";

    public final ColorStateList mColorStateList;
    public BubbleTextView mBubbleTextView;
    public final Handler mHandler;
    public final int mQuickspaceBackgroundRes;

    public DateTextView mClockView;
    public ViewGroup mQuickspaceContent;
    public ImageView mEventSubIcon;
    public TextView mEventTitleSub;
    public ViewGroup mWeatherContentSub;
    public ImageView mWeatherIconSub;
    public TextView mWeatherTempSub;
    public View mTitleSeparator;
    public TextView mEventTitle;
    public ViewGroup mWeatherContent;
    public ImageView mWeatherIcon;
    public TextView mWeatherTemp;

    public boolean mIsQuickEvent;
    public boolean mFinishedInflate;
	private boolean mUseImperialUnit;
	private boolean mWeatherAvailable;

    private QuickSpaceActionReceiver mActionReceiver;
	private QuickspaceController mController;
    private QuickspaceCard mCardInfo;
	private WeatherSettingsObserver mWeatherSettingsObserver;

    public QuickSpaceView(Context context, AttributeSet set) {
        super(context, set);
        mActionReceiver = new QuickSpaceActionReceiver(context);
        mController = QuickspaceController.get(context);
        mHandler = new Handler();
        mColorStateList = ColorStateList.valueOf(Themes.getAttrColor(getContext(), R.attr.workspaceTextColor));
        mQuickspaceBackgroundRes = R.drawable.bg_quickspace;
        setClipChildren(false);
		mWeatherSettingsObserver = new WeatherSettingsObserver(context.getContentResolver());
        mWeatherSettingsObserver.register();
        mWeatherSettingsObserver.updateLockscreenUnit();
    }

    @Override
    public void onNewCard(ArrayList<QuickspaceCard> info) {
		if (info != null && info.size() >= 1) {
		    mCardInfo = (QuickspaceCard) info.get(0);
		    boolean isQuickEvent = mCardInfo != null && mCardInfo.getEventType() != 0;
            if (mIsQuickEvent != isQuickEvent) {
                mIsQuickEvent = isQuickEvent;
                prepareLayout();
            }
            mWeatherAvailable = mCardInfo != null && mCardInfo.getStatus() == 0;
            getQuickSpaceView();
            if (mCardInfo != null && mIsQuickEvent) {
                loadDoubleLine();
            } else {
                loadSingleLine();
            }
		} else {
			Log.d(TAG, "No card info");
		}
    }

    public void loadDoubleLine() {
        setBackgroundResource(mQuickspaceBackgroundRes);
        mEventTitle.setText(mCardInfo.getEventTitle());
        mEventTitle.setEllipsize(TruncateAt.END);
        mEventTitleSub.setText(mCardInfo.getEventAction());
        mEventTitleSub.setEllipsize(TruncateAt.END);
        mEventTitleSub.setOnClickListener(null);
        //mEventSubIcon.setImageTintList(mColorStateList);
        //mEventSubIcon.setImageResource();
        bindWeather(mWeatherContentSub, mWeatherTempSub, mWeatherIconSub);
    }

    public void loadSingleLine() {
        LayoutTransition transition = mQuickspaceContent.getLayoutTransition();
        mQuickspaceContent.setLayoutTransition(transition == null ? new LayoutTransition() : null);
        setBackgroundResource(0);
        bindWeather(mWeatherContent, mWeatherTemp, mWeatherIcon);
        bindClockAndSeparator(false);
    }

    public void bindClockAndSeparator(boolean forced) {
        boolean hasGoogleCalendar = Bits.hasPackageInstalled(Launcher.getLauncher(mContext), "com.google.android.calendar");
        mClockView.setVisibility(View.VISIBLE);
        mClockView.setOnClickListener(hasGoogleCalendar ? mActionReceiver.getCalendarAction() : null);
        if (forced) {
            mClockView.reloadDateFormat(true);
        }
        mTitleSeparator.setVisibility(mWeatherAvailable ? View.VISIBLE : View.GONE);
    }

    public void bindWeather(View container, TextView title, ImageView icon) {
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
	
	public String getWeatherTemp() {
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

    public void loadViews() {
        mEventTitle = (TextView) findViewById(R.id.quick_event_title);
        mEventTitleSub = (TextView) findViewById(R.id.quick_event_title_sub);
        mEventSubIcon = (ImageView) findViewById(R.id.quick_event_icon_sub);
        mWeatherIcon = (ImageView) findViewById(R.id.weather_icon);
        mWeatherIconSub = (ImageView) findViewById(R.id.quick_event_weather_icon);
        mQuickspaceContent = (ViewGroup) findViewById(R.id.quickspace_content);
        mWeatherContent = (ViewGroup) findViewById(R.id.weather_content);
        mWeatherContentSub = (ViewGroup) findViewById(R.id.quick_event_weather_content);
        mWeatherTemp = (TextView) findViewById(R.id.weather_temp);
        mWeatherTempSub = (TextView) findViewById(R.id.quick_event_weather_temp);
        mClockView = (DateTextView) findViewById(R.id.clock_view);
        mTitleSeparator = findViewById(R.id.separator);
        setTypeface(mEventTitle, mEventTitleSub, mWeatherTemp, mWeatherTempSub, mClockView);
    }

    private void setTypeface(TextView... views) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/GoogleSans-Regular.ttf");
        for (TextView view : views) {
            if (view != null) {
                view.setTypeface(tf);
            }
        }
    }

    public void prepareLayout() {
        int indexOfChild = indexOfChild(mQuickspaceContent);
        removeView(mQuickspaceContent);
        addView(LayoutInflater.from(getContext()).inflate(mIsQuickEvent ?
                R.layout.quickspace_doubleline :
                R.layout.quickspace_singleline, this, false), indexOfChild);
        loadViews();
    }

    public void getQuickSpaceView() {
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

    @Override
    public void onLayout(boolean b, int n, int n2, int n3, int n4) {
        super.onLayout(b, n, n2, n3, n4);
        //mEventTitle.setText(cn); Todo: set the event info here
    }

    public void onPause() {
        mHandler.removeCallbacks(this);
    }

    public void run() {
    }

    public void setPadding(int n, int n2, int n3, int n4) {
        super.setPadding(0, 0, 0, 0);
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
        }
    }
}
