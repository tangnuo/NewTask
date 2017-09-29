package com.example.caowj.newtask.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

import butterknife.BindView;

/**
 * 底部导航栏<br/>
 * https://segmentfault.com/a/1190000007697941
 */
public class BottomNavigationActivity extends BaseActivity {

    @BindView(R.id.nav_tv)
    TextView navTv;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        navTv.setText(R.string.item_home);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_home:
                        navTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_location:
                        navTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_like:
                        navTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_me:
                        navTv.setText(item.getTitle());
                        break;
                }
//                switchFragmentText(text);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void memoryRecovery() {

    }

}
