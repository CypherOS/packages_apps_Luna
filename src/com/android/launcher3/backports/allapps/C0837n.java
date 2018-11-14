package com.android.launcher3.backports.allapps;

import com.android.launcher3.ItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutInfo;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.n */
class C0837n implements Runnable {
    /* renamed from: Bz */
    final /* synthetic */ C1811m f1232Bz;
    final /* synthetic */ int[] val$coordinates;
    final /* synthetic */ ItemInfo val$item;
    final /* synthetic */ long val$screenId;

    C0837n(C1811m c1811m, ItemInfo itemInfo, long j, int[] iArr) {
        f1232Bz = c1811m;
        val$item = itemInfo;
        val$screenId = j;
        val$coordinates = iArr;
    }

    public void run() {
        ItemInfo itemInfo = (ShortcutInfo) val$item;
        f1232Bz.f3284By.mLauncher.mModelWriter.addItemToDatabase(itemInfo, -100, val$screenId, val$coordinates[0], val$coordinates[1]);
        List arrayList = new ArrayList();
        arrayList.add(itemInfo);
        f1232Bz.f3284By.mLauncher.bindItems(arrayList, true);
        f1232Bz.f3284By.mLauncher.mDragLayer.announceForAccessibility(f1232Bz.f3284By.mLauncher.getResources().getString(R.string.item_added_to_workspace));
    }
}
