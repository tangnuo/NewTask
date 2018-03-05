package com.example.caowj.newtask.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.caowj.newtask.utils.LogUtil;

/**
 * package: com.example.caowj.newtask.other
 * author: Administrator
 * date: 2018/3/5 16:05
 */
public class BootupReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.myD("监听到的广播：" + action);
        if (ACTION_BOOT.equals(action)) {
            Toast.makeText(context, "检测到开机了", Toast.LENGTH_LONG).show();
        }
    }
}
