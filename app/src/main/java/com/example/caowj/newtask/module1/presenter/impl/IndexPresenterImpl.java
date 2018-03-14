package com.example.caowj.newtask.module1.presenter.impl;

import android.content.Context;

import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.listener.BroadcastCallback;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.model.impl.IndexModelImpl;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.BasePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;

import java.util.List;

/**
 * @Author : caowj
 * @Date : 2018/3/14
 */

public class IndexPresenterImpl extends BasePresenterImpl<BaseView.IndexView> implements BasePresenter.IndexPresenter, BaseDataBridge.IndexDataBridge {

    private final BaseModel.IndexModel indexModel;
    private Context mContext;

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
    public void getAdInfoP() {
        presenterImpl.showProgress();
        indexModel.getAdInfoM();
    }


    /************************************************************/


    @Override
    public void showAdInfoListB(final ADInfoList adInfoList) {
        presenterImpl.hideProgress();

        String code = adInfoList.getCode();
        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {

                presenterImpl.showAdInfoV(adInfoList);
//
//                ArrayList<NavigationBean> titleList = new ArrayList<>();
//
//                List<NavigationBean> navigationBeanList = navigationInfo.getData();
//                if (JudgmentDataUtil.hasCollectionData(navigationBeanList)) {
//                    NavigationBean nav = new NavigationBean();
//                    nav.setId(0);
//                    nav.setCateName("全部");
//                    titleList.add(nav);
//
//                    titleList.addAll(navigationBeanList);
//
//                    presenterImpl.showAdInfoV(titleList);
//                }

            }

            @Override
            public void return1002() {
                LogUtil.myD("获取数据失败1002。。。");
                MyAndroidUtils.showShortToast(mContext, "获取标题失败1002");
            }

            @Override
            public void returnOther(String code) {
                MyAndroidUtils.returnCodePrompt(mContext, code, null);
            }
        });
    }

}
