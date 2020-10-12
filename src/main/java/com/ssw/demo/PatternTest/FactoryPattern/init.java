package com.ssw.demo.PatternTest.FactoryPattern;

import java.io.*;
import java.util.Properties;

/**
 * 操作配置文件的类
 *
 * @author wss
 * @created 2020/9/21 10:31
 * @since 1.0
 */
public class init {
    public static Properties getPro() throws IOException {
        Properties pro = new Properties();
        File f = new File("fruit.properties");
        if (f.exists()) {
            pro.load(new FileInputStream(f));
        } else {
            pro.setProperty("apple", "com.ssw.demo.PatternTest.FactoryPattern.Apple");
            pro.setProperty("orange", "com.ssw.demo.PatternTest.FactoryPattern.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}
