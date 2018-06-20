package com.example.caowj.newtask;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.caowj.newtask.example.mDagger.component.AppComponent;
import com.example.caowj.newtask.example.mDagger.component.DaggerAppComponent;
import com.example.caowj.newtask.example.mDagger.module.AppModule;
import com.kedacom.base.common.AppManager;
import com.kedacom.base.common.BaseApplication;

/**
 * Created by niuxiaowei on 16/3/19.
 */
public class BaseApp extends BaseApplication {

    AppComponent mAppComponent;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    public static Context getContext() {
        return context;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
