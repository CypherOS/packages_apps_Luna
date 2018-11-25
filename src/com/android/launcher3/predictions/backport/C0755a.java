package com.android.launcher3.predictions.backport;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.predictions.backport.C0799i;
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
public class C0755a implements Callback {
    /* renamed from: xm */
    private static C0755a f970xm;
    private final Context mContext;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper(), this);
    public final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);
    /* renamed from: xn */
    public final Map f971xn;
    /* renamed from: xo */
    public C0799i f972xo;

    /* renamed from: a */
    public static synchronized C0755a m2422a(Context context) {
        C0755a c0755a;
        synchronized (C0755a.class) {
            if (f970xm == null) {
                f970xm = new C0755a(context.getApplicationContext());
            }
            c0755a = f970xm;
        }
        return c0755a;
    }

    private C0755a(Context context) {
        this.mContext = context;
        this.f971xn = new HashMap();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            List<ShortcutKey> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            ArrayList arrayList = new ArrayList();
            for (ShortcutKey shortcutKey : list) {
                ShortcutInfo a = m2421a(shortcutKey);
                if (a != null) {
                    arrayList.add(new C0756b(this, shortcutKey, a));
                }
            }
            Message.obtain(this.mUiHandler, 1, arrayList).sendToTarget();
            return true;
        } else if (message.what != 1) {
            return false;
        } else {
            List<C0756b> list2 = (List) message.obj;
            this.f971xn.clear();
            for (C0756b c0756b : list2) {
                this.f971xn.put(c0756b.f973xp, c0756b.f974xq);
            }
            if (this.f972xo != null) {
                this.f972xo.mo3162cG();
            }
            return true;
        }
    }

    /* renamed from: a */
    private ShortcutInfo m2421a(ShortcutKey shortcutKey) {
        LauncherIcons obtain;
        Throwable th;
        Throwable th2;
        List queryForFullDetails = DeepShortcutManager.getInstance(this.mContext).queryForFullDetails(shortcutKey.componentName.getPackageName(), Collections.singletonList(shortcutKey.componentName.getClassName()), shortcutKey.user);
        if (queryForFullDetails.isEmpty()) {
            return null;
        }
        ShortcutInfo shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) queryForFullDetails.get(0), this.mContext);
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
            if (th != null) {
                try {
                    obtain.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
            } else {
                obtain.close();
            }
        }
        throw th;
    }
}
