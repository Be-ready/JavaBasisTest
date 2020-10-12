package com.ssw.demo;

import java.util.concurrent.Callable;

/**
 * @author wss
 * @created 2020/8/18 10:13
 * @since 1.0
 */
public class createThreadTest {

    // 实现Runnable接口
    public static class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("this is a thread created by Runnable");
        }
    }

    // 继承Thread类
    public static class Thread2 extends Thread {
        @Override
        public void run() {
//            super.run();
            System.out.println("this is a thread created by Thread");
        }
    }

    // 实现Callable接口(带返回值)
    public static class Thread3 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
//            return null;
            return 1234;
        }
    }


    public static void main(String[] args) throws Exception {
        Thread1 t1 = new Thread1();
        t1.run();

        Thread2 t2 = new Thread2();
//        t2.start();
        t2.run();

        Thread3 t3 = new Thread3();
        int ret = t3.call();
        System.out.println(ret);
    }
}
