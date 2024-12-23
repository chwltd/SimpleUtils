package com.chwltd.view.imageview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chwltd.api.AppConfig;
import com.chwltd.utils.ImageUtils;
import com.chwltd.utils.SystemUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleImagePresenter extends RelativeLayout {

    //图片数据
    private List<String> imageUrls;
    //图片数量
    private int imageNum = 0;
    //图片展示跟布局
    private LinearLayout rootLinearLayout;
    //图片数量展示view
    private TextView imageNumTextView;
    //分割线宽度
    private int lineWidth = SystemUtils.dp2px(2.5f);
    //三图样式高度
    private int threeImageStyleHeight = SystemUtils.getScreenWidth(getContext())/2 + lineWidth;
    //三图样式图片宽度
    private int threeImageSytleWidth = SystemUtils.getScreenWidth(getContext())/4;
    //图片展示器外边距数据
    private int marginData = 0;
    //一行二图宽度
    private int twoImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*marginData-lineWidth)/2;
    //一行三图宽度
    private int threeImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*marginData-2*lineWidth)/3;
    //图片数量圆角
    private int imageNumRadius = SystemUtils.dp2px(7.5f);
    //图片数量外边距
    private int imageNumMargin = SystemUtils.dp2px(10);
    //图片数量背景颜色
    private int imageNumBackgroundColor = Color.parseColor("#222222");
    //图片数量背景透明度255不透明0全透明
    private int imageNumBackgroundAlpha = 168;
    //图片数量字体大小
    private int imageNumTextSize = 12;
    //图片数量字体颜色
    private int imageNumTextColor = Color.parseColor("#ffffff");

    public SimpleImagePresenter(Context context) {
        super(context);
    }

    public SimpleImagePresenter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SimpleImagePresenter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SimpleImagePresenter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initData(){
        //三图样式高度
        threeImageStyleHeight = SystemUtils.getScreenWidth(getContext())/2 + lineWidth;
        //一行二图宽度
        twoImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*marginData-lineWidth)/2;
        //一行三图宽度
        threeImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*marginData-2*lineWidth)/3;
    }

    public void initView(){
        if(imageNum==1){
            oneImageStyle();
        }else if(imageNum==2){
            twoImageStyle();
        }else if(imageNum==3){
            threeImageStyle();
        }else if(imageNum==4){
            fourImageStyle();
        }else if(imageNum==5){
            fiveImageStyle();
        }else if(imageNum==6){
            sixImageStyle();
        }else if(imageNum<9){
            moreSixImageStyle();
        }else if(imageNum==9){
            nineImageStyle();
        }else if(imageNum>9){
            moreNineImageStyle();
        }

        int imageHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        if(imageNum == 2){
            imageHeight = twoImageHeight;
        }else if(imageNum >= 4&&imageNum<9){
            imageHeight = 2*threeImageHeight+lineWidth;
        }else if(imageNum>9){
            imageHeight = 3*threeImageHeight+2*lineWidth;
        }

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, imageHeight);
            setLayoutParams(layoutParams);
        } else {
            layoutParams.height = imageHeight;
            setLayoutParams(layoutParams);
        }
    }

    public void setImageUrls(List<String> imageUrls) {
        removeAllViews();
        this.imageUrls = imageUrls;
        imageNum = imageUrls.size();
        initView();
    }

    public void setImageUrls(String data){
        removeAllViews();
        Gson gson = new Gson();
        imageUrls = gson.fromJson(data,List.class);
        imageNum = imageUrls.size();
        initView();
    }

    public void setImageUrls(JsonArray jsonArray){
        removeAllViews();
        Gson gson = new Gson();
        imageUrls = gson.fromJson(jsonArray,List.class);
        imageNum = imageUrls.size();
        initView();
    }

    public void loadTwoImage(LinearLayout view,int start){
        for (int i = start; i < start+2; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            imageView.setLayoutParams(params);
            loadImage(imageView,imageUrls.get(i));
            view.addView(imageView);
            if(i<start+1){
                createVerticalLine(view);
            }
        }
    }

    public void loadThreeImage(LinearLayout view,int start){
        for (int i = start; i < start+3; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            imageView.setLayoutParams(params);
            loadImage(imageView,imageUrls.get(i));
            view.addView(imageView);
            if(i<start+2){
                createVerticalLine(view);
            }
        }
    }

    public void oneImageStyle() {
        AutoImageView imageView = new AutoImageView(getContext());
        // 设置布局参数
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // 宽度设置为MATCH_PARENT
                ViewGroup.LayoutParams.WRAP_CONTENT  // 高度设置为WRAP_CONTENT
        );
        imageView.setLayoutParams(layoutParams);
        String imageUrl = imageUrls.get(0);
        Pattern pattern = Pattern.compile("w=(\\d+)&h=(\\d+)");
        Matcher matcher = pattern.matcher(imageUrl);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group(1));  // 获取宽度
            int height = Integer.parseInt(matcher.group(2)); // 获取高度
            // 输出结果
            loadImage(imageView,imageUrl,width,height);
        } else {
            loadImage(imageView,imageUrl);
        }
        addView(imageView);
    }

    public void twoImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, twoImageHeight));
        loadTwoImage(rootLinearLayout,0);
        addView(rootLinearLayout);
    }

    public void threeImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, threeImageStyleHeight));
        ImageView imageView1 = new ImageView(getContext());
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        imageView1.setLayoutParams(params);
        loadImage(imageView1,imageUrls.get(0));
        rootLinearLayout.addView(imageView1);

        createVerticalLine(rootLinearLayout);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(threeImageSytleWidth, ViewGroup.LayoutParams.MATCH_PARENT));
        for (int i = 1; i < 3; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(params);
            loadImage(imageView,imageUrls.get(i));
            linearLayout2.addView(imageView);
            if(i<2){
                createHorizontalLine(linearLayout2);
            }
        }
        rootLinearLayout.addView(linearLayout2);
        addView(rootLinearLayout);
    }

    public void fourImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2*twoImageHeight));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(params);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setLayoutParams(params);

        loadTwoImage(linearLayout1,0);
        loadTwoImage(linearLayout2,2);
        rootLinearLayout.addView(linearLayout1);
        createHorizontalLine(rootLinearLayout);
        rootLinearLayout.addView(linearLayout2);
        addView(rootLinearLayout);
    }

    public void fiveImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2*twoImageHeight));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(params);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setLayoutParams(params);

        loadThreeImage(linearLayout1,0);
        loadTwoImage(linearLayout2,3);
        rootLinearLayout.addView(linearLayout1);
        createHorizontalLine(rootLinearLayout);
        rootLinearLayout.addView(linearLayout2);
        addView(rootLinearLayout);
    }

    public void sixImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2*threeImageHeight+lineWidth));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(params);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setLayoutParams(params);

        loadThreeImage(linearLayout1,0);
        loadThreeImage(linearLayout2,3);
        rootLinearLayout.addView(linearLayout1);
        createHorizontalLine(rootLinearLayout);
        rootLinearLayout.addView(linearLayout2);
        addView(rootLinearLayout);
    }

    public void nineImageStyle() {
        rootLinearLayout = new LinearLayout(getContext());
        rootLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3*threeImageHeight+2*lineWidth));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(params);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setLayoutParams(params);

        LinearLayout linearLayout3 = new LinearLayout(getContext());
        linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3.setLayoutParams(params);

        loadThreeImage(linearLayout1,0);
        loadThreeImage(linearLayout2,3);
        loadThreeImage(linearLayout3,6);
        rootLinearLayout.addView(linearLayout1);
        createHorizontalLine(rootLinearLayout);
        rootLinearLayout.addView(linearLayout2);
        createHorizontalLine(rootLinearLayout);
        rootLinearLayout.addView(linearLayout3);
        addView(rootLinearLayout);
    }

    public void moreSixImageStyle() {
        sixImageStyle();
        createNumTextView();
    }

    public void moreNineImageStyle() {
        nineImageStyle();
        createNumTextView();
    }

    private void loadImage(ImageView imageView,String url){
        ImageUtils.loadImage(getContext(),imageView,url, AppConfig.themeOccupancy,AppConfig.themeError,AppConfig.loadImageSpeed);
    }

    private void loadImage(ImageView imageView,String url,int w ,int h){
        ImageUtils.loadImage(getContext(),imageView,url,AppConfig.themeOccupancy,AppConfig.themeError,AppConfig.loadImageSpeed,w,h);
    }

    private void createNumTextView(){
        TextView textView = new TextView(getContext());
        textView.append(imageNum+" 图");
        textView.setPadding(SystemUtils.dp2px(6),SystemUtils.dp2px(3),SystemUtils.dp2px(6),SystemUtils.dp2px(3));
        textView.setTextSize(imageNumTextSize);
        textView.setTextColor(imageNumTextColor);
        textView.setBackground(createDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.bottomMargin = imageNumMargin;
        params.rightMargin = imageNumMargin;
        textView.setLayoutParams(params);
        addView(textView);
    }

    private void createVerticalLine(LinearLayout root){
        View view = new View(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(lineWidth, ViewGroup.LayoutParams.MATCH_PARENT));
        root.addView(view);
    }

    private void createHorizontalLine(LinearLayout root){
        View view = new View(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lineWidth));
        root.addView(view);
    }

    private Drawable createDrawable(){
        // 创建GradientDrawable对象
        GradientDrawable drawable = new GradientDrawable();
        // 设置形状为矩形，这是默认形状，但明确指定一下更清晰
        drawable.setShape(GradientDrawable.RECTANGLE);

        // 设置圆角半径，这里设置为10dp对应的像素值，你可以根据实际需求调整
        float cornerRadius = imageNumRadius;
        drawable.setCornerRadius(cornerRadius);

        // 设置颜色，使用稍微透明的黑色，格式是ARGB，这里透明度设置为128（取值范围是0-255，0表示完全透明，255表示完全不透明）
        drawable.setColor(imageNumBackgroundColor);
        drawable.setAlpha(imageNumBackgroundAlpha);

        return drawable;
    }

    public void setlineWidth(int lineWidth) {
        this.lineWidth = SystemUtils.dp2px(lineWidth);
    }

    public void setMarginData(int marginData) {
        this.marginData = SystemUtils.dp2px(marginData);
    }

    public void setlineWidthPx(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setMarginDataPx(int marginData) {
        this.marginData = marginData;
    }

    public void setImageNumBackgroundColor(int imageNumBackgroundColor) {
        this.imageNumBackgroundColor = imageNumBackgroundColor;
    }

    public void setImageNumBackgroundColor(String imageNumBackgroundColor) {
        this.imageNumBackgroundColor = Color.parseColor(imageNumBackgroundColor);
    }

    public void setImageNumRadius(int imageNumRadius) {
        this.imageNumRadius = SystemUtils.dp2px(imageNumRadius);
    }

    public void setImageNumRadiusPx(int imageNumRadius) {
        this.imageNumRadius = imageNumRadius;
    }

    public void setImageNumMargin(int imageNumMargin) {
        this.imageNumMargin = SystemUtils.dp2px(imageNumMargin);
    }

    public void setImageNumMarginPx(int imageNumMargin) {
        this.imageNumMargin = imageNumMargin;
    }

    public void setImageNumBackgroundAlpha(int imageNumBackgroundAlpha) {
        this.imageNumBackgroundAlpha = imageNumBackgroundAlpha;
    }

    public void setImageNumTextSize(int imageNumTextSize) {
        this.imageNumTextSize = imageNumTextSize;
    }

    public void setImageNumTextColor(int imageNumTextColor) {
        this.imageNumTextColor = imageNumTextColor;
    }

    public void setImageNumTextColor(String imageNumTextColor) {
        this.imageNumTextColor = Color.parseColor(imageNumTextColor);
    }
}
