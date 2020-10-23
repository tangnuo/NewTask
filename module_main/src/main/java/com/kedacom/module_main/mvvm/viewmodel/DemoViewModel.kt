package com.kedacom.module_main.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.caowj.lib_network.HttpServicesFactory2
import com.kedacom.module_main.mvvm.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 16:54
 * </pre>
 */

class DemoViewModel : BaseViewModel() {
    var data: MutableLiveData<String> = MutableLiveData<String>()

    fun lodaUser() {
        HttpServicesFactory2
                .getInstance()
                .httpServiceApi
                .getJoke(1, 2, "video")
                .enqueue(object : Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        data.value = response.body().toString()
                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        data.value = "接口报错"
                    }
                })
    }
}