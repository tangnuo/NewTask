package com.kedacom.module_learn.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.os.Process
import android.os.Process.myPid
import android.util.Log

/**
 *  Binder浅析
 *  https://juejin.im/post/6890088205916307469
 *  作者：Caowj
 *  邮箱：caoweijian@kedacom.com
 *  日期：2020/11/2 15:38
 */
class NoAidlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MyService::class.java)

        bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                //1. 从对象池拿到可复用的对象（享元模式）
                val data = Parcel.obtain()
                val reply = Parcel.obtain()

                Log.e("caowj", "--- 我是客户端 NoAidlActivity , pid = "
                        + android.os.Process.myPid() + ", thread = "
                        + Thread.currentThread().name)

                val str = "666"
                Log.e("caowj", "客户端向服务端发送：$str")
                //2. 往data写数据，作为请求参数
                data.writeString(str)

                //3. 拿到服务端的IBinder句柄，调用transact
                //约定行为码是1；需要服务端的返回值，所以flags传0表示同步调用
                service?.transact(1, data, reply, 0)

                Log.e("caowj", "--- 我是客户端 NoAidlActivity , pid = "
                        + Process.myPid() + ", thread = "
                        + Thread.currentThread().name)

                //4. 从reply读取服务端的返回值
                Log.e("caowj", "客户端接收服务端返回：" + reply.readString())
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("caowj", "service断开了：" + name?.className)
            }

        }, Context.BIND_AUTO_CREATE)

    }
}