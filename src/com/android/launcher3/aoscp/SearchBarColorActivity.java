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

package com.android.launcher3.aoscp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.android.launcher3.R;
import com.android.launcher3.aoscp.widget.RadioButtonPreference;

import com.google.android.apps.nexuslauncher.qsb.HotseatQsbWidget;

import java.util.ArrayList;
import java.util.List;

public class SearchBarColorActivity extends Activity {

    private static final String TAG = "SearchBarColorActivity";

    private static final String KEY_COLOR_DEFAULT   = "color_default";
    private static final String KEY_COLOR_THEME     = "color_theme";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbar_color_settings);

        FragmentManager fragManager = getFragmentManager();
        Fragment fragment = fragManager.findFragmentById(R.id.searchbar_color_settings);
        if (fragment == null) {
            fragManager.beginTransaction()
                    .add(R.id.searchbar_color_settings, new SearchBarColorFragment())
                    .commit();
        }
    }
	
	public static class SearchBarColorFragment extends LauncherPreferenceFragment 
	        implements RadioButtonPreference.OnClickListener {
	
	    private HotseatQsbWidget mQsb;

        List<RadioButtonPreference> mPreferences = new ArrayList<>();

	    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.launcher_preferences_searchbar_color);
		    final PreferenceScreen screen = getPreferenceScreen();
		    mQsb = new HotseatQsbWidget(getContext());

		    for (int i = 0; i < screen.getPreferenceCount(); i++) {
                Preference pref = screen.getPreference(i);
                if (pref instanceof RadioButtonPreference) {
                    RadioButtonPreference colorPref = (RadioButtonPreference) pref;
                    colorPref.setOnClickListener(this);
                    mPreferences.add(colorPref);
                }
            }

		    switch (mQsb.getQsbColor()) {
                case 0:
                    updateColorPreference(KEY_COLOR_DEFAULT);
                    break;
                case 1:
                    updateColorPreference(KEY_COLOR_THEME);
                    break;
            }
	    }
	
        private void updateColorPreference(String selectionKey) {
            for (RadioButtonPreference pref : mPreferences) {
                if (selectionKey.equals(pref.getKey())) {
                    pref.setChecked(true);
                } else {
                    pref.setChecked(false);
                }
            }
        }

        @Override
        public void onRadioButtonClicked(RadioButtonPreference pref) {
            switch (pref.getKey()) {
                case KEY_COLOR_DEFAULT:
                    mQsb.setQsbColor(0);
                    break;
                case KEY_COLOR_THEME:
                    mQsb.setQsbColor(1);
                    break;
            }
            updateColorPreference(pref.getKey());
        }
	}
}