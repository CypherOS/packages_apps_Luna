package com.android.launcher3.compat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.android.launcher3.IconCache;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.compat.UserHandleCompat;

public abstract class ShortcutConfigActivityInfo {
    private static final String TAG = "SCActivityInfo";
    private final ComponentName mCn;
    private final UserHandleCompat mUser;
	
	public ShortcutConfigActivityInfo(LauncherActivityInfo launcherActivityInfo) {
            super(launcherActivityInfo.getComponentName(), launcherActivityInfo.getUser());
            this.mInfo = launcherActivityInfo;
    }

    class ShortcutConfigActivityInfoVL extends ShortcutConfigActivityInfo {
        private final ActivityInfo mInfo;
        private final PackageManager mPm;

        public ShortcutConfigActivityInfoVL(ActivityInfo activityInfo, PackageManager packageManager) {
            super(new ComponentName(activityInfo.packageName, activityInfo.name), UserHandleCompat.myUserHandle());
            this.mInfo = activityInfo;
            this.mPm = packageManager;
        }

        @Override
        public CharSequence getLabel() {
            return this.mInfo.loadLabel(this.mPm);
        }

        @Override
        public Drawable getFullResIcon(IconCache iconCache) {
            return iconCache.getFullResIcon(this.mInfo);
        }
    }

    class ShortcutConfigActivityInfoVO extends ShortcutConfigActivityInfo {
        private final LauncherActivityInfo mInfo;

        public ShortcutConfigActivityInfoVO(LauncherActivityInfo launcherActivityInfo) {
            super(launcherActivityInfo.getComponentName(), launcherActivityInfo.getUser());
            this.mInfo = launcherActivityInfo;
        }

        @Override
        public CharSequence getLabel() {
            return this.mInfo.getLabel();
        }

        @Override
        public Drawable getFullResIcon(IconCache iconCache) {
            return iconCache.getFullResIcon(this.mInfo);
        }

        @Override
        public boolean startConfigActivity(Activity activity, int i) {
            if (getUser().equals(Process.myUserHandle())) {
                return super.startConfigActivity(activity, i);
            }
            try {
                Activity activity2 = activity;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.startIntentSenderForResult((IntentSender) LauncherApps.class.getDeclaredMethod("getShortcutConfigActivityIntent", new Class[]{LauncherActivityInfo.class}).invoke(activity.getSystemService(LauncherApps.class), new Object[]{this.mInfo}), i, null, 0, 0, 0);
                }
                return true;
            } catch (Throwable e) {
                Log.e(ShortcutConfigActivityInfo.TAG, "Error calling new API", e);
                return false;
            }
        }
    }

    public abstract Drawable getFullResIcon(IconCache iconCache);

    public abstract CharSequence getLabel();

    protected ShortcutConfigActivityInfo(ComponentName componentName, UserHandle userHandle) {
		UserHandleCompat userHandle2 = UserHandleCompat.myUserHandle();
        this.mCn = componentName;
        this.mUser = userHandle2;
    }

    public ComponentName getComponent() {
        return this.mCn;
    }

    public UserHandleCompat getUser() {
        return this.mUser;
    }

    public int getItemType() {
        return 1;
    }

    public ShortcutInfo createShortcutInfo() {
        return null;
    }

    public boolean startConfigActivity(Activity activity, int i) {
        Intent component = new Intent("android.intent.action.CREATE_SHORTCUT").setComponent(getComponent());
        try {
            activity.startActivityForResult(component, i);
            return true;
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
            return false;
        } catch (Throwable e2) {
            Toast.makeText(activity, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Launcher does not have the permission to launch " + component + ". Make sure to create a MAIN intent-filter for the corresponding activity " + "or use the exported attribute for this activity.", e2);
            return false;
        }
    }

    public boolean isPersistable() {
        return true;
    }
}