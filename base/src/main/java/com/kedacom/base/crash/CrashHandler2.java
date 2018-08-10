package com.kedacom.base.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.kedacom.utils.SPUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程未捕获异常处理器，用来处理未捕获异常
 * <p>
 * 在application中初始化
 * <p>
 * CrashHandler2 crashHandler = CrashHandler2.getInstance();
 * crashHandler.init(getApplicationContext());
 *
 * @deprecated 存在问题
 */
public class CrashHandler2 implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashLog/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".log";

    private static CrashHandler2 sInstance = new CrashHandler2();
    private UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler2() {
    }

    public static CrashHandler2 getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        if (!_handleException(ex) && mDefaultCrashHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            // 退出程序,注释下面的重启启动程序代码
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);

            // TODO: 2018/7/6 需要结合缓存信息，跳转到主页。
            int a = SPUtil.getInstance(mContext).getInt("retry", 0);
            if (SPUtil.getInstance(mContext).getInt("retry", 0) < 3) {
                SPUtil.getInstance(mContext).saveInt("retry", SPUtil.getInstance(mContext).getInt("retry", 0) + 1);
//                Intent intent = new Intent();
//                intent.setClass(mContext, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
                Log.w(TAG, "需要跳转页面。");
            } else {
                SPUtil.getInstance(mContext).saveInt("retry", 0);
            }


            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean _handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 使用 Toast 来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                // TODO: 2018/7/6 需要结合application获取context，给出错误提示 。
//                Toast.makeText(MApplication.getContext(), "很抱歉，程序出现异常，即将重启", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            //导出异常信息到SD卡中
            _dumpExceptionToSDCard(ex);
            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
            _uploadExceptionToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 记录异常信息
     *
     * @param ex
     * @return
     * @throws IOException
     */
    private void _dumpExceptionToSDCard(Throwable ex) throws IOException {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(current));
        File file = new File(PATH, FILE_NAME + time + FILE_NAME_SUFFIX);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            _dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    /**
     * 获取手机信息
     *
     * @param pw
     * @throws NameNotFoundException
     */
    private void _dumpPhoneInfo(PrintWriter pw) throws NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);
        //android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);
        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void _uploadExceptionToServer() {
    }

}
