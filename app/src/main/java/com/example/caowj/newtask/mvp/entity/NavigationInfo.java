package com.example.caowj.newtask.mvp.entity;

import com.example.caowj.newtask.mvp.entity.bean.NavigationBean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 16:37
 */
public class NavigationInfo {
    private String code;

    private int gzcount;

    private List<NavigationBean> data;

    private int lineCount;

    private int datalist;


    public List<NavigationBean> getData() {
        return data;
    }

    public void setData(List<NavigationBean> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGzcount() {
        return gzcount;
    }

    public void setGzcount(int gzcount) {
        this.gzcount = gzcount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getDatalist() {
        return datalist;
    }

    public void setDatalist(int datalist) {
        this.datalist = datalist;
    }

}
