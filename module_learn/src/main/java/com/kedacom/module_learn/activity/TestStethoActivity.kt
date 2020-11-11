package com.kedacom.module_learn.activity

import android.os.Bundle
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_learn.R
import com.kedacom.module_learn.api.ApiService
import kotlinx.android.synthetic.main.activity_test_stetho.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * 学习使用Stetho
 */
class TestStethoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_stetho)
        textView.text = "测试Stetho"
        testStetho()
    }

    /**
     * https://gank.io/api/day/history
     */
    private fun testStetho() {
        val client = OkHttpClient.Builder() //                .addInterceptor(cacheInterceptor()) //缓存拦截器
                .addNetworkInterceptor(StethoInterceptor()) //                .cache(cache())
                .build()
        val BASE_URL = "https://gank.io/api/"
        val mRetrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build()
        val service = mRetrofit.create(ApiService::class.java)
        //        Call<ResponseBody> call = service.getBlog();
        val call = service.getFuliByPage(1)


        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    Log.d("caowj", "请求结果：" + response.body()!!.string())
                    textView!!.text = "请求成功，结果见日志。"
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                textView!!.text = t.localizedMessage
            }
        })
    } //    private Cache cache() {
    //        final File cacheDir = FileHelper.buildFile("HttpResponseCache");
    //        return (new Cache(cacheDir, 10 * 1024 * 1024));
    //    }
}