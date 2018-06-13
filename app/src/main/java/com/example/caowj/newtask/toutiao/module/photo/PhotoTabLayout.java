package com.example.caowj.newtask.toutiao.module.photo;

import android.support.v4.app.Fragment;

import com.kedacom.utils.LogUtil;

/**
 * @Author : caowj
 * @Date : 2018/4/23
 */

public class PhotoTabLayout extends Fragment {

    private static PhotoTabLayout instance = null;

    public static PhotoTabLayout getInstance() {
        if (instance == null) {
            instance = new PhotoTabLayout();
        }
        return instance;
    }

    public void onDoubleClick() {
        LogUtil.myD("双击刷新暂时未实现。");
    }
}
