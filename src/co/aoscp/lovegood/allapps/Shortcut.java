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

public class Shortcut {

    public boolean isEnabled = false;
    public final String publisherPackage;
    public final ShortcutInfoCompat shortcut;
    public final String shortcutId;
    public final ShortcutInfo shortcutInfo;

    public Shortcut(String publisherPackage, String shortcutId, ShortcutInfoCompat infoCompat, ShortcutInfo shortcutInfo) {
        this.shortcutId = shortcutId;
        this.publisherPackage = publisherPackage;
        this.shortcut = infoCompat;
        this.shortcutInfo = shortcutInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(this.shortcut.getShortLabel());
        return sb.toString();
    }
}
