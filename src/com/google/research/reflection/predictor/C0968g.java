package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0944c;
import com.google.research.reflection.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.google.research.reflection.predictor.g */
public abstract class C0968g {
    /* renamed from: fG */
    C0965b f355fG;

    /* renamed from: a */
    public abstract void mo9266a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException;

    /* renamed from: a */
    public abstract void mo9267a(Integer num, Integer num2, String str);

    /* renamed from: a */
    public void mo9268a(String str, String str2, Map<String, String> map) {
    }

    /* renamed from: b */
    public abstract void mo9269b(DataOutputStream dataOutputStream) throws IOException;

    public abstract String getName();

    /* renamed from: h */
    public abstract C0972k mo9272h(ReflectionEvent reflectionEvent);

    /* renamed from: j */
    public abstract C0972k mo9273j(ReflectionEvent reflectionEvent);

    /* renamed from: k */
    public boolean mo9274k(ReflectionEvent reflectionEvent) {
        return true;
    }

    /* renamed from: a */
    public static C0968g m3089a(String str, C0944c c0944c) {
        if (str.equals("neural_predictor")) {
            return new C1286d();
        }
        if (str.equals("recency_event_predictor")) {
            return new C1261h();
        }
        if (str.equals("shortcut_neural_predictor")) {
            return new C1299m();
        }
        if (str.equals("Rule_Based_Predictor")) {
            return new C1287j(c0944c);
        }
        return null;
    }

    /* renamed from: Y */
    public Map<String, Boolean> mo9265Y() {
        return Collections.emptyMap();
    }

    /* renamed from: c */
    public void mo9270c(C0965b c0965b) {
        this.f355fG = c0965b;
    }
}
