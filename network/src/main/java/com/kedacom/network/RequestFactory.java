package com.kedacom.network;

import com.kedacom.network.interfaces.IRequestManager;
import com.kedacom.network.okhttp.OkHttpRequestManager;

/**
 * 该类的作用是用于返回一个IRequestManager对象，这个IRequestManager的实现类
 * 可以是使用Volley实现的http请求对象，也可以是OkHttp实现的http请求对象
 * Activity/Fragment/Presenter中，只要调用getRequestManager()方法就能得到
 * http请求的操作接口，而不用关心具体是使用什么实现的。
 *
 * http://cjw-blog.net/2016/12/11/%E8%A7%A3%E8%80%A6%E6%80%9D%E8%B7%AF/
 */
public class RequestFactory {
    public static IRequestManager getRequestManager() {
//        return VolleyRequestManager.getInstance();
        return OkHttpRequestManager.getInstance();
    }
}