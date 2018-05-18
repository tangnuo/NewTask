package com.example.caowj.newtask.designPattern.strategy.mInterface.impl;

import com.example.caowj.newtask.designPattern.strategy.mInterface.MemberStrategy;

/**
 * @Dec ：高级会员折扣类
 * @Author : Caowj
 * @Date : 2018/5/18 14:27
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}
