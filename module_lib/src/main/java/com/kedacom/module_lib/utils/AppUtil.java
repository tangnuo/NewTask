package com.kedacom.module_lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.FilenameFilter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Dec ：APP一般工具类
 * @Author : Caowj
 * @Date : 2018/7/2 10:19
 */
public class AppUtil {
    /**
     * 程序名称
     */
    public static final String WORK_SPACE = "kd";
    public static final String DIR_SYS = "sys";
    public static final String SYSTEM_DIRECTORY_DATA = "data";
    private static final String NOMEDIA_FILENAME = ".nomedia";
    /**
     * 程序包名
     */
    public static String packageName = "";

    public static String getUploadLogZipPath(Context context) {
        File file = new File(AppUtil.getFileRootPath(context) + "/log/upload");
        if (file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /**
     * 获取崩溃日志路径
     *
     * @param context
     * @return
     */
    public static String getCrashPath(Context context) {
        String path = AppUtil.getFileRootPath(context) + "/crash/";
        return path;
    }

    public static String getDeviceMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String deviceMac_ = info.getMacAddress();

        if (deviceMac_ == null) {
            deviceMac_ = "";
        }

        return deviceMac_;
    }

    public static String getWorkSpace(Context context) {
        String projectName = WORK_SPACE;

        if (context != null) {
            projectName = context.getString(AppUtil.getResourcesIdentifier(context, "R.string.app_name"));
        }
        return projectName;
    }

    public static String getProjectPackage(Context context) {
        packageName = context.getPackageName();

        return packageName;
    }


    public static String getSysFilePath(Context context, String directory, String filename) {
        StringBuffer absolutepath = new StringBuffer();
        if (filename.startsWith("sys:")) {
            String path = filename.substring(4);
            absolutepath.append(AppUtil.getAppSysPath(context));
            absolutepath.append('/');
            absolutepath.append(path);
        } else {
            String directoryPath = getSysDirectory(context, directory);
            absolutepath.append(directoryPath);
            if (filename.length() > 0) {
                absolutepath.append('/').append(filename);
            }
        }
        String path = absolutepath.toString();
        path = path.replace("sys:", "");
        return path;
    }

    public static String getSysDirectory(Context context, String directory) {
        StringBuffer absolutepath = new StringBuffer();
        String apppath = AppUtil.getAppSysPath(context);
        absolutepath.append(apppath);

        if (directory.equalsIgnoreCase(DIR_SYS)) {
            absolutepath.append(SYSTEM_DIRECTORY_DATA).append('/').append(DIR_SYS);
        } else {
            absolutepath.append(directory);
        }
        return absolutepath.toString();
    }


    public static String getFileRootPath(Context context) {
        String fileRootPath_ = "";

        fileRootPath_ = AppUtil.getSdCardPath() + System.getProperty("file.separator") + getWorkSpace(context) + "/";

        fileRootPath_ = fileRootPath_.replace("\\", "/");
        File dc = new File(fileRootPath_);

        if (!dc.exists() && !dc.mkdirs()) {
            LogUtil.myD("getfileRootPath(): Can NOT make dirtory.");
        }

        File noMediaFile = new File(fileRootPath_ + NOMEDIA_FILENAME);
        if (!noMediaFile.exists()) {
            try {
                noMediaFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fileRootPath_;
    }

    /**
     * 获取应用固定路径
     *
     * @return
     */
    public static String getAppSysPath(Context context) {
        StringBuffer path = new StringBuffer();

        String filepath = getFileRootPath(context);
        path.append(filepath);

        return path.toString();
    }

    public static String getSdCardPath() {
        String mSdCardPath_ = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

        if ((!TextUtils.isEmpty(mSdCardPath_) && new File(mSdCardPath_).canRead())) {
            return mSdCardPath_;
        } else {
            File sdCardFile = new File(mSdCardPath_);
            File parentFile = sdCardFile.getParentFile();
            File[] files = parentFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.toLowerCase().indexOf("sd") >= 0
//							|| filename.toLowerCase().indexOf("storage") >= 0
                            ;
                }
            });
            if (files != null) {
                for (File f : files) {
                    String sdPath = f.getAbsolutePath();
                    if (sdPath != null && (!TextUtils.isEmpty(sdPath) && f.canRead())) {
                        mSdCardPath_ = sdPath;
                        return mSdCardPath_;
                    }
                }
            }
        }

        return mSdCardPath_;
    }

    /**
     * 获取SubscriberId
     *
     * @param context
     * @return
     */
    public static String getImsi(Context context) {
        TelephonyManager tmManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        /** 获得IMSI */
        String imsi_ = tmManager.getSubscriberId();

        if (imsi_ == null) {
            imsi_ = "";
        }

        return imsi_;
    }

    /**
     * 获取DeviceId
     *
     * @param context
     * @return
     */
    public static String getImeiString(Context context) {
        TelephonyManager tmManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei_ = tmManager.getDeviceId();

        return imei_;
    }


    /**
     * 获取资源id
     *
     * @param context Context
     * @param tag     资源信息 例如“R.id.bottom”
     * @return
     */
    public static int getResourcesIdentifier(Context context, String tag) {
        int code = 0;
        if (tag != null && tag.trim().length() > 0) {
            String[] sp = tag.split("\\.");
            if (sp != null && sp.length == 3) {
                code = context.getResources().getIdentifier(sp[2], sp[1], context.getApplicationInfo().packageName);
            }
        }
        return code;
    }

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5(String val) {
        MessageDigest md5;

        String newstr = new String();
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return newstr;
        }
        byte[] result = md5.digest(val.getBytes());
        for (int i = 0; i < result.length; i++) {
            byte ch = result[i];
            newstr += Hex2Chr((byte) (ch >>> 4));
            newstr += Hex2Chr(ch);
        }
        return newstr;
    }

    /**
     * 4bit binary to char 0-F(将字节转换成16进制表示的字符)
     *
     * @param digit
     * @return
     */
    public static char Hex2Chr(byte digit) {
        char[] hexDigits =
                {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
                };

        return hexDigits[digit & 0x0f];
    }

    /**
     * 获取已安装apk的版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取已安装apk的版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0.0";
        }
    }

    /**
     * 返回Uri方法的详细信息
     * <p>
     * 例如（自定义的协议）：
     * <a href="qipai://www.qipaiapp.com/weblink?Game/index.html?data=123">打开webb</a>
     * 输出：
     * uri:qipai://www.qipaiapp.com/weblink?Game/index.html?data=123
     * Authority:www.qipaiapp.com
     * Fragment:null
     * Host:www.qipaiapp.com
     * LastPathSegment:weblink
     * Path:/weblink
     * Port:-1
     * Query:Game/index.html?data=123
     * Scheme:qipai
     * SchemeSpecificPart://www.qipaiapp.com/weblink?Game/index.html?data=123
     * UserInfo:null
     *
     * @return
     */
    public static String getUriDetail(Uri uri) {
        String uriStr = "uri:" + uri;
        if (uri != null) {
            uriStr += "\n Authority:" + uri.getAuthority();
            uriStr += "\n Fragment:" + uri.getFragment();
            uriStr += "\n Host:" + uri.getHost();
            uriStr += "\n LastPathSegment:" + uri.getLastPathSegment();
            uriStr += "\n Path:" + uri.getPath();
            uriStr += "\n Port:" + uri.getPort();
            uriStr += "\n Query:" + uri.getQuery();
            uriStr += "\n Scheme:" + uri.getScheme();
            uriStr += "\n SchemeSpecificPart:" + uri.getSchemeSpecificPart();
            uriStr += "\n UserInfo:" + uri.getUserInfo();
        }
        return uriStr;
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    public static String getDeviceInfo() {
        String phoneInfo = "Product（手机制造商）: " + android.os.Build.PRODUCT;
        phoneInfo += ",\n BOARD（主板）: " + android.os.Build.BOARD;
        phoneInfo += ",\n BRAND（android系统定制商）: " + android.os.Build.BRAND;
        phoneInfo += ",\n BOOTLOADER（系统启动程序版本号：） " + android.os.Build.BOOTLOADER;
        phoneInfo += ",\n CPU_ABI（cpu指令集）: " + android.os.Build.CPU_ABI;
        phoneInfo += ",\n CPU_ABI2（cpu指令集2）: " + android.os.Build.CPU_ABI2;
        phoneInfo += ",\n DEVICE（设备参数）: " + android.os.Build.DEVICE;
        phoneInfo += ",\n DISPLAY（显示参数）: " + android.os.Build.DISPLAY;
//        phoneInfo += ",\n （无线电固件版本）: " + android.os.Build.getRadioVersion();
        phoneInfo += ",\n FINGERPRINT（硬件识别码 ）:" + android.os.Build.FINGERPRINT;
        phoneInfo += ",\n HARDWARE（硬件名称 ）: " + android.os.Build.HARDWARE;
        phoneInfo += ",\n ID（修订版本列表）: " + android.os.Build.ID;
        phoneInfo += ",\n MANUFACTURER（硬件制造商 ）: " + android.os.Build.MANUFACTURER;
        phoneInfo += ",\n MODEL（版本）: " + android.os.Build.MODEL;
        phoneInfo += ",\n SERIAL（硬件序列号）: " + android.os.Build.SERIAL;
        phoneInfo += ",\n TAGS（描述build的标签）: " + android.os.Build.TAGS;
        phoneInfo += ",\n VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ",\n SDK: " + android.os.Build.VERSION.SDK;
        phoneInfo += ",\n VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ",\n USER: " + android.os.Build.USER;
        LogUtil.myD(phoneInfo);
        return phoneInfo;
    }

    /**
     * 获取手机屏幕宽度(px)
     *
     * @param mContext
     * @return px
     */
    public static int getScreenWidth(Context mContext) {
        int width = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //        width = display.getWidth();//已过时
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        return width;
    }


    /**
     * 获取手机屏幕高度
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        int height = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        height = display.getHeight();
        return height;
    }


    /**
     * 判断是否安装了某个应用
     *
     * @param context
     * @param packageName app包名
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
