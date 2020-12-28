package com.kedacom.module_java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/12/28 14:24
 * </pre>
 */

public class UdpServer {
    public static void main(String[] args) throws SocketException, IOException {
        //创建接收服务器，端口为8888
        DatagramSocket socket = new DatagramSocket(8888);

        //新建一个包，用来装接收的消息，括号内为此包的容量，即能装下的字节数
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        //准备接收消息，并将消息存入包内，若接收不到消息服务器会一直开放，程序不会向下运行
        while(true){
            socket.receive(packet);
            System.out.println("接收到UDP数据："+new String(packet.getData()).trim());
        }
    }
}
