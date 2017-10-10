package com.example.caowj.newtask.base;

import android.app.Application;

import com.example.caowj.newtask.example.mDagger.component.AppComponent;
import com.example.caowj.newtask.example.mDagger.component.DaggerAppComponent;
import com.example.caowj.newtask.example.mDagger.module.AppModule;

/**
 * Created by niuxiaowei on 16/3/19.
 */
public class BaseApp extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
