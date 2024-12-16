package com.chwltd.utils;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.inputmethod.*;
import android.widget.*;
import java.lang.reflect.*;
import java.util.regex.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Display;
import android.hardware.display.*;
import android.widget.Toolbar.*;

public class ViewUtils {

	public static float ButtomNavigationHeight=55.5f;
    /*
    Mus类库
    */
    private static String rule="";
	private static Typeface typeface=null;

	//主页导航栏收起动画
	public static void setButomNavigationVisibility(Context context,View v, boolean show) {
		float start = 0;
		float end = 0;
		if(!show){
			start = -ButtomNavigationHeight;
			end = 0;
		}else{
			start = 0;
			end = -ButtomNavigationHeight;
		}
		ValueAnimator animator = ValueAnimator.ofFloat(start, end);
		animator.setDuration(200);
		animator.setEvaluator(new FloatEvaluator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				int marginInPx = SystemUtils.dp2px(value);
				ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
				layoutParams.bottomMargin = marginInPx;
				v.setLayoutParams(layoutParams);
			}
		});
		animator.start();
	}
	
    /*
    Mus类库
    */
	//设置控件透明度
	public static void setAlpha(Object v,float f)
	{
		((View)v).setAlpha(f);
	}
	
	//获取父控件
	public static Object getParent(Object v)
	{
		return ((View)v).getParent();
	}
	
	//点击控件弹出popupmenu
	@SuppressLint("SuspiciousIndentation")
    public static void showPopupMemu(Object view, Object view1, String list, int i)
	{
		View v=(View)view;
		final TextView v1=(TextView)view1;
		int g=Gravity.LEFT;
		if(i==0)g=Gravity.LEFT;
		else if(i==1)g=Gravity.TOP;
		else if(i==2)g=Gravity.RIGHT;
		else if(i==3)g=Gravity.BOTTOM;
		else if(i==4)g=Gravity.CENTER;
		PopupMenu pm=new PopupMenu(v.getContext(),v,g);
		String[] zu=list.split(Pattern.quote("."));
		for(int o=0;o<zu.length;o++)
		{
			String[] zu1=TextUtils.getBetween(zu[o],"[","]").split(Pattern.quote(","));
			pm.getMenu().add(0,o,0,zu1[0]);
			if(zu1[1].equals("false"))
			pm.getMenu().getItem(o).setEnabled(false);
		}
		pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
			{
				@Override
				public boolean onMenuItemClick(MenuItem p1)
				{
					v1.setText(p1.getItemId()+"");
					return false;
				}
				
			
		});
		pm.show();
	}
	
	//控件圆角
	public static void setRab(Object view,String color,int a,int b,int c,int d)
	{
		View v=(View)view;
		GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(color));
        drawable.setCornerRadius(10f); // 设置圆角半径

        // 为控件的四个角分别设置圆角度数
        drawable.setCornerRadii(
			new float[] {a,a,a,a,  //左上
				b,b,   //右上
				c,c,   //右下
				d,d}); //左下

        // 设置控件的背景为带有圆角的 GradientDrawable
        v.setBackground(drawable);
	}

	
	
	//解决图像控件出现锯齿
	public static void setImage(Object o,Object bp)
	{
		ImageView imageView = (ImageView)o;
		Bitmap bitmap = (Bitmap)bp;
		int imageViewWidth = imageView.getWidth();
		int imageViewHeight = imageView.getHeight();
		int scaleFactor = Math.min(imageViewWidth / (int) bitmap.getWidth(), imageViewHeight / (int) bitmap.getHeight());
		int newWidth =bitmap.getWidth() * scaleFactor;
		int newHeight =bitmap.getHeight() * scaleFactor;
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
		imageView.setImageBitmap(scaledBitmap);
		
	}
	
	//修改全局字体大小
	public static void changeTextSize(Activity activity,int multiple){
        Configuration configuration = activity.getResources().getConfiguration();
        configuration.fontScale = multiple;    //1为标准字体，multiple为放大的倍数
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayMetrics.scaledDensity = configuration.fontScale*displayMetrics.density;
        activity.getResources().updateConfiguration(configuration,displayMetrics);
    }
	
	
	//设置全局字体
	public static void setAllTypeFace(Activity ac,String path)
	{
		if(typeface==null)
			typeface=Typeface.createFromFile(path);
		setAllTypeFace(ac.getWindow().getDecorView());
	}
	
	//设置指定控件内部所有字体
	public static void setTypeFace(Object o,String path)
	{
		if(typeface==null)
			typeface=Typeface.createFromFile(path);
		setAllTypeFace((View)o);
	}
	private static void setAllTypeFace(Object view)
	{
		if (view instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) view;
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				setAllTypeFace(viewGroup.getChildAt(i));
			}
		} else if (view instanceof TextView ||view instanceof EditText || view instanceof Button) {
			((TextView) view).setTypeface(typeface);
		}
	}
	
	//MD Toast
	public static void MDToast(Context activity,Object ic,String i,String textcolor,String backgroundcolor,int elevation,int gravity,int x,int y)
	{
		Bitmap icon=(Bitmap)ic;
		Toast toast= Toast.makeText(activity,"", Toast.LENGTH_SHORT);
		toast.setGravity(gravity,x,y);
		LinearLayout ne=new LinearLayout(activity);
		LinearLayout ne2=new LinearLayout(activity);
		ne2.setGravity(Gravity.LEFT|Gravity.CENTER);
		ImageView img=new ImageView(activity);
		ne2.addView(img);
		LinearLayout.LayoutParams imgg=(LinearLayout.LayoutParams) img.getLayoutParams();  
		imgg.height=((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,activity.getResources().getDisplayMetrics()));
		imgg.width=((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,18,activity.getResources().getDisplayMetrics()));
		imgg.leftMargin=30;
		img.setLayoutParams(imgg);
		img.setImageBitmap(icon);
		ne2.addView(ne);
		LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) ne.getLayoutParams();  
		params.leftMargin=18;
		params.rightMargin=35;
		params.height=((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,activity.getResources().getDisplayMetrics()));
		ne.setLayoutParams(params);
		GradientDrawable drawable=new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE);
		drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		drawable.setCornerRadius(360);
		drawable.setColor(Color.parseColor(backgroundcolor));
		ne2.setBackground(drawable);
		ne2.setElevation(elevation);
		ne.setGravity(Gravity.CENTER);
		TextView te=new TextView(activity);
		ne.addView(te);
		te.setText(i);
		te.setTextColor(Color.parseColor(textcolor));
		toast.setView(ne2);
		toast.show();
	}
	/*
	 sbp("@icon_iapp.png",icon)
	 s text="我是MD提示"
	 //提示文字

	 s textcolor="#ffffff"
	 //提示文字颜色

	 s backgroundcolor="#FF51A1D7"
	 //提示背景颜色

	 s elevation=20
	 //背景阴影大小

	 s gravity=0
	 //toast显示位置,
	 //中间是0,底部是80,顶部是48

	 s x=0
	 //横坐标偏移值

	 s y=-300
	 //纵坐标偏移值
	
	*/
	
	
	
	
	//控件转图片
	public static Bitmap view2bitmap(Object v)
	{
		View view=(View)v;
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0,0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}
	
	
	
	//图像控件着色
	public static void setivColor(Object iv, String color)
	{
		((ImageView)iv).setColorFilter(Color.parseColor(color));
	}
	
	//清除控件动画
	public static void clearDh(Object v)
	{
		View i=(View)v;
		try
		{
			i.animate().cancel();
		}
		catch (Exception e)
		{}
		i.clearAnimation();
		i.setAnimation(null);
	}
	
	//无动画切换滑动窗体
	public static void setCurrentitem(Object v,int i)
	{
		Class vc=v.getClass();
		try
		{
			Method f=vc.getMethod("setCurrentItem", int.class, boolean.class);
			f.invoke(v,i,false);
		}
		catch (Exception e)
		{}
	}
	//gvs(1,v)
	//javax(null,null,c,"setCurrentitem","Object",v,"int",1,"boolean",false){}
	
	//关闭应用栏阴影
	public static void cancelElevation(Object v)
	{
		Class vc=v.getClass();
		try
		{
			Method f=vc.getMethod("setTargetElevation", float.class);
			f.invoke(v,0f);
		}
		catch (Exception e)
		{}
	}
	
	//光标选中指定字符串
	public static void selectEt(Object e,String o)
	{
		EditText et=(EditText)e;
		int i=et.getText().toString().indexOf(o);
		et.setSelection(i,i+o.length());
	}
	
	//将焦点赋予某个编辑框
	public static void giveFocus(Object e,boolean sf)
	{
		EditText et=(EditText)e;
		et.requestFocus();
		permitInput(e,true);
		if(sf)
		{
			try{
			((InputMethodManager)et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED,0);}catch(Exception u){}
		}
	}
	
	//是否允许编辑框输入
	public static void permitInput(Object e,boolean sf)
	{
		EditText et=(EditText)e;
		et.setFocusable(sf);
		et.setFocusableInTouchMode(sf);
	}
	
	//光标移动到指定位置
	public static void setSelected(Object e,int i)
	{
		((EditText)e).setSelection(i);
	}
	
	//选中指定位置之间文本
	public static void setSelected(Object e,int a,int b)
	{
		((EditText)e).setSelection(a,b);
	}
	
	//删除编辑框选中文本
	public static void deleteSelected(Object e)
	{
		EditText et=(EditText)e;
		int s=et.getSelectionStart();
		et.getText().delete(s,et.getSelectionEnd());
		et.setSelection(s);
	}
	
	//获取编辑框选中文本
	public static String getSelected(Object e)
	{
		EditText et=(EditText)e;
		return et.getText().toString().substring(et.getSelectionStart(),et.getSelectionEnd());
	}
	
	public static void limitInput(Object et,String i)
	{
		rule=i.replace("1","0-9").replace("2","a-z").replace("3","A-Z").replace("4","\\.").replace("5","\u4e00-\u9fa5");
        InputFilter typeFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
			{
                Pattern p = Pattern.compile(new StringBuilder().append("[").append(rule).append("]").toString());
                Matcher m = p.matcher(source.toString());
                if (!m.matches()) return "";
                return null;
            }
        };

		((EditText)et).setFilters(new InputFilter[]{typeFilter});

	}
	
    //编辑框光标处插入文本
    public static void insert(Object editText1, String str)
	{
		EditText editText=(EditText)editText1;
        editText.getText().insert(editText.getSelectionEnd(), str);
    }
}
