package com.android.launcher3.reflection.p010a;

import com.android.launcher3.reflection.p015e.C0824c.C0828d;
import com.android.launcher3.reflection.research.predictor.AbstractEventEstimator;
import com.android.launcher3.reflection.research.predictor.C0964g;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

/* renamed from: com.android.launcher3.reflection.a.c */
public class C0790c {
    /* renamed from: a */
    public static C0828d m2558a(C0964g c0964g) throws IOException {
        C0828d c0828d = new C0828d();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        c0964g.mo9251b(dataOutputStream);
        dataOutputStream.flush();
        c0828d.f200cx = byteArrayOutputStream.toByteArray();
        dataOutputStream.close();
        c0828d.f199cw = c0964g.getName();
        if (!(c0964g instanceof AbstractEventEstimator)) {
            return c0828d;
        }
        AbstractEventEstimator abstractEventEstimator = (AbstractEventEstimator) c0964g;
        c0828d.f202cz = abstractEventEstimator.mo14306ap();
        c0828d.f201cy = abstractEventEstimator.mo14305ao();
        c0828d.f195cA = C0792e.m2564a(abstractEventEstimator.mo14307aq());
        c0828d.f196cB = C0792e.m2566b((Map) abstractEventEstimator.mo14303am());
        c0828d.f197cC = C0792e.m2566b((Map) abstractEventEstimator.mo14302al());
        c0828d.f198cD = C0792e.m2566b((Map) abstractEventEstimator.mo14304an());
        return c0828d;
    }
}
