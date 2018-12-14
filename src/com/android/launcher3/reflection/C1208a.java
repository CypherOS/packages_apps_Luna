package com.google.android.apps.nexuslauncher.reflection;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent.OnFinished;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.util.MutableLong;
import com.android.launcher3.util.Preconditions;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.a */
public class C1208a implements C0800b {
    /* renamed from: T */
    private final long f429T;

    public C1208a(Context context) {
        this.f429T = initRecordedTime(context, 1);
    }

    /* renamed from: g */
    public final long mo8495g() {
        return this.f429T;
    }

    protected long getAbsoluteBootTime() {
        return Calendar.getInstance().getTimeInMillis() - SystemClock.elapsedRealtime();
    }

    protected long initRecordedTime(Context context, int i) {
        Intent intent = new Intent("com.google.android.apps.nexuslauncher.reflection.ACTION_BOOT_CYCLE");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 536870912);
        final MutableLong mutableLong = new MutableLong(getAbsoluteBootTime());
        if (broadcast != null) {
            try {
                Preconditions.assertNonUiThread();
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                broadcast.send(i, new OnFinished() {
                    public void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str, Bundle bundle) {
                        mutableLong.value = intent.getLongExtra("time", mutableLong.value);
                        countDownLatch.countDown();
                    }
                }, new Handler(Looper.getMainLooper()));
                countDownLatch.await(1, TimeUnit.SECONDS);
                return mutableLong.value;
            } catch (CanceledException unused) {
            }
        }
        intent.putExtra("time", mutableLong.value);
        ((AlarmManager) context.getSystemService("alarm")).set(1, RecyclerView.FOREVER_NS, PendingIntent.getBroadcast(context, i, intent, 134217728));
        return mutableLong.value;
    }
}
