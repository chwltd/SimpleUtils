package com.chwltd.view.scrollview;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/** 自定义ScrollView下拉二楼效果和上滑改变Title背景透明度 */
public class PullScrollView extends ScrollView {
  /** 下拉二楼效果参数 */
  // 阻尼系数,值越小下拉阻力就越大.
  private float SCROLL_RATIO = 0.8f;

  private float DOWN_Y = 0;
  private float MOVE_Dy = 0;
  private boolean isTwoViewMove = false;
  private boolean isTwoViewOpen = false;
  private boolean isRefreshOpen = false;

  private boolean isVibrate = true;
  // 下拉高度比例 开始震动和松开手指可打开二楼  值越大 下拉高度越大
  private float MOVE_SCALE_OPEN_TWO_VIEW = 0.4f;
  // 下拉二楼监听器
  private ScrollTwoViewListener mScrollTwoViewListener;
  private int mScreenHeight;
  private FrameLayout twoView;
  private LinearLayout.LayoutParams layoutParams;
  private NoTouchView noTouchView;

  /** 上滑改变Title背景透明度效果参数 */
  // 上滑计算比例值监听器
  private ScrollStateListener mScrollStateListener;

  private View headView;
  private boolean isHeadShow = true;

  public PullScrollView(Context context) {
    this(context, null);
  }

  public PullScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initCommon();
    initScreenHeight();
    // 确保获取正确的屏幕高度
  }

  private void initCommon() {
    setOverScrollMode(OVER_SCROLL_NEVER);
  }

  // 设置头部View 用于计算滑动时 比例值 通过比例值可改变Title背景透明度等
  public void setHeadView(View headView) {
    this.headView = headView;
  }

  public void setMOVE_SCALE_OPEN_TWO_VIEW(float num){
    MOVE_SCALE_OPEN_TWO_VIEW = num;
  }

  public void setSCROLL_RATIO(float num){
    SCROLL_RATIO = num;
  }

  // 设置二楼View
  public void setTwoView(FrameLayout twoView) {
    this.twoView = twoView;
    noTouchView = new NoTouchView(getContext());
    LayoutParams fl = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    noTouchView.setLayoutParams(fl);
    twoView.removeView(noTouchView);
    twoView.addView(noTouchView);
    setTwoViewTopMargin(-mScreenHeight);
  }

  private void initScreenHeight() {
    WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    int fullHeight = displayMetrics.heightPixels;

    // 尝试获取状态栏和导航栏的高度
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    int statusBarHeight = 0;
    if (resourceId > 0) {
      statusBarHeight = getResources().getDimensionPixelSize(resourceId);
    }

    resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
    int navigationBarHeight = 0;
    if (resourceId > 0) {
      navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
    }

    // 计算实际可用的屏幕高度
    mScreenHeight = fullHeight - statusBarHeight - navigationBarHeight;
  }


  public void setScrollStateListener(ScrollStateListener scrollStateListener) {
    mScrollStateListener = scrollStateListener;
  }

  public void setScrollTwoViewListener(ScrollTwoViewListener mScrollTwoViewListener) {
    this.mScrollTwoViewListener = mScrollTwoViewListener;
  }

  // 打开二楼View
  public void openTwoView() {
    if (twoView != null) {
      twoViewChangeAnim(false);
    }
  }

  // 关闭二楼View
  public void closeTwoView() {
    if (twoView != null) {
      twoViewChangeAnim(true);
    }
  }

  // 打开二楼View
  public void openRefresh() {
    if (twoView != null) {
            if(!isRefreshOpen){
                mScrollTwoViewListener.openRefresh();
            }
      isRefreshOpen = true;
      //mScrollTwoViewListener.openRefresh();
      refreshChangeAnim(false);
    }
  }

  // 关闭二楼View
  public void closeRefresh() {
    if (twoView != null) {
            if(isRefreshOpen){
                mScrollTwoViewListener.closeRefresh();
            }
      isRefreshOpen = false;
      //mScrollTwoViewListener.closeRefresh();
      refreshChangeAnim(true);
    }
  }

  // 二楼View是否打开
  public boolean isTwoViewOpen() {
    return isTwoViewOpen;
  }

  private void refreshChangeAnim(boolean isClose) {
    if (twoView != null) {
      LinearLayout.LayoutParams layoutParams =
          (LinearLayout.LayoutParams) twoView.getLayoutParams();
      final int startMargin = layoutParams.topMargin;
      final int endMargin =
          isClose
              ? -mScreenHeight
              : (int) -(mScreenHeight + (mScreenHeight / 2 - mScreenHeight / 1.5) / 1.5);
      ValueAnimator mValueAnim = ValueAnimator.ofInt(1);
      // 动画执行过程中的数值变化
      mValueAnim.addUpdateListener(
          new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator mAnim) {
              float fraction = mAnim.getAnimatedFraction(); // 0.0 -->1.0
              Integer evaluate = evaluate(fraction, startMargin, endMargin);
              setRefreshTopMargin(evaluate);
            }
          });
      mValueAnim.setDuration(250); // 设置动画执行时间
      mValueAnim.start();
    }
  }

  private void twoViewChangeAnim(boolean isClose) {
    if (twoView != null) {
      LinearLayout.LayoutParams layoutParams =
          (LinearLayout.LayoutParams) twoView.getLayoutParams();
      final int startMargin = layoutParams.topMargin;
      final int endMargin = isClose ? -mScreenHeight : 0;
      ValueAnimator mValueAnim = ValueAnimator.ofInt(1);
      // 动画执行过程中的数值变化
      mValueAnim.addUpdateListener(
          new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator mAnim) {
              float fraction = mAnim.getAnimatedFraction(); // 0.0 -->1.0
              Integer evaluate = evaluate(fraction, startMargin, endMargin);
              setTwoViewTopMargin(evaluate);
            }
          });
      mValueAnim.setDuration(250); // 设置动画执行时间
      mValueAnim.start();
    }
  }

  private void setTwoViewTopMargin(int topMargin) {
    if (layoutParams == null) {
      layoutParams =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mScreenHeight);
    }
    if (topMargin == -mScreenHeight) {
      // 完全关闭了二楼VIEW
      isTwoViewOpen = false;
      layoutParams.height = mScreenHeight;
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(false);
      }
      twoView.removeView(noTouchView);
      twoView.addView(noTouchView);
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.closeTwoView();
      }
    } else if (topMargin == 0) {
      // 完全打开了二楼View
      isTwoViewOpen = true;
      layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
      twoView.removeView(noTouchView);
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(true);
      }
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.openTwoView();
      }
    } else {
      // 正在打开或关闭二楼VIEW过程中
      layoutParams.height = mScreenHeight;
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(true);
      }
    }
    layoutParams.topMargin = topMargin;
    twoView.setLayoutParams(layoutParams);
    twoView.requestLayout();
  }

  private void setRefreshTopMargin(int topMargin) {
    if (layoutParams == null) {
      layoutParams =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mScreenHeight);
    }
    if (topMargin == -mScreenHeight) {
      // 完全关闭了二楼VIEW
      isTwoViewOpen = false;
      layoutParams.height = mScreenHeight;
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(false);
      }
      twoView.removeView(noTouchView);
      twoView.addView(noTouchView);

    } else if (topMargin == -(mScreenHeight + (mScreenHeight / 2 - mScreenHeight / 1.5) / 1.5)) {
      // 完全打开了二楼View
      isTwoViewOpen = true;
      layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
      twoView.removeView(noTouchView);
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(true);
      }
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.openTwoView();
      }
    } else {
      // 正在打开或关闭二楼VIEW过程中
      layoutParams.height = mScreenHeight;
      if (mScrollTwoViewListener != null) {
        mScrollTwoViewListener.showTwoView(true);
      }
    }
    layoutParams.topMargin = topMargin;
    twoView.setLayoutParams(layoutParams);
    twoView.requestLayout();
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (twoView != null) {
      int action = ev.getActionMasked();
      float MOVE_Y;
      switch (action) {
        case MotionEvent.ACTION_DOWN:
                if (isRefreshOpen) {
            isRefreshOpen = false;
            DOWN_Y = 0;
                    MOVE_Y = 0;
            // setTwoViewTopMargin(-mScreenHeight);

            closeTwoView();
          }
          if (getScaleY() == 0) {
            DOWN_Y = ev.getY();
          }
          break;
        case MotionEvent.ACTION_MOVE:
                if (isRefreshOpen) {
            isRefreshOpen = false;
            DOWN_Y = 0;
                    MOVE_Y = 0;
            // setTwoViewTopMargin(-mScreenHeight);

            closeTwoView();
          }
          if (getScrollY() == 0) {
            MOVE_Y = ev.getY();
            if (DOWN_Y == 0) {
              DOWN_Y = MOVE_Y;
            }
            MOVE_Dy = MOVE_Y - DOWN_Y;
            int TOW_VIEW_MOVE_DY = -mScreenHeight + (int) (MOVE_Dy * SCROLL_RATIO);
            if (TOW_VIEW_MOVE_DY >= -mScreenHeight && TOW_VIEW_MOVE_DY <= 0) {
              isTwoViewMove = true;
              setTwoViewTopMargin(TOW_VIEW_MOVE_DY);
              if ((MOVE_Dy / mScreenHeight) > MOVE_SCALE_OPEN_TWO_VIEW && MOVE_Dy > 0) {
                if (isVibrate) {
                  vibrate();
                  isVibrate = false;
                  mScrollTwoViewListener.showQuake(true);
                }
              } else {
                isVibrate = true;
                if ((MOVE_Dy / mScreenHeight) < MOVE_SCALE_OPEN_TWO_VIEW / 1.5
                    && (MOVE_Dy / mScreenHeight) > MOVE_SCALE_OPEN_TWO_VIEW / 2) {
                  // 判断是否刷新
                  mScrollTwoViewListener.showRefresh(true);
                } else if ((MOVE_Dy / mScreenHeight) < MOVE_SCALE_OPEN_TWO_VIEW / 1.5) {
                  // 判断是否可刷新
                  mScrollTwoViewListener.showRefresh(false);
                } else if ((MOVE_Dy / mScreenHeight) < MOVE_SCALE_OPEN_TWO_VIEW) {
                  // 判断是否震动
                  mScrollTwoViewListener.showQuake(false);
                }
              }
              return true;
            }
          } else {
            if (isTwoViewMove) {
              isTwoViewMove = false;
              DOWN_Y = 0;
              setTwoViewTopMargin(-mScreenHeight);
            }
          }
          break;
        case MotionEvent.ACTION_UP:
          // 判断是否可打开二楼
          if ((MOVE_Dy / mScreenHeight) > MOVE_SCALE_OPEN_TWO_VIEW) {
            DOWN_Y = 0;
            MOVE_Dy = 0;
            openTwoView();
            return true;
          } else if ((MOVE_Dy / mScreenHeight) < MOVE_SCALE_OPEN_TWO_VIEW
              && (MOVE_Dy / mScreenHeight) > MOVE_SCALE_OPEN_TWO_VIEW / 2) {
            // 判断是否刷新
            DOWN_Y = 0;
            MOVE_Dy = 0;
            openRefresh();
            return true;
          }
          DOWN_Y = 0;
          // setTwoViewTopMargin(-mScreenHeight);

          closeTwoView();
          break;
        default:
          break;
      }
    }
    return super.onTouchEvent(ev);
  }

  @Override
  protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    if (headView != null) {
      int headView_height = headView.getMeasuredHeight();
      float scale;
      if (t <= 0) {
        scale = 0.0f;
      } else if (t <= headView_height) {
        scale = (float) t / headView_height;
      } else {
        scale = 1.0f;
      }
      if (mScrollStateListener != null) {
        mScrollStateListener.scrollState(scale);
        if (scale > 0.5) {
          if (isHeadShow) {
            mScrollStateListener.changedState(false);
          }
          isHeadShow = false;
        } else {
          if (!isHeadShow) {
            mScrollStateListener.changedState(true);
          }
          isHeadShow = true;
        }
      }
    }
    super.onScrollChanged(l, t, oldl, oldt);
  }

  // 震动
  @SuppressLint("MissingPermission")
  private void vibrate() {
    try {
      Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(80);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 类型估值器
  public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
    int startInt = startValue;
    return (int) (startInt + fraction * (endValue - startInt));
  }

  // 滚动改变比例监听器
  public interface ScrollStateListener {
    // 滑动过程中比例值改变 scrollScale 0-1
    void scrollState(float scrollScale);

    // 滑动过程中  比例值大于0.5 isOpen=false 小于等于0.5  isOpen=true
    void changedState(boolean isOpen);
  }

  // 解决下拉过程中禁用二楼所有点击事件
  private class NoTouchView extends View {
    public NoTouchView(Context context) {
      super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return true;
    }
  }

  // 二楼View打开关闭监听器
  public interface ScrollTwoViewListener {
    // 是否已显示出二楼view 未开始下拉 isShow=false  下拉中isShow=true 完全打开二楼View isShow=true
    void showTwoView(boolean isShow);

    // 已打开二楼View
    void openTwoView();

    // 已关闭二楼View
    void closeTwoView();

    // 震动范围监听
    void showQuake(boolean isQuake);

    // 刷新状态监听
    void showRefresh(boolean isRefresh);

    // 开始刷新
    void openRefresh();

    // 关闭刷新
    void closeRefresh();
  }
}
