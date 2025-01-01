package com.chwltd.view.badgeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chwltd.api.AppConfig;
import com.chwltd.utils.ImageUtils;
import com.chwltd.utils.SystemUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class SimpleBadgeView extends HorizontalScrollView {
    private List<String> badgeData;
    private LinearLayout rootView;
    private TextView parentTextView;
    private int marginData = SystemUtils.dp2px(2.5f);
    private float titleTextSize = 10;
    private int titleRadius = SystemUtils.dp2px(5f);
    private int titleColor = Color.parseColor("#222222");
    private int titleTextColor = Color.parseColor("#ffffff");
    private int leftPaddingData = SystemUtils.dp2px(5);
    private int topPaddingData = SystemUtils.dp2px(2.5f);

    public SimpleBadgeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public SimpleBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimpleBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleBadgeView(Context context) {
        super(context);
        init();
    }

    public void init(){
        setOverScrollMode(OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
        rootView = new LinearLayout(getContext());
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.setOrientation(LinearLayout.HORIZONTAL);
        rootView.setGravity(Gravity.CENTER_VERTICAL);
        addView(rootView);
    }

    public void initData(){

    }

    public void setBadgeData(List<String> data){
        badgeData = data;
        initBadge();
    }

    public void setParentTextView(TextView textView){
        parentTextView = textView;
    }

    public void initBadge(){
        if(parentTextView == null){
            parentTextView = new TextView(getContext());
            parentTextView.setTextSize(14);
        }
        rootView.removeAllViews();
        for(int i = 0; i < badgeData.size(); i++){
            String data = badgeData.get(i);
            if(data.startsWith("http")){
                addBadge(data);
            }else{
                addTitle(data);
            }
        }
    }

    public void addTitle(String data){
        Gson gson = new Gson();
        int titleColors = titleColor;
        int titleTextColors = titleTextColor;
        if(AppConfig.userTitleColorData != null) {
            JsonObject rootObject = gson.fromJson(AppConfig.userTitleColorData, JsonObject.class);
            JsonObject titleData = rootObject.get(data) == null ? null : rootObject.get(data).getAsJsonObject();
            if(titleData != null){
                titleColors = Color.parseColor(titleData.get("TitleColor").getAsString());
                titleTextColors = Color.parseColor(titleData.get("TextColor").getAsString());
            }
        }
        TextView textView = new TextView(getContext());
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) parentTextView.getTextSize()+2*topPaddingData);
        layoutParams.leftMargin = marginData;
        layoutParams.rightMargin = marginData;
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(data);
        textView.setTextSize(titleTextSize);
        textView.setSingleLine();
        textView.setTextColor(titleTextColors);
        textView.setBackground(createDrawable(titleColors));
        textView.setPadding(leftPaddingData,topPaddingData,leftPaddingData,topPaddingData);
        rootView.addView(textView);
    }

    public void addBadge(String url){
        ImageView imageView = new ImageView(getContext());
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) parentTextView.getTextSize()+2*topPaddingData);
        layoutParams.leftMargin = marginData;
        layoutParams.rightMargin = marginData;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setMinimumHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setAdjustViewBounds(true);
        loadImage(imageView,url);
        rootView.addView(imageView);
    }

    private Drawable createDrawable(int titleColors){
        // 创建GradientDrawable对象
        GradientDrawable drawable = new GradientDrawable();
        // 设置形状为矩形，这是默认形状，但明确指定一下更清晰
        drawable.setShape(GradientDrawable.RECTANGLE);

        // 设置圆角半径，这里设置为10dp对应的像素值，你可以根据实际需求调整
        float cornerRadius = titleRadius;
        drawable.setCornerRadius(cornerRadius);

        // 设置颜色，使用稍微透明的黑色，格式是ARGB，这里透明度设置为128（取值范围是0-255，0表示完全透明，255表示完全不透明）
        drawable.setColor(titleColors);
        drawable.setAlpha(255);

        return drawable;
    }

    private void loadImage(ImageView imageView,String url){
        ImageUtils.loadImage(getContext(),imageView,url,AppConfig.loadImageSpeed);
    }

    public void setMarginData(float data){
        marginData = SystemUtils.dp2px(data);
    }

    public void setMarginData(int data){
        marginData = data;
    }

    public void setTitleTextSize(float data){
        titleTextSize = SystemUtils.dp2px(data);
    }

    public void setTitleRadius(float data){
        titleRadius = SystemUtils.dp2px(data);
    }

    public void setTitleRadius(int data){
        titleRadius = data;
    }

    public void setTitleColor(String data){
        titleColor = Color.parseColor(data);
    }

    public void setTitleColor(int data){
        titleColor = data;
    }

    public void setTitleTextColor(String data){
        titleTextColor = Color.parseColor(data);
    }

    public void setTitleTextColor(int data){
        titleTextColor = data;
    }

    public void setLeftPaddingData(float data){
        leftPaddingData = SystemUtils.dp2px(data);
    }

    public void setLeftPaddingData(int data){
        leftPaddingData = data;
    }

    public void setTopPaddingData(float data){
        topPaddingData = SystemUtils.dp2px(data);
    }

    public void setTopPaddingData(int data){
        topPaddingData = data;
    }
}
