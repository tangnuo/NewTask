package com.example.caowj.newtask.example.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.service.DownApkService;
import com.kedacom.base.common.BaseActivity;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.SharedPreferenceUtil;
import com.kedacom.utils.ToastUtil;

import java.io.File;

/**
 * DownloadManager 下载 （不推荐使用系统默认的下载管理器）
 * <p>
 * 1、首先需要配置android6.0,7.0,8.0的权限
 * <p>
 * https://blog.csdn.net/u012209506/article/details/56012744 (使用handler + timer，可以在页面中显示下载进度)
 * <p>
 * https://www.cnblogs.com/liyiran/p/6393813.html （使用Service，只能在通知栏显示下载进度）
 */
public class TestDownloadManagerActivity extends BaseActivity implements View.OnClickListener {
    private TextView down;
    public static String downloadUrl = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";
    long id;
    private SharedPreferenceUtil mSharedP;
    DownloadManager mManager;

//    private ServiceConnection conn = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            DownApkService.MyBinder binder = (DownApkService.MyBinder) service;
//            DownApkService bindService = binder.getService();
//            long id = bindService.getRadom();
//        }
//
//        //client 和service连接意外丢失时，会调用该方法
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//        }
//
//    };

    /**
     * 下载完成的广播（系统默认）
     */
    private BroadcastReceiver downloadSuccessReceiver;
    /**
     * 下载时，点击通知栏的广播（系统默认）
     */
    private BroadcastReceiver clickNotifacationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        down = findViewById(R.id.down);
        down.setOnClickListener(this);
        mSharedP = SharedPreferenceUtil.getInstance(mActivity);

        // TODO: 2018/7/12 需要先判断是否具有读写权限（先去权限页面申请权限，然后再回来下载文件）
//        startActivity(new Intent(this, TestPremissionActivity.class));

        downloadSuccessReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
                mSharedP = SharedPreferenceUtil.getInstance(mActivity);
                long saveApkId = mSharedP.getLong("downloadId", -1L);

                if (downloadApkId == saveApkId) {
                    //下载完成，自动调用安装应用
                    checkDownloadStatus(context, downloadApkId);
                }
            }
        };


        clickNotifacationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //点击跳转到下载查看页面
                Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(viewDownloadIntent);
            }
        };
        mActivity.registerReceiver(downloadSuccessReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        mActivity.registerReceiver(clickNotifacationReceiver, new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    @Override
    public void onClick(View v) {
        if (canDownloadState(mActivity)) {
            LogUtil.d("UpdateVersion", "DownloadManager 可用");
            Intent downloadApkIntent = new Intent(mActivity, DownApkService.class);
            Bundle bundle = new Bundle();
            bundle.putString("downloadUrl", downloadUrl);
            //文件名不可以使用汉字，否则出现：“解析软件包时出现问题”
            bundle.putString("title", "apkName_caowj");
            downloadApkIntent.putExtra("download", bundle);
            mActivity.startService(downloadApkIntent);
//            可以使用bind广播的形式代替SharedPreference获取id
//            mActivity.bindService(downloadApkIntent, conn, Context.BIND_AUTO_CREATE);
        } else {
            LogUtil.d("UpdateVersion", "DownloadManager 不可用");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(downloadUrl);
            intent.setData(content_url);
            mActivity.startActivity(intent);
        }

        down.setEnabled(false);
        down.setText("下载中，请等待。");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(downloadSuccessReceiver);
        mActivity.unregisterReceiver(clickNotifacationReceiver);
    }

    /**
     * 手机是否支持默认的下载管理器；如果不支持，就跳转浏览器下载。
     * <p>
     * https://www.cnblogs.com/liyiran/p/6393813.html
     *
     * @param mActivity
     * @return
     */
    private boolean canDownloadState(Context mActivity) {
        try {
            int state = mActivity.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10086) {
            installProcess();//再次执行安装流程，包含权限判等
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
                    installProcess();
                    break;
                case DownloadManager.STATUS_FAILED:
                    LogUtil.myD("下载失败.....");
                    break;
            }
        }
    }

    /**
     * 强制安装应用程序
     */
    private void installProcess() {
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            haveInstallPermission = mActivity.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {//没有权限
                // 创建构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                // 设置参数
                builder.setTitle("提示").setIcon(R.drawable.ic_launcher)
                        .setMessage("安装应用需要打开未知来源权限，请去设置中开启权限")
                        .setPositiveButton("美", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //注意这个是8.0新API
                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                                mActivity.startActivityForResult(intent, 10086);
                            }
                        });
                builder.create().show();
            } else {
                //有权限，开始安装应用程序
                installApk();
            }
        } else {
            installApk();
        }
    }

    /**
     * 安装应用
     */
    private void installApk() {
        String apkName = mSharedP.getString("apkName");
        if (apkName != null) {
            LogUtil.d("DownApkReceiver", "存放路径：" + Environment.DIRECTORY_DOWNLOADS + "，apkName：" + apkName);
            File apkFile = mActivity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/" + apkName);

            if (apkFile != null) {
                LogUtil.myD("完整路径1：" + apkFile.getAbsolutePath());
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                } else {
                    //Android7.0之后获取uri要用contentProvider
                    Uri uri = FileProvider.getUriForFile(mActivity, "com.example.caowj.newtask.fileprovider", apkFile);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
            } else {
                LogUtil.d("DownApkReceiver", "下载失败，或者文件不存在");
            }
        } else {
            LogUtil.d("DownApkReceiver", "apkName 为 null");
        }
    }
}

