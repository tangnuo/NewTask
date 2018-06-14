package com.example.caowj.newtask.mvvm.network;

import com.kedacom.network.retrofit.RetrofitFactory;

import retrofit2.Retrofit;

/**
 * Copyright ©2017 by ruzhan
 */

public final class AwakerClient {

    private static final String HOST = "http://www.awaker.cn/api/";

    private static AwakerApi api;

    private AwakerClient() {
    }

    public static AwakerApi get() {
        if (api == null) {
            synchronized (AwakerClient.class) {
                if (api == null) {
                    Retrofit client = RetrofitFactory.getRetrofit(HOST);
                    api = client.create(AwakerApi.class);
                }
            }
        }
        return api;
    }
}
