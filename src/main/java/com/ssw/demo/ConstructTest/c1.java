package com.ssw.demo.ConstructTest;

/** 无参构造函数测试
 * 如果构建了一个带有参数的构造函数但没有显示的写出无参的构造函数，这是可以的
 * 但是此时无法通过一个无参的构造函数来构建（new）时，此时编译器会报错，原因是找不到这个无参的构造函数。
 * 也就是说当一个类你没有给他构造函数，则编译器会自动补上一个无参的，若有的话就不会，你需要显示将此无参的构造函数写出来
 * @author wss
 * @created 2020/9/21 9:43
 * @since 1.0
 */
public class c1 {

    private static class Person {
        private String name;
        private int age;

        Person(String name) {
            this.name = name;
        }
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        void talk() {
            System.out.println("this is person");
        }
    }

    public static void main(String[] args) {
//        Person p = new Person();  // 无法使用无参构造函数获得类的实例
        Person p1 = new Person("ssw", 25);
        p1.talk();
    }
}
