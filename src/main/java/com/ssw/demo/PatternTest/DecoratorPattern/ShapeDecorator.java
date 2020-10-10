package com.ssw.demo.PatternTest.DecoratorPattern;

/** 创建实现了Shape接口的抽象装饰类
 * @author wss
 * @created 2020/9/14 16:36
 * @since 1.0
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratorShape;

    public ShapeDecorator(Shape decoratorShape) {
        this.decoratorShape = decoratorShape;
    }

    public void draw() {
        decoratorShape.draw();
    }
}
