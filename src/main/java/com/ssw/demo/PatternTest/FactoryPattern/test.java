package com.ssw.demo.PatternTest.FactoryPattern;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wss
 * @created 2020/9/21 10:13
 * @since 1.0
 */
public class test {
    public static void main(String[] args) throws IOException {

        // 调用原始工厂类
        Fruit f = Factory1.getInstance("Orange");
        f.eat();       // 输出：eat orange

        // 调用使用反射创建的工厂类
        Fruit f2 = Factory2.getInstance("com.ssw.demo.PatternTest.FactoryPattern.Apple");
        if (f2 != null) {
            f2.eat();  // 输出：eat apple
        }

        // 调用使用反射和配置文件创建的工厂类
        Properties pro = init.getPro();
        Fruit f3 = Factory3.getInstance((String) pro.get("apple"));
        if (f3 != null) {
            f3.eat();  // 输出：eat apple
        }
    }
}
