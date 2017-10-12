package com.example.caowj.newtask.module1.view;

import com.example.caowj.newtask.example.bean.Person;
import com.example.caowj.newtask.module1.bean.NavigationInfo;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.base
 * author: Administrator
 * date: 2017/10/11 11:29
 */
public interface BaseView<T> {

    void setData(List<T> datas);

    void netWorkError();

    void hideProgress();

    void showProgress();

    void showFoot();

    void hideFoot();


    interface NewsDetailView {
        void setData(Person datas);

        void netWorkError();

        void hideProgress();

        void showProgress();
    }

    interface TabNameView extends BaseView {
        void switchNews();

        void setTitleData(List<NavigationInfo> datas);
    }
}
