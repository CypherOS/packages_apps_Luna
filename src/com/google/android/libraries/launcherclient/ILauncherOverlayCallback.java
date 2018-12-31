package com.google.android.libraries.launcherclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILauncherOverlayCallback extends IInterface {

    public static abstract class Stub extends Binder implements ILauncherOverlayCallback {
		public static final String DESCRIPTOR = "com.google.android.libraries.launcherclient.ILauncherOverlayCallback";
        public static final int TRANSACTION_overlayScrollChanged = 1;
        public static final int TRANSACTION_overlayStatusChanged = 2;

        private static class Proxy implements ILauncherOverlayCallback {
            public IBinder mRemote;

            public Proxy(IBinder remote) {
                mRemote = remote;
            }

            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void overlayScrollChanged(float progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(progress);
                    mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void overlayStatusChanged(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILauncherOverlayCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILauncherOverlayCallback)) {
                return new Proxy(obj);
            }
            return (ILauncherOverlayCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(descriptor);
                        overlayScrollChanged(data.readFloat());
                        return true;
                    case 2:
                        data.enforceInterface(descriptor);
                        overlayStatusChanged(data.readInt());
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
            reply.writeString(descriptor);
            return true;
        }
    }

    void overlayScrollChanged(float f) throws RemoteException;

    void overlayStatusChanged(int i) throws RemoteException;
}
