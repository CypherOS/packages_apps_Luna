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
    /* renamed from: id */
    public final String f42id;
    public boolean isEnabled = false;
    public final CharSequence openingPackageDescription;
    public final long position;
    public final String publisherPackage;
    public final ShortcutInfoCompat shortcut;
    public final String shortcutId;
    public final ShortcutInfo shortcutInfo;

    public Action(String str, String str2, long j, String str3, String str4, CharSequence charSequence, ShortcutInfoCompat shortcutInfoCompat, ShortcutInfo shortcutInfo, long j2) {
        this.f42id = str;
        this.shortcutId = str2;
        this.expirationTimeMillis = j;
        this.publisherPackage = str3;
        this.badgePackage = str4;
        this.openingPackageDescription = charSequence;
        this.shortcut = shortcutInfoCompat;
        this.shortcutInfo = shortcutInfo;
        this.position = j2;
        this.contentDescription = shortcutInfo.contentDescription;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(this.f42id);
        sb.append(",");
        sb.append(this.shortcut.getShortLabel());
        sb.append(",");
        sb.append(this.position);
        sb.append("}");
        return sb.toString();
    }
}
