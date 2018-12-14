package com.google.android.apps.nexuslauncher.reflection.p015e;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b */
public interface C0817b {

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$a */
    public static final class C0818a extends MessageNano {
        /* renamed from: bq */
        public String f171bq;
        /* renamed from: bw */
        public String[] f172bw;
        /* renamed from: ci */
        public C0823f f173ci;
        /* renamed from: cj */
        public C0820c f174cj;
        /* renamed from: ck */
        public String f175ck;
        /* renamed from: cl */
        public String f176cl;
        public long duration;
        /* renamed from: id */
        public String f177id;
        public int type;

        public C0818a() {
            this.f177id = BuildConfig.FLAVOR;
            this.type = 0;
            this.duration = 0;
            this.f171bq = BuildConfig.FLAVOR;
            this.f173ci = null;
            this.f174cj = null;
            this.f175ck = BuildConfig.FLAVOR;
            this.f172bw = WireFormatNano.EMPTY_STRING_ARRAY;
            this.f176cl = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f177id.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f177id);
            }
            if (this.type != 0) {
                codedOutputByteBufferNano.writeInt32(2, this.type);
            }
            if (this.duration != 0) {
                codedOutputByteBufferNano.writeInt64(3, this.duration);
            }
            if (!this.f171bq.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(4, this.f171bq);
            }
            if (this.f173ci != null) {
                codedOutputByteBufferNano.writeMessage(5, this.f173ci);
            }
            if (this.f174cj != null) {
                codedOutputByteBufferNano.writeMessage(6, this.f174cj);
            }
            if (!this.f175ck.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(7, this.f175ck);
            }
            if (this.f172bw != null && this.f172bw.length > 0) {
                for (String str : this.f172bw) {
                    if (str != null) {
                        codedOutputByteBufferNano.writeString(8, str);
                    }
                }
            }
            if (!this.f176cl.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(9, this.f176cl);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f177id.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f177id);
            }
            if (this.type != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, this.type);
            }
            if (this.duration != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(3, this.duration);
            }
            if (!this.f171bq.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f171bq);
            }
            if (this.f173ci != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(5, this.f173ci);
            }
            if (this.f174cj != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(6, this.f174cj);
            }
            if (!this.f175ck.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(7, this.f175ck);
            }
            if (this.f172bw != null && this.f172bw.length > 0) {
                int i = 0;
                int i2 = 0;
                for (String str : this.f172bw) {
                    if (str != null) {
                        i2++;
                        i += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                    }
                }
                computeSerializedSize = (computeSerializedSize + i) + (i2 * 1);
            }
            return !this.f176cl.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(9, this.f176cl) : computeSerializedSize;
        }

        /* renamed from: e */
        public final C0818a mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag != 10) {
                    if (readTag == 16) {
                        readTag = codedInputByteBufferNano.readInt32();
                        switch (readTag) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                                this.type = readTag;
                                break;
                        }
                    } else if (readTag == 24) {
                        this.duration = codedInputByteBufferNano.readInt64();
                    } else if (readTag == 34) {
                        this.f171bq = codedInputByteBufferNano.readString();
                    } else if (readTag == 42) {
                        if (this.f173ci == null) {
                            this.f173ci = new C0823f();
                        }
                        codedInputByteBufferNano.readMessage(this.f173ci);
                    } else if (readTag == 50) {
                        if (this.f174cj == null) {
                            this.f174cj = new C0820c();
                        }
                        codedInputByteBufferNano.readMessage(this.f174cj);
                    } else if (readTag == 58) {
                        this.f175ck = codedInputByteBufferNano.readString();
                    } else if (readTag == 66) {
                        readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 66);
                        int length = this.f172bw == null ? 0 : this.f172bw.length;
                        String[] strArr = new String[(readTag + length)];
                        if (length != 0) {
                            System.arraycopy(this.f172bw, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = codedInputByteBufferNano.readString();
                            codedInputByteBufferNano.readTag();
                            length++;
                        }
                        strArr[length] = codedInputByteBufferNano.readString();
                        this.f172bw = strArr;
                    } else if (readTag == 74) {
                        this.f176cl = codedInputByteBufferNano.readString();
                    } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                        return this;
                    }
                } else {
                    this.f177id = codedInputByteBufferNano.readString();
                }
            }
        }

        /* renamed from: c */
        public static C0818a m2620c(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0818a) MessageNano.mergeFrom(new C0818a(), bArr);
        }

        /* renamed from: f */
        public static C0818a m2621f(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new C0818a().mergeFrom(codedInputByteBufferNano);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$b */
    public static final class C0819b extends MessageNano {
        /* renamed from: bB */
        public double f178bB;
        /* renamed from: bC */
        public double f179bC;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 17) {
                    this.f178bB = codedInputByteBufferNano.readDouble();
                } else if (readTag == 25) {
                    this.f179bC = codedInputByteBufferNano.readDouble();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0819b() {
            this.time = 0;
            this.f178bB = 0.0d;
            this.f179bC = 0.0d;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (Double.doubleToLongBits(this.f178bB) != Double.doubleToLongBits(0.0d)) {
                codedOutputByteBufferNano.writeDouble(2, this.f178bB);
            }
            if (Double.doubleToLongBits(this.f179bC) != Double.doubleToLongBits(0.0d)) {
                codedOutputByteBufferNano.writeDouble(3, this.f179bC);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            if (Double.doubleToLongBits(this.f178bB) != Double.doubleToLongBits(0.0d)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeDoubleSize(2, this.f178bB);
            }
            return Double.doubleToLongBits(this.f179bC) != Double.doubleToLongBits(0.0d) ? computeSerializedSize + CodedOutputByteBufferNano.computeDoubleSize(3, this.f179bC) : computeSerializedSize;
        }

        /* renamed from: d */
        public static C0819b m2624d(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0819b) MessageNano.mergeFrom(new C0819b(), bArr);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$c */
    public static final class C0820c extends MessageNano {
        /* renamed from: cm */
        public C0821d f180cm;
        /* renamed from: cn */
        public C0822e f181cn;
        /* renamed from: co */
        public C0819b f182co;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                MessageNano messageNano;
                if (readTag == 10) {
                    if (this.f180cm == null) {
                        this.f180cm = new C0821d();
                    }
                    messageNano = this.f180cm;
                } else if (readTag == 18) {
                    if (this.f181cn == null) {
                        this.f181cn = new C0822e();
                    }
                    messageNano = this.f181cn;
                } else if (readTag == 26) {
                    if (this.f182co == null) {
                        this.f182co = new C0819b();
                    }
                    messageNano = this.f182co;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
                codedInputByteBufferNano.readMessage(messageNano);
            }
        }

        public C0820c() {
            this.f180cm = null;
            this.f181cn = null;
            this.f182co = null;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.f180cm != null) {
                codedOutputByteBufferNano.writeMessage(1, this.f180cm);
            }
            if (this.f181cn != null) {
                codedOutputByteBufferNano.writeMessage(2, this.f181cn);
            }
            if (this.f182co != null) {
                codedOutputByteBufferNano.writeMessage(3, this.f182co);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.f180cm != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, this.f180cm);
            }
            if (this.f181cn != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(2, this.f181cn);
            }
            return this.f182co != null ? computeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(3, this.f182co) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$d */
    public static final class C0821d extends MessageNano {
        /* renamed from: bD */
        public int[] f183bD;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                int i;
                int i2;
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 16) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 16);
                    int[] iArr = new int[readTag];
                    i = 0;
                    for (i2 = 0; i2 < readTag; i2++) {
                        if (i2 != 0) {
                            codedInputByteBufferNano.readTag();
                        }
                        int readInt32 = codedInputByteBufferNano.readInt32();
                        switch (readInt32) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                                int i3 = i + 1;
                                iArr[i] = readInt32;
                                i = i3;
                                break;
                        }
                    }
                    if (i != 0) {
                        i2 = this.f183bD == null ? 0 : this.f183bD.length;
                        if (i2 == 0 && i == readTag) {
                            this.f183bD = iArr;
                        } else {
                            int[] iArr2 = new int[(i2 + i)];
                            if (i2 != 0) {
                                System.arraycopy(this.f183bD, 0, iArr2, 0, i2);
                            }
                            System.arraycopy(iArr, 0, iArr2, i2, i);
                            this.f183bD = iArr2;
                        }
                    }
                } else if (readTag == 18) {
                    readTag = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        switch (codedInputByteBufferNano.readInt32()) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                                i2++;
                                break;
                        }
                    }
                    if (i2 != 0) {
                        codedInputByteBufferNano.rewindToPosition(position);
                        position = this.f183bD == null ? 0 : this.f183bD.length;
                        int[] iArr3 = new int[(i2 + position)];
                        if (position != 0) {
                            System.arraycopy(this.f183bD, 0, iArr3, 0, position);
                        }
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            int readInt322 = codedInputByteBufferNano.readInt32();
                            switch (readInt322) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                    i = position + 1;
                                    iArr3[position] = readInt322;
                                    position = i;
                                    break;
                            }
                        }
                        this.f183bD = iArr3;
                    }
                    codedInputByteBufferNano.popLimit(readTag);
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0821d() {
            this.time = 0;
            this.f183bD = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (this.f183bD != null && this.f183bD.length > 0) {
                for (int writeInt32 : this.f183bD) {
                    codedOutputByteBufferNano.writeInt32(2, writeInt32);
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            if (this.f183bD == null || this.f183bD.length <= 0) {
                return computeSerializedSize;
            }
            int i = 0;
            for (int computeInt32SizeNoTag : this.f183bD) {
                i += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag);
            }
            return (computeSerializedSize + i) + (this.f183bD.length * 1);
        }

        /* renamed from: e */
        public static C0821d m2627e(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0821d) MessageNano.mergeFrom(new C0821d(), bArr);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$e */
    public static final class C0822e extends MessageNano {
        /* renamed from: cp */
        public String f184cp;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 18) {
                    this.f184cp = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0822e() {
            this.time = 0;
            this.f184cp = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (!this.f184cp.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.f184cp);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            return !this.f184cp.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(2, this.f184cp) : computeSerializedSize;
        }

        /* renamed from: f */
        public static C0822e m2629f(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0822e) MessageNano.mergeFrom(new C0822e(), bArr);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.b$f */
    public static final class C0823f extends MessageNano {
        /* renamed from: br */
        public long f185br;
        /* renamed from: bs */
        public long f186bs;
        /* renamed from: bt */
        public String f187bt;
        /* renamed from: bu */
        public long f188bu;
        public long timestamp;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.timestamp = codedInputByteBufferNano.readInt64();
                } else if (readTag == 16) {
                    this.f185br = codedInputByteBufferNano.readInt64();
                } else if (readTag == 24) {
                    this.f186bs = codedInputByteBufferNano.readInt64();
                } else if (readTag == 34) {
                    this.f187bt = codedInputByteBufferNano.readString();
                } else if (readTag == 40) {
                    this.f188bu = codedInputByteBufferNano.readInt64();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0823f() {
            this.timestamp = 0;
            this.f185br = 0;
            this.f186bs = 0;
            this.f187bt = BuildConfig.FLAVOR;
            this.f188bu = 0;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.timestamp != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.timestamp);
            }
            if (this.f185br != 0) {
                codedOutputByteBufferNano.writeInt64(2, this.f185br);
            }
            if (this.f186bs != 0) {
                codedOutputByteBufferNano.writeInt64(3, this.f186bs);
            }
            if (!this.f187bt.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(4, this.f187bt);
            }
            if (this.f188bu != 0) {
                codedOutputByteBufferNano.writeInt64(5, this.f188bu);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.timestamp != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.timestamp);
            }
            if (this.f185br != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(2, this.f185br);
            }
            if (this.f186bs != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(3, this.f186bs);
            }
            if (!this.f187bt.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f187bt);
            }
            return this.f188bu != 0 ? computeSerializedSize + CodedOutputByteBufferNano.computeInt64Size(5, this.f188bu) : computeSerializedSize;
        }
    }
}
