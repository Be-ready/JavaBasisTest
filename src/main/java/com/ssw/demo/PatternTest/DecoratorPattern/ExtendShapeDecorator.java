package com.ssw.demo.PatternTest.DecoratorPattern;

/**
 * @author wss
 * @created 2020/9/14 16:38
 * @since 1.0
 */
public class ExtendShapeDecorator extends ShapeDecorator {

    public ExtendShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    public void draw() {
        decoratorShape.draw();
        setRedBorder(decoratorShape);
    }

    // 新增的功能
    private void setRedBorder(Shape shape) {
        System.out.println("Border Color: Red");
    }

}
