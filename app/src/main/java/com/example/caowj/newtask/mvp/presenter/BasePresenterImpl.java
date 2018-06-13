package com.example.caowj.newtask.mvp.presenter;

/**
 * by y on 2016/5/27.
 */
@SuppressWarnings("ALL")
public class BasePresenterImpl<T> {
    protected final String mTag = getClass().getSimpleName() + "~~~";

    public T presenterImpl;

    public BasePresenterImpl(T view) {
        this.presenterImpl = view;
    }

}
