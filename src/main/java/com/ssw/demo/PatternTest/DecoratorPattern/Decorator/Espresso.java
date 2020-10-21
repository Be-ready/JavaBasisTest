package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/**
 * 浓缩咖啡(继承饮料基类)
 *  相当于具体组件
 * @author wss
 * @created 2020/10/19 13:40
 * @since 1.0
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    //
    @Override
    public double cost() {
        return 1.90;
    }
}
