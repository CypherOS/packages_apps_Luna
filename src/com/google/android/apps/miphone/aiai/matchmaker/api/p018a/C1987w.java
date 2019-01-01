package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.w */
public final class C1987w extends b {
    /* renamed from: vN */
    public boolean f3254vN;
    /* renamed from: vO */
    public boolean f3255vO;
    /* renamed from: vP */
    public int f3256vP;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                this.f3254vN = aVar.readBool();
            } else if (lk == 16) {
                this.f3255vO = aVar.readBool();
            } else if (lk == 24) {
                this.f3256vP = aVar.ll();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1987w() {
        this.f3254vN = false;
        this.f3255vO = false;
        this.f3256vP = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3254vN) {
            codedOutputByteBufferNano.g(1, this.f3254vN);
        }
        if (this.f3255vO) {
            codedOutputByteBufferNano.g(2, this.f3255vO);
        }
        if (this.f3256vP != 0) {
            codedOutputByteBufferNano.R(3, this.f3256vP);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3254vN) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(1) + 1;
        }
        if (this.f3255vO) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(2) + 1;
        }
        if (this.f3256vP != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(3, this.f3256vP);
        }
        return computeSerializedSize;
    }
}
