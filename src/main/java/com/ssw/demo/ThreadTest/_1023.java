package com.ssw.demo.ThreadTest;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程顺序执行
 */
public class _1023 {

    static class _1023_1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println("this is _1023_1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class _1023_2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10);
                System.out.println("this is _1023_2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class _1023_3 implements Runnable {
        CountDownLatch cd1;
        CountDownLatch cd2;

        public _1023_3(CountDownLatch cd1, CountDownLatch cd2) {
            super();
            this.cd1 = cd1;
            this.cd2 = cd2;
        }
        @Override
        public void run() {
            try {
                cd1.await();
                System.out.println("threadName is " + Thread.currentThread().getName());
                cd2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


//    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(new _1023_1());
//        t1.start();
//        t1.join();  // main线程调用t1.join()/ti.join(millis)， main线程进入wait状态，等线程1执行完毕/等待时间到，main线程进入可运行状态
//        Thread t2 = new Thread(new _1023_2());
//        t2.start();
//        t2.join();
//        System.out.println("this is main");
//    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new _1023_1());
        t1.start();
        Thread t2 = new Thread(new _1023_2());
        t2.start();
        System.out.println("this is main");
        // 输出：
//        this is main
//        this is _1023_2
//        this is _1023_1

        Thread t3 = new Thread(new _1023_1());
        t3.start();
        t3.join();  // main线程调用t1.join()/ti.join(millis)， main线程进入wait状态，等线程1执行完毕/等待时间到，main线程进入可运行状态
        Thread t4 = new Thread(new _1023_2());
        t4.start();
        t4.join();

        // 第三种 使用newSingleThreadScheduledExecutor
        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.submit(new _1023_1());
        executor.submit(new _1023_2());
        executor.shutdown();

        // 第四种 使用CountDownLatch
        CountDownLatch cd1 = new CountDownLatch(0);
        CountDownLatch cd2 = new CountDownLatch(1);
        CountDownLatch cd3 = new CountDownLatch(1);
        Thread t5 = new Thread(new _1023_3(cd1, cd2));
        Thread t6 = new Thread(new _1023_3(cd2, cd3));
        Thread t7 = new Thread(new _1023_3(cd3, cd3));
        t5.start();
        t6.start();
        t7.start();
    }
}
