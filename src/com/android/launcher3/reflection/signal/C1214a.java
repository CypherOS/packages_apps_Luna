package com.android.launcher3.reflection.signal;

import android.util.Log;
import com.android.launcher3.reflection.p015e.C0817b.C0818a;
import com.android.launcher3.reflection.p015e.C0817b.C0820c;
import com.android.launcher3.reflection.p015e.C0817b.C0823f;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.android.launcher3.reflection.research.signal.C0970b;
import com.android.launcher3.reflection.research.signal.C0972d;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.signal.a */
public class C1214a implements ReflectionEvent {
    /* renamed from: df */
    public C0818a f464df;

    public C1214a(C0818a c0818a) {
        this.f464df = c0818a;
        mo14094f(Arrays.asList(this.f464df.f172bw));
        if (this.f464df.f174cj == null) {
            this.f464df.f174cj = new C0820c();
        }
        if (this.f464df.f173ci == null) {
            this.f464df.f173ci = new C0823f();
        }
    }

    public C1214a() {
        this.f464df = new C0818a();
        this.f464df.f174cj = new C0820c();
        this.f464df.f173ci = new C0823f();
    }

    public final String getId() {
        return this.f464df.f177id;
    }

    /* renamed from: g */
    public final ReflectionEvent mo14095g(String str) {
        this.f464df.f177id = str;
        return this;
    }

    /* renamed from: C */
    public final ReflectionEventType mo9263C() {
        return ReflectionEventType.values()[this.f464df.type];
    }

    /* renamed from: a */
    public final ReflectionEvent mo9269a(ReflectionEventType reflectionEventType) {
        if (reflectionEventType != null) {
            this.f464df.type = reflectionEventType.ordinal();
        }
        return this;
    }

    public final long getDuration() {
        return this.f464df.duration;
    }

    /* renamed from: D */
    public final C0972d mo9264D() {
        return new C1219f(this.f464df.f173ci);
    }

    /* renamed from: a */
    public final ReflectionEvent mo9270a(C0972d c0972d) {
        this.f464df.f173ci = ((C1219f) c0972d).f469dh;
        return this;
    }

    /* renamed from: E */
    public final C0970b mo9265E() {
        return new C1216c(this.f464df.f174cj);
    }

    /* renamed from: a */
    public final ReflectionEvent mo14092a(C0970b c0970b) {
        this.f464df.f174cj = ((C1216c) c0970b).f466cj;
        return this;
    }

    /* renamed from: F */
    public final List<String> mo9266F() {
        return Arrays.asList(this.f464df.f172bw);
    }

    /* renamed from: f */
    public final ReflectionEvent mo14094f(List<String> list) {
        this.f464df.f172bw = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.f464df.f172bw[i] = (String) list.get(i);
        }
        return this;
    }

    /* renamed from: G */
    public final byte[] mo9267G() {
        if (this.f464df == null) {
            return null;
        }
        return MessageNano.toByteArray(this.f464df);
    }

    /* renamed from: a */
    public final ReflectionEvent mo9271a(byte[] bArr, int i) {
        try {
            return new C1214a(C0818a.m2621f(CodedInputByteBufferNano.newInstance(bArr, 0, i)));
        } catch (IOException unused) {
            Log.e("Reflection", "deserialize event failed!");
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null && this.f464df == null) {
            return true;
        }
        if (obj != null) {
            if (this.f464df != null) {
                if (!(obj instanceof C1214a)) {
                    return false;
                }
                return this.f464df.toString().equals(((C1214a) obj).f464df.toString());
            }
        }
        return false;
    }

    /* renamed from: h */
    public final C1214a mo14096h(String str) {
        this.f464df.f176cl = str;
        return this;
    }

    /* renamed from: H */
    public final String mo9268H() {
        return this.f464df.f176cl;
    }

    /* renamed from: I */
    public final C1214a mo14091I() {
        byte[] G = mo9267G();
        if (G != null) {
            return (C1214a) mo9271a(G, G.length);
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event [id: ");
        sb.append(this.f464df.f177id);
        sb.append(", type: ");
        sb.append(mo9263C());
        sb.append("\n");
        if (mo9264D() != null) {
            sb.append("Timestamp: ");
            sb.append(mo9264D().getTimestamp());
            sb.append(", bootTime: ");
            sb.append(mo9264D().mo9289g());
            sb.append(", elapsedTime: ");
            sb.append(mo9264D().mo9287O());
            sb.append(", timezone: ");
            sb.append(mo9264D().getTimeZone());
            sb.append(", time offset: ");
            sb.append(mo9264D().mo9288P());
            sb.append("\n");
        }
        if (mo9265E() != null) {
            if (mo9265E().mo9281L() != null) {
                sb.append("Latitude: ");
                sb.append(mo9265E().mo9281L().getLatitude());
                sb.append(", Longitude: ");
                sb.append(mo9265E().mo9281L().getLongitude());
                sb.append("\n");
            }
            if (mo9265E().mo9279J() != null) {
                sb.append("Private place alias: ");
                sb.append(mo9265E().mo9279J().mo9274M().get(0));
                sb.append(", time: ");
                sb.append(mo9265E().mo9279J().getTime());
                sb.append("\n");
            }
            if (mo9265E().mo9280K() != null) {
                sb.append("Public place alias: ");
                sb.append(mo9265E().mo9280K().mo9285N());
                sb.append(", time: ");
                sb.append(mo9265E().mo9280K().getTime());
                sb.append("\n");
            }
        }
        sb.append("Event source: ");
        sb.append(mo9266F());
        return sb.toString();
    }
}
