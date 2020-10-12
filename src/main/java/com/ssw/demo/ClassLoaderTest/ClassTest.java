package com.ssw.demo.ClassLoaderTest;
/**
 * 类加载器练习
 *
 * @author wss
 * @created 2020/9/1 16:16
 * @since 1.0
 */

import java.lang.Class;
import java.lang.ref.*;

public class ClassTest {

    public static void main(String[] args) {

//        Class.forName();
        Object obj = new Object();  // 强引用（正常的引用）
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference softObj = new SoftReference(obj);  // 软引用(使用get方法获取对象)
        SoftReference softObj2 = new SoftReference(obj, queue);  // 软引用
        obj = null;  // 去除强引用
        System.out.println(obj);  // 输出null
        System.out.println(softObj.get());  // 输出java.lang.Object@2503dbd3
        System.out.println(softObj2.get()); // 输出java.lang.Object@2503dbd3

        hashEntity he = new hashEntity(1, 2);
        ReferenceQueue rfq = new ReferenceQueue();
        SoftReference srf = new SoftReference(he, rfq);
        hashEntity he2 = (hashEntity) srf.get();
        he = null;
        System.out.println(rfq.poll());         //
        System.out.println(srf.get());          // test.java.com.ssw.demo.ClassLoaderTest.hashEntity@4b67cf4d
        System.out.println(srf.getClass());     // class java.lang.ref.SoftReference
        System.out.println(he2.getI());         // 1
        System.out.println(he);                 // null

        hashEntity he3 = new hashEntity(3, 4);
        WeakReference wr = new WeakReference(he3);  // 弱引用
        System.out.println(wr.get());

        hashEntity he4 = new hashEntity(5, 6);
        PhantomReference pr = new PhantomReference(he4, queue); // 虚引用（参数需要使用引用队列）
        hashEntity he5 = (hashEntity) pr.get();  // pr.get()方法始终返回null
        System.out.println(he5);

        try {
            Class<?> h = Class.forName("test.java.com.ssw.demo.ClassLoaderTest.hashEntity");
            System.out.println(h.getClassLoader());  // sun.misc.Launcher$AppClassLoader@18b4aac2
            System.out.println(h.getName());         // test.java.com.ssw.demo.ClassLoaderTest.hashEntity
            System.out.println(h.getTypeName());     // test.java.com.ssw.demo.ClassLoaderTest.hashEntity
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
