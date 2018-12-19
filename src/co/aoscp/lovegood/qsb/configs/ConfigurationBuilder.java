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
package co.aoscp.lovegood.qsb.configs;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import co.aoscp.lovegood.LunaLauncher;
import co.aoscp.lovegood.LunaLauncher.LunaLauncherCallbacks;
import co.aoscp.lovegood.qsb.search.nano.SearchProto.a_search;
import co.aoscp.lovegood.qsb.search.nano.SearchProto.b_search;
import co.aoscp.lovegood.qsb.search.nano.SearchProto.c_search;
import co.aoscp.lovegood.qsb.search.nano.SearchProto.d_search;
import co.aoscp.lovegood.search.AppSearchProvider;

import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsRecyclerView;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.graphics.BitmapRenderer;
import com.android.launcher3.graphics.BitmapRenderer.Renderer;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.Themes;

import com.google.protobuf.nano.MessageNano;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationBuilder {

    public boolean mHasAllAppsDivider;
    public final LunaLauncher mLauncher;
    public BubbleTextView mBubbleTextView;
    public final Bundle mBundle = new Bundle();
    public final boolean mIsAllApps;
    public final c_search mNano = new c_search();
    public final BaseQsbView mQsbView;
    public final UserManagerCompat mUserManager;
	private BroadcastReceiver mLongClickReceiver;

    public ConfigurationBuilder(BaseQsbView qsbView, boolean isAllApps) {
        mQsbView = qsbView;
        mLauncher = qsbView.mLauncher;
        mIsAllApps = isAllApps;
        mUserManager = UserManagerCompat.getInstance(mLauncher);
		mLongClickReceiver = new BroadcastReceiver() {
			public static WeakReference<LunaLauncher> mWeakReference = new WeakReference(null);

			public static void getWeakReference(LunaLauncher launcher) {
				mWeakReference = new WeakReference(launcher);
			}

			public void onReceive(Context context, Intent intent) {
				LunaLauncher launcher = (LunaLauncher) mWeakReference.get();
				if (launcher != null) {
					ComponentKey searchUri = AppSearchProvider.uriToComponent(intent.getData(), context);
					LauncherActivityInfo resolveActivity = LauncherAppsCompat.getInstance(context)
					        .resolveActivity(new Intent("android.intent.action.MAIN")
							.setComponent(searchUri.componentName), searchUri.user);
					if (resolveActivity != null) {
						ItemDragListener onDragListener = new ItemDragListener(resolveActivity, intent.getSourceBounds());
						onDragListener.init(launcher, false);
						launcher.getDragLayer().setOnDragListener(onDragListener);
						ClipData clipData = new ClipData(new ClipDescription("", new String[]{onDragListener.getMimeType()}), new Item(""));
						Bundle bundle = new Bundle();
						bundle.putParcelable("clip_data", clipData);
						setResult(-1, null, bundle);
					}
				}
			}
		};
    }

    public static Intent getSearchIntent(Rect sourceBounds, View gIcon, View micIcon) {
        Intent intent = new Intent("com.google.nexuslauncher.FAST_TEXT_SEARCH");
        intent.setSourceBounds(sourceBounds);
        if (micIcon.getVisibility() != 0) {
            intent.putExtra("source_mic_alpha", 0.0f);
        }
        return intent.putExtra("source_round_left", true)
		        .putExtra("source_round_right", true)
				.putExtra("source_logo_offset", getCenter(gIcon, sourceBounds))
				.putExtra("source_mic_offset", getCenter(micIcon, sourceBounds))
				.putExtra("use_fade_animation", true)
				.setPackage(LunaLauncherCallbacks.SEARCH_PACKAGE).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    public void mo8434bW() {
        if (mNano.f491ez == null) {
            a_search en = mNano.f479en;
            a_search ez = new a_search();
            ez.f471ef = en.f471ef;
            int i = en.f472eg;
            int i2 = en.f470ee;
            ez.f472eg = i + i2;
            ez.f470ee = i2;
            ez.f473eh = en.f473eh;
            mNano.f491ez = ez;
        }
    }

    public AllAppsRecyclerView getAppsView() {
        return (AllAppsRecyclerView) mLauncher.findViewById(R.id.apps_list_view);
    }

    public int getBackgroundColor() {
        return ColorUtils.compositeColors(Themes.getAttrColor(mLauncher, R.attr.allAppsScrimColor), 
		        ColorUtils.setAlphaComponent(WallpaperColorInfo.getInstance(mLauncher).getMainColor(), 255));
    }

    public final b_search mo8435bZ(AppInfo appInfo, int n) {
        b_search b = new b_search();
        b.label = appInfo.title.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("icon_bitmap_");
        sb.append(n);
        b.f474ej = sb.toString();
        mBundle.putParcelable(b.f474ej, appInfo.iconBitmap);
        Uri uri = AppSearchProvider.buildUri(appInfo, mUserManager);
        b.f476el = uri.toString();
        b.f475ek = new Intent("com.google.android.apps.nexuslauncher.search.APP_LAUNCH", uri.buildUpon().appendQueryParameter("predictionRank", Integer.toString(n)).build()).toUri(0);
        return b;
    }

    public final RemoteViews searchIconTemplate() {
        RemoteViews remoteViews = new RemoteViews(mLauncher.getPackageName(), R.layout.apps_search_icon_template);
        int iconSize = mBubbleTextView.getIconSize();
        int horizontalPadding = (mBubbleTextView.getWidth() - iconSize) / 2;
        int paddingTop = mBubbleTextView.getPaddingTop();
        int paddingBottom = (mBubbleTextView.getHeight() - iconSize) - paddingTop;
        remoteViews.setViewPadding(android.R.id.icon, horizontalPadding, paddingTop, horizontalPadding, paddingBottom);
        int minPadding = Math.min((int) (((float) iconSize) * 0.12f), Math.min(horizontalPadding, Math.min(paddingTop, paddingBottom)));
        remoteViews.setViewPadding(R.id.click_feedback_wrapper, horizontalPadding - minPadding, paddingTop - minPadding, horizontalPadding - minPadding, paddingBottom - minPadding);
        remoteViews.setTextViewTextSize(android.R.id.title, 0, mLauncher.getDeviceProfile().allAppsIconTextSizePx);
        remoteViews.setViewPadding(android.R.id.title, mBubbleTextView.getPaddingLeft(), mBubbleTextView.getCompoundDrawablePadding() + mBubbleTextView.getIconSize(), mBubbleTextView.getPaddingRight(), 0);
        return remoteViews;
    }

    public final RemoteViews searchQsbTemplate() {
        int width;
        RemoteViews remoteViews = new RemoteViews(mLauncher.getPackageName(), R.layout.apps_search_qsb_template);
        int effectiveHeight = ((mQsbView.getHeight() - mQsbView.getPaddingTop()) - mQsbView.getPaddingBottom()) + 20;
        Bitmap mShadowBitmap = mQsbView.mShadowBitmap;
        if (mShadowBitmap != null) {
            int internalWidth = (mShadowBitmap.getWidth() - effectiveHeight) / 2;
            int verticalPadding = (mQsbView.getHeight() - mShadowBitmap.getHeight()) / 2;
            remoteViews.setViewPadding(R.id.qsb_background_container, mQsbView.getPaddingLeft() - internalWidth, verticalPadding, mQsbView.getPaddingRight() - internalWidth, verticalPadding);
            Bitmap bitmap = Bitmap.createBitmap(mShadowBitmap, 0, 0, mShadowBitmap.getWidth() / 2, mShadowBitmap.getHeight());
            Bitmap bitmap2 = Bitmap.createBitmap(mShadowBitmap, (mShadowBitmap.getWidth() - 20) / 2, 0, 20, mShadowBitmap.getHeight());
            remoteViews.setImageViewBitmap(R.id.qsb_background_1, bitmap);
            remoteViews.setImageViewBitmap(R.id.qsb_background_2, bitmap2);
            remoteViews.setImageViewBitmap(R.id.qsb_background_3, bitmap);
        }
        if (mQsbView.mMicIconView.getVisibility() != View.VISIBLE) {
            remoteViews.setViewVisibility(R.id.mic_icon, View.INVISIBLE);
        }
        View gIcon = mQsbView.findViewById(R.id.g_icon);
        if (mQsbView.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            width = mQsbView.getWidth() - gIcon.getRight();
        } else {
            width = gIcon.getLeft();
        }
        int horizontalPadding = width;
        remoteViews.setViewPadding(R.id.qsb_icon_container, horizontalPadding, 0, horizontalPadding, 0);
        return remoteViews;
    }

    public static Point getCenter(View view, Rect rect) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Point point = new Point();
        point.x = (location[0] - rect.left) + (view.getWidth() / 2);
        point.y = (location[1] - rect.top) + (view.getHeight() / 2);
        return point;
    }

    public void mo8437cd() {
        mNano.f490ey = "search_box_template";
        mBundle.putParcelable(mNano.f490ey, searchQsbTemplate());
        mNano.f488ew = R.id.g_icon;
        mNano.f489ex = mQsbView.mMicIconView.getVisibility() == View.VISIBLE ? 
		        R.id.mic_icon : 0;
        a_search viewBounds = getViewBounds(mLauncher.getDragLayer());
        a_search a_search = mNano.f479en;
        int topShift = a_search.f472eg + (mHasAllAppsDivider ? 0 : a_search.f470ee);
        viewBounds.f472eg += topShift;
        viewBounds.f470ee -= topShift;
        mNano.f485et = viewBounds;
        int i = viewBounds.f473eh;
        if (i > 0) {
            int i2 = viewBounds.f470ee;
            if (i2 > 0) {
                mBundle.putParcelable(mNano.f486eu, BitmapRenderer.createHardwareBitmap(i, i2, new SearchBoxRenderer(this, topShift)));
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid preview bitmap size. width: ");
        sb.append(viewBounds.f473eh);
        sb.append("hight: ");
        sb.append(viewBounds.f470ee);
        sb.append(" top shift: ");
        sb.append(topShift);
        Log.e("ConfigurationBuilder", sb.toString());
        viewBounds.f470ee = 0;
        viewBounds.f471ef = 0;
        viewBounds.f472eg = 0;
        viewBounds.f473eh = 0;
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        bitmap.setPixel(0, 0, 0);
        mBundle.putParcelable(mNano.f486eu, bitmap);
    }

    public void mo8432a(int i, Canvas canvas) {
        int save = canvas.save();
        canvas.translate(0.0f, (float) (-i));
        mo8433a(canvas, mLauncher.getAppsView().getRecyclerViewContainer());
        mo8433a(canvas, (View) mLauncher.getAppsView().getFloatingHeaderView());
        canvas.restoreToCount(save);
    }

    public void mo8433a(Canvas canvas, View view) {
        int[] array = new int[]{0, 0};
        mLauncher.getDragLayer().mapCoordInSelfToDescendant(mLauncher.getAppsView(), array);
        mLauncher.getDragLayer().mapCoordInSelfToDescendant(view, array);
        canvas.translate((float) (-array[0]), (float) (-array[1]));
        view.draw(canvas);
        canvas.translate((float) array[0], (float) array[1]);
    }

    public void mo8438ce() {
        int i;
        View view = null;
        AllAppsRecyclerView appsView = getAppsView();
        SpanSizeLookup spanSizeLookup = ((GridLayoutManager) appsView.getLayoutManager()).getSpanSizeLookup();
        int allAppsCols = Math.min(mLauncher.getDeviceProfile().inv.numColumns, appsView.getChildCount());
        int childCount = appsView.getChildCount();
        BubbleTextView[] bubbleTextViewArr = new BubbleTextView[allAppsCols];
        int i4 = -1;
        for (i = 0; i < childCount; i++) {
            ViewHolder childViewHolder = appsView.getChildViewHolder(appsView.getChildAt(i));
            if (childViewHolder.itemView instanceof BubbleTextView) {
                int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(childViewHolder.getLayoutPosition(), allAppsCols);
                if (spanGroupIndex >= 0) {
                    if (i4 >= 0 && spanGroupIndex != i4) {
                        view = childViewHolder.itemView;
                        break;
                    } else {
                        i4 = spanGroupIndex;
                        bubbleTextViewArr[((LayoutParams) childViewHolder.itemView.getLayoutParams()).getSpanIndex()] = (BubbleTextView) childViewHolder.itemView;
                    }
                } else {
                    continue;
                }
            }
        }
        if (bubbleTextViewArr.length == 0 || bubbleTextViewArr[0] == null) {
			Log.e("ConfigBuilder", "No icons rendered in all apps");
			mo8439cf();
			return;
        } else {
            mBubbleTextView = bubbleTextViewArr[0];
            mNano.f484es = allAppsCols;
            i = 0;
            for (int i2 = 0; i2 < bubbleTextViewArr.length; i2++) {
                if (bubbleTextViewArr[i2] == null) {
                    i = allAppsCols - i2;
                    allAppsCols = i2;
                    break;
                }
            }
            mHasAllAppsDivider = appsView.getChildViewHolder(bubbleTextViewArr[0]).getItemViewType() == 4;
            a_search lastColumn = getViewBounds(bubbleTextViewArr[allAppsCols - 1]);
            a_search firstColumn = getViewBounds(bubbleTextViewArr[0]);
            if (Utilities.isRtl(mLauncher.getResources())) {
                a_search temp = lastColumn;
                lastColumn = firstColumn;
                firstColumn = temp;
            }
            int i3 = lastColumn.f473eh;
            int totalIconDistance = lastColumn.f471ef - firstColumn.f471ef;
            int iconDistance = totalIconDistance / allAppsCols;
            firstColumn.f473eh = i3 + totalIconDistance;
            if (Utilities.isRtl(mLauncher.getResources())) {
                firstColumn.f471ef -= i * i3;
                firstColumn.f473eh += i * i3;
            } else {
                firstColumn.f473eh += (iconDistance + i3) * i;
            }
            mNano.f479en = firstColumn;
            if (!mHasAllAppsDivider) {
                firstColumn.f472eg -= firstColumn.f470ee;
            } else if (view != null) {
                a_search viewBounds3 = getViewBounds(view);
                viewBounds3.f473eh = firstColumn.f473eh;
                mNano.f491ez = viewBounds3;
            }
            mo8434bW();
        }
    }

    public void mo8439cf() {
        mNano.f484es = mLauncher.getDeviceProfile().inv.numColumns;
        int width = mLauncher.getHotseat().getWidth();
        int dimensionPixelSize = mLauncher.getResources().getDimensionPixelSize(R.dimen.dynamic_grid_edge_margin);
        a_search en = new a_search();
        en.f471ef = dimensionPixelSize;
        en.f473eh = (width - dimensionPixelSize) - dimensionPixelSize;
        en.f470ee = mLauncher.getDeviceProfile().allAppsCellHeightPx;
        mNano.f479en = en;
        mo8434bW();
        AlphabeticalAppsList apps = getAppsView().getApps();
        mBubbleTextView = (BubbleTextView) mLauncher.getLayoutInflater().inflate(R.layout.all_apps_icon, getAppsView(), false);
        ViewGroup.LayoutParams layoutParams = mBubbleTextView.getLayoutParams();
        layoutParams.height = en.f470ee;
        layoutParams.width = en.f473eh / mNano.f484es;
        if (!apps.getApps().isEmpty()) {
            mBubbleTextView.applyFromApplicationInfo((AppInfo) apps.getApps().get(0));
        }
        mBubbleTextView.measure(MeasureSpec.makeMeasureSpec(layoutParams.width, View.MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY));
        mBubbleTextView.layout(0, 0, layoutParams.width, layoutParams.height);
        ArrayList<b_search> list = new ArrayList(mNano.f484es);
        mNano.f480eo = (b_search[]) list.toArray(new b_search[list.size()]);
    }

    public static a_search getViewBounds(View view) {
        a_search a = new a_search();
        a.f473eh = view.getWidth();
        a.f470ee = view.getHeight();
        int[] array = new int[2];
        view.getLocationInWindow(array);
        a.f471ef = array[0];
        a.f472eg = array[1];
        return a;
    }

    public byte[] build() {
        mNano.f478em = getBackgroundColor();
        mNano.f482eq = Themes.getAttrBoolean(mLauncher, R.attr.isMainColorDark);
        if (mIsAllApps) {
            mo8438ce();
        } else {
            mo8439cf();
        }
        mNano.f481ep = "icon_view_template";
        mBundle.putParcelable(mNano.f481ep, searchIconTemplate());
        mNano.f483er = "icon_long_click";
        mBundle.putParcelable(mNano.f483er, PendingIntent.getBroadcast(
		        mLauncher, 2055, new Intent().setComponent(new ComponentName(mLauncher, mLongClickReceiver)), 
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT));
        mLongClickReceiver.getWeakReference(mLauncher);
        mNano.f487ev = getViewBounds(mQsbView);
        boolean z = mIsAllApps;
        mNano.f477eA = z;
        if (z) {
            mo8437cd();
        }
        d_search d = new d_search();
        d.f492eB = mNano;
        return MessageNano.toByteArray(d);
    }

    public Bundle getExtras() {
        return mBundle;
    }

	public class SearchBoxRenderer implements Renderer {
		private ConfigBuilder mConfigBuilder;
		private int mTopShift;

		public SearchBoxRenderer(ConfigBuilder configBuilder, int topShift) {
			mConfigBuilder = configBuilder;
			mTopShift = topShift;
		}

		public final void draw(Canvas canvas) {
			mConfigBuilder.mo8432a(topShift, canvas);
		}
	}
}