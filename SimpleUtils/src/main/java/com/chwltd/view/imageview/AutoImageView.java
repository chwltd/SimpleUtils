package com.chwltd.view.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chwltd.utils.SystemUtils;

/**
 * 高度自适应ImageView，高度始终充满显示区域，宽度成比例缩放
 */
public class AutoImageView extends androidx.appcompat.widget.AppCompatImageView {
    private static int MIN_HEIGHT_DP;
    private static int MAX_HEIGHT_DP;

    public AutoImageView(Context context) {
        super(context);
        init();
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        MAX_HEIGHT_DP = (int) (SystemUtils.getScreenHeight(getContext())/2.5f);
        MIN_HEIGHT_DP = (int) (SystemUtils.getScreenWidth(getContext())/3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取图片的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        // 获取图片的实际宽高
        Drawable drawable = getDrawable();


        if (drawable != null) {
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();

            System.out.println("drawableWidth:"+drawableWidth);
            System.out.println("drawableHeight:"+drawableHeight);
            System.out.println("width:"+width);
            System.out.println("height:"+height);

            int maxHeightPx = MAX_HEIGHT_DP;
            int minHeightPx = MIN_HEIGHT_DP;
            int maxWidthPx = width;
            int minWidthPx = MIN_HEIGHT_DP;

            if(drawableWidth >= drawableHeight) {

                float scale = (float) drawableWidth / drawableHeight;

                if (drawableHeight > maxHeightPx) {
                    drawableHeight = maxHeightPx;
                } else if (drawableHeight < minHeightPx) {
                    drawableHeight = minHeightPx;
                }

                drawableWidth = (int) (drawableHeight * scale);

                if(drawableWidth > maxWidthPx) {
                    drawableWidth = maxWidthPx;
                    setScaleType(ScaleType.CENTER_CROP);
                }

            }else{

                float scale = (float) drawableHeight / drawableWidth;

                if (drawableWidth > maxWidthPx) {
                    drawableWidth = maxWidthPx;
                } else if (drawableWidth < minWidthPx) {
                    drawableWidth = minWidthPx;
                }

                drawableHeight = (int) (drawableWidth * scale);

                if(drawableHeight > maxHeightPx) {
                    drawableHeight = maxHeightPx;
                    setScaleType(ScaleType.CENTER_CROP);
                }

            }

            // 设置测量的宽高
            setMeasuredDimension(drawableWidth, drawableHeight);
        } else {
            // 如果没有图片，使用默认的宽高
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
