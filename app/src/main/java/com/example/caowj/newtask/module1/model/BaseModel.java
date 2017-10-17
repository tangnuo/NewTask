package com.example.caowj.newtask.module1.model;

/**
 * package: com.example.caowj.newtask.module1.model
 * author: Administrator
 * date: 2017/10/11 11:33
 */
public interface BaseModel<T> {
    void netWork(T model);

    interface TabNameModel extends BaseModel<BaseDataBridge.TabNameData> {
        void getDataByTypeM(int typeId, int pageSize, int pageIndex, BaseDataBridge.TabNameData tabNameData);
    }

}
