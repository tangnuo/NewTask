package com.example.caowj.newtask.example.mDagger;

import dagger.Module;
import dagger.Provides;

/**
 * package: com.example.caowj.newtask.example.mDagger
 * author: Administrator
 * date: 2017/9/29 15:00
 */

@Module
public class UserModule {

    @Provides
    ApiService provideApiService() {
        return new ApiService();
    }


    @Provides
    public UserManager provideUserManager(ApiService apiService) {

        return new UserManager(apiService);
    }
}
