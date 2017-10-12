package com.example.caowj.newtask.utils;

import io.reactivex.disposables.Disposable;

/**
 * package: com.example.y.mvp.utils
 * author: caowj
 * date: 2017/2/15 17:26
 */
public class RxUtils {

    public static Disposable subscription;

    public static void unsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}
