package com.example.caowj.newtask.module1.presenter.impl;

import android.content.Context;

import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;
import com.example.caowj.newtask.module1.entity.bean.PaiPinBean;
import com.example.caowj.newtask.module1.listener.BroadcastCallback;
import com.example.caowj.newtask.module1.model.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.model.impl.TabNameModelImpl;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.BasePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.utils.JudgmentDataUtil;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * by 12406 on 2016/5/14.
 */
public class TabNamePresenterImpl extends BasePresenterImpl<BaseView.TabNameView>
        implements BasePresenter.TabNamePresenter, BaseDataBridge.TabNameData {

    private final BaseModel.TabNameModel tabNameModel;
    private Context mContext;

    public TabNamePresenterImpl(Context mContext, BaseView.TabNameView view) {
        super(view);
        this.mContext = mContext;
        this.tabNameModel = new TabNameModelImpl();
    }

    @Override
    public void getNavigationP() {
        view.showProgress();
        tabNameModel.netWork(this);
    }

    @Override
    public void getDataByTypeP(int typeId, int pageSize, int pageIndex) {
        view.showProgress();
        tabNameModel.getDataByTypeM(typeId, pageSize, pageIndex, this);
    }

    @Override
    public void addData(List<NavigationBean> datas) {
        view.setData(datas);
    }

    @Override
    public void error() {
        view.netWorkError();
    }

    @Override
    public void showNavigationP(final NavigationInfo navigationInfo) {
        view.hideProgress();
        String code = navigationInfo.getCode();

        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {

                ArrayList<NavigationBean> titleList = new ArrayList<>();

                List<NavigationBean> navigationBeanList = navigationInfo.getData();
                if (JudgmentDataUtil.hasCollectionData(navigationBeanList)) {
                    NavigationBean nav = new NavigationBean();
                    nav.setId(0);
                    nav.setCateName("全部");
                    titleList.add(nav);

                    titleList.addAll(navigationBeanList);

                    view.showNavigationV(titleList);
                }
//                int size = navigationBeanList.size();
//                if (size > 0) {
//                    for (int i = 0; i < size; i++) {
//                        NavigationBean mNavigationInfo = navigationBeanList.get(i);
//                        titleList.add(mNavigationInfo);
//                    }
//
//                    view.showNavigationV(titleList);
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

    @Override
    public void showPaiPinInfoP(final PaiPinInfo2 paiPinInfo) {
        view.hideProgress();
        String code = paiPinInfo.getCode();

        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {

                List<PaiPinBean> navigationBeanList = paiPinInfo.getData();

                view.showPaipinInfoV(navigationBeanList);
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
