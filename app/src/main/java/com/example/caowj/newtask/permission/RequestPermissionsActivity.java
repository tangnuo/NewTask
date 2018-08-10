package com.example.caowj.newtask.permission;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.caowj.newtask.MainActivity;
import com.example.caowj.newtask.R;
import com.kedacom.base.common.AppManager;
import com.kedacom.base.common.BaseActivity;
import com.kedacom.utils.AlertDialogUtil;
import com.kedacom.utils.DataList.DataList;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.ToastUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 申请危险权限：https://github.com/googlesamples/easypermissions
 *
 * @deprecated 进一步的权限封装参考 {@link PermissionActivity}
 */
public class RequestPermissionsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks {

    /**
     * 需要android6.0(23)以上处理的权限
     * <p>
     * https://developer.android.google.cn/guide/topics/permissions/overview#permission-groups
     */
    private String[] PERMISSIONS = DataList.getDangerousPermissionsByGroup(9);
    private static final int RC_CAMERA_PERM = 123;
    /**
     * 倒计时定时器
     */
    private TimeCount timeCount;
    /**
     * 倒计时弹窗
     */
    private AlertDialog alertDialog;
    private long millisInFuture = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.common_layout_textview);
        // android系统大于等于6.0时需要处理时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestNeedPermission();
        } else {
            LogUtil.myD("低版本无需考虑危险权限");
        }

        timeCount = new TimeCount(millisInFuture, 1000);// 构造CountDownTimer对象
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void requestNeedPermission() {
        if (hasPermission()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "APP需要你开启权限",
                    RC_CAMERA_PERM,
                    PERMISSIONS);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        LogUtil.myD("onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        LogUtil.myD("onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
//            6秒后自动关闭APP
            timeCount.start();
            alertDialog = AlertDialogUtil.getMaterialDialog(mActivity, false, null, millisInFuture / 1000 + "秒后将退出APP", null, null, null, null);
            alertDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            LogUtil.myD("在用户从应用设置界面返回后，做一些事情，比如显示一个吐司。");

            ToastUtil.showShortToast(mActivity, "从设置页面返回了");
            // Do something after user returned from app settings screen, like showing a Toast.
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        LogUtil.myD("onRationaleAccepted:" + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        LogUtil.myD("onRationaleDenied:" + requestCode);
    }

    /**
     * 是否拥有了这些权限
     *
     * @return
     */
    private boolean hasPermission() {
        return EasyPermissions.hasPermissions(this, PERMISSIONS);
    }

    /**
     * 倒计时定时器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {
            // 计时完毕时触发
            if (alertDialog != null) {
                alertDialog.dismiss();
                alertDialog = null;
            }
            AppManager.getAppManager().AppExit();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            if (alertDialog != null) {
                alertDialog.setMessage(millisUntilFinished / 1000 + "秒后将退出APP");
            }
        }
    }
}
