package com.ssw.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 8-2
 *
 * @author wss
 * @created 2020/8/14 10:39
 * @since 1.0
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    // 使用CountDownLatch
//    public static void test(String[] args) throws InterruptedException {
//
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("1");
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                c.countDown();  // 该子线程执行完毕后，计数器减一
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("2");
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                c.countDown();  // 该子线程执行完毕后，计数器减一
//            }
//        });
//        t1.start();
//        t2.start();
//        c.await();  // 主线程挂起，待子线程全部执行完毕（此时计数器的count为0）后，再执行
//        System.out.println("3");  // 执行主线程
//
//        //输出如下
//        //1
//        //2
//        //3
//        // 解析：主线程main会等待子线程（t1,t2）执行完毕再执行
//    }

    // 使用join
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
            }
        });
        t1.start();
        t2.start();
        t1.join();  // join会判断当前线程是否存活（调用isAlive()方法），即是否执行完毕，
        t2.join();
        System.out.println("3");

        //输出如下
        //1
        //2
        //3
        // 解析：主线程main会等待子线程（t1,t2）执行完毕再执行
    }

}
