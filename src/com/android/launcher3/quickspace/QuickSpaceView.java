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
package com.android.launcher3.quickspace;

import android.animation.ValueAnimator;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.internal.ambient.weather.WeatherClient;

import com.android.launcher3.BubbleTextView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherTab;
import com.android.launcher3.R;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.quickspace.views.DateTextView;

import java.net.URISyntaxException;

public class QuickSpaceView extends FrameLayout implements ValueAnimator.AnimatorUpdateListener, WeatherClient.WeatherObserver, Runnable {

    private static final String SETTING_WEATHER_LOCKSCREEN_UNIT = "weather_lockscreen_unit";

    protected ContentResolver mContentResolver;

    private BubbleTextView mBubbleTextView;
    private DateTextView mClockView;
    private View.OnClickListener mCalendarClickListener;
    private ImageView mWeatherIcon;
    private TextView mWeatherTemp;
    private View mSeparator;
    private ViewGroup mQuickspaceContent;
    private ViewGroup mWeatherContent;

    private final Handler mHandler;
    private WeatherClient mWeatherClient;
    private WeatherClient.WeatherInfo mWeatherInfo;
    private WeatherSettingsObserver mWeatherSettingsObserver;
    private boolean mUseImperialUnit;
    private boolean mHasGoogleCalendar = hasPackage("com.google.android.calendar");

    public QuickSpaceView(Context context, AttributeSet set) {
        super(context, set);
        mHandler = new Handler();
        if (WeatherClient.isAvailable(context)) {
            mWeatherSettingsObserver = new WeatherSettingsObserver(
                  mHandler, context.getContentResolver());
            mWeatherSettingsObserver.register();
            mWeatherSettingsObserver.updateLockscreenUnit();
            mWeatherClient = new WeatherClient(getContext());
            mWeatherClient.addObserver(this);
        }

        mCalendarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Uri content_URI = CalendarContract.CONTENT_URI;
                final Uri.Builder appendPath = content_URI.buildUpon().appendPath("time");
                ContentUris.appendId(appendPath, System.currentTimeMillis());
                final Intent addFlags = new Intent(Intent.ACTION_VIEW)
                        .setData(appendPath.build())
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                try {
                    final Context context = getContext();
                    Launcher.getLauncher(context).startActivitySafely(view, addFlags, null);
                } catch (ActivityNotFoundException ex) {
                    LauncherAppsCompat.getInstance(getContext()).showAppDetailsForProfile(new ComponentName("com.google.android.calendar", ""), Process.myUserHandle());
                }
            }
        };
    }

    private void initListeners() {
        loadSingleLine();
    }

    private void loadSingleLine() {
        setBackgroundResource(0);
        mClockView.setOnClickListener(mHasGoogleCalendar ? mCalendarClickListener : null);
        if (!WeatherClient.isAvailable(getContext())) {
            mWeatherContent.setVisibility(View.GONE);
            mSeparator.setVisibility(View.GONE);
            Log.d("QuickSpaceView", "WeatherProvider is unavailable");
            return;
        }
        if (mWeatherInfo == null) {
            mWeatherContent.setVisibility(View.GONE);
            mSeparator.setVisibility(View.GONE);
            Log.d("QuickSpaceView", "WeatherInfo is null");
            return;
        }
        if (mWeatherInfo.getStatus() != WeatherClient.WEATHER_UPDATE_SUCCESS) {
            mWeatherContent.setVisibility(View.GONE);
            mSeparator.setVisibility(View.GONE);
            Log.d("QuickSpaceView", "Could not update weather");
            return;
        }

        int temperatureMetric = mWeatherInfo.getTemperature(true);
        int temperatureImperial = mWeatherInfo.getTemperature(false);
        String temperatureText = mUseImperialUnit ?
                Integer.toString(temperatureImperial) + "°F" :
                Integer.toString(temperatureMetric) + "°C";
        Icon conditionIcon = Icon.createWithResource(getContext(), mWeatherInfo.getWeatherConditionImage());

        mSeparator.setVisibility(View.VISIBLE);
        mWeatherContent.setVisibility(View.VISIBLE);
        mWeatherTemp.setText(temperatureText);
        mWeatherIcon.setImageIcon(conditionIcon);
    }

    private void loadViews() {
        mClockView = findViewById(R.id.clock_view);
        mQuickspaceContent = findViewById(R.id.quickspace_content);
        mSeparator = findViewById(R.id.separator);
        mWeatherIcon = findViewById(R.id.weather_icon);
        mWeatherContent = findViewById(R.id.weather_content);
        mWeatherTemp = findViewById(R.id.weather_temp);

        setTypeface(mClockView, mWeatherTemp);
    }

    private void setTypeface(TextView... views) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/GoogleSans-Regular.ttf");
        for (TextView view : views) {
            if (view != null) {
                view.setTypeface(tf);
            }
        }
    }

    public void getQuickSpaceView() {
        boolean visible = mQuickspaceContent.getVisibility() == View.VISIBLE;
        initListeners();
        if (!visible) {
            mQuickspaceContent.setVisibility(View.VISIBLE);
            mQuickspaceContent.setAlpha(0f);
            mQuickspaceContent.animate().setDuration(200L).alpha(1f);
        }
    }

    @Override
    public void onWeatherUpdated(WeatherClient.WeatherInfo weatherInfo) {
        mWeatherInfo = weatherInfo;
        getQuickSpaceView();
    }

    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        invalidate();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        loadViews();
        mContentResolver = getContext().getContentResolver();
        mBubbleTextView = findViewById(R.id.dummyBubbleTextView);
        mBubbleTextView.setTag(new ItemInfo() {
            @Override
            public ComponentName getTargetComponent() {
                return new ComponentName(getContext(), "");
            }
        });
        mBubbleTextView.setContentDescription("");
    }

    public void onResume() {
        getQuickSpaceView();
    }

    @Override
    public void run() {
        getQuickSpaceView();
    }

    @Override
    public void setPadding(final int n, final int n2, final int n3, final int n4) {
        super.setPadding(0, 0, 0, 0);
    }

    private boolean hasPackage(String pkgName) {
        try {
            ApplicationInfo ai = getContext().getPackageManager()
                    .getApplicationInfo(pkgName, 0);
            return ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private class WeatherSettingsObserver extends ContentObserver {

        private Handler mHandler;
        private ContentResolver mResolver;

        WeatherSettingsObserver(Handler handler, ContentResolver resolver) {
            super(handler);
            mHandler = handler;
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