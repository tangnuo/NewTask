package com.kedacom.module_common.network;


import com.kedacom.module_common.network.interfaces.IRequestCallback;
import com.kedacom.module_common.network.interfaces.IRequestManager;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/14 13:59
 */
public class TestSample {
    private void test() {
        //测试请求
        String url = "https://api.douban.com/v2/movie/top250";
        //这里发起请求依赖的是IRequestManager接口
        IRequestManager requestManager = RequestFactory.getRequestManager();

        requestManager.get(url, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
