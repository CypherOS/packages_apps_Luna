/*
 * Copyright (C) 2016 The Android Open Source Project
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
package co.aoscp.lovegood.micode.biometrics;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.support.annotation.StringRes;

import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;

public class BiometricsManager {

    private Context mContext;

    /**
     * Title of fingerprint dialog.
     */
    private String mTitle;

    /**
     * Public constructor.
     *
     * @param context {@link Context} of the caller.
     */
    public BiometricsManager(Context context) {
        mContext = context;
    }

    /**
     * Set the title of the dialog. This is the required field.
     *
     * @param title Title string.
     * @return {@link BiometricsManager}
     * @see #setTitle(int)
     */
    public BiometricsManager setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * Set the title of the dialog. This is the required field.
     *
     * @param title String resource of the title.
     * @return {@link BiometricsManager}
     * @see #setTitle(String)
     */
    public BiometricsManager setTitle(@StringRes final int title) {
        mTitle = mContext.getString(title);
        return this;
    }

    /**
     * Build the {@link BiometricsPrompt}. This dialog will be displayed for android version.
     */
    public void show(Context context, AuthenticationCallback callback) {
        if (mTitle == null) {
            throw new IllegalArgumentException("Title of the dialog cannot be null. Call setTitle() " +
                    "to set the title of the dialog.");
        }
        
        Launcher launcher = Launcher.getLauncher(context);

        AbstractFloatingView.closeAllOpenViews(launcher);
        BiometricsPrompt prompt = (BiometricsPrompt) launcher.getLayoutInflater().inflate(
                R.layout.biometrics_prompt, launcher.getDragLayer(), false);
        prompt.setCallback(callback);
        prompt.preCreateAndShow(mTitle);
    }
}