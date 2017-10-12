package com.example.caowj.newtask.module1.Api;


import com.example.caowj.newtask.module1.bean.NewsInfo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 天行数据api
 * by y on 2016/5/17.
 */
public interface TianService {


    //    http://api.tianapi.com/wxnew/?key=145170256f0d5a708fef46f45977122b&num=10

    @GET("wxnew/")
    Call<ResponseBody> GetWxNews(@Query("num") int num, @Query("key") String key);


    @GET("wxnew/")
    Call<NewsInfo> GetWxNews1(@Query("num") int num, @Query("key") String key);


    @GET("wxnew/")
    Observable<NewsInfo> GetWxNews2(@Query("num") int num, @Query("key") String key);

}
