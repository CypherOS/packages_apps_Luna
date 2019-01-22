package co.aoscp.lovegood.micode.anim;

import android.view.animation.Interpolator;

public class ReverseOutputInterpolator implements Interpolator {

    public Interpolator mInterpolator;

    public ReverseOutputInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public float getInterpolation(float input) {
        return ((float) 1) - mInterpolator.getInterpolation(input);
    }
}
