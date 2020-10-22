package com.kedacom.module_main

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.SparseArray
import com.alibaba.android.arouter.facade.annotation.Route
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_lib.base.common.BaseActivity
import com.kedacom.module_lib.utils.LogUtil
import com.kedacom.module_main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteConstants.MAIN_ACTIVITY)
public class MainActivity : BaseActivity() {
    private var sparseArray: SparseArray<String>? = null
    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.myD("44444")
        setContentView(R.layout.activity_main)
        initData()


        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mRecyclerView.adapter = mainAdapter
    }

    private fun initData() {
        sparseArray = SparseArray()
//        sparseArray!!.put(0, RouteConstants.LEARN_FUNCTION_LIST)
//        sparseArray!!.put(1, RouteConstants.MAIN_ACTIVITY)
        mainAdapter = MainAdapter(mActivity, sparseArray!!)
    }

    companion object {
        fun newInstance(context: Context) {
            //显示Intent
//        Intent intent = new Intent(context, FunctionListActivity.class);
//        context.startActivity(intent);
        }
    }
}