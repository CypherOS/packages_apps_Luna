package com.google.android.libraries.gsa.launcherclient;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.android.launcher3.AbstractFloatingView;
import com.google.android.libraries.launcherclient.ILauncherOverlay;
import com.google.android.libraries.launcherclient.ILauncherOverlayCallback.Stub;
import java.lang.ref.WeakReference;

public class LauncherClient {
    public static int aes = -1;
    protected ILauncherOverlay aeA;
    public int aeB = 0;
    public boolean aeC = false;
    public int aeD = 0;
    public int mFlags;
    public LayoutParams aeF;
    public OverlayCallbacks aeG;
    private Bundle aeH;
    public final Activity aet;
    private final LauncherClientCallbacks aeu;
    public final C1245b aev = new C1245b("Client", 20);
    public final C1245b aew = new C1245b("Service", 10);
    public final C1251i aex;
    public final ClientService aey;
    public final BroadcastReceiver mInstallListener = new C1247d(this);

    public class OverlayCallbacks extends Stub implements Callback {
        public LauncherClient client;
        private final Handler uIHandler = new Handler(Looper.getMainLooper(), this);
        public Window window;
        private boolean windowHidden = false;
        public WindowManager windowManager;
        int windowShift;

        OverlayCallbacks() {
        }

        public final void overlayScrollChanged(float f) {
            this.uIHandler.removeMessages(2);
            Message.obtain(this.uIHandler, 2, Float.valueOf(f)).sendToTarget();
            if (f > 0.0f && this.windowHidden) {
                this.windowHidden = false;
            }
        }

        public final void overlayStatusChanged(int i) {
            Message.obtain(this.uIHandler, 4, i, 0).sendToTarget();
        }

        public boolean handleMessage(Message message) {
            if (this.client == null) {
                return true;
            }
            switch (message.what) {
                case 2:
                    if ((this.client.aeD & 1) != 0) {
                        float floatValue = ((Float) message.obj).floatValue();
                        this.client.aeu.onOverlayScrollChanged(floatValue);
                        if (floatValue <= 0.0f) {
                            this.client.aew.parse(0, "onScroll 0, overlay closed", 0.0f);
                        } else if (floatValue >= 1.0f) {
                            this.client.aew.parse(0, "onScroll 1, overlay opened", 0.0f);
                        } else {
                            this.client.aew.parse(1, "onScroll", floatValue);
                        }
                    }
                    return true;
                case 3:
                    LayoutParams attributes = this.window.getAttributes();
                    if (((Boolean) message.obj).booleanValue()) {
                        attributes.x = this.windowShift;
                        attributes.flags |= 512;
                    } else {
                        attributes.x = 0;
                        attributes.flags &= -513;
                    }
                    this.windowManager.updateViewLayout(this.window.getDecorView(), attributes);
                    return true;
                case 4:
                    this.client.m3611bm(message.arg1);
                    this.client.aew.parse("stateChanged", message.arg1);
                    if (this.client.aeu instanceof C1634h) {
                        ((C1634h) this.client.aeu).mo5125aw(message.arg1);
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    public LauncherClient(Activity activity, LauncherClientCallbacks c1250g, ClientOptions c1249f) {
        this.aet = activity;
        this.aeu = c1250g;
        this.aex = new C1251i(activity, 65);
        this.mFlags = c1249f.options;
        this.aey = ClientService.m5340I((Context) activity);
        ClientService c1633a = this.aey;
        c1633a.mWeakReference = new WeakReference(this);
        this.aeA = c1633a.ael;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (VERSION.SDK_INT >= 19) {
            intentFilter.addDataSchemeSpecificPart("com.google.android.googlequicksearchbox", 0);
        }
        this.aet.registerReceiver(mInstallListener, intentFilter);
        if (aes <= 0) {
            m3606K((Context) activity);
        }
        reconnect();
        if (VERSION.SDK_INT >= 19 && this.aet.getWindow() != null && this.aet.getWindow().peekDecorView() != null && this.aet.getWindow().peekDecorView().isAttachedToWindow()) {
            onAttachedToWindow();
        }
    }

    public final void onAttachedToWindow() {
        if (!this.aeC) {
            this.aev.parse(0, "attachedToWindow", 0.0f);
            setParams(this.aet.getWindow().getAttributes());
        }
    }

    public final void onResume() {
        if (!this.aeC) {
            this.aeB |= 2;
            if (!(this.aeA == null || this.aeF == null)) {
                try {
                    if (aes < 4) {
                        this.aeA.onResume();
                    } else {
                        this.aeA.setActivityState(this.aeB);
                    }
                } catch (RemoteException e) {
                }
            }
            this.aev.parse("stateChanged ", this.aeB);
        }
    }

    public final void onPause() {
        if (!this.aeC) {
            this.aeB &= -3;
            if (!(this.aeA == null || this.aeF == null)) {
                try {
                    if (aes < 4) {
                        this.aeA.onPause();
                    } else {
                        this.aeA.setActivityState(this.aeB);
                    }
                } catch (RemoteException e) {
                }
            }
            this.aev.parse("stateChanged ", this.aeB);
        }
    }

    public final void onStart() {
        if (!this.aeC) {
            this.aey.mo5123I(false);
            reconnect();
            this.aeB |= 1;
            if (!(this.aeA == null || this.aeF == null)) {
                try {
                    this.aeA.setActivityState(this.aeB);
                } catch (RemoteException e) {
                }
            }
            this.aev.parse("stateChanged ", this.aeB);
        }
    }

    public final void onStop() {
        if (!this.aeC) {
            this.aey.mo5123I(true);
            this.aex.disconnect();
            this.aeB &= -2;
            if (!(this.aeA == null || this.aeF == null)) {
                try {
                    this.aeA.setActivityState(this.aeB);
                } catch (RemoteException e) {
                }
            }
            this.aev.parse("stateChanged ", this.aeB);
        }
    }

    public final void reconnect() {
        if (!this.aeC) {
            if (!(this.aey.mo4046im() && this.aex.mo4046im())) {
                this.aet.runOnUiThread(new C1248e(this));
            }
        }
    }

    /* renamed from: a */
    public final void setParams(LayoutParams layoutParams) {
        if (this.aeF != layoutParams) {
            this.aeF = layoutParams;
            if (this.aeF != null) {
                updateConfiguration();
                return;
            }
            if (this.aeA != null) {
                try {
                    this.aeA.onDetachedFromWindow(this.aet.isChangingConfigurations());
                } catch (RemoteException e) {
                }
                this.aeA = null;
            }
        }
    }

    /* renamed from: ih */
    public final void updateConfiguration() {
        if (this.aeA != null) {
            try {
                if (this.aeG == null) {
                    this.aeG = new OverlayCallbacks();
                }
                OverlayCallbacks overlayCallbacks = this.aeG;
                overlayCallbacks.client = this;
                overlayCallbacks.windowManager = this.aet.getWindowManager();
                Point point = new Point();
                overlayCallbacks.windowManager.getDefaultDisplay().getRealSize(point);
                overlayCallbacks.windowShift = -Math.max(point.x, point.y);
                overlayCallbacks.window = this.aet.getWindow();
                if (aes < 3) {
                    this.aeA.windowAttached(this.aeF, this.aeG, this.mFlags);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("layout_params", this.aeF);
                    bundle.putParcelable("configuration", this.aet.getResources().getConfiguration());
                    bundle.putInt("client_options", this.mFlags);
                    if (this.aeH != null) {
                        bundle.putAll(this.aeH);
                    }
                    this.aeA.windowAttached2(bundle, this.aeG);
                }
                if (aes >= 4) {
                    this.aeA.setActivityState(this.aeB);
                } else if ((this.aeB & 2) != 0) {
                    this.aeA.onResume();
                } else {
                    this.aeA.onPause();
                }
            } catch (RemoteException e) {
            }
        }
    }

    public final boolean isConnected() {
        return this.aeA != null;
    }

    /* renamed from: ii */
    public final void startMove() {
        this.aev.parse(0, "startMove", 0.0f);
        if (isConnected()) {
            try {
                this.aeA.startScroll();
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: ij */
    public final void endMove() {
        this.aev.parse(0, "endMove", 0.0f);
        if (isConnected()) {
            try {
                this.aeA.endScroll();
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: i */
    public final void updateMove(float f) {
        this.aev.parse(1, "updateMove", f);
        if (isConnected()) {
            try {
                this.aeA.onScroll(f);
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: J */
    public final void hideOverlay(boolean z) {
        this.aev.mo4037c("hideOverlay", z);
        if (this.aeA != null) {
            try {
                this.aeA.closeOverlay(z);
            } catch (RemoteException e) {
            }
        }
    }

    public final boolean startSearch(byte[] bArr, Bundle bundle) {
        this.aev.parse(0, "startSearch", 0.0f);
        if (aes >= 6 && this.aeA != null) {
            try {
                return this.aeA.startSearch(bArr, bundle);
            } catch (RemoteException e) {
                Log.e("DrawerOverlayClient", "Error starting session for search", e);
            }
        }
        return false;
    }

    /* renamed from: i */
    public final void mo4020i(Bundle bundle) {
        C1245b c1245b = this.aev;
        String str = "setPrivateOptions : ";
        String valueOf = String.valueOf(bundle == null ? "null" : TextUtils.join(",", bundle.keySet()));
        c1245b.parse(0, valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0.0f);
        this.aeH = bundle;
        mo4024ik();
    }

    /* renamed from: ik */
    public final void mo4024ik() {
        if (this.aeF != null && aes >= 7) {
            updateConfiguration();
        }
    }

    /* renamed from: b */
    final void mo4017b(ILauncherOverlay iLauncherOverlay) {
        this.aew.mo4037c("Connected", iLauncherOverlay != null);
        this.aeA = iLauncherOverlay;
        if (this.aeA == null) {
            m3611bm(0);
            return;
        }
        if (this.aeF != null) {
            updateConfiguration();
        }
    }

    /* renamed from: bm */
    public void m3611bm(int i) {
        if (this.aeD != i) {
            this.aeD = i;
            LauncherClientCallbacks c1250g = this.aeu;
            boolean attached = true;
            if ((i & 1) == 0) {
                attached = false;
            }
            c1250g.onServiceStateChanged(attached);
        }
    }

    /* renamed from: J */
    static Intent m3605J(Context context) {
        String packageName = context.getPackageName();
        int myUid = Process.myUid();
        StringBuilder stringBuilder = new StringBuilder(18 + String.valueOf(packageName).length());
        stringBuilder.append("app://");
        stringBuilder.append(packageName);
        stringBuilder.append(":");
        stringBuilder.append(myUid);
        return new Intent("com.android.launcher3.WINDOW_OVERLAY").setPackage("com.google.android.googlequicksearchbox").setData(Uri.parse(stringBuilder.toString()).buildUpon().appendQueryParameter("v", Integer.toString(9)).appendQueryParameter("cv", Integer.toString(14)).build());
    }

    /* renamed from: K */
    public static void m3606K(Context context) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(m3605J(context), AbstractFloatingView.TYPE_TASK_MENU);
        if (resolveService == null || resolveService.serviceInfo.metaData == null) {
            aes = 1;
        } else {
            aes = resolveService.serviceInfo.metaData.getInt("service.api.version", 1);
        }
    }

	public Activity getActivity() {
		return aet;
	}

	public C1251i getBaseService() {
		return aex;
	}

	public int getClientOptions() {
		return mFlags;
	}

	public ClientService getClientService() {
		return aey;
	}

	public C1245b getEventInfo() {
		return aev;
	}

	public OverlayCallbacks getOverlayCallback() {
		return aeG;
	}

	public void setOverlayCallback(OverlayCallbacks callback) {
		aeG = callback;
	}

	public LayoutParams getParams() {
		return aeF;
	}

	public boolean isDestroyed() {
		return aeC;
	}

	public void setDestroyed(boolean isDestroyed) {
		aeC = isDestroyed;
	}
}
