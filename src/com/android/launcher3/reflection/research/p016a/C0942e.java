package com.android.launcher3.reflection.research.p016a;

import android.support.p003v7.widget.RecyclerView;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/* renamed from: com.android.launcher3.reflection.research.a.e */
public class C0942e {
    /* renamed from: e */
    public static Calendar m2974e(ReflectionEvent reflectionEvent) {
        Calendar instance;
        if (reflectionEvent.mo9264D().getTimeZone() == null || reflectionEvent.mo9264D().getTimeZone().isEmpty()) {
            instance = Calendar.getInstance(TimeZone.getTimeZone(ISO8601Utils.UTC_ID));
            instance.setTimeInMillis(reflectionEvent.mo9264D().getTimestamp() + reflectionEvent.mo9264D().mo9288P());
            return instance;
        }
        instance = Calendar.getInstance(TimeZone.getTimeZone(reflectionEvent.mo9264D().getTimeZone()));
        instance.setTimeInMillis(reflectionEvent.mo9264D().getTimestamp());
        return instance;
    }

    /* renamed from: a */
    public static long m2973a(ReflectionEvent reflectionEvent, ReflectionEvent reflectionEvent2) {
        long timestamp = (reflectionEvent2.mo9264D().getTimestamp() - reflectionEvent.mo9264D().getTimestamp()) - reflectionEvent.getDuration();
        if (reflectionEvent.mo9264D().mo9289g() <= 0 || reflectionEvent2.mo9264D().mo9289g() <= 0) {
            if (timestamp < 0) {
                return RecyclerView.FOREVER_NS;
            }
            return timestamp;
        } else if (Objects.equals(Long.valueOf(reflectionEvent.mo9264D().mo9289g()), Long.valueOf(reflectionEvent2.mo9264D().mo9289g()))) {
            return (reflectionEvent2.mo9264D().mo9287O() - reflectionEvent.mo9264D().mo9287O()) - reflectionEvent.getDuration();
        } else {
            long g = ((reflectionEvent2.mo9264D().mo9289g() + reflectionEvent2.mo9264D().mo9287O()) - (reflectionEvent.mo9264D().mo9289g() + reflectionEvent.mo9264D().mo9287O())) - reflectionEvent.getDuration();
            int i = (timestamp > 0 ? 1 : (timestamp == 0 ? 0 : -1));
            if (i >= 0 && g >= 0) {
                return Math.min(timestamp, g);
            }
            if (i < 0 && g >= 0) {
                return g;
            }
            if (i < 0 || g >= 0) {
                return RecyclerView.FOREVER_NS;
            }
            return timestamp;
        }
    }
}
