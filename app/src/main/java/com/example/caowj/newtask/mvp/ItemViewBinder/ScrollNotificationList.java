package com.example.caowj.newtask.mvp.ItemViewBinder;

import com.example.caowj.newtask.mvp.entity.CommonBean;
import com.example.caowj.newtask.mvp.entity.bean.ScrollNotification;

import java.util.List;

/**
 * @Author : caowj
 * @Date : 2018/3/15
 */
public class ScrollNotificationList extends CommonBean {
    private List<ScrollNotification> data;


    public List<ScrollNotification> getData() {
        return data;
    }

    public void setData(List<ScrollNotification> data) {
        this.data = data;
    }
}