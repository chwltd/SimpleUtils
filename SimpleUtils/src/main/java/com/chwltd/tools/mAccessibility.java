package com.chwltd.tools;

import android.accessibilityservice.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.widget.*;
import android.view.*;
import android.view.accessibility.*;


public class mAccessibility extends AccessibilityService
{
	
	/*
	 <service
	 android:name="com.mus.utils.mAccessibility"
	 android:label="王者秒"
	 android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
	 <intent-filter>
	 <action android:name="android.accessibilityservice.AccessibilityService"/>
	 </intent-filter>
	 <meta-data
	 android:name="android.accessibilityservice"
	 android:resource="@xml/aya_config"/>
	 </service>
	 <service
	 android:name="com.mus.clothes.KeepRunning"
	 android:enabled="true"
	 android:exported="false" />
	 
	 android:canPerformGestures="true"
	 android:canTakeScreenshot="true"
	*/
	
    private static TextView mListener=null;
	private static Handler handler;
	private static boolean running=false,listenKey=true;
	
    public void onAccessibilityEvent(AccessibilityEvent event)
    {
		
	}

	public static void setKeyListener(Object tv)
	{
		mListener=(TextView)tv;
	}
	
	public static void openKeyListener(boolean f)
	{
		listenKey=f;
	}
	
	protected boolean onKeyEvent(KeyEvent event)
	{
		if (listenKey&&mListener!=null)
		{
            if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP)
				mListener.setText("up");
			else if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)
				mListener.setText("down");
			return true;
		}
		else return super.onKeyEvent(event);
	}
	
	private void Sleep(long s)
	{
		try
		{
			Thread.sleep(s);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void Click(float x, float y)
	{
		Click(x, y, 50);
	}

	private void Click(float x, float y, long t)
    {
		android.graphics.Path path = new android.graphics.Path();
		path.moveTo(x, y);
		android.accessibilityservice.GestureDescription.Builder builder = new android.accessibilityservice.GestureDescription.Builder();
		//100L 第一个是开始的时间，第二个是持续时间
		android.accessibilityservice.GestureDescription description = builder.addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 0, t)).build();
		dispatchGesture(description, null, null);
    }

	
	private void Touch(float x, float y, float x2, float y2, long t)
    {
		Path path = new Path();
		path.moveTo(x, y);//滑动起点
		path.lineTo(x2, y2);//滑动终点
		GestureDescription.Builder builder = new GestureDescription.Builder();
		//100L 第一个是开始的时间，第二个是持续时间
		GestureDescription description = builder.addStroke(new GestureDescription.StrokeDescription(path, 0, t)).build();
		dispatchGesture(description, null, null);
    }

	
	private void clickByText(String t)
	{
		clickByText(t,50,0);
	}
    private void clickByText(String text,long len,int x) 
    {
		try
		{
			AccessibilityNodeInfo nodeInfo = getRootInActiveWindow().findAccessibilityNodeInfosByText(text).get(x);
			Rect r=new Rect();
			nodeInfo.getBoundsInScreen(r);
			Click((r.left+r.right)/2,(r.top+r.bottom)/2,len);
		}
		catch(Exception e)
		{
		}
	}

	private void clickById(String t)
	{
		clickById(t,50,0);
	}
	
	private void clickById(String id,long len,int x) 
    {
		try
		{
			AccessibilityNodeInfo nodeInfo = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(id).get(x);
			Rect r=new Rect();
			nodeInfo.getBoundsInScreen(r);
			Click((r.left+r.right)/2,(r.top+r.bottom)/2,x);
		}
		catch(Exception e)
		{
		}
	}



	/**
	 * 全局事件
	 * 值说明 1=返回键 2=HOME键 3=最近打开应用列表 4=打开通知栏 5=设置 6=长按电源 7=切换窗口 8=锁屏 9=截屏 10=接/挂电话
	 * */
	private boolean Key(int action)
	{
		return this.performGlobalAction(action);
    }


	public static void runCode(String code)
	{
		Message msg=new Message();
		msg.obj=code;
		handler.sendMessage(msg);
	}

    private void startAction(final String code)
    {
		if (running)
		{
			return;
		}
		new Thread()
		{
			public void run()
			{
				running = true;
				String[] clb=code.replace(" ", "").replace("\n", "").split(";", -1);
				for (int i=0;i < clb.length;i++)
				{
					if (clb[i].toLowerCase().startsWith("click("))
					{
						String[] cm=getBetween(clb[i], "(", ")").split(",", -1);
						long t=50;
						if (cm.length == 3)
						{
							t = Long.parseLong(cm[2]);
						}
						Click(Float.parseFloat(cm[0]), Float.parseFloat(cm[1]), t);
					}
					else if (clb[i].toLowerCase().startsWith("sleep("))
					{
						Sleep(Long.parseLong(getBetween(clb[i], "(", ")")));
					}
					else if (clb[i].toLowerCase().startsWith("key("))
					{
						Key(Integer.parseInt(getBetween(clb[i], "(", ")")));
					}
					else if (clb[i].toLowerCase().startsWith("touch("))
					{
						String[] cm=getBetween(clb[i], "(", ")").split(",", -1);
						int t=500;
						if (cm.length == 5)
						{
							t = Integer.parseInt(cm[4]);
						}
						Touch(Float.parseFloat(cm[0]), Float.parseFloat(cm[1]),Float.parseFloat(cm[2]),Float.parseFloat(cm[3]), t);
					}
                    else if(clb[i].toLowerCase().startsWith("clickbyid("))
					{
						String[] cm=getBetween(clb[i], "(", ")").split(",", -1);
						long t=50;int x=0;
						if(cm.length>=2)
						{
							x=Integer.parseInt(cm[1]);
							if (cm.length == 3)
							{
								t = Long.parseLong(cm[2]);
							}
						}
						clickById(cm[0],t,x);
						
					}
                    else if(clb[i].toLowerCase().startsWith("clickbytext("))
					{
						String[] cm=getBetween(clb[i], "(", ")").split(",", -1);
						long t=50;int x=0;
						if(cm.length>=2)
						{
							x=Integer.parseInt(cm[1]);
							if (cm.length == 3)
							{
								t = Long.parseLong(cm[2]);
							}
						}
						clickByText(cm[0],t,x);
					}
				}
				running = false;
			}
		}.start(); 
	}

	private static String getBetween(String str, String str2, String str3)
	{
		int i;
		int indexOf;
		if (str2 != null)
		{
			int indexOf2=str.indexOf(str2);
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


	protected void onServiceConnected()
	{     
		handler=new Handler()
		{
			public void handleMessage(Message msg)
			{
				startAction(msg.obj.toString());
			}
		};
	}

	public static void setPermission(Context context)
    {
		context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
    }

    public static boolean havePermission(Context context)
    {
		int i;
		String string;
        Class cc=null;
		try
		{
			cc = Class.forName("com.mus.utils.mAccessibility");
		}
        catch (Exception ex)
        {}
        String str = context.getPackageName() + "/" + cc.getCanonicalName();
        try
		{
			i = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), "accessibility_enabled");
		}
		catch (Settings.SettingNotFoundException unused)
		{
			i = 0;
        }
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (i == 1 && (string = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services")) != null)
		{
			simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext())
			{
				if (simpleStringSplitter.next().equalsIgnoreCase(str))
				{
					return true;
				}
			}
        }
		return false;
	}
	
	public void onInterrupt()
	{

	}

}
				
