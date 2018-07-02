package com.kedacom.network.retrofit.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 自定义OkHttpClient信息
 * Copyright ©2017 by ruzhan
 */

public class HttpClient {

    private static HttpClient httpClient;

    private OkHttpClient okHttpClient;

    private HttpClient() {

//                    // 指定缓存路径,缓存大小 50Mb
//                    Cache cache = new Cache(new File(mContext.getCacheDir(), "HttpCache"),
//                            1024 * 1024 * 50);
//
//                    // Cookie 持久化
//                    ClearableCookieJar cookieJar =
//                            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mContext));

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                            .cookieJar(cookieJar)//cookie
//                            .cache(cache)//缓存
//                            .addInterceptor(cacheControlInterceptor)//应用拦截器（缓存）
                .addInterceptor(new LogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

//        // Log 拦截器
//                    if (BuildConfig.DEBUG) {
//                        builder = initInterceptor(builder);
//                    }


        okHttpClient = builder.build();
    }

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                }
            }
        }
        return httpClient;
    }

    public static OkHttpClient getHttpClient() {
        return getInstance().okHttpClient;
    }


}
