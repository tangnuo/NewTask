package com.example.caowj.newtask.base;

import android.app.Application;
import android.content.Context;

import com.example.caowj.newtask.example.mDagger.component.AppComponent;
import com.example.caowj.newtask.example.mDagger.component.DaggerAppComponent;
import com.example.caowj.newtask.example.mDagger.module.AppModule;

/**
 * Created by niuxiaowei on 16/3/19.
 */
public class BaseApp extends Application {

    AppComponent mAppComponent;
    private static BaseApp instance;
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    public BaseApp() {
    }


    // 单例模式获取唯一的MyApplication实例
    public static BaseApp getInstance() {
        if (null == instance) {
            instance = new BaseApp();
        }
        return instance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
