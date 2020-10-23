package com.kedacom.module_common.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kedacom.module_common.permission.PermissionControl;


/**
 * package: com.example.caowj.newtask.base
 * author: Administrator
 * date: 2017/9/1 11:50
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static String[] requirePermission = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS
    };
    public String mTag = this.getClass().getSimpleName() + "~~";
    public Activity mActivity;
    private PermissionControl control;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Title显示当前类名
        this.setTitle(mTag);
        mActivity = this;

        if (setRequirePermission().length > 1) {
            requirePermission = setRequirePermission();
        }
        control = new PermissionControl(this, requirePermission, () -> Log.e("CMW", "has permission"));
        control.getAppPermissions();

    }

    /**
     * 自定义需要申请的权限（默认全部权限）
     *
     * @return
     */
    protected String[] setRequirePermission() {
        return new String[0];
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.onPermissionResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        control.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        control.onDestroy();
    }

}

