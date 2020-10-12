package com.ssw.demo.MapSetListTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author wss
 * @created 2020/9/11 15:05
 * @since 1.0
 */
public class MapTest {

    private static int MAXIMUM_CAPACITY = 1 << 30;

    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;  // >>>:无符号右移
        n |= n >>> 2;  // |= 按位或（示例：1|=2 = 3,即0001|=0010 = 0011）
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void t() {

        Map map = new HashMap();
        String s = "s";
        Class<?> c = s.getClass();
        System.out.println(c.getGenericInterfaces());  // [Ljava.lang.reflect.Type;@50134894
        System.out.println(c.getGenericInterfaces().length);  // 3
        System.out.println(tableSizeFor(5));  // 输出 8
        System.out.println(tableSizeFor(10));  // 输出 16

        System.out.println(1 | 3);  // | 按位或（输出为3，0001 | 0011 = 0011）
        System.out.println(1 & 3);  // & 按位与（输出为1，0001 & 0011 = 0001）
        System.out.println(1 ^ 3);  // ^ 异或  （输出为2，0001 ^ 0011 = 0010） 相同得0，不同得1
    }

    /**
     * 打断点，了解HashMap的插入操作
     */
    @Test
    public void t1() {

        Map map = new HashMap();
        map.put("a", "test1");
        map.put("a", "test2");
        map.put("b", "test3");
        System.out.println(map.entrySet());  // [a=test2, b=test3]

        // 删除key "a"
        map.remove("a");
        System.out.println(map.entrySet());  // [b=test3]
    }

    /**
     * 二、hashMap迭代删除
     * 1. 迭代器
     * 2. java8中Collection新添加的removeIf()方法
     */
    @Test
    public void t2() {
        Map map = new HashMap();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 1);
        map.put("e", 2);
        map.put("f", 3);

        // 方式1
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        Map.Entry<String, Integer> entry;
        while (it.hasNext()) {
            entry = it.next();
            if (Integer.parseInt(entry.getKey()) == 2) {  // 按指定条件删除（过滤）
                it.remove();
            }
        }
        System.out.println(map);

        // 方式2
        // 方式2.1 （removeIf()和lambda表达式结合）
//        map.entrySet().removeIf(entry -> entry.getValue() % 2 == 0); // 出错

        // 方式2.2 removeIf()
        map.entrySet().removeIf(new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean test(Map.Entry<String, Integer> o) {
                return o.getValue() % 2 == 0;  // 按指定条件过滤
            }
        });
        System.out.println(map);
    }

    /**
     * String to Integer 方法：
     * 1. Integer.valueOf(string).intValue()
     * 2. Integer.parseInt(string)
     */
    @Test
    public void t3() {
        System.out.println(Integer.valueOf("2").intValue());
        System.out.println(Integer.parseInt("2"));
    }
}
