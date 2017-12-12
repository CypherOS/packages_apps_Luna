package com.android.launcher3.aoscp;

import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.android.launcher3.Utilities;
import java.util.Calendar;
import java.util.TimeZone;

public class b {
    Drawable fb;
    int fc;
    int fd;
    int fe;
    LayerDrawable ff;
    int fg;
    private final Calendar fh = Calendar.getInstance();
    int fi;
    int fj;
    float scale;

    public b clone() {
        b bVar = null;
        if (this.fb == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.scale = this.scale;
        bVar2.fg = this.fg;
        bVar2.fi = this.fi;
        bVar2.fj = this.fj;
        bVar2.fc = this.fc;
        bVar2.fd = this.fd;
        bVar2.fe = this.fe;
        bVar2.fb = this.fb.getConstantState().newDrawable();
        bVar2.ff = bVar2.dI();
        if (bVar2.ff != null) {
            bVar = bVar2;
        }
        return bVar;
    }

    public LayerDrawable dI() {
        if (this.fb instanceof LayerDrawable) {
            return (LayerDrawable) this.fb;
        }
        if (this.fb instanceof AdaptiveIconDrawable) {
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) this.fb;
            if (adaptiveIconDrawable.getForeground() instanceof LayerDrawable) {
                return (LayerDrawable) adaptiveIconDrawable.getForeground();
            }
        }
        return null;
    }

    public boolean dG() {
        this.fh.setTimeInMillis(System.currentTimeMillis());
        int i = (this.fh.get(10) + (12 - this.fc)) % 12;
        int i2 = (this.fh.get(12) + (60 - this.fd)) % 60;
        int i3 = (this.fh.get(13) + (60 - this.fe)) % 60;
        boolean z = false;
        if (this.fg != -1 && this.ff.getDrawable(this.fg).setLevel((i * 60) + this.fh.get(12))) {
            z = true;
        }
        if (this.fi != -1 && this.ff.getDrawable(this.fi).setLevel(i2 + (this.fh.get(10) * 60))) {
            z = true;
        }
        if (this.fj == -1 || !this.ff.getDrawable(this.fj).setLevel(i3 * 10)) {
            return z;
        }
        return true;
    }

    public void dH(TimeZone timeZone) {
        this.fh.setTimeZone(timeZone);
    }
}
