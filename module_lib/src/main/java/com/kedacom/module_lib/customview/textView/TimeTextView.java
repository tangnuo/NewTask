package com.kedacom.module_lib.customview.textView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

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
//                        LogUtil.myD("倒计时时间差（秒）：" + Time);
                        if (mTime > 0) {
                            TimeTextView.this.setText("倒计时    还有" + dateDiffDToSecond(Time, ","));
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


    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    ;

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static String dateDiffDToSecond(long diff, String splitFlag) {
        String result = "";
        long nd = 24 * 60 * 60;// 一天的秒数
        long nh = 60 * 60;// 一小时的秒数
        long nm = 60;// 一分钟的秒数
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh;// 计算差多少小时
        min = diff % nd % nh / nm;// 计算差多少分钟
        // hour = diff % nd / nh + day * 24;// 计算差多少小时
        // min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm;// 计算差多少秒
        // 输出结果
        /*
         * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
         * (min - day * 24 * 60) + "分钟" + sec + "秒。");
         */
//        result = day + splitFlag + hour + splitFlag + min + splitFlag + sec;
        result = day + splitFlag + formatChar(hour) + splitFlag + formatChar(min) + splitFlag + formatChar(sec);//TODO 2.5.1时间显示需要2位数显示

        return result;
    }

    /**
     * 时间显示2位不足补0
     *
     * @param num
     * @return
     */
    public static String formatChar(long num) {
        return String.valueOf(num).length() == 1 ? "0" + String.valueOf(num) : String.valueOf(num);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
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

//        LogUtil.myD("时间差：" + Time + run + "，当前时间：" + t2);
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
