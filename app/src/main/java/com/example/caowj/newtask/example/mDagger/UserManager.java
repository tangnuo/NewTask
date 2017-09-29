package com.example.caowj.newtask.example.mDagger;

import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * package: com.example.caowj.newtask.example.mDagger
 * author: Administrator
 * date: 2017/9/29 16:30
 */
public class UserManager {

    ApiService mApiService;

    public UserManager(ApiService apiService) {
        mApiService = apiService;
    }

    public void register(Context mContext) {
        //请求网络数据
        Log.i(TAG, "UserManager--register");
        mApiService.register(mContext);
    }
}
