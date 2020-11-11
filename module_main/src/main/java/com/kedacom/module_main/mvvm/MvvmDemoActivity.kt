package com.kedacom.module_main.mvvm

import android.os.Bundle
import com.kedacom.module_main.R
import com.kedacom.module_main.databinding.ActivityMvvmDemoBinding
import com.kedacom.module_main.mvvm.base.BaseMvvmActivity
import com.kedacom.module_main.mvvm.viewmodel.DemoViewModel

class MvvmDemoActivity : BaseMvvmActivity<ActivityMvvmDemoBinding>() {
    private lateinit var mViewModel: DemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = DemoViewModel()
        binding.viewModel = mViewModel
        binding.viewRef = this
        initData()
    }

    private fun initData() {
        mViewModel.data.value = ""
    }

    override fun getLayout(): Int {
        return R.layout.activity_mvvm_demo
    }

}