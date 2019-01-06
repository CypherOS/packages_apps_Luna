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

package co.aoscp.lovegood;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.Random;

public class ShortcutProvider extends ContentProvider {

	private static final String TAG = "ShortcutProvider";

    private static final Uri SUGGEST_PROVIDER = Uri.parse("content://" + "co.aoscp.minai.suggestprovider");
    private static final String[] SUGGESTION_DATA = {ShortcutDb.KEY_ID, ShortcutDb.KEY_SHORTCUT_PACKAGE, ShortcutDb.KEY_SHORTCUT_ID};

	private static final long SUGGESTION_UPDATE_INTERVAL = 60000; // 1 min //3600000; // 1 Hour

	private AlarmManager mAlarmManager;
	private PendingIntent mPendingUpdate;
	private String mUpdateAction;
	private IntentFilter mUpdateFilter;

	private boolean mIsScreenOn = true;
	private long mLastUpdated = 0;
	private long mScheduledTime = 0;

    private ShortcutDb mDbHelper;
	private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                onScreenOff();
            } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                onScreenOn();
            } else if (mUpdateAction.equals(intent.getAction())) {
                getContext().getContentResolver().notifyChange(SUGGEST_PROVIDER, null);
            }
        }
    };

    @Override
    public boolean onCreate() {
        mDbHelper = new ShortcutDb(getContext());
		addUpdateReceiver();
        return false;
    }

	private void addUpdateReceiver() {
		mUpdateAction = "updateAction_" + Integer.toString(new Random().nextInt((20000000 - 10000000) + 1) + 10000000);
        mPendingUpdate = PendingIntent.getBroadcast(getContext(), new Random().nextInt((20000000 - 10000000) + 1) + 10000000, new Intent(mUpdateAction), 0);
		mUpdateFilter = new IntentFilter();
        mUpdateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mUpdateFilter.addAction(Intent.ACTION_SCREEN_ON);
        mUpdateFilter.addAction(mUpdateAction);
        mUpdateFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        getContext().registerReceiver(mUpdateReceiver, mUpdateFilter);
	}

	private boolean needsUpdate() {
        boolean hasExpired = System.currentTimeMillis() - mLastUpdated > SUGGESTION_UPDATE_INTERVAL;
        return hasExpired;
    }

	private void onScreenOn() {
		mIsScreenOn = true;
        if (needsUpdate() || mLastUpdated == 0) {
            Log.d(TAG, "Needs update, triggering updateUi");
            getContext().getContentResolver().notifyChange(SUGGEST_PROVIDER, null);
        } else {
            Log.d(TAG, "Scheduling update");
            scheduleUpdateAlarm();
        }
    }

	private void onScreenOff() {
        mIsScreenOn = false;
        cancelUpdateAlarm();
    }

	private void scheduleUpdateAlarm() {
        if (!mIsScreenOn) {
            return;
        }

        if (System.currentTimeMillis() >= mScheduledTime){
            mScheduledTime = System.currentTimeMillis() + SUGGESTION_UPDATE_INTERVAL;
        }
        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.cancel(mPendingUpdate);
        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, mScheduledTime, mPendingUpdate);
        Log.d(TAG, "Update scheduled");
    }

	private void cancelUpdateAlarm() {
        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.cancel(mPendingUpdate);
        Log.d(TAG, "Update scheduling canceled");
    }

	private void resetUpdateAlarm() {
        mScheduledTime = 0;
        scheduleUpdateAlarm();
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long hasSuggested = hasMatchedShortcutAlready(values);
        long id = hasSuggested != -1 ? hasSuggested : db.insert(ShortcutDb.SQLITE_TABLE, null, values);
        return Uri.parse(SUGGEST_PROVIDER + "/" + id);
    }

    private int hasMatchedShortcutAlready(ContentValues values) {
        try (Cursor query = query(ShortcutProvider.SUGGEST_PROVIDER, ShortcutProvider.SUGGESTION_DATA, null, null, null)) {
            if (query != null) {
                if (query.moveToFirst()) {
                    return values.get(ShortcutDb.KEY_ID).equals(query.getString(0)) ? query.getInt(0) : -1;
                }
            }
        }
        return -1;
    }

    @Override
    public Cursor query(Uri uri, String[] data, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(ShortcutDb.SQLITE_TABLE);
        return builder.query(db, data, null, null, null, null, null);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        return db.delete(ShortcutDb.SQLITE_TABLE, null, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
		mLastUpdated = System.currentTimeMillis();
		resetUpdateAlarm();
        return 0;
    }

}