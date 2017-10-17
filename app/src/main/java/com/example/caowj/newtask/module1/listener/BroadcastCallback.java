package com.example.caowj.newtask.module1.listener;


/**
 * 广播回调方法
 * package: com.jsfengling.qipai.myInterface
 * author: caowj
 * date: 2016/12/23 11:21
 */
public interface BroadcastCallback {
    /**
     * code:1001
     */
    void return1001();

    /**
     * code:1002
     */
    void return1002();

    /**
     * 其他code值
     *
     * @param code 其他code值
     */
    void returnOther(String code);
}
