package com.android.launcher3.reflection.research.layers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* renamed from: com.android.launcher3.reflection.research.layers.g */
public class C1278g extends C1255d {
    /* renamed from: fc */
    private int f616fc = 0;

    public final String getName() {
        return "OutputLayer";
    }

    public C1278g(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        super(false, i2, i3, i4, i5, i6, i7, i8, z, false, 0.0f);
        this.f616fc = i;
    }

    /* renamed from: a */
    final void mo14298a(int i, C0951e c0951e, C0951e c0951e2, C0951e c0951e3) throws InvalidValueException {
        final int i2 = i;
        final C0951e c0951e4 = c0951e;
        final C0951e c0951e5 = c0951e2;
        final C0951e c0951e6 = c0951e3;
        C0959i.m3052ak().mo9236a(c0951e.f322eR.length, new C0957h() {
            /* renamed from: b */
            public final Boolean mo9225b(int i) {
                if (i2 == 0) {
                    if (C1278g.this.f616fc == 0) {
                        c0951e4.f322eR[i] = (c0951e5.f322eR[i] * (1.0d - c0951e5.f322eR[i])) * (c0951e5.f322eR[i] - c0951e4.f322eR[i]);
                    } else if (C1278g.this.f616fc == 1) {
                        c0951e4.f322eR[i] = c0951e5.f322eR[i] - c0951e4.f322eR[i];
                    }
                } else if (i2 == 2) {
                    c0951e4.f322eR[i] = c0951e5.f322eR[i] - c0951e4.f322eR[i];
                } else {
                    throw new RuntimeException("unsupported activation function for the output layer");
                }
                if (c0951e6 != null) {
                    double[] dArr = c0951e4.f322eR;
                    dArr[i] = dArr[i] * c0951e6.f322eR[i];
                }
                return Boolean.valueOf(true);
            }
        });
    }

    /* renamed from: aj */
    public final C1278g clone() {
        C1278g c1278g = new C1278g();
        super.mo14299a((C1255d) c1278g);
        c1278g.f616fc = this.f616fc;
        return c1278g;
    }

    /* renamed from: b */
    public final void mo9206b(DataOutputStream dataOutputStream) throws IOException {
        super.mo9206b(dataOutputStream);
        dataOutputStream.writeInt(this.f616fc);
        mo9208c(dataOutputStream);
    }

    /* renamed from: b */
    public final void mo9205b(DataInputStream dataInputStream) throws IOException {
        super.mo9205b(dataInputStream);
        this.f616fc = dataInputStream.readInt();
        mo9207c(dataInputStream);
    }
}
