package com.example.caowj.newtask;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kedacom.base.common.AppManager;
import com.kedacom.base.common.BaseActivity;
import com.kedacom.utils.DataList.DataList;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.ToastUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 1、通过theme设置background背景，不用加载Layout。
 * <p>
 * 2、申请危险权限：https://github.com/googlesamples/easypermissions
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
//            3秒后自动关闭APP
            ToastUtil.showLongToast(mActivity, "3秒后自动退出");
            AppManager.getAppManager().AppExit();
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
}
