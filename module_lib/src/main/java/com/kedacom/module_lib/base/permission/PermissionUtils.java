package com.kedacom.module_lib.base.permission;

import android.Manifest;
import android.app.Activity;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：chumengwei on 2018/7/10 09:47
 * <p>
 * We can do anything we want to do if we stick to it long enough.
 */
public class PermissionUtils {

    //摄像机
    public static boolean hasCameraPermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.CAMERA);
    }

    //读取SD卡
    public static boolean hasReadPermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    //写入SD卡
    public static boolean hasWritePermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    //手机状态
    public static boolean hasPhoneStatePermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.READ_PHONE_STATE);
    }

    //定位
    public static boolean hasLocationPermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    //联系人
    public static boolean hasContactsPermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.READ_CONTACTS);
    }

    //视频、音频
    public static boolean hasAudioPermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.RECORD_AUDIO);
    }

    //wifi
    public static boolean hasWifiStatePermission(Activity activity) {
        return EasyPermissions.hasPermissions(activity, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE);
    }

}
