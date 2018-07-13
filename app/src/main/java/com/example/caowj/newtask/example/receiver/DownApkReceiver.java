package com.example.caowj.newtask.example.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.kedacom.utils.LogUtil;
import com.kedacom.utils.SharedPreferenceUtil;
import com.kedacom.utils.ToastUtil;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * @Dec ：主要实现点击事件和下载完成事件的响应；即未完成时点击跳转到下载查看页面，完成后自动调用安装应用。
 * <p>
 * 如果手动监听了下载进度，可以不使用这个广播接收器。
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
                //下载完成，自动调用安装应用
                checkDownloadStatus(context, downloadApkId);
            }
        } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            //点击跳转到下载查看页面
            Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
            viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(viewDownloadIntent);
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
            String downId = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
            String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
            int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            int pro = (bytes_downloaded * 100) / bytes_total;
            LogUtil.myD("下载进度监听：" + downId + "，，" + title + ",," + address + ",," + pro);

            switch (status) {
                case DownloadManager.STATUS_PENDING:
                    LogUtil.myD("等待中");
                    break;
                case DownloadManager.STATUS_PAUSED:
                    LogUtil.myD("暂停了一下");
                    break;
                case DownloadManager.STATUS_RUNNING:
                    LogUtil.myD("正在下载.....");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    // TODO: 2018/7/12  在activity中安装apk
//                    installProcess();
                    ToastUtil.showShortToast(ctx, "安装存在问题，需要优化。");
                    break;
                case DownloadManager.STATUS_FAILED:
                    LogUtil.myD("下载失败.....");
                    break;
            }

        }
    }
}
