package com.kedacom.module_learn.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.caowj.lib_network.HttpServicesFactory2;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kedacom.module_common.utils.ToastUtil;
import com.kedacom.module_learn.R;
import com.kedacom.module_learn.api.ApiService;
import com.kedacom.module_learn.bean.UploadInfo;
import com.kedacom.module_common.common.BaseActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit2上传图片
 */
public class TestUploadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_upload);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
//                upload2();
//                upload3();
            }
        });

    }

    /**
     * 图片上传（注意Android6.0 动态权限）
     * <p>
     * https://www.zhaoapi.cn/file/upload?uid=10134
     */
    public void uploadImage() {
        try {
            String BASE_URL = "https://www.zhaoapi.cn/file/";


            File file = new File("/storage/emulated/0/zhcx/img/cgzf/33000033_0.jpg");

            System.out.println("file路径 = " + file.getAbsolutePath());
            if (file != null) {
                //动态代理生成实现类
                ApiService interfaceApi = HttpServicesFactory2.getInstance().createService(ApiService.class);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                Call<ResponseBody> bodyCall = interfaceApi.uploadImage(filePart);
                bodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("caowj","上传成功" + response.body());
                        ToastUtil.showShortToast(mActivity, "上传成功");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ToastUtil.showShortToast(mActivity, "上传失败");
                        Log.d("caowj","上传失败" + t.fillInStackTrace());
                    }
                });

            } else {
                Log.d("caowj", "文件不存在：" + file.getName());
            }
        } catch (Exception e) {
            Log.d("caowj", e.getMessage());
        }
    }


    /**
     * 白城智查通图片上传
     * <p>
     * http://122.138.250.132:9900/file/upload/teupload/ydzf/33/33000034
     */
    private void upload2() {
        try {
            String BASE_URL = "http://122.138.250.132:9900/file/upload/teupload/";

            File file = new File("/storage/emulated/0/zhcx/img/cgzf/33000033_0.jpg");

            System.out.println("file路径 = " + file.getAbsolutePath());
            if (file != null) {

////                方法一：常规写法
//                Retrofit retrofit = RetrofitFactory.getRetrofit(BASE_URL);
//                //动态代理生成实现类
//                ApiService interfaceApi = retrofit.create(ApiService.class);


                //方法二：使用stetho
                OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(cacheInterceptor()) //缓存拦截器
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();

                ApiService interfaceApi = retrofit.create(ApiService.class);


                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                Call<UploadInfo> bodyCall = interfaceApi.upload(filePart);
                bodyCall.enqueue(new Callback<UploadInfo>() {
                    @Override
                    public void onResponse(Call<UploadInfo> call, Response<UploadInfo> response) {
                        Log.d("caowj","上传成功" + response.body().toString());
                        ToastUtil.showShortToast(mActivity, "上传成功");
                    }

                    @Override
                    public void onFailure(Call<UploadInfo> call, Throwable t) {
                        ToastUtil.showShortToast(mActivity, "上传失败");
                        Log.d("caowj","上传失败" + t.fillInStackTrace());
                    }
                });

            } else {
                Log.d("caowj", "文件不存在：" + file.getName());
            }
        } catch (Exception e) {
            Log.d("caowj", e.getMessage());
        }
    }

    /**
     * 自定义服务端
     * <p>
     * http://10.79.0.32:8080/UploadFileServer/servlet/UploadHandleServlet
     * <p>
     * 注意：需要自定义ConverterFactory，否则gson异常。
     */
    private void upload3() {
        try {
            String BASE_URL = "http://10.79.0.32:8080/UploadFileServer/";

            File file = new File("/storage/emulated/0/zhcx/img/cgzf/33000033_1.jpg");

            System.out.println("file路径 = " + file.getAbsolutePath());
            if (file != null) {

                //方法二：使用stetho
                OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(cacheInterceptor()) //缓存拦截器
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())//注意，这里需要特殊处理
                        .build();

                ApiService interfaceApi = retrofit.create(ApiService.class);


                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                Call<UploadInfo> bodyCall = interfaceApi.upload3(filePart);
                bodyCall.enqueue(new Callback<UploadInfo>() {
                    @Override
                    public void onResponse(Call<UploadInfo> call, Response<UploadInfo> response) {
                        Log.d("caowj","上传成功" + response.body().toString());
                        ToastUtil.showShortToast(mActivity, "上传成功");
                    }

                    @Override
                    public void onFailure(Call<UploadInfo> call, Throwable t) {
                        ToastUtil.showShortToast(mActivity, "上传失败，可能gson异常");
                        Log.d("caowj","上传失败" + t.fillInStackTrace());
                    }
                });

            } else {
                Log.d("caowj", "文件不存在：" + file.getName());
            }
        } catch (Exception e) {
            Log.d("caowj", e.getMessage());
        }
    }


}

