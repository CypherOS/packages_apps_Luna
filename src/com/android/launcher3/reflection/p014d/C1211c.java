package com.android.launcher3.reflection.p014d;

import android.content.ComponentName;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.C0832f;
import com.android.launcher3.reflection.p010a.C0788a;
import com.android.launcher3.reflection.p010a.C0789b;
import com.android.launcher3.reflection.p015e.C0817b.C0818a;
import com.android.launcher3.reflection.signal.C1214a;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.android.launcher3.reflection.research.p016a.C0940c;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.android.launcher3.reflection.d.c */
public class C1211c implements C0940c {
    /* renamed from: bK */
    private static final long f431bK = TimeUnit.DAYS.toMillis(1);
    /* renamed from: ad */
    private final C0789b f432ad;
    /* renamed from: bI */
    private final Map<String, Long> f433bI = new HashMap();
    /* renamed from: bJ */
    private final Map<String, String> f434bJ = new HashMap();
    /* renamed from: bL */
    private final C1209a f435bL;

    /* renamed from: com.android.launcher3.reflection.d.c$a */
    public static class C0808a {
        /* renamed from: bM */
        public final long f147bM;
        /* renamed from: bN */
        public final List<C1214a> f148bN;

        public C0808a(long j, List<C1214a> list) {
            this.f147bM = j;
            this.f148bN = list;
        }
    }

    public C1211c(C1209a c1209a, C0789b c0789b) {
        this.f435bL = c1209a;
        this.f432ad = c0789b;
    }

    /* renamed from: d */
    public final synchronized void mo14077d(ReflectionEvent reflectionEvent) {
        mo14075a(((C1214a) reflectionEvent).f464df);
    }

    /* renamed from: a */
    public final synchronized void mo14075a(C0818a c0818a) {
        Preconditions.assertNonUiThread();
        try {
            c0818a = C0818a.m2620c(MessageNano.toByteArray(c0818a));
            SQLiteDatabase writableDatabase = this.f435bL.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("timestamp", Long.valueOf(c0818a.f173ci.timestamp));
            contentValues.put("client", BuildConfig.FLAVOR);
            contentValues.put("type", Integer.valueOf(c0818a.type));
            contentValues.put("id", c0818a.f177id);
            contentValues.put("generated_from", c0818a.f176cl);
            contentValues.put("eventSource", c0818a.f172bw.length > 1 ? c0818a.f172bw[1] : BuildConfig.FLAVOR);
            if (c0818a.f174cj != null) {
                if (c0818a.f174cj.f180cm != null) {
                    contentValues.put("semanticPlace", MessageNano.toByteArray(c0818a.f174cj.f180cm));
                }
                if (c0818a.f174cj.f181cn != null) {
                    contentValues.put("public_place", MessageNano.toByteArray(c0818a.f174cj.f181cn));
                }
                if (c0818a.f174cj.f182co != null) {
                    contentValues.put("latLong", MessageNano.toByteArray(c0818a.f174cj.f182co));
                }
            }
            c0818a.f174cj = null;
            contentValues.put("proto", MessageNano.toByteArray(c0818a));
            writableDatabase.insert("reflection_event", null, contentValues);
        } catch (InvalidProtocolBufferNanoException unused) {
        }
    }

    /* renamed from: a */
    public final synchronized void mo14074a(long j) {
        Preconditions.assertNonUiThread();
        j -= 3024000000L;
        this.f435bL.getWritableDatabase().delete("reflection_event", "timestamp <= ?", new String[]{Long.toString(j)});
    }

    /* renamed from: a */
    public final synchronized void mo14076a(String str, String str2, Map<String, String> map) {
        SQLiteStatement compileStatement;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Preconditions.assertNonUiThread();
        SQLiteDatabase writableDatabase = this.f435bL.getWritableDatabase();
        writableDatabase.beginTransaction();
        Cursor query;
        try {
            String[] strArr = new String[1];
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("%");
            strArr[0] = sb.toString();
            query = writableDatabase.query("reflection_event", new String[]{"_id", "id"}, "id like ?", strArr, null, null, null, null);
            compileStatement = writableDatabase.compileStatement("UPDATE reflection_event SET id = ? WHERE _id = ?");
            try {
                int columnIndex = query.getColumnIndex("_id");
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndex);
                    String string = query.getString(columnIndexOrThrow);
                    String str3 = (String) map.get(string);
                    if (str3 == null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str2);
                        sb2.append("_");
                        sb2.append(map.size());
                        str3 = sb2.toString();
                        map.put(string, str3);
                    }
                    compileStatement.bindString(1, str3);
                    compileStatement.bindLong(2, j);
                    compileStatement.executeUpdateDelete();
                }
                writableDatabase.setTransactionSuccessful();
                if (compileStatement != null) {
                    C1211c.m4728a(null, (AutoCloseable) compileStatement);
                }
                if (query != null) {
                    C1211c.m4728a(null, (AutoCloseable) query);
                }
                writableDatabase.endTransaction();
            } catch (Throwable th22) {
                Throwable th4 = th22;
                th22 = th;
                th = th4;
            }
        } catch (SQLException e) {
            try {
                FileLog.d("Reflection.EvtDbLogger", "Error renaming EventIds", e);
            } finally {
                writableDatabase.endTransaction();
            }
            return;
        } catch (Throwable th5) {
            if (query != null) {
                C1211c.m4728a(th3, (AutoCloseable) query);
            }
        }
        return;
        if (compileStatement != null) {
            C1211c.m4728a(th22, (AutoCloseable) compileStatement);
        }
        throw th;
    }

    /* renamed from: q */
    public final String mo9173q() {
        return m4727a("music_app", f431bK, C0940c.f278do);
    }

    /* renamed from: r */
    public final String mo9174r() {
        return m4727a("taxi_app", f431bK, C0940c.f279dp);
    }

    /* renamed from: s */
    public final String mo9175s() {
        return m4727a("cafe_app", f431bK, C0940c.f280dq);
    }

    /* renamed from: a */
    private String m4727a(String str, long j, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - (this.f433bI.get(str) == null ? 0 : ((Long) this.f433bI.get(str)).longValue()) < j) {
            if (this.f434bJ.get(str) != null) {
                return (String) this.f434bJ.get(str);
            }
        }
        j = 10;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < strArr.length; i++) {
            long e = m4729e(strArr[i]);
            if (e >= j) {
                str3 = strArr[i];
                j = e;
            }
        }
        this.f433bI.put(str, Long.valueOf(currentTimeMillis));
        C0789b c0789b = this.f432ad;
        if (str3 != null) {
            if (c0789b.f101aV.containsKey(str3)) {
                C0788a c0788a = (C0788a) c0789b.f101aV.get(str3);
                str2 = C0832f.m2647a(new ComponentName(c0788a.packageName, c0788a.className));
            }
        }
        this.f434bJ.put(str, str2);
        return str2;
    }

    /* renamed from: e */
    private synchronized long m4729e(String str) {
        SQLiteStatement compileStatement;
        compileStatement = this.f435bL.getReadableDatabase().compileStatement("select count(*) from reflection_event where id like ?");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/%");
        compileStatement.bindString(1, sb.toString());
        return compileStatement.simpleQueryForLong();
    }
}
