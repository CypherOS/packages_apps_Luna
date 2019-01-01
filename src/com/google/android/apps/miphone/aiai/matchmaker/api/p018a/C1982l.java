package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.l */
public final class C1982l extends b {
    @NanoEnumValue(legacy = false, value = C0862m.class)
    /* renamed from: uN */
    public int f3210uN;
    /* renamed from: vc */
    public int f3211vc;
    /* renamed from: vd */
    public int f3212vd;
    /* renamed from: ve */
    public int f3213ve;

    public C1982l() {
        this.f3210uN = 0;
        this.f3211vc = 0;
        this.f3212vd = 0;
        this.f3213ve = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3210uN != 0) {
            codedOutputByteBufferNano.R(1, this.f3210uN);
        }
        if (this.f3211vc != 0) {
            codedOutputByteBufferNano.R(2, this.f3211vc);
        }
        if (this.f3212vd != 0) {
            codedOutputByteBufferNano.R(3, this.f3212vd);
        }
        if (this.f3213ve != 0) {
            codedOutputByteBufferNano.R(4, this.f3213ve);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3210uN != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(1, this.f3210uN);
        }
        if (this.f3211vc != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(2, this.f3211vc);
        }
        if (this.f3212vd != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(3, this.f3212vd);
        }
        if (this.f3213ve != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(4, this.f3213ve);
        }
        return computeSerializedSize;
    }

    /* renamed from: d */
    public final C1982l mergeFrom(a aVar) {
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 3) {
                        stringBuilder = new StringBuilder(51);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum OverviewInteraction");
                    } else {
                        this.f3210uN = ll;
                    }
                } catch (IllegalArgumentException e) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 16) {
                this.f3211vc = aVar.readInt32();
            } else if (lk == 24) {
                this.f3212vd = aVar.readInt32();
            } else if (lk == 32) {
                this.f3213ve = aVar.readInt32();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(51);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum OverviewInteraction");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
