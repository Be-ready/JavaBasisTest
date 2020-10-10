package com.ssw.demo.PatternTest.DecoratorPattern;

/**
 * @author wss
 * @created 2020/9/14 16:34
 * @since 1.0
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
