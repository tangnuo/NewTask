package com.example.caowj.newtask.base;

import android.os.Handler;
import android.os.Message;


import com.kedacom.utils.LogUtil;

import java.lang.ref.WeakReference;

/**
 * https://www.cnblogs.com/Westfalen/p/6287479.html?utm_source=itdadao&utm_medium=referral
 *
 * @Author : caowj
 * @Date : 2018/3/27
 */
public abstract class BaseHandler<T> extends Handler {
    private final String mTag = "BaseHandler~~~";

    private final WeakReference<T> weakReference;
    private boolean isOver = false;


    public BaseHandler(T referencedObject) {
        this.weakReference = new WeakReference<>(referencedObject);
        isOver = false;
    }

    protected T getWeakReference() {
        return weakReference.get();
    }


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (weakReference != null && weakReference.get() != null && !isOver) {
            //虽然这里已经做了非空判断，但是针对一些不会销毁的MainActivity还是需要加额外的判断。
            handleMessage2(msg, msg.what);
        } else {
            // 确认Activity是否不可用
            LogUtil.myW(mTag + "Activity or Fragment is gone;isOver:" + isOver);
        }
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    /**
     * 抽象方法用户实现,用来处理具体的业务逻辑
     *
     * @param msg
     * @param what {@link }
     */
    public abstract void handleMessage2(Message msg, int what);
}
