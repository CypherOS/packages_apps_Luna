package com.android.launcher3.reflection.signal;

import android.util.Log;
import com.android.launcher3.reflection.C0832f;
import com.android.launcher3.reflection.p012c.p013a.C0801a.C0802a;
import com.android.launcher3.reflection.p012c.p013a.C0801a.C0806e;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.android.launcher3.reflection.research.predictor.C0961b;
import com.android.launcher3.reflection.research.signal.C0970b;
import com.android.launcher3.reflection.research.signal.C0972d;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace;
import com.android.launcher3.reflection.research.signal.ReflectionPrivatePlace.Alias;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LegacyEventProtoUtils {
    static final EventTimestampComparator COMPARATOR = new EventTimestampComparator();

    static class EventTimestampComparator implements Comparator<C1214a> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            C1214a c1214a = (C1214a) obj;
            C1214a c1214a2 = (C1214a) obj2;
            if (c1214a.mo9264D().getTimestamp() == c1214a2.mo9264D().getTimestamp()) {
                return 0;
            }
            return c1214a2.mo9264D().getTimestamp() > c1214a.mo9264D().getTimestamp() ? -1 : 1;
        }

        EventTimestampComparator() {
        }
    }

    /* renamed from: f */
    public static C0961b m2703f(String str) throws IOException {
        C0961b c0961b = new C0961b();
        if (str != null) {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1)));
            int readInt = dataInputStream.readInt();
            byte[] bArr = null;
            for (int i = 0; i < readInt; i++) {
                C0802a b;
                int readInt2 = dataInputStream.readInt();
                if (bArr == null || bArr.length < readInt2) {
                    bArr = new byte[readInt2];
                }
                dataInputStream.read(bArr, 0, readInt2);
                try {
                    b = C0802a.m2594b(CodedInputByteBufferNano.newInstance(bArr, 0, readInt2));
                } catch (IOException unused) {
                    Log.e("Reflection.loadBuffer", "deserialize event failed!");
                    b = null;
                }
                boolean z = (b == null || b.f137id == null || b.time == 0 || !C0832f.f209ai.matcher(b.f137id).find()) ? false : true;
                if (z) {
                    List<C1214a> a = m2699a(b);
                    for (C1214a c : a) {
                        c0961b.mo9239c(c);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Converted event ");
                    sb.append(b.f137id);
                    sb.append(", ");
                    sb.append(b.type);
                    sb.append(", ");
                    sb.append(b.f133bw);
                    sb.append(" to events (");
                    sb.append(a.size());
                    sb.append("):\n");
                    for (C1214a c1214a : a) {
                        sb.append(c1214a.f464df.f177id);
                        sb.append(", ");
                        sb.append(c1214a.mo9263C());
                        sb.append(", ");
                        sb.append(c1214a.mo9266F());
                        sb.append(", ");
                        sb.append(c1214a.mo9264D().getTimestamp());
                        sb.append(10);
                    }
                    Log.d("Reflection.loadBuffer", sb.toString());
                } else {
                    Log.d("Reflection.loadBuffer", String.format("Event not loaded correctly (id: %s, type %s, eventSrc: %s, time: %d)", new Object[]{b.f137id, b.type, b.f133bw, Long.valueOf(b.time)}));
                }
            }
        }
        return c0961b;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static List<C1214a> m2699a(C0802a c0802a) {
        int i;
        ReflectionEventType reflectionEventType;
        ArrayList arrayList = new ArrayList();
        C1214a c1214a = new C1214a();
        c1214a.mo14095g(c0802a.f137id);
        String str = c0802a.type;
        switch (str.hashCode()) {
            case 704091517:
                if (str.equals("app_install")) {
                    i = 0;
                    break;
                }
            case 795320962:
                if (str.equals("headset")) {
                    i = 3;
                    break;
                }
            case 894073423:
                if (str.equals("deep_link_usage")) {
                    i = 4;
                    break;
                }
            case 1066479505:
                if (str.equals("app_launch")) {
                    i = 1;
                    break;
                }
            case 1844346371:
                if (str.equals("app_usage")) {
                    i = 2;
                    break;
                }
        }
        i = -1;
        switch (i) {
            case 0:
                reflectionEventType = ReflectionEventType.APP_INSTALL;
                break;
            case 1:
            case 2:
                break;
            case 3:
                reflectionEventType = ReflectionEventType.HEADSET;
                break;
            case 4:
                reflectionEventType = ReflectionEventType.SHORTCUTS;
                break;
            default:
                Log.e("Reflection.loadBuffer", String.format("Unknown event type %s, default to app usage.", new Object[]{str}));
                break;
        }
        reflectionEventType = ReflectionEventType.APP_USAGE;
        c1214a.mo9269a(reflectionEventType);
        c1214a.f464df.duration = c0802a.duration;
        c1214a.f464df.f171bq = c0802a.f127bq;
        C1219f c1219f = new C1219f();
        c1219f.mo14098e(c0802a.time);
        c1219f.mo14099f(c0802a.f128br);
        c1219f.mo14101h(c0802a.f131bu);
        c1219f.mo14100g(c0802a.f129bs);
        c1219f.mo14102i(c0802a.f130bt);
        c1214a.mo9270a((C0972d) c1219f);
        c1214a.mo14092a((C0970b) m2702d(c0802a));
        if (c0802a.f133bw != null) {
            c1214a.mo14094f(Arrays.asList(c0802a.f133bw));
        }
        arrayList.add(c1214a);
        arrayList.addAll(m2700b(c0802a));
        arrayList.addAll(m2701c(c0802a));
        Collections.sort(arrayList, COMPARATOR);
        return arrayList;
    }

    /* renamed from: b */
    private static List<C1214a> m2700b(C0802a c0802a) {
        ArrayList arrayList = new ArrayList();
        if (c0802a.f132bv != null) {
            for (C0806e c0806e : c0802a.f132bv) {
                if (c0806e.type.equals("headset")) {
                    C1214a c1214a = new C1214a();
                    c1214a.mo9269a(ReflectionEventType.HEADSET);
                    c1214a.mo14095g(c0806e.f146id);
                    c1214a.mo9270a(new C1219f().mo14098e(c0806e.time));
                    arrayList.add(c1214a);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private static List<C1214a> m2701c(C0802a c0802a) {
        ArrayList arrayList = new ArrayList();
        if (c0802a.f132bv != null) {
            for (C0806e c0806e : c0802a.f132bv) {
                if (c0806e.type.equals("app_usage")) {
                    C1214a c1214a = new C1214a();
                    c1214a.mo9269a(ReflectionEventType.APP_USAGE);
                    c1214a.mo14095g(c0806e.f146id);
                    c1214a.mo9270a(new C1219f().mo14098e(c0806e.time));
                    arrayList.add(c1214a);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    private static C1216c m2702d(C0802a c0802a) {
        C1216c c1216c = new C1216c();
        if (c0802a.f132bv != null) {
            for (C0806e c0806e : c0802a.f132bv) {
                if (c0806e.type.equals("semantic_place") && c0806e.f143bF.length > 0) {
                    c1216c.mo9282a((ReflectionPrivatePlace) m2698a(c0806e));
                }
                c0806e.type.equals("lat_long");
            }
        }
        return c1216c;
    }

    public static C1217d m2698a(C0806e c0806e) {
        int i;
        Object obj;
        List singletonList;
        Long valueOf;
        C1217d c1217d = new C1217d();
        String str = c0806e.f143bF[0];
        int hashCode = str.hashCode();
        if (hashCode != 2255103) {
            if (hashCode == 2702129) {
                if (str.equals("Work")) {
                    i = 1;
                    switch (i) {
                        case 0:
                            obj = Alias.HOME;
                            break;
                        case 1:
                            obj = Alias.WORK;
                            break;
                        default:
                            obj = null;
                            break;
                    }
                    singletonList = Collections.singletonList(obj);
                    if (singletonList != null) {
                        c1217d.f467cm.f183bD = new int[singletonList.size()];
                        for (int i2 = 0; i2 < singletonList.size(); i2++) {
                            c1217d.f467cm.f183bD[i2] = ((Alias) singletonList.get(i2)).ordinal();
                        }
                    }
                    valueOf = Long.valueOf(c0806e.time);
                    c1217d.f467cm.time = valueOf.longValue();
                    return c1217d;
                }
            }
        } else if (str.equals("Home")) {
            i = 0;
            switch (i) {
                case 0:
                    break;
                case 1:
                    break;
            }
            singletonList = Collections.singletonList(obj);
            if (singletonList != null) {
            }
            valueOf = Long.valueOf(c0806e.time);
            c1217d.f467cm.time = valueOf.longValue();
            return c1217d;
        }
        i = -1;
        switch (i) {
            case 0:
                break;
            case 1:
                break;
        }
        singletonList = Collections.singletonList(obj);
        if (singletonList != null) {
        }
        valueOf = Long.valueOf(c0806e.time);
        c1217d.f467cm.time = valueOf.longValue();
        return c1217d;
    }
}
