package com.example.caowj.newtask.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.caowj.newtask.example.activity.TestIntentServiceActivity;
import com.example.caowj.newtask.utils.LogUtil;

/**
 * IntentService:
 *<p>
 *     1、处理耗时操作，不需要开启新线程；
 *     2、完成所有任务后自动关闭服务；
 *</p>
 * package: com.example.caowj.newtask.example.service
 * author: Administrator
 * date: 2018/3/6 09:23
 */
public class MyIntentService extends IntentService {

    /**
     * 是否正在运行
     */
    private boolean isRunning;

    /**
     * 进度
     */
    private int count;

    /**
     * 广播
     */
    private LocalBroadcastManager mLocalBroadcastManager;


    public MyIntentService() {
        super("MyIntentService");
        LogUtil.myD("MyIntentService");
    }

    public MyIntentService(String name) {
        super(name);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.myD("onCreate");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogUtil.myD("onHandleIntent");
        try {
            Thread.sleep(1000);
            isRunning = true;
            count = 0;
            while (isRunning) {
                count++;
                if (count >= 100) {
                    isRunning = false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中...", count);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送进度消息
     */
    private void sendThreadStatus(String status, int progress) {
        Intent intent = new Intent(TestIntentServiceActivity.ACTION_TYPE_THREAD);
        intent.putExtra("status", status);
        intent.putExtra("progress", progress);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.myD("线程结束运行..." + count);
    }

}
