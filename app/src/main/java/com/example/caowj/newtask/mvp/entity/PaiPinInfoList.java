package com.example.caowj.newtask.mvp.entity;

import com.example.caowj.newtask.mvp.entity.bean.PaiPinBean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 16:11
 */
public class PaiPinInfoList extends CommonBean {

    private List<PaiPinBean> data;

    public List<PaiPinBean> getData() {
        return data;
    }

    public void setData(List<PaiPinBean> data) {
        this.data = data;
    }

}
