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

package co.aoscp.lovegood.allapps;

import android.graphics.Canvas;
import com.android.launcher3.graphics.BitmapRenderer.Renderer;

public class ActionDragView implements Renderer {

    private MyDragPreviewProvider mDragPreview;
    private float mWidth;

    public ActionDragView(MyDragPreviewProvider dragPreview, float width) {
        mDragPreview = dragPreview;
        mWidth = width;
    }

    public void draw(Canvas canvas) {
        mDragPreview.drawDragView(canvas, mWidth);
    }
}
