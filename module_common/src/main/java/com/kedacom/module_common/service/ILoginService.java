package com.kedacom.module_common.service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Login模块对外提供数据的接口
 */
public interface ILoginService {

    /**
     * 是否已经登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     *
     * @return
     */
    String getAccountId();


    /**
     * 创建 UserFragment
     *
     * @param activity
     * @param containerId
     * @param manager
     * @param bundle
     * @param tag
     * @return
     */
    Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag);
}
