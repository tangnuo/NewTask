package com.example.caowj.newtask.module1.presenter;

import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
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

    /**
     * 所有的接口面向model
     */
    interface TabNameDataBridge extends BaseDataBridge<NavigationBean> {
        void showNavigationP(NavigationInfo navigationInfo);

        void showPaiPinInfoP(PaiPinInfo2 paiPinInfo);
    }

    interface IndexDataBridge extends BaseDataBridge<ADInfoList> {
        void showMoreInfoB(List<Object> infoList);

        void showFixedInfoB(List<Object> infoList);

        void showAdInfoListB(ADInfoList adInfoList);

        void showNotificationB(ScrollNotificationList notificationList);
    }
}
