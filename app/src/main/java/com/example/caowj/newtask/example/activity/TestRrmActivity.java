package com.example.caowj.newtask.example.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.module1.Api.Api;
import com.example.caowj.newtask.module1.Api.TianService;
import com.example.caowj.newtask.module1.bean.NewsBean2;
import com.example.caowj.newtask.module1.bean.NewsInfo;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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


/**
 * 根据天行数据，访问微信精选文章（成功）
 * <p/>
 * <p>
 * http://api.tianapi.com/wxnew/?key=145170256f0d5a708fef46f45977122b&num=10
 */
public class TestRrmActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_rrm;
    }

    @Override
    protected void memoryRecovery() {

    }


    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                test1();
                break;
            case R.id.button2:
                test2();
                break;
            case R.id.button3:
                test3();
                break;
        }
    }

    /**
     * Retrofit2+RxJava2访问api，返回json
     */
    void test3() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_TX)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        TianService apiService = Retrofit.create(TianService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Observable<NewsInfo> call = apiService.GetWxNews2(10, WSConstants.TIAN_XING_API_KEY);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(@NonNull NewsInfo newsInfo) {
                        LogUtil.myD("onNext..." + newsInfo.getCode());
                        MyAndroidUtils.showShortToast(mActivity, "code:" + newsInfo.getCode());

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
     * Retrofit2访问api，返回json
     */
    void test2() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_TX)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        TianService apiService = Retrofit.create(TianService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<NewsInfo> call = apiService.GetWxNews1(10, WSConstants.TIAN_XING_API_KEY);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<NewsInfo>() {
            @Override
            public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
                if (response.isSuccessful()) {
                    NewsInfo body = response.body();
                    //获取json字符串
                    List<NewsBean2> newsBeanList = body.getNewslist();

                    LogUtil.myD("code:" + body.getCode() + ",,," + newsBeanList.get(0).getTitle());
                    MyAndroidUtils.showShortToast(mActivity, "title:" + newsBeanList.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<NewsInfo> call, Throwable t) {
                Log.i(mTag, "请求失败:" + t.getMessage());
            }
        });
    }


    /**
     * Retrofit2访问api，返回ResponseBody
     */
    void test1() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_TX)
                .build();

        //通过Retrofit实例，创建服务接口对象
        TianService apiService = Retrofit.create(TianService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<ResponseBody> call = apiService.GetWxNews(10, WSConstants.TIAN_XING_API_KEY);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();

                    LogUtil.myD("code:" + body.toString());
                    MyAndroidUtils.showShortToast(mActivity, "body:" + body.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(mTag, "请求失败:" + t.getMessage());
            }
        });
    }


    ////////////////////////////////////////////////

    /**
     * 基础实例
     */
    void Test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hahaha");
                e.onNext("hahaha");
                e.onNext("hahaha");
                Log.e(mTag, "运行在什么线程" + Thread.currentThread().getName());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())               //线程调度器,将发送者运行在子线程
                .observeOn(AndroidSchedulers.mainThread())          //接受者运行在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(mTag, "onSubscribe: ");
                        Log.e(mTag, "接收在什么线程" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(mTag, "onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(mTag, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(mTag, "onComplete: ");
                    }
                });


        Flowable.range(0, 10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription sub;

                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.w("mTag", "onsubscribe start");
                        sub = s;
                        sub.request(1);
                        Log.w("mTag", "onsubscribe end");
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.w("mTag", "onNext--->" + o);
                        sub.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.w("mTag", "onComplete");
                    }
                });
    }

}
