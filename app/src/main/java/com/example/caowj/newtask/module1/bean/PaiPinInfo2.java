package com.example.caowj.newtask.module1.bean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 16:11
 */
public class PaiPinInfo2 {
    private List<PaiPinBean> adBeanList;

    private String code;

    private int gzcount;

    private int lineCount;

    private int datalist;

    public List<PaiPinBean> getAdBeanList() {
        return adBeanList;
    }

    public void setAdBeanList(List<PaiPinBean> adBeanList) {
        this.adBeanList = adBeanList;
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
