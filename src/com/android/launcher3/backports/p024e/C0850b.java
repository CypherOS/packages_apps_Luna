package com.android.launcher3.backports.p024e;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.os.UserHandle;

import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ComponentKey;

/* renamed from: com.google.android.apps.nexuslauncher.e.b */
public class C0850b {
    /* renamed from: a */
    public static ComponentKey m686a(Uri uri, Context context) {
        if (uri == null || uri.getQueryParameter("component") == null) {
            return null;
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(uri.getQueryParameter("component"));
        UserHandle userForSerialNumber = UserManagerCompat.getInstance(context).getUserForSerialNumber(Long.parseLong(uri.getQueryParameter("user")));
        if (userForSerialNumber == null) {
            return null;
        }
        return new ComponentKey(unflattenFromString, userForSerialNumber);
    }

    /* renamed from: a */
    public static ComponentKey m687a(String str, Context context) {
        int i;
        ComponentName unflattenFromString;
        UserHandle userForSerialNumber;
        if (str.startsWith("_")) {
            str = str.substring(1);
            i = 1;
        } else {
            i = 0;
        }
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            Long valueOf = Long.valueOf(str.substring(indexOf + 1));
            unflattenFromString = ComponentName.unflattenFromString(substring);
            userForSerialNumber = UserManagerCompat.getInstance(context).getUserForSerialNumber(valueOf.longValue());
        } else {
            unflattenFromString = ComponentName.unflattenFromString(str);
            userForSerialNumber = Process.myUserHandle();
        }
        if (unflattenFromString == null || userForSerialNumber == null) {
            return null;
        }
        return i != 0 ? new ShortcutKey(unflattenFromString, userForSerialNumber) : new ComponentKey(unflattenFromString, userForSerialNumber);
    }
}
