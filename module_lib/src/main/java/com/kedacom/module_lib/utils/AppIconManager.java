package com.kedacom.module_lib.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;

/**
 * 动态切换APP的图标
 * http://blog.csdn.net/qq_16628781/article/details/69054325
 * package: com.example.caowj.newtask.utils
 * author: Administrator
 * date: 2018/3/5 15:42
 */
public class AppIconManager {
    private PackageManager mPackageManager;
    //默认组件
    private ComponentName componentNameDefault;
    private ComponentName componentName2;
    private ComponentName componentName3;

    /**
     * 设置第icon2图标生效
     */
    private void enableComponentName2() {
        disableComponent(componentNameDefault);
        disableComponent(componentName3);
        enableComponent(componentName2);
    }

    /**
     * 设置第icon3图标生效
     */
    private void enableComponentName3() {
        disableComponent(componentNameDefault);
        disableComponent(componentName2);
        enableComponent(componentName3);
    }

    private void defaultName() {
        enableComponent(componentNameDefault);
        disableComponent(componentName2);
        disableComponent(componentName3);
    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    //最后调用
    public void pmTest(Activity mContext) {
        //获取到包管理类实例
        mPackageManager = mContext.getPackageManager();
        //得到此activity的全限定名
        componentNameDefault = mContext.getComponentName();
        //根据全限定名创建一个组件，即activity-alias 节点下的name：HomeActivity2 对应的组件
        componentName2 = new ComponentName(mContext.getBaseContext(), "com.example.caowj.newtask.SplashActivity2");
        componentName3 = new ComponentName(mContext.getBaseContext(), "com.example.caowj.newtask.SplashActivity3");

        LogUtil.myD("default:" + componentNameDefault + "\ncomponentName2:" + componentName2 + "\ncomponentName3:" + componentName3);
//        String action = mContext.getActionFromServer();//从后台获取到应该使用那一个组件，或者根据时间来判断
        String action = "comp1";//从后台获取到应该使用那一个组件，或者根据时间来判断

        if ("comp2".equals(action)) {
            enableComponentName2();
        } else if ("comp3".equals(action)) {
            enableComponentName3();
        } else {
            defaultName();
            LogUtil.myD("显示默认图标");
        }
        //如果没有，则显示默认图标
    }


}
