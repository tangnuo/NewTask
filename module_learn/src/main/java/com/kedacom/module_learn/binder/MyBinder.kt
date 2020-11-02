package com.kedacom.module_learn.binder

import android.os.Binder
import android.os.Parcel
import android.os.Process
import android.util.Log

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/11/2 15:30
 * </pre>
 */

class MyBinder : Binder() {
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        if (code == 1) {//如果是约定好的行为码1
            Log.e("caowj", "--- 我是服务端 MyBinder , pid = "
                    + Process.myPid() + ", thread = "
                    + Thread.currentThread().name)
            //1. 从data读取客户端参数
            Log.e("caowj", "服务端收到：" + data.readString())

            val str = "777"
            Log.e("caowj", "服务端返回：$str")
            //2. 从reply向客户端写返回值
            reply?.writeString(str)

            //3. 处理完成
            return true
        }
        return super.onTransact(code, data, reply, flags)
    }
}