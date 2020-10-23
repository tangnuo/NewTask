package com.kedacom.module_main.mvp.model;

import com.kedacom.module_main.mvp.listener.HttpCallback;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 16:02
 * </pre>
 */

public interface IBaseModel {
    void loadUser(HttpCallback callback);
}
