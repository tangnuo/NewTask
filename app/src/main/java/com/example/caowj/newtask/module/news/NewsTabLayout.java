package com.example.caowj.newtask.module.news;

import android.support.v4.app.Fragment;

/**
 * @Author : caowj
 * @Date : 2018/4/23
 */

public class NewsTabLayout extends Fragment {
    private static NewsTabLayout instance = null;

    public static NewsTabLayout getInstance() {
        if (instance == null) {
            instance = new NewsTabLayout();
        }
        return instance;
    }

}
