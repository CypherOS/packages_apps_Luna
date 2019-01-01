package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.C0855a;

public interface IScreenMatchmaker extends IInterface {

    public abstract class Stub extends BaseStub implements IScreenMatchmaker {

        public class Proxy extends BaseProxy implements IScreenMatchmaker {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker");
            }

            public final String extractContentAsync(String str, String str2, InteractionContextData interactionContextData, long j, Bitmap bitmap, Bundle bundle, IContentCallback iContentCallback) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeString(str2);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) interactionContextData);
                obtainAndWriteInterfaceToken.writeLong(j);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) bitmap);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
                C0855a.m564a(obtainAndWriteInterfaceToken, (IInterface) iContentCallback);
                Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
                str2 = transactAndReadException.readString();
                transactAndReadException.recycle();
                return str2;
            }

            public final String getEntitiesAsync(String str, String str2, ContentData contentData, long j, Bitmap bitmap, Bundle bundle, IEntitiesCallback iEntitiesCallback) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeString(str2);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) contentData);
                obtainAndWriteInterfaceToken.writeLong(j);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) bitmap);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
                C0855a.m564a(obtainAndWriteInterfaceToken, (IInterface) iEntitiesCallback);
                Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
                str2 = transactAndReadException.readString();
                transactAndReadException.recycle();
                return str2;
            }

            public final boolean registerSettingsCallback(ISettingsCallback iSettingsCallback) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0855a.m564a(obtainAndWriteInterfaceToken, (IInterface) iSettingsCallback);
                Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
                boolean a = C0855a.m567a(transactAndReadException);
                transactAndReadException.recycle();
                return a;
            }

            public final void reportFeedback(String str, FeedbackData feedbackData) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeString(str);
                C0855a.m565a(obtainAndWriteInterfaceToken, (Parcelable) feedbackData);
                Parcel obtain = Parcel.obtain();
                try {
                    this.mRemote.transact(4, obtainAndWriteInterfaceToken, obtain, 0);
                    obtain.readException();
                } finally {
                    obtainAndWriteInterfaceToken.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IScreenMatchmaker asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker");
            if (queryLocalInterface instanceof IScreenMatchmaker) {
                return (IScreenMatchmaker) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    String extractContentAsync(String str, String str2, InteractionContextData interactionContextData, long j, Bitmap bitmap, Bundle bundle, IContentCallback iContentCallback);

    String getEntitiesAsync(String str, String str2, ContentData contentData, long j, Bitmap bitmap, Bundle bundle, IEntitiesCallback iEntitiesCallback);

    boolean registerSettingsCallback(ISettingsCallback iSettingsCallback);

    void reportFeedback(String str, FeedbackData feedbackData);
}
