package com.kedacom.module_lib.base.crash;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;


import com.kedacom.module_lib.base.common.AppManager;
import com.kedacom.module_lib.utils.AppUtil;
import com.kedacom.module_lib.utils.LogUtil;
import com.kedacom.module_lib.utils.TimeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * UncaughtException处理类,
 * <p>
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 */
public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    // CrashHandler实例
    private static CrashHandler INSTANCE = null;
    // 系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }

        return INSTANCE;
    }

    public static void exitProgram(Context context) {
        if (context != null && context instanceof Activity) {
            Activity act = (Activity) context;

            act.finish();
        }
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.restartPackage(context.getPackageName());

        System.exit(0);
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;

        checkAndDelLog();
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            if (ex instanceof RuntimeException) {
            } else {
                mDefaultHandler.uncaughtException(thread, ex);
            }
            exit();
        } else {
            exit();
        }

    }

    private void exit() {
        try {
//			NotificationManager nManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//			nManager.cancel(Constant.CLIENTAPK_NOTIFY_ID);
//			ActivityUtil.cancelNotification(mContext);

            AppManager.getAppManager().removeAllActivity();
            exitProgram(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        String tfile = saveCrashInfo2File(ex);
//         if (NetWorkUtil.isWifiConnected(mContext)) {
//         uploadFile(GlobalUserInfo.CRASHLOGURL, tfile);
//         }
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String time = formatter.format(new Date());
            String fileName = "v" + AppUtil.getVersionName(mContext) + "-" + time
                    + "-mx-" + AppUtil.getImeiString(mContext) + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = AppUtil.getCrashPath(mContext);
                File dir = new File(path);
                if (!dir.exists()) {

                    boolean flat = dir.mkdirs();
                    if (flat) {
                        save2File(sb, fileName, path);
                    } else {
                        LogUtil.myW("新建文件夹失败，可能缺少文件读写权限。");
                    }
                } else {
                    save2File(sb, fileName, path);
                }
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    /**
     * 保存日志文件到本地
     *
     * @param sb       日志信息
     * @param fileName 文件名称
     * @param path     文件路径
     * @throws IOException
     */
    private void save2File(StringBuffer sb, String fileName, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + fileName);
        fos.write(sb.toString().getBytes());
        fos.close();
    }


    private void checkAndDelLog() {
        File logDir = new File(AppUtil.getCrashPath(mContext));

        File[] files = logDir.listFiles();

        String nameH = "v" + AppUtil.getVersionName(mContext) + "-";
        String yesterdayString = nameH + TimeUtil.getYesterdayString();
        String todayString = nameH + TimeUtil.formatDate(new Date(), "yyyy-MM-dd");

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String name = file.getName();
                    if (name.startsWith(yesterdayString) || name.startsWith(todayString)) {
                    } else {
                        try {
                            file.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                }

            }
        }
    }
}
