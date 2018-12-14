package com.android.launcher3.predictions;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.PredictionUiCallback;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionsController implements Callback {

    private static PredictionsController mController;
    public final Handler mWorkerThread = new Handler(LauncherModel.getWorkerLooper(), this);
    private final Handler mUiThread = new Handler(Looper.getMainLooper(), this);
    public Map<ShortcutKey, ShortcutInfo> mShortcutMapper;
    public PredictionUiCallback mUiCallback;
    private Context mContext;

    private class PredictionInfo {
        public ShortcutKey mShortcutKey;
        public ShortcutInfo mShortcutInfo;

        public PredictionInfo(ShortcutKey shortcutKey, ShortcutInfo shortcutInfo) {
            mShortcutKey = shortcutKey;
            mShortcutInfo = shortcutInfo;
        }
    }

    public static synchronized PredictionsController getInstance(Context context) {
        PredictionsController controller;
        synchronized (PredictionsController.class) {
            Preconditions.assertUIThread();
            if (mController == null) {
                mController = new PredictionsController(context.getApplicationContext());
            }
            controller = mController;
        }
        return controller;
    }

    private PredictionsController(Context context) {
        mContext = context;
        mShortcutMapper = new HashMap();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            List<ShortcutKey> list = message.obj != null ? (List) message.obj : Collections.EMPTY_LIST;
            ArrayList arrayList = new ArrayList();
            for (ShortcutKey shortcutKey : list) {
                ShortcutInfo a = getInfo(shortcutKey);
                if (a != null) {
                    arrayList.add(new PredictionInfo(shortcutKey, a));
                }
            }
            Message.obtain(mUiThread, 1, arrayList).sendToTarget();
            return true;
        } else if (message.what != 1) {
            return false;
        } else {
            List<PredictionInfo> list2 = (List) message.obj;
            mShortcutMapper.clear();
            for (PredictionInfo info : list2) {
                mShortcutMapper.put(info.mShortcutKey, info.mShortcutInfo);
            }
            if (mUiCallback != null) {
                mUiCallback.onUpdateUI();
            }
            return true;
        }
    }

    private ShortcutInfo getInfo(ShortcutKey shortcutKey) {
        LauncherIcons obtain;
        List queryForFullDetails = DeepShortcutManager.getInstance(mContext).queryForFullDetails(shortcutKey.componentName.getPackageName(), Collections.singletonList(shortcutKey.getId()), shortcutKey.user);
        if (queryForFullDetails.isEmpty()) {
            return null;
        }
        ShortcutInfo shortcutInfo = new ShortcutInfo((ShortcutInfoCompat) queryForFullDetails.get(0), mContext);
        try {
            obtain = LauncherIcons.obtain(mContext);
            try {
                obtain.createShortcutIcon((ShortcutInfoCompat) queryForFullDetails.get(0), true, null).applyTo((ItemInfoWithIcon) shortcutInfo);
                if (obtain != null) {
                    obtain.close();
                }
                return shortcutInfo;
            } catch (Throwable ignored) {
            }
        } catch (Exception unused) {
            return null;
        }
        if (obtain != null) {
            try {
                obtain.close();
            } catch (Throwable ignored) {
            }
        }
		return shortcutInfo;
    }
}
