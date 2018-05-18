package com.example.caowj.newtask.designPattern.factory.abstractfactory;


import com.example.caowj.newtask.designPattern.factory.abstractfactory.cpu.Cpu;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.cpu.CpuSnapDragon;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.memory.Memory;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.memory.MemoryToshiba;

/**
 * Created by tb on 2017/5/13.
 * 具体工厂类2
 */

public class Factory2 extends AbstractFactory {
    @Override
    public Cpu getCpu() {
        return new CpuSnapDragon();
    }

    @Override
    public Memory getMemory() {
        return new MemoryToshiba();
    }
}
