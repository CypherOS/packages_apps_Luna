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

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;

import com.android.launcher3.AppInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherCallbacks;
import com.android.launcher3.uioverrides.WallpaperColorInfo;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LunaLauncher extends Launcher implements WallpaperColorInfo.OnChangeListener {

	/** The system setting for Color Manager **/
    private static final String COLOR_MANAGER_THEME = "system_theme";

	/** Whether to force dark theme if Configuration.UI_MODE_NIGHT_YES. */
    private static final boolean DARK_THEME_IN_NIGHT_MODE = true;

    public LunaLauncher() {
        setLauncherCallbacks(new LunaLauncherCallbacks(this));
    }
	
	public void applyTheme() {
		WallpaperColorInfo wallpaperColorInfo = WallpaperColorInfo.getInstance(this);
		wallpaperColorInfo.addOnChangeListener(this);
		int themeRes = getThemeRes(wallpaperColorInfo);
		if (themeRes != mThemeRes) {
			mThemeRes = themeRes;
		}
		updateTheme(wallpaperColorInfo);
	}
	
	@Override
    public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
        if (mThemeRes != getThemeRes(wallpaperColorInfo)) {
            recreate();
        }
    }
	
	protected void updateTheme(WallpaperColorInfo wallpaperColorInfo) {
		final Configuration config = this.getResources().getConfiguration();
        ContentResolver resolver = this.getContentResolver();
        final boolean supportsDarkText = wallpaperColorInfo.supportsDarkText();
        final int systemTheme = Settings.Secure.getInt(resolver, COLOR_MANAGER_THEME, 0);
		final boolean nightModeWantsDarkTheme = DARK_THEME_IN_NIGHT_MODE
                && (config.uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        switch (systemTheme) {
            case 1:
                setTheme(supportsDarkText ? R.style.LauncherTheme_DarkText : R.style.LauncherTheme);
                break;
            case 2:
                setTheme(supportsDarkText ? R.style.LauncherThemeDark_DarkText : R.style.LauncherThemeDark);
                break;
            case 3:
                setTheme(supportsDarkText ? R.style.LauncherThemeBlack_DarkText : R.style.LauncherThemeBlack);
                break;
            default:
                setTheme(nightModeWantsDarkTheme ? R.style.LauncherTheme_Dark : mThemeRes);
                break;
        }
    }

    public class LunaLauncherCallbacks implements LauncherCallbacks, OnSharedPreferenceChangeListener {

        private final LunaLauncher mLauncher;

        public LunaLauncherCallbacks(LunaLauncher launcher) {
            mLauncher = launcher;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
			// Update theme
			mLauncher.applyTheme();
		}

        @Override
        public void onResume() { }

        @Override
        public void onStart() { }

        @Override
        public void onStop() { }

        @Override
        public void onPause() { }

        @Override
        public void onDestroy() { }

        @Override
        public void onSaveInstanceState(Bundle outState) { }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) { }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) { }

        @Override
        public void onAttachedToWindow() { }

        @Override
        public void onDetachedFromWindow() { }

        @Override
        public void dump(String prefix, FileDescriptor fd, PrintWriter w, String[] args) { }

        @Override
        public void onHomeIntent(boolean internalStateHandled) { }

        @Override
        public boolean handleBackPressed() {
            return false;
        }
      
        @Override
        public void onTrimMemory(int level) { }

        @Override
        public void onLauncherProviderChange() { }

        @Override
        public void bindAllApplications(ArrayList<AppInfo> apps) { }

        @Override
        public boolean startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData) {
            return false;
        }

        @Override
        public boolean hasSettings() {
            return false;
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { }
    }
}
