package com.example.caowj.newtask.module1.model;

import com.example.caowj.newtask.module1.bean.NavigationInfo;

import java.util.List;

/**
 * by y on 2016/5/27.
 */
public interface BaseDataBridge<T> {

    void addData(List<T> datas);

    void error();

    interface TabNameData extends BaseDataBridge<NavigationInfo> {
    }

}
