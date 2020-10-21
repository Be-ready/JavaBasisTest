package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/** 饮料父抽象类
 * @author wss
 * @created 2020/10/19 13:33
 * @since 1.0
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
