package com.example.caowj.newtask.example.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.utils.business.MyAndroidUtils;

/**
 * Android 6.0 动态权限申请
 * <br/>
 * https://blog.csdn.net/xietansheng/article/details/54315674
 *
 * 危险权限列表：https://developer.android.google.cn/guide/topics/permissions/overview#permission-groups
 */
public class TestPremissionActivity extends AppCompatActivity {

    /**
     * 需要android6.0(23)以上处理的权限
     */
//    private String[] PERMISSIONS = {
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.READ_PHONE_STATE};

    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);
        TextView textView = findViewById(android.R.id.text1);
        textView.setText("Android 6.0 动态权限申请");


        // android系统大于等于6.0时需要处理时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestNeedPermission();
        } else {
            successToast();
        }
    }

    private void successToast() {
        MyAndroidUtils.showLongToast(this, "已经拥有这些权限了");
    }

    /**
     * 判断是否需要进行权限的请求
     */
    private void requestNeedPermission() {
        boolean temp = false;
        for (String permission : PERMISSIONS) {
            if (!hasPermissionGranted(permission)) {
                temp = true;
                break;
            }
        }
        if (temp) {
            //请求权限
            ActivityCompat.requestPermissions(this, PERMISSIONS, 0);
        } else {
            successToast();
        }
    }

    /**
     * 判断该权限是否已经授权
     *
     * @param permission
     * @return
     */
    private boolean hasPermissionGranted(String permission) {
        //检查权限
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            boolean temp = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "拒绝" + grantResults[i] + "权限会导致定位失败！", Toast.LENGTH_LONG).show();
                    temp = true;
                    break;
                }
            }

            if (temp) {
                //此处可以优化（给出提示信息，或手动授权等。。。）
                requestNeedPermission();
            } else {
                successToast();
            }
        }
    }
}
