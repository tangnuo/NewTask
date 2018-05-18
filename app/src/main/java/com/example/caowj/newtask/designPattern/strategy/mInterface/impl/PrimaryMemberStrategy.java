package com.example.caowj.newtask.designPattern.strategy.mInterface.impl;

import com.example.caowj.newtask.designPattern.strategy.mInterface.MemberStrategy;

/**
 * @Dec ：初级会员折扣类
 * @Author : Caowj
 * @Date : 2018/5/18 14:24
 */
public class PrimaryMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }
}
