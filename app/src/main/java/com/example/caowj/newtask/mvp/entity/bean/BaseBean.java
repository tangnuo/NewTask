package com.example.caowj.newtask.mvp.entity.bean;

import java.util.List;

/**
 * by y on 2016/5/27.
 */
@SuppressWarnings("ALL")
public class BaseBean<T> {

    private List<T> tngou;


    public List<T> getInfo() {
        return tngou;
    }

    public void setInfo(List<T> tngou) {
        this.tngou = tngou;
    }


    public class TabNameBean extends BaseBean<NavigationBean> {
    }


}
