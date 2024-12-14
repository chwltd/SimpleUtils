package com.chwltd.tools;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.database.Cursor;

import java.text.ParseException;
import androidx.viewpager.widget.ViewPager;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.File;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.transition.TransitionManager;
import android.transition.AutoTransition;

public class Component {


    public static void loadauto(Activity activity,int id,int speed)
    {
        try
        {
            AutoTransition autotran=new AutoTransition();
            autotran.setDuration(speed);
            TransitionManager.beginDelayedTransition(activity.findViewById(id),autotran);
        }
        catch (Exception e)
        {

        }
    }

    public static List getImagePathsFromClipData(Context context, ClipData clipData) {
        List imagePaths = new ArrayList();

        if (clipData != null) {
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item item = clipData.getItemAt(i);
                Uri imageUri = item.getUri();
                String uridata = imageUri.toString();
                String num= getBetween(uridata,"%3A",null);
                imageUri=Uri.parse("content://media/external_primary/images/media/"+num);
                if (imageUri != null) {
                    if (imageUri.getScheme().equals("file")) {
                        String imagePath = imageUri.getPath();
                        imagePaths.add(imagePath);
                    } else if (imageUri.getScheme().equals("content")) {
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor cursor = context.getContentResolver().query(imageUri, proj, null, null, null);
                        if (cursor != null) {
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            String imagePath = cursor.getString(column_index);
                            imagePaths.add(imagePath);
                            cursor.close();
                        }
                    }
                }
            }
        }

        return imagePaths;
    }

    public static String getImagePathsFromData(Context context, Uri imageUri) {
        String imagePath = null;
        if (imageUri != null) {
            if (imageUri.getScheme().equals("file")) {
                imagePath = imageUri.getPath();
            } else if (imageUri.getScheme().equals("content")) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(imageUri, proj, null, null, null);
                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    imagePath = cursor.getString(column_index);
                    cursor.close();
                }
            }
        }
        return imagePath;
    }

    public static String getBetween(String str, String str2, String str3)
    {
        int i;
        int indexOf;
        if (str2 != null)
        {
            int indexOf2 = str.indexOf(str2);
            if (indexOf2 == -1)
            {
                return null;
            }
            i = indexOf2 + str2.length();
        }
        else
        {
            i = 0;
        }
        if (str3 == null)
        {
            indexOf = str.length();
        }
        else
        {
            indexOf = str.indexOf(str3, i);
            if (indexOf == -1)
            {
                return null;
            }
        }
        return str.substring(i, indexOf);
    }


    public static void pickImages(Activity activity) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(intent, 1);
    }

    public static void refreshimg(Activity activity,String 目录){
        File file = new File(目录);
        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
    }


    public static int isdark(int i1,int i2){
        return i1&i2;
    }



    private static final String PREF_NAME = "KeyValuePrefs";

    public static boolean saveData(Context context, String key, String value) {
        // 存储
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        return editor.commit();
    }


    public static String getValue(Context context, String key) {
        //读取
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }


    public static boolean removeKey(Context context, String key) {
        // 清除
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        return editor.commit();
    }


    public static String Package(Context mContext){
        try {
            PackageInfo packageInfo=mContext.getPackageManager().getPackageInfo(mContext.getPackageName(),PackageManager.GET_SIGNATURES);
            Signature[] signatures=packageInfo.signatures;
            return signatures[0].toCharsString();
        } catch (PackageManager.NameNotFoundException e) {}
        return null;
    }



    //uri
    public static String getRealPath(Context context, Uri uri)
    {
        String imagePath = null;
        if(DocumentsContract.isDocumentUri(context, uri))
        {
            // 如果是document类型的Uri，则通过document id处理

            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals(uri.getAuthority()))

            {

                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;

                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }

            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority()))

            {

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
            else if ("com.android.externalstorage.documents".equals(uri.getAuthority()))
            {
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type))
                {
                    imagePath = Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme()))
        {
            // content类型普通方式处理
            imagePath = getImagePath(context, uri, null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme()))
        {
            // file类型直接获取图片路径
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    @SuppressLint("Range")
    public static String getImagePath(Context context, Uri uri, String selection)
    {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //滑动窗体美化
    public static void setdh(ViewPager mVp)
    {
        mVp.setOffscreenPageLimit(3);
        mVp.setPageMargin(-10);
        mVp.setPageTransformer(true,new ViewPager.PageTransformer()
        {

            private static final float MIN_SCALE=0.85f;
            //private static final float MIN_ALPHA=0.4f;
            // @Override
            public void transformPage(View view,float position)
            {

                if(position<=-1)
                {
                    view.setScaleX(MIN_SCALE);
                    view.setScaleY(MIN_SCALE);
                    //view.setAlpha(MIN_ALPHA);
                }
                else if(position<=1)
                {
                    if(position<0)
                    {
                        float scaleA=MIN_SCALE+(1-MIN_SCALE)*(1+position);
                        view.setScaleX(scaleA);
                        view.setScaleY(scaleA);
                        //float alphaA=MIN_ALPHA+(1-MIN_ALPHA)*(1+position);
                        //view.setAlpha(alphaA);
                    }
                    else
                    {
                        float scaleB=MIN_SCALE+(1-MIN_SCALE)*(1-position);
                        view.setScaleX(scaleB);
                        view.setScaleY(scaleB);
                        //float alphaB=MIN_ALPHA+(1-MIN_ALPHA)*(1+position);
                        //view.setAlpha(alphaB);
                    }
                }
                else
                {
                    view.setScaleX(MIN_SCALE);
                    view.setScaleY(MIN_SCALE);
                    //view.setAlpha(MIN_ALPHA);
                }
            }

        });
    }

    public static String DistanceTime(String str) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        Long time1 = parse.getTime();
        time1 = time1/1000;
        long time2 = System.currentTimeMillis();
        time2 = time2/1000;
        //获得相差秒数
        long time=time2-time1;
        if(time<=60){
            return "刚刚";
        }
        else if(time<=3600){
            return time/60+"分钟前";
        }
        else if(time<=86400){
            return time/3600+"小时前";
        }
        else if(time<=604800){
            return time/86400+"天前";
        }
        else{
            return str;
        }
    }



    public static boolean DistanceTime(String str,String str2) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        Date parse2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str2);
        Long time1 = parse.getTime();
        time1 = time1/1000;
        long time2 = parse2.getTime();
        time2 = time2/1000;
        //获得相差秒数
        //long time=time2-time1;
        if(time2<time1){
            return false;
        }
        else{
            return true;
        }
    }



    public static String removeComments(String code) {
        StringBuilder result = new StringBuilder();
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
        boolean inQuotes = false;
        boolean inCharLiteral = false;
        int i = 0;

        while (i < code.length()) {
            char c = code.charAt(i);

            if (inSingleLineComment) {
                if (c == '\n') {
                    inSingleLineComment = false;
                    result.append("\n");
                }
            } else if (inMultiLineComment) {
                if (c == '.' && i < code.length() - 1 && code.charAt(i + 1) == '/') {
                    inMultiLineComment = false;
                    i++; // Skip '*'
                }
            } else if (inQuotes) {
                if (c == '"') {
                    inQuotes = false;
                }
                result.append(c);
            } else if (inCharLiteral) {
                if (c == '\'') {
                    inCharLiteral = false;
                }
                result.append(c);
            } else {
                if (c == '"' && (i == 0 || code.charAt(i - 1) != '\\')) {
                    inQuotes = true;
                    result.append(c);
                } else if (c == '\'' && (i == 0 || code.charAt(i - 1) != '\\')) {
                    inCharLiteral = true;
                    result.append(c);
                } else if (c == '/' && i < code.length() - 1 && code.charAt(i + 1) == '/') {
                    inSingleLineComment = true;
                    i++; // Skip '/'
                } else if (c == '/' && i < code.length() - 1 && code.charAt(i + 1) == '.') {
                    inMultiLineComment = true;
                    i++; // Skip '/'
                } else {
                    result.append(c);
                }
            }

            i++;
        }

        return result.toString();
    }

    //处理每行代码中的空格
    private static String processCode(String code) {
        // 按行分割代码
        String[] lines = code.split("\n");

        // 处理每一行代码
        StringBuilder processedCode = new StringBuilder();
        for (String line : lines) {
            // 清除开头的空格
            String processedLine = line.trim();

            // 添加到处理结果中
            processedCode.append(processedLine).append("\n");
        }

        // 移除最后一个换行符
        processedCode.deleteCharAt(processedCode.length() - 1);

        // 返回处理结果
        return processedCode.toString();
    }

    //集成程序
    private static String Process(String code) {
        String 注释 = removeComments(code);
        String 空格 = processCode(注释);
        return 空格;
    }
}
