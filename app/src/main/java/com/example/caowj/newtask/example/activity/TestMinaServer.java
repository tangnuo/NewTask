package com.example.caowj.newtask.example.activity;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * mina服务端测试<br/>
 * 注意：文本过滤器慎用，初步调试时卡在这。
 * <p>
 * package: com.example.caowj.newtask.example
 * author: Administrator
 * date: 2017/9/18 14:23
 */
public class TestMinaServer {
    // 端口号，要求客户端与服务器端一致
    private static int PORT = 8080;

    public static void main(String[] args) {
        IoAcceptor acceptor = null;
        try {
            // 创建一个非阻塞的server端的Socket
            acceptor = new NioSocketAcceptor();

            //添加日志过滤器
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            // 自定义的编解码器
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

            // 设置过滤器（使用mina提供的文本换行符编解码器）
//            acceptor.getFilterChain().addLast("codec",
//                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
//                            LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));

            // 设置读取数据的换从区大小
            acceptor.getSessionConfig().setReadBufferSize(2048);
            // 读写通道10秒内无操作进入空闲状态
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            // 为接收器设置管理服务
            acceptor.setHandler(new DemoServerHandler());
            // 绑定端口
            acceptor.bind(new InetSocketAddress(PORT));
            System.out.println("服务器启动成功... 端口号未：" + PORT);

        } catch (Exception e) {
            System.out.println("服务器启动异常...");
            e.printStackTrace();
        }
    }

    private static class DemoServerHandler extends IoHandlerAdapter {

        // 从端口接受消息，会响应此方法来对消息进行处理
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            String msg = message.toString();
            if ("exit".equals(msg)) {
                // 如果客户端发来exit，则关闭该连接
                session.close(true);
            }
            // 向客户端发送消息
            Date date = new Date();
            session.write(date.toString());
            System.out.println("服务器接受消息成功..." + msg);
        }

        // 向客服端发送消息后会调用此方法
        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
//      session.close(true);//加上这句话实现短连接的效果，向客户端成功发送数据后断开连接
            System.out.println("服务器发送消息成功...");
        }

        // 关闭与客户端的连接时会调用此方法
        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
            System.out.println("服务器与客户端断开连接...");
        }

        // 服务器与客户端创建连接
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
            System.out.println("服务器与客户端创建连接...");
        }

        // 服务器与客户端连接打开
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            System.out.println("服务器与客户端连接打开...");
            super.sessionOpened(session);
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
            super.sessionIdle(session, status);
            System.out.println("服务器进入空闲状态...");
        }

        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            super.exceptionCaught(session, cause);
            System.out.println("服务器发送异常...");
        }
    }
}
