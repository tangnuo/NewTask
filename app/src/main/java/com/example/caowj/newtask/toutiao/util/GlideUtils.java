package com.example.caowj.newtask.toutiao.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.kedacom.imageloader.ImageLoader;
import com.kedacom.utils.NetWorkUtil;

/**
 * Created by Meiji on 2017/5/31.
 */
@GlideModule
public class GlideUtils extends AppGlideModule {

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            /*GlideApp.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop())
                    .into(view);*/
            ImageLoader.getInstance().load(view, url);
        }
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            /*GlideApp.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop().error(errorResId))
                    .into(view);*/

            ImageLoader.getInstance().load(view, url);
        }
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
//        GlideApp.with(context)
//                .load(url)
//                .transition(withCrossFade())
//                .apply(new RequestOptions().centerCrop())
//                .listener(listener)
//                .into(view);

        ImageLoader.getInstance().load(view, url, listener);
    }

    public static void loadNormal(Context context, String url, ImageView view) {

        ImageLoader.getInstance().load(view, url);
    }

}
