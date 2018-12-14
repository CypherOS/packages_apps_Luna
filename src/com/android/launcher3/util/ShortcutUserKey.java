package com.android.launcher3.util;

import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.os.UserHandle;

import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.shortcuts.ShortcutKey;

public class ShortcutUserKey {

    public static ComponentKey parse(String str, Context context) {
        boolean z;
        ComponentName unflattenFromString;
        UserHandle userForSerialNumber;
        if (str.startsWith("_")) {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
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
        if (unflattenFromString != null) {
            if (userForSerialNumber != null) {
                return z ? new ShortcutKey(unflattenFromString, userForSerialNumber) : new ComponentKey(unflattenFromString, userForSerialNumber);
            }
        }
        return null;
    }
}
