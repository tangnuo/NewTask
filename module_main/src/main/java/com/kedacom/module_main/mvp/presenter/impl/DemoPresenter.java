package com.kedacom.module_main.mvp.presenter.impl;

import android.util.Log;

import com.kedacom.module_main.mvp.listener.HttpCallback;
import com.kedacom.module_main.mvp.model.IBaseModel;
import com.kedacom.module_main.mvp.model.impl.DemoModel;
import com.kedacom.module_main.mvp.presenter.IBasePresenter;
import com.kedacom.module_main.mvp.view.IBaseView;
import com.kedacom.module_main.mvp.view.MvpDemoActivity;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 16:01
 * </pre>
 */

public class DemoPresenter implements IBasePresenter {

    private MvpDemoActivity baseView;
    private DemoModel baseModel;

    public DemoPresenter(MvpDemoActivity baseView) {
        this.baseView = baseView;
        this.baseModel = new DemoModel();
    }

    @Override
    public void doRefresh() {
        Log.d("caowj", "IBasePresenter");

        baseView.onShowLoading();
        baseModel.loadUser(new HttpCallback() {
            @Override
            public void onSuccess(String data) {
                baseView.onRefreshData(data);
                baseView.onHideLoading();
            }

            @Override
            public void onError(String message) {
                baseView.onHideLoading();
            }
        });
    }

    @Override
    public void doShowNetError() {
    }
}
