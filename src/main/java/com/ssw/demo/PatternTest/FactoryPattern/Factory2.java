package com.ssw.demo.PatternTest.FactoryPattern;

import java.util.Properties;

/** 使用反射创建工厂类
 * @author wss
 * @created 2020/9/21 10:16
 * @since 1.0
 */
public class Factory2 {

    public static Fruit getInstance(String className) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
