package com.example.caowj.newtask.designPattern.factory.abstractfactory;


import com.example.caowj.newtask.designPattern.factory.abstractfactory.cpu.Cpu;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.cpu.CpuHelio;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.memory.Memory;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.memory.MemorySamsung;

/**
 * Created by tb on 2017/5/13.
 * 具体工厂类1
 */

public class Factory1 extends AbstractFactory {
    @Override
    public Cpu getCpu() {
        return new CpuHelio();
    }

    @Override
    public Memory getMemory() {
        return new MemorySamsung();
    }
}
