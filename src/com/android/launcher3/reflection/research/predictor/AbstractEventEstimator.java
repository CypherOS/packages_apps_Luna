package com.android.launcher3.reflection.research.predictor;

import android.support.p003v7.widget.RecyclerView;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractEventEstimator extends C0964g {
    /* renamed from: ds */
    protected HashMap<Integer, Long> f599ds = new HashMap();
    /* renamed from: fn */
    protected HashMap<Integer, Integer> f600fn = new HashMap();
    /* renamed from: fo */
    protected HashMap<String, Integer> f601fo = new HashMap();
    /* renamed from: fp */
    protected int f602fp;
    /* renamed from: fq */
    protected HashMap<Integer, Long> f603fq = new HashMap();
    /* renamed from: fr */
    private float[] f604fr = null;
    /* renamed from: fs */
    protected int f605fs = 100;

    public static class PredictorInvalidException extends Exception {
        public PredictorInvalidException(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    protected abstract C0968k mo14301a(float[] fArr, ReflectionEvent reflectionEvent);

    /* renamed from: i */
    protected abstract C0968k mo14311i(ReflectionEvent reflectionEvent);

    /* renamed from: k */
    protected abstract void mo14312k(String str);

    /* renamed from: g */
    public final int mo14310g(ReflectionEvent reflectionEvent) {
        String id = reflectionEvent.getId();
        long timestamp = reflectionEvent.mo9264D().getTimestamp();
        Integer num = (Integer) this.f601fo.get(id);
        if (num == null) {
            num = Integer.valueOf(this.f601fo.size());
            this.f601fo.put(id, num);
            this.f603fq.put(num, Long.valueOf(timestamp));
        }
        return num.intValue();
    }

    /* renamed from: h */
    public final C0968k mo9254h(ReflectionEvent reflectionEvent) {
        Integer num;
        if (!this.f601fo.containsKey(reflectionEvent.getId()) && this.f601fo.size() == this.f605fs) {
            String str;
            long j = RecyclerView.FOREVER_NS;
            Integer num2 = null;
            for (Entry entry : this.f599ds.entrySet()) {
                if (((Long) entry.getValue()).longValue() < j) {
                    Integer num3 = (Integer) entry.getKey();
                    num2 = num3;
                    j = ((Long) entry.getValue()).longValue();
                }
            }
            for (Entry entry2 : this.f601fo.entrySet()) {
                if (((Integer) entry2.getValue()).equals(num2)) {
                    str = (String) entry2.getKey();
                    break;
                }
            }
            str = null;
            if (str != null) {
                try {
                    if (!this.f601fo.isEmpty()) {
                        int size = this.f601fo.size() - 1;
                        num = (Integer) this.f601fo.remove(str);
                        if (this.f601fo.isEmpty()) {
                            throw new PredictorInvalidException("Predictor becomes invalid");
                        } else if (num != null) {
                            this.f602fp -= ((Integer) this.f600fn.remove(num)).intValue();
                            this.f603fq.remove(num);
                            this.f599ds.remove(num);
                            if (size > num.intValue()) {
                                for (Entry entry3 : this.f601fo.entrySet()) {
                                    if (((Integer) entry3.getValue()).intValue() == size) {
                                        entry3.setValue(num);
                                        break;
                                    }
                                }
                                this.f600fn.put(num, Integer.valueOf(((Integer) this.f600fn.remove(Integer.valueOf(size))).intValue()));
                                this.f603fq.put(num, Long.valueOf(((Long) this.f603fq.remove(Integer.valueOf(size))).longValue()));
                                this.f599ds.put(num, Long.valueOf(((Long) this.f599ds.remove(Integer.valueOf(size))).longValue()));
                            }
                            mo9249a(num, Integer.valueOf(size), str);
                        } else {
                            mo9249a(null, null, str);
                        }
                    }
                } catch (PredictorInvalidException unused) {
                }
            }
        }
        int g = mo14310g(reflectionEvent);
        C0968k i = mo14311i(reflectionEvent);
        num = (Integer) this.f600fn.get(Integer.valueOf(g));
        if (num == null) {
            num = Integer.valueOf(0);
        }
        this.f600fn.put(Integer.valueOf(g), Integer.valueOf(num.intValue() + 1));
        this.f602fp++;
        this.f599ds.put(Integer.valueOf(g), Long.valueOf(reflectionEvent.mo9264D().getTimestamp()));
        return i;
    }

    /* renamed from: al */
    public final HashMap<Integer, Long> mo14302al() {
        return this.f603fq;
    }

    /* renamed from: j */
    public final C0968k mo9255j(ReflectionEvent reflectionEvent) {
        if (this.f604fr == null || this.f601fo.size() > this.f604fr.length) {
            this.f604fr = new float[this.f601fo.size()];
        }
        if (this.f604fr.length > 0) {
            Arrays.fill(this.f604fr, 0.0f);
        }
        C0968k a = mo14301a(this.f604fr, reflectionEvent);
        ArrayList<C0967a> arrayList = new ArrayList(this.f601fo.size());
        float[] fArr = a.f365fP;
        if (fArr != null) {
            for (Entry entry : this.f601fo.entrySet()) {
                arrayList.add(new C0967a((String) entry.getKey(), fArr[((Integer) entry.getValue()).intValue()], getName()));
            }
        }
        Collections.sort(arrayList, Collections.reverseOrder());
        a.f367fR = arrayList;
        return a;
    }

    /* renamed from: a */
    public final void mo9250a(String str, String str2, Map<String, String> map) {
        String sb;
        ArrayList arrayList = new ArrayList();
        for (String sb2 : this.f601fo.keySet()) {
            if (sb2.startsWith(str)) {
                arrayList.add(sb2);
            }
        }
        int i = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            while (true) {
                HashMap<String, Integer> hashMap = this.f601fo;
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
            if (!this.f601fo.isEmpty()) {
                Integer num = (Integer) this.f601fo.remove(str3);
                if (num != null) {
                    this.f601fo.put(sb2, num);
                }
                mo14312k(str3);
            }
        }
    }

    /* renamed from: am */
    public final HashMap<Integer, Integer> mo14303am() {
        return this.f600fn;
    }

    /* renamed from: an */
    public final HashMap<Integer, Long> mo14304an() {
        return this.f599ds;
    }

    /* renamed from: ao */
    public final int mo14305ao() {
        return this.f605fs;
    }

    /* renamed from: c */
    public final void mo14308c(int i) {
        this.f605fs = i;
    }

    /* renamed from: ap */
    public final int mo14306ap() {
        return this.f602fp;
    }

    /* renamed from: d */
    public final void mo14309d(int i) {
        this.f602fp = i;
    }

    /* renamed from: aq */
    public final HashMap<String, Integer> mo14307aq() {
        return this.f601fo;
    }
}
