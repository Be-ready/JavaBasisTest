package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/**
 * 装饰者
 *
 * @author wss
 * @created 2020/10/19 13:45
 * @since 1.0
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    /**
     * 调料Mocha的价钱+被装饰者的价钱
     * @return
     */
    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
