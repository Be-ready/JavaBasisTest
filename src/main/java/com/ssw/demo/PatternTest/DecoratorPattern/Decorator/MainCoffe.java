package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/**
 * @author wss
 * @created 2020/10/19 13:50
 * @since 1.0
 */
public class MainCoffe {

    public static void main(String[] args) {
        System.out.println(1.90+0.20+0.30);
        Beverage beverage = new Espresso();  // 点一杯浓缩咖啡(不需要调料)
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Mocha(beverage);  // 添加调料Mocha
        beverage = new Milk(beverage);   // 添加调料Milk
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }
}
