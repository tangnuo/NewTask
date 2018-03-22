package com.example.caowj.newtask.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.TimeUtil;

import java.util.Date;

/**
 * 倒计时控件
 * <p>
 * http://blog.csdn.net/gengqiquan/article/details/50541007
 * </p>
 *
 * @Author : caowj
 * @Date : 2018/3/22
 */

public class TimeTextView extends android.support.v7.widget.AppCompatTextView {

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }


    long Time;//时间差
    private boolean run = true; // 是否启动了
    @SuppressLint("NewApi")
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (run) {
                        long mTime = Time;
                        LogUtil.myD("倒计时时间差（秒）：" + Time);
                        if (mTime > 0) {
                            TimeTextView.this.setText("倒计时    还有" + TimeUtil.dateDiffDToSecond(Time));
//                            Time = Time - 1000;
                            Time = Time - 1;//单位必须保持一致，秒级别的运算
                            //1秒后再次发送
                            handler.sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        TimeTextView.this.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };


    public TimeTextView(Context context) {
        super(context);
    }

    ;

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("NewApi")
    /**
     * 需要传入秒
     */
    public void setTimes(long mT) {
        // 标示已经启动
        Date date = new Date();
        //TODO 必要的时候需要从服务端获取时间
        long t2 = date.getTime() / 1000;
        Time = mT - t2;
        date = null;

        LogUtil.myD("时间差：" + Time + run + "，当前时间：" + t2);
        if (Time > 0) {
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        } else {
            TimeTextView.this.setVisibility(View.GONE);
        }
    }

    public void stop() {
        run = false;
    }
}
