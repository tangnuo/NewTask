package com.kedacom.module_main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.kedacom.module_common.constant.RouteConstants;
import com.kedacom.module_common.service.ILoginService;
import com.kedacom.module_common.service.ServiceFactory;
import com.kedacom.module_lib.base.common.BaseActivity;
import com.kedacom.module_lib.utils.LogUtil;
import com.kedacom.module_lib.utils.ToastUtil;
import com.kedacom.module_main.adapter.MainAdapter;

public class MainActivity extends BaseActivity {

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

        setContentView(R.layout.activity_main);

        initData();


        ILoginService accountService = ServiceFactory.getInstance().getAccountService();

        if (accountService.isLogin()) {
            LogUtil.myD("用户已登录" + accountService.getAccountId());
        } else {
            ToastUtil.showShortToast(mActivity, "请先登录！");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(functionListAdapter);
    }


    protected void initData() {
        sparseArray = new SparseArray<>();

        sparseArray.put(0, RouteConstants.LEARN_FUNCTION_LIST);
        sparseArray.put(1, RouteConstants.NEWS_ACTIVITY);

        functionListAdapter = new MainAdapter(mActivity, sparseArray);
    }
}
