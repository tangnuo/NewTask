package com.kedacom.module_learn.binder

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/11/2 15:30
 * </pre>
 */

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        //返回服务端的IBinder句柄
        return MyBinder()
    }
}