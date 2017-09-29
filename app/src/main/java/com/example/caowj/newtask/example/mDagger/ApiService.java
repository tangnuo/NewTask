package com.example.caowj.newtask.example.mDagger;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * package: com.example.caowj.newtask.example.mDagger.user
 * author: Administrator
 * date: 2017/9/29 17:25
 */
public class ApiService {
    private static final String TAG = "ApiService";

//通过构造函数提供依赖 （注释掉provideApiService()）
//    @Inject
//    public ApiService(){
//
//    }

    public void register(Context mContext) {
        //请求网络数据
        Log.i(TAG, "测试数据");
        Toast.makeText(mContext, "成功调取方法", Toast.LENGTH_SHORT).show();

    }
}
