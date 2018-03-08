package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.adapter.MyAdapter;
import com.example.caowj.newtask.example.mDagger.component.DaggerAppComponent;
import com.example.caowj.newtask.example.mDagger.module.AppModule;

import javax.inject.Inject;

/**
 * dagger2实现依赖注入
 */
public class TestDaggerActivity2 extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Inject
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_dagger2;
    }

    @Override
    protected void memoryRecovery() {

    }
}
