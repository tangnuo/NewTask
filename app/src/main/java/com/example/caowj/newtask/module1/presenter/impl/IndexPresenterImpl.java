package com.example.caowj.newtask.module1.presenter.impl;

import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.model.impl.IndexModelImpl;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.BasePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.utils.JudgmentDataUtil;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.List;

/**
 * @Author : caowj
 * @Date : 2018/3/14
 */

public class IndexPresenterImpl extends BasePresenterImpl<BaseView.IndexView> implements BasePresenter.IndexPresenter, BaseDataBridge.IndexDataBridge {

    private final BaseModel.IndexModel indexModel;

    public IndexPresenterImpl(BaseView.IndexView view) {
        super(view);
        indexModel = new IndexModelImpl(this);
    }


    @Override
    public void addData(List<ADInfoList> datas) {

    }

    @Override
    public void error() {

    }

    @Override
    public void getFixedInfoP() {
        presenterImpl.showProgress();
        indexModel.getFixedInfoM();
    }

    @Override
    public void getAdInfoP() {
        presenterImpl.showProgress();
        indexModel.getAdInfoM();
    }

    @Override
    public void getNotificationP() {
        indexModel.getNotificationM();
    }


    /************************************************************/


    @Override
    public void showAdInfoListB(final ADInfoList adInfoList) {
        presenterImpl.hideProgress();
        if (adInfoList != null && JudgmentDataUtil.hasCollectionData(adInfoList.getData())) {
            presenterImpl.showAdInfoV(adInfoList);
        } else {
            LogUtil.myW(mTag + "adInfoList is null...");
        }
    }

    @Override
    public void showNotificationB(ScrollNotificationList notificationList) {
        if (notificationList != null && JudgmentDataUtil.hasCollectionData(notificationList.getData())) {
            presenterImpl.showNotificationV(notificationList);
        }
    }

    @Override
    public void showFixedInfoB(List<Object> infoList) {
        presenterImpl.hideProgress();
        if (infoList != null && JudgmentDataUtil.hasCollectionData(infoList)) {
            presenterImpl.showFixedInfoV(infoList);
        }
    }
}
