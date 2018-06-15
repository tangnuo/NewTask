package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.caowj.newtask.BaseApp;
import com.example.caowj.newtask.example.mDagger.component.AppComponent;

/**
 * package: com.example.caowj.newtask.example.activity
 * author: Administrator
 * date: 2017/10/9 11:25
 */
public class BaseDaggerActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return ((BaseApp) getApplication()).getAppComponent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
