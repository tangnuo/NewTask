package com.example.caowj.newtask.example.mDagger;

import android.content.Context;
import android.widget.Toast;

import com.kedacom.utils.LogUtil;

/**
 * package: com.example.caowj.newtask.example.mDagger
 * author: Administrator
 * date: 2017/9/29 17:32
 */
public class UserTools {
    public void getUserInfo(Context mContext) {
        //请求网络数据
        Toast.makeText(mContext, "成功调取方法", Toast.LENGTH_SHORT).show();
        LogUtil.myD("UserTools--getUserInfo...");
    }
}
