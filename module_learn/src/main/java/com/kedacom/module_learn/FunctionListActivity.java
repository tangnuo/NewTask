package com.kedacom.module_learn;

import android.Manifest;
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
import com.kedacom.module_learn.activity.TestUploadActivity;
import com.kedacom.module_learn.adapter.FunctionListAdapter;
import com.kedacom.module_lib.base.common.BaseActivity;
import com.kedacom.module_lib.base.permission.PermissionControl;
import com.kedacom.module_lib.utils.ToastUtil;

@Route(path = RouteConstants.LEARN_FUNCTION_LIST)
public class FunctionListActivity extends BaseActivity {

    RecyclerView mRecyclerView;

    private SparseArray<Class> sparseArray;
    private FunctionListAdapter functionListAdapter;

    private static final String[] requirePermission = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS
    };
    private PermissionControl control;

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
    protected String[] setRequirePermission() {
        String[] requirePermission = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        return requirePermission;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);
        mRecyclerView = findViewById(R.id.mRecyclerView);

        String name = getIntent().getStringExtra("name");
        long age = getIntent().getLongExtra("age", 0);
        UserInfo userInfo = getIntent().getParcelableExtra("userInfo");
        ToastUtil.showShortToast(mActivity, "name:" + name + ",age:" + age + ",userInfo:" + userInfo == null ? userInfo.toString() : "");

        initData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(functionListAdapter);
    }


    protected void initData() {
        sparseArray = new SparseArray<>();
        sparseArray.put(0, TestSmartRefreshLayoutActivity.class);
        sparseArray.put(1, TestStethoActivity.class);
        sparseArray.put(2, TestUploadActivity.class);


        functionListAdapter = new FunctionListAdapter(mActivity, sparseArray);
    }
}

