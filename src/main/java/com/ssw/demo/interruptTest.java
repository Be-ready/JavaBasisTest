package com.ssw.demo;

import java.util.concurrent.TimeUnit;

/**
 * 测试使用interrupt()方法和标识位中断线程
 *
 * @author wss
 * @created 2020/8/10 16:15
 * @since 1.0
 */
public class interruptTest {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();  // 直接调用interrupt()方法中断线程
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();            // 调用自定义方法中断线程（通过标识位）
    }

    public static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;  // 标识位

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            // 将标识位设置为false,从而中断线程
            on = false;
        }
    }

}
