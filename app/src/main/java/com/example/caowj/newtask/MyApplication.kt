package com.example.caowj.newtask

import com.alibaba.android.arouter.launcher.ARouter
import com.kedacom.module_lib.base.common.BaseApplication

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 13:34
 */
open class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //因为只有在集成模式下才会用到ARouter，所以放在APP—module中。
        if (BuildConfig.isARouter) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}