package com.example.caowj.newtask.mvp.presenter.impl;

import com.example.caowj.newtask.mvp.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.mvp.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.mvp.model.BaseModel;
import com.example.caowj.newtask.mvp.model.impl.IndexModelImpl;
import com.example.caowj.newtask.mvp.presenter.BaseDataBridge;
import com.example.caowj.newtask.mvp.presenter.BasePresenter;
import com.example.caowj.newtask.mvp.presenter.BasePresenterImpl;
import com.example.caowj.newtask.mvp.view.BaseView;
import com.kedacom.utils.JudgmentDataUtil;
import com.kedacom.utils.LogUtil;

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
    public void getMoreInfoP(int pageIndex) {
        presenterImpl.showProgress();
//        一个页面中有一个分页
//        indexModel.getMoreInfoM(pageIndex);

//        一个页面有两个分页
        indexModel.getMoreInfoM2(pageIndex);
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
        } else {
            LogUtil.myW(mTag + "notificationList is null...");
        }
    }

    @Override
    public void showMoreInfoB(List<Object> infoList) {
        presenterImpl.hideProgress();
        if (infoList != null && JudgmentDataUtil.hasCollectionData(infoList)) {
            presenterImpl.showMoreInfoV(infoList);
        } else {
            LogUtil.myW(mTag + "infoList is null...");
        }
    }

    @Override
    public void showFixedInfoB(List<Object> infoList) {
        presenterImpl.hideProgress();
        if (infoList != null && JudgmentDataUtil.hasCollectionData(infoList)) {
            presenterImpl.showFixedInfoV(infoList);
        } else {
            LogUtil.myW(mTag + "infoList is null...");
        }
    }
}
