package com.example.caowj.newtask.example.mDagger;

import android.content.Context;

import com.example.caowj.newtask.example.mDagger.ApiService;
import com.example.caowj.newtask.utils.LogUtil;

/**
 * package: com.example.caowj.newtask.example.mDagger.user
 * author: Administrator
 * date: 2017/9/29 17:26
 */
public class UserManager {

    ApiService mApiService;

    public UserManager(ApiService apiService) {
        mApiService = apiService;
    }

    public void register(Context mContext) {
        //请求网络数据
        LogUtil.myD("UserManager--register");
        mApiService.register(mContext);
    }
}
