package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04.code4_6;

/**
 * 不可变的实体类
 * 1.所有成员都是private final
 * 2.不提供队成员的改变方法（setXXX,）
 * 3.确保所有的方法不会被重载{1：使用final class（强不可变类），2：将所有方法加上final（弱不可变类）}
 *
 * @author wss
 * @created 2020/9/18 14:56
 * @since 1.0
 */
public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
