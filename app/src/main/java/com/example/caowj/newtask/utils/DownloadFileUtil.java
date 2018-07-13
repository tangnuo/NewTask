package com.example.caowj.newtask.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * @Dec ：根据state状态，统一设置下载文件保存的路径（保存和获取保持一致）。
 * @Author : Caowj
 * @Date : 2018/7/13 13:19
 */
public class DownloadFileUtil {
    /**
     * 1：系统默认下载路径（SD卡--> Download文件夹）
     * <p>
     * 2：应用内路径（ /android/data/packages ,所以兼容7.0（Android -> data -> com.app -> files -> Download -> dxtj.apk）
     * <p>
     * 3：自定义任意路径
     */
    private static int state = 3;

    /**
     * apk名称
     * <p>
     * 注意：文件名不可以使用汉字，否则出现：“解析软件包时出现问题”
     */
    private static String apkName = "Test-caowj.apk";
    /**
     * 自定义文件下载路径
     */
    private static String apkPathByCustom = Environment.getExternalStorageDirectory() + "/NewTask/download/" + apkName;

    /**
     * 设置文件下载路径（根据设定的状态，设置对应的下载目录）
     *
     * @param mContext
     * @param request
     * @return
     */
    public static DownloadManager.Request setDestinationByState(Context mContext, DownloadManager.Request request) {
        if (request == null) {
            return null;
        }

        switch (state) {
            case 1:
                String filePath = Environment.DIRECTORY_DOWNLOADS;
                //方法三：使用系统默认下载路径（（相对路径，内部自动拼接）），不同厂家，不同room，存在问题。设置文件存放路径( SD卡--> Download文件夹)
                //创建目录
                Environment.getExternalStoragePublicDirectory(filePath).mkdir();
                //取路径的时候，使用Environment#getExternalStoragePublicDirectory(String)
                request.setDestinationInExternalPublicDir(filePath, apkName);
                break;
            case 2:
                filePath = Environment.DIRECTORY_DOWNLOADS;
                //方法一：使用应用内路径（相对路径，内部自动拼接） /android/data/packages ,所以兼容7.0（Android -> data -> com.app -> files -> Download -> dxtj.apk）
                //取路径的时候，使用Context#getExternalFilesDir(String)
                request.setDestinationInExternalFilesDir(mContext, filePath, apkName);
                break;
            case 3:
                //方法二：自定义文件路径（必须是完整路径）
                request.setDestinationUri(Uri.fromFile(new File(apkPathByCustom)));
                break;
        }
        return request;
    }

    /**
     * 获取下载文件的完整路径
     * <p>
     * 1、file:///storage/emulated/0/Download/Test-caowj.apk
     * <p>
     * 2、file:///storage/emulated/0/Android/data/com.example.caowj.newtask/files/Download/Test-caowj.apk
     * <p>
     * 3、/storage/emulated/0/NewTask/download/Test-caowj.apk
     *
     * @param mContext
     * @return
     */
    public static File getDownloadFilePath(Context mContext) {
        File apkFile = null;
        switch (state) {
            case 1:
                apkFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/" + apkName);
                break;
            case 2:
                apkFile = mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/" + apkName);
                break;
            case 3:
                //方法二：自定义文件路径（必须是完整路径）
                apkFile = new File(apkPathByCustom);
                break;
        }
        return apkFile;
    }
}
