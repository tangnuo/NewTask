package com.kedacom.module_learn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kedacom.module_common.bean.main.UserInfo;
import com.kedacom.module_common.constant.RouteConstants;
import com.kedacom.module_learn.activity.TestSmartRefreshLayoutActivity;
import com.kedacom.module_learn.activity.TestStethoActivity;
import com.kedacom.module_learn.adapter.FunctionListAdapter;
import com.kedacom.module_lib.base.mvc.BaseButterKnifeActivity;
import com.kedacom.module_lib.utils.LogUtil;
import com.kedacom.module_lib.utils.ToastUtil;

import butterknife.BindView;

@Route(path = RouteConstants.LEARN_FUNCTION_LIST)
public class FunctionListActivity extends BaseButterKnifeActivity {

    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SparseArray<Class> sparseArray;
    private FunctionListAdapter functionListAdapter;

    public static void newInstance(Context context) {
        //显示Intent
        Intent intent = new Intent(context, FunctionListActivity.class);
        context.startActivity(intent);

//        隐式Intent
//        String mAction = context.getPackageName() + ".FunctionList";
//        Intent intent = new Intent(mAction);
//        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String name = getIntent().getStringExtra("name");
        long age = getIntent().getLongExtra("age", 0);
        UserInfo userInfo = getIntent().getParcelableExtra("userInfo");
        ToastUtil.showShortToast(mActivity, "name:" + name + ",age:" + age + ",userInfo:" + userInfo.toString());

        mRecyclerView.setAdapter(functionListAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_function_list;
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
        sparseArray.put(0, TestSmartRefreshLayoutActivity.class);
        sparseArray.put(1, TestStethoActivity.class);


        functionListAdapter = new FunctionListAdapter(mActivity, sparseArray);
    }

    @Override
    protected void memoryRecovery() {

    }
}

