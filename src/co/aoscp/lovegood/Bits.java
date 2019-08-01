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

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;

public class Bits {

    public static boolean hasPackageInstalled(Context context, String pkgName) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(pkgName, 0);
            return ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static IntentFilter getPackageIntentInfo(String pkg, String... action) {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : action) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(pkg, 0);
        return intentFilter;
    }

    public static <T> T notNullOrDefault(T value, T defValue) {
        return value == null ? defValue : value;
    }

    public static boolean hasEnrolledFingerprints(Context context) {
        FingerprintManager fpManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        if(fpManager != null) {
           return fpManager.hasEnrolledFingerprints();
        }
        return false;
    }
}
