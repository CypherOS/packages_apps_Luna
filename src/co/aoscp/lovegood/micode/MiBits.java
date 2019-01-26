/*
 * Copyright (C) 2019 CypherOS
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

package co.aoscp.lovegood.micode;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class MiBits {

    public static boolean hasBiometricsSupport(Context context) {
        KeyguardManager keyguardMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        PackageManager pm = context.getPackageManager();
		if (!"Poundcake".equals(Build.LUNA.VERSION_CODE)) {
            return false;
        }

		if (!keyguardMgr.isKeyguardSecure()) {
            return false;
        }

		if (!pm.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return false;
        }
		return true;
	}
}