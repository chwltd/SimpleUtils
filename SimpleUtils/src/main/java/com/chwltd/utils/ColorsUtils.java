package com.chwltd.utils;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.palette.graphics.Palette;

public class ColorsUtils {
    /*
    获取网络图片温和色
    */
    public static String getImageSortColor(String url){
        String color = null;
        Bitmap bp = ImageUtils.getBitMBitmap(url);
        return getBitmapSortColor(bp);
    }
    /*
    获取Bitmap温和色
    */
    public static String getBitmapSortColor(Bitmap bp){
        String color = null;
        Palette palette = Palette.from(bp).generate();
        if(Integer.toHexString(palette.getMutedSwatch().getRgb()) != null){
            color = Integer.toHexString(palette.getMutedSwatch().getRgb());
        }else if(Integer.toHexString(palette.getVibrantSwatch().getRgb()) != null){
            color = Integer.toHexString(palette.getVibrantSwatch().getRgb());
        }else if(Integer.toHexString(palette.getDominantSwatch().getRgb()) != null){
            color = Integer.toHexString(palette.getDominantSwatch().getRgb());
        }
        return "#"+color;
    }
    /*
    直接获取RGB
    */
  public static String getCenterColors(float person, int startColor, int endColor) {
    float redCurrent;
    float blueCurrent;
    float greenCurrent;
    float alphaCurrent;
    int redStart = Color.red(startColor);
    int blueStart = Color.blue(startColor);
    int greenStart = Color.green(startColor);
    int alphaStart = Color.alpha(startColor);

    int redEnd = Color.red(endColor);
    int blueEnd = Color.blue(endColor);
    int greenEnd = Color.green(endColor);
    int alphaEnd = Color.alpha(endColor);

    int redDifference = redEnd - redStart;
    int blueDifference = blueEnd - blueStart;
    int greenDifference = greenEnd - greenStart;
    int alphaDifference = alphaEnd - alphaStart;

    redCurrent = redStart + person * redDifference;
    blueCurrent = blueStart + person * blueDifference;
    greenCurrent = greenStart + person * greenDifference;
    alphaCurrent = alphaStart + person * alphaDifference;
    // return (int)redCurrent;
    int result =
        Color.argb((int) alphaCurrent, (int) redCurrent, (int) greenCurrent, (int) blueCurrent);
    String colors = Integer.toHexString(result);
    String results = "#" + colors;
    return results;
  }
    /*
    获取中间色
    */
  public static int getCenterColor(float person, int startColor, int endColor) {
    float redCurrent;
    float blueCurrent;
    float greenCurrent;
    float alphaCurrent;
    int redStart = Color.red(startColor);
    int blueStart = Color.blue(startColor);
    int greenStart = Color.green(startColor);
    int alphaStart = Color.alpha(startColor);

    int redEnd = Color.red(endColor);
    int blueEnd = Color.blue(endColor);
    int greenEnd = Color.green(endColor);
    int alphaEnd = Color.alpha(endColor);

    int redDifference = redEnd - redStart;
    int blueDifference = blueEnd - blueStart;
    int greenDifference = greenEnd - greenStart;
    int alphaDifference = alphaEnd - alphaStart;

    redCurrent = redStart + person * redDifference;
    blueCurrent = blueStart + person * blueDifference;
    greenCurrent = greenStart + person * greenDifference;
    alphaCurrent = alphaStart + person * alphaDifference;
    // return (int)redCurrent;
    int result =
        Color.argb((int) alphaCurrent, (int) redCurrent, (int) greenCurrent, (int) blueCurrent);

    return result;
  }
}
