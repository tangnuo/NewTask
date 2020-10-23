package com.kedacom.module_main.mvp.listener;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 16:19
 * </pre>
 */

public interface HttpCallback {
    void onSuccess(String data);

    void onError(String message);
}
