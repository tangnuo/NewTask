package com.example.caowj.newtask.example.mDagger.module;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.caowj.newtask.example.fragment.BaseFragment2;
import com.example.caowj.newtask.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * package: com.example.caowj.newtask.example.mDagger.module
 * author: Administrator
 * date: 2017/9/30 15:22
 */
@Module
public class AppModule {
    private AppCompatActivity appCompatActivity;
    private Context mContext;

    public AppModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    FragmentManager providesFragmentManager() {
        return appCompatActivity.getSupportFragmentManager();
    }


    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

//    @Provides
//    @Singleton
//    public Navigator provideNavigator() {
//        return new Navigator();
//    }

    @Provides
    @Singleton
    public ToastUtil provideToastUtil() {
        return new ToastUtil(mContext);
    }

    /************************************提供数据******************************************/


    @Provides
    List<String> providesTitles() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("张三:" + i);
        }
        return list;
    }

    @Provides
    List<Fragment> providesFragmentList(List<String> titles) {
        List<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            fragments.add(BaseFragment2.getInstance(title));
        }
        return fragments;
    }

}
