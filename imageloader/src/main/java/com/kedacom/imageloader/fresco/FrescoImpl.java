package com.kedacom.imageloader.fresco;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.kedacom.imageloader.IImageLoader;

/**
 * @Dec ：Fresco加载图片
 * @Author : Caowj
 * @Date : 2018/6/14 12:11
 */
public class FrescoImpl implements IImageLoader {
    @Override
    public void load(ImageView imageView, String url) {

    }

    @Override
    public void load(ImageView imageView, String url, int placeholderId, int errorId) {

    }

    @Override
    public void load(ImageView imageView, String url, RequestListener<Drawable> listener) {

    }

    @Override
    public void load(ImageView imageView, int resId, RequestListener<Drawable> listener) {

    }

    @Override
    public void loadThumb(ImageView imageView, String url) {

    }

    @Override
    public void loadCropCircle(ImageView imageView, String url) {

    }

    @Override
    public void loadResourceId(ImageView imageView, int resId) {

    }
}
