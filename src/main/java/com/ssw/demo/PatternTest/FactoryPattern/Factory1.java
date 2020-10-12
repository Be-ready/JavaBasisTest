package com.ssw.demo.PatternTest.FactoryPattern;

/**
 * 原始方法构造工厂类
 * 缺点：添加子类时，需要修改工厂类
 *
 * @author wss
 * @created 2020/9/21 10:11
 * @since 1.0
 */
public class Factory1 {
    public static Fruit getInstance(String fruitName) {
        Fruit f = null;
        if ("Apple".equals(fruitName)) {
            f = new Apple();
        }
        if ("Orange".equals(fruitName)) {
            f = new Orange();
        }
        return f;
    }
}
