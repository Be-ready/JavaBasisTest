package com.ssw.demo.TransientTest;

import java.io.*;

/**
 * @author wss
 * @created 2020/9/2 11:10
 * @since 1.0
 */
public class transientTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Rectangle re = new Rectangle(3, 4);
        System.out.println("原始对象：" + re);

        // 将对象写入流
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("rectangle"));
        o.writeObject(re);
        o.close();

        // 从流中读对象
        ObjectInputStream i = new ObjectInputStream(new FileInputStream("rectangle"));
        Rectangle re2 = (Rectangle) i.readObject();
        System.out.println("序列化后的对象：" + re2);
        re2.setArea();
        System.out.println("恢复后的对象：" + re2);

        // 输出如下：（由于area字段被transient关键字修饰，不能被序列化）
//        原始对象：Rectangle{width=3, heighth=4, area=12}
//        序列化后的对象：Rectangle{width=3, heighth=4, area=null}
//        恢复后的对象：Rectangle{width=3, heighth=4, area=12}
    }
}
