package co.aoscp.lovegood.micode.anim;

import android.view.animation.Interpolator;

public class ReverseInputInterpolator implements Interpolator {

    public Interpolator mInterpolator;

    public ReverseInputInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public float getInterpolation(float input) {
        return mInterpolator.getInterpolation(((float) 1) - input);
    }
}
