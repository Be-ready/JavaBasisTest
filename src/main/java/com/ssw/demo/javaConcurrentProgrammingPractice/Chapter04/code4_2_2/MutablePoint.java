package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04.code4_2_2;

/**
 * 车辆跟踪示例的记录车辆坐标的实体类
 *
 * @author wss
 * @created 2020/9/18 14:28
 * @since 1.0
 */
public class MutablePoint {
    private int x;
    private int y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint m) {
        this.x = m.getX();
        this.y = m.getY();
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }
}
