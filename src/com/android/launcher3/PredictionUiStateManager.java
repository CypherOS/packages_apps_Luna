package com.android.launcher3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.android.launcher3.AppInfo;
import com.android.launcher3.IconCache;
import com.android.launcher3.IconCache.ItemInfoUpdateReceiver;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherState;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.instant.InstantAppController;
import com.android.launcher3.predictions.PredictionsController;
import com.android.launcher3.reflection.PredictionBuilder;
import com.android.launcher3.reflection.Properties;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ComponentKeyMapper;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.util.ShortcutUserKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictionUiStateManager implements OnSharedPreferenceChangeListener, OnGlobalLayoutListener, ItemInfoUpdateReceiver {

    private static PredictionUiStateManager sInstance;
    private Client mActiveClient = Client.HOME;
    private AllAppsContainerView mAppsView;
    private final Context mContext;
    private PredictionState mCurrentState = parseLastState();
    private final IconCache mIconCache;
    private final InstantAppController mInstantAppsController;
    private final SharedPreferences mMainPrefs;
    private final int mMaxIconsPerRow;
    private PredictionState mPendingState;
    private final SharedPreferences mPrivatePrefs;
    private final PredictionsController mShortcutPredictionsController;

    public interface AppPredictionConsumer {
        void setPredictedApps(boolean enabled, List<ComponentKeyMapper> list);
    }

    public enum Client {
        HOME("GEL"),
        OVERVIEW("OVERVIEW_GEL");

        public final String mClient;
        private final PredictionBuilder mKeyConfg;

        private Client(String client) {
            mClient = client;
            mKeyConfg = PredictionBuilder.getKey(client);
        }
    }

    public static class PredictionState {
        public List<ComponentKeyMapper> apps;
        public boolean isEnabled;
        public List<ComponentKey> orderedApps;
    }

    public void reapplyItemInfo(ItemInfoWithIcon itemInfoWithIcon) {
    }

    public static PredictionUiStateManager getInstance(Context context) {
        Preconditions.assertUIThread();
        if (sInstance == null) {
            sInstance = new PredictionUiStateManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private PredictionUiStateManager(Context context) {
        mContext = context;
        mMainPrefs = Utilities.getPrefs(context);
        mPrivatePrefs = Properties.get(context);
        mMaxIconsPerRow = LauncherAppState.getIDP(context).numColumns;
        mInstantAppsController = InstantAppController.getInstance(context);
        mShortcutPredictionsController = PredictionsController.getInstance(context);
        mIconCache = LauncherAppState.getInstance(context).getIconCache();
        mMainPrefs.registerOnSharedPreferenceChangeListener(this);
        mPrivatePrefs.registerOnSharedPreferenceChangeListener(this);
        mInstantAppsController.mUiCallback = new PredictionUiCallbackDispatcher(this);
        mShortcutPredictionsController.mUiCallback = new PredictionUiCallbackDispatcher(this);
    }

    public Client getClient() {
        return mActiveClient;
    }

    public void switchClient(Client client) {
        if (client != mActiveClient) {
            mActiveClient = client;
            dispatchOnChange(true);
        }
    }

    public void setTargetAppsView(AllAppsContainerView allAppsContainerView) {
        mAppsView = allAppsContainerView;
        if (mPendingState != null) {
            applyState(mPendingState);
            mPendingState = null;
        } else {
            applyState(mCurrentState);
        }
        updateDependencies(mCurrentState);
    }

    public void onGlobalLayout() {
        if (mAppsView != null) {
            if (mPendingState != null && canApplyPredictions(mPendingState)) {
                applyState(mPendingState);
                mPendingState = null;
            }
            if (mPendingState == null) {
                mAppsView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }

    private void scheduleApplyPredictedApps(PredictionState predictionState) {
        boolean notPending = mPendingState == null;
        mPendingState = predictionState;
        if (notPending) {
            mAppsView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (mActiveClient.mKeyConfg.lastPredictions.equals(str) || mActiveClient.mKeyConfg.orderByRank.equals(str)) {
            dispatchOnChange(true);
        }
    }

    private void applyState(PredictionState predictionState) {
        boolean enabled = mCurrentState.isEnabled;
        mCurrentState = predictionState;
        if (mAppsView != null) {
            ((AppPredictionConsumer) mAppsView.getFloatingHeaderView()).setPredictedApps(mCurrentState.isEnabled, mCurrentState.apps);
            if (enabled != mCurrentState.isEnabled) {
                Launcher.getLauncher(mAppsView.getContext()).getStateManager().reapplyState(true);
            }
        }
    }

    private void dispatchOnChange(boolean enabled) {
        PredictionState parseLastState = enabled ? parseLastState() : mPendingState == null ? mCurrentState : mPendingState;
        if (!enabled || mAppsView == null || canApplyPredictions(parseLastState)) {
            applyState(parseLastState);
        } else {
            scheduleApplyPredictedApps(parseLastState);
        }
    }

    private PredictionState parseLastState() {
        PredictionState predictionState = new PredictionState();
        predictionState.isEnabled = true;
        if (predictionState.isEnabled) {
            predictionState.apps = new ArrayList();
            predictionState.orderedApps = new ArrayList();
            String string = mPrivatePrefs.getString(mActiveClient.mKeyConfg.orderByRank, null);
            if (!TextUtils.isEmpty(string)) {
                for (String ranking : string.split(";")) {
                    ComponentKey orderByRank = ShortcutUserKey.parse(ranking, mContext);
                    if (orderByRank != null) {
                        predictionState.orderedApps.add(orderByRank);
                    }
                }
            }
            string = mPrivatePrefs.getString(mActiveClient.mKeyConfg.lastPredictions, null);
            if (!TextUtils.isEmpty(string)) {
                for (String last : string.split(";")) {
                    ComponentKey lastPredictions = ShortcutUserKey.parse(last, mContext);
                    if (lastPredictions != null) {
                        predictionState.apps.add(new ComponentKeyMapper(mContext, lastPredictions));
                    }
                }
            }
            updateDependencies(predictionState);
            return predictionState;
        }
        predictionState.apps = Collections.EMPTY_LIST;
        predictionState.orderedApps = Collections.EMPTY_LIST;
        return predictionState;
    }

    private void updateDependencies(PredictionState predictionState) {
        if (predictionState.isEnabled) {
            if (mAppsView != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int size = predictionState.apps.size();
                int i = 0;
                for (int i2 = 0; i2 < size && i < mMaxIconsPerRow; i2++) {
                    ComponentKeyMapper componentKeyMapper = (ComponentKeyMapper) predictionState.apps.get(i2);
                    if ("@instantapp".equals(componentKeyMapper.getComponentClass())) {
                        arrayList.add(componentKeyMapper.getPackage());
                    } else {
                        AppInfo appInfo = (AppInfo) componentKeyMapper.getApp(mAppsView.getAppsStore());
                        if (appInfo == null) {
                        } else if (appInfo.usingLowResIcon) {
                            mIconCache.updateIconInBackground(this, appInfo);
                        }
                    }
                    i++;
                }
                InstantAppController instantAppController = mInstantAppsController;
                if (!arrayList.isEmpty()) {
                    Message.obtain(instantAppController.mWorkerThread, 1, arrayList).sendToTarget();
                }
                PredictionsController controller = mShortcutPredictionsController;
                if (!arrayList2.isEmpty()) {
                    Message.obtain(controller.mWorkerThread, 0, arrayList2).sendToTarget();
                }
            }
        }
    }

    public void dispatchOnChange() {
        dispatchOnChange(false);
    }

    public boolean arePredictionsEnabled() {
        return mCurrentState.isEnabled;
    }

    private boolean canApplyPredictions(PredictionState predictionState) {
        if (mAppsView == null) {
            return true;
        }
        Launcher launcher = Launcher.getLauncher(mAppsView.getContext());
        if (mAppsView.isShown()) {
            if (!launcher.isForceInvisible()) {
                if (mCurrentState.isEnabled == predictionState.isEnabled) {
                    if (mCurrentState.apps.isEmpty() == predictionState.apps.isEmpty()) {
                        if (!launcher.getDeviceProfile().isVerticalBarLayout() && launcher.isInState(LauncherState.OVERVIEW) && launcher.getAllAppsController().getProgress() > 1.0f) {
                            return true;
                        }
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public PredictionState getCurrentState() {
        return mCurrentState;
    }
}
