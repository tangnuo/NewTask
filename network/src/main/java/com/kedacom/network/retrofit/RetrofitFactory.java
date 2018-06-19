package com.kedacom.network.retrofit;

import com.kedacom.network.retrofit.config.HttpClient;
import com.kedacom.utils.Check;
import com.kedacom.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meiji on 2017/4/22.
 */

public class RetrofitFactory {

    private volatile static Retrofit mRetrofit;
    private Map<String,  Object> serviceByType = new HashMap<>();


    /**
     * 获取Retrofit实例
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit getRetrofit(String baseUrl) {
        synchronized (RetrofitFactory.class) {
            if (mRetrofit == null) {
                mRetrofit = newRetrofit(baseUrl);
            } else {
                String oldBaseUrl = mRetrofit.baseUrl().toString().toLowerCase();
                if (!baseUrl.toLowerCase().equals(oldBaseUrl)) {
                    mRetrofit = newRetrofit(baseUrl);
                } else {
                    LogUtil.myW("retrofit已经存在。oldBaseUrl：" + oldBaseUrl + "\nbaseUrl:" + baseUrl);
                }
            }
        }

        return mRetrofit;
    }


    /**
     * 实例化Retrofit
     *
     * @param baseUrl
     * @return
     */
    private static Retrofit newRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                            .client(builder.build())
                .client(HttpClient.getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory(QipaiGsonConverterFactory.create())//自定义转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }


    /**
     * 获取service实例
     * @param apiInterface
     * @param <T>
     * @return
     */
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

    /*************************功能扩展 begin**************************/

//    /**
//     * 缓存机制
//     * 在响应请求之后在 data/data/<包名>/cache 下建立一个response 文件夹，保持缓存数据。
//     * 这样我们就可以在请求的时候，如果判断到没有网络，自动读取缓存的数据。
//     * 同样这也可以实现，在我们没有网络的情况下，重新打开App可以浏览的之前显示过的内容。
//     * 也就是：判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取。
//     * https://werb.github.io/2016/07/29/%E4%BD%BF%E7%94%A8Retrofit2+OkHttp3%E5%AE%9E%E7%8E%B0%E7%BC%93%E5%AD%98%E5%A4%84%E7%90%86/
//     */
//    private Interceptor interceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetWorkUtil.isNetworkConnected(mContext)) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//            }
//
//            Response originalResponse = chain.proceed(request);
//            if (NetWorkUtil.isNetworkConnected(mContext)) {
//                // 有网络时 设置缓存为默认值
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                        .build();
//            } else {
//                // 无网络时 设置超时为1周
//                int maxStale = 60 * 60 * 24 * 7;
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    };

    /*************************功能扩展 end**************************/

}
