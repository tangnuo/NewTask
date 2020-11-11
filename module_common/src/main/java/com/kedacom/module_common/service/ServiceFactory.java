package com.kedacom.module_common.service;

import com.kedacom.module_common.service.impl.EmptyAccountService;

/**
 * 组件间数据传递
 * <p>
 * https://juejin.im/post/5b5f17976fb9a04fa775658d
 */
public class ServiceFactory {

    private ILoginService accountService;

    /**
     * 禁止外部创建 ServiceFactory 对象
     */
    private ServiceFactory() {
    }

    /**
     * 通过静态内部类方式实现 ServiceFactory 的单例
     */
    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    /**
     * 返回 Login 组件的 Service 实例
     */
    public ILoginService getAccountService() {
        if (accountService == null) {
            accountService = new EmptyAccountService();
        }
        return accountService;
    }

    /**
     * 接收 Login 组件实现的 Service 实例
     */
    public void setAccountService(ILoginService accountService) {
        this.accountService = accountService;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }
}
