package com.ssw.demo.HeadFirstDesignPattern.code_1.originall;

/**
 * @author wss
 * @created 2020/9/21 15:03
 * @since 1.0
 */
public class t {
    public static void main(String[] args) {

        // 使用继承超类并覆盖相应方法实现不同功能的方式
        Duck mallardDuck = new MallardDuck();
        Duck redHeadDuck = new RedHeadDuck();
        mallardDuck.display();
        redHeadDuck.display();

        //
        Duck rubberDuck = new RubberDuck();  // 橡皮鸭实例（不会飞，但是继承Duck超类后，变成会飞行了）
        rubberDuck.fly();
        rubberDuck.quark();
        rubberDuck.display();

        // 将超类Duck中的fly()方法修改为能覆盖
    }
}
