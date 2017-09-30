package com.example.caowj.newtask.example.mDagger.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.caowj.newtask.example.fragment.BaseFragment2;

import java.util.ArrayList;
import java.util.List;

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

    public AppModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    FragmentManager providesFragmentManager() {
        return appCompatActivity.getSupportFragmentManager();
    }

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
