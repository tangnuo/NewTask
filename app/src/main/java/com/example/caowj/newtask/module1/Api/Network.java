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
 * by y on 2016/4/28.
 */
public class Network {


    private static CollectionService tngouApi;

    public static CollectionService getCollectionService() {
        if (tngouApi == null) {
            tngouApi = getRetrofit(Api.BASE_API_QIPAI).create(CollectionService.class);
        }
        return tngouApi;
    }


    private static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build())
                .baseUrl(baseUrl)
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
