package com.google.android.libraries.launcherclient;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.WindowManager.LayoutParams;

public interface ILauncherOverlay extends IInterface {

    public abstract class Stub extends Binder implements ILauncherOverlay {
		public static final String DESCRIPTOR = "com.google.android.libraries.launcherclient.ILauncherOverlay";

        public static class Proxy implements ILauncherOverlay {

			public final IBinder mRemote;

            public Proxy(IBinder iBinder) {
                mRemote = iBinder;
            }

			public IBinder asBinder() {
				return mRemote;
			}

			public Parcel obtainAndWriteInterfaceToken() {
				Parcel obtain = Parcel.obtain();
				obtain.writeInterfaceToken(Stub.DESCRIPTOR);
				return obtain;
			}

			public void transactOneway(int i, Parcel parcel) {
				try {
					mRemote.transact(i, parcel, null, 1);
				} finally {
					parcel.recycle();
				}
			}

            public final void startScroll() {
                transactOneway(1, obtainAndWriteInterfaceToken());
            }

            public final void onScroll(float f) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeFloat(f);
                transactOneway(2, obtainAndWriteInterfaceToken);
            }

            public final void endScroll() {
                transactOneway(3, obtainAndWriteInterfaceToken());
            }

            public final void windowAttached(LayoutParams layoutParams, ILauncherOverlayCallback iLauncherOverlayCallback, int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2222a(obtainAndWriteInterfaceToken, (Parcelable) layoutParams);
                C0679a.m2221a(obtainAndWriteInterfaceToken, (IInterface) iLauncherOverlayCallback);
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(4, obtainAndWriteInterfaceToken);
            }

            public final void windowAttached2(Bundle bundle, ILauncherOverlayCallback iLauncherOverlayCallback) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2222a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
                C0679a.m2221a(obtainAndWriteInterfaceToken, (IInterface) iLauncherOverlayCallback);
                transactOneway(14, obtainAndWriteInterfaceToken);
            }

            public final void onDetachedFromWindow(boolean z) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2223a(obtainAndWriteInterfaceToken, z);
                transactOneway(5, obtainAndWriteInterfaceToken);
            }

            public final void closeOverlay(boolean z) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
				C0679a.m2223a(obtainAndWriteInterfaceToken, z);
                transactOneway(6, obtainAndWriteInterfaceToken);
            }

            public final void onPause() {
                transactOneway(7, obtainAndWriteInterfaceToken());
            }

            public final void onResume() {
                transactOneway(8, obtainAndWriteInterfaceToken());
            }

            public final void setActivityState(int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(16, obtainAndWriteInterfaceToken);
            }

            public final void openOverlay(int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(9, obtainAndWriteInterfaceToken);
            }

            public final boolean startSearch(byte[] bArr, Bundle bundle) {
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
                    asBinder().transact(17, _data, _reply, 0);
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
                boolean _arg0 = false;
                int bundle = 0;
                switch (code) {
                    case 1:
                        data.enforceInterface(descriptor);
                        startScroll();
                        return true;
                    case 2:
                        data.enforceInterface(descriptor);
                        onScroll(data.readFloat());
                        return true;
                    case 3:
                        data.enforceInterface(descriptor);
                        endScroll();
                        return true;
                    case 4:
                        data.enforceInterface(descriptor);
                        if (data.readInt() != 0) {
                            bundle = (LayoutParams) LayoutParams.CREATOR.createFromParcel(data);
                        }
                        windowAttached(bundle, com.google.android.libraries.launcherclient.ILauncherOverlayCallback.Stub.asInterface(data.readStrongBinder()), data.readInt());
                        return true;
                    case 5:
                        data.enforceInterface(descriptor);
                        if (data.readInt() != 0) {
                            _arg0 = true;
                        }
                        onDetachedFromWindow(_arg0);
                        return true;
                    case 6:
                        data.enforceInterface(descriptor);
                        closeOverlay(data.readInt());
                        return true;
                    case 7:
                        data.enforceInterface(descriptor);
                        onPause();
                        return true;
                    case 8:
                        data.enforceInterface(descriptor);
                        onResume();
                        return true;
                    case 9:
                        data.enforceInterface(descriptor);
                        openOverlay(data.readInt());
                        return true;
                    case 14:
                        data.enforceInterface(descriptor);
                        if (data.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                        }
                        windowAttached2(bundle, com.google.android.libraries.launcherclient.ILauncherOverlayCallback.Stub.asInterface(data.readStrongBinder()));
                        return true;
                    case 16:
                        data.enforceInterface(descriptor);
                        setActivityState(data.readInt());
                        return true;
                    case 17:
                        data.enforceInterface(descriptor);
                        byte[] dataArray = data.createByteArray();
                        if (data.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                        }
                        boolean _result2 = startSearch(dataArray, bundle);
                        reply.writeNoException();
                        reply.writeInt(_result2);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
            reply.writeString(descriptor);
            return true;
        }
    }

    void closeOverlay(boolean z);

    void endScroll();

    void onPause();

    void onResume();

    void onScroll(float f);

    void openOverlay(int i);

    void setActivityState(int i);

    void startScroll();

    boolean startSearch(byte[] bArr, Bundle bundle);

    void windowAttached(LayoutParams layoutParams, ILauncherOverlayCallback iLauncherOverlayCallback, int i);

    void windowAttached2(Bundle bundle, ILauncherOverlayCallback iLauncherOverlayCallback);

    void onDetachedFromWindow(boolean z);
}
