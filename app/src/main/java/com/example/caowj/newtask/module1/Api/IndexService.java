package com.example.caowj.newtask.module1.Api;


import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ChoiceArticleList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 网络请求接口
 * by y on 2016/5/17.
 */
public interface IndexService {

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


/*******************************************************************/

    /**
     * 获取首页轮播图
     * <p>
     * http://test.qipaiapp.com/QiPaiAPI/Paipininfo.asmx/GetAdList?token=1
     *
     * @param token
     * @return
     */
    @GET(Api.PAIPININFO_URL + "GetAdList")
    Observable<ADInfoList> GetAdList(@Query("token") String token);

    /**
     * 滚动广告
     *<p>
     *     http://test.qipaiapp.com/QiPaiAPI_2_6_6/SysNews.asmx/SystemNotificationList?token=QWASD874HAsSF8asJAYOgaIU3JG98hDSN2g3SD671g29385FSA811NASs
     *</p>
     * @param token
     * @return
     */
    @GET(Api.SYSNEWS_URL + "SystemNotificationList")
    Observable<ScrollNotificationList> GetNotificationList(@Query("token") String token);


    /**
     * 精选文章
     * <p>
     * http://test.qipaiapp.com/QiPaiAPI_2_6_6/Paipininfo.asmx?op=GetListWenZhang
     * </p>
     *
     * @param token
     * @return
     */
    @GET(Api.PAIPININFO_URL + "GetListWenZhang")
    Observable<ChoiceArticleList> GetListWenZhang(@Query("pageSize") int pageSize, @Query("pageIndex") int pageIndex, @Query("token") String token);


    /**
     * 往期文章
     * <p>
     * http://test.qipaiapp.com/QiPaiAPI_2_6_6/Paipininfo.asmx?op=GetListWangQiWenZhang
     * </p>
     *
     * @param pageSize
     * @param pageIndex
     * @param token
     * @return
     */
    @GET(Api.PAIPININFO_URL + "GetListWangQiWenZhang")
    Observable<ChoiceArticleList> GetListWangQiWenZhang(@Query("pageSize") int pageSize, @Query("pageIndex") int pageIndex, @Query("token") String token);
}
