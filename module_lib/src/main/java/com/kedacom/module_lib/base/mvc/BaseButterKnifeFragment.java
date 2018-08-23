package com.kedacom.module_lib.base.mvc;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
public abstract class BaseButterKnifeFragment extends Fragment {
    private static ProgressDialog mProgressDialog;
    public String mTag = this.getClass().getSimpleName() + "~~";
    public Activity mActivity;
    protected View rootView;

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

    /**
     * 显示ProgressDialog
     *
     * @param context    上下文
     * @param message    消息
     * @param cancelable 是否可以取消
     */
    public void loading(Context context, String message, boolean cancelable) {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(cancelable);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
            mProgressDialog.dismiss();
        }
        mProgressDialog.show();
    }

    /**
     * 关闭ProgressDialog
     */
    public void closeLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /*********************************************************/

    protected abstract int getContentView();

    protected void initWidget() {
    }

    protected void initData() {
    }

}
