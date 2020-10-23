package com.kedacom.module_main

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.SparseArray
import com.alibaba.android.arouter.facade.annotation.Route
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteConstants.MAIN_ACTIVITY)
class MainActivity : BaseActivity() {
    private var sparseArray: SparseArray<String>? = null
    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("caowj", "44444")
        setContentView(R.layout.activity_main)
        initData()


        mainList.layoutManager = LinearLayoutManager(mActivity)
        mainList.adapter = mainAdapter
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