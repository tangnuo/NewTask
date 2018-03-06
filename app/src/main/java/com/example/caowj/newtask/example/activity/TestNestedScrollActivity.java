package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * 嵌套滚动
 * </p>
 */
public class TestNestedScrollActivity extends BaseActivity {
    List<String> mData;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.ll_sc_content)
    LinearLayout llScContent;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(1);
        initView2();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_nested_scroll;
    }


    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    private void initView2() {
        //设置ToolBar
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back_icon);
        setSupportActionBar(toolbar);//替换系统的actionBar

        //设置TabLayout
        for (int i = 1; i < 20; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }
        //TabLayout的切换监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initData(tab.getPosition() + 1);
                setScrollViewContent();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setScrollViewContent();
    }

    @Override
    protected void memoryRecovery() {

    }

    /**
     * 刷新ScrollView的内容
     */
    private void setScrollViewContent() {
        //NestedScrollView下的LinearLayout
        llScContent.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            View view = View.inflate(mActivity, R.layout.common_layout_textview, null);
            ((TextView) view.findViewById(R.id.tv_info)).setText(mData.get(i));
            //动态添加 子View
            llScContent.addView(view, i);
        }
    }
}
