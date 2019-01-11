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

	public static final int BOOST_ON_OPEN = 9;
    public static final Set<String> EMPTY_SET = new HashSet();
	private static final int MAX_SUGGESTIONS = 2;

	private static final String KEY_SUGGESTIONS = "key_suggestions";
	private static final String KEY_SUGGESTIONS_COUNT = "key_suggestions_count_";

	private boolean mEnabled;

    private static class ShortcutData {
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

	private int getLaunchCount(String suggestions) {
        return mPrefs.getInt(KEY_SUGGESTIONS_COUNT + suggestions, 0);
    }

	private boolean decayHasSpotFree(Set<String> toDecay, Editor edit) {
        boolean spotFree = false;
        Set<String> toRemove = new HashSet<>();
        for (String suggestion : toDecay) {
            int launchCount = getLaunchCount(suggestion);
            if (launchCount > 0) {
                edit.putInt(KEY_SUGGESTIONS_COUNT + suggestion, --launchCount);
            } else if (!spotFree) {
                edit.remove(KEY_SUGGESTIONS_COUNT + suggestion);
                toRemove.add(suggestion);
                spotFree = true;
            }
        }
        for (String suggestions : toRemove) {
            toDecay.remove(suggestions);
        }
        return spotFree;
    }

	private void updateSuggestionState() {
		boolean enabled = mPrefs.getBoolean(SettingsFragment.KEY_SHORTCUT_SUGGESTIONS, true);
		mEnabled = enabled;
		Log.d(TAG, "Updated shortcut suggestion state");
		if (!mEnabled) {
			Set<String> predictionSet = getStringSetCopy();
            for (String suggestions : predictionSet) {
                Log.i("Predictor", "Clearing " + suggestions + " at " + getLaunchCount(suggestions));
                mPrefs.edit().remove(KEY_SUGGESTIONS_COUNT + suggestions);
            }
            mPrefs.edit().putStringSet(KEY_SUGGESTIONS, EMPTY_SET);
            mPrefs.edit().apply();
        }
	}

	@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SettingsFragment.KEY_SHORTCUT_SUGGESTIONS.equals(str)) {
			updateSuggestionState();
        }
    }

	public ArrayList<Shortcut> getPredictedShortcuts() {
		ArrayList<Shortcut> list = new ArrayList();
		clearNonExistingShortcuts();
		List<String> predictionList = new ArrayList(getStringSetCopy());
		Collections.sort(predictionList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(getLaunchCount(o2), getLaunchCount(o1));
            }
        });

		for (String prediction : predictionList) {
			String[] shortcutInfo = prediction.split('/');
			list.add(loadPredictedShortcuts(shortcutInfo[0], shortcutInfo[1]));
		}
		
		Log.d(TAG, "Got predicted shortcuts");
        return list;
	}

    public void updateUi() {
		Message.obtain(mWorkerThread, MSG_LOAD_PREDICTIONS).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
			case MSG_LOAD_PREDICTIONS:
			    Message.obtain(mUiThread, MSG_UPDATE_PREDICTIONS, 0, 0, getPredictedShortcuts()).sendToTarget();
                break;
        }
        return true;
    }

    private ArrayList<Shortcut> loadPredictedShortcuts(String packageName, String shortcutId) {
		mPredictedShortcuts.clear();
        if (!mEnabled) {
            return mPredictedShortcuts;
        }

        try {
            LauncherIcons obtain = LauncherIcons.obtain(mContext);
			try {
				MultiHashMap shortcutMap = new MultiHashMap();
				ShortcutData data = new ShortcutData();
				data.publisherPkg = packageName;
				data.shortcutId = shortcutId;
				shortcutMap.addToList(data.publisherPkg, data);

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
                                Shortcut shortcut = new Shortcut(shortcutData.publisherPkg, shortcutData.shortcutId, infoCompat, shortcutInfo);
                                mPredictedShortcuts.add(shortcut);

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

	public void getLaunchedShortcuts(String packageName, ItemInfo shortcutId) {
		if (mEnabled) {
			clearNonExistingShortcuts();

			String loggedSuggestion = packageName + '/' + ((ShortcutInfo) shortcutId).getDeepShortcutId();
            Set<String> suggestions = getStringSetCopy();

            if (suggestions.contains(loggedSuggestion)) {
                mPrefs.edit().putInt(KEY_SUGGESTIONS + loggedSuggestion, getLaunchCount(loggedSuggestion) + BOOST_ON_OPEN);
            } else if (suggestions.size() < MAX_SUGGESTIONS || decayHasSpotFree(suggestions, mPrefs.edit())) {
                suggestions.add(loggedSuggestion);
            }

            mPrefs.edit().putStringSet(KEY_SUGGESTIONS, suggestions);
            mPrefs.edit().apply();
		}
    }

	private void clearNonExistingShortcuts() {
        Set<String> originalSet = mPrefs.getStringSet(KEY_SUGGESTIONS, EMPTY_SET);
        Set<String> predictionSet = new HashSet<>(originalSet);
        for (String suggestions : originalSet) {
            try {
                mPackageManager.getPackageInfo(suggestions.substring(0, suggestions.indexOf('/')), 0);
            } catch (NameNotFoundException | NumberFormatException e) {
                predictionSet.remove(suggestions);
                mPrefs.edit().remove(KEY_SUGGESTIONS_COUNT + suggestions);
            }
        }
        mPrefs.edit().putStringSet(KEY_SUGGESTIONS, predictionSet);
        mPrefs.edit().apply();
        Log.d(TAG, "Shortcuts cleared!");
    }

	private Set<String> getStringSetCopy() {
        Set<String> set = new HashSet<>();
        set.addAll(mPrefs.getStringSet(KEY_SUGGESTIONS, EMPTY_SET));
        return set;
    }
}
