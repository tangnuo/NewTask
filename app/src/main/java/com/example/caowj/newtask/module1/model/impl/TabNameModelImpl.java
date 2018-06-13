package com.example.caowj.newtask.module1.model.impl;


import android.util.Log;

import com.example.caowj.newtask.module1.Api.Network;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfoList;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.kedacom.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * 使用Retrofit2获取服务端数据。
 *
 * by y on 2016/4/28.
 */
public class TabNameModelImpl extends BaseModelImpl<BaseDataBridge.TabNameDataBridge> implements BaseModel.TabNameModel {


    public TabNameModelImpl(BaseDataBridge.TabNameDataBridge modelImpl) {
        super(modelImpl);
    }


    @Override
    public void netWork() {

        //通过接口服务对象，调用接口中的方法，获取call对象
        Call<NavigationInfo> call = Network.getCollectionService().GetAllList("4", WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<NavigationInfo>() {
            @Override
            public void onResponse(Call<NavigationInfo> call, Response<NavigationInfo> response) {
                if (response.isSuccessful()) {
                    NavigationInfo navigationInfo = response.body();
                    //获取json字符串
                    String result = navigationInfo.toString();
                    LogUtil.myD("code:" + navigationInfo.getCode() + ",dataList:" + navigationInfo.getDatalist());

                    modelImpl.showNavigationP(navigationInfo);
                }
            }

            @Override
            public void onFailure(Call<NavigationInfo> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
                modelImpl.error();
            }
        });

    }

    @Override
    public void getDataByTypeM(int typeId, int pageSize, int pageIndex) {

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
        Call<PaiPinInfoList> call = Network.getCollectionService().GetListYaWu(properties, WSConstants.WEB_SERVER_TOKEN);
        //通过call对象执行网络请求(同步请求execute，异步请求enqueue)
        call.enqueue(new Callback<PaiPinInfoList>() {
            @Override
            public void onResponse(Call<PaiPinInfoList> call, Response<PaiPinInfoList> response) {
                if (response.isSuccessful()) {
                    PaiPinInfoList navigationInfo = response.body();
                    //获取json字符串
                    String result = navigationInfo.toString();
                    LogUtil.myD("code:" + navigationInfo.getCode() + ",dataList:" + navigationInfo.getDatalist());

                    modelImpl.showPaiPinInfoP(navigationInfo);
                }
            }

            @Override
            public void onFailure(Call<PaiPinInfoList> call, Throwable t) {
                Log.i(TAG, "请求失败:" + t.getMessage());
                modelImpl.error();
            }
        });
    }
}
