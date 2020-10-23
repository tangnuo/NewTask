package com.kedacom.module_main.mvp.model.impl;

import android.os.Handler;

import com.caowj.lib_network.HttpServicesFactory2;
import com.kedacom.module_main.mvp.listener.HttpCallback;
import com.kedacom.module_main.mvp.model.IBaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 16:07
 * </pre>
 */

public class DemoModel implements IBaseModel {
    @Override
    public void loadUser(final HttpCallback callback) {
        // 调用网络接口
        HttpServicesFactory2.getInstance().getHttpServiceApi().getJoke(1, 2, "video").enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                callback.onSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
