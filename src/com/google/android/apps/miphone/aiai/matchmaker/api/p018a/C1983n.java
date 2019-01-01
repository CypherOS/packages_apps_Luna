package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.n */
public final class C1983n extends b {
    /* renamed from: vf */
    public boolean f3214vf;
    /* renamed from: vg */
    public boolean f3215vg;
    /* renamed from: vh */
    public boolean f3216vh;
    /* renamed from: vi */
    public int f3217vi;
    /* renamed from: vj */
    public int f3218vj;
    /* renamed from: vk */
    public int f3219vk;
    /* renamed from: vl */
    public int f3220vl;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                this.f3214vf = aVar.readBool();
            } else if (lk == 16) {
                this.f3215vg = aVar.readBool();
            } else if (lk == 24) {
                this.f3216vh = aVar.readBool();
            } else if (lk == 32) {
                this.f3217vi = aVar.ll();
            } else if (lk == 40) {
                this.f3218vj = aVar.ll();
            } else if (lk == 48) {
                this.f3219vk = aVar.ll();
            } else if (lk == 56) {
                this.f3220vl = aVar.ll();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1983n() {
        this.f3214vf = false;
        this.f3215vg = false;
        this.f3216vh = false;
        this.f3217vi = 0;
        this.f3218vj = 0;
        this.f3219vk = 0;
        this.f3220vl = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3214vf) {
            codedOutputByteBufferNano.g(1, this.f3214vf);
        }
        if (this.f3215vg) {
            codedOutputByteBufferNano.g(2, this.f3215vg);
        }
        if (this.f3216vh) {
            codedOutputByteBufferNano.g(3, this.f3216vh);
        }
        if (this.f3217vi != 0) {
            codedOutputByteBufferNano.R(4, this.f3217vi);
        }
        if (this.f3218vj != 0) {
            codedOutputByteBufferNano.R(5, this.f3218vj);
        }
        if (this.f3219vk != 0) {
            codedOutputByteBufferNano.R(6, this.f3219vk);
        }
        if (this.f3220vl != 0) {
            codedOutputByteBufferNano.R(7, this.f3220vl);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3214vf) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(1) + 1;
        }
        if (this.f3215vg) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(2) + 1;
        }
        if (this.f3216vh) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(3) + 1;
        }
        if (this.f3217vi != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3217vi);
        }
        if (this.f3218vj != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(5, this.f3218vj);
        }
        if (this.f3219vk != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(6, this.f3219vk);
        }
        if (this.f3220vl != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(7, this.f3220vl);
        }
        return computeSerializedSize;
    }
}
