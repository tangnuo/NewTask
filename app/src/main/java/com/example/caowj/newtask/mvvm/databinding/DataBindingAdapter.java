package com.example.caowj.newtask.mvvm.databinding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.kedacom.imageloader.GlideUtil;

/**
 * Created by ruzhan on 2017/7/15.
 */

public final class DataBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideUtil.loadNormal(imageUrl, iv);
        }
    }

    @BindingAdapter({"imageThumbUrl"})
    public static void loadImageThumb(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideUtil.loadNormal(imageUrl, iv);
        }
    }

    @BindingAdapter({"imageCropCircleUrl"})
    public static void loadImageCropCircle(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideUtil.loadNormal(imageUrl, iv);
        }
    }

    @BindingAdapter({"imageCropCircleUrlRes"})
    public static void loadImageCropCircle(ImageView iv, int resId) {
        if (resId > 0) {
            GlideUtil.loadResourceId(resId, iv);
        }
    }
}
