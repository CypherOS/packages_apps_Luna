package com.android.launcher3.backports.allapps;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.m */
class C1811m extends LauncherAccessibilityDelegate {
    /* renamed from: By */
    final /* synthetic */ ActionsRowView f3284By;

    C1811m(ActionsRowView actionsRowView, Launcher launcher) {
        this.f3284By = actionsRowView;
        super(launcher);
    }

    public final void addSupportedActions(View view, AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
        accessibilityNodeInfo.addAction((AccessibilityAction) this.mActions.get(2131361797));
        accessibilityNodeInfo.addAction((AccessibilityAction) this.mActions.get(2131361793));
    }

    public final boolean performAction(View view, ItemInfo itemInfo, int i) {
        if (i != 2131361793) {
            return super.performAction(view, itemInfo, i);
        }
        int[] iArr = new int[2];
        this.f3284By.mLauncher.mStateManager.goToState(LauncherState.NORMAL, true, new C0837n(this, itemInfo, findSpaceOnWorkspace(itemInfo, iArr), iArr));
        return true;
    }
}
