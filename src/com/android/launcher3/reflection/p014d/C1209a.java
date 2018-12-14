package com.google.android.apps.nexuslauncher.reflection.p014d;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.launcher3.util.NoLocaleSQLiteHelper;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.d.a */
public class C1209a extends NoLocaleSQLiteHelper {
    public C1209a(Context context, String str) {
        super(context, str, 4);
    }

    public synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE reflection_event (_id INTEGER PRIMARY KEY AUTOINCREMENT,timestamp INTEGER,client TEXT,type TEXT,id TEXT,latLong BLOB,semanticPlace BLOB,proto BLOB,eventSource TEXT,public_place BLOB,generated_from TEXT)");
    }

    public final void createEmptyDB(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reflection_event");
        onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StringBuilder sb;
        switch (i) {
            case 1:
                sQLiteDatabase.beginTransaction();
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE reflection_event ADD COLUMN eventSource TEXT DEFAULT NULL");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException e) {
                    sb = new StringBuilder("Adding event source column failed: ");
                    sb.append(e.getMessage());
                    Log.d("Reflection.EvtDbHelper", sb.toString());
                    break;
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            case 2:
                sQLiteDatabase.beginTransaction();
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE reflection_event ADD COLUMN public_place BLOB DEFAULT NULL");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException e2) {
                    sb = new StringBuilder("Adding public place column failed: ");
                    sb.append(e2.getMessage());
                    Log.d("Reflection.EvtDbHelper", sb.toString());
                    break;
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            case 3:
                sQLiteDatabase.beginTransaction();
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE reflection_event ADD COLUMN generated_from TEXT DEFAULT NULL");
                    sQLiteDatabase.setTransactionSuccessful();
                    return;
                } catch (SQLException e22) {
                    sb = new StringBuilder("Adding generated from column failed: ");
                    sb.append(e22.getMessage());
                    Log.d("Reflection.EvtDbHelper", sb.toString());
                    break;
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            case 4:
                return;
        }
        Log.w("Reflection.EvtDbHelper", "Destroying all old data.");
        createEmptyDB(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StringBuilder sb = new StringBuilder("Database version downgrade from: ");
        sb.append(i);
        sb.append(" to ");
        sb.append(i2);
        sb.append(". Wiping database.");
        Log.w("Reflection.EvtDbHelper", sb.toString());
        createEmptyDB(sQLiteDatabase);
    }
}
