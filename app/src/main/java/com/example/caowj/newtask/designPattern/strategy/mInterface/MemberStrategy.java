package com.example.caowj.newtask.designPattern.strategy.mInterface;

/**
 * @Dec ：会员策略接口
 * @Author : Caowj
 * @Date : 2018/5/18 14:23
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param booksPrice 图书的原价
     * @return 计算出打折后的价格
     */
    public double calcPrice(double booksPrice);
}
