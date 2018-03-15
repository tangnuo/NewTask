package com.example.caowj.newtask.module1.model.impl;

import android.nfc.Tag;

/**
 * package: com.example.caowj.newtask.module1.model.impl
 * author: Administrator
 * date: 2017/10/18 10:47
 */
public class BaseModelImpl<T> {
    protected final String mTag = getClass().getSimpleName() + "~~~";

    public T modelImpl;

    public BaseModelImpl(T modelImpl) {
        this.modelImpl = modelImpl;
    }
}
