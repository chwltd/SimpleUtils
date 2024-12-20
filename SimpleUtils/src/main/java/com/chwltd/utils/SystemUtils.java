package com.chwltd.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.icu.math.BigDecimal;
import android.media.AudioManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class SystemUtils {

	private static Toast toast = null;

	public static void CHToast(Context context,String text){
		if(toast == null) {
			toast = new Toast(context);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(getToastView(context,text));
		toast.show();
	}

	public static void CHToast(Context context,String text,int y){
		if(toast == null) {
			toast = new Toast(context);
		}
		toast.setGravity(Gravity.CENTER, 0, y);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(getToastView(context,text));
		toast.show();
	}

	public static void CHToast(Context context,String text,String color){
		if(toast == null) {
			toast = new Toast(context);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(getToastView(context,text,color));
		toast.show();
	}

	public static void CHToast(Context context,String text,String color,int y){
		if(toast == null) {
			toast = new Toast(context);
		}
		toast.setGravity(Gravity.CENTER, 0, y);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(getToastView(context,text,color));
		toast.show();
	}

	public static View getToastView(Context context,String text){
		int padding = SystemUtils.dp2px(15);
		int radius = SystemUtils.dp2px(10);
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		linearLayout.setPadding(padding,padding,padding,padding);
		TextView textView = new TextView(context);
		textView.setText(text);
		textView.setTextColor(Color.parseColor("#ffffff"));
		textView.setTextSize(16);
		textView.setMaxLines(2);
		textView.setEllipsize(TextUtils.TruncateAt.END);
		linearLayout.addView(textView);
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setColor(Color.parseColor("#cc666666"));
		gradientDrawable.setCornerRadius(radius);
		linearLayout.setBackground(gradientDrawable);
		return linearLayout;
	}

	public static View getToastView(Context context,String text,String color){
		int padding = SystemUtils.dp2px(15);
		int radius = SystemUtils.dp2px(10);
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		linearLayout.setPadding(padding,padding,padding,padding);
		TextView textView = new TextView(context);
		textView.setText(text);
		textView.setTextColor(Color.parseColor("#ffffff"));
		textView.setTextSize(16);
		textView.setMaxLines(2);
		textView.setEllipsize(TextUtils.TruncateAt.END);
		linearLayout.addView(textView);
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setColor(Color.parseColor(color));
		gradientDrawable.setCornerRadius(radius);
		linearLayout.setBackground(gradientDrawable);
		return linearLayout;
	}

	/**
	 * 获取屏幕宽度
	 * @param context 上下文
	 * @return 屏幕宽度（像素）
	 */
	public static int getScreenWidth(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		if (windowManager != null) {
			windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		}
		return displayMetrics.widthPixels; // 返回屏幕宽度（像素）
	}

	public static int getScreenHeight(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		if (windowManager != null) {
			windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		}
		return displayMetrics.heightPixels; // 返回屏幕高度（像素）
	}


	//获取Androidid
	public static String getAndroidId(Context context) {
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

  public static void changeStatusBarTextColor(Activity activity, boolean isLightText) {
	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
		  Window window = activity.getWindow();
		  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		  window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));

		  int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				  | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
		  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			  flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
		  }
		  if (isLightText) {
			  flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
		  } else {
			  flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
		  }
		  window.getDecorView().setSystemUiVisibility(flags);
	  } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		  Window window = activity.getWindow();
		  window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				  WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	  }
  }

	public static int getStatusBarHeightByReflection(Context context) {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			int heightId = Integer.parseInt(c.getField("status_bar_height").get(obj).toString());
			return context.getResources().getDimensionPixelSize(heightId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

		public static int getStatusBarHeight(Context context) {
			int statusBarHeight = 0;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
				if (resourceId > 0) {
					statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
				}
			}
			return statusBarHeight;
		}

		// 另一种方式通过获取屏幕可视区域来计算状态栏高度
		public static int getStatusBarHeight2(Context context) {
			Rect rect = new Rect();
			((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
			int statusBarHeight = rect.top;
			return statusBarHeight;
		}

  /*
  Mus类库
  */
  
	//判断是否授予忽略电池优化
	public static boolean canKeepRunning(Context activity)
	{
		try
		{
        	return ((int)Class.forName("androidx.core.content.ContextCompat").getMethod("checkSelfPermission", Class.forName("android.content.Context"), Class.forName("java.lang.String")).invoke(activity,"android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"))==0;
		}
		catch (Exception e)
		{
			return false;
		}

	}
   //请求授予忽略电池优化
	public static void requestKeepRunning(Activity activity) {
		Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
		intent.setData(Uri.parse("package:" + activity.getPackageName()));
		activity.startActivity(intent);
	}
	
	//判断是否处于深色模式
	//返回false是深色
	//返回true是浅色
	public static boolean getThemeMode(Context context)
	{
		return (context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO;
	}
    
	//判断处于横竖屏
	public static int getScreenOrientation(Context context) {
		Configuration configuration = context.getResources().getConfiguration();
		int orientation = configuration.orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return 0;//横
		} else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
			return 1;//竖
		} else {
			return -1;//未知
		}
	}
	
	//判断蓝牙是否打开
	public boolean isBluetoothEnabled() {
		BluetoothAdapter ba=BluetoothAdapter.getDefaultAdapter();
		if (ba == null) {
			return false;
		}
		return ba.isEnabled();
	}
	
	//申请卸载某应用
	public static void unninstallApp(Context con,String packageName) {
		Intent intent =new Intent();
		if (intent != null) {
			// 在Android 11（API级别30）及更高版本上，需要使用ACTION_UNINSTALL_PACKAGE来卸载应用
			if (Build.VERSION.SDK_INT >= 29) {
				intent.setAction(Intent.ACTION_UNINSTALL_PACKAGE);
				intent.setData(Uri.parse("package:" + packageName));
			} else {
				// 在Android 10（API级别29）及更低版本上，可以使用ACTION_DELETE来卸载应用
				intent.setAction(Intent.ACTION_DELETE);
				intent.setData(Uri.parse("package:" + packageName));
			}
			con.startActivity(intent);
		} else {

		}
	}
	//需添加<uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES" />权限
	
	
	
	//判断权限
	public static boolean checkPermission(Context con,String name)
	{
		try
		{
			Method mt=Class.forName("androidx.core.content.ContextCompat").getMethod("checkSelfPermission",Context.class,String.class);
			if(((int)mt.invoke(mt,con,name))==PackageManager.PERMISSION_GRANTED)
			 return true;
		}
		catch(Exception e){Toast.makeText(con,e.toString(), Toast.LENGTH_SHORT).show();}
		return false;
	}
	/*
	cls("",class)
	javax(re,null,c,"checkPermission","Context",activity,"String","android.permission.CAMERA","Class",class)
	tw(re)
	*/
	
	//申请权限
	public static void requestPermission(Activity con,String permission)
	{
		try
		{
			Method mt=Class.forName("androidx.core.app.ActivityCompat").getMethod("requestPermissions",Activity.class,String[].class,int.class);
			mt.invoke(mt,con,new String[]{permission},666);	
			
		}
		catch (Exception e)
		{
			Toast.makeText(con,e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	/*
	cls("androidx.core.app.ActivityCompat",class)
	 javax(re,null,c,"requestPermission","Activity",activity,"String","android.permission.CAMERA","Class",class)
	tw(re)
	*/
	

    //是否全局黑白
	public static void ashingApp(Activity activity,boolean sf)
	{
		Paint a=new Paint();
		ColorMatrix b=new ColorMatrix();
		int i=0;
		if(!sf)i=1;
		b.setSaturation(i);
		a.setColorFilter(new ColorMatrixColorFilter(b));
		activity.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE,a);
	}

	//加载dex
	public static DexClassLoader loadDex(Context activity,String path)
	{
		return new DexClassLoader(path,"/data/data/"+activity.getPackageName()+"/files/",null,activity.getClassLoader());
	}
	
	
	
	//是否允许键盘顶起布局
	public static void forbidPan(Activity activity,boolean sf)
	{
		int i=0;
		if(sf)i=16;
		else i=32;
		activity.getWindow().setSoftInputMode(i);
	}
	
	//直接退出软件结束软件进程
	public static void finishApp()
	{
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	
	//是否允许截图录屏
	public static void forbidScreenshot(Activity activity,boolean sf)
	{
		if(sf)
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
		else
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
	}
	
	//跳转管理所有文件设置界面
	public static void openAllFilesAccessSetting(Context activity) {
		Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
		activity.startActivity(intent);
	}
	
	
	//判断是否有悬浮窗权限
	public static boolean checkFloatPermission(Context context)
	{
        if (Build.VERSION.SDK_INT < 19)
		{
            return true;
        }
        if (Build.VERSION.SDK_INT < 23)
		{
            try
			{
                Class<?> cls = Class.forName("android.content.Context");
                Field declaredField = cls.getDeclaredField("APP_OPS_SERVICE");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(cls);
                if (!(obj instanceof String))
				{
                    return false;
                }
                String str = (String) obj;
                Class<?>[] clsArr = new Class[1];
                try
				{
                    clsArr[0] = Class.forName("java.lang.String");
                    Object invoke = cls.getMethod("getSystemService", clsArr).invoke(context, str);
                    Class<?> cls2 = Class.forName("android.app.AppOpsManager");
                    Field declaredField2 = cls2.getDeclaredField("MODE_ALLOWED");
                    declaredField2.setAccessible(true);
                    Class<?>[] clsArr2 = new Class[3];
                    clsArr2[0] = Integer.TYPE;
                    clsArr2[1] = Integer.TYPE;
                    try
					{
                        clsArr2[2] = Class.forName("java.lang.String");
                        return ((Integer) cls2.getMethod("checkOp", clsArr2).invoke(invoke, new Integer(24), new Integer(Binder.getCallingUid()), context.getPackageName())).intValue() == declaredField2.getInt(cls2);
                    }
					catch (ClassNotFoundException e)
					{
                        throw new NoClassDefFoundError(e.getMessage());
                    }
                }
				catch (ClassNotFoundException e2)
				{
                    throw new NoClassDefFoundError(e2.getMessage());
                }
            }
			catch (Exception e3)
			{
                return false;
            }
        }
		else if (Build.VERSION.SDK_INT <= 26)
		{
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            if (appOpsManager == null)
			{
                return false;
            }
            int checkOpNoThrow = appOpsManager.checkOpNoThrow("android:system_alert_window", Process.myUid(), context.getPackageName());
            return checkOpNoThrow == 0 || checkOpNoThrow == 1;
        }
		else
		{
            return Settings.canDrawOverlays(context);
        }
    }

	
	
	//跳转悬浮窗权限界面
    public static void requestFloatPermission(Context context)
	{
        int i = Build.VERSION.SDK_INT;
        if (i >= 26)
		{
            context.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION"));
        }
		else if (i >= 23)
		{
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(Uri.parse(new StringBuffer().append("package:").append(context.getPackageName()).toString()));
            context.startActivity(intent);
        }
    }
	
	
	//判断是否插入SIM卡
	public static boolean haveSIMCard(Context co)
	{
        if (((TelephonyManager)co.getSystemService(co.TELEPHONY_SERVICE)).getSimState() == TelephonyManager.SIM_STATE_READY)
		{
            return true;
        }
		else
		{
            return false;
        }
    }
	
	//判断是否插入SD卡
	public static boolean haveSDCard()
	{
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
            return true;
        }
        else
		{
			return false;
        }

    }
	
	//sp2px
	public static int sp2px(float spValue) {
        float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //px转换成sp   
    public static int px2sp(float pxValue) {
        float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
	
	//px2dp
	public static int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
	
	//dp2px
	public static int dp2px(float dpValue) {
	final float scale = Resources.getSystem().getDisplayMetrics().density;
	return (int) (dpValue * scale + 0.5f);
    }
	
	//跳转应用详情界面
	public static void gotoSettings(Context co)
	{
		Intent intent = new Intent();
		intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		Uri uri = Uri.fromParts("package", co.getPackageName(), null);
		intent.setData(uri);
		co.startActivity(intent);
	}
	
	//获取手机电量百分比
	public double getBatteryCapacity(Context context)
	{
        return ((BatteryManager)context.getSystemService(Context.BATTERY_SERVICE)).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }
	
	//判断手机是否在充电
	public static boolean isCharging(Context context)
	{
		if (((BatteryManager)context.getSystemService(Context.BATTERY_SERVICE)).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) == 2.0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//判断屏幕是否亮屏
	public static boolean isScreenOn(Context context)
	{
		PowerManager pm= (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		return pm.isScreenOn();
	}
	
	//调节音量百分比
	public static void setVolume(Context co, int i)
	{
		AudioManager am=(AudioManager)co.getSystemService(Context.AUDIO_SERVICE);
		am.setStreamVolume(3, i, 0);
	}
	
	//获取剩余储存空间
	public String getAvailableRom()
	{
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long length= blockSize * availableBlocks;
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
	
	//判断是否处于模拟器环境
	public static boolean checkEmulatorSensor(Context context)
	{
		boolean isSupportSensor = false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
			List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
			if (sensorList != null && sensorList.size() > 0) {
				isSupportSensor = true;
			}
		}
		return isSupportSensor;
	}
	
	
	//判断xposed环境
	public static boolean isXposed() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Object localObject = ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
		if (localObject == null)
		{
			return false;
		}
		else return true;
	}
	
	//(沉浸)状态栏及字体颜色
	public static void setStatusBar(Activity ac,boolean f,String color1,String color2,int c)
	{
		Window window=ac.getWindow();
		if(f)
			window.addFlags(512);
		else
		{
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		}
		window.setStatusBarColor(Color.parseColor(color1));
		window.setNavigationBarColor(Color.parseColor(color2));
		if(c==1)c=8192;else c=1024;
		window.getDecorView().setSystemUiVisibility(c);
	}
	
	
	//判断是否存在底部栏
	public static boolean hasNavBar(Context context)
	{
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0)
		{
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride))
			{
                hasNav = false;
            }
			else if ("0".equals(sNavBarOverride))
			{
                hasNav = true;
            }
            return hasNav;
        }
		else
		{
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    //判断虚拟按键栏是否重写
    private static String getNavBarOverride()
	{
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
            try
			{
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            }
			catch (Exception e)
			{

            }
        }
        return sNavBarOverride;
    }
	
	//收起键盘
	public static void closePan(Activity con)
	{InputMethodManager k;
        EditText et=new EditText(con);
        et.setFocusableInTouchMode(false);
        et.setFocusable(false);
        ((ViewGroup)con.getWindow().getDecorView()).addView(et);
        et.setVisibility(View.GONE);
		InputMethodManager manager = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
	}
	
    //获取键盘高度
    public static int getPanh(Activity co)
	{
		View decorView = co.getWindow().getDecorView();
		Rect rect = new Rect();
		decorView.getWindowVisibleDisplayFrame(rect);
		return (decorView.getHeight() - rect.bottom);
	}
}
