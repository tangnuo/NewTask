package com.kedacom.module_common.base.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * package: com.example.caowj.newtask.base
 * author: Administrator
 * date: 2017/9/1 11:50
 */
public abstract class BaseActivity extends AppCompatActivity {
    public String mTag = this.getClass().getSimpleName() + "~~";
    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Title显示当前类名
        this.setTitle(mTag);
        mActivity = this;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

