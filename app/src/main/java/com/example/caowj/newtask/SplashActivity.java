package com.example.caowj.newtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.caowj.newtask.permission.RequestPermissionsActivity;

/**
 * 通过theme设置background背景，不用加载Layout。
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, RequestPermissionsActivity.class);
        startActivity(intent);
        finish();
    }
}
