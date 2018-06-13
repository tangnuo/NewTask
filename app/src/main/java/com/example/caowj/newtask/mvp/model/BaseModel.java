package com.example.caowj.newtask.mvp.model;

/**
 * package: com.example.caowj.newtask.module1.model
 * author: Administrator
 * date: 2017/10/11 11:33
 */
public interface BaseModel<T> {
    void netWork();

    interface TabNameModel extends BaseModel {
        void getDataByTypeM(int typeId, int pageSize, int pageIndex);
    }


    interface IndexModel extends BaseModel {
        void getMoreInfoM2(int pageIndex);

        void getMoreInfoM(int pageIndex);

        void getFixedInfoM();

        void getAdInfoM();

        void getNotificationM();
    }
}
