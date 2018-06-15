package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.adapter.HoveringAdapter;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.customview.headerScrollView.HeaderScrollHelper;
import com.kedacom.customview.headerScrollView.HeaderScrollView;
import com.kedacom.utils.DensityUtil;

/**
 * 微博详情页（标题置顶），HeaderScrollView+RecyclerView。
 * <br/>
 * 博客：https://blog.csdn.net/colinandroid/article/details/72770863
 * <br/>
 * 源码：https://github.com/colinNaive/RecyclerViewScrollView
 */
public class TestWeiboDetailActivity extends BaseButterKnifeActivity implements HeaderScrollHelper.ScrollableContainer {

    private RecyclerView recyclerView;
    private HeaderScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scrollView = (HeaderScrollView) findViewById(R.id.view_hover);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        scrollView.setCurrentScrollableContainer(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置recyclerview的高度为屏幕高度-状态栏高度-header高度
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.getHeightInPx(this) - DensityUtil.getBarHeight(this) - DensityUtil.dip2px(this, 55)));
        final String[] data = {"header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer"};
        HoveringAdapter adapter = new HoveringAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_weibo_detail;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    public View getScrollableView() {
        return recyclerView;
    }
}
