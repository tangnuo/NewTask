package com.example.caowj.newtask.module1.presenter.impl;

import android.content.Context;

import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;
import com.example.caowj.newtask.module1.listener.BroadcastCallback;
import com.example.caowj.newtask.module1.model.BaseDataBridge;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.model.impl.TabNameModelImpl;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.BasePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
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
    public void requestNetWork() {
        tabNameModel.netWork(this);
        LogUtil.myD("requestNetWork...");
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
    public void addNavigation(final NavigationInfo navigationInfo) {
        String code = navigationInfo.getCode();

        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {

                //获取标题内容
                List<String> titles = new ArrayList<>();
                ArrayList<NavigationBean> titleList = new ArrayList<>();


                List<NavigationBean> navigationBeanList = navigationInfo.getData();
                int size = navigationBeanList.size();
                if (size > 0) {
                    NavigationBean nav = new NavigationBean();
                    nav.setId(0);
                    nav.setCateName("全部");
                    titleList.add(nav);
                    titles.add("全部");

                    for (int i = 0; i < size; i++) {
                        NavigationBean mNavigationInfo = navigationBeanList.get(i);
                        titleList.add(mNavigationInfo);
//                        titles.add(StringTool.convert(mNavigationInfo.getCateName()));
                        titles.add(mNavigationInfo.getCateName());
                    }

//                    view.setData(titleList);
                    view.setTitleData(titleList);


                }
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
