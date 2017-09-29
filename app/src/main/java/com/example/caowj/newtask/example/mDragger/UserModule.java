package com.example.caowj.newtask.example.mDragger;

import dagger.Module;
import dagger.Provides;

/**
 * package: com.example.caowj.newtask.example.mDragger
 * author: Administrator
 * date: 2017/9/29 15:00
 */

@Module
public class UserModule {

    @Provides
    ApiService provideApiService() {
        return new ApiService();
    }
}
