/*
 * Copyright (C) 2018 CypherOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.aoscp.lovegood.anim;

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
