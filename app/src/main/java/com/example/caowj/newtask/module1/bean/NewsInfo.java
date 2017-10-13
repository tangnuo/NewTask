package com.example.caowj.newtask.module1.bean;

import java.util.List;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 16:37
 */
public class NewsInfo {
    private int code;

    private String msg;

    private List<NewsBean2> newslist;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsBean2> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean2> newslist2) {
        this.newslist = newslist2;
    }
}
