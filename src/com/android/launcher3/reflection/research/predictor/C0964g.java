package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.android.launcher3.reflection.research.predictor.g */
public abstract class C0964g {
    /* renamed from: fG */
    C0961b f355fG;

    /* renamed from: a */
    public abstract void mo9248a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException;

    /* renamed from: a */
    public abstract void mo9249a(Integer num, Integer num2, String str);

    /* renamed from: a */
    public void mo9250a(String str, String str2, Map<String, String> map) {
    }

    /* renamed from: b */
    public abstract void mo9251b(DataOutputStream dataOutputStream) throws IOException;

    public abstract String getName();

    /* renamed from: h */
    public abstract C0968k mo9254h(ReflectionEvent reflectionEvent);

    /* renamed from: j */
    public abstract C0968k mo9255j(ReflectionEvent reflectionEvent);

    /* renamed from: k */
    public boolean mo9256k(ReflectionEvent reflectionEvent) {
        return true;
    }

    /* renamed from: a */
    public static C0964g m3074a(String str, C0940c c0940c) {
        if (str.equals("neural_predictor")) {
            return new C1281d();
        }
        if (str.equals("recency_event_predictor")) {
            return new C1257h();
        }
        if (str.equals("shortcut_neural_predictor")) {
            return new C1294m();
        }
        if (str.equals("Rule_Based_Predictor")) {
            return new C1282j(c0940c);
        }
        return null;
    }

    /* renamed from: Y */
    public Map<String, Boolean> mo9247Y() {
        return Collections.emptyMap();
    }

    /* renamed from: c */
    public void mo9252c(C0961b c0961b) {
        this.f355fG = c0961b;
    }
}
