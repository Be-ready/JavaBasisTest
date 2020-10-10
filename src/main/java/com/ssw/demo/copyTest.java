package com.ssw.demo;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;

/** 深拷贝和浅拷贝
 * @author wss
 * @created 2020/8/19 16:42
 * @since 1.0
 */
public class copyTest {

    public static class Person implements Cloneable, Serializable {
        private int age;
        private String name;
        private Company company;

        public Person(int age, String name, Company company) {
            this.age = age;
            this.name = name;
            this.company = company;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", company=" + company +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class Company implements Cloneable, Serializable {

        private String name;

        public Company(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Company c = new Company("fpi");
        Person p1 = new Person(21, "ssw", c);
        Person p2 = (Person) p1.clone();  // 浅拷贝
        p1.age=25;
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.equals(p2));
        // 输出：
        // Person{age=25, name='ssw', company=Company{name='fpi'}}
        // Person{age=21, name='ssw', company=Company{name='fpi'}}
        // false
        // 解释：p1和p2已经是两个不同的对象了，两者无关联

        // 深拷贝（序列化方式）
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(p1);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Person p3 = (Person) ois.readObject();
        ois.close();
        System.out.println(p1);
        System.out.println(p3);
        System.out.println(p1.equals(p3));

    }
}
