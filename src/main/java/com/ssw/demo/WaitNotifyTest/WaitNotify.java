package com.ssw.demo.WaitNotifyTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**《Java并发编程的艺术》 ，4-11
 * @author wss
 * @created 2020/8/11 10:11
 * @since 1.0
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }
    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. waiting @ "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();  // wait会释放锁，下面的sout暂时不会执行
                    }catch (InterruptedException e){

                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock. notify @ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}

// 输出结果：
// Thread[WaitThread,5,test] flag is true. waiting @ 10:25:53
// Thread[NotifyThread,5,test]hold lock. notify @ 10:25:54
// Thread[NotifyThread,5,test] hold lock again. sleep @ 10:25:59
// Thread[WaitThread,5,test] flag is false. running @ 10:26:04