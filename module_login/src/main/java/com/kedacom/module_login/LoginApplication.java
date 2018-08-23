package com.kedacom.module_login;

import com.kedacom.module_common.service.ServiceFactory;
import com.kedacom.module_lib.base.common.BaseApplication;
import com.kedacom.module_login.impl.LoginServiceImpl;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/23 13:29
 */
public class LoginApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 将 LoginServiceImpl 类的实例注册到 ServiceFactory
        ServiceFactory.getInstance().setAccountService(new LoginServiceImpl());
    }
}
