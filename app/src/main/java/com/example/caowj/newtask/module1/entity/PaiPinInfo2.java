package com.example.caowj.newtask.module1.entity;

import com.example.caowj.newtask.module1.entity.bean.PaiPinBean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 16:11
 */
public class PaiPinInfo2 {
    private List<PaiPinBean> data;

    private String code;

    private int gzcount;

    private int lineCount;

//    private int datalist;


    public List<PaiPinBean> getData() {
        return data;
    }

    public void setData(List<PaiPinBean> data) {
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

}
