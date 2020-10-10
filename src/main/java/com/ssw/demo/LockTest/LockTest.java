package com.ssw.demo.LockTest;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wss
 * @created 2020/8/18 14:31
 * @since 1.0
 */
public class LockTest {

    static class Ticket implements Runnable {

        private int tickets = 100;
        private Lock lock = new ReentrantLock();  // 可重入锁

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    Thread.sleep(50);
                    if (tickets > 0) {
                        // --tickets：先对tickets-1,再使用tickets的值
                        System.out.println(Thread.currentThread().getName() +
                                "正在售票，余额为：" + (--tickets));
                    }else {
                        System.out.println("票已售完！");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Test
    public void t() {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();

    }

    /**
     * 测试lock的API
     */
    @Test
    public void t1() {

    }
}
