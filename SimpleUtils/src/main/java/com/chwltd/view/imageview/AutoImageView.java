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
    private static final int MIN_HEIGHT_DP = 120;
    private static final int MAX_HEIGHT_DP = 350;

    public AutoImageView(Context context) {
        super(context);
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

            int maxHeightPx = SystemUtils.dp2px(MAX_HEIGHT_DP);
            int minHeightPx = SystemUtils.dp2px(MIN_HEIGHT_DP);
            int maxWidthPx = width;
            int minWidthPx = SystemUtils.dp2px(MIN_HEIGHT_DP);

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
                    drawableWidth = maxWidthPx;
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