package com.kedacom.base.mvvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kedacom.base.R;
import com.kedacom.base.mvvm.helper.LoadMoreHelper;
import com.kedacom.base.mvvm.viewmodel.BaseListViewModel;

/**
 * https://github.com/ruzhan123/awaker/blob/master/app/src/main/java/com/future/awaker/base
 * Copyright ©2017 by ruzhan
 */

public class BaseListFragment3<VB extends ViewDataBinding> extends BaseFragment3<VB>
        implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView recyclerView;
    protected BaseListViewModel listViewModel;

    protected boolean isStopRefresh;
    protected boolean isStopLoadMore;

    protected boolean isRefresh;

    @Override
    protected int getLayout() {
        return R.layout.frag_base_list;
    }

    protected void initData() {

    }

    public void setListViewModel(BaseListViewModel viewModel) {
        listViewModel = viewModel;
        addRunStatusChangeCallBack(listViewModel);
    }

    @Override
    protected void onCreateBindView() {
        swipeRefresh = findViewById(binding.getRoot(), R.id.swipe_refresh);
        recyclerView = findViewById(binding.getRoot(), R.id.recycler_view);

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (LoadMoreHelper.isLoadMore(recyclerView, newState)) {
                    onLoadMore();
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
    }

    @Override
    public void onRefresh() {
        if (listViewModel == null) {
            return;
        }
        if (isStopRefresh) {
            listViewModel.refreshing.set(false);
            return;
        }
        isRefresh = true;
        listViewModel.fetchData(isRefresh);
    }

    public void onLoadMore() {
        if (isStopLoadMore || listViewModel == null) {
            return;
        }
        isRefresh = false;
        listViewModel.fetchData(isRefresh);
    }

    @Override
    protected void runStatusChange(boolean isRunning) {
        if (listViewModel != null &&
                !listViewModel.isRunning.get()) { // 网络请求结束隐藏下拉刷新
            listViewModel.refreshing.set(false);
        }
    }
}