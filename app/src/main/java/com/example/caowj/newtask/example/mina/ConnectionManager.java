package com.example.caowj.newtask.example.mina;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.caowj.newtask.utils.LogUtil;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

/**
 * package: com.example.caowj.newtask.example.mina
 * author: Administrator
 * date: 2017/9/18 11:21
 */
public class ConnectionManager {
    public static final String BROADCAST_ACTION = "com.commonlibrary.mina";
    public static final String MESSAGE = "message";
    private ConnectionConfig mConfig;
    private WeakReference<Context> mContext;
    private NioSocketConnector mConnection;
    private IoSession mSession;
    private InetSocketAddress mAddress;

    public ConnectionManager(ConnectionConfig config) {
        this.mConfig = config;
        this.mContext = new WeakReference<Context>(config.getContext());
        init();
    }

    private void init() {
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnection = new NioSocketConnector();
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
        mConnection.getFilterChain().addLast("logger", new LoggingFilter());
        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        mConnection.setHandler(new DefaultHandler(mContext.get()));
        mConnection.setDefaultRemoteAddress(mAddress);
    }

    /**
     * 与服务器连接
     *
     * @return
     */
    public boolean connect() {
        try {
            ConnectFuture future = mConnection.connect();
            future.awaitUninterruptibly();
            mSession = future.getSession();

            SessionManager.getInstance().setSeesion(mSession);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "连接失败");
            return false;
        }
        return mSession != null ? true : false;

    }

    /**
     * 断开连接
     */
    public void disConnection() {
        mConnection.dispose();
        mConnection = null;
        mSession = null;
        mAddress = null;
        mContext = null;
    }

    private static class DefaultHandler extends IoHandlerAdapter {
        private final Context mContext;


        public DefaultHandler(Context context) {
            this.mContext = context;
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
            //将我们的session保存到我们的session manager类中， 从而可以发送消息到服务器
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            if (mContext != null) {
                Intent intent = new Intent(BROADCAST_ACTION);
                intent.putExtra(MESSAGE, message.toString());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                LogUtil.myD("接收到的消息：" + message);
            }
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);

        }
    }
}
