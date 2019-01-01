package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.K */
public final class C1973K extends b {
    public float height;
    public float left;
    public float top;
    public float width;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 13) {
                this.left = Float.intBitsToFloat(aVar.ln());
            } else if (lk == 21) {
                this.top = Float.intBitsToFloat(aVar.ln());
            } else if (lk == 29) {
                this.width = Float.intBitsToFloat(aVar.ln());
            } else if (lk == 37) {
                this.height = Float.intBitsToFloat(aVar.ln());
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1973K() {
        this.left = 0.0f;
        this.top = 0.0f;
        this.width = 0.0f;
        this.height = 0.0f;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (Float.floatToIntBits(this.left) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(1, this.left);
        }
        if (Float.floatToIntBits(this.top) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(2, this.top);
        }
        if (Float.floatToIntBits(this.width) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(3, this.width);
        }
        if (Float.floatToIntBits(this.height) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(4, this.height);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (Float.floatToIntBits(this.left) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(1) + 4;
        }
        if (Float.floatToIntBits(this.top) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(2) + 4;
        }
        if (Float.floatToIntBits(this.width) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(3) + 4;
        }
        if (Float.floatToIntBits(this.height) != Float.floatToIntBits(0.0f)) {
            return computeSerializedSize + (CodedOutputByteBufferNano.bZ(4) + 4);
        }
        return computeSerializedSize;
    }
}
