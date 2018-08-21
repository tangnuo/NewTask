package com.kedacom.module_common.imageloader.picasso;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.kedacom.module_common.imageloader.IImageLoader;

/**
 * @Dec ：使用Picass加载图片：http://square.github.io/picasso/
 * @Author : Caowj
 * @Date : 2018/6/14 12:06
 */
public class PicassoImpl implements IImageLoader {
    @Override
    public void load(ImageView imageView, String url) {
//        Picasso.with(imageView.getContext())
//                .load(url)
////                .resize(50, 50)
//                .centerCrop()
//                .into(imageView);
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
