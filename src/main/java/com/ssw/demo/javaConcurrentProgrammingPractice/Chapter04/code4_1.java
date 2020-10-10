package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04;

/** 使用java监视器模式的线程安全计数器
 * 遵循java监视器模式的对象会把对象的所有可变状态都封装起来，并由对象自己的内置锁来保护
 * @author wss
 * @created 2020/9/18 14:11
 * @since 1.0
 */
public class code4_1 {

    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }

}
