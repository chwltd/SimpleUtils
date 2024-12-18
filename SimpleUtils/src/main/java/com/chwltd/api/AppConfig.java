package com.chwltd.api;

import java.lang.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

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
        }
        themeOnColor = onColor;
        themeOffColor = offColor;
        themeOnColor2 = "#ffffff";
        themeOffColor2 = "#e0e0e0";
        themeType=type;

        themeOccupancy = new ColorDrawable(Color.parseColor("#e0e0e0"));
        themeError = new ColorDrawable(Color.parseColor("#666666"));
        autoViewPagerSpeed = 3000;
        loadImageSpeed = 200;
    }
}