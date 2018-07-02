package com.example.caowj.newtask.example.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseButterKnifeActivity;

/**
 * 消息提醒，上下滚动（TextSwitcher）
 */
public class TextSwitcherActivity extends BaseButterKnifeActivity {

    private static final int NEWS_MESSAGE_TEXTVIEW = 100;//通知公告信息
    private TextSwitcher textSwitcher;
    private String[] news = {"双11回馈活动产品利率增长0.005%", "国家大数据发展纲要，我爸是李刚", "郑重公告", "某某网站会员须知", "网站维护公告"};
    private int index = 0;//textview上下滚动下标
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    index++;
                    textSwitcher.setText(news[index % news.length]);
                    if (index == news.length) {
                        index = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textSwitcher = (TextSwitcher) findViewById(R.id.tv_message);
        newsMessage();
        textSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index % news.length) {
                    case 0:
                        Toast.makeText(mActivity, news[0], Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mActivity, news[1], Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mActivity, news[2], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(mActivity, news[3], Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(mActivity, news[4], Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void newsMessage() {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mActivity);
                textView.setSingleLine();
                textView.setTextSize(22);//字号
                textView.setTextColor(Color.parseColor("#ff3333"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });

        Class s;
        new Thread() {
            @Override
            public void run() {
                while (index < news.length) {
                    synchronized (this) {
                        SystemClock.sleep(4000);//每隔4秒滚动一次
                        handler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                    }
                }
            }
        }.start();
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_text_switcher;
    }

    @Override
    protected void memoryRecovery() {

    }
}
