package com.example.caowj.newtask.example.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.mina.ConnectionManager;
import com.example.caowj.newtask.example.mina.MinaService;
import com.example.caowj.newtask.example.mina.SessionManager;
import com.kedacom.utils.LogUtil;

/**
 * http://blog.csdn.net/zxc123e/article/details/54692437<p/>
 * http://blog.csdn.net/chenzheng8975/article/details/53558387?locationNum=13&fps=1
 *
 * 执行步骤：
 * 1、启动服务端测试类：TestMinaServer.java
 * 2、启动服务连接服务端
 * 3、发送数据。
 *
 * @deprecated see { MinaDemo项目 }
 */
public class TestMinaActivity extends BaseActivity implements View.OnClickListener {

    private MessageBroadcastReceiver receiver = new MessageBroadcastReceiver();
    private Button btn1, btn2;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        message = (TextView) findViewById(R.id.textView2);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        registerBroadcast();
    }


    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(ConnectionManager.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    private void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MinaService.class));
        unregisterBroadcast();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
//                SessionManager.getInstance().writeToServer("1234");
                SessionManager.getInstance().writeToServer("I am android client ~ start connect222。/n");
                LogUtil.myD("发送消息。。。");
                break;
            case R.id.btn2:
                Intent intent = new Intent(this, MinaService.class);
                startService(intent);
                LogUtil.myD("click minaService...");
                break;
        }
    }

    private class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            message.setText(intent.getStringExtra(ConnectionManager.MESSAGE));
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_mina;
    }

    @Override
    protected void memoryRecovery() {

    }

    /**************************************************/
}
