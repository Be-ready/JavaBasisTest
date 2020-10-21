package com.ssw.demo.ThreadTest.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wss
 * @created 2020/10/15 14:50
 * @since 1.0
 */
public class MyLock implements Runnable{

    private static int num = 0;
    private Lock lock = new ReentrantLock();  // 可重入锁
    @Override
    public void run() {

        // 将加锁步骤放在try catch中，去锁放在finally中
        try{
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                num++;
            }
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyLock());
            t.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("num: " + num);  // 结果不是10000
    }
}
