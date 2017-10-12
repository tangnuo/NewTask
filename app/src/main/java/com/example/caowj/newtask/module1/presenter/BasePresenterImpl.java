package com.example.caowj.newtask.module1.presenter;

/**
 * by y on 2016/5/27.
 */
@SuppressWarnings("ALL")
public class BasePresenterImpl<T> {

    public T view;

    public BasePresenterImpl(T view) {
        this.view = view;
    }

}
