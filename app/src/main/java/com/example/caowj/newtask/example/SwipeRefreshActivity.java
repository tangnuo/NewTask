package com.example.caowj.newtask.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.adapter.SwipeAdater;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * https://github.com/jianghejie/XRecyclerView<p/>
 * 官方刷新控件+RecyclerView自动加载更多。<br/>
 * 关键点：<p/>
 * 1、设置xRecyclerView不可下拉；<br/>
 * 2、在SwipeRefreshLayout外包裹一层FrameLayout。https://github.com/jianghejie/XRecyclerView/issues/163<br/>
 */
public class SwipeRefreshActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private XRecyclerView mRecyclerView;
    private SwipeAdater mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        mRecyclerView = (XRecyclerView) this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = LayoutInflater.from(this).inflate(R.layout.item_recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);


        mRecyclerView.setPullRefreshEnabled(false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        //设置刷新时动画的颜色，可以设置4个
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }


        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();        //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        mAdapter = new SwipeAdater(listData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        refreshTime++;
        times = 0;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                listData.clear();
                for (int i = 0; i < 10; i++) {
                    listData.add("item" + i + "after " + refreshTime + " times of refresh");
                }
                mAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();
                mSwipeRefreshLayout.setRefreshing(false);
            }

        }, 1000);            //refresh data here
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        initData();
        Log.d("caowj", "mSwipeRefreshLayout is ok");
    }
}
