package com.android.launcher3.reflection;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import com.android.launcher3.LauncherSettings$Favorites;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.p010a.C0792e;
import com.android.launcher3.reflection.p014d.C0809d;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class FirstPageComponentFilter {
    /* renamed from: X */
    private final ContentResolver f88X;
    /* renamed from: Y */
    private final IntentParser f89Y = new IntentParser();
    /* renamed from: Z */
    private final SharedPreferences f90Z;
    /* renamed from: aa */
    private final C0809d f91aa;
    /* renamed from: ab */
    private Set<String> f92ab = new HashSet();
    private final Context mContext;

    static class IntentParser {
        IntentParser() {
        }

        /* renamed from: b */
        public final Intent mo8464b(String str) {
            try {
                return Intent.parseUri(str, 0);
            } catch (URISyntaxException e) {
                Log.e("Reflection.1stPFilter", String.format("Invalid intent URI %s", new Object[]{str}), e);
                return null;
            }
        }
    }

    public FirstPageComponentFilter(ContentResolver contentResolver, SharedPreferences sharedPreferences, C0809d c0809d, Context context) {
        this.f88X = contentResolver;
        this.f90Z = sharedPreferences;
        this.f91aa = c0809d;
        this.mContext = context;
        if (this.f90Z != null) {
            Set<String> stringSet = this.f90Z.getStringSet("model_subtracted_events", null);
            if (stringSet != null) {
                this.f92ab = stringSet;
            }
        }
    }

    public final synchronized void update() {
        Preconditions.assertNonUiThread();
        this.f92ab = new HashSet();
        Object format = String.format(Locale.ENGLISH, "(SELECT %s from %s ORDER BY %s ASC LIMIT 1)", new Object[]{"_id", "workspaceScreens", "screenRank"});
        Cursor query = this.f88X.query(LauncherSettings$Favorites.CONTENT_URI, new String[]{"intent", "profileId"}, String.format(Locale.ENGLISH, "%s = %d AND (%s = %d OR (%s = %d AND %s = %s))", new Object[]{"itemType", Integer.valueOf(0), "container", Integer.valueOf(-101), "container", Integer.valueOf(-100), "screen", format}), null, null);
        if (query.moveToFirst()) {
            do {
                try {
                    String string = query.getString(0);
                    long j = query.getLong(1);
                    if (string != null) {
                        Intent b = this.f89Y.mo8464b(string);
                        if (b != null) {
                            if (b.getComponent() != null) {
                                this.f92ab.add(C0792e.m2563a(b.getComponent(), j, this.mContext));
                            }
                        }
                        Log.e("Reflection.1stPFilter", "No component retrieved from intent.");
                    }
                } catch (Exception e) {
                    Log.e("Reflection.1stPFilter", "Error in reading intent from cursor", e);
                }
            } while (query.moveToNext());
        }
        query.close();
    }
}
