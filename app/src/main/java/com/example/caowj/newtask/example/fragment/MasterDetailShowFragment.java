package com.example.caowj.newtask.example.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.adapter.MasterDetailShowAdapter;
import com.kedacom.base.mvc.BaseButterKnifeFragment;
import com.kedacom.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 大师--介绍
 */
public class MasterDetailShowFragment extends BaseButterKnifeFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private Activity mActivity;
    private MasterDetailShowAdapter adapter;
    private List<String> contentList = new ArrayList<>();

    public RecyclerView getmRecyclerView() {
        if (mRecyclerView == null) {
            LogUtil.myW("MasterDetailShowFragment~~~" + "mRecyclerView is null");
        }
        return mRecyclerView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_show, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MasterDetailShowAdapter(mActivity, contentList);
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.find_item_bg));
        mRecyclerView.setAdapter(adapter);

        getContents();
    }


    private void getContents() {
        contentList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            contentList.add("测试数据：" + i);
        }
        adapter.setMasterProductList(contentList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.memoryRecovery();
            adapter = null;
        }
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
        if (mActivity != null) {
            mActivity = null;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_master_show;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
