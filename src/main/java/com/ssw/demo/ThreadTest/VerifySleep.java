package com.ssw.demo.ThreadTest;

import org.junit.Test;

/**
 * Thread.sleep()不会释放当前线程持有的任何监视器，那
 * @author wss
 * @created 2020/10/15 17:15
 * @since 1.0
 */
public class VerifySleep {
    private static int i = 0;
    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("runable1");
            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                System.out.println("runable1 "+i);
            }
        }
    }

    private static class MyRunnable2 implements Runnable {

        @Override
        public void run() {
            System.out.println("runable2");
            synchronized (this) {
                i--;
                System.out.println("runable2 "+i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        new Thread(new MyRunnable2()).start();
    }
}
