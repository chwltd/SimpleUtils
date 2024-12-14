package com.chwltd.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.TypedValue;
import android.view.Gravity;

import com.google.android.material.tabs.TabLayout;

public class TabLayoutUtils {

  // 单一方法设置 TabLayout 的指示器
  private static void setIndicator(TabLayout tabLayout, Drawable indicatorDrawable, int gravity, boolean fullWidth) {
    tabLayout.setSelectedTabIndicator(indicatorDrawable);
    tabLayout.setSelectedTabIndicatorGravity(gravity);
    tabLayout.setTabIndicatorFullWidth(fullWidth);

    tabLayout.setInlineLabel(true);
    tabLayout.setUnboundedRipple(false);
    tabLayout.setTabRippleColor(null);
    tabLayout.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC);
  }

  public static void set(Context context, int type, TabLayout tabLayout) {
    Drawable indicatorDrawable;
    int gravity;
    boolean fullWidth = false;

    switch (type) {
      case 1:
        indicatorDrawable = createLineIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_BOTTOM;
        break;
      case 2:
        indicatorDrawable = createRoundCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 3:
        indicatorDrawable = createGoogleIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_BOTTOM;
        fullWidth = false;
        break;
      case 4:
        indicatorDrawable = createBottomCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_BOTTOM;
        fullWidth = false;
        break;
      case 5:
        indicatorDrawable = createRoundIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_BOTTOM;
        fullWidth = false;
        break;
      case 6:
        indicatorDrawable = createSmallCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_BOTTOM;
        fullWidth = false;
        break;
      case 7:
        indicatorDrawable = createCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 8:
        indicatorDrawable = createLeftLeafCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 9:
        indicatorDrawable = createRightLeafCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 10:
        indicatorDrawable = createLeftMsgCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 11:
        indicatorDrawable = createRightMsgCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
      case 12:
        indicatorDrawable = createMsgCardIndicator(context);
        gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
        fullWidth = true;
        break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
    }
    setIndicator(tabLayout, indicatorDrawable, gravity, fullWidth);
  }

  public static void setBbs(Context context, TabLayout tabLayout) {
    Drawable indicatorDrawable = createRoundCardIndicator(context);
    int gravity = TabLayout.INDICATOR_GRAVITY_STRETCH;
    boolean fullWidth = true;

    setIndicator(tabLayout, indicatorDrawable, gravity, fullWidth);
  }

  public static void set(Context context, TabLayout tabLayout, Drawable drawable, int gravity, boolean fullWidth) {
    setIndicator(tabLayout, drawable, gravity, fullWidth);
  }

  // 第一个样式
  private static Drawable createLineIndicator(Context context) {
    GradientDrawable gradientDrawable2 = new GradientDrawable();
    gradientDrawable2.setShape(GradientDrawable.RECTANGLE);

    GradientDrawable ring = new GradientDrawable();
    ring.setShape(GradientDrawable.RECTANGLE);
    int h = dipToPx(context, 6);
    ring.setSize(h, h);
    ring.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});

    RotateDrawable rotate = new RotateDrawable();
    rotate.setDrawable(ring);
    rotate.setFromDegrees(45.0f);
    rotate.setPivotX(0.5f);
    rotate.setPivotY(0.5f);
    rotate.setLevel(30);

    Drawable[] layers = new Drawable[2];
    layers[0] = rotate;
    layers[1] = gradientDrawable2;

    LayerDrawable layerDrawable = new LayerDrawable(layers);
    layerDrawable.setLayerSize(1, dipToPx(context, 2756), dipToPx(context, 3));
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerGravity(1, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInsetBottom(0, 0);
    layerDrawable.setLayerInsetRight(1, dipToPx(context, 2500));

    return layerDrawable;
  }

  // 第六个样式
  private static Drawable createCardIndicator(Context context) {
    int angle = dipToPx(context, 12);
    int m = dipToPx(context, 6);
    LayerDrawable layerDrawable = createCard(angle);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  // 第二个样式
  private static Drawable createRoundCardIndicator(Context context) {
    int angle = dipToPx(context, 130);
    int m = dipToPx(context, 3);
    LayerDrawable layerDrawable = createCard(angle);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  // 第三个样式
  private static Drawable createGoogleIndicator(Context context) {
    int angle = dipToPx(context, 3);
    int h = dipToPx(context, 3);
    int m = dipToPx(context, 2);

    LayerDrawable layerDrawable = createCard(angle, 0, angle, 0);
    layerDrawable.setLayerHeight(0, h);
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInset(0, m, 0, m, 0);

    return layerDrawable;
  }

  // 第四个样式
  private static Drawable createBottomCardIndicator(Context context) {
    int angle = dipToPx(context, 3);
    int h = dipToPx(context, 6);
    int m = dipToPx(context, 5);

    LayerDrawable layerDrawable = createCard(angle);
    layerDrawable.setLayerHeight(0, h);
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInsetBottom(0, m);
    return layerDrawable;
  }

  // 第五个样式
  private static Drawable createRoundIndicator(Context context) {
    int angle = dipToPx(context, 3);
    int h = dipToPx(context, 6);
    int m = dipToPx(context, 5);
    LayerDrawable layerDrawable = createCard(angle);
    layerDrawable.setLayerSize(0, h, h);
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInsetBottom(0, m);
    return layerDrawable;
  }

  // 第六个样式
  private static Drawable createSmallCardIndicator(Context context) {
    int angle = dipToPx(context, 4);
    int w = dipToPx(context, 23);
    int h = dipToPx(context, 5);
    int m = dipToPx(context, 5);
    LayerDrawable layerDrawable = createCard(angle);

    layerDrawable.setLayerSize(0, w, h);
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInsetBottom(0, m);
    return layerDrawable;
  }

  private static Drawable createLeftLeafCardIndicator(Context context) {
    int angle = dipToPx(context, 13);
    int m = dipToPx(context, 6);
    LayerDrawable layerDrawable = createCard(0, angle, angle, 0);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  private static Drawable createRightLeafCardIndicator(Context context) {
    int angle = dipToPx(context, 13);
    int m = dipToPx(context, 6);
    LayerDrawable layerDrawable = createCard(angle, 0, 0, angle);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  private static Drawable createLeftMsgCardIndicator(Context context) {
    int angle = dipToPx(context, 13);
    int m = dipToPx(context, 6);
    LayerDrawable layerDrawable = createCard(0, angle, angle, angle);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  private static Drawable createRightMsgCardIndicator(Context context) {
    int angle = dipToPx(context, 13);
    int m = dipToPx(context, 6);
    LayerDrawable layerDrawable = createCard(angle, angle, angle, 0);
    layerDrawable.setLayerInset(0, m, m, m, m);
    return layerDrawable;
  }

  private static Drawable createMsgCardIndicator(Context context) {
    int angle = dipToPx(context, 4);
    GradientDrawable gradientDrawable2 = new GradientDrawable();
    gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable2.setCornerRadii(new float[]{angle, angle, angle, angle, angle, angle, angle, angle});

    GradientDrawable ring = new GradientDrawable();
    ring.setShape(GradientDrawable.RECTANGLE);
    int h = dipToPx(context, 11);
    int b = dipToPx(context, 3);
    int m = dipToPx(context, 6);

    RotateDrawable rotate = new RotateDrawable();
    rotate.setDrawable(ring);
    rotate.setFromDegrees(45.0f);
    rotate.setPivotX(0.5f);
    rotate.setPivotY(0.5f);
    rotate.setLevel(45);

    Drawable[] layers = new Drawable[2];
    layers[0] = rotate;
    layers[1] = gradientDrawable2;

    LayerDrawable layerDrawable = new LayerDrawable(layers);
    layerDrawable.setLayerSize(0, h, h);
    layerDrawable.setLayerInsetBottom(0, b);
    layerDrawable.setLayerGravity(0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    layerDrawable.setLayerInset(1, m, m, m, m);

    return layerDrawable;
  }

  public static LayerDrawable createCard(int left1, int left2, int right1, int right2) {
    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable.setCornerRadii(new float[]{left1, left1, right1, right1, right2, right2, left2, left2});

    return new LayerDrawable(new Drawable[]{gradientDrawable});
  }

  public static LayerDrawable createCard(int left1, int left2, int right1, int right2, int m) {
    LayerDrawable layerDrawable = createCard(left1, left2, right1, right2);
    layerDrawable.setLayerInset(0, m, m, m, m);

    return layerDrawable;
  }

  public static LayerDrawable createCard(int num) {
    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable.setCornerRadii(new float[]{num, num, num, num, num, num, num, num});

    return new LayerDrawable(new Drawable[]{gradientDrawable});
  }

  public static LayerDrawable createCard(int num, int m) {
    LayerDrawable layerDrawable = createCard(num);
    layerDrawable.setLayerInset(0, m, m, m, m);

    return layerDrawable;
  }

  public static int dipToPx(Context context, int dipValue) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
  }

  public static int pxToDip(Context context, int pxValue) {
    return (int) (pxValue / context.getResources().getDisplayMetrics().density);
  }

  public static int pxToSp(Context context, int pxValue) {
    return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
  }

  public static int spToPx(Context context, int spValue) {
    return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity);
  }
}