package com.example.caowj.newtask.example.mDagger.component;

import android.app.Activity;

import com.example.caowj.newtask.example.mDagger.module.ActivityModule;
import com.example.caowj.newtask.example.mDagger.scopes.PerActivity;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
