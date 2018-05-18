package com.example.caowj.newtask.designPattern.factory.abstractfactory.memory;

import android.util.Log;

/**
 * Created by tb on 2017/5/13.
 * 三星的内存
 */

public class MemorySamsung extends Memory {
    @Override
    public void printInfo() {
        Log.d("Memory:", "MemorySamsung");
    }
}
