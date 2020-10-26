package com.kedacom.module_learn

import com.facebook.stetho.Stetho
import com.kedacom.module_common.common.BaseApplication

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/28 13:27
 */
class LearnApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}