package com.example.caowj.newtask.example.mina;

import android.util.Log;

import org.apache.mina.core.session.IoSession;

/**
 * package: com.example.caowj.newtask.example.mina
 * author: Administrator
 * date: 2017/9/18 11:21
 */
public class SessionManager {
    private static SessionManager mInstance = null;

    private IoSession mSession;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (mInstance == null) {
            synchronized (SessionManager.class) {
                if (mInstance == null) {
                    mInstance = new SessionManager();
                }
            }
        }
        return mInstance;
    }

    public void setSeesion(IoSession session) {
        this.mSession = session;
    }

    public void writeToServer(Object msg) {
        if (mSession != null) {
            Log.e("tag", "客户端准备发送消息");
            mSession.write(msg);
        }
    }

    public void closeSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void removeSession() {
        this.mSession = null;
    }
}
