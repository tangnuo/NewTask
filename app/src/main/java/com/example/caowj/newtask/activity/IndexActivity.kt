package com.example.caowj.newtask.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.SparseArray
import com.example.caowj.newtask.R
import com.example.caowj.newtask.adapter.IndexAdapter
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_common.service.ServiceFactory
import com.kedacom.module_lib.base.common.BaseActivity
import com.kedacom.module_lib.utils.LogUtil
import com.kedacom.module_lib.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : BaseActivity() {
    private var sparseArray: SparseArray<String>? = null
    private var indexAdapter: IndexAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.myD("11111")
        setContentView(R.layout.activity_index)
        initData()
        val accountService = ServiceFactory.getInstance().accountService
        if (accountService.isLogin) {
            LogUtil.myD("用户已登录" + accountService.accountId)
        } else {
            ToastUtil.showShortToast(mActivity, "请先登录！")
        }

        mIndex.layoutManager = LinearLayoutManager(mActivity)
        mIndex.adapter = indexAdapter
    }

    private fun initData() {
        sparseArray = SparseArray()
        sparseArray!!.put(0, RouteConstants.LEARN_FUNCTION_LIST)
        sparseArray!!.put(1, RouteConstants.MAIN_ACTIVITY)
        indexAdapter = IndexAdapter(mActivity, sparseArray!!)
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