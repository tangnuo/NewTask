package com.example.caowj.newtask.example.mDagger.component;

import android.content.Context;

import com.example.caowj.newtask.example.activity.TestDaggerActivity2;
import com.example.caowj.newtask.example.mDagger.module.AppModule;
import com.example.caowj.newtask.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * package: com.example.caowj.newtask.example.mDagger.component
 * author: Administrator
 * date: 2017/9/30 15:37
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(TestDaggerActivity2 activity);

    Context getContext();

//    Navigator getNavigator();

    ToastUtil getToastUtil();
}
