package com.example.caowj.newtask.example.activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 使用include、merge、viewStub优化UI页面
 */
public class TestUIActivity extends BaseActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.stub)
    ViewStub stub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_ui;
    }

    @Override
    protected void memoryRecovery() {

    }

    @OnClick({R.id.btn_test1, R.id.btn_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                // 这里调用的是inflate方法，当然，也可以调用setVisibility方法（但是不建议这么做）
                // 只能点击一次加载视图按钮，因为inflate只能被调用一次
                // 如果再次点击按钮，会抛出异常"ViewStub must have a non-null ViewGroup viewParent"
                View lazyView = stub.inflate();
                btnTest1.setEnabled(false);
                break;
            case R.id.btn_test2:
//                sendNotification();
                sendBroadcast();
                break;
        }
    }

    /**
     * 发送通知（使用PendingIntent跳转到一个activity组件）
     */
    private void sendNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(mActivity.NOTIFICATION_SERVICE);
        Notification.Builder builder1 = new Notification.Builder(mActivity);
        builder1.setSmallIcon(android.R.drawable.stat_notify_chat); //设置图标
        builder1.setTicker("显示第二个通知caowj");
        builder1.setContentTitle("通知caowj"); //设置标题
        builder1.setContentText("点击查看详细内容caowj"); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失

        Intent intent = new Intent(mActivity, TestTextureViewActivity.class);
//        跳转到一个activity组件
        PendingIntent pendingIntent = PendingIntent.getActivity(mActivity, 0, intent, 0);//当点击消息时就会向系统发送openintent意图
        builder1.setContentIntent(pendingIntent);

        Notification notification1 = builder1.build();
        mNotificationManager.notify(124, notification1); // 通过通知管理器发送通知
    }


    /**
     * <p>闹钟</p>
     * 使用PendingIntent 打开一个广播组件
     */
    private void sendBroadcast() {
        int INTERVAL = 1000 * 60 * 60 * 24;
        AlarmManager alarmService = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 8);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        Intent alarmIntent = new Intent(this, TestTextureViewActivity.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        alarmService.setRepeating(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), INTERVAL, broadcast);
    }
}
