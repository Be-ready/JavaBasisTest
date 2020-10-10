package com.ssw.demo.PatternTest.DecoratorPattern;

/**
 * @author wss
 * @created 2020/9/14 16:35
 * @since 1.0
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
