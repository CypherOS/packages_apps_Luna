package com.android.launcher3.aoscp;

final class SomeRunnable implements Runnable {
    final ClockStatusReceiver mClockStatusReceiver;

    SomeRunnable(ClockStatusReceiver clockStatusReceiver) {
        mClockStatusReceiver = clockStatusReceiver;
    }

    public void run() {
        mClockStatusReceiver.dN();
    }
}
