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

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.preference.TwoStatePreference;
import android.provider.Settings;

import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.allapps.ActionsController;

import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.R;
import com.android.launcher3.SettingsActivity;
import com.android.launcher3.SettingsActivity.LauncherSettingsFragment;

import java.util.Objects;

public class SettingsFragment extends SettingsActivity implements OnPreferenceStartFragmentCallback {

    public static final String KEY_MINUS_ONE = "pref_enable_minus_one";

    public static final String KEY_SUGGESTIONS = "pref_suggestions";
    public static final String KEY_APP_SUGGESTIONS = "pref_app_suggestions";
    public static final String KEY_APP_ACTIONS = "pref_show_suggested_actions";
	public static final String KEY_SHORTCUT_SUGGESTIONS = "pref_show_suggested_shortcuts";

    @Override
    protected PreferenceFragment getNewFragment() {
        return new SettingsPreferenceFragment();
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment preferenceFragment, Preference preference) {
        Fragment instantiate = Fragment.instantiate(this, preference.getFragment(), preference.getExtras());
        if (instantiate instanceof DialogFragment) {
            ((DialogFragment) instantiate).show(getFragmentManager(), preference.getKey());
        } else {
            getFragmentManager().beginTransaction().replace(android.R.id.content, instantiate).addToBackStack(preference.getKey()).commit();
        }
        return true;
    }

    /**
     * This fragment shows the luna launcher preferences.
     */
    public static class SettingsPreferenceFragment extends LauncherSettingsFragment implements OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            SwitchPreference minusOne = (SwitchPreference) findPreference(KEY_MINUS_ONE);
            if (!Bits.hasPackageInstalled(getActivity(),
                        LunaLauncherCallbacks.SEARCH_PACKAGE)) {
                getPreferenceScreen().removePreference(minusOne);
            }

            PreferenceScreen prefScreen = (PreferenceScreen) findPreference(KEY_SUGGESTIONS);
            ((SwitchPreference) findPreference(KEY_APP_SUGGESTIONS)).setOnPreferenceChangeListener(this);
            try {
                getContext().getPackageManager().getPackageInfo(ActionsController.AIAI_PACKAGE, AbstractFloatingView.TYPE_TASK_MENU);
            } catch (NameNotFoundException ex) {
                prefScreen.removePreference(findPreference(KEY_APP_ACTIONS));
            }
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (!KEY_APP_SUGGESTIONS.equals(preference.getKey())) {
                return false;
            }
            if (((Boolean) newValue).booleanValue()) {
                return true;
            }
            SettingsFragment.SuggestionConfirmationFragment suggestionConfirmationFragment = new SettingsFragment.SuggestionConfirmationFragment();
            suggestionConfirmationFragment.setTargetFragment(this, 0);
            suggestionConfirmationFragment.show(getFragmentManager(), preference.getKey());
            return false;
        }
    }

    public static class SuggestionConfirmationFragment extends DialogFragment implements OnClickListener {
        public Dialog onCreateDialog(Bundle bundle) {
            return new Builder(getContext())
                .setTitle(R.string.title_disable_suggestions_prompt)
                .setMessage(R.string.msg_disable_suggestions_prompt)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(R.string.label_turn_off_suggestions, this)
                .create();
        }

        public void onClick(DialogInterface dialogInterface, int res) {
            if (getTargetFragment() instanceof PreferenceFragment) {
                Preference findPreference = ((PreferenceFragment) getTargetFragment()).findPreference(KEY_APP_SUGGESTIONS);
                if (findPreference instanceof TwoStatePreference) {
                    ((TwoStatePreference) findPreference).setChecked(false);
                }
            }
        }
    }
}
