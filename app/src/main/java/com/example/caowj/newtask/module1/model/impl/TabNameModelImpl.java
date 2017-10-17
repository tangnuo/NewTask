package com.example.caowj.newtask.module1.model.impl;


import android.util.Log;

import com.example.caowj.newtask.module1.Api.Api;
import com.example.caowj.newtask.module1.Api.CollectionService;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.converter.QipaiGsonConverterFactory;
import com.example.caowj.newtask.module1.entity.AdInfo;
import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;
import com.example.caowj.newtask.module1.entity.YawuInfo;
import com.example.caowj.newtask.module1.entity.bean.BaseBean;
import com.example.caowj.newtask.module1.model.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * by y on 2016/4/28.
 */
public class TabNameModelImpl implements BaseModel.TabNameModel {


    @Override
    public void netWork(final BaseDataBridge.TabNameData tabNameData) {

        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<NavigationInfo> call = apiService.GetAllList("4", WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<NavigationInfo>() {
            @Override
            public void onResponse(Call<NavigationInfo> call, Response<NavigationInfo> response) {
                if (response.isSuccessful()) {
                    NavigationInfo navigationInfo = response.body();
                    //获取json字符串
                    String result = navigationInfo.toString();
                    LogUtil.myD("code:" + navigationInfo.getCode() + ",dataList:" + navigationInfo.getDatalist());

                    tabNameData.addNavigation(navigationInfo);
                }
            }

            @Override
            public void onFailure(Call<NavigationInfo> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
            }
        });

    }

/////////////////////////////////////////////////////////////////

    /**
     * 服务端返回null，失败
     */
    void test5() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<YawuInfo> call = apiService.YaWuZhuanTi(0, WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<YawuInfo>() {
            @Override
            public void onResponse(Call<YawuInfo> call, Response<YawuInfo> response) {
                if (response.isSuccessful()) {
                    YawuInfo body = response.body();
                    //获取json字符串
                    String result = body.toString();
                    LogUtil.myD("code:" + body.getCode() + ",dataList:" + body.getDatalist());
                }
            }

            @Override
            public void onFailure(Call<YawuInfo> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
            }
        });
    }


    /**
     * 单纯使用Retr2访问数据，并将结果转换成json。（返回值不是json，需要处理）
     */
    void test4() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<PaiPinInfo2> call = apiService.GetPaiPiID(12, WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<PaiPinInfo2>() {
            @Override
            public void onResponse(Call<PaiPinInfo2> call, Response<PaiPinInfo2> response) {
                if (response.isSuccessful()) {
                    PaiPinInfo2 body = response.body();
                    //获取json字符串
                    String result = body.toString();
                    LogUtil.myD("code:" + result);
                }
            }

            @Override
            public void onFailure(Call<PaiPinInfo2> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
            }
        });
    }


    /**
     * 单纯使用Retr2访问数据（能成功）
     */
    void test3() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<ResponseBody> call = apiService.GetAdList3(WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    //获取json字符串
                    try {
                        String result = body.string();
                        LogUtil.myD("sss:" + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
            }
        });
    }


    /**
     * Observable对应Observer
     */
    void test1(final BaseDataBridge.TabNameData tabNameData) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        CollectionService service = retrofit.create(CollectionService.class);

        service.GetAdList(WSConstants.WEB_SERVER_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AdInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(@NonNull AdInfo adInfo) {
                        LogUtil.myD("s:" + adInfo.getCode());
//                        tabNameData.addData();
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.myD("onError..." + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.myD("onComplete...");
                    }
                });
    }


    /**
     * Flowable对应Subscriber
     */
    void test2() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:4567/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        CollectionService service = retrofit.create(CollectionService.class);

        service.getNewsList(1, 1)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<BaseBean.TabNameBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(BaseBean.TabNameBean tabNameBean) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void test() {
//        NetWorkRequest.tabName(new Observer<BaseBean.TabNameBean>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull BaseBean.TabNameBean tabNameBean) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
}
