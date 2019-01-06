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

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;

import co.aoscp.lovegood.Bits;
import co.aoscp.lovegood.SettingsFragment;

import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.util.MultiHashMap;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class ShortcutsController implements Callback, OnSharedPreferenceChangeListener {

    public static final String TAG = "ShortcutsController";

	private static final Uri SUGGEST_PROVIDER = Uri.parse("content://" + "co.aoscp.minai.suggestprovider");
	private static final String[] SUGGESTION_DATA = new String[] {"suggest_id", "shortcut_package", "shortcut_id"};

    private static ShortcutsController sController;
    private final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Handler mUiThread = new Handler(Looper.getMainLooper(), this);
    private Context mContext;
    private SharedPreferences mPrefs;
	private ContentValues mShortcutData;
	private UpdateListener mListener;
    private final ArrayList<Shortcut> mPredictedShortcuts = new ArrayList();
    private final ArrayList<Shortcut> mProccessedShortcuts = new ArrayList();
	private final ContentObserver UI_OBSERVER = new ContentObserver(mUiThread) {
		@Override
        public void onChange(boolean enabled) {
            updateUi();
        }
    };

	private static final int MSG_CLEAR_TOP_SUGGESTION = 0;
	private static final int MSG_LOAD_PREDICTIONS = 1;
	private static final int MSG_UPDATE_PREDICTIONS = 2;
	private static final int MAX_SUGGESTIONS = 2;
	private static final String KEY_SUGGESTIONS_LIST = "key_suggestions";
	private static final String KEY_SUGGESTIONS_INITIALIZED = "key_suggestions_initialized";

	private boolean mEnabled;

    private static class ShortcutData {
		String suggestId;
        String shortcutId;
        String publisherPkg;

        private ShortcutData() {
        }

        ShortcutData(byte b) {
            this();
        }
    }

    public interface UpdateListener {
        void onShortcutsUpdated(ArrayList<Shortcut> arrayList);
    }

    private class PackageInfo {
        String packageName;
        UserHandle user;

        PackageInfo(String str, UserHandle userHandle) {
            this.packageName = str;
            this.user = userHandle;
        }
    }

    public static ShortcutsController get(Context context) {
        Preconditions.assertUIThread();
        if (sController == null) {
            sController = new ShortcutsController(context.getApplicationContext());
        }
        return sController;
    }

    private ShortcutsController(Context context) {
        mContext = context;
        mPrefs = Utilities.getPrefs(context);
        mPrefs.registerOnSharedPreferenceChangeListener(this);
		updateSuggestionState();
    }

	private void updateSuggestionState() {
		boolean enabled = mPrefs.getBoolean(SettingsFragment.KEY_SHORTCUT_SUGGESTIONS, true);
		mEnabled = enabled;
		Log.d(TAG, "Updated shortcut suggestion state");
		if (!mEnabled) {
			Log.d(TAG, "Removing suggest observer");
			mContext.getContentResolver().unregisterContentObserver(UI_OBSERVER);
		} else {
			Log.d(TAG, "Adding suggest observer");
			mContext.getContentResolver().registerContentObserver(SUGGEST_PROVIDER, true, UI_OBSERVER);
		}
		//updateUi();
	}

	@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SettingsFragment.KEY_SHORTCUT_SUGGESTIONS.equals(str)) {
			updateSuggestionState();
        }
    }

    private static ArrayList<Shortcut> updatePredictions(ArrayList<Shortcut> predictions) {
        ArrayList<Shortcut> predictionsList = new ArrayList();
        Iterator it = predictions.iterator();
        while (it.hasNext()) {
            Shortcut shortcut = (Shortcut) it.next();
            predictionsList.add(shortcut);
        }
        return predictionsList;
    }

    public void updateUi() {
		Message.obtain(mWorkerThread, MSG_LOAD_PREDICTIONS).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
			case MSG_CLEAR_TOP_SUGGESTION:
			    int currentList = mPrefs.getInt(KEY_SUGGESTIONS_LIST, 0);
				if (currentList >= MAX_SUGGESTIONS && isAlreadySuggested()) {
			        ListIterator processedIt = mProccessedShortcuts.listIterator();
				    processedIt.next();
				    processedIt.remove();
					Log.d(TAG, "Removing last suggestion for new spot");
				}
				Message.obtain(mUiThread, MSG_UPDATE_PREDICTIONS, 0, 0, updatePredictions(loadPredictedShortcuts())).sendToTarget();
                break;
            case MSG_LOAD_PREDICTIONS:
			    Message.obtain(mWorkerThread, MSG_CLEAR_TOP_SUGGESTION).sendToTarget();
                break;
            case MSG_UPDATE_PREDICTIONS:
                mProccessedShortcuts.addAll((ArrayList) message.obj);
                if (mListener != null) {
                    mListener.onShortcutsUpdated(mProccessedShortcuts);
                    break;
                }
                break;
        }
        return true;
    }

    private ArrayList<Shortcut> loadPredictedShortcuts() {
		mPredictedShortcuts.clear();
        if (!mEnabled) {
            return mPredictedShortcuts;
        }

		if (isAlreadySuggested()) {
			Log.d(TAG, "Suggestion is already on the list");
			return mPredictedShortcuts;
		}

        try {
            LauncherIcons obtain = LauncherIcons.obtain(mContext);
			try (Cursor query = mContext.getContentResolver().query(SUGGEST_PROVIDER, SUGGESTION_DATA, null, null,
                null)) {
                if (query == null) {
                    try {
                        Log.e(TAG, "No cursor found");
                        if (obtain != null) {
                            obtain.close();
                        }
                        return mPredictedShortcuts;
                    } catch (Throwable unused) {
                    }
                }
				MultiHashMap shortcutMap = new MultiHashMap();
				while (query.moveToNext()) {
					ShortcutData data = new ShortcutData();
					data.suggestId = query.getString(0);
				    data.publisherPkg = query.getString(1);
				    data.shortcutId = query.getString(2);
				    shortcutMap.addToList(data.publisherPkg, data);
				}
				DeepShortcutManager shortcuts = DeepShortcutManager.getInstance(mContext);
				Iterator shortcutMapIt = shortcutMap.entrySet().iterator();
				while (shortcutMapIt.hasNext()) {
					Entry entry = (Entry) shortcutMapIt.next();
                    String keyData = (String) entry.getKey();
                    List list = (ArrayList) entry.getValue();
                    ArrayList dataList = new ArrayList();
                    Iterator dataListIt = list.iterator();
					while (dataListIt.hasNext()) {
                        dataList.add(((ShortcutData) dataListIt.next()).shortcutId);
                    }
					List<ShortcutInfoCompat> queryDetails = shortcuts.queryForFullDetails(keyData, dataList, Process.myUserHandle());
					for (ShortcutInfoCompat infoCompat : queryDetails) {
                        ShortcutData shortcutData = parseData(list, infoCompat);
                        if (shortcutData != null) {
                            if (TextUtils.isEmpty(infoCompat.getShortLabel())) {
                                StringBuilder emptyShortcutInfo = new StringBuilder("Empty shortcut label: shortcut=");
                                emptyShortcutInfo.append(infoCompat);
                                Log.d(TAG, emptyShortcutInfo.toString());
                            }
                            ShortcutInfo shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) infoCompat, mContext);
                            shortcutInfo.runtimeStatusFlags |= 512;
                            obtain.createShortcutIcon(infoCompat, true).applyTo(shortcutInfo);
                            try {
                                Shortcut shortcut = new Shortcut(shortcutData.suggestId, shortcutData.publisherPkg, 
								        shortcutData.shortcutId, infoCompat, shortcutInfo);
                                mPredictedShortcuts.add(shortcut);
								addToSuggestionList();
								StringBuilder details = new StringBuilder("Adding shortcut: ");
								details.append(shortcutData.publisherPkg);
								details.append(shortcutData.shortcutId);
								Log.d(TAG, details.toString());
                            } catch (Throwable ignored) {
								Log.d(TAG, "Could not create shortcut data");
                            }
                        }
                    }
                }
				try {
					if (obtain != null) {
                        obtain.close();
                    }
					mContext.getContentResolver().update(SUGGEST_PROVIDER, null, null, null);
					return mPredictedShortcuts;
                } catch (Throwable ignored) {
                }
			} catch (Throwable ignored) {
            }
		} catch (Exception e) {
            Log.d(TAG, "Error loading predicted shortcuts");
        }
		Log.d(TAG, "Storing shortcut predictions set");
        return mPredictedShortcuts;
    }

    public ArrayList<Shortcut> getPredictedShortcuts() {
        return mProccessedShortcuts;
    }

    public void setListener(UpdateListener updateListener) {
        mListener = updateListener;
    }

    private ShortcutData parseData(List<ShortcutData> list, ShortcutInfoCompat infoCompat) {
        if (infoCompat != null) {
            for (ShortcutData data : list) {
                if (data.shortcutId.equals(infoCompat.getId())) {
                    return data;
                }
            }
        }
        return null;
    }

	public boolean isEnabled() {
        return mEnabled;
    }

	public void getLaunchedShortcuts(String pkgName, ItemInfo id) {
		boolean isInitialized = mPrefs.getBoolean(KEY_SUGGESTIONS_INITIALIZED, false);
		if (mEnabled && !isInitialized) {
			Log.d(TAG, "Beginning first setup");
			addShortcutData(pkgName, id);
			updateUi();
			mPrefs.edit().putBoolean(KEY_SUGGESTIONS_INITIALIZED, true).apply();
			return;
		}
		addShortcutData(pkgName, id);
    }

	private void addToSuggestionList() {
		mPrefs.edit().putInt(KEY_SUGGESTIONS_LIST, mPrefs.getInt(KEY_SUGGESTIONS_LIST, 0) + 1).apply();
	}

	private void addShortcutData(String pkgName, ItemInfo shortcutId) {
        ContentValues data = new ContentValues();
		data.put("suggest_id", pkgName + "_" + shortcutId);
        data.put("shortcut_package", pkgName);
        data.put("shortcut_id", ((ShortcutInfo) shortcutId).getDeepShortcutId());
        mContext.getContentResolver().insert(SUGGEST_PROVIDER, data);
		mShortcutData = data;
    }

	private boolean isAlreadySuggested() {
        try (Cursor query = mContext.getContentResolver().query(SUGGEST_PROVIDER, SUGGESTION_DATA, null, null, null)) {
            if (query != null) {
                if (query.moveToFirst()) {
					if (mShortcutData.get("suggest_id").equals(query.getString(0))) {
						return true;
					}
                }
            }
        }
        return false;
    }
}
