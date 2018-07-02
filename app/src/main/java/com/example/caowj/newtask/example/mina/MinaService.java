package com.example.caowj.newtask.example.mina;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kedacom.utils.LogUtil;

/**
 * package: com.example.caowj.newtask.example.mina
 * author: Administrator
 * date: 2017/9/18 11:24
 */
public class MinaService extends Service {
    private ConnectionHandlerThread thread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        thread = new ConnectionHandlerThread("mina", getApplicationContext());
        LogUtil.myD("service create:");
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.disConnection();
        LogUtil.myD("destory service:");
    }

    /**
     * 负责调用ConnectionManager
     */
    class ConnectionHandlerThread extends HandlerThread {
        boolean isConnection;
        ConnectionManager mManager;
        private Context context;

        public ConnectionHandlerThread(String name, Context context) {
            super(name);
            this.context = context;
            ConnectionConfig config = new ConnectionConfig.Builder(context)
                    .setIp(ConstantUtil.WEB_MATCH_PATH)
                    .setPort(ConstantUtil.WEB_MATCH_PORT)
                    .setReadBufferSize(10240)
                    .setReadBufferSize(10000).builder();
            System.out.println(config.getReadBufferSize());
            mManager = new ConnectionManager(config);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            while (true) {
                isConnection = mManager.connect(); //

                if (isConnection) {

                    Log.d("caowj", "连接成功。。。");
                    break;
                } else {
                    Log.d("caowj", "连接失败。。。");
                }
                try {
                    Log.e("caowj", "尝试重新连接");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void disConnection() {
            mManager.disConnection();
        }
    }
}
