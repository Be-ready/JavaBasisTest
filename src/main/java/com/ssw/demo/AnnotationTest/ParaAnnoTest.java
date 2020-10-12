package com.ssw.demo.AnnotationTest;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 通过反射获取自定义注解
 *
 * @author wss
 * @created 2020/9/3 10:00
 * @since 1.0
 */
public class ParaAnnoTest {

    @MyFiled(description = "用户名", length = 12)
    private String userName;

    @MyAnno2(operation = "构造函数", description = "为userName赋值")
    public void test(String userName) {
        this.userName = userName;
        System.out.println(this.userName);
    }

    @Test
    public void testMyFiled() {

        Class c = ParaAnnoTest.class;
        for (Field f : c.getDeclaredFields()) {
            if (f.isAnnotationPresent(MyFiled.class)) {
                MyFiled anno = f.getAnnotation(MyFiled.class);
                System.out.println("字段:{" + f.getName() + "}, 描述:{" + anno.description()
                        + "},长度:{" + anno.length() + "}");
            }
        }
    }

    @Test
    public void testMyAnno2() {
        test("丽丽");
        Class c = MyAnno2.class;

        // 没起作用，
        for (Field f : c.getDeclaredFields()) {
            if (f.isAnnotationPresent(MyAnno2.class)) {
                MyAnno2 anno = f.getAnnotation(MyAnno2.class);
                System.out.println(f.getName());
                System.out.println(anno.description());
                System.out.println(anno.operation());
            }

        }
    }
}
