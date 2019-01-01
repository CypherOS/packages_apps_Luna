package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.G */
public final class C1970G extends b {
    /* renamed from: wA */
    public boolean f3152wA;
    /* renamed from: wz */
    public boolean f3153wz;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                this.f3153wz = aVar.readBool();
            } else if (lk == 16) {
                this.f3152wA = aVar.readBool();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1970G() {
        this.f3153wz = false;
        this.f3152wA = false;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3153wz) {
            codedOutputByteBufferNano.g(1, this.f3153wz);
        }
        if (this.f3152wA) {
            codedOutputByteBufferNano.g(2, this.f3152wA);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3153wz) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(1) + 1;
        }
        if (this.f3152wA) {
            return computeSerializedSize + (CodedOutputByteBufferNano.bZ(2) + 1);
        }
        return computeSerializedSize;
    }
}
