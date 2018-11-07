package com.google.android.apps.nexuslauncher;

import android.content.res.Configuration;

import com.android.launcher3.AppInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.ComponentKeyMapper;

import java.util.List;

public class NexusLauncherActivity extends Launcher {
	
	private NexusLauncher mLauncher;
	
	public NexusLauncherActivity() {
        mLauncher = new NexusLauncher(this);
    }
	
	public List<ComponentKeyMapper<AppInfo>> getPredictedApps() {
        return mLauncher.fA.getPredictedApps();
    }
}