package com.example.caowj.newtask.example.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;

import com.example.caowj.newtask.R;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.SharedPreferenceUtil;
import com.kedacom.utils.ToastUtil;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * @Dec ：apk下载监听器
 * @Author : Caowj
 * @Date : 2018/7/12 14:24
 */
public class DownApkReceiver extends BroadcastReceiver {
    SharedPreferenceUtil mSharedP;
    DownloadManager mManager;
    Context ctx;

    @Override
    public void onReceive(Context context, Intent intent) {
        ctx = context;
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
            mSharedP = SharedPreferenceUtil.getInstance(ctx);
            long saveApkId = mSharedP.getLong("downloadId", -1L);

            if (downloadApkId == saveApkId) {
                checkDownloadStatus(context, downloadApkId);
            }
        }
    }

    /**
     * 监听下载的状态和进度
     *
     * @param context
     * @param downloadId
     */
    private void checkDownloadStatus(Context context, long downloadId) {
        mManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor cursor = mManager.query(query);
        if (cursor != null && cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));

            switch (status) {
                case DownloadManager.STATUS_PENDING:
//                    等待中
                    break;
                case DownloadManager.STATUS_PAUSED:
//                    暂停了一下
                    break;
                case DownloadManager.STATUS_RUNNING:
                    LogUtil.d("DownApkReceiver", "正在下载.....");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    // TODO: 2018/7/12  在activity中安装apk
//                    installProcess();
                    ToastUtil.showShortToast(ctx, "安装存在问题，需要优化。");
                    break;
                case DownloadManager.STATUS_FAILED:
                    LogUtil.d("DownApkReceiver", "下载失败.....");
                    break;
            }

            String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
            String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
            int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            int pro = (bytes_downloaded * 100) / bytes_total;
            LogUtil.myD("下载进度监听：" + title + ",," + address + ",," + pro);
        }
    }


}
