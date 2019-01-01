package com.google.research.reflection.predictor;

import android.support.p003v7.widget.RecyclerView;
import com.google.research.reflection.predictor.C0972k.C0971a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractEventEstimator extends C0968g {
    /* renamed from: ds */
    protected HashMap<Integer, Long> f598ds = new HashMap();
    /* renamed from: fn */
    protected HashMap<Integer, Integer> f599fn = new HashMap();
    /* renamed from: fo */
    protected HashMap<String, Integer> f600fo = new HashMap();
    /* renamed from: fp */
    protected int f601fp;
    /* renamed from: fq */
    protected HashMap<Integer, Long> f602fq = new HashMap();
    /* renamed from: fr */
    private float[] f603fr = null;
    /* renamed from: fs */
    protected int f604fs = 100;

    public static class PredictorInvalidException extends Exception {
        public PredictorInvalidException(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    protected abstract C0972k mo14342a(float[] fArr, ReflectionEvent reflectionEvent);

    /* renamed from: i */
    protected abstract C0972k mo14352i(ReflectionEvent reflectionEvent);

    /* renamed from: k */
    protected abstract void mo14353k(String str);

    /* renamed from: g */
    public final int mo14351g(ReflectionEvent reflectionEvent) {
        String id = reflectionEvent.getId();
        long timestamp = reflectionEvent.mo9282D().getTimestamp();
        Integer num = (Integer) this.f600fo.get(id);
        if (num == null) {
            num = Integer.valueOf(this.f600fo.size());
            this.f600fo.put(id, num);
            this.f602fq.put(num, Long.valueOf(timestamp));
        }
        return num.intValue();
    }

    /* renamed from: h */
    public final C0972k mo9272h(ReflectionEvent reflectionEvent) {
        Integer num;
        if (!this.f600fo.containsKey(reflectionEvent.getId()) && this.f600fo.size() == this.f604fs) {
            String str;
            long j = RecyclerView.FOREVER_NS;
            Integer num2 = null;
            for (Entry entry : this.f598ds.entrySet()) {
                if (((Long) entry.getValue()).longValue() < j) {
                    Integer num3 = (Integer) entry.getKey();
                    num2 = num3;
                    j = ((Long) entry.getValue()).longValue();
                }
            }
            for (Entry entry2 : this.f600fo.entrySet()) {
                if (((Integer) entry2.getValue()).equals(num2)) {
                    str = (String) entry2.getKey();
                    break;
                }
            }
            str = null;
            if (str != null) {
                try {
                    if (!this.f600fo.isEmpty()) {
                        int size = this.f600fo.size() - 1;
                        num = (Integer) this.f600fo.remove(str);
                        if (this.f600fo.isEmpty()) {
                            throw new PredictorInvalidException("Predictor becomes invalid");
                        } else if (num != null) {
                            this.f601fp -= ((Integer) this.f599fn.remove(num)).intValue();
                            this.f602fq.remove(num);
                            this.f598ds.remove(num);
                            if (size > num.intValue()) {
                                for (Entry entry3 : this.f600fo.entrySet()) {
                                    if (((Integer) entry3.getValue()).intValue() == size) {
                                        entry3.setValue(num);
                                        break;
                                    }
                                }
                                this.f599fn.put(num, Integer.valueOf(((Integer) this.f599fn.remove(Integer.valueOf(size))).intValue()));
                                this.f602fq.put(num, Long.valueOf(((Long) this.f602fq.remove(Integer.valueOf(size))).longValue()));
                                this.f598ds.put(num, Long.valueOf(((Long) this.f598ds.remove(Integer.valueOf(size))).longValue()));
                            }
                            mo9267a(num, Integer.valueOf(size), str);
                        } else {
                            mo9267a(null, null, str);
                        }
                    }
                } catch (PredictorInvalidException unused) {
                }
            }
        }
        int g = mo14351g(reflectionEvent);
        C0972k i = mo14352i(reflectionEvent);
        num = (Integer) this.f599fn.get(Integer.valueOf(g));
        if (num == null) {
            num = Integer.valueOf(0);
        }
        this.f599fn.put(Integer.valueOf(g), Integer.valueOf(num.intValue() + 1));
        this.f601fp++;
        this.f598ds.put(Integer.valueOf(g), Long.valueOf(reflectionEvent.mo9282D().getTimestamp()));
        return i;
    }

    /* renamed from: al */
    public final HashMap<Integer, Long> mo14343al() {
        return this.f602fq;
    }

    /* renamed from: j */
    public final C0972k mo9273j(ReflectionEvent reflectionEvent) {
        if (this.f603fr == null || this.f600fo.size() > this.f603fr.length) {
            this.f603fr = new float[this.f600fo.size()];
        }
        if (this.f603fr.length > 0) {
            Arrays.fill(this.f603fr, 0.0f);
        }
        C0972k a = mo14342a(this.f603fr, reflectionEvent);
        ArrayList<C0971a> arrayList = new ArrayList(this.f600fo.size());
        float[] fArr = a.f365fP;
        if (fArr != null) {
            for (Entry entry : this.f600fo.entrySet()) {
                arrayList.add(new C0971a((String) entry.getKey(), fArr[((Integer) entry.getValue()).intValue()], getName()));
            }
        }
        Collections.sort(arrayList, Collections.reverseOrder());
        a.f367fR = arrayList;
        return a;
    }

    /* renamed from: a */
    public final void mo9268a(String str, String str2, Map<String, String> map) {
        String sb;
        ArrayList arrayList = new ArrayList();
        for (String sb2 : this.f600fo.keySet()) {
            if (sb2.startsWith(str)) {
                arrayList.add(sb2);
            }
        }
        int i = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            while (true) {
                HashMap<String, Integer> hashMap = this.f600fo;
                StringBuilder sb3 = new StringBuilder(String.valueOf(str2).length() + 11);
                sb3.append(str2);
                sb3.append(i);
                if (!hashMap.containsKey(sb3.toString())) {
                    break;
                }
                i++;
            }
            StringBuilder sb4 = new StringBuilder(String.valueOf(str2).length() + 11);
            sb4.append(str2);
            sb4.append(i);
            sb2 = sb4.toString();
            map.put(str3, sb2);
            if (!this.f600fo.isEmpty()) {
                Integer num = (Integer) this.f600fo.remove(str3);
                if (num != null) {
                    this.f600fo.put(sb2, num);
                }
                mo14353k(str3);
            }
        }
    }

    /* renamed from: am */
    public final HashMap<Integer, Integer> mo14344am() {
        return this.f599fn;
    }

    /* renamed from: an */
    public final HashMap<Integer, Long> mo14345an() {
        return this.f598ds;
    }

    /* renamed from: ao */
    public final int mo14346ao() {
        return this.f604fs;
    }

    /* renamed from: c */
    public final void mo14349c(int i) {
        this.f604fs = i;
    }

    /* renamed from: ap */
    public final int mo14347ap() {
        return this.f601fp;
    }

    /* renamed from: d */
    public final void mo14350d(int i) {
        this.f601fp = i;
    }

    /* renamed from: aq */
    public final HashMap<String, Integer> mo14348aq() {
        return this.f600fo;
    }
}
