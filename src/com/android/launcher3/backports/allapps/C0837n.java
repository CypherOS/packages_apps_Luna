package com.android.launcher3.backports.allapps;

import com.android.launcher3.ItemInfo;
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
        this.f1232Bz = c1811m;
        this.val$item = itemInfo;
        this.val$screenId = j;
        this.val$coordinates = iArr;
    }

    public void run() {
        ItemInfo itemInfo = (ShortcutInfo) this.val$item;
        this.f1232Bz.f3284By.mLauncher.mModelWriter.addItemToDatabase(itemInfo, -100, this.val$screenId, this.val$coordinates[0], this.val$coordinates[1]);
        List arrayList = new ArrayList();
        arrayList.add(itemInfo);
        this.f1232Bz.f3284By.mLauncher.bindItems(arrayList, true);
        this.f1232Bz.f3284By.mLauncher.mDragLayer.announceForAccessibility(this.f1232Bz.f3284By.mLauncher.getResources().getString(2131820664));
    }
}
