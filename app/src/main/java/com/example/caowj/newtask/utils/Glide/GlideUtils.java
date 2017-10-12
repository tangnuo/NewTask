package com.example.caowj.newtask.utils.Glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.module1.listener.BitmapLoadingListener;
import com.example.caowj.newtask.utils.AlimmdnUtil;
import com.example.caowj.newtask.utils.Glide.GlideTransformation.RotateTransformation;
import com.example.caowj.newtask.utils.LogUtil;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.Glide.with;

/**
 * Glide工具类<br/>
 * 参考：http://www.2cto.com/kf/201606/516590.html<br/>
 * http://mrfu.me/2016/02/27/Glide_Getting_Started/ <br/>
 * transformation框架：https://github.com/wasabeef/glide-transformations<br/>
 * <p>
 * 注意：监听器和缩略图方法不要同时使用，否则会导致监听器响应两次。
 * <br/>
 * package: com.jsfengling.qipai.tools.Glide
 * author: caowj
 * date: 2016/7/7 13:48
 */
public class GlideUtils {
    //默认配置
    public static GlideLoadConfig defConfig = new GlideLoadConfig.Builder().
            setCropType(GlideLoadConfig.CENTER_CROP).
//            setCropCircle(true).//设置裁剪，参考：cropCircle，roundedCorners，grayscale，blur,rotate
        setAsBitmap(true).
                    setThumbnail(0.5f).
                    setPlaceHolderResId(R.drawable.img_loading).
                    setErrorResId(R.drawable.img_loading).
//                    setSize(new GlideLoadConfig.OverrideSize(720, 1280)).
        setDiskCacheStrategy(GlideLoadConfig.DiskCache.SOURCE).
                    setPrioriy(GlideLoadConfig.LoadPriority.HIGH).build();

    public static GlideLoadConfig fitCenter = new GlideLoadConfig.Builder().
            setCropType(GlideLoadConfig.FIT_CENTER).
            setAsBitmap(true).
            setThumbnail(0.5f).
            setPlaceHolderResId(R.drawable.img_loading).
            setErrorResId(R.drawable.img_loading).
            setDiskCacheStrategy(GlideLoadConfig.DiskCache.SOURCE).
            setPrioriy(GlideLoadConfig.LoadPriority.HIGH).build();

    /**
     * 针对非阿里百川解析图片
     *
     * @param width
     * @param height
     * @return
     */
    public static GlideLoadConfig setConfigSize(String url, int width, int height) {

        if (AlimmdnUtil.findCharByKey(url) >= 0) {
            LogUtil.d("GlideUtils", "setConfigSize-->已被阿里百川解析不需要手动裁剪尺寸");
            return null;
        }
        GlideLoadConfig defConfig = new GlideLoadConfig.Builder().
                setCropType(GlideLoadConfig.CENTER_CROP).
//            setCropCircle(true).//设置裁剪，参考：cropCircle，roundedCorners，grayscale，blur,rotate
        setAsBitmap(true).
                        setThumbnail(0.5f).
                        setPlaceHolderResId(R.drawable.img_loading).
                        setErrorResId(R.drawable.img_loading).
                        setSize(new GlideLoadConfig.OverrideSize(width, height)).
                        setDiskCacheStrategy(GlideLoadConfig.DiskCache.RESULT).
                        setPrioriy(GlideLoadConfig.LoadPriority.HIGH).build();
        return defConfig;
    }

    public static void loadStringRes(ImageView view, String imageUrl) {
        load(view.getContext(), view, imageUrl, null, null);
    }

    /**
     * 加载String类型的资源
     * SD卡资源："file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"<p/>
     * assets资源："file:///android_asset/f003.gif"<p/>
     * raw资源："Android.resource://com.frank.glide/raw/raw_1"或"android.resource://com.frank.glide/raw/"+R.raw.raw_1<p/>
     * drawable资源："android.resource://com.frank.glide/drawable/news"或load"android.resource://com.frank.glide/drawable/"+R.drawable.news<p/>
     * ContentProvider资源："content://media/external/images/media/139469"<p/>
     * http资源："http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg"<p/>
     * https资源："https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp"<p/>
     * 注意：不适合等比例缩放
     *
     * @param view
     * @param imageUrl
     * @param config
     * @param listener 避免每次加载都使用新的监听器
     */
    public static void loadStringRes(ImageView view, String imageUrl, GlideLoadConfig config, BitmapLoadingListener listener) {
        load(view.getContext(), view, imageUrl, config, listener);
    }

    public static void loadFile(ImageView view, File file, GlideLoadConfig config, BitmapLoadingListener listener) {
        load(view.getContext(), view, file, config, listener);
    }

    public static void loadResId(ImageView view, Integer resourceId, GlideLoadConfig config, BitmapLoadingListener listener) {
        load(view.getContext(), view, resourceId, config, listener);
    }

    public static void loadUri(ImageView view, Uri uri, GlideLoadConfig config, BitmapLoadingListener listener) {
        load(view.getContext(), view, uri, config, listener);
    }

    public static void loadGif(ImageView view, String gifUrl, GlideLoadConfig config, BitmapLoadingListener listener) {
        load(view.getContext(), view, gifUrl, GlideLoadConfig.parseBuilder(config).setAsGif(true).build(), listener);
    }

    public static void loadTarget(Context context, Object objUrl, GlideLoadConfig config, final BitmapLoadingListener listener) {
        load(context, null, objUrl, config, listener);
    }

    /**
     * 根据不同的配置加载图片
     *
     * @param context
     * @param view
     * @param objUrl
     * @param config
     * @param listener
     */
    private static void load(Context context, ImageView view, Object objUrl, GlideLoadConfig config, final BitmapLoadingListener listener) {
        if (null == objUrl) {
//            throw new IllegalArgumentException("objUrl is null");
            objUrl = R.drawable.img_loading;
            LogUtil.myE("objUrl is null");
        }
        if (null == config) {
            config = defConfig;
        }
        try {
            GenericRequestBuilder builder = null;
            if (config.isAsGif()) {//gif类型
                GifRequestBuilder request = with(context).load(objUrl).asGif();
                if (config.getCropType() == GlideLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else {
                    request.fitCenter();
                }
                builder = request;
            } else if (config.isAsBitmap()) {  //bitmap 类型
                BitmapRequestBuilder request = with(context).load(objUrl).asBitmap();
                if (config.getCropType() == GlideLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else {
                    request.fitCenter();
                }

                //transform bitmap
                //图片转化框架：http://www.open-open.com/lib/view/open1456706974203.html
                if (config.isRoundedCorners()) {
                    request.transform(new RoundedCornersTransformation(context, 50, 50));
                } else if (config.isCropCircle()) {
                    request.transform(new CropCircleTransformation(context));
                } else if (config.isGrayscale()) {
                    request.transform(new GrayscaleTransformation(context));
                } else if (config.isBlur()) {
                    request.transform(new BlurTransformation(context, 8, 8));
                } else if (config.isRotate()) {
                    request.transform(new RotateTransformation(context, config.getRotateDegree()));
                }
                builder = request;
            } else if (config.isCrossFade()) { // 渐入渐出动画
                DrawableRequestBuilder request = with(context).load(objUrl).crossFade();
                if (config.getCropType() == GlideLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else {
                    request.fitCenter();
                }
                builder = request;
            } else {
                LogUtil.myE("未知的GenericRequestBuilder对象");
            }
            //缓存设置
            builder.diskCacheStrategy(config.getDiskCacheStrategy().getStrategy()).
                    skipMemoryCache(config.isSkipMemoryCache()).
                    priority(config.getPrioriy().getPriority());
            builder.dontAnimate();

            /*Glide的signature方法有效地添加了标识，通过简单的传入StringSignature即可实现判断图片信息是否为最新，从而加载最新的图片。
            在自行开发过程中，可以更好的处理旧图片问题，把标识信息和图片分开存放，如有图片更新，可删除旧图片，只缓存新图片。当然，主要看是否需要保存旧图片。*/
            if (null != config.getTag()) {
                builder.signature(new StringSignature(config.getTag()));
            } else {
                builder.signature(new StringSignature(objUrl.toString()));
            }
            if (null != config.getAnimator()) {
                builder.animate(config.getAnimator());
            } else if (null != config.getAnimResId()) {
                builder.animate(config.getAnimResId());
            }

            if (listener == null) {
                //设置缩略图
                if (config.getThumbnail() > 0.0f) {
                    builder.thumbnail(config.getThumbnail());
                }
            } else {
//                LogUtil.myW(" 监听器和缩略图不要同时使用，否则会导致监听器响应两次");
            }

            //设置错误图片
            if (null != config.getErrorResId()) {
                builder.error(config.getErrorResId());
            }
            //设置占位符
            if (null != config.getPlaceHolderResId()) {
                builder.placeholder(config.getPlaceHolderResId());
            }

            //设置尺寸
            if (null != config.getSize()) {
                LogUtil.d("GlideUtils", "width:" + config.getSize().getWidth() + "\theight:" + config.getSize().getHeight());
                builder.override(config.getSize().getWidth(), config.getSize().getHeight());
            }

            //设置监听器
            if (null != listener) {
                setListener(builder, listener);
            }

            if (null != config.getThumbnailUrl()) {
                BitmapRequestBuilder thumbnailRequest = with(context).load(config.getThumbnailUrl()).asBitmap();
                builder.thumbnail(thumbnailRequest).into(view);
            } else {
                setTargetView(builder, config, view);
            }
        } catch (Exception e) {
            view.setImageResource(config.getErrorResId());
        }
    }

    private static void setListener(GenericRequestBuilder request, final BitmapLoadingListener listener) {
        /*request.listener(new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                if (!e.getMessage().equals("divide by zero")) {
                    listener.onError();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                listener.onSuccess();
                return false;
            }
        });*/

        /*request.listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        });*/

        request.listener(new RequestListener<Object, Bitmap>() {
            @Override
            public boolean onException(Exception e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                if (null != listener) {
                    listener.onError();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (null != listener) {
                    listener.onSuccess(resource);
//                    LogUtil.myD("GlideUtils.load()监听器响应了");
                }
                return false;
            }
        });
    }

    /**
     * 设置目标视图：
     * http://mrfu.me/2016/02/27/Glide_Callbacks_SimpleTarget_and_ViewTarget_for_Custom_View_Classes/<br/>
     *
     * @param request
     * @param config
     * @param view
     */
    private static void setTargetView(GenericRequestBuilder request, GlideLoadConfig config, ImageView view) {
        //set targetView
        if (null != config.getSimpleTarget()) {
            request.into(config.getSimpleTarget());
        } else if (null != config.getViewTarget()) {
            request.into(config.getViewTarget());
        } else if (null != config.getNotificationTarget()) {
            request.into(config.getNotificationTarget());
        } else if (null != config.getAppWidgetTarget()) {
            request.into(config.getAppWidgetTarget());
        } else {
            request.into(view);
        }
    }

    /**
     * 加载bitmap
     *
     * @param context
     * @param url
     * @param listener
     */
    public static void loadBitmap(Context context, String url, final BitmapLoadingListener listener) {
        if (url == null) {
            if (listener != null) {
                listener.onError();
            }
        } else {

            BitmapRequestBuilder bitmapRequestBuilder = Glide.with(context).
                    load(url).
                    asBitmap().
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).
                    dontAnimate().
                    placeholder(R.drawable.img_loading).
                    error(R.drawable.img_loading).
                    override(720, 1280);

            if (listener == null) {
                bitmapRequestBuilder.thumbnail(0.2f);//thumbnail和监听器同时使用会导致监听器响应两次。
            }

            bitmapRequestBuilder.into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap bitmapResource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (listener != null) {
                        listener.onSuccess(bitmapResource);
//                                LogUtil.myD("GlideUtils.loadBitmap()加载图片成功了");
                    }
                }
            });
        }
    }

    /**
     * 加载详情页图片
     *
     * @param context
     * @param url
     * @param listener
     */
    public static void loadDetailImageBitmap(Context context, String url, final BitmapLoadingListener listener) {
        if (url == null) {
            if (listener != null) {
                listener.onError();
            }
        } else {
            with(context).
                    load(url).
                    asBitmap().
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).
                    dontAnimate().
                    placeholder(R.drawable.img_loading).
                    error(R.drawable.img_loading).
                    override(720, 1280).
                    into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmapResource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (listener != null) {
                                listener.onSuccess(bitmapResource);
                            }
                        }
                    });
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    public static void cancelAllTasks(Context context) {
        with(context).pauseRequests();
    }

    /**
     * 恢复所有任务
     */
    public static void resumeAllTasks(Context context) {
        with(context).resumeRequests();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public static void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }

    /**
     * 清除所有缓存
     *
     * @param context
     */
    public static void cleanAll(Context context) {
        clearDiskCache(context);
        Glide.get(context).clearMemory();
    }


    public static void clearTarget(View view) {
        Glide.clear(view);
    }


}