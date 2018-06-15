package com.example.caowj.newtask.toutiao.module.base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.color.CircleView;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.toutiao.Constant;
import com.example.caowj.newtask.toutiao.util.SettingUtil;
import com.kedacom.base.common.BaseActivity;


/**
 * Created by Meiji on 2016/12/12.
 */

public abstract class BaseToolbarActivity extends BaseActivity {

    private static final String TAG = "BaseActivity";
    //    protected SlidrInterface slidrInterface;
    protected Context mContext;
    private int iconType = -1;

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化滑动返回
     */
    protected void initSlidable() {
        int isSlidable = SettingUtil.getInstance().getSlidable();
//        if (isSlidable != Constant.SLIDABLE_DISABLE) {
//            SlidrConfig config = new SlidrConfig.Builder()
//                    .edge(isSlidable == Constant.SLIDABLE_EDGE)
//                    .build();
//            slidrInterface = Slidr.attach(this, config);
//        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.iconType = SettingUtil.getInstance().getCustomIconValue();
        this.mContext = this;
        initSlidable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int color = SettingUtil.getInstance().getColor();
        int drawable = Constant.ICONS_DRAWABLES[SettingUtil.getInstance().getCustomIconValue()];
        if (getSupportActionBar() != null)
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(CircleView.shiftColorDown(color));
            // 最近任务栏上色
            ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(
                    getString(R.string.app_name),
                    BitmapFactory.decodeResource(getResources(), drawable),
                    color);
            setTaskDescription(tDesc);
            if (SettingUtil.getInstance().getNavBar()) {
                getWindow().setNavigationBarColor(CircleView.shiftColorDown(color));
            } else {
                getWindow().setNavigationBarColor(Color.BLACK);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Fragment 逐个出栈
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onStop() {

        if (iconType != SettingUtil.getInstance().getCustomIconValue()) {
            new Thread(() -> {

                String act = ".SplashActivity_";

                for (String s : Constant.ICONS_TYPE) {
                    getPackageManager().setComponentEnabledSetting(new ComponentName(BaseToolbarActivity.this, getPackageName() + act + s),
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                }

                act += Constant.ICONS_TYPE[SettingUtil.getInstance().getCustomIconValue()];

                getPackageManager().setComponentEnabledSetting(new ComponentName(BaseToolbarActivity.this, getPackageName() + act),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
            }).start();
        }

        super.onStop();
    }

//    public <X> AutoDisposeConverter<X> bindAutoDispose() {
//        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
//                .from(this, Lifecycle.Event.ON_DESTROY));
//    }
}
