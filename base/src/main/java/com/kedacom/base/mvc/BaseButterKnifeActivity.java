package com.kedacom.base.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.kedacom.base.common.BaseActivity;

import butterknife.ButterKnife;

/**
 * package: com.example.caowj.newtask.base
 * author: Administrator
 * date: 2017/9/1 11:50
 */
public abstract class BaseButterKnifeActivity extends BaseActivity {
    public String mTag = this.getClass().getSimpleName() + "~~";
    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Title显示当前类名
        this.setTitle(mTag);
        mActivity = this;
        if (getContentView() == 0) {
            Toast.makeText(mActivity, "请传入布局文件", Toast.LENGTH_SHORT).show();
            return;
        }


//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置标题栏颜色
//        StatusBarCompat.compat(this, getResources().getColor(R.color.bg_title_bar));


        setContentView(getContentView());
        ButterKnife.bind(this);
        Log.d("caowj", "111111");
        initWidget();
        initData();
        Log.d("caowj", "333333");
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
        memoryRecovery();
    }

    /*********************************************************/

    protected abstract int getContentView();

    protected void initWidget() {
        //使用了@BindView代替了。
    }

    protected void initData() {
    }

    protected abstract void memoryRecovery();
}

