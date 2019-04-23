package demo.zp.zpdialogdemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *@author mysterylove caiyu.happy@163.com
 *Description
 *安卓图片处理类
 *@param 
 *@return  
 */
public class BitmapAndDrawable {

	
	
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *将两张画布拼接成一张画布
//	 *@param Bitmap,Bitmap
	 *@return Bitmap 
	 */
	public static Bitmap add2Bitmap(Bitmap first, Bitmap second) {
        int width =first.getWidth() + second.getWidth();
        int height = Math.max(first.getHeight(), second.getHeight());
        Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getWidth(), 0, null);
		first.recycle();
		second.recycle();
        return result;
	}
	
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *传入两张图片直接将图片拼接成图片 
//	 *@param Resources res,int id1,int id2
	 *@return Drawable 
	 */
	public static Drawable getBitmapResult(Resources res, int id1, int id2){
		Bitmap bmp1 = BitmapFactory.decodeResource(res, id1);
		Bitmap bmp2 = BitmapFactory.decodeResource(res, id2);
		Bitmap result=add2Bitmap(bmp1,bmp2);
		BitmapDrawable bd= new BitmapDrawable(res, result);
		return bd;
	}
	
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *Drawable缩放
//	 *@param Drawable drawable, int w, int h
	 *@return Drawable 
	 */
	@SuppressWarnings("deprecation")
	public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
		 int width = drawable.getIntrinsicWidth();
		 int height = drawable.getIntrinsicHeight();
		 // drawable转换成bitmap
		 Bitmap oldbmp = drawableToBitmap(drawable);
		// 创建操作图片用的Matrix对象
		Matrix matrix = new Matrix();
		// 计算缩放比例
		float sx = ((float) w / width);
		float sy = ((float) h / height);
		// 设置缩放比例
		matrix.postScale(sx, sy);
		// 建立新的bitmap，其内容是对原bitmap的缩放后的图
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
				matrix, true);
		return new BitmapDrawable(newbmp);
	}
	
	
	
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *将Drawable转化为Bitmap
//	 *@param Drawable drawable
	 *@return Bitmap 
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}


	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *Bitmap缩放
//	 *@param Bitmap bitmap, int width, int height
	 *@return Bitmap
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}


	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *Bitmap → byte[]
//	 *@param Bitmap bm
	 *@return byte[]
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}


	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *byte[] → Bitmap
//	 *@param byte[] b
	 *@return Bitmap
	 */
	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *获得圆角图片
//	 *@param Bitmap bitmap, float roundPx
	 *@return Bitmap
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}


	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *获得带倒影的图片
//	 *@param Bitmap bitmap
	 *@return Bitmap
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }



	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *根据银行卡号数字字符将资源图片拼接为对应数字图片，中间图片产物已经回收
//	 *@param  Resources res,char[] a,int[]b
	 *@return Drawable
	 */
	public static Drawable getProduceDrawable(Resources res, char[] a, int[]b){
		Bitmap result=null;
		int k;
		/**
		 * Bitmap[] factory={BitmapFactory.decodeResource(res, b[0]),BitmapFactory.decodeResource(res, b[1]),BitmapFactory.decodeResource(res, b[2]),BitmapFactory.decodeResource(res, b[3])
		 * ,BitmapFactory.decodeResource(res, b[4]),BitmapFactory.decodeResource(res, b[5]),BitmapFactory.decodeResource(res, b[6]),BitmapFactory.decodeResource(res, b[7]),BitmapFactory.decodeResource(res, b[8]),
		 * BitmapFactory.decodeResource(res, b[9]),BitmapFactory.decodeResource(res, b[10]),BitmapFactory.decodeResource(res, b[11])};
		 * */
		for(int i=0;i<a.length;i++){
			if(a[i]!=' '){
				k= Integer.parseInt(a[i]+"");
				if(i==0){
					result= BitmapFactory.decodeResource(res, b[k]);
					result=add2Bitmap(result, BitmapFactory.decodeResource(res, b[11]));//拼接缝隙，有美工可以删除
					continue;
				}
				result=add2Bitmap(result, BitmapFactory.decodeResource(res, b[k]));
				result=add2Bitmap(result, BitmapFactory.decodeResource(res, b[11]));//拼接缝隙
			}else{
				result=add2Bitmap(result, BitmapFactory.decodeResource(res, b[10]));
				result=add2Bitmap(result, BitmapFactory.decodeResource(res, b[11]));//拼接缝隙
			}
		}
		BitmapDrawable bd=new BitmapDrawable(res,result);
		return bd;
	}

	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *高斯模糊背景，建议单独开辟子线程,>4.4才可以使用此方法
	 *导入renderscript-v8.jar,android sdk  路径下
	 *androidsdk\sdk\build-tools\22.0.0\renderscript\lib\packaged
	 *librsjni.so和libRSSupport.so
//	 *@param  View v,Bitmap bitmap
	 *@return void
	 */
	@SuppressLint("NewApi")
	public void GosiBackGround(Context context, View v, Bitmap bitmap){
		float radius = 20;
		Bitmap overlay = Bitmap.createBitmap((int) (v.getMeasuredWidth()),(int) (v.getMeasuredHeight())
				, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		canvas.translate(-v.getLeft(), -v.getTop());
		canvas.drawBitmap(bitmap, 0, 0, null);
		RenderScript rs = RenderScript.create(context);
		Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
		ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs,overlayAlloc.getElement());
		blur.setInput(overlayAlloc);
		blur.setRadius(radius);
		blur.forEach(overlayAlloc);
		overlayAlloc.copyTo(overlay);
		if(Build.VERSION.SDK_INT>=16){
			v.setBackground(new BitmapDrawable(context.getResources(), overlay));
		}else{
			v.setBackgroundDrawable(new BitmapDrawable(context.getResources(), overlay));
		}

		rs.destroy();
	}
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *图片载入防止oom
//	 *@param  String path
	 *@return Bitmap
	 */
	public static Bitmap DecFile(String path){
		BitmapFactory.Options bfOptions=new BitmapFactory.Options();
		bfOptions.inDither=false; 
		bfOptions.inPurgeable=true; 
		bfOptions.inTempStorage=new byte[12 * 1024];  
		bfOptions.inJustDecodeBounds = true; 
		File file = new File(path);
		FileInputStream fs=null;
		Bitmap bmp = null;
		try { 
			fs = new FileInputStream(file);
			if(fs != null) 
				bmp = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
		     } catch (Exception e) {
//			e.printStackTrace(); 
//		    	 AresLog.e("图片过大", "OUT OF MOMERY");
		}
		return bmp;
		//当然要将取得图片进行放缩显示等处理也可以在以上得到的bmp进行。
		//图片处理后记得回收内存
//		if(!bmp.isRecycled()){
//		   bmp.recycle();   //回收图片所占的内存         
//		   System.gc();  //提醒系统及时回收
//		}
	}
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *规定好宽高，压缩图片载入防止oom
//	 *@param  File dst, int width, int height
	 *@return Bitmap
	 */
	public static Bitmap getBitmapFromFile(File dst, int width, int height) {
	    if (null != dst && dst.exists()) {
	        BitmapFactory.Options opts = null;
	        if (width > 0 && height > 0) {
	            opts = new BitmapFactory.Options();

	            //设置inJustDecodeBounds为true后，decodeFile并不分配空间，此时计算原始图片的长度和宽度
	            opts.inJustDecodeBounds = true;
	            BitmapFactory.decodeFile(dst.getPath(), opts);
	            // 计算图片缩放比例
	            final int minSideLength = Math.min(width, height);
	            opts.inSampleSize = computeSampleSize(opts, minSideLength,
	                    width * height);

	            //这里一定要将其设置回false，因为之前我们将其设置成了true
	            opts.inJustDecodeBounds = false;
	            opts.inInputShareable = true;
	            opts.inPurgeable = true;
	        }
	        try {
	            return BitmapFactory.decodeFile(dst.getPath(), opts);
	        } catch (OutOfMemoryError e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}


	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *自适应View试图的大小，压缩图片载入防止oom
//	 *@param  File path,ImageView img
	 *@return Bitmap
	 */
	public static Bitmap decBitmapFromFile(File dst, View v){
		if(null!=dst&&dst.exists()){
			v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
			int width=v.getMeasuredWidth();
			int hight=v.getMeasuredHeight();
			try{
				BitmapFactory.Options factoryOptions=new BitmapFactory.Options();
				factoryOptions.inJustDecodeBounds=true;
				BitmapFactory.decodeFile(dst.getPath(),factoryOptions);
				int imgwidth=factoryOptions.outWidth;
				int imghight=factoryOptions.outHeight;
				int scaleFactor= Math.min(imgwidth/width,imghight/hight);//确定压缩比例
				factoryOptions.inJustDecodeBounds=false;
				factoryOptions.inSampleSize=scaleFactor;
				factoryOptions.inPurgeable=true;
				Bitmap bitmap= BitmapFactory.decodeFile(dst.getPath(),factoryOptions);
				return bitmap;
			}catch (Exception e){
			}
		}
		return null;

	}
	/**
	 *@author mysterylove caiyu.happy@163.com
	 *Description
	 *计算描述
	 *If set to a value > 1, requests the decoder to subsample the original image, returning a smaller image to save memory.
	 *(1 -> decodes full size; 2 -> decodes 1/4th size; 4 -> decode 1/16th size). 
	 *Because you rarely need to show and have full size bitmap images on your phone. 
	 *For manipulations smaller sizes are usually enough.
//	 *@param  File dst, int width, int height
	 *@return Bitmap
	 */
	public static int computeSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {
	    int initialSize = computeInitialSampleSize(options, minSideLength,
	            maxNumOfPixels);

	    int roundedSize;
	    if (initialSize <= 8) {
	        roundedSize = 1;
	        while (roundedSize < initialSize) {
	            roundedSize <<= 1;
	        }
	    } else {
	        roundedSize = (initialSize + 7) / 8 * 8;
	    }

	    return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {
	    double w = options.outWidth;
	    double h = options.outHeight;

	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
	            .sqrt(w * h / maxNumOfPixels));
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
	            .floor(w / minSideLength), Math.floor(h / minSideLength));

	    if (upperBound < lowerBound) {
	        // return the larger one when there is no overlapping zone.
	        return lowerBound;
	    }

	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
	        return 1;
	    } else if (minSideLength == -1) {
	        return lowerBound;
	    } else {
	        return upperBound;
	    }
	}
	
	/**
	 * @author Mario Klingemann
	 *Description
	 *高斯模糊背景算法,计算时间较长
//	 *@param  View v,Bitmap bitmap
	 *@return Bitmap
	 */
	public static Bitmap doBlur(Bitmap sentBitmap, int radius,
								boolean canReuseInBitmap) {

		// Stack Blur v1.0 from
		// http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
		// Java Author: Mario Klingemann <mario at quasimondo.com>
		// http://incubator.quasimondo.com
		// created Feburary 29, 2004
		// Android port : Yahel Bouaziz <yahel at kayenko.com>
		// http://www.kayenko.com
		// ported april 5th, 2012
		// This is a compromise between Gaussian Blur and Box blur
		// It creates much better looking blurs than Box Blur, but is
		// 7x faster than my Gaussian Blur implementation.
		// I called it Stack Blur because this describes best how this
		// filter works internally: it creates a kind of moving stack
		// of colors whilst scanning through the image. Thereby it
		// just has to add one new block of color to the right side
		// of the stack and remove the leftmost color. The remaining
		// colors on the topmost layer of the stack are either added on
		// or reduced by one, depending on if they are on the right or
		// on the left side of the stack.
		// If you are using this algorithm in your code please add
		// the following line:
		// Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

		Bitmap bitmap;
		if (canReuseInBitmap) {
			bitmap = sentBitmap;
		} else {
			bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
		}
		if (radius < 1) {
			return (null);
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);
		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;
		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];
		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}
		yw = yi = 0;
		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;
		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;
			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;
				sir = stack[i + radius];
				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];
				rbs = r1 - Math.abs(i);
				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				// Preserve alpha channel: ( 0xff000000 & pix[yi] )
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
						| (dv[gsum] << 8) | dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];
				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi += w;
			}
		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}
	
	/**
	 * 回收ImageView图片资源
	 *@author mysterylove caiyu.happy@163.com
//	 *@param ImageView view
	 *@return null
	 */
	@SuppressWarnings("deprecation")
	public static void recycleImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        //drawable资源
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
        	imageView.setImageResource(0);
        	imageView.setImageDrawable(null);
        	imageView.setImageBitmap(null);
        	//imageView = null;
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
            	Log.i("TAG", "recycleViewBg名称："+bitmap);
//            	Log.i("TAG", "recycleViewBg大小："+bitmap.getByteCount());
            	bitmapDrawable.setCallback(null);
            	bitmapDrawable = null;
                bitmap.recycle();
                bitmap = null;
                System.gc();
//                return ;
            }
        }
        //背景资源
        Drawable drawable_background = null;
        if(null!=imageView.getBackground()){
        	drawable_background=(Drawable)imageView.getBackground();
        }
        if(drawable_background != null && drawable_background instanceof BitmapDrawable){
        	imageView.setBackgroundDrawable(null);
        	imageView.setBackgroundResource(0);
        	imageView = null;
        	BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable_background;
        	drawable_background  = null;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
            	Log.i("TAG", "recycleViewBg名称："+bitmap);
//            	Log.i("TAG", "recycleViewBg大小："+bitmap.getByteCount());
            	bitmapDrawable.setCallback(null);
            	bitmapDrawable = null;
                bitmap.recycle();
                bitmap = null;
                System.gc();
            }
        }
        
    }
	/**
	 * 回收View图片资源
	 *@author mysterylove caiyu.happy@163.com
//	 *@param View v
	 *@return null
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void recycleViewBg(View v){
		//background
		Drawable drawable = v.getBackground();
		if(drawable != null && drawable instanceof BitmapDrawable){
			v.setBackgroundDrawable(null);
			v.setBackgroundResource(0);
			v = null;
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();

            if (bitmap != null && !bitmap.isRecycled()) {
//				AresLog.i("TAG", "recycleViewBg名称："+bitmap);
//				AresLog.i("TAG", "recycleViewBg大小："+bitmap.getByteCount());//bitmap.getByteCount()
            	bitmapDrawable.setCallback(null);
            	bitmapDrawable = null;
                bitmap.recycle();
                bitmap = null;
                System.gc();
                return ;
            }
		}
	}
	
	 /**
     * 基于质量的压缩算法， 此方法未 解决压缩后图像失真问题
     * <br> 可先调用比例压缩适当压缩图片后，再调用此方法可解决上述问题
//     * @param bts
     * @param maxBytes 压缩后的图像最大大小 单位为byte
     * @return
     */

     public  static Bitmap compressBitmap(Bitmap bitmap, long maxBytes) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.PNG, 100, baos);
			int options = 90;
			while (baos.toByteArray().length > maxBytes) {
				baos.reset();
				bitmap.compress(CompressFormat.PNG, options, baos);
				options -= 10;
			}
			byte[] bts = baos.toByteArray();
			Bitmap bmp = BitmapFactory.decodeByteArray(bts, 0, bts.length);
			baos.close();
			return bmp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}

     }
     
     //通过图片id加载图片
     @SuppressWarnings("deprecation")
	public  static Bitmap createBitmap(Context context, int resId, int zoom){//zoom=1:不压缩.zoom=2:压缩1/4
    	 Bitmap bitmap = null;
    	 InputStream is = null;
		try {
			BitmapFactory.Options opt = new BitmapFactory.Options();
	        opt.inSampleSize = zoom;//设置缩放比例 
//			opt.inPreferredConfig = Bitmap.Config.RGB_565;   
			opt.inPurgeable = true;  
			opt.inInputShareable = true;  
			//获取资源图片  
			is = context.getResources().openRawResource(resId);
			bitmap = BitmapFactory.decodeStream(is,null,opt);
			//对图片做压缩处理
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}finally{
			if(null!=is){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					
				}
			}
			
		}
 		return bitmap;
     }
     
//   ///设置imageview的背景还是内容
//   @SuppressWarnings("deprecation")
//   public  static void logoType(Context context, ImageView view, String BankId , boolean isBg, String type){
//	   int icon = 0;
//	   if (BankId.equals("BOCOM")||BankId.equals("BOCOM2")) {//包括二类借记卡
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.bocom_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.bocom_logo;
//		   }else{
//			   icon = R.drawable.bocom_logo03;
//		   }
//	   }else if (BankId.equals("ABC")||BankId.equals("ABCB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.abc_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.abc_logo;
//		   }else{
//			   icon = R.drawable.abc_logo03;
//		   }
//	   } else if (BankId.equals("BOC")||BankId.equals("BOCB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.boc_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.boc_logo;
//		   }else{
//			   icon = R.drawable.boc_logo03;
//		   }
//	   } else if (BankId.equals("BOS")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.bos_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.bos_logo;
//		   }else{
//			   icon = R.drawable.bos_logo03;
//		   }
//	   } else if (BankId.equals("BRCB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.brcb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.brcb_logo;
//		   }else{
//			   icon = R.drawable.brcb_logo03;
//		   }
//	   } else if (BankId.equals("CCB")||BankId.equals("CCBB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.ccb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.ccb_logo;
//		   }else{
//			   icon = R.drawable.ccb_logo03;
//		   }
//	   } else if (BankId.equals("CEB")||BankId.equals("CEBB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.ceb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.ceb_logo;
//		   }else{
//			   icon = R.drawable.ceb_logo03;
//		   }
//	   } else if (BankId.equals("CIB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.cib_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.cib_logo;
//		   }else{
//			   icon = R.drawable.cib_logo03;
//		   }
//	   } else if (BankId.equals("CMB")||BankId.equals("CMBB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.cmb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.cmb_logo;
//		   }else{
//			   icon = R.drawable.cmb_logo03;
//		   }
//	   } else if (BankId.equals("CMBC")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.cmbc_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.cmbc_logo;
//		   }else{
//			   icon = R.drawable.cmbc_logo03;
//		   }
//	   } else if (BankId.equals("GDB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.gdb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.gdb_logo;
//		   }else{
//			   icon = R.drawable.gdb_logo03;
//		   }
//	   } else if (BankId.equals("HXB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.hxb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.hxb_logo;
//		   }else{
//			   icon = R.drawable.hxb_logo03;
//		   }
//	   } else if (BankId.equals("ICBC")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.icbc_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.icbc_logo;
//		   }else{
//			   icon = R.drawable.icbc_logo03;
//		   }
//	   } else if (BankId.equals("PAB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.pab_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.pab_logo;
//		   }else{
//			   icon = R.drawable.pab_logo03;
//		   }
//	   } else if (BankId.equals("PSBC")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.psbc_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.psbc_logo;
//		   }else{
//			   icon = R.drawable.psbc_logo03;
//		   }
//	   } else if (BankId.equals("SPDB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.spdb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.spdb_logo;
//		   }else{
//			   icon = R.drawable.spdb_logo03;
//		   }
//	   } else if (BankId.equals("SRCB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.srcb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.srcb_logo;
//		   }else{
//			   icon = R.drawable.srcb_logo03;
//		   }
//	   } else if (BankId.equals("YKCB")||BankId.equals("CITI")||BankId.equals("CNCB")) {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.ykcb_logo;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.ykcb_logo;
//		   }else{
//			   icon = R.drawable.ykcb_logo03;
//		   }
//	   } else {
//		   if(StringUtil.isNotEmpty(type)&&type.equals("1")){//卡片详情
//			   icon = R.drawable.other_logo02;
//		   }else if(StringUtil.isNotEmpty(type)&&type.equals("2")){//卡列表
//			   icon = R.drawable.other_logo02;
//		   }else{
//			   icon = R.drawable.other_logo03;
//		   }
//	   }
//	   if(isBg){
//		   view.setBackgroundDrawable(new BitmapDrawable(BitmapAndDrawable.createBitmap(context, icon,1)));
//	   }else{
//		   view.setImageDrawable(new BitmapDrawable(BitmapAndDrawable.createBitmap(context, icon,1)));
//	   }
//   }
     public static Bitmap getBitmapFromUrl(String url) throws Exception {
 		byte[] bytes = getBytesFromUrl(url);
 		return byteToBitmap(bytes);
 	}
     
     public static Bitmap byteToBitmap(byte[] byteArray){
 		if(byteArray.length!=0){ 
             return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
         } 
         else { 
             return null; 
         }  
 	}
     
     public static InputStream getRequest(String path) throws Exception {
 		URL url = new URL(path);
 		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 		conn.setRequestMethod("GET");
 		conn.setConnectTimeout(8000);
 		if (conn.getResponseCode() == 200&&conn.getInputStream()!=null){
 			return conn.getInputStream();
 		}
 		return null;
 	}
     
     public static byte[] getBytesFromUrl(String url) throws Exception {
		 return readInputStream(getRequest(url));
	}
     
     public static byte[] readInputStream(InputStream inStream) throws Exception {
 		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
 		byte[] buffer = new byte[4096];
 		int len = 0;
 		while ((len = inStream.read(buffer)) != -1) {
 			outSteam.write(buffer, 0, len);
 		}
 		outSteam.close();
 		inStream.close();
 		return outSteam.toByteArray();
 	}
	public static Bitmap getViewBitmap(View v, boolean isParemt){
		if (v == null) {
			return null;
		}
		Bitmap screenshot;
		v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		screenshot = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Config.ARGB_8888);
		Canvas c = new Canvas(screenshot);
		v.draw(c);
//		v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//		v.buildDrawingCache();
//		screenshot = v.getDrawingCache();
		return screenshot;
	}
}
