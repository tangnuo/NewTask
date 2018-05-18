package com.example.caowj.newtask.designPattern.strategy;

import com.example.caowj.newtask.designPattern.strategy.mInterface.MemberStrategy;

/**
 * @Dec ：价格类
 * @Author : Caowj
 * @Date : 2018/5/18 14:21
 * https://blog.csdn.net/zhangliangzi/article/details/52161211
 */
public class Price {
    //持有一个具体的策略对象
    private MemberStrategy strategy;

    /**
     * 构造函数，传入一个具体的策略对象
     *
     * @param strategy 具体的策略对象
     */
    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 计算图书的价格
     *
     * @param booksPrice 图书的原价
     * @return 计算出打折后的价格
     */
    public double quote(double booksPrice) {
        return this.strategy.calcPrice(booksPrice);
    }
}
