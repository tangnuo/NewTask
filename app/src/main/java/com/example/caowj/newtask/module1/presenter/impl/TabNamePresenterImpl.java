package com.example.caowj.newtask.module1.presenter.impl;

import android.content.Context;

import com.example.caowj.newtask.module1.constants.Constants;
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
    private List<PaiPinBean> pinBeanList = new ArrayList<>();
    //是否继续加载
    private boolean isLoading = true;
    private int pageIndex = 1;
    private int typeId;

    public TabNamePresenterImpl(Context mContext, BaseView.TabNameView view) {
        super(view);
        this.mContext = mContext;
        this.tabNameModel = new TabNameModelImpl(this);
    }

    @Override
    public void getNavigationP() {
        presenterImpl.showProgress();
        tabNameModel.netWork();
    }

    @Override
    public void getDataByTypeP(int typeId) {
        this.typeId = typeId;
        isLoading = true;
        pageIndex = 1;

        presenterImpl.showProgress();
        tabNameModel.getDataByTypeM(typeId, Constants.PAGESIZE, pageIndex);
    }

    @Override
    public void onRefreshBegin() {
        pageIndex = 1;

//        view.showProgress();
        tabNameModel.getDataByTypeM(typeId, Constants.PAGESIZE, pageIndex);
    }

    @Override
    public void onLoadMoreBegin() {
        presenterImpl.showProgress();
        if (JudgmentDataUtil.hasCollectionData(pinBeanList)) {
            if (isLoading) {
                pageIndex++;
                tabNameModel.getDataByTypeM(typeId, Constants.PAGESIZE, pageIndex);
            } else {
                LogUtil.myD("无法加载更多了");
            }
        } else {
            pageIndex = 1;
            tabNameModel.getDataByTypeM(typeId, Constants.PAGESIZE, pageIndex);
        }
    }

    @Override
    public void addData(List<NavigationBean> datas) {

    }

    @Override
    public void error() {
        presenterImpl.netWorkError();
    }

    @Override
    public void showNavigationP(final NavigationInfo navigationInfo) {
        presenterImpl.hideProgress();
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

                    presenterImpl.showNavigationV(titleList);
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
        presenterImpl.hideProgress();
        String code = paiPinInfo.getCode();

        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {
                if (pageIndex == 1) {
                    pinBeanList.clear();
                }
                List<PaiPinBean> navigationBeanList = paiPinInfo.getData();
                if (JudgmentDataUtil.hasCollectionData(navigationBeanList)) {
                    isLoading = true;
                    pinBeanList.addAll(navigationBeanList);
                } else {
                    isLoading = false;
                }

                presenterImpl.showPaipinInfoV(pinBeanList);
            }

            @Override
            public void return1002() {
                isLoading = false;
                if (pageIndex == 1) {
                    pinBeanList.clear();
                    presenterImpl.showPaipinInfoV(pinBeanList);
                }
                LogUtil.myD("没有数据返回1002。。。");
                MyAndroidUtils.showShortToast(mContext, "没有数据返回1002");
            }

            @Override
            public void returnOther(String code) {
                //最简单的方法：isLoading = false;

                isLoading = true;
                pageIndex--;
                if (pageIndex < 1) {
                    pageIndex = 1;
                }
                MyAndroidUtils.returnCodePrompt(mContext, code, null);
            }
        });
    }
}
