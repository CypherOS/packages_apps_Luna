package com.android.launcher3.reflection.research.signal;

import java.util.List;

public interface ReflectionEvent {

    public enum ReflectionEventType {
        APP_USAGE,
        APP_INSTALL,
        INSTANT_APP_USAGE,
        SHORTCUTS,
        HEADSET,
        CLICK,
        SPECIAL_SCREEN,
        NOTIFICATION
    }

    /* renamed from: C */
    ReflectionEventType mo9263C();

    /* renamed from: D */
    C0972d mo9264D();

    /* renamed from: E */
    C0970b mo9265E();

    /* renamed from: F */
    List<String> mo9266F();

    /* renamed from: G */
    byte[] mo9267G();

    /* renamed from: H */
    String mo9268H();

    /* renamed from: a */
    ReflectionEvent mo9269a(ReflectionEventType reflectionEventType);

    /* renamed from: a */
    ReflectionEvent mo9270a(C0972d c0972d);

    /* renamed from: a */
    ReflectionEvent mo9271a(byte[] bArr, int i);

    long getDuration();

    String getId();
}
