package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1971H;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1972I;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1988y;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1989z;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.f */
public class C1709f implements C0874A {
    private final Context context;
    /* renamed from: xd */
    C0918e f2400xd;
    /* renamed from: xw */
    final C0920h f2401xw;
    /* renamed from: xx */
    final RectF f2402xx = new RectF();
    /* renamed from: xy */
    C1968E f2403xy;
    /* renamed from: xz */
    public C0938z f2404xz;

    /* renamed from: a */
    public final void mo3300a(Menu menu) {
        mo5355a(null, menu, this.f2400xd);
    }

    /* renamed from: a */
    public final boolean mo3301a(MenuItem menuItem) {
        String str;
        String valueOf;
        if (this.f2403xy != null) {
            if (this.f2400xd != null) {
                C1989z[] c1989zArr = this.f2403xy.f3137wl;
                if (c1989zArr == null) {
                    return false;
                }
                int itemId = menuItem.getItemId() - 16908353;
                if (itemId >= 0) {
                    if (itemId < c1989zArr.length) {
                        C1989z c1989z = c1989zArr[itemId];
                        if (c1989z == null) {
                            return false;
                        }
                        C1988y c1988y = c1989z.f3269vX;
                        if (c1988y != null) {
                            c1988y.f3260vR = c1989z.f3268vR;
                            if (c1988y.f3264vV != null) {
                                Intent a = mo5354a(c1988y.f3264vV, true);
                                if (a == null) {
                                    return true;
                                }
                                try {
                                    if (this.f2403xy != null) {
                                        this.f2401xw.mo3379b(c1988y, this.f2403xy);
                                    }
                                    if (a.getComponent() == null || TextUtils.isEmpty(a.getComponent().getClassName())) {
                                        this.context.startActivity(a);
                                    } else {
                                        this.context.sendBroadcast(a);
                                    }
                                } catch (Throwable e) {
                                    str = "Error launching intent for ";
                                    valueOf = String.valueOf(c1989z.f3268vR);
                                    C0936x.m647a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
                                    return false;
                                }
                            }
                            PendingIntent pendingIntent;
                            C0918e c0918e = (C0918e) C0915av.m613t(this.f2400xd);
                            String str2 = c1988y.f3258id;
                            if (c0918e.f1128xu == null) {
                                String str3 = "Unable to find pending intent for ";
                                str2 = String.valueOf(str2);
                                C0936x.m647a(str2.length() != 0 ? str3.concat(str2) : new String(str3), new Throwable());
                                pendingIntent = null;
                            } else {
                                EntitiesData entitiesData = (EntitiesData) C0915av.m613t(c0918e.f1128xu);
                                int size = entitiesData.f997ut.size();
                                StringBuilder stringBuilder = new StringBuilder(23 + String.valueOf(str2).length());
                                stringBuilder.append("Accessing ");
                                stringBuilder.append(str2);
                                stringBuilder.append("; ");
                                stringBuilder.append(size);
                                Log.e("TEST", stringBuilder.toString());
                                pendingIntent = (PendingIntent) entitiesData.f997ut.get(str2);
                            }
                            if (this.f2403xy != null) {
                                this.f2401xw.mo3379b(c1988y, this.f2403xy);
                            }
                            if (pendingIntent != null) {
                                try {
                                    pendingIntent.send();
                                } catch (Throwable e2) {
                                    str = "Error launching intent for ";
                                    valueOf = String.valueOf(c1989z.f3268vR);
                                    C0936x.m647a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e2);
                                    return false;
                                } catch (Throwable e22) {
                                    str = "Pending Intent canceled for ";
                                    valueOf = String.valueOf(c1989z.f3268vR);
                                    C0936x.m647a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e22);
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder(48);
                stringBuilder2.append("Invalid menu item clicked; item id = ");
                stringBuilder2.append(itemId);
                C0936x.m653h(stringBuilder2.toString());
                return false;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final void mo3302b(Rect rect) {
        if (this.f2403xy != null) {
            if (this.f2404xz != null) {
                rect.set((int) this.f2402xx.left, (int) this.f2402xx.top, (int) this.f2402xx.right, (int) this.f2402xx.bottom);
            }
        }
    }

    /* renamed from: a */
    final void mo5355a(ActionMode actionMode, Menu menu, C0918e c0918e) {
        if (this.f2403xy != null) {
            C1989z[] c1989zArr = this.f2403xy.f3137wl;
            if (c1989zArr != null) {
                menu.clear();
                SubMenu subMenu = null;
                for (int i = 0; i < c1989zArr.length; i++) {
                    C1989z c1989z = c1989zArr[i];
                    if (!(c1989z == null || c1989z.f3269vX == null)) {
                        MenuItem add;
                        if (c1989z.f3271vZ) {
                            if (subMenu == null) {
                                subMenu = menu.addSubMenu(16908353, (c1989zArr.length + i) + 16908353, c1989zArr.length + i, "");
                                subMenu.getItem().setShowAsAction(0);
                                subMenu.getItem().collapseActionView();
                            }
                            add = subMenu.add(16908353, i + 16908353, (c1989zArr.length + i) + 1, c1989z.f3268vR);
                            add.setShowAsAction(0);
                            add.setOnActionExpandListener(new C0919g(this, c1989z));
                            m2249a(c1989z, add, c0918e);
                        } else {
                            add = menu.add(16908353, i + 16908353, i, c1989z.f3268vR);
                            add.setContentDescription(c1989z.f3268vR);
                            add.setShowAsAction(2);
                            m2249a(c1989z, add, c0918e);
                            this.f2401xw.mo3378a(c1989z.f3269vX, (C1968E) C0915av.m613t(this.f2403xy));
                        }
                    }
                }
                if (!(actionMode == null || this.f2404xz == null)) {
                    this.f2404xz.mo3405a(actionMode);
                }
            }
        }
    }

    /* renamed from: a */
    final Intent mo5354a(C1971H c1971h, boolean z) {
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(c1971h.f3155wC)) {
            intent.setAction(c1971h.f3155wC);
        }
        if (!TextUtils.isEmpty(c1971h.packageName) && !TextUtils.isEmpty(c1971h.className)) {
            intent.setClassName(c1971h.packageName, c1971h.className);
        } else if (!TextUtils.isEmpty(c1971h.packageName)) {
            intent.setPackage(c1971h.packageName);
        }
        if (!TextUtils.isEmpty(c1971h.f3156wD)) {
            intent.setData(Uri.parse(c1971h.f3156wD));
        }
        if (!TextUtils.isEmpty(c1971h.mimeType)) {
            intent.setType(c1971h.mimeType);
        }
        if (c1971h.flags != 0) {
            intent.setFlags(c1971h.flags);
        }
        for (C1972I c1972i : c1971h.f3154wB) {
            switch (c1972i.type) {
                case 1:
                    intent.putExtra(c1972i.name, c1972i.f3158wF);
                    break;
                case 2:
                    intent.putExtra(c1972i.name, c1972i.f3159wG);
                    break;
                case 3:
                    intent.putExtra(c1972i.name, c1972i.f3160wH);
                    break;
                case 4:
                    intent.putExtra(c1972i.name, c1972i.f3161wI);
                    break;
                case 5:
                    if (z) {
                        intent.putExtra(c1972i.name, mo5354a(c1972i.f3162wJ, false));
                        break;
                    }
                    throw new IllegalArgumentException("Only ONE level of nested intent is allowed");
                case 6:
                    intent.putExtra(c1972i.name, Uri.parse(c1972i.f3163wf));
                    break;
                default:
                    int i = c1972i.type;
                    StringBuilder stringBuilder = new StringBuilder(61);
                    stringBuilder.append("Menu action error: unknown menu intent param type ");
                    stringBuilder.append(i);
                    C0936x.m653h(stringBuilder.toString());
                    return null;
            }
        }
        return intent;
    }

    /* renamed from: a */
    private void m2249a(C1989z c1989z, MenuItem menuItem, C0918e c0918e) {
        if (c0918e != null && c1989z.f3269vX != null && !TextUtils.isEmpty(c1989z.f3269vX.f3258id)) {
            Bitmap bitmap;
            String str = (String) C0915av.m613t(c1989z.f3269vX.f3258id);
            if (c0918e.f1128xu == null) {
                String str2 = "Unable to find icon for ";
                str = String.valueOf(str);
                C0936x.m647a(str.length() != 0 ? str2.concat(str) : new String(str2), new Throwable());
                bitmap = null;
            } else {
                bitmap = (Bitmap) ((EntitiesData) C0915av.m613t(c0918e.f1128xu)).f996us.get(str);
            }
            if (bitmap != null) {
                menuItem.setIcon(new BitmapDrawable(this.context.getResources(), bitmap));
                String str3 = "Setting icon for ";
                String valueOf = String.valueOf(c1989z.f3268vR);
                C0936x.m650e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            }
        }
    }

    C1709f(Context context, C0920h c0920h) {
        this.context = context;
        this.f2401xw = c0920h;
    }
}
