package com.google.research.reflection.p016a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.google.research.reflection.a.f */
public class C0947f {
    /* renamed from: a */
    public static <V, K> void m2993a(DataOutputStream dataOutputStream, Map<K, V> map) throws IOException {
        dataOutputStream.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            C0947f.m2992a(dataOutputStream, entry.getKey());
            C0947f.m2992a(dataOutputStream, entry.getValue());
        }
    }

    /* renamed from: a */
    public static <T, K, V> void m2992a(DataOutputStream dataOutputStream, T t) throws IOException {
        if (t instanceof Integer) {
            dataOutputStream.writeInt(((Integer) t).intValue());
        } else if (t instanceof Long) {
            dataOutputStream.writeLong(((Long) t).longValue());
        } else if (t instanceof Float) {
            dataOutputStream.writeFloat(((Float) t).floatValue());
        } else if (t instanceof String) {
            dataOutputStream.writeUTF((String) t);
        } else if (t instanceof HashMap) {
            C0947f.m2993a(dataOutputStream, (Map) (HashMap) t);
        } else {
            int i = 0;
            if (t instanceof int[]) {
                int[] iArr = (int[]) t;
                dataOutputStream.writeInt(iArr.length);
                while (i < iArr.length) {
                    dataOutputStream.writeInt(iArr[i]);
                    i++;
                }
                return;
            }
            if (t instanceof float[]) {
                float[] fArr = (float[]) t;
                dataOutputStream.writeInt(fArr.length);
                while (i < fArr.length) {
                    dataOutputStream.writeFloat(fArr[i]);
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    public static <V, K> HashMap<K, V> m2991a(DataInputStream dataInputStream, Class<K> cls, Class<V> cls2) throws IOException {
        HashMap<K, V> hashMap = new HashMap();
        int readInt = dataInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(C0947f.m2990a(dataInputStream, (Class) cls), C0947f.m2990a(dataInputStream, (Class) cls2));
        }
        return hashMap;
    }

    /* renamed from: a */
    public static <T> Object m2990a(DataInputStream dataInputStream, Class<T> cls) throws IOException {
        if (cls == Integer.class) {
            return Integer.valueOf(dataInputStream.readInt());
        }
        if (cls == Long.class) {
            return Long.valueOf(dataInputStream.readLong());
        }
        if (cls == Float.class) {
            return Float.valueOf(dataInputStream.readFloat());
        }
        if (cls == String.class) {
            return dataInputStream.readUTF();
        }
        int i = 0;
        int readInt;
        if (cls == int[].class) {
            readInt = dataInputStream.readInt();
            int[] iArr = new int[readInt];
            while (i < readInt) {
                iArr[i] = dataInputStream.readInt();
                i++;
            }
            return iArr;
        } else if (cls != float[].class) {
            return null;
        } else {
            readInt = dataInputStream.readInt();
            float[] fArr = new float[readInt];
            while (i < readInt) {
                fArr[i] = dataInputStream.readFloat();
                i++;
            }
            return fArr;
        }
    }
}
