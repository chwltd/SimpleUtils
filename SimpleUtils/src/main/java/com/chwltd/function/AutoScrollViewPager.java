package com.chwltd.function;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class AutoScrollViewPager {

    private ViewPager viewPager;
    private Handler handler;
    private Runnable runnable;
    private int interval = 3000; // 默认轮播间隔为3000毫秒
    private boolean isAutoScroll = false;

    public AutoScrollViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        handler = new Handler();
        setupTouchListener();
    }

    // 设置轮播间隔
    public void setInterval(int interval) {
        this.interval = interval;
    }

    // 开始轮播
    public void startAutoScroll() {
        if (!isAutoScroll) {
            isAutoScroll = true;
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (isAutoScroll) {
                        int nextItem = (viewPager.getCurrentItem() + 1) % viewPager.getAdapter().getCount();
                        viewPager.setCurrentItem(nextItem, true);
                        handler.postDelayed(this, interval);
                    }
                }
            };
            handler.postDelayed(runnable, interval);
        }
    }

    // 暂停轮播
    public void stopAutoScroll() {
        if (isAutoScroll) {
            isAutoScroll = false;
            handler.removeCallbacks(runnable);
        }
    }

    // 设置触摸监听，触摸时暂停轮播
    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchListener() {
        viewPager.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoScroll();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    startAutoScroll();
                    break;
            }
            return false; // 继续处理触摸事件
        });
    }
}
