package com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone;

import android.util.Log;

/**
 * Created by tb on 2017/5/13.
 * 以手机为我们的产品
 */

public abstract class Phone {
    private static final String TAG = "Phone";

    /**
     * @param type 手机型号
     */
    public Phone(String type) {
        printPhoneType(type);
    }

    private void printPhoneType(String type) {
        Log.d(TAG, "printPhoneType: " + type);
    }
}
