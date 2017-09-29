package com.example.caowj.newtask.example.mDagger.component;

/**
 * package: com.example.caowj.newtask.example.mDagger.component
 * author: Administrator
 * date: 2017/9/29 17:44
 */

import com.example.caowj.newtask.example.activity.TestDaggerActivity;
import com.example.caowj.newtask.example.mDagger.module.HttpModule;
import com.example.caowj.newtask.example.mDagger.module.UserModule;

import dagger.Component;

/**
 * 多个Module
 */
@Component(modules = {UserModule.class, HttpModule.class})
public interface MultiModuleComponent {
    void inject(TestDaggerActivity activity);
}
