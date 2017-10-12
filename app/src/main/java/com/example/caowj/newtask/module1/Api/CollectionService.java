package com.example.caowj.newtask.module1.Api;


import com.example.caowj.newtask.module1.bean.AdInfo;
import com.example.caowj.newtask.module1.bean.BaseBean;
import com.example.caowj.newtask.module1.bean.PaiPinInfo2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * by y on 2016/5/17.
 */
public interface CollectionService {


    @GET(Api.TAB_NAME)
    Observable<BaseBean.TabNameBean> getTabName();

    @GET(Api.NEWS_LIST)
    Flowable<BaseBean.TabNameBean> getNewsList(@Query("id") int id, @Query("page") int page);

    @GET(Api.PAIPININFO_URL + "GetAdList")
    Observable<AdInfo> GetAdList(@Query("token") String token);


    @GET(Api.PAIPININFO_URL + "GetAdList")
    Call<ResponseBody> GetAdList3(@Query("token") String token);


    @GET(Api.PAIPININFO_URL + "GetAdList")
    Call<AdInfo> GetAdList4(@Query("token") String token);

    //////////////////////////////////////////////////

    @GET(Api.PAIPININFO_URL + "/GetPaiPiID")
    Call<PaiPinInfo2> GetPaiPiID(@Query("paipinID") int paipinID, @Query("token") String token);


    /////////////////////////////////////////////////


//
//   @GET(Api.NEWS_LIST)
//    Observable<BaseBean.NewsListBean> getNewsList(@Query("id") int id, @Query("page") int page);

}
