package com.example.caowj.newtask.designPattern.factory.abstractfactory.cpu;

import android.util.Log;

/**
 * Created by tb on 2017/5/13.
 * 高通的cpu
 */

public class CpuSnapDragon extends Cpu {
    @Override
    public void printInfo() {
        Log.d("CPU:", "CpuSnapDragon");
    }
}
