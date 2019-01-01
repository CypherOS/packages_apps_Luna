package com.google.research.reflection.signal;

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
    ReflectionEventType mo9281C();

    /* renamed from: D */
    C0976d mo9282D();

    /* renamed from: E */
    C0974b mo9283E();

    /* renamed from: F */
    List<String> mo9284F();

    /* renamed from: G */
    byte[] mo9285G();

    /* renamed from: H */
    String mo9286H();

    /* renamed from: a */
    ReflectionEvent mo9287a(ReflectionEventType reflectionEventType);

    /* renamed from: a */
    ReflectionEvent mo9288a(C0976d c0976d);

    /* renamed from: a */
    ReflectionEvent mo9289a(byte[] bArr, int i);

    long getDuration();

    String getId();
}
