package com.google.android.apps.nexuslauncher.reflection.p015e;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c */
public interface C0824c {

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$a */
    public static final class C0825a extends MessageNano {
        /* renamed from: cq */
        private static volatile C0825a[] f189cq;
        public int key;
        public long value;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.key = codedInputByteBufferNano.readInt32();
                } else if (readTag == 16) {
                    this.value = codedInputByteBufferNano.readInt64();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: w */
        public static C0825a[] m2632w() {
            if (f189cq == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f189cq == null) {
                        f189cq = new C0825a[0];
                    }
                }
            }
            return f189cq;
        }

        public C0825a() {
            this.key = 0;
            this.value = 0;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.key != 0) {
                codedOutputByteBufferNano.writeInt32(1, this.key);
            }
            if (this.value != 0) {
                codedOutputByteBufferNano.writeInt64(2, this.value);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.key != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.key);
            }
            return this.value != 0 ? computeSerializedSize + CodedOutputByteBufferNano.computeInt64Size(2, this.value) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$b */
    public static final class C0826b extends MessageNano {
        /* renamed from: cr */
        private static volatile C0826b[] f190cr;
        /* renamed from: cs */
        public C0828d f191cs;
        /* renamed from: ct */
        public int f192ct;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    if (this.f191cs == null) {
                        this.f191cs = new C0828d();
                    }
                    codedInputByteBufferNano.readMessage(this.f191cs);
                } else if (readTag == 16) {
                    this.f192ct = codedInputByteBufferNano.readInt32();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: x */
        public static C0826b[] m2634x() {
            if (f190cr == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f190cr == null) {
                        f190cr = new C0826b[0];
                    }
                }
            }
            return f190cr;
        }

        public C0826b() {
            this.f191cs = null;
            this.f192ct = 0;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.f191cs != null) {
                codedOutputByteBufferNano.writeMessage(1, this.f191cs);
            }
            if (this.f192ct != 0) {
                codedOutputByteBufferNano.writeInt32(2, this.f192ct);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.f191cs != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, this.f191cs);
            }
            return this.f192ct != 0 ? computeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(2, this.f192ct) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$c */
    public static final class C0827c extends MessageNano {
        /* renamed from: cu */
        public String f193cu;
        /* renamed from: cv */
        public C0829e[] f194cv;
        public long timestamp;
        public int version;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.timestamp = codedInputByteBufferNano.readInt64();
                } else if (readTag == 16) {
                    this.version = codedInputByteBufferNano.readInt32();
                } else if (readTag == 26) {
                    this.f193cu = codedInputByteBufferNano.readString();
                } else if (readTag == 34) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                    int length = this.f194cv == null ? 0 : this.f194cv.length;
                    C0829e[] c0829eArr = new C0829e[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f194cv, 0, c0829eArr, 0, length);
                    }
                    while (length < c0829eArr.length - 1) {
                        c0829eArr[length] = new C0829e();
                        codedInputByteBufferNano.readMessage(c0829eArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0829eArr[length] = new C0829e();
                    codedInputByteBufferNano.readMessage(c0829eArr[length]);
                    this.f194cv = c0829eArr;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0827c() {
            this.timestamp = 0;
            this.version = 0;
            this.f193cu = BuildConfig.FLAVOR;
            this.f194cv = C0829e.m2639y();
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.timestamp != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.timestamp);
            }
            if (this.version != 0) {
                codedOutputByteBufferNano.writeInt32(2, this.version);
            }
            if (!this.f193cu.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(3, this.f193cu);
            }
            if (this.f194cv != null && this.f194cv.length > 0) {
                for (MessageNano messageNano : this.f194cv) {
                    if (messageNano != null) {
                        codedOutputByteBufferNano.writeMessage(4, messageNano);
                    }
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.timestamp != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.timestamp);
            }
            if (this.version != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, this.version);
            }
            if (!this.f193cu.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f193cu);
            }
            if (this.f194cv != null && this.f194cv.length > 0) {
                for (MessageNano messageNano : this.f194cv) {
                    if (messageNano != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(4, messageNano);
                    }
                }
            }
            return computeSerializedSize;
        }

        /* renamed from: g */
        public static C0827c m2636g(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0827c) MessageNano.mergeFrom(new C0827c(), bArr);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$d */
    public static final class C0828d extends MessageNano {
        /* renamed from: cA */
        public C0830f[] f195cA;
        /* renamed from: cB */
        public C0825a[] f196cB;
        /* renamed from: cC */
        public C0825a[] f197cC;
        /* renamed from: cD */
        public C0825a[] f198cD;
        /* renamed from: cw */
        public String f199cw;
        /* renamed from: cx */
        public byte[] f200cx;
        /* renamed from: cy */
        public int f201cy;
        /* renamed from: cz */
        public int f202cz;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                int length;
                C0825a[] c0825aArr;
                if (readTag == 10) {
                    this.f199cw = codedInputByteBufferNano.readString();
                } else if (readTag == 18) {
                    this.f200cx = codedInputByteBufferNano.readBytes();
                } else if (readTag == 24) {
                    this.f201cy = codedInputByteBufferNano.readInt32();
                } else if (readTag == 32) {
                    this.f202cz = codedInputByteBufferNano.readInt32();
                } else if (readTag == 42) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 42);
                    length = this.f195cA == null ? 0 : this.f195cA.length;
                    C0830f[] c0830fArr = new C0830f[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f195cA, 0, c0830fArr, 0, length);
                    }
                    while (length < c0830fArr.length - 1) {
                        c0830fArr[length] = new C0830f();
                        codedInputByteBufferNano.readMessage(c0830fArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0830fArr[length] = new C0830f();
                    codedInputByteBufferNano.readMessage(c0830fArr[length]);
                    this.f195cA = c0830fArr;
                } else if (readTag == 50) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 50);
                    length = this.f196cB == null ? 0 : this.f196cB.length;
                    c0825aArr = new C0825a[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f196cB, 0, c0825aArr, 0, length);
                    }
                    while (length < c0825aArr.length - 1) {
                        c0825aArr[length] = new C0825a();
                        codedInputByteBufferNano.readMessage(c0825aArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0825aArr[length] = new C0825a();
                    codedInputByteBufferNano.readMessage(c0825aArr[length]);
                    this.f196cB = c0825aArr;
                } else if (readTag == 58) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 58);
                    length = this.f197cC == null ? 0 : this.f197cC.length;
                    c0825aArr = new C0825a[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f197cC, 0, c0825aArr, 0, length);
                    }
                    while (length < c0825aArr.length - 1) {
                        c0825aArr[length] = new C0825a();
                        codedInputByteBufferNano.readMessage(c0825aArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0825aArr[length] = new C0825a();
                    codedInputByteBufferNano.readMessage(c0825aArr[length]);
                    this.f197cC = c0825aArr;
                } else if (readTag == 66) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 66);
                    length = this.f198cD == null ? 0 : this.f198cD.length;
                    c0825aArr = new C0825a[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f198cD, 0, c0825aArr, 0, length);
                    }
                    while (length < c0825aArr.length - 1) {
                        c0825aArr[length] = new C0825a();
                        codedInputByteBufferNano.readMessage(c0825aArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0825aArr[length] = new C0825a();
                    codedInputByteBufferNano.readMessage(c0825aArr[length]);
                    this.f198cD = c0825aArr;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0828d() {
            this.f199cw = BuildConfig.FLAVOR;
            this.f200cx = WireFormatNano.EMPTY_BYTES;
            this.f201cy = 0;
            this.f202cz = 0;
            this.f195cA = C0830f.m2641z();
            this.f196cB = C0825a.m2632w();
            this.f197cC = C0825a.m2632w();
            this.f198cD = C0825a.m2632w();
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f199cw.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f199cw);
            }
            if (!Arrays.equals(this.f200cx, WireFormatNano.EMPTY_BYTES)) {
                codedOutputByteBufferNano.writeBytes(2, this.f200cx);
            }
            if (this.f201cy != 0) {
                codedOutputByteBufferNano.writeInt32(3, this.f201cy);
            }
            if (this.f202cz != 0) {
                codedOutputByteBufferNano.writeInt32(4, this.f202cz);
            }
            if (this.f195cA != null && this.f195cA.length > 0) {
                for (MessageNano messageNano : this.f195cA) {
                    if (messageNano != null) {
                        codedOutputByteBufferNano.writeMessage(5, messageNano);
                    }
                }
            }
            if (this.f196cB != null && this.f196cB.length > 0) {
                for (MessageNano messageNano2 : this.f196cB) {
                    if (messageNano2 != null) {
                        codedOutputByteBufferNano.writeMessage(6, messageNano2);
                    }
                }
            }
            if (this.f197cC != null && this.f197cC.length > 0) {
                for (MessageNano messageNano22 : this.f197cC) {
                    if (messageNano22 != null) {
                        codedOutputByteBufferNano.writeMessage(7, messageNano22);
                    }
                }
            }
            if (this.f198cD != null && this.f198cD.length > 0) {
                for (MessageNano messageNano3 : this.f198cD) {
                    if (messageNano3 != null) {
                        codedOutputByteBufferNano.writeMessage(8, messageNano3);
                    }
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int i;
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f199cw.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f199cw);
            }
            if (!Arrays.equals(this.f200cx, WireFormatNano.EMPTY_BYTES)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeBytesSize(2, this.f200cx);
            }
            if (this.f201cy != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, this.f201cy);
            }
            if (this.f202cz != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, this.f202cz);
            }
            if (this.f195cA != null && this.f195cA.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano : this.f195cA) {
                    if (messageNano != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(5, messageNano);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f196cB != null && this.f196cB.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano2 : this.f196cB) {
                    if (messageNano2 != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(6, messageNano2);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f197cC != null && this.f197cC.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano22 : this.f197cC) {
                    if (messageNano22 != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(7, messageNano22);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f198cD != null && this.f198cD.length > 0) {
                for (MessageNano messageNano3 : this.f198cD) {
                    if (messageNano3 != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(8, messageNano3);
                    }
                }
            }
            return computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$e */
    public static final class C0829e extends MessageNano {
        /* renamed from: cE */
        private static volatile C0829e[] f203cE;
        /* renamed from: bp */
        public String f204bp;
        /* renamed from: cF */
        public C0826b[] f205cF;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    this.f204bp = codedInputByteBufferNano.readString();
                } else if (readTag == 18) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                    int length = this.f205cF == null ? 0 : this.f205cF.length;
                    C0826b[] c0826bArr = new C0826b[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f205cF, 0, c0826bArr, 0, length);
                    }
                    while (length < c0826bArr.length - 1) {
                        c0826bArr[length] = new C0826b();
                        codedInputByteBufferNano.readMessage(c0826bArr[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    c0826bArr[length] = new C0826b();
                    codedInputByteBufferNano.readMessage(c0826bArr[length]);
                    this.f205cF = c0826bArr;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: y */
        public static C0829e[] m2639y() {
            if (f203cE == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f203cE == null) {
                        f203cE = new C0829e[0];
                    }
                }
            }
            return f203cE;
        }

        public C0829e() {
            this.f204bp = BuildConfig.FLAVOR;
            this.f205cF = C0826b.m2634x();
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f204bp.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f204bp);
            }
            if (this.f205cF != null && this.f205cF.length > 0) {
                for (MessageNano messageNano : this.f205cF) {
                    if (messageNano != null) {
                        codedOutputByteBufferNano.writeMessage(2, messageNano);
                    }
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f204bp.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f204bp);
            }
            if (this.f205cF != null && this.f205cF.length > 0) {
                for (MessageNano messageNano : this.f205cF) {
                    if (messageNano != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(2, messageNano);
                    }
                }
            }
            return computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.e.c$f */
    public static final class C0830f extends MessageNano {
        /* renamed from: cG */
        private static volatile C0830f[] f206cG;
        /* renamed from: id */
        public int f207id;
        public String name;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.f207id = codedInputByteBufferNano.readInt32();
                } else if (readTag == 18) {
                    this.name = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        /* renamed from: z */
        public static C0830f[] m2641z() {
            if (f206cG == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f206cG == null) {
                        f206cG = new C0830f[0];
                    }
                }
            }
            return f206cG;
        }

        public C0830f() {
            this.f207id = 0;
            this.name = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.f207id != 0) {
                codedOutputByteBufferNano.writeInt32(1, this.f207id);
            }
            if (!this.name.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.name);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.f207id != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.f207id);
            }
            return !this.name.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(2, this.name) : computeSerializedSize;
        }
    }
}
