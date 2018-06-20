package com.kedacom.base.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/20 14:31
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
    }


    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getAppManager().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getAppManager().removeActivity(activity);
        }
    };
}
