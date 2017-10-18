package com.example.caowj.newtask.module1.view;

import com.example.caowj.newtask.example.bean.Person;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;
import com.example.caowj.newtask.module1.entity.bean.PaiPinBean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.base
 * author: Administrator
 * date: 2017/10/11 11:29
 */
public interface BaseView<T> {

    void netWorkError();

    void hideProgress();

    void showProgress();


    interface NewsDetailView {
        void setData(Person datas);
    }

    interface TabNameView extends BaseView {
        void showNavigationV(List<NavigationBean> datas);

        void showPaipinInfoV(List<PaiPinBean> paiPinBeanList);
    }
}
