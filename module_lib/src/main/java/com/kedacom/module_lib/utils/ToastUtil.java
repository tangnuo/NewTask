package com.kedacom.module_lib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 管理toast的类，整个app用该类来显示toast
 * Created by niuxiaowei on 16/3/22.
 */
public class ToastUtil {
    static Toast toast;

    /**
     * 使用getApplicationContext（）防止内存泄漏<br/>
     * http://blog.csdn.net/u011781834/article/details/42553249<br/>
     *
     * @param mContext
     * @param text
     */
    public static void showShortToast(Context mContext, String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void showLongToast(Context mContext, String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
