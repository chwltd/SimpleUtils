package com.chwltd.function;
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

    // 暂停轮播
    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeCallbacks(runnable);
    }

    // 设置触摸监听，触摸时暂停轮播
    private void setupTouchListener() {
        viewPager.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                stopAutoScroll();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                startAutoScroll();
            }
            return false; // 继续处理触摸事件
        });
    }
}