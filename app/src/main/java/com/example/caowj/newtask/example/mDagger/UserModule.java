package com.example.caowj.newtask.example.mDagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * package: com.example.caowj.newtask.example.mDagger
 * author: Administrator
 * date: 2017/9/29 15:00
 */

@Module
public class UserModule {

    //在Module中传递上下文对象
    private Context mContext;

    public UserModule(Context context) {
        this.mContext = context;
    }

    public UserModule() {
    }

    @Provides
    ApiService provideApiService() {
        return new ApiService();
    }


    /**
     * 如果自定义Module中的方法需要对象参数，怎么处理？
     * <p>
     * 1、通过Module中的方法进行提供，比如provideApiService()
     * 2、通过类的构造函数进行提供对象，注释掉上面的provideUserStore()方法
     *
     * @param apiService
     * @return
     */
    @Provides
    public UserManager provideUserManager(ApiService apiService) {

        return new UserManager(apiService);
    }
}
