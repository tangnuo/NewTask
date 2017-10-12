package com.example.caowj.newtask.module1.presenter.impl;

import com.example.caowj.newtask.module1.bean.NavigationInfo;
import com.example.caowj.newtask.module1.model.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.model.impl.TabNameModelImpl;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.BasePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.List;

/**
 * by 12406 on 2016/5/14.
 */
public class TabNamePresenterImpl extends BasePresenterImpl<BaseView.TabNameView>
        implements BasePresenter.TabNamePresenter, BaseDataBridge.TabNameData {

    private final BaseModel.TabNameModel tabNameModel;

    public TabNamePresenterImpl(BaseView.TabNameView view) {
        super(view);
        this.tabNameModel = new TabNameModelImpl();
    }

    @Override
    public void requestNetWork() {
        tabNameModel.netWork(this);
        LogUtil.myD("requestNetWork...");
    }

    @Override
    public void addData(List<NavigationInfo> datas) {
        view.setData(datas);
    }

    @Override
    public void error() {
        view.netWorkError();
    }
}
