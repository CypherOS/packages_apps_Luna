package com.google.research.reflection.layers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* renamed from: com.google.research.reflection.layers.g */
public class C1283g extends C1259d {
    /* renamed from: fc */
    private int f615fc = 0;

    public final String getName() {
        return "OutputLayer";
    }

    public C1283g(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        super(false, i2, i3, i4, i5, i6, i7, i8, z, false, 0.0f);
        this.f615fc = i;
    }

    /* renamed from: a */
    final void mo14339a(int i, C0955e c0955e, C0955e c0955e2, C0955e c0955e3) throws InvalidValueException {
        final int i2 = i;
        final C0955e c0955e4 = c0955e;
        final C0955e c0955e5 = c0955e2;
        final C0955e c0955e6 = c0955e3;
        C0963i.m3067ak().mo9254a(c0955e.f322eR.length, new C0961h() {
            /* renamed from: b */
            public final Boolean mo9243b(int i) {
                if (i2 == 0) {
                    if (C1283g.this.f615fc == 0) {
                        c0955e4.f322eR[i] = (c0955e5.f322eR[i] * (1.0d - c0955e5.f322eR[i])) * (c0955e5.f322eR[i] - c0955e4.f322eR[i]);
                    } else if (C1283g.this.f615fc == 1) {
                        c0955e4.f322eR[i] = c0955e5.f322eR[i] - c0955e4.f322eR[i];
                    }
                } else if (i2 == 2) {
                    c0955e4.f322eR[i] = c0955e5.f322eR[i] - c0955e4.f322eR[i];
                } else {
                    throw new RuntimeException("unsupported activation function for the output layer");
                }
                if (c0955e6 != null) {
                    double[] dArr = c0955e4.f322eR;
                    dArr[i] = dArr[i] * c0955e6.f322eR[i];
                }
                return Boolean.valueOf(true);
            }
        });
    }

    /* renamed from: aj */
    public final C1283g clone() {
        C1283g c1283g = new C1283g();
        super.mo14340a((C1259d) c1283g);
        c1283g.f615fc = this.f615fc;
        return c1283g;
    }

    /* renamed from: b */
    public final void mo9224b(DataOutputStream dataOutputStream) throws IOException {
        super.mo9224b(dataOutputStream);
        dataOutputStream.writeInt(this.f615fc);
        mo9226c(dataOutputStream);
    }

    /* renamed from: b */
    public final void mo9223b(DataInputStream dataInputStream) throws IOException {
        super.mo9223b(dataInputStream);
        this.f615fc = dataInputStream.readInt();
        mo9225c(dataInputStream);
    }
}
