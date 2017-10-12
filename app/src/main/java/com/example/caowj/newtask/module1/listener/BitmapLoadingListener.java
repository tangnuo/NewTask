package com.example.caowj.newtask.module1.listener;

import android.graphics.Bitmap;

/**
 * bitmap加载监听器
 * package: com.jsfengling.qipai.tools.Glide
 * author: caowj
 * date: 2016/7/7 13:49
 */
public interface BitmapLoadingListener {
    void onSuccess(Bitmap mBitmap);

    void onError();
}
