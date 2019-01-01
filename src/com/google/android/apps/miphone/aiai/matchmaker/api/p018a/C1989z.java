package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.z */
public final class C1989z extends b {
    /* renamed from: vW */
    private static volatile C1989z[] f3265vW;
    /* renamed from: id */
    public String f3266id;
    /* renamed from: uE */
    public String f3267uE;
    /* renamed from: vR */
    public String f3268vR;
    /* renamed from: vX */
    public C1988y f3269vX;
    /* renamed from: vY */
    public C1988y[] f3270vY;
    /* renamed from: vZ */
    public boolean f3271vZ;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.f3266id = aVar.readString();
            } else if (lk == 18) {
                this.f3268vR = aVar.readString();
            } else if (lk == 26) {
                if (this.f3269vX == null) {
                    this.f3269vX = new C1988y();
                }
                aVar.a(this.f3269vX);
            } else if (lk == 34) {
                lk = j.c(aVar, 34);
                int length = this.f3270vY == null ? 0 : this.f3270vY.length;
                C1988y[] c1988yArr = new C1988y[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3270vY, 0, c1988yArr, 0, length);
                }
                while (length < c1988yArr.length - 1) {
                    c1988yArr[length] = new C1988y();
                    aVar.a(c1988yArr[length]);
                    aVar.lk();
                    length++;
                }
                c1988yArr[length] = new C1988y();
                aVar.a(c1988yArr[length]);
                this.f3270vY = c1988yArr;
            } else if (lk == 40) {
                this.f3271vZ = aVar.readBool();
            } else if (lk == 50) {
                this.f3267uE = aVar.readString();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    /* renamed from: cn */
    public static C1989z[] m3212cn() {
        if (f3265vW == null) {
            synchronized (f.ahE) {
                if (f3265vW == null) {
                    f3265vW = new C1989z[0];
                }
            }
        }
        return f3265vW;
    }

    public C1989z() {
        this.f3266id = "";
        this.f3268vR = "";
        this.f3269vX = null;
        this.f3270vY = C1988y.m3211cm();
        this.f3271vZ = false;
        this.f3267uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3266id == null || this.f3266id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3266id);
        }
        if (!(this.f3268vR == null || this.f3268vR.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3268vR);
        }
        if (this.f3269vX != null) {
            codedOutputByteBufferNano.a(3, this.f3269vX);
        }
        if (this.f3270vY != null && this.f3270vY.length > 0) {
            for (g gVar : this.f3270vY) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(4, gVar);
                }
            }
        }
        if (this.f3271vZ) {
            codedOutputByteBufferNano.g(5, this.f3271vZ);
        }
        if (!(this.f3267uE == null || this.f3267uE.equals(""))) {
            codedOutputByteBufferNano.c(6, this.f3267uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3266id == null || this.f3266id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3266id);
        }
        if (!(this.f3268vR == null || this.f3268vR.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3268vR);
        }
        if (this.f3269vX != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(3, this.f3269vX);
        }
        if (this.f3270vY != null && this.f3270vY.length > 0) {
            for (g gVar : this.f3270vY) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(4, gVar);
                }
            }
        }
        if (this.f3271vZ) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(5) + 1;
        }
        if (this.f3267uE == null || this.f3267uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(6, this.f3267uE);
    }
}
