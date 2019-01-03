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

public class ActionsController implements OnSharedPreferenceChangeListener, Callback {

    public static final String TAG = "ActionsController";
    public static final String AIAI_PACKAGE = "com.google.android.as";
    public static final int MAX_ITEMS = 2;

    private static final Uri SUGGEST_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsuggestprovider").build();
    private static final Uri LOGGING_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionloggingprovider").build();
    private static final Uri SETTING_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsettingprovider").build();
    private static final String[] ACTION_DATA = new String[]{"action_id", "shortcut_id", "expiration_time_millis", "publisher_package", "badge_package", "position"};

    private static ActionsController sController;
    private final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Handler mUiThread = new Handler(Looper.getMainLooper(), this);
    private Context mContext;
    private SharedPreferences mActionsEnabled;
    private SharedPreferences mFileImpressions;
    private final ArrayList<Action> mPredictedActions = new ArrayList();
    private final ArrayList<Action> mProccessedActions = new ArrayList();

    private final ContentObserver UI_OBSERVER = new ContentObserver(mUiThread) {
        public void onChange(boolean enabled) {
            updateUi();
        }
    };

    private final Logger mLogger = new Logger(this);
    private final LauncherAppsCompat mLauncherAppsCompat;
    private UpdateListener mListener;
    private PackageChangeCallback mCallback = new PackageChangeCallback(this, (byte) 0);

    class ActionServicesReceiver extends BroadcastReceiver {
        ActionServicesReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            getContentProvider();
        }
    }

    private static class ActionData {
        String actionId;
        String shortcutId;
        long expTimeMils;
        String publisherPkg;
        String badgePkg;
        long position;

        private ActionData() {
        }

        ActionData(byte b) {
            this();
        }
    }

    private static class Data {
        int clickedPos;
        int eventType;
        long timestamp;
        String clickedId;
        String topSuggestions;

        private Data() {
        }

        Data(byte b) {
            this();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("eventType:");
            sb.append(eventType);
            sb.append(" clickedId:");
            sb.append(clickedId);
            sb.append(" clickedPos:");
            sb.append(clickedPos);
            sb.append(" top:");
            sb.append(topSuggestions);
            return sb.toString();
        }
    }

    public static class Logger {

        public static final int CLICK_EVENT_TYPE = 1;
        public static final int DISMISS_EVENT_TYPE = 3;
        public static final int IMPRESSION_EVENT_TYPE = 2;

        private ActionsController mController;

        Logger(ActionsController controller) {
            mController = controller;
        }

        public void logImpression() {
            SharedPreferences prefs = mController.mFileImpressions;
            long currentTimeMillis = System.currentTimeMillis();
            int min = Math.min(mController.mProccessedActions.size(), 2);
            for (int i = 0; i < min; i++) {
                String proccessedActions = ((Action) mController.mProccessedActions.get(i)).f42id;
                if (proccessedActions != null) {
                    if (prefs.contains(proccessedActions)) {
                        String string = prefs.getString(proccessedActions, "");
                        String[] split = string.split(",");
                        long parseLong = Long.parseLong(split[0]);
                        for (int i2 = 1; i2 < split.length; i2++) {
                            parseLong += Long.parseLong(split[i2]);
                        }
                        long timestamp = currentTimeMillis - parseLong;
                        Editor edit = prefs.edit();
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append(",");
                        sb.append(timestamp);
                        edit.putString(proccessedActions, sb.toString()).apply();
                    } else {
                        prefs.edit().putString(proccessedActions, String.valueOf(currentTimeMillis)).apply();
                    }
                }
            }
        }

        public void logClick(String id, int pos) {
            Data data = new Data((byte) 0);
            data.timestamp = System.currentTimeMillis();
            data.eventType = 1;
            if (id == null) {
                id = "";
            }
            data.clickedId = id;
            data.clickedPos = pos + 1;
            Message.obtain(mController.mWorkerThread, 0, 0, 0, data).sendToTarget();
        }

        public void logDismiss(String id) {
            Data data = new Data((byte) 0);
            data.timestamp = System.currentTimeMillis();
            data.eventType = 3;
            if (id == null) {
                id = "";
            }
            data.clickedId = id;
            Message.obtain(mController.mWorkerThread, 0, 0, 0, data).sendToTarget();
        }
    }

    public interface UpdateListener {
        void onUpdated(ArrayList<Action> arrayList);
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

        private ActionsController mController;

        public void onShortcutsChanged(String str, List<ShortcutInfoCompat> list, UserHandle userHandle) {
        }

        private PackageChangeCallback() {
        }

        PackageChangeCallback(ActionsController controller, byte b) {
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
            Message.obtain(mController.mWorkerThread, 5, 0, 0, new PackageInfo(str, userHandle)).sendToTarget();
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

    public static ActionsController get(Context context) {
        Preconditions.assertUIThread();
        if (sController == null) {
            sController = new ActionsController(context.getApplicationContext());
        }
        return sController;
    }

    private ActionsController(Context context) {
        mContext = context;
        mActionsEnabled = Utilities.getPrefs(context);
        mFileImpressions = context.getSharedPreferences("pref_file_impressions", 0);
        mActionsEnabled.registerOnSharedPreferenceChangeListener(this);
        updateActionProviderState();
        getContentProvider();
        mLauncherAppsCompat = LauncherAppsCompat.getInstance(context);
        mLauncherAppsCompat.addOnAppsChangedCallback(mCallback);
        context.registerReceiver(new ActionServicesReceiver(), Bits.getPackageIntentInfo(AIAI_PACKAGE, "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_DATA_CLEARED", "android.intent.action.PACKAGE_RESTARTED"));
    }

    private boolean isEnabled() {
        return mActionsEnabled.getBoolean(SettingsFragment.KEY_APP_ACTIONS, true);
    }

    private void updateActionProviderState() {
        Log.d(TAG, "Updating action suggestion state");
        Message.obtain(mWorkerThread, 2, Boolean.valueOf(isEnabled())).sendToTarget();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SettingsFragment.KEY_APP_ACTIONS.equals(str)) {
            updateActionProviderState();
            updateUi();
        }
    }

    private void getContentProvider() {
        ContentResolver resolver = mContext.getContentResolver();
        resolver.unregisterContentObserver(UI_OBSERVER);
        try {
            resolver.registerContentObserver(SUGGEST_PROVIDER, true, UI_OBSERVER);
            updateUi();
            Message.obtain(mWorkerThread, 3).sendToTarget();
        } catch (SecurityException e) {
            Log.d(TAG, "Content provider not found");
        }
    }

    private static ArrayList<Action> addAndApply(ArrayList<Action> arrayList) {
        ArrayList<Action> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (action.isEnabled) {
                arrayList2.add(action);
            }
        }
        return arrayList2;
    }

    public void updateActionsOnPackageChange(PackageInfo packageInfo) {
        Iterator it = mPredictedActions.iterator();
        boolean isApplying = false;
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (action.shortcut != null && action.shortcutInfo != null && action.badgePackage.equals(packageInfo.packageName) && action.shortcut.getUserHandle().equals(packageInfo.user)) {
                applyActions(action);
                isApplying = true;
            }
        }
        if (isApplying) {
            Message.obtain(mUiThread, 4, 0, 0, addAndApply((ArrayList) mPredictedActions)).sendToTarget();
        }
    }

    private void updateUi() {
        Message.obtain(mWorkerThread, 1).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                try {
                    mContext.getContentResolver().insert(LOGGING_PROVIDER, getActionData((Data) message.obj));
                    break;
                } catch (Exception e) {
                    Log.d(TAG, "Failed to write to logging provider");
                    break;
                }
            case 1:
                Message.obtain(mUiThread, 4, 0, 0, addAndApply(loadPredictedActions())).sendToTarget();
                break;
            case 2:
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                ContentValues contentValues = new ContentValues();
                contentValues.put("enable_action_suggest", Boolean.valueOf(booleanValue));
                try {
                    mContext.getContentResolver().insert(SETTING_PROVIDER, contentValues);
                    break;
                } catch (Exception e2) {
                    Log.d(TAG, "Failed to write to settings provider");
                    break;
                }
            case 3:
                parseImpressions();
                break;
            case 4:
                mProccessedActions.clear();
                mProccessedActions.addAll((ArrayList) message.obj);
                if (mListener != null) {
                    mListener.onUpdated(mProccessedActions);
                    break;
                }
                break;
            case 5:
                updateActionsOnPackageChange((PackageInfo) message.obj);
                break;
        }
        return true;
    }

    private ArrayList<Action> loadPredictedActions() {
        mPredictedActions.clear();
        if (!isEnabled()) {
            return mPredictedActions;
        }
        try {
            LauncherIcons obtain = LauncherIcons.obtain(mContext);
            try (Cursor query = mContext.getContentResolver().query(SUGGEST_PROVIDER, ACTION_DATA, null, null, null)) {
                if (query == null) {
                    try {
                        Log.e(TAG, "No cursor found");
                        if (obtain != null) {
                            obtain.close();
                        }
                        return mPredictedActions;
                    } catch (Throwable th4) {
                    }
                }
                MultiHashMap multiHashMap = new MultiHashMap();
                while (true) {
                    if (!query.moveToNext()) {
                        break;
                    }
                    ActionData actionData = new ActionData();
                    actionData.actionId = query.getString(0);
                    actionData.shortcutId = query.getString(1);
                    actionData.publisherPkg = query.getString(3);
                    actionData.badgePkg = query.getString(4);
                    actionData.expTimeMils = query.getLong(2);
                    actionData.position = query.getLong(5);
                    if (actionData.expTimeMils <= 0 || actionData.expTimeMils >= System.currentTimeMillis()) {
                        multiHashMap.addToList(actionData.publisherPkg, actionData);
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("shortcut expired id=");
                        stringBuilder.append(actionData.shortcutId);
                        stringBuilder.append(" ts=");
                        stringBuilder.append(actionData.expTimeMils);
                        Log.d(TAG, stringBuilder.toString());
                    }
                }
                DeepShortcutManager shortcuts = DeepShortcutManager.getInstance(mContext);
                Iterator hashMapIterator = multiHashMap.entrySet().iterator();
                while (hashMapIterator.hasNext()) {
                    Entry entry = (Entry) hashMapIterator.next();
                    String keyData = (String) entry.getKey();
                    List list = (ArrayList) entry.getValue();
                    ArrayList actionDataList = new ArrayList();
                    Iterator ArrayListIterator = list.iterator();
                    while (ArrayListIterator.hasNext()) {
                        actionDataList.add(((ActionData) ArrayListIterator.next()).shortcutId);
                    }
                    List<ShortcutInfoCompat> queryDetails = shortcuts.queryForFullDetails(keyData, actionDataList, Process.myUserHandle());
                    for (ShortcutInfoCompat shortcutInfoCompat : queryDetails) {
                        ActionData dataParse = parseData(list, shortcutInfoCompat);
                        if (dataParse != null) {
                            if (TextUtils.isEmpty(shortcutInfoCompat.getShortLabel())) {
                                StringBuilder emptyShortcutInfo = new StringBuilder("Empty shortcut label: shortcut=");
                                emptyShortcutInfo.append(shortcutInfoCompat);
                                Log.d(TAG, emptyShortcutInfo.toString());
                            }
                            ShortcutInfo shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) shortcutInfoCompat, mContext);
                            shortcutInfo.runtimeStatusFlags |= 512;
                            obtain.createShortcutIcon(shortcutInfoCompat, true, null).applyTo(shortcutInfo);
                            ItemInfoWithIcon shortcutInfoBadge = obtain.getShortcutInfoBadge(shortcutInfoCompat, LauncherAppState.getInstance(mContext).getIconCache());
                            try {
                                Action action = new Action(dataParse.actionId, dataParse.shortcutId, 
                                    dataParse.expTimeMils, dataParse.publisherPkg, dataParse.badgePkg, 
                                    shortcutInfoBadge.contentDescription, shortcutInfoCompat, shortcutInfo, dataParse.position);
                                mPredictedActions.add(action);
                            } catch (Throwable th6) {
                            }
                        }
                        StringBuilder shortCutDetailsEmpty = new StringBuilder("shortcut details not found: shortcut=");
                        shortCutDetailsEmpty.append(shortcutInfoCompat);
                        Log.d(TAG, shortCutDetailsEmpty.toString());
                    }
                }
                try {
                    getPredictedActions(mPredictedActions);
                    if (obtain != null) {
                        obtain.close();
                    }
                    return mPredictedActions;
                } catch (Throwable th8) {
                }
            } catch (Throwable th9) {
            }
        } catch (Exception e) {
			Log.d(TAG, "Error loading actions");
        }
		return mPredictedActions;
    }

    /* renamed from: a */
    private ContentValues getActionData(Data data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(data.timestamp));
        contentValues.put("event_type", Integer.valueOf(data.eventType));
        contentValues.put("clicked_type", Integer.valueOf(0));
        contentValues.put("clicked_id", data.clickedId);
        contentValues.put("clicked_position", Integer.valueOf(data.clickedPos));
        contentValues.put("top_suggestions", data.topSuggestions);
        return contentValues;
    }

    private void parseImpressions() {
        try {
            Map all = mFileImpressions.getAll();
            Data data = new Data((byte) 0);
            data.eventType = 2;
            Set<String> keySet = all.keySet();
            ArrayList arrayList = new ArrayList();
            for (String str : keySet) {
                String[] split = ((String) all.get(str)).split(",");
                data.timestamp = Long.parseLong(split[0]);
                data.topSuggestions = str;
                arrayList.add(getActionData(data));
                for (int i = 1; i < split.length; i++) {
                    data.timestamp += Long.parseLong(split[i]);
                    arrayList.add(getActionData(data));
                }
            }
            mContext.getContentResolver().bulkInsert(LOGGING_PROVIDER, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        } catch (Exception e) {
            Log.d(TAG, "Write impression logs");
        } catch (Throwable th) {
            mFileImpressions.edit().clear().apply();
        }
        mFileImpressions.edit().clear().apply();
    }

    public ArrayList<Action> getActions() {
        return mProccessedActions;
    }

    public SharedPreferences getImpressions() {
        return mFileImpressions;
    }

    private void getPredictedActions(ArrayList<Action> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (action.shortcut != null) {
                if (action.shortcutInfo != null) {
                    applyActions(action);
                }
            }
            it.remove();
        }

        Collections.sort(arrayList, new Comparator<Action>() {
            @Override
            public int compare(Action obj1, Action obj2) {
                return Long.compare(((Action) obj1).position, ((Action) obj2).position);
            }
        });
    }

    private void applyActions(Action action) {
        action.isEnabled = false;
        if (action.shortcut.isEnabled()) {
            ApplicationInfo applicationInfo = mLauncherAppsCompat.getApplicationInfo(action.badgePackage, 0, action.shortcut.getUserHandle());
            if (applicationInfo != null && applicationInfo.enabled && !PackageManagerHelper.isAppSuspended(applicationInfo)) {
                action.isEnabled = true;
            }
        }
    }

    public void setListener(UpdateListener updateListener) {
        mListener = updateListener;
    }

    public Logger getLogger() {
        return mLogger;
    }

    private ActionData parseData(List<ActionData> list, ShortcutInfoCompat shortcutInfoCompat) {
        if (shortcutInfoCompat != null) {
            for (ActionData actionData : list) {
                if (actionData.shortcutId.equals(shortcutInfoCompat.getId())) {
                    return actionData;
                }
            }
        }
        return null;
    }

    public void onActionDismissed(Action action) {
        if (action != null) {
            mLogger.logDismiss(action.f42id);
        }
    }
}
