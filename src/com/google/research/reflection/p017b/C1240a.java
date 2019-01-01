package com.google.research.reflection.p017b;

import android.support.p003v7.widget.RecyclerView;
import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0945d;
import com.google.research.reflection.p016a.C0946e;
import com.google.research.reflection.p016a.C0947f;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
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

/* renamed from: com.google.research.reflection.b.a */
public class C1240a extends C0949f {
    /* renamed from: dr */
    protected HashMap<String, Integer> f534dr;
    /* renamed from: ds */
    protected HashMap<Integer, Long> f535ds;
    /* renamed from: dt */
    protected boolean[] f536dt;
    /* renamed from: du */
    protected int f537du;
    /* renamed from: dv */
    protected long f538dv;
    /* renamed from: dw */
    protected long f539dw;
    /* renamed from: dx */
    protected int f540dx;

    public String getFeatureName() {
        return "local_app_launch_history";
    }

    public C1240a() {
        this.f534dr = new HashMap();
        this.f535ds = new HashMap();
        this.f537du = 200;
        this.f538dv = 600000;
        this.f539dw = 0;
        this.f540dx = 2;
        this.f536dt = new boolean[this.f537du];
    }

    public C1240a(int i) {
        this.f534dr = new HashMap();
        this.f535ds = new HashMap();
        this.f537du = 200;
        this.f538dv = 600000;
        this.f539dw = 0;
        this.f540dx = 2;
        this.f537du = i;
        this.f536dt = new boolean[this.f537du];
    }

    public C1240a(int i, long j, long j2, int i2) {
        this.f534dr = new HashMap();
        this.f535ds = new HashMap();
        this.f537du = 200;
        this.f538dv = 600000;
        this.f539dw = 0;
        this.f540dx = 2;
        this.f540dx = i2;
        this.f538dv = j;
        this.f539dw = j2;
        this.f537du = i;
        this.f536dt = new boolean[this.f537du];
    }

    /* renamed from: S */
    public C1240a clone() {
        ? c1240a = new C1240a(this.f537du, this.f538dv, this.f539dw, this.f540dx);
        c1240a.mo14330a(this);
        return c1240a;
    }

    /* renamed from: a */
    public final void mo14330a(C1240a c1240a) {
        for (Entry entry : c1240a.f534dr.entrySet()) {
            this.f534dr.put((String) entry.getKey(), (Integer) entry.getValue());
        }
        for (Entry entry2 : c1240a.f535ds.entrySet()) {
            this.f535ds.put((Integer) entry2.getKey(), (Long) entry2.getValue());
        }
        this.f536dt = Arrays.copyOf(c1240a.f536dt, c1240a.f536dt.length);
        this.f281dA = c1240a.f281dA;
    }

    /* renamed from: a */
    protected final int mo14328a(String str, long j) {
        Integer num = (Integer) this.f534dr.get(str);
        if (num == null) {
            if (this.f534dr.size() == this.f537du) {
                long j2 = RecyclerView.FOREVER_NS;
                String str2 = null;
                for (Entry entry : this.f534dr.entrySet()) {
                    long longValue = ((Long) this.f535ds.get(entry.getValue())).longValue();
                    if (longValue < j2) {
                        str2 = (String) entry.getKey();
                        j2 = longValue;
                    }
                }
                num = (Integer) this.f534dr.get(str2);
                mo9209g(Arrays.asList(new String[]{str2}));
                this.f536dt[num.intValue()] = true;
            } else {
                for (int i = 0; i < this.f536dt.length; i++) {
                    if (!this.f536dt[i]) {
                        num = Integer.valueOf(i);
                        this.f536dt[i] = true;
                        break;
                    }
                }
            }
            this.f534dr.put(str, num);
        }
        this.f535ds.put(num, Long.valueOf(j));
        return num.intValue();
    }

    /* renamed from: g */
    public final void mo9209g(List<String> list) {
        if (!list.isEmpty()) {
            Integer num = (Integer) this.f534dr.remove((String) list.get(0));
            if (num != null) {
                this.f535ds.remove(num);
                this.f536dt[num.intValue()] = false;
                if (this.f281dA != null) {
                    this.f281dA.mo9211a(this, num.intValue());
                }
            }
        }
    }

    /* renamed from: f */
    protected boolean mo14331f(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9281C() == ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9284F().size() > 0) {
                return "GEL".equals(reflectionEvent.mo9284F().get(0));
            }
        }
        return false;
    }

    /* renamed from: a */
    protected ArrayList<C0945d> mo14329a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent, long j, long j2, int i) {
        ArrayList<C0945d> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (int i2 = c0942a.f274dk - 1; i2 >= 0; i2--) {
            ReflectionEvent reflectionEvent2 = (ReflectionEvent) c0942a.mo9187a(i2);
            if (mo14331f(reflectionEvent2)) {
                long a = C0946e.m2988a(reflectionEvent2, reflectionEvent) - reflectionEvent2.getDuration();
                if (a >= j) {
                    break;
                } else if (a >= j2) {
                    int a2 = mo14328a(reflectionEvent2.getId(), reflectionEvent.mo9282D().getTimestamp());
                    C0945d c0945d = (C0945d) hashMap.get(Integer.valueOf(a2));
                    if (c0945d == null) {
                        if (hashMap.size() >= i) {
                            break;
                        }
                        c0945d = new C0945d(a2);
                        hashMap.put(Integer.valueOf(a2), c0945d);
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
    public final int mo9200T() {
        return this.f537du;
    }

    /* renamed from: a */
    public final void mo9204a(DataInputStream dataInputStream) throws IOException {
        this.f537du = dataInputStream.readInt();
        this.f540dx = dataInputStream.readInt();
        this.f538dv = dataInputStream.readLong();
        this.f539dw = dataInputStream.readLong();
        this.f534dr = C0947f.m2991a(dataInputStream, String.class, Integer.class);
        this.f535ds = C0947f.m2991a(dataInputStream, Integer.class, Long.class);
        this.f536dt = new boolean[this.f537du];
        for (Integer intValue : this.f534dr.values()) {
            this.f536dt[intValue.intValue()] = true;
        }
    }

    /* renamed from: a */
    public final void mo9205a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f537du);
        dataOutputStream.writeInt(this.f540dx);
        dataOutputStream.writeLong(this.f538dv);
        dataOutputStream.writeLong(this.f539dw);
        C0947f.m2993a(dataOutputStream, (Map) this.f534dr);
        C0947f.m2993a(dataOutputStream, (Map) this.f535ds);
    }

    /* renamed from: a */
    protected final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        ArrayList a = mo14329a(c0942a, reflectionEvent, this.f538dv, this.f539dw, this.f540dx);
        C0955e c0955e = new C0955e(1, this.f537du);
        Iterator it = a.iterator();
        while (it.hasNext()) {
            C0945d c0945d = (C0945d) it.next();
            if (c0945d.value > 0.0f) {
                if (c0945d.index < this.f537du) {
                    c0955e.f322eR[c0945d.index] = 1.0d;
                } else {
                    int i = c0945d.index;
                    StringBuilder sb = new StringBuilder(26);
                    sb.append("invalid index: ");
                    sb.append(i);
                    throw new RuntimeException(sb.toString());
                }
            }
        }
        return c0955e;
    }
}
