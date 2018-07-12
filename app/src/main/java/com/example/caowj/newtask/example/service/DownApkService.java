package com.example.caowj.newtask.example.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;

import com.kedacom.utils.LogUtil;
import com.kedacom.utils.SharedPreferenceUtil;

import java.io.File;

/**
 * @Dec ：耗时操作放在在service中，通过广播监听下载进度。
 * @Author : Caowj
 * @Date : 2018/7/12 14:29
 */
public class DownApkService extends Service {
    Context context = this;
    SharedPreferenceUtil mSp;

    public DownApkService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle downloadBundle = intent.getBundleExtra("download");
        if (downloadBundle != null) {
            String downloadUrl = downloadBundle.getString("downloadUrl");
            String title = downloadBundle.getString("title");
            if (!TextUtils.isEmpty(downloadUrl)) {
                mSp = SharedPreferenceUtil.getInstance(context);
                long downloadId = downloadApk(downloadUrl, title);
                mSp.saveLong("downloadId", downloadId);
            }
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 下载apk
     *
     * @param url   网络路径
     * @param title apk名称（不包含后缀）
     * @return
     */
    private long downloadApk(String url, String title) {

        String apkName = title + ".apk";
        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/" + apkName);
        if (file != null && file.exists()) {
            boolean delFlag = file.delete();
            LogUtil.myD("完整路径2：" + file.getAbsolutePath() + "，删除状态：" + delFlag);
        } else {
            LogUtil.myW("文件不存在");
        }
        mSp.saveString("apkName", apkName);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        //设置WIFI下进行更新
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //是否允许漫游
        request.setAllowedOverRoaming(false);
        //设置类型为.apk
        request.setMimeType("application/vnd.android.package-archive");
        //下载中和下载完后都显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //使用系统默认的下载路径 此处为应用内 /android/data/packages ,所以兼容7.0
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "标题321");
        //通知栏标题
        request.setTitle("标题123");
        //通知栏描述信息
        request.setDescription("描述45678");
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        //设置文件存放路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkName);

        DownloadManager mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        return mDownloadManager.enqueue(request);
    }
}