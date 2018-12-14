package com.android.launcher3.reflection.research.p017b;

import android.support.p003v7.widget.RecyclerView;
import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0941d;
import com.android.launcher3.reflection.research.p016a.C0942e;
import com.android.launcher3.reflection.research.p016a.C0943f;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.android.launcher3.reflection.research.b.a */
public class C1236a extends C0945f {
    /* renamed from: dr */
    protected HashMap<String, Integer> f535dr;
    /* renamed from: ds */
    protected HashMap<Integer, Long> f536ds;
    /* renamed from: dt */
    protected boolean[] f537dt;
    /* renamed from: du */
    protected int f538du;
    /* renamed from: dv */
    protected long f539dv;
    /* renamed from: dw */
    protected long f540dw;
    /* renamed from: dx */
    protected int f541dx;

    public String getFeatureName() {
        return "local_app_launch_history";
    }

    public C1236a() {
        this.f535dr = new HashMap();
        this.f536ds = new HashMap();
        this.f538du = 200;
        this.f539dv = 600000;
        this.f540dw = 0;
        this.f541dx = 2;
        this.f537dt = new boolean[this.f538du];
    }

    public C1236a(int i) {
        this.f535dr = new HashMap();
        this.f536ds = new HashMap();
        this.f538du = 200;
        this.f539dv = 600000;
        this.f540dw = 0;
        this.f541dx = 2;
        this.f538du = i;
        this.f537dt = new boolean[this.f538du];
    }

    public C1236a(int i, long j, long j2, int i2) {
        this.f535dr = new HashMap();
        this.f536ds = new HashMap();
        this.f538du = 200;
        this.f539dv = 600000;
        this.f540dw = 0;
        this.f541dx = 2;
        this.f541dx = i2;
        this.f539dv = j;
        this.f540dw = j2;
        this.f538du = i;
        this.f537dt = new boolean[this.f538du];
    }

    /* renamed from: S */
    public C1236a clone() {
        C1236a c1236a = new C1236a(this.f538du, this.f539dv, this.f540dw, this.f541dx);
        c1236a.mo14289a(this);
        return c1236a;
    }

    /* renamed from: a */
    public final void mo14289a(C1236a c1236a) {
        for (Entry entry : c1236a.f535dr.entrySet()) {
            this.f535dr.put((String) entry.getKey(), (Integer) entry.getValue());
        }
        for (Entry entry2 : c1236a.f536ds.entrySet()) {
            this.f536ds.put((Integer) entry2.getKey(), (Long) entry2.getValue());
        }
        this.f537dt = Arrays.copyOf(c1236a.f537dt, c1236a.f537dt.length);
        this.f281dA = c1236a.f281dA;
    }

    /* renamed from: a */
    protected final int mo14287a(String str, long j) {
        Integer num = (Integer) this.f535dr.get(str);
        if (num == null) {
            if (this.f535dr.size() == this.f538du) {
                long j2 = RecyclerView.FOREVER_NS;
                String str2 = null;
                for (Entry entry : this.f535dr.entrySet()) {
                    long longValue = ((Long) this.f536ds.get(entry.getValue())).longValue();
                    if (longValue < j2) {
                        str2 = (String) entry.getKey();
                        j2 = longValue;
                    }
                }
                num = (Integer) this.f535dr.get(str2);
                mo9191g(Arrays.asList(new String[]{str2}));
                this.f537dt[num.intValue()] = true;
            } else {
                for (int i = 0; i < this.f537dt.length; i++) {
                    if (!this.f537dt[i]) {
                        num = Integer.valueOf(i);
                        this.f537dt[i] = true;
                        break;
                    }
                }
            }
            this.f535dr.put(str, num);
        }
        this.f536ds.put(num, Long.valueOf(j));
        return num.intValue();
    }

    /* renamed from: g */
    public final void mo9191g(List<String> list) {
        if (!list.isEmpty()) {
            Integer num = (Integer) this.f535dr.remove((String) list.get(0));
            if (num != null) {
                this.f536ds.remove(num);
                this.f537dt[num.intValue()] = false;
                if (this.f281dA != null) {
                    this.f281dA.mo9193a(this, num.intValue());
                }
            }
        }
    }

    /* renamed from: f */
    protected boolean mo14290f(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9263C() == ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9266F().size() > 0) {
                return "GEL".equals(reflectionEvent.mo9266F().get(0));
            }
        }
        return false;
    }

    /* renamed from: a */
    protected ArrayList<C0941d> mo14288a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent, long j, long j2, int i) {
        ArrayList<C0941d> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (int i2 = c0938a.f274dk - 1; i2 >= 0; i2--) {
            ReflectionEvent reflectionEvent2 = (ReflectionEvent) c0938a.mo9169a(i2);
            if (mo14290f(reflectionEvent2)) {
                long a = C0942e.m2973a(reflectionEvent2, reflectionEvent) - reflectionEvent2.getDuration();
                if (a >= j) {
                    break;
                } else if (a >= j2) {
                    int a2 = mo14287a(reflectionEvent2.getId(), reflectionEvent.mo9264D().getTimestamp());
                    C0941d c0941d = (C0941d) hashMap.get(Integer.valueOf(a2));
                    if (c0941d == null) {
                        if (hashMap.size() >= i) {
                            break;
                        }
                        c0941d = new C0941d(a2);
                        hashMap.put(Integer.valueOf(a2), c0941d);
                    }
                    r4v11.value += 1.0f;
                } else {
                    continue;
                }
            }
        }
        arrayList.addAll(hashMap.values());
        return arrayList;
    }

    /* renamed from: T */
    public final int mo9182T() {
        return this.f538du;
    }

    /* renamed from: a */
    public final void mo9186a(DataInputStream dataInputStream) throws IOException {
        this.f538du = dataInputStream.readInt();
        this.f541dx = dataInputStream.readInt();
        this.f539dv = dataInputStream.readLong();
        this.f540dw = dataInputStream.readLong();
        this.f535dr = C0943f.m2976a(dataInputStream, String.class, Integer.class);
        this.f536ds = C0943f.m2976a(dataInputStream, Integer.class, Long.class);
        this.f537dt = new boolean[this.f538du];
        for (Integer intValue : this.f535dr.values()) {
            this.f537dt[intValue.intValue()] = true;
        }
    }

    /* renamed from: a */
    public final void mo9187a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f538du);
        dataOutputStream.writeInt(this.f541dx);
        dataOutputStream.writeLong(this.f539dv);
        dataOutputStream.writeLong(this.f540dw);
        C0943f.m2978a(dataOutputStream, (Map) this.f535dr);
        C0943f.m2978a(dataOutputStream, (Map) this.f536ds);
    }

    /* renamed from: a */
    protected final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        ArrayList a = mo14288a(c0938a, reflectionEvent, this.f539dv, this.f540dw, this.f541dx);
        C0951e c0951e = new C0951e(1, this.f538du);
        Iterator it = a.iterator();
        while (it.hasNext()) {
            C0941d c0941d = (C0941d) it.next();
            if (c0941d.value > 0.0f) {
                if (c0941d.index < this.f538du) {
                    c0951e.f322eR[c0941d.index] = 1.0d;
                } else {
                    int i = c0941d.index;
                    StringBuilder sb = new StringBuilder(26);
                    sb.append("invalid index: ");
                    sb.append(i);
                    throw new RuntimeException(sb.toString());
                }
            }
        }
        return c0951e;
    }
}
