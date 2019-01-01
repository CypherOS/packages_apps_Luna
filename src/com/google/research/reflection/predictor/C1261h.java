package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0946e;
import com.google.research.reflection.p016a.C0947f;
import com.google.research.reflection.predictor.C0972k.C0971a;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.google.research.reflection.predictor.h */
public class C1261h extends C0968g {
    /* renamed from: fH */
    Map<String, ReflectionEvent> f605fH = new HashMap();
    /* renamed from: fI */
    private List<String> f606fI = new ArrayList();

    /* renamed from: com.google.research.reflection.predictor.h$1 */
    class C09691 implements Comparator<String> {
        C09691() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return -Long.compare(((ReflectionEvent) C1261h.this.f605fH.get((String) obj)).mo9282D().getTimestamp(), ((ReflectionEvent) C1261h.this.f605fH.get((String) obj2)).mo9282D().getTimestamp());
        }
    }

    public final String getName() {
        return "recency_event_predictor";
    }

    /* renamed from: at */
    private void m5067at() {
        this.f606fI.clear();
        this.f606fI.addAll(this.f605fH.keySet());
        Collections.sort(this.f606fI, new C09691());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public final C0972k mo9273j(ReflectionEvent reflectionEvent) {
        boolean z;
        C0972k c0972k = new C0972k();
        int i = 0;
        if (!this.f606fI.isEmpty()) {
            if (!this.f605fH.isEmpty()) {
                if (C0946e.m2988a((ReflectionEvent) this.f605fH.get(this.f606fI.get(0)), reflectionEvent) <= 21600000) {
                    z = true;
                    if (z) {
                        return c0972k;
                    }
                    for (String str : this.f606fI) {
                        if (C0946e.m2988a((ReflectionEvent) this.f605fH.get(str), reflectionEvent) > 21600000) {
                            break;
                        }
                        i++;
                        c0972k.f367fR.add(new C0971a(str, 1.0f / ((float) i), "recency_event_predictor"));
                    }
                    return c0972k;
                }
            }
        }
        z = false;
        if (z) {
        }
    }

    /* renamed from: k */
    public final boolean mo9274k(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9281C() != ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9281C() != ReflectionEventType.INSTANT_APP_USAGE) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    public final C0972k mo9272h(ReflectionEvent reflectionEvent) {
        if (!this.f606fI.isEmpty()) {
            for (int size = this.f606fI.size() - 1; size >= 0; size--) {
                String str = (String) this.f606fI.get(size);
                if (C0946e.m2988a((ReflectionEvent) this.f605fH.get(str), reflectionEvent) <= 21600000) {
                    break;
                }
                this.f605fH.remove(str);
            }
        }
        this.f605fH.put(reflectionEvent.getId(), reflectionEvent);
        m5067at();
        return new C0972k();
    }

    /* renamed from: a */
    public final void mo9267a(Integer num, Integer num2, String str) {
        this.f605fH.remove(str);
        this.f606fI.remove(str);
    }

    /* renamed from: b */
    public final void mo9269b(DataOutputStream dataOutputStream) throws IOException {
        Map<String, ReflectionEvent> map = this.f605fH;
        dataOutputStream.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            C0947f.m2992a(dataOutputStream, entry.getKey());
            byte[] G = ((ReflectionEvent) entry.getValue()).mo9285G();
            dataOutputStream.writeInt(G.length);
            dataOutputStream.write(G);
        }
    }

    /* renamed from: a */
    public final void mo9266a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException {
        int readInt = dataInputStream.readInt();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            String str = (String) C0947f.m2990a(dataInputStream, String.class);
            int readInt2 = dataInputStream.readInt();
            byte[] bArr = new byte[readInt2];
            dataInputStream.read(bArr, 0, readInt2);
            hashMap.put(str, reflectionEvent.mo9289a(bArr, readInt2));
        }
        this.f605fH = hashMap;
        m5067at();
    }
}
