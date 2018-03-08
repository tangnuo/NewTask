package com.example.caowj.newtask.module1.Api;


import com.example.caowj.newtask.module1.entity.AdInfo;
import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;
import com.example.caowj.newtask.module1.entity.YawuInfo;
import com.example.caowj.newtask.module1.entity.bean.BaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 网络请求接口
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


//    http://test.qipaiapp.com/QiPaiAPI/PaipinInfo.asmx/YaWuZhuanTi

    @GET(Api.PAIPININFO_URL + "/YaWuZhuanTi")
    Call<YawuInfo> YaWuZhuanTi(@Query("type") int type, @Query("token") String token);


    /**
     * 获取分类（http://test.qipaiapp.com/QiPaiAPI/PaipinCate.asmx/GetAllList）
     *
     * @param position
     * @param token
     * @return
     */
    @GET(Api.PAIPINCATE_URL + "GetAllList")
    Call<NavigationInfo> GetAllList(@Query("position") String position, @Query("token") String token);

    /**
     * 雅物分类商品信息
     * http://test.qipaiapp.com/QiPaiAPI/Paipininfo.asmx/GetListYaWu?typeId=0&SceneType=0&pageSize=10&pageIndex=1&isDate=0&sprice=0&endPrice=0&sortType=0&token=1
     *
     * @param map
     * @param token
     * @return
     */
    @GET(Api.PAIPININFO_URL + "GetListYaWu")
    Call<PaiPinInfo2> GetListYaWu(@QueryMap Map<String, Integer> map, @Query("token") String token);
}
