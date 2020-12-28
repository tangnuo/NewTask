package com.kedacom.module_learn.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_learn.R
import com.kedacom.module_learn.helper.ThreadPoolManager
import kotlinx.android.synthetic.main.activity_test_udp.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.*

/**
 * android基于UDP协议发送数据的demo
 *
 * 1、保证IP地址是正确的；
 * 2、先启动UdpServer;
 *
 * https://blog.csdn.net/servermanage/article/details/102333244
 */
class TestUdpActivity : BaseActivity(), View.OnClickListener {

    private val MSG_SEND_DATA = 1001
    private val MSG_RECEIVE_DATA = 1002

    @Volatile
    private var mStopReceiver = true
    lateinit var mBtnReceiveUdpData: Button
    private var mAddress: InetAddress? = null
    private var mSocket: DatagramSocket? = null

    /**
     * 发送给整个局域网,局域网网段192.168.0.X
     */
    private val mBroadCastIp = "10.67.8.220"

    /**
     * 发送方和接收方需要端口一致
     */
    private val mSendPort = 8888
    private lateinit var mSendBuf: ByteArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_udp)
        btn_send_udp_data.setOnClickListener(this)
        btn_receive_udp_data.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_send_udp_data -> mainHandler.sendEmptyMessage(MSG_SEND_DATA)
            R.id.btn_receive_udp_data -> mainHandler.sendEmptyMessage(MSG_RECEIVE_DATA)
            else -> {
            }
        }
    }

    private var mainHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_SEND_DATA -> sendUDPMessage(System.currentTimeMillis().toString() + "")
                MSG_RECEIVE_DATA -> {
                    mStopReceiver = !mStopReceiver
                    if (mStopReceiver) {
                        mBtnReceiveUdpData.setText(R.string.start_receive_udp_data)
                    } else {
                        mBtnReceiveUdpData.setText(R.string.stop_receive_udp_data)
                    }
                    if (!mStopReceiver) {
                        receiveMessage()
                    }
                }
                else -> {
                }
            }
        }
    }

    fun sendUDPMessage(msg: String) {

        // 初始化socket
        try {
            mSocket = DatagramSocket()
        } catch (e: SocketException) {
            e.printStackTrace()
        }
        try {
            mAddress = InetAddress.getByName(mBroadCastIp)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        }
        ThreadPoolManager.getInstance().startTaskThread({
            try {
                mSendBuf = msg.toByteArray(charset("utf-8"))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            val recvPacket1 = DatagramPacket(mSendBuf, mSendBuf.size, mAddress, mSendPort)
            try {
                mSocket!!.send(recvPacket1)
                mSocket!!.close()
                Log.e(mTag, "sendUDPMessage msg：$msg")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }, "")
    }

    /**
     * 发送方和接收方需要端口一致
     */
    private val mReceivePort = 9999
    private lateinit var mReceiveBuf: ByteArray
    private var mReceiveSocket: DatagramSocket? = null
    private fun receiveMessage() {
        ThreadPoolManager.getInstance().startTaskThread({
            try {
                mReceiveSocket = DatagramSocket(mReceivePort)
            } catch (e: SocketException) {
                e.printStackTrace()
            }
            mReceiveBuf = ByteArray(1024)
            val packet = DatagramPacket(mReceiveBuf, mReceiveBuf.size)
            while (!mStopReceiver) {
                try {
                    mReceiveSocket!!.receive(packet)
                    val receive = String(packet.data, 0, packet.length, (charset("utf-8")))
                    Log.e(mTag, "receiveMessage msg：$receive")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            mReceiveSocket!!.close()
            mReceiveSocket!!.disconnect()
        }, "")
    }
}