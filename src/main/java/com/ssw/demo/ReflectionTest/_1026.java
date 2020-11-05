package com.ssw.demo.ReflectionTest;

import java.lang.reflect.Field;

/**
 * 利用反射修改final类型的数据
 */
public class _1026 {

    static class Student{
        private String name;
        private Integer age;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
    static void set(Student s) {
        s.setName("sssss");
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "hello world!";
        Field value = String.class.getDeclaredField("value");  // String类的value是private
        value.setAccessible(true);          // 暴力访问
        char[] s_ = (char[])value.get(s);   // 获得数据
        System.out.println(s_);
        s_[5] = '_';                        // 修改数据
        System.out.println(s_);
        System.out.println(s);
        // 输出：
        // hello world!
        //hello_world!
        //hello_world!


        // 测试引用传递
        Student student = new Student();
        student.setName("李四");
        student.setAge(23);
        System.out.println(student);
        set(student);
        System.out.println(student);
    }
}
