/*
 * Copyright (C) 2019 CypherOS
 * Copyright (C) 2019 Lawnchair Launcher
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
package co.aoscp.lovegood;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.UserHandle;
import android.support.annotation.Keep;
import android.view.View;
import android.view.ViewGroup;

import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Hotseat;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppTransitionManagerImpl;
import com.android.launcher3.LauncherState;
import com.android.launcher3.ShortcutAndWidgetContainer;
import com.android.launcher3.Utilities;
import com.android.launcher3.Workspace;
import com.android.launcher3.Workspace.ItemOperator;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.allapps.AllAppsRecyclerView;
import com.android.launcher3.allapps.AllAppsTransitionController;
import com.android.launcher3.util.ComponentKey;

import com.android.quickstep.TaskUtils;
import com.android.quickstep.views.RecentsView;

import com.android.systemui.shared.recents.model.RecentsTaskLoadPlan;
import com.android.systemui.shared.recents.model.RecentsTaskLoadPlan.Options;
import com.android.systemui.shared.recents.model.RecentsTaskLoadPlan.PreloadOptions;
import com.android.systemui.shared.recents.model.RecentsTaskLoader;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;

import java.util.Arrays;

public class LunaAppTransitionManagerImpl extends LauncherAppTransitionManagerImpl {

    public static final String TAG = "LunaAppTransitionManagerImpl";
    public Launcher mLauncher;

    public LunaAppTransitionManagerImpl(Context context) {
        super(context);
        mLauncher = Launcher.getLauncher(context);
    }

    @Override
    public Animator getClosingWindowAnimators(RemoteAnimationTargetCompat[] targets) {
        if (allowWindowIcon(targets)) {
            for (RemoteAnimationTargetCompat remote : targets) {
                if (remote.mode == 1) {
                    Task task = loadTask(remote.taskId);
                    if (task != null) {
                        ComponentKey component = TaskUtils.getLaunchComponentKeyForTask(task.key);
                        View findIconForComponent = findIconForComponent(component);
                        if (findIconForComponent != null) {
                            AnimatorSet anim = new AnimatorSet();
                            Rect windowSourceBounds = getWindowSourceBounds(targets);
                            playIconAnimators(anim, findIconForComponent, windowSourceBounds, true);
                            anim.play(getOpeningWindowAnimators(findIconForComponent, targets, windowSourceBounds, true));
                            return anim;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Animator animator = super.getClosingWindowAnimators(targets);
        return animator;
    }

    private Task loadTask(int taskId) {
        RecentsView recentsView = (RecentsView) mLauncher.getOverviewPanel();
        RecentsTaskLoadPlan plan = new RecentsTaskLoadPlan(mLauncher);
        Options launchOpts = new Options();
        launchOpts.runningTaskId = taskId;
        launchOpts.numVisibleTasks = 1;
        launchOpts.onlyLoadForCache = true;
        launchOpts.onlyLoadPausedActivities = false;
        launchOpts.loadThumbnails = false;
        PreloadOptions preOpt = new PreloadOptions();
        preOpt.loadTitles = false;
        RecentsTaskLoader taskLoader = recentsView.getModel().getRecentsTaskLoader();
        plan.preloadPlan(preOpt, taskLoader, -1, UserHandle.myUserId());
        taskLoader.loadTasks(plan, launchOpts);
        return plan.getTaskStack().findTaskWithId(taskId);
    }

    private boolean allowWindowIcon(RemoteAnimationTargetCompat[] targets) {
        boolean isAllowed = false;
        if ((!mLauncher.isInState(LauncherState.NORMAL) && !mLauncher.isInState(LauncherState.ALL_APPS)) || mLauncher.hasSomeInvisibleFlag(2)) {
            return false;
        }
        if (!launcherIsATargetWithMode(targets, 0)) {
            if (!mLauncher.isForceInvisible()) {
                return isAllowed;
            }
        }
        isAllowed = true;
        return isAllowed;
    }

    private Rect getWindowSourceBounds(RemoteAnimationTargetCompat[] targets) {
        int i = 0;
        Rect bounds = new Rect(0, 0, mDeviceProfile.widthPx, mDeviceProfile.heightPx);
        if (mLauncher.isInMultiWindowModeCompat()) {
            int length = targets.length;
            while (i < length) {
                RemoteAnimationTargetCompat target = targets[i];
                if (target.mode == 1) {
                    bounds.set(target.sourceContainerBounds);
                    Point point = target.position;
                    bounds.offsetTo(point.x, point.y);
                    return bounds;
                }
                i++;
            }
        }
        return bounds;
    }

    private View findIconForComponent(ComponentKey component) {
        if (mLauncher.isInState(LauncherState.NORMAL)) {
            return findWorkspaceIconForComponent(component);
        }
        if (mLauncher.isInState(LauncherState.ALL_APPS)) {
            return findAllAppsIconForComponent(component);
        }
        return null;
    }

    private View findWorkspaceIconForComponent(ComponentKey component) {
        ItemOperator operator = new ComponentItemOperator(component);
        ShortcutAndWidgetContainer[] containers = new ShortcutAndWidgetContainer[2];
        containers[0] = mLauncher.getWorkspace().getCurrentContainer();
        containers[1] = mLauncher.getHotseat().getLayout().getShortcutsAndWidgets();
        return findInViews(operator, containers);
    }

    private View findAllAppsIconForComponent(ComponentKey component) {
        ItemOperator operator = new ComponentItemOperator(component);
        ViewGroup[] containers = new ViewGroup[2];
        AllAppsContainerView appsView = mLauncher.getAllAppsController().getAppsView();
        containers[0] = appsView.getActiveRecyclerView();
		containers[1] = appsView.getFloatingHeaderView();
        return findInViews(operator, containers);
    }
	
	public View findInViews(ItemOperator op, ViewGroup... views) {
        for (ViewGroup view : views) {
            int itemCount = view.getChildCount();
            for (int itemIdx = 0; itemIdx < itemCount; itemIdx++) {
                View item = view.getChildAt(itemIdx);
                if (op.evaluate((ItemInfo) item.getTag(), item)) {
                    return item;
                }
            }
        }
        return null;
    }
}
