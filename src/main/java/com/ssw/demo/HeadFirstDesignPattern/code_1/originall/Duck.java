package com.ssw.demo.HeadFirstDesignPattern.code_1.originall;

/**
 * 鸭子超类
 */
abstract class Duck {

    // 呱呱叫
    public void quark() {
        System.out.println("quark...");
    }

    // 游泳
    public void swim() {
        System.out.println("swimming...");
    }

    public void fly() {
        System.out.println("I can fly...");
    }

    // 其他行为，可被子类继承并覆盖
    abstract void display();
}
