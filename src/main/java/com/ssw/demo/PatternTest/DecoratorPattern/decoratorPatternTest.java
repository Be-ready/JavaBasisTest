package com.ssw.demo.PatternTest.DecoratorPattern;

/**
 * 装饰器模式练习
 * 装饰器模式专注与对被装饰对象附加额外功能(在不修改原先对象核心的功能的情况下，对功能进行增强)
 * <p>
 * 假设我去买咖啡，首先服务员给我冲了一杯原味咖啡，我希望服务员给我加些牛奶和白糖混合入原味咖啡中。使用装饰器模式就可以解决这个问题。
 *
 * @author wss
 * @created 2020/9/14 16:10
 * @since 1.0
 */
public class decoratorPatternTest {

    public static void main(String[] args) {
        Shape circle = new Circle();

        // 使用ExtendShapeDecorator来装饰Shape对象
        ShapeDecorator redCircle = new ExtendShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new ExtendShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }

}
