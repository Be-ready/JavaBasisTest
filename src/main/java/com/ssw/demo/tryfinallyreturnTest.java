package com.ssw.demo;

/**
 * @author wss
 * @created 2020/8/18 9:27
 * @since 1.0
 */
public class tryfinallyreturnTest {

    public static int test() {
        int x = 1;
        try {
            System.out.println("this is try");
            x++;
            return x;

        } finally {
            x++;
            System.out.println("this is finally");
            return x;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}

// 解析：
// 1、当try中有return时，代码执行顺序如下：
//      1).如果有返回值，就把返回值保存到局部变量中
//      2).执行jsr指令跳到finally语句里执行
//      3).执行完finally语句后，返回之前保存在局部变量表里的值
// 2、当try和finally中都有return时，会忽略try中的return,使用finally中的return
// 所以返回值是3