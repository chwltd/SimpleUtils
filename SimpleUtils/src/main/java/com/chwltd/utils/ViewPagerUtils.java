package com.chwltd.utils;

import android.content.Context;
import android.widget.Scroller;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import android.annotation.SuppressLint;
import android.view.View;

public class ViewPagerUtils {
  /** 设置viewpager 之间的切换速度 */
  public static void initSwitchTime(Context context, ViewPager viewPager, int time) {
    try {
      Field field = ViewPager.class.getDeclaredField("mScroller");
      field.setAccessible(true);
      field.set(viewPager, new ViewPagerScroller(context, time));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static class ViewPagerScroller extends Scroller {
    int time;

    public ViewPagerScroller(Context context, int time) {
      super(context);
      this.time = time;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
      super.startScroll(startX, startY, dx, dy, time);
    }
  }

  /** 设置ViewPager的动画 */
  public static void setZoomOutPageTransformer(ViewPager mVp) {
    mVp.setPageTransformer(
        true,
        new ViewPager.PageTransformer() {

          private static final float MIN_SCALE = 0.85f;
          private static final float MIN_ALPHA = 0.5f;

          public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) {
              // [-Infinity,-1)
              // This page is way off-screen to the left.
              view.setAlpha(0);

            } else if (position <= 1)
            // a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            {
              // [-1,1]
              // Modify the default slide transition to shrink the page as well
              float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
              float vertMargin = pageHeight * (1 - scaleFactor) / 2;
              float horzMargin = pageWidth * (1 - scaleFactor) / 2;
              if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
              } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
              }

              // Scale the page down (between MIN_SCALE and 1)
              view.setScaleX(scaleFactor);
              view.setScaleY(scaleFactor);

              // Fade the page relative to its size.
              view.setAlpha(
                  MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
              // (1,+Infinity]
              // This page is way off-screen to the right.
              view.setAlpha(0);
            }
          }
        });
  }
}
