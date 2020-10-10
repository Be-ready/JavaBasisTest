package com.ssw.demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author wss
 * @created 2020/8/21 9:07
 * @since 1.0
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> s = new ArrayList<>(10);
        s.size(); // 返回的是元素的长度
        System.out.println(s.size());  // 此时输出为0
        s.add("a"); // 添加
        s.add("b");
        s.add(null);  // 可以存储null值

        s.remove("a");  // 按元素删除，返回删除的旧值
        s.remove(1); // 按下标删除，返回删除的旧值

        s.get(2);  // 按下标获取元素

    }

}
