package com.ssw.demo.ReflectionTest;

/**
 * @author wss
 * @created 2020/10/12 10:58
 * @since 1.0
 */
public class Vo {
    public String testField;
    private String name;
    private String time;

    public Vo() {
    }

    public Vo(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String test() {
        return "this is private method!";
    }

    @Override
    public String toString() {
        return "Vo{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("this is main, this is para " + args[0]);
    }
}
