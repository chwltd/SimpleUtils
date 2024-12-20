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

import com.bumptech.glide.Glide;
import com.chwltd.api.AppConfig;
import com.chwltd.utils.ImageUtils;
import com.chwltd.utils.SystemUtils;
import com.google.gson.Gson;

import java.util.List;

public class SimpleImageView extends RelativeLayout {

    private List<String> imageUrls;
    private int imageNum;
    private LinearLayout linearLayout;
    private int viewHeight = SystemUtils.dp2px(220);
    private int viewWidth = SystemUtils.dp2px(110);
    private int lineWidth = SystemUtils.dp2px(2.5f);

    private float simpleImageViewMargin = SystemUtils.dp2px(0);
    private int twoImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*simpleImageViewMargin-lineWidth)/2;
    private int threeImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*simpleImageViewMargin-2*lineWidth)/3;

    public SimpleImageView(Context context) {
        super(context);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSimpleImageViewMargin(float margin){
        simpleImageViewMargin = SystemUtils.dp2px(margin);
        twoImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*simpleImageViewMargin-lineWidth)/2;
        threeImageHeight = (int) (SystemUtils.getScreenWidth(getContext())-2*simpleImageViewMargin-2*lineWidth)/3;
    }

    public void setImageUrl(List<String> urls) {
        imageUrls = urls;
        imageNum = urls.size();
        initView();
    }

    public void setImageUrl(String data){
        Gson gson = new Gson();
        imageUrls = gson.fromJson(data,List.class);
        imageNum = imageUrls.size();
        initView();
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
        }else {
            moreImageStyle();
        }

        int imageHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        if(imageNum == 2){
            imageHeight = twoImageHeight;
        }else if(imageNum >= 4){
            imageHeight = 2*threeImageHeight+lineWidth;
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
//
//        // 设置最大高度和最小高度、宽度
//        imageView.setMaxHeight(SystemUtils.dp2px(350)); // 最大高度350dp
//        imageView.setMinimumHeight(SystemUtils.dp2px(120)); // 最小高度120dp
//        imageView.setMinimumWidth(SystemUtils.dp2px(120)); // 最小宽度120dp
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setAdjustViewBounds(true);

        loadImage(imageView,imageUrls.get(0));
        addView(imageView);
    }

    public void twoImageStyle() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, twoImageHeight));
        loadTwoImage(linearLayout,0);
        addView(linearLayout);
    }

    public void threeImageStyle() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        int imageHeight = 2*viewWidth+lineWidth;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imageHeight));
        ImageView imageView1 = new ImageView(getContext());
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        imageView1.setLayoutParams(params);
        loadImage(imageView1,imageUrls.get(0));
        linearLayout.addView(imageView1);

        createVerticalLine(linearLayout);

        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(viewWidth, ViewGroup.LayoutParams.MATCH_PARENT));
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
        linearLayout.addView(linearLayout2);
        addView(linearLayout);
    }

    public void fourImageStyle() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2*twoImageHeight));

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
        linearLayout.addView(linearLayout1);
        createHorizontalLine(linearLayout);
        linearLayout.addView(linearLayout2);
        addView(linearLayout);
    }

    public void fiveImageStyle() {
        fourImageStyle();
        createNumTextView();
    }

    public void sixImageStyle() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2*threeImageHeight+lineWidth));

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
        linearLayout.addView(linearLayout1);
        createHorizontalLine(linearLayout);
        linearLayout.addView(linearLayout2);
        addView(linearLayout);
    }

    public void moreImageStyle() {
        sixImageStyle();
        createNumTextView();
    }

    private void loadImage(ImageView imageView,String url){
        ImageUtils.loadImage(getContext(),imageView,url,AppConfig.themeOccupancy,AppConfig.themeError,AppConfig.loadImageSpeed);
    }

    private void createNumTextView(){
        TextView textView = new TextView(getContext());
        textView.append(imageNum+" 图");
        textView.setPadding(SystemUtils.dp2px(6),SystemUtils.dp2px(3),SystemUtils.dp2px(6),SystemUtils.dp2px(3));
        textView.setTextSize(13);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setBackground(createDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.bottomMargin = SystemUtils.dp2px(10);
        params.rightMargin = SystemUtils.dp2px(10);
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
        float cornerRadius = SystemUtils.dp2px(5);
        drawable.setCornerRadius(cornerRadius);

        // 设置颜色，使用稍微透明的黑色，格式是ARGB，这里透明度设置为128（取值范围是0-255，0表示完全透明，255表示完全不透明）
        drawable.setColor(Color.parseColor("#222222"));
        drawable.setAlpha(168);

        return drawable;
    }
}
