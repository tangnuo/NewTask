package com.example.caowj.newtask.mvp.Api;


import com.kedacom.network.retrofit.RetrofitFactory;
import com.kedacom.utils.LogUtil;

/**
 * 参考：https://github.com/7449/Retrofit_RxJava_MVP/blob/master/app/src/main/java/com/example/y/mvp/network/Network.java
 * by y on 2016/4/28.
 */
public class Network {

    private static CollectionService tngouApi;
    private static TianService tianService;
    private static IndexService indexService;


    public static CollectionService getCollectionService() {
        if (tngouApi == null) {
            tngouApi = RetrofitFactory.getRetrofit(Api.BASE_API_QIPAI).create(CollectionService.class);
        } else {
            LogUtil.myD("CollectionService 已经存在，可能有问题。。。");
        }

        return tngouApi;
    }

    public static TianService getTianService() {
        if (tianService == null) {
            tianService = RetrofitFactory.getRetrofit(Api.BASE_API_TX).create(TianService.class);
        } else {
            LogUtil.myD("TianService 已经存在，可能有问题。。。");
        }

        return tianService;
    }

    public static IndexService getIndexService() {
        if (indexService == null) {
            indexService = RetrofitFactory.getRetrofit(Api.BASE_API_QIPAI).create(IndexService.class);
        } else {
            LogUtil.myD("IndexService 已经存在，可能有问题。。。");
        }

        return indexService;
    }
}
