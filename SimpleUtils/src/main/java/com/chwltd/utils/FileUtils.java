package com.chwltd.utils;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import com.chwltd.tools.*;
import java.io.*;
import java.math.*;

public class FileUtils {
    	//带密码压缩文件
	public static boolean zipFile(String path,String path1,String pass)
	{
		return ZipUtil.zipFile(path,path1,pass);
	}
	//带密码解压文件
	public static boolean unzipFile(String path,String path1,String pass)
	{
		return ZipUtil.unzipFile(path,path1,pass);
	}
	
	
	//安装apk
	public static void installApk(Context context, String apkFilePath) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri apkUri;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			apkUri=FileProvider.getUriForFile(context, context.getPackageName()+".myFileProvider", new File(apkFilePath));
		} else {
			apkUri=Uri.fromFile(new File(apkFilePath));
		}

		intent.setDataAndType(apkUri,"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	//获取apk基本信息
	public static String apkInfo(String absPath,Context context) { 
		PackageManager pm = context.getPackageManager(); 
		PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath,PackageManager.GET_ACTIVITIES); 
		if (pkgInfo != null) { 
			ApplicationInfo appInfo = pkgInfo.applicationInfo; 
			appInfo.sourceDir = absPath; 
			appInfo.publicSourceDir = absPath; 
			String appName = pm.getApplicationLabel(appInfo).toString();// 得到应用名 
			String packageName = appInfo.packageName; // 得到包名 
			String version = pkgInfo.versionName; // 得到版本信息 
			return String.format("PackageName:%s,Vesion:%s,AppName:%s", packageName, version, appName); 
		} 
		return null;
	}
	
	public static Bitmap apkIcon(String absPath,Context context) { 
		PackageManager pm = context.getPackageManager(); 
		PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath,PackageManager.GET_ACTIVITIES); 
		if (pkgInfo != null) { 
			ApplicationInfo appInfo = pkgInfo.applicationInfo; 
			appInfo.sourceDir = absPath; 
			appInfo.publicSourceDir = absPath; 
			Drawable icon = appInfo.loadIcon(pm);
			return ((BitmapDrawable)icon).getBitmap();
		} 
		return null;
	}
	
	//加解密文件
	public static String aesFile(int type,String a,String b,String key)
	{
		if(type==0)
			return AES1.encryptFile(a,b,key);
		else if(type==1)
			return AES1.decryptFile(a,b,key);
		else
			return "error:type";
	}
	
    //创建文件夹
	public static boolean newFolder(String p)
	{
		return (new File(p)).mkdirs();
	}

	//删除文件夹(调用shell比java高效)
	public static void deleteFilefolder(String path) throws IOException
	{
		Runtime.getRuntime().exec("rm -rf " + path);
	}

	//删除文件
	public static void deleteFile(String path) throws IOException
	{
		Runtime.getRuntime().exec("rm -f " + path);
		
	}
	//获取文件大小
	public static String getFileSize(String path)
	{
		long length=(new File(path)).length();
		if (length < 1024)
		{
			return length + "B";
		}
		else if (length >= 1024 && length < 1024 * 1024)
		{
			return (new BigDecimal((double)length / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "KB";
		}
		else if (length >= 1024 * 1024 && length < 1024 * 1024 * 1024)
		{
			return (new BigDecimal((double)length / 1024 / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "MB";
		}
		else
		{	
			return (new BigDecimal((double)length / 1024 / 1024 / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "GB";
		}
	}
}
