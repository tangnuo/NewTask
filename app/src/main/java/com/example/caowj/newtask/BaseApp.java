package com.example.caowj.newtask;

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
