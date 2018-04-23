package com.example.caowj.newtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.activity.FunctionListActivity;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;
import com.leon.channel.helper.ChannelReaderUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.btn_test)
    Button btnTest;
    LocalBroadcastManager localBroadcastManager;


    BroadcastReceiver sm = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(mActivity, "接收到了本地广播", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
//        new AppIconManager().pmTest(this);
//        mActivity.registerReceiver(sm,new IntentFilter("BROADCAST_GOTO_INDEX"));
        localBroadcastManager.registerReceiver(sm, new IntentFilter("BROADCAST_GOTO_INDEX"));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        btnGo.setText("从这里开始");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void memoryRecovery() {
        localBroadcastManager.unregisterReceiver(sm);
    }


    @OnClick({R.id.btn_go, R.id.btn_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_go:
                FunctionListActivity.newInstance(this);
                break;
            case R.id.btn_test:
//                testLocalBroadcast();
                testVasDolly();
                break;
        }
    }

    /**
     * 测试本地广播
     */
    private void testLocalBroadcast() {
//                全局广播和局部广播
        Intent mIntent = new Intent();
        mIntent.setAction("BROADCAST_GOTO_INDEX");
//                sendBroadcast(mIntent);
        localBroadcastManager.sendBroadcast(mIntent);
    }

    /**
     * 测试多渠道信息
     */
    private void testVasDolly() {
        String channel = ChannelReaderUtil.getChannel(getApplicationContext());
        MyAndroidUtils.showShortToast(mActivity, "渠道信息：" + channel);
    }
}
