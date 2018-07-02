package com.example.caowj.newtask.mvvm.viewmodel;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class RefreshListModel<T> {

    public static final int REFRESH = 3001; // refresh
    public static final int UPDATE = 3002; // update

    @RefreshType
    public int refreshType;
    public List<T> list;

    public RefreshListModel() {

    }

    public void setRefreshType() {
        refreshType = REFRESH;
    }

    public void setUpdateType() {
        refreshType = UPDATE;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isRefreshType() {
        return REFRESH == refreshType;
    }

    public void setRefreshType(List<T> list) {
        refreshType = REFRESH;
        this.list = list;
    }

    public boolean isUpdateType() {
        return UPDATE == refreshType;
    }

    public void setUpdateType(List<T> list) {
        refreshType = UPDATE;
        this.list = list;
    }

    @IntDef({REFRESH, UPDATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RefreshType {

    }
}
