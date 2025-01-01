package com.chwltd.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import android.content.Context;

public class ImageUtils {
  /*
  Lottie
  */
  public static void playLottie(ImageView v, String lottie) {
    LottieDrawable lottieDrawable = new LottieDrawable();
    v.setImageDrawable(lottieDrawable);
    lottieDrawable.setComposition(
        LottieCompositionFactory.fromJsonStringSync(lottie, null).getValue());
    lottieDrawable.setRepeatCount(LottieDrawable.INFINITE);
    lottieDrawable.start();
  }

  /*
  Glide加载图片
  */
  public static void loadImage(Context context, ImageView imageView, String url) {
    Glide.with(context)
            .load(url)
            .into(imageView);
  }

    public static void loadImage(Context context, ImageView imageView, String url ,int speed) {
        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(speed))
                .into(imageView);
    }

  public static void loadImage(Context context, ImageView imageView, String url, Drawable drawable, Drawable drawable2 ,int speed) {
    Glide.with(context)
            .load(url)
            .placeholder(drawable)
            .error(drawable2)
            .transition(DrawableTransitionOptions.withCrossFade(speed))
            .into(imageView);
  }

    public static void loadImage(Context context, ImageView imageView, String url, Drawable drawable, Drawable drawable2 ,int speed ,int w ,int h) {
        Glide.with(context)
                .load(url)
                .override(w,h)
                .placeholder(drawable)
                .error(drawable2)
                .transition(DrawableTransitionOptions.withCrossFade(speed))
                .into(imageView);
    }

  public static Bitmap getBitMBitmap(String urlpath) {
    final Bitmap[] bitmapHolder = new Bitmap[1];
    Thread thread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  URL url = new URL(urlpath);
                  URLConnection conn = url.openConnection();
                  conn.connect();
                  InputStream in = conn.getInputStream();
                  bitmapHolder[0] = BitmapFactory.decodeStream(in);
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            });
    thread.start(); // 启动新线程来异步执行网络请求和图片解码操作
    try {
      thread.join(); // 等待线程完成，以确保图片已正确加载和初始化在 bitmapHolder 中
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return bitmapHolder[0];
  }

  /*
  布局转Bitmap
  */
  public static Bitmap getBitmapFromView(View v) {
    Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
    Canvas c = new Canvas(b);
    v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
    // Draw background
    Drawable bgDrawable = v.getBackground();
    if (bgDrawable != null) {
      bgDrawable.draw(c);
    } else {
      c.drawColor(Color.WHITE);
    }
    // Draw view to canvas
    v.draw(c);
    return b;
  }
    /*
    mus类库
    */
    	//SVG转bitmap
	/*public Bitmap convertSvgToBitmap(byte[] svgData) {
        Bitmap bitmap = null;
        try {
            SVGDOMImplementation domImpl = new SVGDOMImplementation();
            Document document = domImpl.createDocument(null, "svg", null);
            SVGTranscoder transcoder = new SVGTranscoder();

            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(svgData));
            transcoder.addTranscodingHint(ImageTranscoder.KEY_BACKGROUND_COLOR, null);

            TranscoderOutput output = new TranscoderOutput(new ByteArrayOutputStream());
            transcoder.transcode(input, output);

            byte[] transcodedData = ((ByteArrayOutputStream) output.getOutputStream()).toByteArray();
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            canvas.drawBitmap(image, 0, 0, null);

            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
	*/
	
	public static void editImgInfor(String path)
	{
		try
		{
					}
		catch (Exception e)
		{}
	}
	
	
	//改变bitmap比例
	public static Bitmap resizeBitmap(Object src, float scaleWidth, float scaleHeight) {
		Bitmap srcBitmap=(Bitmap)src;
		if (srcBitmap == null) {
			return null;
		}

		// Get the current width and height of the bitmap
		int width = srcBitmap.getWidth();
		int height = srcBitmap.getHeight();

		// Calculate the new width and height according to the given scales
		int newWidth = (int) (width * scaleWidth);
		int newHeight = (int) (height * scaleHeight);
        
		// Create a matrix for the scaling transformation
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		// Create a new bitmap with the transformed dimensions
		Bitmap dstBitmap = Bitmap.createBitmap(newWidth, newHeight, srcBitmap.getConfig());

		// Create a canvas with the new bitmap
		Canvas canvas = new Canvas(dstBitmap);

		// Draw the scaled bitmap onto the canvas using the transformation matrix
		Paint paint = new Paint();
		canvas.drawBitmap(srcBitmap, matrix, paint);

		return dstBitmap;
	}
	
	
	
	//获取图片缩略图(压缩图片)
    public static Bitmap getImageThumbnail(String path, int width, int height, boolean b) {
		// 获取资源中的图像
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path,options);

		// 计算采样率
		options.inSampleSize = calculateInSampleSize(options, width, height);

		// 重新解码图像以获取缩略图
		options.inJustDecodeBounds = false;
		if(b) {
            options.inPreferredConfig = Bitmap.Config.RGB_565; // 减小内存占用
        }
		Bitmap bitmap = BitmapFactory.decodeFile(path,options);

		return bitmap;
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
	
	
	//图片文字化
	public static Bitmap getTextBitmap(Bitmap bitmap, String str, int i) {
        int width;
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap cannot be null.");
        }
        int width2 = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (bitmap.getWidth() % i == 0) {
            width = bitmap.getWidth();
        } else {
            width = ((bitmap.getWidth() / i) + 1) * i;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, bitmap.getHeight() % i == 0 ? bitmap.getHeight() : ((bitmap.getHeight() / i) + 1) * i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(4095);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < height) {
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 >= width2) {
                        break;
                    }
                    int[] pixels = getPixels(bitmap, i6, i4, i, i);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setColor(getAverage(pixels));
                    paint.setTextSize(i);
                    Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                    int i7 = i2;
                    i2++;
                    canvas.drawText(String.valueOf(str.charAt(i7)), i6, i4 - (i4 == 0 ? i + fontMetrics.ascent : (i + fontMetrics.ascent) * 2), paint);
                    if (i2 == str.length()) {
                        i2 = 0;
                    }
                    i5 = i6 + i;
                }
                i3 = i4 + i;
            } else {
                return createBitmap;
            }
        }
    }
	
	
	
	//图片颜色替换
	public static Bitmap replaceColor(Bitmap bitmap,String str, String str2) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        int width = copy.getWidth();
        int height = copy.getHeight();
        int parseColor = Color.parseColor(str);
        int parseColor2 = Color.parseColor(str2);
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                if (copy.getPixel(i2, i) == parseColor) {
                    copy.setPixel(i2, i, parseColor2);
                }
            }
        }
        return copy;
    }
	
	//图片黑白化
	public static Bitmap convertToBlackWhite(Bitmap bitmap)
	{
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = iArr[(width * i) + i2];
                int i4 = (int) ((((i3 & 16711680) >> 16) * 0.3d) + (((i3 & 65280) >> 8) * 0.59d) + ((i3 & 255) * 0.11d));
                iArr[(width * i) + i2] = (-16777216) | (i4 << 16) | (i4 << 8) | i4;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return ThumbnailUtils.extractThumbnail(createBitmap, width, height);
    }

	//图像模糊
	public static Bitmap blurBitmap(Context context, Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        create2.setRadius(f);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        create.destroy();
        return createBitmap;
    }

	//bitmap着色
	public static Bitmap setBpColor(Object ib, String tintColor)
	{
		Bitmap inputBitmap=(Bitmap)ib;
        if (inputBitmap == null)
		{
            return null;
        }
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), inputBitmap.getConfig());
        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(inputBitmap, 0, 0, paint);
        return outputBitmap;
    }
	
	

	//获取图片分辨率(宽*高)(文件获取)
	public static String getAticulatipn(Object bp)
	{
		Bitmap bt=(Bitmap)bp;
		return bt.getWidth() + "*" + bt.getHeight();
	}

    public static Drawable getTransparentBlurredBackground(Context context, int width, int height, float radius) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);

        Bitmap blurredBitmap = applyBlur(context, bitmap, radius);

        return new BitmapDrawable(context.getResources(), blurredBitmap);
    }

    private static Bitmap applyBlur(Context context, Bitmap bitmap, float radius) {
        Bitmap outputBitmap = Bitmap.createBitmap(bitmap);

        RenderScript rs = RenderScript.create(context);
        Allocation input = Allocation.createFromBitmap(rs, bitmap);
        Allocation output = Allocation.createTyped(rs, input.getType());

        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);

        output.copyTo(outputBitmap);

        rs.destroy();

        return outputBitmap;
    }
	
	//图片转base64
	public static String bitmapToBase64(Object bit)
	{
		Bitmap bitmap=(Bitmap)bit;
        String result = null;
        ByteArrayOutputStream baos = null;
        try
		{
            if (bitmap != null)
			{
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = android.util.Base64.encodeToString(bitmapBytes, android.util.Base64.DEFAULT);
            }
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }
		finally
		{
            try
			{
                if (baos != null)
				{
                    baos.flush();
                    baos.close();
                }
            }
			catch (IOException e)
			{
                e.printStackTrace();
            }
        }
        return result;
    }
	
	
	//base64转bitmap
	public static Bitmap base64ToBitmap(String base64Data)
	{  
		byte[] bytes =android.util.Base64.decode(base64Data, android.util.Base64.DEFAULT);  
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);  
	}
	
	//判断是否图片文件
    public static boolean isImageFile(String str)
	{
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outWidth != -1;
    }
    
    public static int[] getPixels(Bitmap bitmap, int i, int i2, int i3, int i4) {
        int[] iArr = new int[i3 * i4];
        int i5 = 0;
        for (int i6 = i2; i6 < i4 + i2 && i6 < bitmap.getHeight(); i6++) {
            int i7 = i;
            while (i7 < i3 + i && i7 < bitmap.getWidth()) {
                iArr[i5] = bitmap.getPixel(i7, i6);
                i7++;
                i5++;
            }
        }
        return iArr;
    }

    public static int getAverage(int[] iArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < iArr.length) {
            int i5 = iArr[i4];
            i += (16711680 & i5) >> 16;
            i4++;
            i3 += i5 & 255;
            i2 = ((65280 & i5) >> 8) + i2;
        }
        float length = iArr.length;
        return Color.argb(255, Math.round(i / length), Math.round(i2 / length), Math.round(i3 / length));
    }
}
