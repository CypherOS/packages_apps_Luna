package co.aoscp.lovegood.anim;

import android.view.animation.Interpolator;

public class ReverseInputInterpolator implements Interpolator {

    public final Interpolator mInterpolator;

    public ReverseInputInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public float getInterpolation(float input) {
        return mInterpolator.getInterpolation(((float) 1) - input);
    }
}
