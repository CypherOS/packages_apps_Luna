package com.android.launcher3.backports.p020a;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.backports.C0861l;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.shortcuts.ShortcutKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.apps.nexuslauncher.a.a */
public class C0825a implements Callback {
    /* renamed from: AA */
    private static C0825a f1184AA;
    /* renamed from: AB */
    public final Map f1185AB;
    /* renamed from: AC */
    public C0861l f1186AC;
    private final Context mContext;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper(), this);
    public final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);

    /* renamed from: b */
    public static synchronized C0825a m648b(Context context) {
        C0825a c0825a;
        synchronized (C0825a.class) {
            if (f1184AA == null) {
                f1184AA = new C0825a(context.getApplicationContext());
            }
            c0825a = f1184AA;
        }
        return c0825a;
    }

    private C0825a(Context context) {
        this.mContext = context;
        this.f1185AB = new HashMap();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            List<ShortcutKey> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            List arrayList = new ArrayList();
            for (ShortcutKey shortcutKey : list) {
                ShortcutInfo a = m647a(shortcutKey);
                if (a != null) {
                    arrayList.add(new C0826b(this, shortcutKey, a));
                }
            }
            Message.obtain(this.mUiHandler, 1, arrayList).sendToTarget();
            return true;
        } else if (message.what != 1) {
            return false;
        } else {
            List<C0826b> list2 = (List) message.obj;
            this.f1185AB.clear();
            for (C0826b c0826b : list2) {
                this.f1185AB.put(c0826b.f1187AD, c0826b.f1188AE);
            }
            if (this.f1186AC != null) {
                this.f1186AC.onUpdateUI();
            }
            return true;
        }
    }

    /* renamed from: a */
    private ShortcutInfo m647a(ShortcutKey shortcutKey) {
        LauncherIcons obtain;
        Throwable th;
        Throwable th2;
        List queryForFullDetails = DeepShortcutManager.getInstance(this.mContext).queryForFullDetails(shortcutKey.componentName.getPackageName(), Collections.singletonList(shortcutKey.componentName.getClassName()), shortcutKey.user);
        if (queryForFullDetails.isEmpty()) {
            return null;
        }
        ItemInfoWithIcon shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) queryForFullDetails.get(0), this.mContext);
        try {
            obtain = LauncherIcons.obtain(this.mContext);
            try {
                obtain.createShortcutIcon((ShortcutInfoCompat) queryForFullDetails.get(0), true, null).applyTo(shortcutInfo);
                if (obtain != null) {
                    obtain.close();
                }
                return shortcutInfo;
            } catch (Throwable th22) {
                Throwable th3 = th22;
                th22 = th;
                th = th3;
            }
        } catch (Exception e) {
            return null;
        }
        throw th;
        if (obtain != null) {
            if (th22 != null) {
                try {
                    obtain.close();
                } catch (Throwable th4) {
                    th22.addSuppressed(th4);
                }
            } else {
                obtain.close();
            }
        }
        throw th;
    }
}
