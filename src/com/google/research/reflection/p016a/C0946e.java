package com.google.research.reflection.p016a;

import android.support.p003v7.widget.RecyclerView;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/* renamed from: com.google.research.reflection.a.e */
public class C0946e {
    /* renamed from: e */
    public static Calendar m2989e(ReflectionEvent reflectionEvent) {
        Calendar instance;
        if (reflectionEvent.mo9282D().getTimeZone() == null || reflectionEvent.mo9282D().getTimeZone().isEmpty()) {
            instance = Calendar.getInstance(TimeZone.getTimeZone(ISO8601Utils.UTC_ID));
            instance.setTimeInMillis(reflectionEvent.mo9282D().getTimestamp() + reflectionEvent.mo9282D().mo9306P());
            return instance;
        }
        instance = Calendar.getInstance(TimeZone.getTimeZone(reflectionEvent.mo9282D().getTimeZone()));
        instance.setTimeInMillis(reflectionEvent.mo9282D().getTimestamp());
        return instance;
    }

    /* renamed from: a */
    public static long m2988a(ReflectionEvent reflectionEvent, ReflectionEvent reflectionEvent2) {
        long timestamp = (reflectionEvent2.mo9282D().getTimestamp() - reflectionEvent.mo9282D().getTimestamp()) - reflectionEvent.getDuration();
        if (reflectionEvent.mo9282D().mo9307g() <= 0 || reflectionEvent2.mo9282D().mo9307g() <= 0) {
            if (timestamp < 0) {
                return RecyclerView.FOREVER_NS;
            }
            return timestamp;
        } else if (Objects.equals(Long.valueOf(reflectionEvent.mo9282D().mo9307g()), Long.valueOf(reflectionEvent2.mo9282D().mo9307g()))) {
            return (reflectionEvent2.mo9282D().mo9305O() - reflectionEvent.mo9282D().mo9305O()) - reflectionEvent.getDuration();
        } else {
            long g = ((reflectionEvent2.mo9282D().mo9307g() + reflectionEvent2.mo9282D().mo9305O()) - (reflectionEvent.mo9282D().mo9307g() + reflectionEvent.mo9282D().mo9305O())) - reflectionEvent.getDuration();
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
