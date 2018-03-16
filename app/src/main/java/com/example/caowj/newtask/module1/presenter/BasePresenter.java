package com.example.caowj.newtask.module1.presenter;

/**
 * View发出请求后，通过这些接口通知model处理。
 * package: com.example.caowj.newtask.module1.presenter.impl
 * author: Administrator
 * date: 2017/10/11 11:31
 */
public interface BasePresenter {
    interface ImageDetailPresenter {
        void requestNetWork(int id);

        void competence(int requestCode, int[] grantResults);
    }


    /**
     * 所有的接口面向View
     */
    interface TabNamePresenter {
        void getNavigationP();

        void getDataByTypeP(int typeId);

        void onRefreshBegin();

        void onLoadMoreBegin();
    }

    interface IndexPresenter {
        /**
         * 获取固定信息
         */
        void getFixedInfoP();

        void getAdInfoP();

        void getNotificationP();
    }
}


