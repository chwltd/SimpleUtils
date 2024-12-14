package com.chwltd.view.viewpager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class FreeScrollViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public FreeScrollViewPager(Context context){
        super(context);
    }
    
    public FreeScrollViewPager(Context context,AttributeSet sttrs){
        super(context,sttrs);
    }
    
    public void setIsCanScroll(boolean can){
        this.isCanScroll = can;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO: Implement this method
        if(isCanScroll){
                    return super.onTouchEvent(arg0);
        }
        return false;
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO: Implement this method
        if(isCanScroll){
                    return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }
    
    @Override
    public void scrollTo(int arg0, int arg1) {
        super.scrollTo(arg0, arg1);
        // TODO: Implement this method
    }
    
    @Override
    public void setCurrentItem(int arg0, boolean arg1) {
        super.setCurrentItem(arg0, arg1);
        // TODO: Implement this method
    }
    
    @Override
    public void setCurrentItem(int arg0) {
        super.setCurrentItem(arg0);
        // TODO: Implement this method
    }
    
}
