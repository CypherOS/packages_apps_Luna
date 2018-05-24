package com.google.android.apps.nexuslauncher;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.provider.Settings;

import com.android.launcher3.AppInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.ComponentKeyMapper;
import com.google.android.libraries.gsa.launcherclient.LauncherClient;

import java.util.List;

public class NexusLauncherActivity extends Launcher {
    private NexusLauncher mLauncher;

    public NexusLauncherActivity() {
        mLauncher = new NexusLauncher(this);
    }

    protected void overrideTheme(boolean isDark, boolean supportsDarkText) {
        ContentResolver resolver = this.getContentResolver();
        int flags = Utilities.getDevicePrefs(this).getInt(NexusLauncherOverlay.PREF_PERSIST_FLAGS, 0);
        int orientFlag = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 16 : 8;
        boolean useGoogleInOrientation = (orientFlag & flags) != 0;
        int userThemeSetting = Settings.Secure.getIntForUser(resolver, Settings.Secure.DEVICE_THEME, 0, mCurrentUserId);
        if (userThemeSetting == 2 || (useGoogleInOrientation && isDark && userThemeSetting == 0)) { // Respect ColorOM settings, only apply if set to automatic or set to "Dark: Setting 2"
            setTheme(R.style.GoogleSearchLauncherThemeDark);
        } else if (userThemeSetting == 3 || (useGoogleInOrientation && userThemeSetting == 3)) { // Apply black theme if set to "Black: Setting 3"
            setTheme(R.style.GoogleSearchLauncherThemeBlack);
        } else if (useGoogleInOrientation && supportsDarkText && Utilities.ATLEAST_NOUGAT) {
            setTheme(R.style.GoogleSearchLauncherThemeDarkText);
        } else if (useGoogleInOrientation) {
            setTheme(R.style.GoogleSearchLauncherTheme);
        } else {
            super.overrideTheme(isDark, supportsDarkText);
        }
		mAllAppsController.updateAllAppsAlpha();
    }

    public List<ComponentKeyMapper<AppInfo>> getPredictedApps() {
        return mLauncher.mCallbacks.getPredictedApps();
    }

    public LauncherClient getGoogleNow() {
        return mLauncher.mClient;
    }
}
