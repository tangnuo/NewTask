package com.example.caowj.newtask.example.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.widget.imageView.LargeImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片中心点缩放（双击、手势）
 */
public class ZoomImageActivity extends AppCompatActivity {

    private LargeImageView mLargeImageView;
    private MyHandler handler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);


        test2();

    }

    private void test2() {
        mLargeImageView = (LargeImageView) findViewById(R.id.large_image);


//        本地文件
//        try {
//            InputStream inputStream = getAssets().open("world.jpg");
//            if (inputStream == null) {
//                LogUtil.myD("inputStream is null");
//            } else {
//                mLargeImageView.setInputStream(inputStream);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//网络文件
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://wx3.sinaimg.cn/mw690/005MDHA3gy1fpq9c2gjfsj30ku55ak9j.jpg";

                InputStream inputStream = getHttpBitmap(url);
                if (inputStream == null) {
                    LogUtil.myD("inputStream is null");
                } else {
                    Message message = new Message();
                    message.obj = inputStream;
                    handler.sendMessage(message);
                }

            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLargeImageView = null;
    }

    /**
     * 获取网络图片数据流
     *
     * @param url
     * @return
     */
    public InputStream getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            myFileURL = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            // 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            // 连接设置获得数据流
            conn.setDoInput(true);
            // 不使用缓存
            conn.setUseCaches(false);
            // 这句可有可无，没有影响
            // conn.connect();
            // 得到数据流
            is = conn.getInputStream();
//            // 解析得到图片
//            bitmap = BitmapFactory.decodeStream(is);
//            // 关闭数据流
//            is.close();
//            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InputStream is = (InputStream) msg.obj;
            if (mLargeImageView != null) {
                mLargeImageView.setInputStream(is);
            }
        }
    }
}
