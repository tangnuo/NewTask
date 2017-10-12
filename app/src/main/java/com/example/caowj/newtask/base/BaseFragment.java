package com.example.caowj.newtask.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * package: com.example.caowj.newtask.base
 * author: Administrator
 * date: 2017/9/1 11:54
 */
public abstract class BaseFragment extends Fragment {
    public String mTag = this.getClass().getSimpleName() + "~~";
    protected View rootView;
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();// 获取所在的activity对象
        rootView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(mActivity, rootView);
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*********************************************************/

    protected abstract int getContentView();

    protected void initWidget() {
    }

    protected void initData() {
    }

}
