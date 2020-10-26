package com.kedacom.module_learn.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.caowj.lib_network.HttpServicesFactory2
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_common.utils.ToastUtil
import com.kedacom.module_learn.R
import com.kedacom.module_learn.api.ApiService
import com.kedacom.module_learn.bean.UploadInfo
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Retrofit2上传图片
 */
class TestUploadActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_upload)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { v: View? -> uploadImage() }
    }

    /**
     * 图片上传（注意Android6.0 动态权限）
     *
     *
     * https://www.zhaoapi.cn/file/upload?uid=10134
     */
    fun uploadImage() {
        try {
            val BASE_URL = "https://www.zhaoapi.cn/file/"
            val file = File("/storage/emulated/0/zhcx/img/cgzf/33000033_0.jpg")
            println("file路径 = " + file.absolutePath)
            if (file.exists()) {
                //动态代理生成实现类
                val interfaceApi = HttpServicesFactory2.getInstance().createService(ApiService::class.java)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val filePart = MultipartBody.Part.createFormData("file", file.name, requestBody)
                val bodyCall = interfaceApi.uploadImage(filePart)
                bodyCall.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.d("caowj", "上传成功" + response.body())
                        ToastUtil.showShortToast(mActivity, "上传成功")
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        ToastUtil.showShortToast(mActivity, "上传失败")
                        Log.d("caowj", "上传失败" + t.fillInStackTrace())
                    }
                })
            } else {
                Log.d("caowj", "文件不存在：" + file.name)
            }
        } catch (e: Exception) {
            Log.d("caowj", e.message)
        }
    }

    /**
     * 白城智查通图片上传
     *
     *
     * http://122.138.250.132:9900/file/upload/teupload/ydzf/33/33000034
     */
    private fun upload2() {
        try {
            val BASE_URL = "http://122.138.250.132:9900/file/upload/teupload/"
            val file = File("/storage/emulated/0/zhcx/img/cgzf/33000033_0.jpg")
            println("file路径 = " + file.absolutePath)
            if (file != null) {

////                方法一：常规写法
//                Retrofit retrofit = RetrofitFactory.getRetrofit(BASE_URL);
//                //动态代理生成实现类
//                ApiService interfaceApi = retrofit.create(ApiService.class);


                //方法二：使用stetho
                val client = OkHttpClient.Builder() //                .addInterceptor(cacheInterceptor()) //缓存拦截器
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build()
                val interfaceApi = retrofit.create(ApiService::class.java)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val filePart = MultipartBody.Part.createFormData("file", file.name, requestBody)
                val bodyCall = interfaceApi.upload(filePart)
                bodyCall.enqueue(object : Callback<UploadInfo> {
                    override fun onResponse(call: Call<UploadInfo>, response: Response<UploadInfo>) {
                        Log.d("caowj", "上传成功" + response.body().toString())
                        ToastUtil.showShortToast(mActivity, "上传成功")
                    }

                    override fun onFailure(call: Call<UploadInfo>, t: Throwable) {
                        ToastUtil.showShortToast(mActivity, "上传失败")
                        Log.d("caowj", "上传失败" + t.fillInStackTrace())
                    }
                })
            } else {
                Log.d("caowj", "文件不存在：" + file.name)
            }
        } catch (e: Exception) {
            Log.d("caowj", e.message)
        }
    }

    /**
     * 自定义服务端
     *
     *
     * http://10.79.0.32:8080/UploadFileServer/servlet/UploadHandleServlet
     *
     *
     * 注意：需要自定义ConverterFactory，否则gson异常。
     */
    private fun upload3() {
        try {
            val BASE_URL = "http://10.79.0.32:8080/UploadFileServer/"
            val file = File("/storage/emulated/0/zhcx/img/cgzf/33000033_1.jpg")
            println("file路径 = " + file.absolutePath)
            if (file != null) {

                //方法二：使用stetho
                val client = OkHttpClient.Builder() //                .addInterceptor(cacheInterceptor()) //缓存拦截器
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()
                val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create()) //注意，这里需要特殊处理
                        .build()
                val interfaceApi = retrofit.create(ApiService::class.java)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val filePart = MultipartBody.Part.createFormData("file", file.name, requestBody)
                val bodyCall = interfaceApi.upload3(filePart)
                bodyCall.enqueue(object : Callback<UploadInfo> {
                    override fun onResponse(call: Call<UploadInfo>, response: Response<UploadInfo>) {
                        Log.d("caowj", "上传成功" + response.body().toString())
                        ToastUtil.showShortToast(mActivity, "上传成功")
                    }

                    override fun onFailure(call: Call<UploadInfo>, t: Throwable) {
                        ToastUtil.showShortToast(mActivity, "上传失败，可能gson异常")
                        Log.d("caowj", "上传失败" + t.fillInStackTrace())
                    }
                })
            } else {
                Log.d("caowj", "文件不存在：" + file.name)
            }
        } catch (e: Exception) {
            Log.d("caowj", e.message)
        }
    }
}