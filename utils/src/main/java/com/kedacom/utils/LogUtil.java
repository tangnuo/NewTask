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
    /**
     * v
     *
     * @param tag
     * @param desc
     */
    public static void v(String tag, String desc) {
        showLogWithLineNum(1, tag, desc);
    }

    public static void v(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG) {
            Log.v(tag, desc);
        }
    }

    /**
     * d
     *
     * @param tag
     * @param desc
     */
    public static void d(String tag, String desc) {
        showLogWithLineNum(2, tag, desc);
    }

    public static void d(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG) {
            Log.d(tag, desc, tr);
        }
    }

    /**
     * i
     *
     * @param tag
     * @param desc
     */
    public static void i(String tag, String desc) {
        showLogWithLineNum(3, tag, desc);
    }

    public static void i(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG) {
            Log.i(tag, desc, tr);
        }
    }

    /**
     * w
     *
     * @param tag
     * @param desc
     */
    public static void w(String tag, String desc) {
        showLogWithLineNum(4, tag, desc);
    }

    public static void w(String tag, Throwable ioe) {
        if (DEBUG_LOG) {
            Log.w(tag, ioe);
        }
    }

    public static void w(String tag, String desc, Throwable e) {
        if (DEBUG_LOG) {
            Log.w(tag, desc, e);
        }
    }

    /**
     * e
     *
     * @param tag
     * @param desc
     */
    public static void e(String tag, String desc) {
        showLogWithLineNum(5, tag, desc);
    }

    public static void e(String tag, String desc, Throwable tr) {
        if (DEBUG_LOG) {
            Log.e(tag, desc, tr);
        }
    }


    /**
     * 显示Log信息（带行号）
     *
     * @param logLevel 1 v ; 2 d ; 3 i ; 4 w ; 5 e .
     * @param info     显示的log信息
     */
    public static void showLogWithLineNum(int logLevel, String logTag, String info) {
        info = getMsgFormat(info);
        switch (logLevel) {
            case 1:
                if (DEBUG_LOG) {
                    Log.v(logTag, info);
                }
                break;
            case 2:
                if (DEBUG_LOG) {
                    Log.d(logTag, info);
                }
                break;
            case 3:
                if (DEBUG_LOG) {
                    Log.i(logTag, info);
                }
                break;
            case 4:
                if (DEBUG_LOG) {
                    Log.w(logTag, info);
                }
                break;
            case 5:
                if (DEBUG_LOG) {
                    Log.e(logTag, info);
                }
                break;
        }
    }


    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行<br>
     * at cn.utils.MainActivity.onCreate(MainActivity.java:17) 就是用來定位行的代碼<br>
     *
     * @return [ Thread:main, at
     * cn.utils.MainActivity.onCreate(MainActivity.java:17)]
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
//                return "[ Thread:" + Thread.currentThread().getName() + ", at "
//                        + st.getClassName() + "." + st.getMethodName()
//                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";

                return "[ at "
                        + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }


    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg) {
        return msg + "具体位置：" + getFunctionName();
    }
}
