package com.example.caowj.newtask.designPattern.factory.abstractfactory.memory;

import android.util.Log;

/**
 * Created by tb on 2017/5/13.
 * 东芝的内存
 */

public class MemoryToshiba extends Memory {
    @Override
    public void printInfo() {
        Log.d("Memory:", "MemoryToshiba");
    }
}
