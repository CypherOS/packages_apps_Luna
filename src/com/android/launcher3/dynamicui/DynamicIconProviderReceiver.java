package com.android.launcher3.dynamicui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import java.util.List;

final class DynamicIconProviderReceiver extends BroadcastReceiver {
    final DynamicIconProvider mDynamicIconProvider;

    DynamicIconProviderReceiver(DynamicIconProvider dynamicIconProvider) {
        mDynamicIconProvider = dynamicIconProvider;
    }

    public void onReceive(Context context, Intent intent) {
        for (UserHandle userHandle : UserManagerCompat.getInstance(context).getUserProfiles()) {
            LauncherModel model = LauncherAppState.getInstance(context).getModel();
            model.onPackageChanged("com.google.android.calendar", userHandle);
            List queryForPinnedShortcuts = DeepShortcutManager.getInstance(context).queryForPinnedShortcuts("com.google.android.calendar", userHandle);
            if (!queryForPinnedShortcuts.isEmpty()) {
                model.updatePinnedShortcuts("com.google.android.calendar", queryForPinnedShortcuts, userHandle);
            }
        }
    }
}
