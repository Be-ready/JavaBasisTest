package com.ssw.demo.ReflectionTest;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author wss
 * @created 2020/10/12 10:57
 * @since 1.0
 */
public class reflectionTest {

    // 反射需要掌握6个单词
    // Class        类
    // Constructor  构造
    // Method       方法
    // Filed        字段
    // instance     实例
    // invoke       执行

    /**
     * 运行时获得某个类的真正类型（子类还是父类）
     */
    public void getClassRealType() {
        Vo v = new Vo1();
        Class c = v.getClass();  // 猜想一下，此时c指的是Vo还是Vo1
        System.out.println("结果：" + c);  // 结果：class com.ssw.demo.ReflectionTest.Vo1
        // 解释：
        // 当Java创建某个类的对象，比如Vo1类对象时，Java会检查内存中是否有相应的Class对象。
        // 如果内存中没有相应的Class对象，那么Java会在.class文件中寻找Vo1类的定义，并加载Vo1类的Class对象。
        //
        //一旦Class对象加载成功，就可以用它来创建这种类型的所有对象。这也就是说，每个对象在运行时都会有对应的Class对象，
        // 这个Class对象包含了这个对象的类型信息。因此，我们能够通过Class对象知道某个对象“真正”的类型，并不会因为向上转型而丢失。

    }

    /**
     * Class获得方式
     */
    public void getClassM() {

        // 通过类型
        Class c = Vo.class;
        System.out.println("第一种方式：" + c);

        // 通过实例对象
        Vo v = new Vo();
        Class<? extends Vo> c1 = v.getClass();
        System.out.println("第二种方式：" + c1);

        // 通过全限定类名
        try {
            Class<?> c2 = Class.forName("com.ssw.demo.ReflectionTest.Vo");
            System.out.println("第三种方式：" + c2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 通过配置文件+反射  pro.txt
        Properties p = new Properties();
        try {
            p.load(new FileReader("src/main/resources/pro.txt"));
            String className = p.getProperty("className");
            Class<?> c3 = Class.forName(className);
            System.out.println("第四种方式：" + c3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过Class获得对象的无参构造方法，从而获得对象的实例
     */
    private void getContruction() {
        Class<Vo> c = Vo.class;     // 获得Class
        Constructor<Vo> constructor = null;
        try {
            constructor = c.getConstructor();  // 获得构造方法
            System.out.println("获得构造 -- 没有形参 " + constructor);
            Vo v = constructor.newInstance();  // 根据构造方法获得对象实例
            System.out.println("实例对象，没有实参 " + v);

            // 简化版
            Vo v1 = Vo.class.newInstance();
            System.out.println("简化版 " + v1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 有参构造函数，并实例化
     */
    private void getContructionWithPara() {
        Class c = Vo.class;
        Constructor constructor = null;
        try {
            constructor = c.getConstructor(String.class, String.class);
            System.out.println("获得构造 -- 有形参 " + constructor);
            Vo v = (Vo) constructor.newInstance("张三", "2020-10-12 11:16");  // 根据构造方法获得对象实例
            System.out.println("实例对象，有实参 " + v);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得方法
     * public     getMethod()
     * private    getDeclaredMethod()； setAccessible(true)暴力访问
     * main方法    getMethod() main方法本质还是一个public方法
     */
    private void getMethod() {
        Class c = Vo.class;
        try {
            Object o = c.newInstance();
            System.out.println("通过无参构造函数创建实例 " + o);

            // 公有方法（public）
            Method m = c.getMethod("setName", String.class);
            System.out.println("通过Class.getMethod方法获得实例的公有方法（setName） " + m);
            Object setMethodReturn = m.invoke(o, "李四");
            System.out.println("公有方法：set方法的返回值 " + setMethodReturn);  // void类型方法返回值为null

            // 私有方法(private)
            Method privateMethod = c.getDeclaredMethod("test");
            System.out.println("暴力反射私有方法 test " + privateMethod);
            privateMethod.setAccessible(true);  // 暴力访问，不加会报错：
            // java.lang.IllegalAccessException:
            //  Class com.ssw.demo.ReflectionTest.reflectionTest can not access a member of class com.ssw.demo.ReflectionTest.Vo with modifiers "private"
            //	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
            Object privateReturn = privateMethod.invoke(o);
            System.out.println("私有方法的返回值：" + privateReturn);

            // main方法
            Method mainMethod = c.getMethod("main", String[].class);
            Object mainReturn = mainMethod.invoke(null, (Object) new String[]{"你好"});
            System.out.println("main方法的返回值：" + mainReturn);  // main方法的类型为void，返回值为null

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得字段
     * public     getField()
     * private    getDeclaredField()
     */
    private void getFiled() {
        Class c = Vo.class;
        try {
            Object obj = c.newInstance();

            // 共有字段
            Field publicField = c.getField("testField");
            System.out.println("公有字段的字段名：" + publicField);

            publicField.set(obj, "努力学习Java"); // 为该字段赋值
            Object publicReturn = publicField.get(obj);
            System.out.println("公有字段返回值：" + publicReturn);

            // 私有字段
            Field privateField = c.getDeclaredField("name");
            System.out.println("私有字段的字段名：" + privateField);
            privateField.setAccessible(true);  // 暴力访问
            publicField.set(obj, "为私有字段赋值"); // 为该字段赋值
            Object privateReturn = publicField.get(obj);
            System.out.println("公有字段返回值：" + privateReturn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射运行配置文件的内容
     */
    private void getFromFile() {
        Properties pro = new Properties();
        try {
            pro.load(new FileReader("src/main/resources/pro.txt"));
            Class c = Class.forName(pro.getProperty("className"));
            Method m = c.getDeclaredMethod(pro.getProperty("methodName"));
            m.setAccessible(true);
            Object returnV = m.invoke(c.newInstance());
            System.out.println(returnV);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reflectionTest t = new reflectionTest();
//        t.getClassRealType();
        t.getClassM();
//        t.getContruction();
//        t.getContructionWithPara();
//        t.getMethod();
//        t.getFiled();
//        t.getFromFile();
    }
}
