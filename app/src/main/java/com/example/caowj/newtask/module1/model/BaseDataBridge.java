package com.example.caowj.newtask.module1.model;

import com.example.caowj.newtask.module1.entity.NavigationInfo;
import com.example.caowj.newtask.module1.entity.PaiPinInfo2;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;

import java.util.List;

/**
 * model获取数据后，通过这些接口通知presenter
 * by y on 2016/5/27.
 */
public interface BaseDataBridge<T> {

    void addData(List<T> datas);

    void error();

    interface TabNameData extends BaseDataBridge<NavigationBean> {
        void showNavigationP(NavigationInfo navigationInfo);

        void showPaiPinInfoP(PaiPinInfo2 paiPinInfo);
    }

}
