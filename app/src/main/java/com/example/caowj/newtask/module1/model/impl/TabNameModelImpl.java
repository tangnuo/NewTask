package com.example.caowj.newtask.module1.model.impl;


import android.util.Log;

import com.example.caowj.newtask.module1.Api.Api;
import com.example.caowj.newtask.module1.Api.CollectionService;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.converter.QipaiGsonConverterFactory;
import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;
import com.example.caowj.newtask.module1.entity.YawuInfo;
import com.example.caowj.newtask.module1.model.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * by y on 2016/4/28.
 */
public class TabNameModelImpl implements BaseModel.TabNameModel {


    @Override
    public void netWork(final BaseDataBridge.TabNameData tabNameData) {

        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<NavigationInfo> call = apiService.GetAllList("4", WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<NavigationInfo>() {
            @Override
            public void onResponse(Call<NavigationInfo> call, Response<NavigationInfo> response) {
                if (response.isSuccessful()) {
                    NavigationInfo navigationInfo = response.body();
                    //获取json字符串
                    String result = navigationInfo.toString();
                    LogUtil.myD("code:" + navigationInfo.getCode() + ",dataList:" + navigationInfo.getDatalist());

                    tabNameData.showNavigationP(navigationInfo);
                }
            }

            @Override
            public void onFailure(Call<NavigationInfo> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
                tabNameData.error();
            }
        });

    }

/////////////////////////////////////////////////////////////////

    /**
     * 服务端返回null，失败
     */
    void test5() {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<YawuInfo> call = apiService.YaWuZhuanTi(0, WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<YawuInfo>() {
            @Override
            public void onResponse(Call<YawuInfo> call, Response<YawuInfo> response) {
                if (response.isSuccessful()) {
                    YawuInfo body = response.body();
                    //获取json字符串
                    String result = body.toString();
                    LogUtil.myD("code:" + body.getCode() + ",dataList:" + body.getDatalist());
                }
            }

            @Override
            public void onFailure(Call<YawuInfo> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
            }
        });
    }


    @Override
    public void getDataByTypeM(int typeId, int pageSize, int pageIndex, final BaseDataBridge.TabNameData tabNameData) {
        Retrofit Retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API_QIPAI)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(QipaiGsonConverterFactory.create())
                .build();

        //通过Retrofit实例，创建服务接口对象
        CollectionService apiService = Retrofit.create(CollectionService.class);
        //通过接口服务对象，调用接口中的方法，获取call对象
        Map<String, Integer> properties = new HashMap<>();
        properties.put("typeID", typeId);
        properties.put("SceneType", 0);
        properties.put("pageSize", pageSize);
        properties.put("pageIndex", pageIndex);
        properties.put("IsDate", 0);
        properties.put("sPrice", 0);
        properties.put("endPrice", 0);
        properties.put("sortType", 0);
        Call<PaiPinInfo2> call = apiService.GetListYaWu(properties, WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<PaiPinInfo2>() {
            @Override
            public void onResponse(Call<PaiPinInfo2> call, Response<PaiPinInfo2> response) {
                if (response.isSuccessful()) {
                    PaiPinInfo2 navigationInfo = response.body();
                    //获取json字符串
                    String result = navigationInfo.toString();
                    LogUtil.myD("code:" + navigationInfo.getCode() + ",dataList:" + navigationInfo.getDatalist());

                    tabNameData.showPaiPinInfoP(navigationInfo);
                }
            }

            @Override
            public void onFailure(Call<PaiPinInfo2> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
                tabNameData.error();
            }
        });
    }
}
