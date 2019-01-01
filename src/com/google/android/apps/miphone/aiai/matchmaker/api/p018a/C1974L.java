package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.L */
public final class C1974L extends b {
    /* renamed from: wK */
    public long f3164wK;
    /* renamed from: wL */
    public long f3165wL;
    /* renamed from: wM */
    public long f3166wM;
    /* renamed from: wN */
    public long f3167wN;
    /* renamed from: wO */
    public long f3168wO;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                this.f3164wK = aVar.lm();
            } else if (lk == 16) {
                this.f3165wL = aVar.lm();
            } else if (lk == 24) {
                this.f3166wM = aVar.lm();
            } else if (lk == 32) {
                this.f3167wN = aVar.lm();
            } else if (lk == 40) {
                this.f3168wO = aVar.lm();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1974L() {
        this.f3164wK = 0;
        this.f3165wL = 0;
        this.f3166wM = 0;
        this.f3167wN = 0;
        this.f3168wO = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3164wK != 0) {
            codedOutputByteBufferNano.i(1, this.f3164wK);
        }
        if (this.f3165wL != 0) {
            codedOutputByteBufferNano.i(2, this.f3165wL);
        }
        if (this.f3166wM != 0) {
            codedOutputByteBufferNano.i(3, this.f3166wM);
        }
        if (this.f3167wN != 0) {
            codedOutputByteBufferNano.i(4, this.f3167wN);
        }
        if (this.f3168wO != 0) {
            codedOutputByteBufferNano.i(5, this.f3168wO);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3164wK != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.k(1, this.f3164wK);
        }
        if (this.f3165wL != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.k(2, this.f3165wL);
        }
        if (this.f3166wM != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.k(3, this.f3166wM);
        }
        if (this.f3167wN != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.k(4, this.f3167wN);
        }
        if (this.f3168wO != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.k(5, this.f3168wO);
        }
        return computeSerializedSize;
    }
}
