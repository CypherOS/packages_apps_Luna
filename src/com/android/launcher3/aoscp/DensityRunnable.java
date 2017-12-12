package com.android.launcher3.aoscp;

final class DensityRunnable implements Runnable {
    final ClockStatusReceiver mClockStatusReceiver;
    final /* synthetic */ b fq;

    DensityRunnable(ClockStatusReceiver clockStatusReceiver, b bVar) {
        mClockStatusReceiver = clockStatusReceiver;
        this.fq = bVar;
    }

    public void run() {
        mClockStatusReceiver.dO(this.fq);
    }
}
