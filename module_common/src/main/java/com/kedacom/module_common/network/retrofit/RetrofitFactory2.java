package com.kedacom.module_common.network.retrofit;


import com.kedacom.module_common.utils.Check;
import com.kedacom.module_common.utils.FileHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 适合BaseUrl固定的场合<br/>
 * 使用缓存拦截器时，需要传入全局Context。
 * Created by SilenceDut on 16/10/28.
 */

public class RetrofitFactory2 {

    private static final String BASE_URL = "https://free-api.heweather.com/s6/";
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private static final int MAX_AGE = 60 * 10; //with network 10min
    private static final int MAX_STALE = 60 * 60 * 24; //1 day ,no network
    private volatile static RetrofitFactory2 sAppHttpClient;
    private Map<String, Object> serviceByType = new HashMap<>();
    private Retrofit mRetrofit;

    private RetrofitFactory2() {

        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(cacheInterceptor()) //缓存拦截器
                .cache(cache()).build();

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static RetrofitFactory2 getInstance() {
        if (sAppHttpClient == null) {
            synchronized (RetrofitFactory2.class) {
                if (sAppHttpClient == null) {
                    sAppHttpClient = new RetrofitFactory2();
                }
            }
        }
        return sAppHttpClient;
    }

//    /**
//     * 缓存拦截器（需要全局Context）
//     *
//     * @return
//     */
//    private Interceptor cacheInterceptor() {
//        return new Interceptor() {
//            @Override
//            public Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
//
//                Request request = chain.request();
//
//                //需要传入Context
//                if (!NetWorkUtil.isNetworkConnected(CoreManager.getContext())) {
//                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                }
//                Response originalResponse = chain.proceed(request);
//
//                if (NetWorkUtil.isNetworkConnected(CoreManager.getContext())) {
//                    return originalResponse.newBuilder().header("Cache-Control", "public ,max-age=" + MAX_AGE).build();
//                } else {
//                    return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_STALE).build();
//                }
//            }
//        };
//    }

    private Cache cache() {
        final File cacheDir = FileHelper.buildFile("HttpResponseCache");
        return (new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));

    }

    public synchronized <T> T getService(Class<T> apiInterface) {
        String serviceName = apiInterface.getName();
        if (Check.isNull(serviceByType.get(serviceName))) {
            T service = mRetrofit.create(apiInterface);
            serviceByType.put(serviceName, service);
            return service;
        } else {
            return (T) serviceByType.get(serviceName);
        }
    }

}
