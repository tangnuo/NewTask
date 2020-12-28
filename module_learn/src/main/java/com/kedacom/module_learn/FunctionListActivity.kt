package com.kedacom.module_learn

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kedacom.module_common.bean.ItemBean
import com.kedacom.module_common.bean.main.UserInfo
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_common.constant.RouteConstants
import com.kedacom.module_common.utils.ToastUtil
import com.kedacom.module_learn.activity.*
import com.kedacom.module_learn.adapter.FunctionListAdapter
import com.kedacom.module_learn.binder.NoAidlActivity
import kotlinx.android.synthetic.main.activity_function_list.*
import java.util.*

@Route(path = RouteConstants.LEARN_FUNCTION_LIST)
class FunctionListActivity : BaseActivity() {
    private var sparseArray: ArrayList<ItemBean>? = null
    private var functionListAdapter: FunctionListAdapter? = null
    override fun setRequirePermission(): Array<String> {
        return arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_list)
        val name = intent.getStringExtra("name")
        val age = intent.getLongExtra("age", 0)
        val userInfo: UserInfo? = intent.getParcelableExtra("userInfo")
        ToastUtil.showShortToast(mActivity, if ("name:$name,age:$age,userInfo:$userInfo" == null) userInfo.toString() else "")
        initData()
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mRecyclerView.adapter = functionListAdapter
    }

    private fun initData() {
        sparseArray = ArrayList()
        sparseArray!!.add(ItemBean("SmartRefresh刷新控件", TestSmartRefreshLayoutActivity::class.java))
        sparseArray!!.add(ItemBean("Stetho调试工具", TestStethoActivity::class.java))
        sparseArray!!.add(ItemBean("Retrofit2上传图片", TestUploadActivity::class.java))
        sparseArray!!.add(ItemBean("Binder浅析", NoAidlActivity::class.java))
        sparseArray!!.add(ItemBean("实时模糊搜索", SearchActivity::class.java))
        sparseArray!!.add(ItemBean("Android 10 作用域存储", ScopedStorageActivity::class.java))
        sparseArray!!.add(ItemBean("Android 10 沙盒文件保存，读取", ScopedStorageActivity2::class.java))
        sparseArray!!.add(ItemBean("RecyclerView + PagerSnapHelper", PagerSnapHelperActivity::class.java))
        sparseArray!!.add(ItemBean("基于UDP协议发送数据", TestUdpActivity::class.java))
        functionListAdapter = FunctionListAdapter(mActivity, sparseArray)
    }

    companion object {
        private val requirePermission = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS
        )

        fun newInstance(context: Context) {
            //显示Intent
            val intent = Intent(context, FunctionListActivity::class.java)
            context.startActivity(intent)

//        隐式Intent
//        String mAction = context.getPackageName() + ".FunctionList";
//        Intent intent = new Intent(mAction);
//        context.startActivity(intent);
        }
    }
}