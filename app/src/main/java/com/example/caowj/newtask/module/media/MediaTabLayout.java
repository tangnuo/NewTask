package com.example.caowj.newtask.module.media;

import android.support.v4.app.Fragment;

/**
 * @Author : caowj
 * @Date : 2018/4/23
 */

public class MediaTabLayout extends Fragment {

    private static MediaTabLayout instance = null;

    public static MediaTabLayout getInstance() {
        if (instance == null) {
            instance = new MediaTabLayout();
        }
        return instance;
    }
}
