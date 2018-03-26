package com.example.caowj.newtask.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.StatusBarCompat;

import butterknife.ButterKnife;

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
        if (getContentView() == 0) {
            Toast.makeText(mActivity, "请传入布局文件", Toast.LENGTH_SHORT).show();
            return;
        }


//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置标题栏颜色
//        StatusBarCompat.compat(this, getResources().getColor(R.color.bg_title_bar));


        setContentView(getContentView());
        LogUtil.myD("111111");
        ButterKnife.bind(this);
        initWidget();
        initData();
        LogUtil.myD("33333");
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
        //使用了BindView代替了。
    }

    protected void initData() {
    }

    protected abstract void memoryRecovery();
}

