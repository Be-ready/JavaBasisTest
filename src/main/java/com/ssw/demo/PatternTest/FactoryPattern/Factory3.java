package com.ssw.demo.PatternTest.FactoryPattern;

/** 反射和配置文件结合创建工厂类
 * @author wss
 * @created 2020/9/21 10:29
 * @since 1.0
 */
public class Factory3 {
    public static Fruit getInstance(String classname) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(classname).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
