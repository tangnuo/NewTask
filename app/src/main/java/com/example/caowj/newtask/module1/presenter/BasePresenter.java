package com.example.caowj.newtask.module1.presenter;

import com.example.caowj.newtask.example.bean.Person;

/**
 * package: com.example.caowj.newtask.module1.presenter.impl
 * author: Administrator
 * date: 2017/10/11 11:31
 */
public interface BasePresenter {
    interface ImageDetailPresenter {
        void requestNetWork(int id);

        void competence(int requestCode, int[] grantResults);
    }

    interface ImageListPresenter {
        void requestNetWork(int id, int page, boolean isNull);

        void onClick(Person info);
    }


    interface TabNamePresenter {
        void requestNetWork();
    }
}