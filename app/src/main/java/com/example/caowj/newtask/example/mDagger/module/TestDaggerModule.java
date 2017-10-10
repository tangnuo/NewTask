package com.example.caowj.newtask.example.mDagger.module;


import com.example.caowj.newtask.example.mDagger.GetUserData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@Module
public class TestDaggerModule {

    @Provides
    public GetUserData provideUserData() {
        return new GetUserData();
    }

}
