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

	public static final int MSG_WRITE_IMPRESSIONS = 0;
	public static final int MSG_UPDATE_ACTIONS = 1;
	public static final int MSG_ENABLE_ACTION_SUGGEST = 2;
	public static final int MSG_PARSE_IMPRESSIONS = 3;
	public static final int MSG_NOTIFY_OBSERVER = 4;
	public static final int MSG_ACTIONS_PACKAGE_CHANGED = 5;

    private static final Uri SUGGEST_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsuggestprovider").build();
    private static final Uri LOGGING_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionloggingprovider").build();
    private static final Uri SETTING_PROVIDER = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsettingprovider").build();
    private static final String[] ACTION_DATA = new String[]{"action_id", "shortcut_id", "expiration_time_millis", "publisher_package", "badge_package", "position"};

    private static ActionsController sController;
    private final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Handler mUiThread = new Handler(Looper.getMainLooper(), this);
    private Context mContext;
    private SharedPreferences mActionsPref;
    private SharedPreferences mFileImpressions;
    private final ArrayList<Action> mPredictedActions = new ArrayList();
    private final ArrayList<Action> mProccessedActions = new ArrayList();

    private final ContentObserver UI_OBSERVER = new ContentObserver(mUiThread) {
		@Override
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

		@Override
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
            SharedPreferences impressions = mController.getImpressions();
            long currentTimeMillis = System.currentTimeMillis();
            int min = Math.min(mController.getActions().size(), 2);
            for (int i = 0; i < min; i++) {
                String actions = ((Action) mController.getActions().get(i)).actionId;
                if (actions != null) {
                    if (impressions.contains(actions)) {
                        String string = impressions.getString(actions, "");
                        String[] split = string.split(",");
                        long parseLong = Long.parseLong(split[0]);
                        for (int i2 = 1; i2 < split.length; i2++) {
                            parseLong += Long.parseLong(split[i2]);
                        }
                        long timestamp = currentTimeMillis - parseLong;
                        Editor edit = impressions.edit();
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append(",");
                        sb.append(timestamp);
                        edit.putString(actions, sb.toString()).apply();
                    } else {
                        impressions.edit().putString(actions, String.valueOf(currentTimeMillis)).apply();
                    }
                }
            }
        }

        public void logClick(String id, int pos) {
            Data data = new Data((byte) 0);
            data.timestamp = System.currentTimeMillis();
            data.eventType = CLICK_EVENT_TYPE;
            if (id == null) {
                id = "";
            }
            data.clickedId = id;
            data.clickedPos = pos + 1;
            Message.obtain(mController.mWorkerThread, MSG_WRITE_IMPRESSIONS, 0, 0, data).sendToTarget();
        }

        public void logDismiss(String id) {
            Data data = new Data((byte) 0);
            data.timestamp = System.currentTimeMillis();
            data.eventType = DISMISS_EVENT_TYPE;
            if (id == null) {
                id = "";
            }
            data.clickedId = id;
            Message.obtain(mController.mWorkerThread, MSG_WRITE_IMPRESSIONS, 0, 0, data).sendToTarget();
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

        @Override
        public void onShortcutsChanged(String str, List<ShortcutInfoCompat> list, UserHandle userHandle) {
        }

        private PackageChangeCallback() {
        }

        PackageChangeCallback(ActionsController controller, byte b) {
            this();
            mController = controller;
        }

        @Override
        public void onPackageRemoved(String str, UserHandle userHandle) {
            onPackageChanged(str, userHandle);
        }

        @Override
        public void onPackageAdded(String str, UserHandle userHandle) {
            onPackageChanged(str, userHandle);
        }

        @Override
        public void onPackageChanged(String str, UserHandle userHandle) {
            Message.obtain(mController.mWorkerThread, MSG_ACTIONS_PACKAGE_CHANGED, 0, 0, new PackageInfo(str, userHandle)).sendToTarget();
        }

        @Override
        public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        @Override
        public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        @Override
        public void onPackagesSuspended(String[] strArr, UserHandle userHandle) {
            for (String onPackageChanged : strArr) {
                onPackageChanged(onPackageChanged, userHandle);
            }
        }

        @Override
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
        mActionsPref = Utilities.getPrefs(context);
        mFileImpressions = context.getSharedPreferences("pref_file_impressions", 0);
        mActionsPref.registerOnSharedPreferenceChangeListener(this);
        updateActionProviderState(isEnabled());
        getContentProvider();
        mLauncherAppsCompat = LauncherAppsCompat.getInstance(context);
        mLauncherAppsCompat.addOnAppsChangedCallback(mCallback);
        context.registerReceiver(new ActionServicesReceiver(), Bits.getPackageIntentInfo(AIAI_PACKAGE, "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_DATA_CLEARED", "android.intent.action.PACKAGE_RESTARTED"));
    }

    private boolean isEnabled() {
        return mActionsPref.getBoolean(SettingsFragment.KEY_APP_ACTIONS, true);
    }

    private void updateActionProviderState(boolean isEnabled) {
        Log.d(TAG, "Updating action suggestion state");
        Message.obtain(mWorkerThread, MSG_ENABLE_ACTION_SUGGEST, Boolean.valueOf(isEnabled)).sendToTarget();
    }

	@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SettingsFragment.KEY_APP_ACTIONS.equals(str)) {
			boolean isEnabled = mActionsPref.getBoolean(SettingsFragment.KEY_APP_ACTIONS, true);
			if (isEnabled() != isEnabled) {
				isEnabled = isEnabled();
                updateActionProviderState(isEnabled);
                updateUi();
			}
        }
    }

    private void getContentProvider() {
        ContentResolver resolver = mContext.getContentResolver();
        resolver.unregisterContentObserver(UI_OBSERVER);
        try {
            resolver.registerContentObserver(SUGGEST_PROVIDER, true, UI_OBSERVER);
            updateUi();
            Message.obtain(mWorkerThread, MSG_PARSE_IMPRESSIONS).sendToTarget();
        } catch (SecurityException e) {
            Log.d(TAG, "Content provider not found");
        }
    }

    private static ArrayList<Action> addAndApply(ArrayList<Action> actions) {
        ArrayList<Action> list = new ArrayList();
        Iterator it = actions.iterator();
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (action.isEnabled) {
                list.add(action);
            }
        }
        return list;
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
            Message.obtain(mUiThread, MSG_NOTIFY_OBSERVER, 0, 0, addAndApply((ArrayList) mPredictedActions)).sendToTarget();
        }
    }

    private void updateUi() {
        Message.obtain(mWorkerThread, MSG_UPDATE_ACTIONS).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_WRITE_IMPRESSIONS:
                try {
                    mContext.getContentResolver().insert(LOGGING_PROVIDER, getImpressionData((Data) message.obj));
                    break;
                } catch (Exception e) {
                    Log.d(TAG, "Failed to write to logging provider");
                    break;
                }
            case MSG_UPDATE_ACTIONS:
                Message.obtain(mUiThread, MSG_NOTIFY_OBSERVER, 0, 0, addAndApply(loadPredictedActions())).sendToTarget();
                break;
            case MSG_ENABLE_ACTION_SUGGEST:
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
            case MSG_PARSE_IMPRESSIONS:
                parseImpressions();
                break;
            case MSG_NOTIFY_OBSERVER:
                mProccessedActions.clear();
                mProccessedActions.addAll((ArrayList) message.obj);
                if (mListener != null) {
                    mListener.onUpdated(mProccessedActions);
                    break;
                }
                break;
            case MSG_ACTIONS_PACKAGE_CHANGED:
                updateActionsOnPackageChange((PackageInfo) message.obj);
                break;
        }
        return true;
    }

    private ArrayList<Action> loadPredictedActions() {
		ActionData data;
        mPredictedActions.clear();
        if (!isEnabled()) {
            return mPredictedActions;
        }
        try {
            LauncherIcons obtain = LauncherIcons.obtain(mContext);
            try (Cursor query = mContext.getContentResolver().query(SUGGEST_PROVIDER, ACTION_DATA, null, null, null)) {
                if (query == null) {
                    try {
                        Log.d(TAG, "No cursor found");
                        if (obtain != null) {
                            obtain.close();
                        }
                        return mPredictedActions;
                    } catch (Throwable th4) {
                    }
                }
                MultiHashMap multiHashMap = new MultiHashMap();
                while (query.moveToNext()) {
                    data = new ActionData();
                    data.actionId = query.getString(0);
                    data.shortcutId = query.getString(1);
                    data.publisherPkg = query.getString(3);
                    data.badgePkg = query.getString(4);
                    data.expTimeMils = query.getLong(2);
                    data.position = query.getLong(5);
                    if (data.expTimeMils <= 0 || data.expTimeMils >= System.currentTimeMillis()) {
                        multiHashMap.addToList(data.publisherPkg, data);
                    } else {
                        StringBuilder sbExp = new StringBuilder("shortcut expired id=");
                        sbExp.append(data.shortcutId);
                        sbExp.append(" ts=");
                        sbExp.append(data.expTimeMils);
                        Log.d(TAG, sbExp.toString());
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
                        data = parseData(list, shortcutInfoCompat);
                        if (data != null) {
                            if (TextUtils.isEmpty(shortcutInfoCompat.getShortLabel())) {
                                StringBuilder sbEmptyInfo = new StringBuilder("Empty shortcut label: shortcut=");
                                sbEmptyInfo.append(shortcutInfoCompat);
                                Log.d(TAG, sbEmptyInfo.toString());
                            }
                            ShortcutInfo shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) shortcutInfoCompat, mContext);
                            shortcutInfo.runtimeStatusFlags |= 512;
                            obtain.createShortcutIcon(shortcutInfoCompat, true, null).applyTo(shortcutInfo);
                            ItemInfoWithIcon shortcutInfoBadge = obtain.getShortcutInfoBadge(shortcutInfoCompat, LauncherAppState.getInstance(mContext).getIconCache());
                            try {
                                Action action = new Action(data.actionId, data.shortcutId, 
                                    data.expTimeMils, data.publisherPkg, data.badgePkg, 
                                    shortcutInfoBadge.contentDescription, shortcutInfoCompat, shortcutInfo, data.position);
                                mPredictedActions.add(action);
                            } catch (Throwable th6) {
                            }
                        }
                        StringBuilder sbEmptyDetails = new StringBuilder("shortcut details not found: shortcut=");
                        sbEmptyDetails.append(shortcutInfoCompat);
                        Log.d(TAG, sbEmptyDetails.toString());
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

    private ContentValues getImpressionData(Data data) {
        ContentValues values = new ContentValues();
        values.put("timestamp", Long.valueOf(data.timestamp));
        values.put("event_type", Integer.valueOf(data.eventType));
        values.put("clicked_type", Integer.valueOf(0));
        values.put("clicked_id", data.clickedId);
        values.put("clicked_position", Integer.valueOf(data.clickedPos));
        values.put("top_suggestions", data.topSuggestions);
        return values;
    }

    private void parseImpressions() {
        try {
            Map all = mFileImpressions.getAll();
            Data data = new Data((byte) 0);
            data.eventType = Logger.IMPRESSION_EVENT_TYPE;
            Set<String> keySet = all.keySet();
            ArrayList impressions = new ArrayList();
            for (String topSuggestions : keySet) {
                String[] split = ((String) all.get(topSuggestions)).split(",");
                data.timestamp = Long.parseLong(split[0]);
                data.topSuggestions = topSuggestions;
                impressions.add(getImpressionData(data));
                for (int i = 1; i < split.length; i++) {
                    data.timestamp += Long.parseLong(split[i]);
                    impressions.add(getImpressionData(data));
                }
            }
            mContext.getContentResolver().bulkInsert(LOGGING_PROVIDER, (ContentValues[]) impressions.toArray(new ContentValues[impressions.size()]));
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
            ApplicationInfo info = mLauncherAppsCompat.getApplicationInfo(action.badgePackage, 0, action.shortcut.getUserHandle());
            if (info != null && info.enabled && !PackageManagerHelper.isAppSuspended(info)) {
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

    private ActionData parseData(List<ActionData> actionData, ShortcutInfoCompat shortcutInfoCompat) {
        if (shortcutInfoCompat != null) {
            for (ActionData data : actionData) {
                if (data.shortcutId.equals(shortcutInfoCompat.getId())) {
                    return data;
                }
            }
        }
        return null;
    }

    public void onActionDismissed(Action action) {
        if (action != null) {
            mLogger.logDismiss(action.actionId);
        }
    }
}
