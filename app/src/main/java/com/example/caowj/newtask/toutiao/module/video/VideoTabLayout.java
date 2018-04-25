package com.example.caowj.newtask.toutiao.module.video;

import android.support.v4.app.Fragment;

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
}
