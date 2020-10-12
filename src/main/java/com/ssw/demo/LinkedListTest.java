package com.ssw.demo;

import java.util.LinkedList;

/**
 * @author wss
 * @created 2020/8/21 10:27
 * @since 1.0
 */
public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList<String> s = new LinkedList<>();
        s.add("s1");
        s.add(1, "s2");  // 按下标添加时，下标与当前下标的差值不能超过1，否则报错
        // 报错示例如下：
        // s.add(4,"s4"); // 当前下标为1，欲添加下标4，会报错
        for (String s_ : s) {
            System.out.print(s_ + " ");
        }
    }

}
