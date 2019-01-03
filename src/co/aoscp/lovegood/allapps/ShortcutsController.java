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
import android.content.ContentProviderClient;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ShortcutsController implements Callback, OnSharedPreferenceChangeListener {

    public static final String TAG = "ShortcutsController";

    private static ShortcutsController sController;
    private final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Handler mUiThread = new Handler(Looper.getMainLooper(), this);
    private Context mContext;
    private SharedPreferences mPrefs;
	private ItemInfo mShortcutInfo;
	private String mPackageName;
    private final ArrayList<Shortcut> mPredictedShortcuts = new ArrayList();
    private final ArrayList<Shortcut> mProccessedShortcuts = new ArrayList();

	private static final int MSG_LOAD_PREDICTIONS = 0;
	private static final int MSG_UPDATE_PREDICTIONS = 1;
	private static final int MSG_UPDATE_PREDICTIONS_ON_PACKAGE_CHANGE = 2;
	private static final long PREDICTIONS_EXPIRE_MILS = 3600000; // 1 Hour
	
	private boolean mEnabled;

	private LauncherAppsCompat mLauncherAppsCompat;
	private PackageChangeCallback mCallback = new PackageChangeCallback(this, (byte) 0);
    private UpdateListener mListener;

    private static class ShortcutData {
        String shortcutId;
        String publisherPkg;
		String badgePkg;

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
	
	private class PackageChangeCallback implements OnAppsChangedCallbackCompat {

        private ShortcutsController mController;

        public void onShortcutsChanged(String str, List<ShortcutInfoCompat> list, UserHandle userHandle) {
        }

        private PackageChangeCallback() {
        }

        PackageChangeCallback(ShortcutsController controller, byte b) {
            this();
            mController = controller;
        }

        public void onPackageRemoved(String str, UserHandle userHandle) {
            onPackageChanged(str, userHandle);
        }

        public void onPackageAdded(String str, UserHandle userHandle) {
            onPackageChanged(str, userHandle);
        }

        public void onPackageChanged(String str, UserHandle userHandle) {
            Message.obtain(mController.mWorkerThread, MSG_UPDATE_PREDICTIONS_ON_PACKAGE_CHANGE, 0, 0, new PackageInfo(str, userHandle)).sendToTarget();
        }

        public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        public void onPackagesSuspended(String[] strArr, UserHandle userHandle) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        public void onPackagesUnsuspended(String[] strArr, UserHandle userHandle) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
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
		mLauncherAppsCompat = LauncherAppsCompat.getInstance(context);
        mLauncherAppsCompat.addOnAppsChangedCallback(mCallback);
    }

	@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SettingsFragment.KEY_SHORTCUT_SUGGESTIONS.equals(str)) {
			boolean enabled = mPrefs.getBoolean(SettingsFragment.KEY_SHORTCUT_SUGGESTIONS, true);
			if (enabled != mEnabled) {
				mEnabled = enabled;
				Log.d(TAG, "Updated shortcut suggestion state");
				updateUi();
			}
        }
    }

    private static ArrayList<Shortcut> updatePredictions(ArrayList<Shortcut> predictions) {
        ArrayList<Shortcut> predictionsList = new ArrayList();
        Iterator it = predictions.iterator();
        while (it.hasNext()) {
            Shortcut shortcut = (Shortcut) it.next();
            if (shortcut.isEnabled) {
                predictionsList.add(shortcut);
            }
        }
        return predictionsList;
    }

	public void updateShortcutsOnPackageChange(PackageInfo packageInfo) {
        Iterator it = mPredictedShortcuts.iterator();
        boolean isApplying = false;
        while (it.hasNext()) {
            Shortcut shortcut = (Shortcut) it.next();
            if (shortcut.shortcut != null && shortcut.shortcutInfo != null && shortcut.badgePackage.equals(packageInfo.packageName) && shortcut.shortcut.getUserHandle().equals(packageInfo.user)) {
                setPredictedShortcuts(shortcut);
                isApplying = true;
            }
        }
        if (isApplying) {
            Message.obtain(mUiThread, MSG_LOAD_PREDICTIONS, 0, 0, updatePredictions((ArrayList) mPredictedShortcuts)).sendToTarget();
        }
    }

    public void updateUi() {
        Message.obtain(mWorkerThread, MSG_LOAD_PREDICTIONS).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_LOAD_PREDICTIONS:
                Message.obtain(mUiThread, MSG_UPDATE_PREDICTIONS, 0, 0, updatePredictions(loadPredictedShortcuts())).sendToTarget();
                break;
            case MSG_UPDATE_PREDICTIONS:
                mProccessedShortcuts.clear();
                mProccessedShortcuts.addAll((ArrayList) message.obj);
                if (mListener != null) {
                    mListener.onShortcutsUpdated(mProccessedShortcuts);
                    break;
                }
                break;
			case MSG_UPDATE_PREDICTIONS_ON_PACKAGE_CHANGE:
                updateShortcutsOnPackageChange((PackageInfo) message.obj);
                break;
        }
        return true;
    }
	
    private ArrayList<Shortcut> loadPredictedShortcuts() {
        mPredictedShortcuts.clear();
        if (!mEnabled) {
            return mPredictedShortcuts;
        }

		if (mShortcutInfo == null && mPackageName == null) {
			Log.d(TAG, "No shortcut info was found");
			return mPredictedShortcuts;
		}
        try {
            LauncherIcons obtain = LauncherIcons.obtain(mContext);
			try {
				MultiHashMap shortcutMap = new MultiHashMap();
				ShortcutData data = new ShortcutData();
				data.publisherPkg = mPackageName;
				data.shortcutId = ((ShortcutInfo) mShortcutInfo).getDeepShortcutId();
				data.badgePkg = ((ShortcutInfoCompat) mShortcutInfo).getBadgePackage(mContext);
				shortcutMap.addToList(data.publisherPkg, data);
				/*if (PREDICTIONS_EXPIRE_MILS <= 0 || PREDICTIONS_EXPIRE_MILS >= System.currentTimeMillis()) {
					shortcutMap.addToList(data.publisherPkg, data);
				} else {
                    StringBuilder sb = new StringBuilder("Shortcut expired id=");
                    sb.append(data.shortcutId);
                    sb.append(" ts=");
                    sb.append(PREDICTIONS_EXPIRE_MILS);
                    Log.d(TAG, sb.toString());
				}*/
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
                            ShortcutInfo shortcutInfo = new ShortcutInfo(infoCompat, mContext);
                            shortcutInfo.runtimeStatusFlags |= 512;
                            obtain.createShortcutIcon(infoCompat, true).applyTo((ItemInfoWithIcon) shortcutInfo);
                            try {
                                Shortcut shortcut = new Shortcut(
								        shortcutData.publisherPkg, shortcutData.shortcutId, shortcutData.badgePkg, infoCompat, shortcutInfo);
                                mPredictedShortcuts.add(shortcut);
                            } catch (Throwable ignored) {
                            }
                        }
                        StringBuilder shortCutDetailsEmpty = new StringBuilder("Shortcut details not found: shortcut=");
                        shortCutDetailsEmpty.append(infoCompat);
                        Log.d(TAG, shortCutDetailsEmpty.toString());
                    }
                }
				try {
					getPredictionShortcuts(mPredictedShortcuts);
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
        return mPredictedShortcuts;
    }

    public ArrayList<Shortcut> getPredictedShortcuts() {
        return mProccessedShortcuts;
    }

    private void getPredictionShortcuts(ArrayList<Shortcut> shortcuts) {
        Iterator it = shortcuts.iterator();
        while (it.hasNext()) {
            Shortcut shortcut = (Shortcut) it.next();
            if (shortcut.shortcut != null) {
                if (shortcut.shortcutInfo != null) {
                    setPredictedShortcuts(shortcut);
                }
            }
            it.remove();
        }
    }

    private void setPredictedShortcuts(Shortcut shortcut) {
        shortcut.isEnabled = false;
        if (shortcut.shortcut.isEnabled()) {
            ApplicationInfo applicationInfo = mLauncherAppsCompat.getApplicationInfo(shortcut.badgePackage, 0, shortcut.shortcut.getUserHandle());
            if (applicationInfo != null && applicationInfo.enabled && !PackageManagerHelper.isAppSuspended(applicationInfo)) {
                shortcut.isEnabled = true;
            }
        }
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

	public void getLaunchedShortcuts(String pkgName, ItemInfo info) {
        mPackageName = pkgName;
		mShortcutInfo = info;
    }
}
