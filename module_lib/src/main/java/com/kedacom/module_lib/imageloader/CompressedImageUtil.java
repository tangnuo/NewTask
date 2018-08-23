package com.kedacom.module_lib.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.kedacom.module_lib.utils.AppUtil;
import com.kedacom.module_lib.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Dec ：图片压缩工具
 * @Author : Caowj
 * @Date : 2018/7/19 14:19
 */
public class CompressedImageUtil {

    /**
     * 根据路径获得突破并【压缩】返回bitmap用于显示
     * 按照宽480,、高800进行缩放
     * 可以尝试使用createImageThumbnail替换
     *
     * @param filePath 本地文件路径
     * @return Bitmap
     */
    public static Bitmap getSmallBitmapByCompressedMemory(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int scale = 2;

//        使用一：旧版使用的压缩方法
//        scale = calculateInSampleSize(options, 480, 800);

        //使用二：创建缩略图时使用128*128。
//      scale = options.inSampleSize = computeSampleSize(options, -1, 128*128);

        //使用三：
        scale = computeSampleSize(options, -1, (int) (0.3 * 1024 * 1024));
        options.inSampleSize = scale;
        //ARGB_4444会导致质量问题，会有阴影
//		options.inPreferredConfig = Bitmap.Config.ARGB_4444;
//		LogUtil.myD( "getSmallBitmap,缩放值scale:"+scale);
//        LogUtil.myD("scale:" + scale);
        options.inJustDecodeBounds = false;

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            LogUtil.myE("图片解析异常：" + e.getMessage());
        }

        if (bitmap == null) {
            //删除无效的缓存图片
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        return bitmap;
    }

    /**
     * 方法一：计算压缩的比率之内存（荐）
     *
     * @param options        解析图片所需的BitmapFactory.Options
     * @param minSideLength  调整后图片最小的宽或高值,一般赋值为 -1
     * @param maxNumOfPixels 调整后图片的内存占用量上限
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
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

    /**
     * 计算原始大小
     *
     * @param options        解析图片所需的BitmapFactory.Options
     * @param minSideLength  调整后图片最小的宽或高值,一般赋值为 -1
     * @param maxNumOfPixels 调整后图片的内存占用量上限
     * @return
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
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
     * 获取本地图片,并计算压缩比例.
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmapByCompressedWH(Context mContext, String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int ss = calculateInSampleSize(mContext, options);
        LogUtil.myD("本地图片上传的缩放比例ss:" + ss + ",宽：" + options.outWidth + "，高：" + options.outHeight);
        options.inSampleSize = ss;
        options.inJustDecodeBounds = false;

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            LogUtil.myE("图片解析异常：" + e.getMessage());
        }

        if (bitmap == null) {
            //删除无效的缓存图片
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        return bitmap;
    }

    /**
     * 方法二：计算压缩的比率之宽高
     * 根据屏幕分辨率（最低：1280*720） 计算图片的缩放值
     *
     * @param mContext
     * @param options
     * @return
     */
    public static int calculateInSampleSize(Context mContext, BitmapFactory.Options options) {
        // Raw height and width of image
        int screenW = AppUtil.getScreenWidth(mContext);
        int screenH = AppUtil.getScreenHeight(mContext);
        LogUtil.myD("手机分辨率:宽：" + screenW + "，高：" + screenH);
        if (screenH < 1280) {
            screenH = 1280;
        }
        if (screenW < 720) {
            screenW = 720;
        }
        int inSampleSize = calculateInSampleSizeDefault(options, screenW, screenH);
        return inSampleSize;
    }

    /**
     * 计算缩放比例的默认方法
     *
     * @param options
     * @param reqW    限制的宽度
     * @param reqH    限制的高度
     * @return
     */
    private static int calculateInSampleSizeDefault(BitmapFactory.Options options, int reqW, int reqH) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;//等于3,表示宽高分别缩放到最初的1/3，即原图的1/9

        if (height > reqH || width > reqW) {
            final int heightRatio = Math.round((float) height / (float) reqH);
            final int widthRatio = Math.round((float) width / (float) reqW);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        if (inSampleSize < 1) {
            inSampleSize = 1;
        }
        return inSampleSize;
    }


    /**
     * 方法三：压缩图片质量,减少所占空间，和内存无关
     *
     * @param image  原bitmap
     * @param kCount (单位kb)
     * @return
     */
    public static Bitmap compressImage(Bitmap image, int kCount) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > kCount) { // 循环判断如果压缩后图片是否大于1000kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            if (options > 10) {
                options -= 10;// 每次都减少10
            }
            image.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        // 对于大图片，最好不要直接使用decodeStream()，先压缩。参考： http://www.juapk.com/thread-37-1-1.html
        image = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return image;
    }


    /**
     * 方法四：压缩图片宽高
     *
     * @param path  图片原路径
     * @param width 设定图片的宽高（正方形）
     * @return
     * @throws IOException
     */
    public static Bitmap revitionImageSize(String path, int width) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= width) && (options.outHeight >> i <= width)) {
                in = new BufferedInputStream(new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }


    /**
     * 保存城管执法的图片
     * <p>
     * 1、图片加水印
     * <p>
     * 2、图片小于50k
     *
     * @param localUrl 本地图片路径（例如：/storage/emulated/0/Pictures/multi_image_20180808_130928.jpg）
     * @param path     目标文件路径（例如：/storage/emulated/0/zhcx/img/cgzf/）
     * @param filename 文件名称（例如：33000019_0.jpg）
     */
    public static void saveCgzfPic(String localUrl, String path, String filename) {

        //获得图片的宽和高，但并不把图片加载到内存当中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(localUrl, options);

        options.inSampleSize = computeSampleSize(options, -1, (int) (0.5 * 1024 * 1024));

        //使用获取到的inSampleSize再次解析图片
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(localUrl, options);


        LogUtil.myD("inSampleSize:" + options.inSampleSize);


        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File file = new File(rootFile, filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            //质量压缩
            int quality = 95;

            while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于50kb,大于继续压缩
                LogUtil.d("caowj", "length:" + baos.toByteArray().length + ",,,quality:" + quality);
                baos.reset(); // 重置baos即清空baos
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);// 这里压缩options%，把压缩后的数据存放到baos中

                //每次减少5%质量
                if (quality > 5) {//避免出现options<=0
                    quality -= 5;
                } else {
                    break;
                }
            }

            LogUtil.d("caowj", "2length:" + baos.toByteArray().length + ",,,2quality:" + quality);


            //保存图片
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);

//// TODO: 2018/8/8 问题：byte数组解析成bitmap后，再次解析成byte数组，变大了，为什么？
//            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
//            L.d("caowj", "3length:" + baos.toByteArray().length);
//
//            Bitmap bitmap2 = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//
//            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            L.d("caowj", "sss:" + baos.toByteArray().length);
//            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, fos);


            fos.flush();
            fos.close();
            if (bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
