package com.kedacom.utils;

import android.util.Log;

/**
 * Logcat封装
 * <p>
 * 升级：http://blog.csdn.net/brian512/article/details/47056127<br/>
 * http://www.cnblogs.com/HopeGi/archive/2012/12/31/2840812.html<br/>
 */
public class LogUtil {
    /**
     * 日志调试标识，调试为true，正式为false
     */
    public static final boolean DEBUG_LOG = true;
    /**
     * 服务器调试标识，调试为true，正式为false
     */
    public static final boolean DEBUG_SERVER = false;

    private static String LogTag = "caowj";

    private LogUtil() {
    }

    /**
     * 自定义日志输出，tag固定
     *
     * @param desc
     */
    public static void myD(String desc) {
        d(LogTag, desc);
    }

    public static void myV(String desc) {
        v(LogTag, desc);
    }

    public static void myE(String desc) {
        e(LogTag, desc);
    }

    public static void myW(String desc) {
        w(LogTag, desc);
    }

    /*********************************************/
    public static void d(String tag, String desc) {
        if (DEBUG_LOG)
            Log.d(tag, desc);
    }

    public static void d(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG)
            Log.d(tag, desc, tr);
    }

    public static void v(String tag, String desc) {
        if (DEBUG_LOG)
            Log.v(tag, desc);
    }

    public static void v(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG)
            Log.v(tag, desc);
    }

    public static void w(String tag, String desc) {
        if (DEBUG_LOG)
            Log.w(tag, desc);
    }

    public static void w(String tag, Throwable ioe) {
        if (DEBUG_LOG)
            Log.w(tag, ioe);
    }

    public static void w(String tag, String desc, Throwable e) {
        if (DEBUG_LOG)
            Log.w(tag, desc, e);
    }

    public static void i(String tag, String desc) {
        if (DEBUG_LOG)
            Log.i(tag, desc);
    }

    public static void i(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG)
            Log.i(tag, desc, tr);
    }

    public static void e(String tag, String desc) {
        if (DEBUG_LOG)
            Log.e(tag, desc);
    }

    public static void e(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG)
            Log.e(tag, desc, tr);
    }
}
