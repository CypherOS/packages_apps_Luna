package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0942e;
import com.android.launcher3.reflection.research.p016a.C0943f;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
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

/* renamed from: com.android.launcher3.reflection.research.predictor.h */
public class C1257h extends C0964g {
    /* renamed from: fH */
    Map<String, ReflectionEvent> f606fH = new HashMap();
    /* renamed from: fI */
    private List<String> f607fI = new ArrayList();

    /* renamed from: com.android.launcher3.reflection.research.predictor.h$1 */
    class C09651 implements Comparator<String> {
        C09651() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return -Long.compare(((ReflectionEvent) C1257h.this.f606fH.get((String) obj)).mo9264D().getTimestamp(), ((ReflectionEvent) C1257h.this.f606fH.get((String) obj2)).mo9264D().getTimestamp());
        }
    }

    public final String getName() {
        return "recency_event_predictor";
    }

    /* renamed from: at */
    private void m5045at() {
        this.f607fI.clear();
        this.f607fI.addAll(this.f606fH.keySet());
        Collections.sort(this.f607fI, new C09651());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public final C0968k mo9255j(ReflectionEvent reflectionEvent) {
        boolean z;
        C0968k c0968k = new C0968k();
        int i = 0;
        if (!this.f607fI.isEmpty()) {
            if (!this.f606fH.isEmpty()) {
                if (C0942e.m2973a((ReflectionEvent) this.f606fH.get(this.f607fI.get(0)), reflectionEvent) <= 21600000) {
                    z = true;
                    if (z) {
                        return c0968k;
                    }
                    for (String str : this.f607fI) {
                        if (C0942e.m2973a((ReflectionEvent) this.f606fH.get(str), reflectionEvent) > 21600000) {
                            break;
                        }
                        i++;
                        c0968k.f367fR.add(new C0967a(str, 1.0f / ((float) i), "recency_event_predictor"));
                    }
                    return c0968k;
                }
            }
        }
        z = false;
        if (z) {
        }
    }

    /* renamed from: k */
    public final boolean mo9256k(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9263C() != ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9263C() != ReflectionEventType.INSTANT_APP_USAGE) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    public final C0968k mo9254h(ReflectionEvent reflectionEvent) {
        if (!this.f607fI.isEmpty()) {
            for (int size = this.f607fI.size() - 1; size >= 0; size--) {
                String str = (String) this.f607fI.get(size);
                if (C0942e.m2973a((ReflectionEvent) this.f606fH.get(str), reflectionEvent) <= 21600000) {
                    break;
                }
                this.f606fH.remove(str);
            }
        }
        this.f606fH.put(reflectionEvent.getId(), reflectionEvent);
        m5045at();
        return new C0968k();
    }

    /* renamed from: a */
    public final void mo9249a(Integer num, Integer num2, String str) {
        this.f606fH.remove(str);
        this.f607fI.remove(str);
    }

    /* renamed from: b */
    public final void mo9251b(DataOutputStream dataOutputStream) throws IOException {
        Map<String, ReflectionEvent> map = this.f606fH;
        dataOutputStream.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            C0943f.m2977a(dataOutputStream, entry.getKey());
            byte[] G = ((ReflectionEvent) entry.getValue()).mo9267G();
            dataOutputStream.writeInt(G.length);
            dataOutputStream.write(G);
        }
    }

    /* renamed from: a */
    public final void mo9248a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException {
        int readInt = dataInputStream.readInt();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            String str = (String) C0943f.m2975a(dataInputStream, String.class);
            int readInt2 = dataInputStream.readInt();
            byte[] bArr = new byte[readInt2];
            dataInputStream.read(bArr, 0, readInt2);
            hashMap.put(str, reflectionEvent.mo9271a(bArr, readInt2));
        }
        this.f606fH = hashMap;
        m5045at();
    }
}
