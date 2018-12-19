package com.google.android.libraries.launcherclient;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class BaseProxy implements IInterface {
    private final String mDescriptor;
    public final IBinder mRemote;

    public BaseProxy(IBinder iBinder, String str) {
        this.mRemote = iBinder;
        this.mDescriptor = str;
    }

    public IBinder asBinder() {
        return mRemote;
    }

    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.mDescriptor);
        return obtain;
    }

    public final void transactOneway(int i, Parcel parcel) {
        try {
            this.mRemote.transact(i, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
