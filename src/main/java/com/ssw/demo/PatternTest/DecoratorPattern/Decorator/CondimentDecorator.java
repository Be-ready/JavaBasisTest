package com.ssw.demo.PatternTest.DecoratorPattern.Decorator;

/**
 * 调料抽象类(装饰者父类)
 *  使用继承达到装饰者和被装饰对象的类型匹配
 * 注释： CondimentDecorator是抽象子类，可以不实现父类的抽象方法
 * @author wss
 * @created 2020/10/19 13:36
 * @since 1.0
 */
public abstract class CondimentDecorator extends Beverage {

    public abstract String getDescription();  // 新建抽象方法，描述添加了何种调料

}




