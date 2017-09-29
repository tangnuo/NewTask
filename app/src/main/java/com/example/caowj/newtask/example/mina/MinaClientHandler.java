package com.example.caowj.newtask.example.mina;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * package: com.example.caowj.newtask.example.mina
 * author: Administrator
 * date: 2017/9/18 10:57
 */
public class MinaClientHandler extends IoHandlerAdapter {
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        Log.i("TEST", "客户端发生异常" + cause.getMessage());
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        Log.i("TEST", "i客户端接收到的信息为:" + msg);
        super.messageReceived(session, message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        super.messageSent(session, message);
    }
}
