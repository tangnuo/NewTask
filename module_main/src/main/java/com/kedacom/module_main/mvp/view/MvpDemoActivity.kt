package com.kedacom.module_main.mvp.view

import android.os.Bundle
import android.util.Log
import com.kedacom.module_common.common.BaseActivity
import com.kedacom.module_main.R
import com.kedacom.module_main.mvp.presenter.impl.DemoPresenter
import kotlinx.android.synthetic.main.activity_mvp_demo.*

class MvpDemoActivity : BaseActivity(), IBaseView<String> {
    private lateinit var presenter: DemoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp_demo)
        presenter = DemoPresenter(this)

        btn_search.setOnClickListener { onLoadData() }
    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onLoadData() {
        Log.d("caowj", "IBaseView")
        presenter.doRefresh()
    }

    override fun onRefreshData(data: String?) {
        tv_data.text = "" + data
    }

}