package com.kedacom.imageloader.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kedacom.imageloader.IImageLoader;
import com.kedacom.imageloader.R;

/**
 * Copyright ©2017 by ruzhan
 */

public class GlideImpl implements IImageLoader {

    private static final String KEY_MEMORY = "com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout";

    private static final float SIZE_MULTIPLIER = 0.3f;
    private static final int TIMEOUT_MS = 16000;

    private DrawableTransitionOptions normalTransitionOptions = new DrawableTransitionOptions()
            .crossFade();

    private static Option<Integer> TIMEOUT_OPTION =
            Option.memory(KEY_MEMORY, TIMEOUT_MS);

    @Override
    public void load(ImageView imageView, String url) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .fitCenter()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .diskCacheStrategy(DiskCacheStrategy.NONE);


        GlideApp.with(imageView.getContext())
                .load(url)
                .transition(normalTransitionOptions)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void load(ImageView imageView, String url, int placeholderId, int errorId) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholderId)
                .error(errorId)
                .diskCacheStrategy(DiskCacheStrategy.DATA);
//                .override(200, 100);//指定图片大小;


        GlideApp.with(imageView.getContext())
                .load(url)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .transition(normalTransitionOptions)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void load(ImageView imageView, String url, final RequestListener<Drawable> listener) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .transition(normalTransitionOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        listener.onLoadFailed(e, model, target, isFirstResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        listener.onResourceReady(resource, model, target, dataSource, isFirstResource);
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void load(ImageView imageView, int resId, final RequestListener<Drawable> listener) {
        GlideApp.with(imageView.getContext())
                .load(resId)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        listener.onLoadFailed(e, model, target, isFirstResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        listener.onResourceReady(resource, model, target, dataSource,
                                isFirstResource);
                        return false;
                    }
                })
                .transition(normalTransitionOptions)
                .into(imageView);
    }

    @Override
    public void loadThumb(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .thumbnail(SIZE_MULTIPLIER)
                .transition(normalTransitionOptions)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(imageView);
    }

    @Override
    public void loadCropCircle(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .transition(normalTransitionOptions)
                .transform(new MultiTransformation<>(new CircleCrop()))
                .placeholder(R.drawable.ic_launcher_round)
                .error(R.drawable.ic_launcher_round)
                .into(imageView);
    }

    @Override
    public void loadResourceId(ImageView imageView, int resId) {
        GlideApp.with(imageView.getContext())
                .load(resId)
                .set(TIMEOUT_OPTION, TIMEOUT_MS)
                .transition(normalTransitionOptions)
                .transform(new MultiTransformation<>(new CircleCrop()))
                .placeholder(R.drawable.ic_launcher_round)
                .error(R.drawable.ic_launcher_round)
                .into(imageView);
    }

}
