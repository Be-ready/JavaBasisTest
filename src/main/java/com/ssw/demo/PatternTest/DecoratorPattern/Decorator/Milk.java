package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/**
 * 调料牛奶
 * （装饰者子类有一个实例变量，记录所装饰的事物，这里是饮料父类Beverage）
 * @author wss
 * @created 2020/10/19 13:54
 * @since 1.0
 */
public class Milk extends CondimentDecorator {

    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return .30 + beverage.cost();
    }
}
