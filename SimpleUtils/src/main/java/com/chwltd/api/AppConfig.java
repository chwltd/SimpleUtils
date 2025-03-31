package com.chwltd.api;

import java.lang.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class AppConfig {
    public static boolean themeType;
    //文本颜色
    public static String themeTextColor;
    //列表背景
    public static String themeItemBgColor;
    //tab颜色
    public static String themeTabColor;
    //背景颜色
    public static String themeBgColor;
    //触摸颜色
    public static String themeRippleColor;
    //内容颜色
    public static String themeContentColor;
    //顶部栏颜色
    public static String themeTopBgColor;
    //广告位底部颜色
    public static String themeBannerBgColor;
    //总体主题色(打开)
    public static String themeOnColor;
    //总体主题色(关闭)
    public static String themeOffColor;
    //总体主题色2(打开)
    public static String themeOnColor2;
    //总体主题色2(关闭)
    public static String themeOffColor2;
    //分割线颜色
    public static String themeLineColor;
    //占位符
    public static Drawable themeOccupancy;
    //错误符
    public static Drawable themeError;
    //自动轮播速度
    public static int autoViewPagerSpeed;
    //加载图片渐变动画速度
    public static int loadImageSpeed;
    //Toast背景颜色
    public static String themeToastBgColor;
    //Toast背景颜色
    public static String themePostLabelBgColor;
    //称号颜色数据
    public static String userTitleColorData = "{\"测试\":{\"TextColor\":\"#ffffff\",\"TitleColor\":\"#2196F3\"}}";

    public static void setTheme(boolean type){
        setTheme(type,themeOnColor,themeOffColor);
    }

    public static void setTheme(boolean type,String onColor,String offColor){
        if(type==false){
            themeTextColor = "#ffffff";
            themeItemBgColor = "#222222";
            themeTabColor = "#222222";
            themeBgColor = "#111111";
            themeRippleColor = "#666666";
            themeContentColor = "#888888";
            themeTopBgColor = "#444444";
            themeBannerBgColor = "#444444";
            themeLineColor = "#444444";
            themeToastBgColor = "#cc666666";
            themePostLabelBgColor = "#444444";
        }else{
            themeTextColor = "#222222";
            themeItemBgColor = "#ffffff";
            themeTabColor = "#ffffff";
            themeBgColor = "#fafafa";
            themeRippleColor = "#e0e0e0";
            themeContentColor = "#9e9e9e";
            themeTopBgColor = "#ffffff";
            themeBannerBgColor = "#444444";
            themeLineColor = "#e0e0e0";
            themeToastBgColor = "#cc222222";
            themePostLabelBgColor = "#f0f0f0";
        }
        themeOnColor = onColor;
        themeOffColor = offColor;
        themeOnColor2 = "#ffffff";
        themeOffColor2 = "#e0e0e0";
        themeType=type;

        themeOccupancy = new ColorDrawable(Color.parseColor(themeRippleColor));
        //themeOccupancy = createSimpleDrawable();

        themeError = new ColorDrawable(Color.parseColor("#666666"));
        autoViewPagerSpeed = 3000;
        loadImageSpeed = 200;
    }

    public static Drawable createSimpleDrawable() {
        // 设置文字画笔，用于绘制中间的英文单词 "Simple"
        Paint textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#666666"));
        textPaint.setTextSize(40); // 设置文字大小，可按需调整

        // 先获取文字的边界矩形，以此来确定合适的宽高尺寸
        Rect textBounds = new Rect();
        textPaint.getTextBounds("至简Simple", 0, "至简Simple".length(), textBounds);

        // 根据文字边界矩形适当增加一些边距，确定Bitmap的宽高，这里左右各加10px边距，上下加20px边距，可按需调整
        int width = textBounds.width()+300;
        int height = textBounds.height()+150;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // 设置底色画笔并绘制底色
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#e0e0e0"));
        canvas.drawRect(0, 0, width, height, backgroundPaint);

        // 重新计算文字在水平和垂直方向上的居中位置
        int x = (width - textBounds.width()) / 2;
        int y = (height + textBounds.height()) / 2;

        // 绘制文字
        canvas.drawText("至简Simple", x, y, textPaint);

        // 将绘制好的Bitmap包装成Drawable对象并返回
        return new BitmapDrawable(bitmap);
    }
}