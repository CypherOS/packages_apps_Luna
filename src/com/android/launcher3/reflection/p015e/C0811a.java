package com.google.android.apps.nexuslauncher.reflection.p015e;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a */
public interface C0811a {

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a$a */
    public static final class C0812a extends MessageNano {
        /* renamed from: bS */
        public String f153bS;
        /* renamed from: bT */
        public String f154bT;
        /* renamed from: bU */
        public String f155bU;
        /* renamed from: bV */
        public String f156bV;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    this.f153bS = codedInputByteBufferNano.readString();
                } else if (readTag == 18) {
                    this.f154bT = codedInputByteBufferNano.readString();
                } else if (readTag == 26) {
                    this.f155bU = codedInputByteBufferNano.readString();
                } else if (readTag == 34) {
                    this.f156bV = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0812a() {
            this.f153bS = BuildConfig.FLAVOR;
            this.f154bT = BuildConfig.FLAVOR;
            this.f155bU = BuildConfig.FLAVOR;
            this.f156bV = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f153bS.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f153bS);
            }
            if (!this.f154bT.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.f154bT);
            }
            if (!this.f155bU.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(3, this.f155bU);
            }
            if (!this.f156bV.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(4, this.f156bV);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f153bS.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f153bS);
            }
            if (!this.f154bT.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.f154bT);
            }
            if (!this.f155bU.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f155bU);
            }
            return !this.f156bV.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(4, this.f156bV) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a$b */
    public static final class C0813b extends MessageNano {
        /* renamed from: bA */
        public String f157bA;
        /* renamed from: bW */
        public C0812a f158bW;
        /* renamed from: bX */
        public C0816e f159bX;
        public String packageName;
        public long timestamp;

        public C0813b() {
            this.f157bA = BuildConfig.FLAVOR;
            this.timestamp = 0;
            this.f158bW = null;
            this.f159bX = null;
            this.packageName = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f157bA.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f157bA);
            }
            if (this.timestamp != 0) {
                codedOutputByteBufferNano.writeInt64(2, this.timestamp);
            }
            if (this.f158bW != null) {
                codedOutputByteBufferNano.writeMessage(3, this.f158bW);
            }
            if (this.f159bX != null) {
                codedOutputByteBufferNano.writeMessage(4, this.f159bX);
            }
            if (!this.packageName.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(5, this.packageName);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f157bA.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f157bA);
            }
            if (this.timestamp != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(2, this.timestamp);
            }
            if (this.f158bW != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, this.f158bW);
            }
            if (this.f159bX != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(4, this.f159bX);
            }
            return !this.packageName.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(5, this.packageName) : computeSerializedSize;
        }

        /* renamed from: c */
        public final C0813b mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    this.f157bA = codedInputByteBufferNano.readString();
                } else if (readTag == 16) {
                    this.timestamp = codedInputByteBufferNano.readInt64();
                } else if (readTag == 26) {
                    if (this.f158bW == null) {
                        this.f158bW = new C0812a();
                    }
                    codedInputByteBufferNano.readMessage(this.f158bW);
                } else if (readTag == 34) {
                    if (this.f159bX == null) {
                        this.f159bX = new C0816e();
                    }
                    codedInputByteBufferNano.readMessage(this.f159bX);
                } else if (readTag == 42) {
                    this.packageName = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: d */
        public static C0813b m2613d(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new C0813b().mergeFrom(codedInputByteBufferNano);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a$c */
    public static final class C0814c extends MessageNano {
        /* renamed from: bY */
        private static volatile C0814c[] f160bY;
        /* renamed from: bZ */
        public String f161bZ;
        /* renamed from: ca */
        public float f162ca;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    this.f161bZ = codedInputByteBufferNano.readString();
                } else if (readTag == 21) {
                    this.f162ca = codedInputByteBufferNano.readFloat();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: v */
        public static C0814c[] m2616v() {
            if (f160bY == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f160bY == null) {
                        f160bY = new C0814c[0];
                    }
                }
            }
            return f160bY;
        }

        public C0814c() {
            this.f161bZ = BuildConfig.FLAVOR;
            this.f162ca = 0.0f;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f161bZ.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f161bZ);
            }
            if (Float.floatToIntBits(this.f162ca) != Float.floatToIntBits(0.0f)) {
                codedOutputByteBufferNano.writeFloat(2, this.f162ca);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f161bZ.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f161bZ);
            }
            return Float.floatToIntBits(this.f162ca) != Float.floatToIntBits(0.0f) ? computeSerializedSize + CodedOutputByteBufferNano.computeFloatSize(2, this.f162ca) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a$d */
    public static final class C0815d extends MessageNano {
        /* renamed from: bG */
        public double[] f163bG;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag != 0) {
                    int length;
                    double[] dArr;
                    switch (readTag) {
                        case 9:
                            readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 9);
                            length = this.f163bG == null ? 0 : this.f163bG.length;
                            dArr = new double[(readTag + length)];
                            if (length != 0) {
                                System.arraycopy(this.f163bG, 0, dArr, 0, length);
                            }
                            while (length < dArr.length - 1) {
                                dArr[length] = codedInputByteBufferNano.readDouble();
                                codedInputByteBufferNano.readTag();
                                length++;
                            }
                            dArr[length] = codedInputByteBufferNano.readDouble();
                            this.f163bG = dArr;
                            break;
                        case 10:
                            readTag = codedInputByteBufferNano.readRawVarint32();
                            length = codedInputByteBufferNano.pushLimit(readTag);
                            readTag /= 8;
                            int length2 = this.f163bG == null ? 0 : this.f163bG.length;
                            dArr = new double[(readTag + length2)];
                            if (length2 != 0) {
                                System.arraycopy(this.f163bG, 0, dArr, 0, length2);
                            }
                            while (length2 < dArr.length) {
                                dArr[length2] = codedInputByteBufferNano.readDouble();
                                length2++;
                            }
                            this.f163bG = dArr;
                            codedInputByteBufferNano.popLimit(length);
                            break;
                        default:
                            if (WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                                break;
                            }
                            return this;
                    }
                }
                return this;
            }
        }

        public C0815d() {
            this.f163bG = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.f163bG != null && this.f163bG.length > 0) {
                for (double writeDouble : this.f163bG) {
                    codedOutputByteBufferNano.writeDouble(1, writeDouble);
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            return (this.f163bG == null || this.f163bG.length <= 0) ? computeSerializedSize : (computeSerializedSize + (this.f163bG.length * 8)) + (this.f163bG.length * 1);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.a$e */
    public static final class C0816e extends MessageNano {
        /* renamed from: cb */
        public int f164cb;
        /* renamed from: cc */
        public C0814c[] f165cc;
        /* renamed from: cd */
        public C0814c[] f166cd;
        /* renamed from: ce */
        public C0814c[] f167ce;
        /* renamed from: cf */
        public C0814c[] f168cf;
        /* renamed from: cg */
        public C0814c[] f169cg;
        /* renamed from: ch */
        public C0815d f170ch;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                int length;
                C0814c[] c0814cArr;
                if (readTag == 8) {
                    this.f164cb = codedInputByteBufferNano.readInt32();
                } else if (readTag == 18) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                    length = this.f165cc == null ? 0 : this.f165cc.length;
                    c0814cArr = new C0814c[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f165cc, 0, c0814cArr, 0, length);
                    }
                    while (length < c0814cArr.length - 1) {
                        c0814cArr[length] = new C0814c();
                        codedInputByteBufferNano.readMessage(c0814cArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0814cArr[length] = new C0814c();
                    codedInputByteBufferNano.readMessage(c0814cArr[length]);
                    this.f165cc = c0814cArr;
                } else if (readTag == 26) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                    length = this.f166cd == null ? 0 : this.f166cd.length;
                    c0814cArr = new C0814c[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f166cd, 0, c0814cArr, 0, length);
                    }
                    while (length < c0814cArr.length - 1) {
                        c0814cArr[length] = new C0814c();
                        codedInputByteBufferNano.readMessage(c0814cArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0814cArr[length] = new C0814c();
                    codedInputByteBufferNano.readMessage(c0814cArr[length]);
                    this.f166cd = c0814cArr;
                } else if (readTag == 34) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                    length = this.f167ce == null ? 0 : this.f167ce.length;
                    c0814cArr = new C0814c[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f167ce, 0, c0814cArr, 0, length);
                    }
                    while (length < c0814cArr.length - 1) {
                        c0814cArr[length] = new C0814c();
                        codedInputByteBufferNano.readMessage(c0814cArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0814cArr[length] = new C0814c();
                    codedInputByteBufferNano.readMessage(c0814cArr[length]);
                    this.f167ce = c0814cArr;
                } else if (readTag == 42) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 42);
                    length = this.f168cf == null ? 0 : this.f168cf.length;
                    c0814cArr = new C0814c[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f168cf, 0, c0814cArr, 0, length);
                    }
                    while (length < c0814cArr.length - 1) {
                        c0814cArr[length] = new C0814c();
                        codedInputByteBufferNano.readMessage(c0814cArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0814cArr[length] = new C0814c();
                    codedInputByteBufferNano.readMessage(c0814cArr[length]);
                    this.f168cf = c0814cArr;
                } else if (readTag == 50) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 50);
                    length = this.f169cg == null ? 0 : this.f169cg.length;
                    c0814cArr = new C0814c[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f169cg, 0, c0814cArr, 0, length);
                    }
                    while (length < c0814cArr.length - 1) {
                        c0814cArr[length] = new C0814c();
                        codedInputByteBufferNano.readMessage(c0814cArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0814cArr[length] = new C0814c();
                    codedInputByteBufferNano.readMessage(c0814cArr[length]);
                    this.f169cg = c0814cArr;
                } else if (readTag == 58) {
                    if (this.f170ch == null) {
                        this.f170ch = new C0815d();
                    }
                    codedInputByteBufferNano.readMessage(this.f170ch);
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0816e() {
            this.f164cb = 0;
            this.f165cc = C0814c.m2616v();
            this.f166cd = C0814c.m2616v();
            this.f167ce = C0814c.m2616v();
            this.f168cf = C0814c.m2616v();
            this.f169cg = C0814c.m2616v();
            this.f170ch = null;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.f164cb != 0) {
                codedOutputByteBufferNano.writeInt32(1, this.f164cb);
            }
            if (this.f165cc != null && this.f165cc.length > 0) {
                for (MessageNano messageNano : this.f165cc) {
                    if (messageNano != null) {
                        codedOutputByteBufferNano.writeMessage(2, messageNano);
                    }
                }
            }
            if (this.f166cd != null && this.f166cd.length > 0) {
                for (MessageNano messageNano2 : this.f166cd) {
                    if (messageNano2 != null) {
                        codedOutputByteBufferNano.writeMessage(3, messageNano2);
                    }
                }
            }
            if (this.f167ce != null && this.f167ce.length > 0) {
                for (MessageNano messageNano22 : this.f167ce) {
                    if (messageNano22 != null) {
                        codedOutputByteBufferNano.writeMessage(4, messageNano22);
                    }
                }
            }
            if (this.f168cf != null && this.f168cf.length > 0) {
                for (MessageNano messageNano222 : this.f168cf) {
                    if (messageNano222 != null) {
                        codedOutputByteBufferNano.writeMessage(5, messageNano222);
                    }
                }
            }
            if (this.f169cg != null && this.f169cg.length > 0) {
                for (MessageNano messageNano3 : this.f169cg) {
                    if (messageNano3 != null) {
                        codedOutputByteBufferNano.writeMessage(6, messageNano3);
                    }
                }
            }
            if (this.f170ch != null) {
                codedOutputByteBufferNano.writeMessage(7, this.f170ch);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int i;
            int computeSerializedSize = super.computeSerializedSize();
            if (this.f164cb != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.f164cb);
            }
            if (this.f165cc != null && this.f165cc.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano : this.f165cc) {
                    if (messageNano != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(2, messageNano);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f166cd != null && this.f166cd.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano2 : this.f166cd) {
                    if (messageNano2 != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(3, messageNano2);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f167ce != null && this.f167ce.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano22 : this.f167ce) {
                    if (messageNano22 != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(4, messageNano22);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f168cf != null && this.f168cf.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano222 : this.f168cf) {
                    if (messageNano222 != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(5, messageNano222);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f169cg != null && this.f169cg.length > 0) {
                for (MessageNano messageNano3 : this.f169cg) {
                    if (messageNano3 != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(6, messageNano3);
                    }
                }
            }
            return this.f170ch != null ? computeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(7, this.f170ch) : computeSerializedSize;
        }
    }
}
