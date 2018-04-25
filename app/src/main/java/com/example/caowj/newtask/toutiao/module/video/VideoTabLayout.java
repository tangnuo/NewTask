package com.example.caowj.newtask.toutiao.module.video;

import android.support.v4.app.Fragment;

import com.example.caowj.newtask.utils.LogUtil;

/**
 * @Author : caowj
 * @Date : 2018/4/23
 */

public class VideoTabLayout extends Fragment {
    private static VideoTabLayout instance = null;

    public static VideoTabLayout getInstance() {
        if (instance == null) {
            instance = new VideoTabLayout();
        }
        return instance;
    }

    public void onDoubleClick() {
        LogUtil.myD("双击刷新暂时未实现。");
    }
}
