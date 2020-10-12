package com.ssw.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wss
 * @created 2020/8/17 14:01
 * @since 1.0
 */
public class ThreadLocalTest2 {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final ThreadLocal<SimpleDateFormat> sdf2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
//            return super.initialValue();
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) {

        // 可代替传统方式
        // 示例：
        new Thread(new Runnable() {  // 该处使用的是匿名内部方法？
            @Override
            public void run() {
                System.out.println("this is a thread by classic method");
            }
        }).start();
        // lambda方式
        new Thread(() -> {
            System.out.println("this is a thread by using lambda expression");
        }).start();

        ThreadLocal<String> local = new ThreadLocal<>();
        Random random = new Random();
        // -> java8的lambda表达式
        // 示例：(parameters) -> expression或者(parameters) -> { statements; }
        IntStream.range(0, 5).forEach(a -> new Thread(() -> {
            local.set(a + "  " + random.nextInt(10));
            System.out.println("线程和local值分别是 " + local.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

    }
}
