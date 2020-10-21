package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/** 相当于具体组件
 * @author wss
 * @created 2020/10/19 13:43
 * @since 1.0
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
