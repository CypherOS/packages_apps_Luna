package com.google.research.reflection.predictor;

import com.google.research.reflection.predictor.C0972k.C0971a;
import java.util.BitSet;
import java.util.Map.Entry;

/* renamed from: com.google.research.reflection.predictor.f */
public class C0967f {
    /* renamed from: fF */
    private final BitSet f354fF = new BitSet(32);

    /* renamed from: a */
    public final C0967f mo9263a(C0971a c0971a) {
        this.f354fF.clear(0, 2);
        for (String str : c0971a.f361fS) {
            if (str.equals("neural_predictor")) {
                this.f354fF.set(0);
            } else if (str.equals("recency_event_predictor")) {
                this.f354fF.set(1);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C0967f mo9262a(C0970i c0970i) {
        this.f354fF.clear(16, 22);
        for (C0968g Y : c0970i.f358fL) {
            for (Entry entry : Y.mo9265Y().entrySet()) {
                if (!((Boolean) entry.getValue()).booleanValue()) {
                    String str = (String) entry.getKey();
                    if (str.equals("local_app_launch_history")) {
                        this.f354fF.set(16);
                    } else {
                        BitSet bitSet;
                        int i;
                        if (str.equals("private_place")) {
                            bitSet = this.f354fF;
                            i = 17;
                        } else if (str.equals("lat_lng")) {
                            bitSet = this.f354fF;
                            i = 18;
                        } else if (str.equals("headset_wired")) {
                            bitSet = this.f354fF;
                            i = 19;
                        } else if (str.equals("headset_bluetooth")) {
                            bitSet = this.f354fF;
                            i = 20;
                        } else if (str.equals("local_app_usage_history")) {
                            bitSet = this.f354fF;
                            i = 21;
                        }
                        bitSet.set(i);
                    }
                }
            }
        }
        return this;
    }

    /* renamed from: as */
    public final int mo9264as() {
        int i = 0;
        int i2 = 0;
        while (i < this.f354fF.length()) {
            i2 |= this.f354fF.get(i) ? 1 << i : 0;
            i++;
        }
        return i2;
    }
}
