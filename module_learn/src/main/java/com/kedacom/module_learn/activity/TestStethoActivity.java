package com.kedacom.module_learn.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kedacom.module_learn.R;
import com.kedacom.module_learn.api.ApiService;
import com.kedacom.module_lib.base.common.BaseActivity;
import com.kedacom.module_lib.utils.FileHelper;
import com.kedacom.module_lib.utils.LogUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 学习使用Stetho
 */
public class TestStethoActivity extends BaseActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_stetho);

        textView = findViewById(R.id.textView);
        textView.setText("测试Stetho");

        testStetho();
    }

    /**
     * https://gank.io/api/day/history
     */
    private void testStetho() {


        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(cacheInterceptor()) //缓存拦截器
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache()).build();

        String BASE_URL = "https://gank.io/api/";
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();

        ApiService service = mRetrofit.create(ApiService.class);
//        Call<ResponseBody> call = service.getBlog();
        Call<ResponseBody> call = service.getFuliByPage(1);


        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtil.myD("请求结果：" + response.body().string());
                    textView.setText("请求成功，结果见日志。");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                textView.setText(t.getLocalizedMessage());
            }
        });


    }


    private Cache cache() {
        final File cacheDir = FileHelper.buildFile("HttpResponseCache");
        return (new Cache(cacheDir, 10 * 1024 * 1024));

    }
}
