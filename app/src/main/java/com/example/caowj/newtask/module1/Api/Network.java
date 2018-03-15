package com.example.caowj.newtask.module1.Api;


import com.example.caowj.newtask.module1.converter.QipaiGsonConverterFactory;
import com.example.caowj.newtask.utils.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
            tngouApi = getRetrofit(Api.BASE_API_QIPAI).create(CollectionService.class);
        }
        return tngouApi;
    }

    public static TianService getTianService() {
        if (tianService == null) {
            tianService = getRetrofit(Api.BASE_API_TX).create(TianService.class);
        }
        return tianService;
    }

    public static IndexService getIndexService() {
        if (indexService == null) {
            indexService = getRetrofit(Api.BASE_API_QIPAI).create(IndexService.class);
        }
        return indexService;
    }

    /******************************************************************/

    /**
     * 获取Retrofit实例
     *
     * @param baseUrl
     * @return
     */
    private static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build())
                .baseUrl(baseUrl)
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RxJava2支持
                //.addConverterFactory(ScalarsConverterFactory.create())
                //.addConverterFactory(new StringConverterFactory())
                //.addConverterFactory(new JsonConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory()
                //.addConverterFactory(new FileRequestBodyConverterFactory())
                .build();
    }

    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtil.myD("request:" + request.toString());
            okhttp3.Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtil.myD("response body:" + content);
            if (response.body() != null) {
                ResponseBody body = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }
    }
}
