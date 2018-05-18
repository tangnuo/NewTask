package com.example.caowj.newtask.designPattern.strategy.mInterface.impl;

import com.example.caowj.newtask.designPattern.strategy.mInterface.MemberStrategy;

/**
 * @Dec ：中级会员折扣类
 * @Author : Caowj
 * @Date : 2018/5/18 14:26
 */
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }

}