package com.chwltd.api;

import java.lang.*;

public class AppTheme {
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
        }
        themeOnColor = onColor;
        themeOffColor = offColor;
        themeOnColor2 = "#ffffff";
        themeOffColor2 = "#e0e0e0";
        themeType=type;
    }
}