package com.kedacom.module_lib.base.mvvm.listener;

import android.view.View;

/**
 * Copyright ©2017 by ruzhan
 */

public interface OnItemClickListener<T> {

    void onItemClick(View view, int position, T bean);
}
