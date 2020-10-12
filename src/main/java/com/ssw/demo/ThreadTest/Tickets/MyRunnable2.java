package com.ssw.demo.ThreadTest.Tickets;

/**
 * 同步代码块形式
 */
public class MyRunnable2 implements Runnable {
    private static int num = 100;  // 票数
    private Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (num > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + ", 正在卖第" + num-- + "张票!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
