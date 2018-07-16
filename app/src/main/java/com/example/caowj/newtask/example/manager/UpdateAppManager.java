package com.example.caowj.newtask.example.manager;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.caowj.newtask.utils.DownloadFileUtil;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.NetWorkUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * app更新管理
 */
public class UpdateAppManager {

    private static final String TAG = "UpdateAppManager";

    private static final int RESULT_DOWNLOADING = 1;
    private static final int RESULT_SUCCEED = 2;
    private static final int RESULT_FAILED = 3;

    private static final String BUNDLE_PROGRESS = "BUNDLE_PROGRESS";
    private static final String BUNDLE_TOTAL = "BUNDLE_TOTAL";


    private static final long INITIAL_DELAY = 0;//延迟执行
    private static final long PERIOD = 2;//2S执行一次

    private static final long READ_TIME_OUT = 30 * 1000;//下载状态中要是30s无下载将会退出程序
    private static final long COUNTDOWN_INTERVAL = 1 * 1000;//倒计时1s间隔

    private Context mContext;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private DownloadManager.Query query;

    private long downloadId;
    private ScheduledExecutorService scheduledExecutorService;

    private long currentSize;//当前已下载值
    private long totalSize;//文件总大小
    private int status;//当前下载状态

    private ResultHandler resultHandler;
    private OnDownloadResultListener onDownloadResultListener;
    private boolean isStartCountdown;//false:未开启倒计时，true:开启倒计时


    public UpdateAppManager(Context context) {
        this.mContext = context;
        resultHandler = new ResultHandler();
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();//用于轮询查询当前下载信息
    }


    public static UpdateAppManager getInstance(Context context) {
        return new UpdateAppManager(context);
    }

    public UpdateAppManager downloading() {
        configurationDownload();
        return this;
    }

    public UpdateAppManager setOnDownloadResultListener(OnDownloadResultListener onDownloadResultListener) {
        this.onDownloadResultListener = onDownloadResultListener;
        return this;
    }


    /**
     * 配置下载
     */
    private void configurationDownload() {
        request = new DownloadManager.Request(Uri.parse("http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk"));
        //设置WIFI下进行更新
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //是否允许漫游
        request.setAllowedOverRoaming(false);
        //设置类型为.apk
        request.setMimeType("application/vnd.android.package-archive");
        //下载中和下载完后都显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //通知栏标题
        request.setTitle("标题123");
        //通知栏描述信息
        request.setDescription("描述45678");

//        String filePath = Environment.DIRECTORY_DOWNLOADS;
//
//        //方法一：使用应用内路径（相对路径，内部自动拼接） /android/data/packages ,所以兼容7.0（Android -> data -> com.app -> files -> Download -> dxtj.apk）
//        //取路径的时候，使用Context#getExternalFilesDir(String)
//        request.setDestinationInExternalFilesDir(this, filePath, apkName);
//
//        //方法二：自定义文件路径（必须是完整路径）
//        filePath = Environment.getExternalStorageDirectory() + "/NewTask/download/";
//        request.setDestinationUri(Uri.parse(filePath));
//
//        //方法三：使用系统默认下载路径（（相对路径，内部自动拼接）），不同厂家，不同room，存在问题。设置文件存放路径( SD卡--> Download文件夹)
//        //创建目录
//        Environment.getExternalStoragePublicDirectory(filePath).mkdir();
//        //取路径的时候，使用Environment#getExternalStoragePublicDirectory(String)
//        request.setDestinationInExternalPublicDir(filePath, apkName);

        request = DownloadFileUtil.setDestinationByState(mContext, request);
        downloadId = downloadManager.enqueue(request);//进入下载，获取当前下载的ID

        scheduledExecutorService.scheduleAtFixedRate(progressRunnable, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);//轮询查询下载进度
    }


    /**
     * 下载结果处理
     */
    private class ResultHandler extends Handler {

        public ResultHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_DOWNLOADING:
                    if (onDownloadResultListener != null) {
                        Bundle bundle = msg.getData();
                        onDownloadResultListener.onDownloading(bundle.getLong(BUNDLE_PROGRESS), bundle.getLong(BUNDLE_TOTAL));
                    }
                    break;
                case RESULT_SUCCEED:
                    if (onDownloadResultListener != null) {
                        onDownloadResultListener.onSucceed((Uri) msg.obj);
                    }
                    clear();
                    break;
                case RESULT_FAILED:
                    if (onDownloadResultListener != null) {
                        onDownloadResultListener.onFailed((String) msg.obj);
                    }
                    clear();
                    break;
            }
        }
    }

    /**
     * 内存回收
     */
    private void clear() {
        if (resultHandler != null) {
            resultHandler.removeCallbacksAndMessages(null);
        }
        mContext = null;
    }

    /**
     * 在主线程中执行下载结果回调
     */
    public interface OnDownloadResultListener {
        /**
         * 正在下载中
         *
         * @param progress  当前已下载进度
         * @param totalSize 文件总大小
         */
        void onDownloading(long progress, long totalSize);

        /**
         * 下载成功
         *
         * @param uri 文件保存路径信息
         */
        void onSucceed(Uri uri);

        /**
         * 下载失败
         *
         * @param error ： 未知的错误；断网；网址错误等
         */
        void onFailed(String error);
    }


    /**
     * 查询下载状态
     */
    private void queryDownloadInfo() {
        query = new DownloadManager.Query().setFilterById(downloadId);//过滤查询下载信息
        Cursor cursor = null;
        try {
            cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                currentSize = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                totalSize = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                //根据状态值传递信息
                showStatus(status);
            }
        } catch (IllegalArgumentException e) {
            Message message = resultHandler.obtainMessage();
            message.what = RESULT_FAILED;
            message.obj = e.getMessage();
            resultHandler.sendMessage(message);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 根据状态值处理相应逻辑
     *
     * @param status
     */
    private void showStatus(int status) {
        Message message = resultHandler.obtainMessage();
        switch (status) {
            case DownloadManager.STATUS_PENDING://准备下载
            case DownloadManager.STATUS_RUNNING://下载中

                if (currentSize < 0) {
                    currentSize = 0;
                }
                if (totalSize < 0) {
                    totalSize = 0;
                }
                if (currentSize > 0 && totalSize > 0) {
                    message.what = RESULT_DOWNLOADING;
                    Bundle bundle = new Bundle();
                    bundle.putLong(BUNDLE_PROGRESS, currentSize);
                    bundle.putLong(BUNDLE_TOTAL, totalSize);
                    message.setData(bundle);
                    resultHandler.sendMessage(message);
                    if (isStartCountdown) {
                        countDownTimer.cancel();
                        isStartCountdown = false;
                    }
                } else {
                    if (!isStartCountdown) {//表示未开启倒计时则开启
                        LogUtil.d(TAG, "已开启下载倒计时");
                        countDownTimer.start();
                        isStartCountdown = true;
                    }
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                //下载暂停,可能由于网络原因。

                message.what = RESULT_FAILED;
                close();
                removeDownloadTask();
                message.obj = NetWorkUtil.isNetworkConnected(mContext) ? "暂无网络，已取消下载" : "其他因素导致暂停下载";
                resultHandler.sendMessage(message);
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                //下载成功

                close();
                message.what = RESULT_SUCCEED;
                //不采用这种路径（）
                message.obj = downloadManager.getUriForDownloadedFile(downloadId);
                resultHandler.sendMessage(message);
                break;
            case DownloadManager.STATUS_FAILED:
                //下载失败

                close();
                removeDownloadTask();
                message.what = RESULT_FAILED;
                message.obj = "下载失败";
                resultHandler.sendMessage(message);
                break;
            default://未知的下载错误

                close();
                removeDownloadTask();
                message.what = RESULT_FAILED;
                message.obj = "未知的下载错误";
                resultHandler.sendMessage(message);
                break;
        }
    }


    private CountDownTimer countDownTimer = new CountDownTimer(READ_TIME_OUT, COUNTDOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            //倒计时处理
        }

        @Override
        public void onFinish() {
            //倒计时结束
            close();
            removeDownloadTask();
            Message message = resultHandler.obtainMessage();
            message.what = RESULT_FAILED;
            message.obj = "下载失败";
            resultHandler.sendMessage(message);
        }
    };


    /**
     * 子线程中轮询下载进度等信息
     */
    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            queryDownloadInfo();
        }
    };

    /**
     * 关闭轮询
     */
    private void close() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }
    }

    /**
     * 移除并删除下载任务
     */
    private void removeDownloadTask() {
        downloadManager.remove(downloadId);
    }
}
