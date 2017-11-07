package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.adapter.ViewPagerAdapter;
import com.example.caowj.newtask.example.fragment.StickHeaderFragment;

/**
 * 仿饿了么标题悬浮
 * <p>
 * <p/>
 * 使用折叠控件：https://github.com/cachapa/ExpandableLayout
 */
public class ElemTabActivity extends BaseActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        toolbar.setTitle("美食");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addItem(new StickHeaderFragment(), "Tab1");
        viewPagerAdapter.addItem(new StickHeaderFragment(), "Tab2");
        viewPagerAdapter.addItem(new StickHeaderFragment(), "Tab3");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_elem_tab;
    }

    @Override
    protected void memoryRecovery() {

    }
}
