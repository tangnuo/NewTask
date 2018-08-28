package com.kedacom.module_learn.api;

import com.kedacom.module_learn.bean.UploadInfo;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/27 15:26
 */
public interface ApiService {

    @Multipart
    @POST("ydzf/33/33000034")
    Call<UploadInfo> upload(@Part MultipartBody.Part file);

    @Multipart
    @POST("upload?uid=10134")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);
}

