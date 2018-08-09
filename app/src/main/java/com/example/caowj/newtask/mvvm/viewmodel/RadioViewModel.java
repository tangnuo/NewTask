package com.example.caowj.newtask.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.caowj.newtask.BR;


/**
 * Author：chumengwei on 2018/7/31 10:38
 * <p>
 * We can do anything we want to do if we stick to it long enough.
 */
public class RadioViewModel extends BaseObservable {

    @Bindable
    private int selectIndex = -1;

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int index) {
        if (selectIndex == index) {
            selectIndex = -1;
        } else {
            selectIndex = index;
        }
        notifyPropertyChanged(BR.selectIndex);
    }
}
