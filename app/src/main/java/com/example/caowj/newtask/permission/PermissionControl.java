package com.example.caowj.newtask.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：chumengwei on 2018/8/6 15:28
 * <p>
 * We can do anything we want to do if we stick to it long enough.
 */
public class PermissionControl implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    public static final int PERMISSION_REQUEST_CODE = 10;
    private String[] mRequirePermission;
    private Activity mActivity;
    private PermissionSuccessListener mListener;

    public PermissionControl(Activity activity, String[] requirePermission, PermissionSuccessListener listener) {
        mRequirePermission = requirePermission;
        mActivity = activity;
        mListener = listener;
    }

    /**
     * 获取权限
     */
    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    public void getAppPermissions() {
        if (EasyPermissions.hasPermissions(mActivity, mRequirePermission)) {
            if (null != mListener) {
                mListener.onPermissionSuccess();
            }
        } else {
            EasyPermissions.requestPermissions(mActivity, "为了方便您的使用，需要打开" + getPermissionNeedDesc(mRequirePermission), PERMISSION_REQUEST_CODE, mRequirePermission);
        }
    }

    /**
     * 权限被拒绝
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //权限被永久禁止
        if (EasyPermissions.somePermissionPermanentlyDenied(mActivity, perms)) {
            new AppSettingsDialog.Builder(mActivity)
                    .setTitle("温馨提示")
                    .setRationale("为了不影响app的正常使用，请去设置页打开相关权限。")
                    .build().show();
        } else {
            //权限被暂时禁止
            String[] permsArray = new String[perms.size()];
            String permissionNeedDesc = getPermissionNeedDesc(perms.toArray(permsArray));
            EasyPermissions.requestPermissions(mActivity, "未获得" + permissionNeedDesc + "，将无法使用后续功能，是否去授予权限？", PERMISSION_REQUEST_CODE, mRequirePermission);
        }
    }

    /**
     * 权限被永久禁止是，引导去设置页打开，返回页面的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onPermissionResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Toast.makeText(mActivity, getPermissionResultDesc(), Toast.LENGTH_LONG).show();
        }
    }

    public String getPermissionNeedDesc(String[] requirePermission) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < requirePermission.length; i++) {
            builder.append(getPermissionNeedString(requirePermission[i]));
            if (i != requirePermission.length - 1) {
                builder.append("、");
            }
        }
        builder.append("权限");
        return builder.toString();
    }

    private String getPermissionResultDesc() {
        StringBuilder builder = new StringBuilder("为了方便您的使用，需要打开相关权限:");
        for (String permission : mRequirePermission) {
            builder.append("\n").append(getPermissionResultString(permission));
        }
        return builder.toString();
    }

    private String getPermissionNeedString(String permission) {
        String permissionString;
        switch (permission) {
            case Manifest.permission.CAMERA: {
                permissionString = "相机";
            }
            break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE: {
                permissionString = "写入SD卡";
            }
            break;
            case Manifest.permission.READ_EXTERNAL_STORAGE: {
                permissionString = "读取SD卡";
            }
            break;
            case Manifest.permission.READ_PHONE_STATE: {
                permissionString = "手机状态";
            }
            break;
            case Manifest.permission.READ_CONTACTS: {
                permissionString = "联系人";
            }
            break;
            default: {
                permissionString = "相关";
            }
            break;
        }
        return permissionString;
    }

    private String getPermissionResultString(String permission) {
        String permissionString;
        switch (permission) {
            case Manifest.permission.CAMERA: {
                permissionString = "相机权限:" + (PermissionUtils.hasCameraPermission(mActivity) ? "是" : "否");
            }
            break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE: {
                permissionString = "写入SD卡权限:" + (PermissionUtils.hasWritePermission(mActivity) ? "是" : "否");
            }
            break;
            case Manifest.permission.READ_EXTERNAL_STORAGE: {
                permissionString = "读取SD卡权限:" + (PermissionUtils.hasReadPermission(mActivity) ? "是" : "否");
            }
            break;
            case Manifest.permission.READ_PHONE_STATE: {
                permissionString = "手机状态权限:" + (PermissionUtils.hasPhoneStatePermission(mActivity) ? "是" : "否");
            }
            break;
            case Manifest.permission.READ_CONTACTS: {
                permissionString = "联系人权限:" + (PermissionUtils.hasContactsPermission(mActivity) ? "是" : "否");
            }
            break;
            default: {
                permissionString = "相关权限:否";
            }
            break;
        }
        return permissionString;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void onDestroy() {
        if (null != mActivity) {
            mActivity = null;
        }
        if (null != mRequirePermission) {
            mRequirePermission = null;
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
    }

    @Override
    public void onRationaleDenied(int requestCode) {
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

}
