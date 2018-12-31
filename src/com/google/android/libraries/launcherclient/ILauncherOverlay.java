package com.google.android.libraries.launcherclient;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.WindowManager.LayoutParams;

public interface ILauncherOverlay extends IInterface {

    public abstract class Stub extends Binder implements ILauncherOverlay {
		public static final String DESCRIPTOR = "com.google.android.libraries.launcherclient.ILauncherOverlay";
		public static final int START_SCROLL_TRANSACTION = 1;
		public static final int ON_SCROLL_TRANSACTION = 2;
		public static final int END_SCROLL_TRANSACTION = 3;
		public static final int WINDOW_ATTACHED_TRANSACTION = 4;
		public static final int WINDOW_DETACHED_TRANSACTION = 5;
		public static final int CLOSE_OVERLAY_TRANSACTION = 6;
		public static final int ON_PAUSE_TRANSACTION = 7;
        public static final int ON_RESUME_TRANSACTION = 8;
        public static final int OPEN_OVERLAY_TRANSACTION = 9;
        public static final int SET_ACTIVITY_STATE_TRANSACTION = 16;
        public static final int START_SEARCH_TRANSACTION = 17;

        public static class Proxy implements ILauncherOverlay {

			public final IBinder mRemote;

            public Proxy(IBinder iBinder) {
                mRemote = iBinder;
            }

			public IBinder asBinder() {
				return mRemote;
			}

            public void startScroll() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    mRemote.transact(START_SCROLL_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onScroll(float progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(progress);
                    mRemote.transact(ON_SCROLL_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void endScroll() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    mRemote.transact(END_SCROLL_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void windowAttached(LayoutParams lp, ILauncherOverlayCallback cb, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lp != null) {
                        _data.writeInt(1);
                        lp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(flags);
                    mRemote.transact(WINDOW_ATTACHED_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onDetachedFromWindow(boolean isChangingConfigurations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isChangingConfigurations ? 1 : 0);
                    mRemote.transact(WINDOW_DETACHED_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void closeOverlay(boolean flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags ? 1 : 0);
                    mRemote.transact(CLOSE_OVERLAY_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onPause() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    mRemote.transact(ON_PAUSE_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onResume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    mRemote.transact(ON_RESUME_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

			public void openOverlay(int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    mRemote.transact(OPEN_OVERLAY_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void setActivityState(int flags) throws RemoteException {
				Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    mRemote.transact(SET_ACTIVITY_STATE_TRANSACTION, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public boolean startSearch(byte[] bArr, Bundle bundle) throws RemoteException {
				Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(bArr);
                    boolean _result = true;
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(START_SEARCH_TRANSACTION, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
				return false;
            }
        }

		public Stub(String str) {
			attachInterface(this, DESCRIPTOR);
		}

        public static ILauncherOverlay asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
			IInterface iin = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILauncherOverlay)) {
                return new Proxy(iBinder);
            }
			return (ILauncherOverlay) iin;
        }

		public IBinder asBinder() {
            return this;
        }
		
		public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            if (code != 1598968902) {
                switch (code) {
                    case START_SCROLL_TRANSACTION:
                        data.enforceInterface(descriptor);
                        startScroll();
                        return true;
                    case ON_SCROLL_TRANSACTION:
                        data.enforceInterface(descriptor);
                        onScroll(data.readFloat());
                        return true;
                    case END_SCROLL_TRANSACTION:
                        data.enforceInterface(descriptor);
                        endScroll();
                        return true;
                    case WINDOW_ATTACHED_TRANSACTION:
                        data.enforceInterface(descriptor);
						LayoutParams layoutParams = null;
                        if (data.readInt() != 0) {
                            layoutParams = (LayoutParams) LayoutParams.CREATOR.createFromParcel(data);
                        }
                        windowAttached(layoutParams, com.google.android.libraries.launcherclient.ILauncherOverlayCallback.Stub.asInterface(data.readStrongBinder()), data.readInt());
                        return true;
                    case WINDOW_DETACHED_TRANSACTION:
                        data.enforceInterface(descriptor);
                        onDetachedFromWindow(data.readInt() != 0);
                        return true;
                    case CLOSE_OVERLAY_TRANSACTION:
                        data.enforceInterface(descriptor);
                        closeOverlay(data.readInt() != 0);
                        return true;
                    case ON_PAUSE_TRANSACTION:
                        data.enforceInterface(descriptor);
                        onPause();
                        return true;
                    case ON_RESUME_TRANSACTION:
                        data.enforceInterface(descriptor);
                        onResume();
                        return true;
                    case OPEN_OVERLAY_TRANSACTION:
                        data.enforceInterface(descriptor);
                        openOverlay(data.readInt());
                        return true;
                    case SET_ACTIVITY_STATE_TRANSACTION:
                        data.enforceInterface(descriptor);
                        setActivityState(data.readInt());
                        return true;
                    case START_SEARCH_TRANSACTION:
                        data.enforceInterface(descriptor);
						Bundle bundle = new Bundle();
                        byte[] dataArray = data.createByteArray();
                        if (data.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                        }
                        int result = startSearch(dataArray, bundle) ? 1 : 0;
                        reply.writeNoException();
                        reply.writeInt(result);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
            reply.writeString(descriptor);
            return true;
        }
    }

    void closeOverlay(boolean z) throws RemoteException;

    void endScroll() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onScroll(float f) throws RemoteException;

    void openOverlay(int i) throws RemoteException;

    void setActivityState(int i) throws RemoteException;

    void startScroll() throws RemoteException;

    boolean startSearch(byte[] bArr, Bundle bundle) throws RemoteException;

    void windowAttached(LayoutParams layoutParams, ILauncherOverlayCallback iLauncherOverlayCallback, int i) throws RemoteException;

    void onDetachedFromWindow(boolean z) throws RemoteException;
}
