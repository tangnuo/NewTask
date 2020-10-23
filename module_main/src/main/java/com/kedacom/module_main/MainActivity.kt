package com.kedacom.module_main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_main.adapter.MainAdapter
import com.kedacom.module_main.bean.ItemBean
import com.kedacom.module_main.mvp.view.MvpDemoActivity
import com.kedacom.module_main.mvvm.MvvmDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteConstants.MAIN_ACTIVITY)
class MainActivity : BaseActivity() {
    private lateinit var list: ArrayList<ItemBean>
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("caowj", "44444")
        setContentView(R.layout.activity_main)
        initData()

        mainList.layoutManager = LinearLayoutManager(mActivity)
        mainList.adapter = mainAdapter
    }

    private fun initData() {
        list = ArrayList<ItemBean>()
        list.add(ItemBean("MvpDemo", MvpDemoActivity::class.java))
        list.add(ItemBean("MvvmDemo", MvvmDemoActivity::class.java))
        mainAdapter = MainAdapter(mActivity, list)
    }

    companion object {
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}