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

package co.aoscp.lovegood.allapps;

import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;

public class Action {

    public final String badgePackage;
    public final CharSequence contentDescription;
    public final long expirationTimeMillis;
    public final String actionId;
    public boolean isEnabled = false;
    public final CharSequence openingPackageDescription;
    public final long position;
    public final String publisherPackage;
    public final ShortcutInfoCompat shortcut;
    public final String shortcutId;
    public final ShortcutInfo shortcutInfo;

    public Action(String actionId, String shortcutId, long expirationTimeMillis, String publisherPackage, 
                String badgePackage, CharSequence openingPackageDescription, ShortcutInfoCompat shortcutInfoCompat, 
                ShortcutInfo shortcutInfo, long position) {
        this.actionId = actionId;
        this.shortcutId = shortcutId;
        this.expirationTimeMillis = expirationTimeMillis;
        this.publisherPackage = publisherPackage;
        this.badgePackage = badgePackage;
        this.openingPackageDescription = openingPackageDescription;
        this.shortcut = shortcutInfoCompat;
        this.shortcutInfo = shortcutInfo;
        this.position = position;
        this.contentDescription = shortcutInfo.contentDescription;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(this.actionId);
        sb.append(",");
        sb.append(this.shortcut.getShortLabel());
        sb.append(",");
        sb.append(this.position);
        sb.append("}");
        return sb.toString();
    }
}
