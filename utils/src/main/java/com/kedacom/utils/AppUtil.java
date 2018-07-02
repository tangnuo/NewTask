package com.kedacom.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

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
     * 程序包名
     */
    public static String packageName = "";

    /**
     * 程序名称
     */
    public static final String WORK_SPACE = "kd";

    private static final String NOMEDIA_FILENAME = ".nomedia";

    public static final String DIR_SYS = "sys";

    public static final String SYSTEM_DIRECTORY_DATA = "data";


    public static String getUploadLogZipPath(Context context) {
        File file = new File(AppUtil.getFileRootPath(context) + "/log/upload");
        if (file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /**
     * 获取崩溃日志路径
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
            projectName = context.getString(AppUtil.getResourcesIdentifier(context, "R.string.projectname"));
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
}
