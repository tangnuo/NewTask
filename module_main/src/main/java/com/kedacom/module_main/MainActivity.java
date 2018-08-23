package com.kedacom.module_main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.kedacom.module_lib.base.mvc.BaseButterKnifeActivity;
import com.kedacom.module_common.constant.RouteConstants;
import com.kedacom.module_lib.utils.LogUtil;
import com.kedacom.module_main.adapter.MainAdapter;

import butterknife.BindView;

public class MainActivity extends BaseButterKnifeActivity {

    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SparseArray<String> sparseArray;
    private MainAdapter functionListAdapter;

    public static void newInstance(Context context) {
        //显示Intent
//        Intent intent = new Intent(context, FunctionListActivity.class);
//        context.startActivity(intent);

//        隐式Intent
        String mAction = context.getPackageName() + ".FunctionList";
        Intent intent = new Intent(mAction);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.myD("44444");

        mRecyclerView.setAdapter(functionListAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        LogUtil.myD("222222");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    /**
     *
     */
    @Override
    protected void initData() {
        super.initData();
        sparseArray = new SparseArray<>();

        sparseArray.put(0, RouteConstants.LEARN_FUNCTION_LIST);
        sparseArray.put(1, RouteConstants.NEWS_ACTIVITY);

        functionListAdapter = new MainAdapter(mActivity, sparseArray);
    }

    @Override
    protected void memoryRecovery() {

    }
}
