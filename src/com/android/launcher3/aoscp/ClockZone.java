package com.android.launcher3.aoscp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class ClockZone extends BroadcastReceiver {
    final ClockStatusReceiver mClockStatusReceiver;

    ClockZone(ClockStatusReceiver clockStatusReceiver) {
        mClockStatusReceiver = clockStatusReceiver;
    }

    public void onReceive(Context context, Intent intent) {
        mClockStatusReceiver.dM(intent.getStringExtra("time-zone"));
    }
}
