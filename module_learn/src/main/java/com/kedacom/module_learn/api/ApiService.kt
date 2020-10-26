package com.kedacom.module_learn.api

import com.kedacom.module_learn.bean.UploadInfo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/27 15:26
 */
interface ApiService {
    @Multipart
    @POST("ydzf/33/33000034")
    fun upload(@Part file: MultipartBody.Part?): Call<UploadInfo>

    @Multipart
    @POST("servlet/UploadHandleServlet")
    fun upload3(@Part file: MultipartBody.Part?): Call<UploadInfo>

    @Multipart
    @POST("upload?uid=10134")
    fun uploadImage(@Part file: MultipartBody.Part?): Call<ResponseBody>

    // https://gank.io/api/day/history
    @get:GET("day/history")
    val blog: Call<ResponseBody>

    //    https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/3
    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    fun getFuliByPage(@Path("page") page: Int): Call<ResponseBody>
}