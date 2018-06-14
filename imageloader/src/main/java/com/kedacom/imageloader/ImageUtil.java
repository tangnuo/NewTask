package com.kedacom.imageloader;

import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.kedacom.imageloader.glide.GlideImpl;

/**
 * @Dec ：图片加载工具
 * @Author : Caowj
 * @Date : 2018/6/13 12:39
 */
public class ImageUtil {

    private static IImageLoader imageLoader;

    private ImageUtil() {
    }

    private static IImageLoader getInstance() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new GlideImpl();
//                    imageLoader = new PicassoImpl();
//                    imageLoader = new FrescoImpl();
                }
            }
        }
        return imageLoader;
    }


    /********************************************************/


    public static void loadCenterCrop(String url, ImageView view, int defaultResId) {
        ImageLoader.getInstance().load(view, url, defaultResId, defaultResId);
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(String url, ImageView view, int defaultResId, int errorResId) {
        ImageLoader.getInstance().load(view, url, defaultResId, defaultResId);
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(String url, ImageView view, RequestListener listener) {
        ImageLoader.getInstance().load(view, url, listener);
    }

    public static void loadNormal(String url, ImageView view) {
        ImageLoader.getInstance().load(view, url);
    }

    public static void loadResourceId(int url, ImageView view) {
        ImageLoader.getInstance().loadResourceId(view, url);
    }


}
