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
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.support.annotation.StringRes;

import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;

public class BiometricsManager {

    private BiometricsPrompt mPrompt;
    private Context mContext;
	private CryptoRegistrar mRegistrar;

    private String mTitle;
    private Drawable mIcon;

    public BiometricsManager(Context context) {
        mContext = context;
		mRegistrar = new CryptoRegistrar(context);
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

    public BiometricsManager setIcon(Drawable icon) {
        mIcon = icon;
        return this;
    }

    public void show(AuthenticationCallback callback) {
        Launcher launcher = Launcher.getLauncher(mContext);

        AbstractFloatingView.closeAllOpenViews(launcher);
        mPrompt = (BiometricsPrompt) launcher.getLayoutInflater().inflate(
                R.layout.biometrics_prompt, launcher.getDragLayer(), false);
        mPrompt.preCreateAndShow(mTitle, mIcon);
		beginAuth(callback);
    }

	public void updatePrompt(int msg) {
		if (mPrompt != null) {
			mPrompt.updateStatus(msg);
		}
	}

	public void closePrompt() {
		if (mPrompt != null) {
			mPrompt.handleClose();
		}
	}

    public void beginAuth(AuthenticationCallback callback) {
        mRegistrar.setCallback(callback);
		mRegistrar.beginAuth();
    }
}