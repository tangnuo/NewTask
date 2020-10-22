package com.kedacom.module_main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.SparseArray
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_common.service.ServiceFactory
import com.kedacom.module_lib.base.common.BaseActivity
import com.kedacom.module_lib.utils.LogUtil
import com.kedacom.module_lib.utils.ToastUtil
import com.kedacom.module_main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var sparseArray: SparseArray<String>? = null
    private var functionListAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.myD("44444")
        setContentView(R.layout.activity_main)
        initData()
        val accountService = ServiceFactory.getInstance().accountService
        if (accountService.isLogin) {
            LogUtil.myD("用户已登录" + accountService.accountId)
        } else {
            ToastUtil.showShortToast(mActivity, "请先登录！")
        }

        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mRecyclerView.adapter = functionListAdapter
    }

    private fun initData() {
        sparseArray = SparseArray()
        sparseArray!!.put(0, RouteConstants.LEARN_FUNCTION_LIST)
        sparseArray!!.put(1, RouteConstants.NEWS_ACTIVITY)
        functionListAdapter = MainAdapter(mActivity, sparseArray!!)
    }

    companion object {
        fun newInstance(context: Context) {
            //显示Intent
//        Intent intent = new Intent(context, FunctionListActivity.class);
//        context.startActivity(intent);

//        隐式Intent
            val mAction = context.packageName + ".FunctionList"
            val intent = Intent(mAction)
            context.startActivity(intent)
        }
    }
}