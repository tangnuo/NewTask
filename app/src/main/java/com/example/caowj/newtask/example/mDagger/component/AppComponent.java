package com.example.caowj.newtask.example.mDagger.component;

import com.example.caowj.newtask.example.activity.TestDaggerActivity2;
import com.example.caowj.newtask.example.mDagger.module.AppModule;

import dagger.Component;

/**
 * package: com.example.caowj.newtask.example.mDagger.component
 * author: Administrator
 * date: 2017/9/30 15:37
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(TestDaggerActivity2 activity);
}
