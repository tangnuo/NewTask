package com.example.caowj.newtask.example.mDragger;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * package: com.example.caowj.newtask.example.mDragger
 * author: Administrator
 * date: 2017/9/29 14:59
 */
public class ApiService {
    private static final String TAG = "ApiService";

    public void register(Context mContext) {
        //请求网络数据
        Log.i(TAG, "测试数据");
        Toast.makeText(mContext, "成功调取方法", Toast.LENGTH_SHORT).show();

    }
}
