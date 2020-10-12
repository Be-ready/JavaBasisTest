package com.ssw.demo.ThreadTest.Tickets;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable4 implements Runnable {
    private static int num = 100;      // 票数
    Lock lock = new ReentrantLock();   // 可重入锁

    @Override
    public void run() {
        while (true) {
            // 将加锁过程放在try中
            try {
                lock.lock();
                if (num > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + ", 正在卖第" + num-- + "张票!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                // 将关闭锁的过程放在finally中，确保加锁之后的代码出问题时，该锁能关闭
                lock.unlock();
            }
        }
    }

}
